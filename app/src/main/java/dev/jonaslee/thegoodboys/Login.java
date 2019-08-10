package dev.jonaslee.thegoodboys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dev.jonaslee.thegoodboys.DAO.Control;
import dev.jonaslee.thegoodboys.services.HttpService;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText login = findViewById(R.id.login);
        final EditText senha = findViewById(R.id.senha);
        final TextView warn = findViewById(R.id.warnl);
        Button btnSend = findViewById(R.id.send);
        final Intent intent = new Intent(this, dev.jonaslee.thegoodboys.MainActivity.class);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Control retorno = new HttpService("?type=login&user=" + login.getText().toString() + "&pass=" + senha.getText().toString()).execute().get();
                    if (retorno.getStatus() == "erro"){
                        warn.setText(retorno.getResp());
                    }else {
                        if (retorno.getStatus().length() == 5) {
                            warn.setText("Usuario ou Senha Invalidos!");
                        } else {
                            warn.setText("Logando com sucesso !!!");
                            MainActivity.user = retorno.getResp().replace("%20", " ");
                            startActivity(intent);
                        }
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this, dev.jonaslee.thegoodboys.Login.class);
        startActivity(in);
    }
}