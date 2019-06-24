package dev.jonaslee.thegoodboys.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetImage extends AsyncTask<Void, Void, Bitmap> {

    private final String arg;

    public GetImage (String arg){
        this.arg = arg;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Log.d("Dentro da vivivivi 3: ", "1");
        StringBuilder resp = new StringBuilder();
        Bitmap img = null;
        try {
             URL url = new URL(arg);
             HttpURLConnection con = (HttpURLConnection) url.openConnection();
             InputStream is = con.getInputStream();
             img = BitmapFactory.decodeStream(is);
            Log.d("Dentro da vivivivi 2: ", "2");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}