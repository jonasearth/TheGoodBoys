package dev.jonaslee.thegoodboys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dev.jonaslee.thegoodboys.DAO.Control;
import dev.jonaslee.thegoodboys.services.GetImage;
import dev.jonaslee.thegoodboys.services.HttpService;
import dev.jonaslee.thegoodboys.services.Jogadores_serv;
import dev.jonaslee.thegoodboys.services.ListaJogadoresAdapter;

public class Jogadores extends AppCompatActivity {
private Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);


    }


    @Override
    protected void onResume() {
        super.onResume();
        final ArrayList[] lista = {new ArrayList<Jogadores_serv>()};
        final ListView[] listView = {(ListView) findViewById(R.id.jogadores_lista)};
        ListaJogadoresAdapter jogadoresAdapter = new ListaJogadoresAdapter(this, lista[0]);
        listView[0].setAdapter(jogadoresAdapter);

        Button btnseach = findViewById(R.id.btn_seach_jog);
        final Activity asds = this;
        btnseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 lista[0] = new ArrayList<Jogadores_serv>();
                ListaJogadoresAdapter jogadoresAdapter = new ListaJogadoresAdapter(asds, lista[0]);
                listView[0].setAdapter(jogadoresAdapter);


                String jog = "";
                EditText jogador = findViewById(R.id.et_seach_jog);
                if (jogador.getText().toString().isEmpty()){
                    jog = "?type=seach&ref=jogadores&quant=all";
                }else{
                    jog = "?type=seach&ref=jogadores&quant=" + jogador.getText().toString() ;
                }
                try {
                    Control retorno = new HttpService(jog).execute().get();
                    if (retorno.getStatus().length() == 5){
                    }else{
                        String resp[] = retorno.getResp().split(",");
                        int i = 0;
                        while(resp.length > i){
                            String subs[] = resp[i].split("~");
                            Bitmap img = null;
                            int id = Integer.parseInt(subs[0]);
                            Bitmap bitimg = new GetImage("http://35.198.59.162/tgb/fotos/" + id + ".jpg").execute().get();
                            String nome = subs[1].replace("%20", " ");
                            String pos = subs[2].replace("%20", " ");

                            lista[0].add(new Jogadores_serv(id, bitimg, nome, pos));
                            i++;
                        }
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jogadoresAdapter = new ListaJogadoresAdapter(asds, lista[0]);
                listView[0] = (ListView) findViewById(R.id.jogadores_lista);
                listView[0].setAdapter(jogadoresAdapter);
            }
        });


        final Jogadores_serv[] jogadores = new Jogadores_serv[1];
        final int[] id_jogador = new int[1];

        listView[0].setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(asds, dev.jonaslee.thegoodboys.Jogador.class);
                jogadores[0] = (Jogadores_serv) lista[0].get(position);
                 id_jogador[0] = jogadores[0].getId();
                Bitmap bm = jogadores[0].getFoto();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG, 100, os);

                Log.d("coisasss", "asdasd " + id_jogador[0]);
                intent.putExtra("id", "" + id_jogador[0]);
                intent.putExtra("foto", os.toByteArray());
                intent.putExtra("nome", jogadores[0].getNome());
                intent.putExtra("posicao", jogadores[0].getPosicao());

                startActivity(intent);
                asds.finish();


            }
        });




    }


    public void seachJogador(View v){



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
