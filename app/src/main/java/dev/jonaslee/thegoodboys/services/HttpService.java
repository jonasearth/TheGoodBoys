package dev.jonaslee.thegoodboys.services;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import dev.jonaslee.thegoodboys.DAO.*;


public class HttpService extends AsyncTask<Void, Void, Control> {

    private final String arg;

    public HttpService (String arg){
        this.arg = arg;
    }

    @Override
    protected Control doInBackground(Void... voids) {
        StringBuilder resp = new StringBuilder();
        try {
            URL url = new URL("http://35.198.59.162/tgb/controle.php" + arg);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resp.append(scanner.next());

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(resp.toString(), Control.class);

    }
}
