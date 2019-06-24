package dev.jonaslee.thegoodboys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import dev.jonaslee.thegoodboys.DAO.Control;
import dev.jonaslee.thegoodboys.services.HttpService;

public class Jogador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Você é um menino muito curioso :D", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Bundle extras = getIntent().getExtras();
       /* if (extras.getByteArray("foto").length > 1){
            byte[] a = extras. getByteArray("foto");
            Bitmap foto = BitmapFactory.decodeByteArray(a, 0, a.length);
            ImageView ft = findViewById(R.id.img_jogador_deta);
            ft.setImageBitmap(foto);
        }*/

        TextView nome = findViewById(R.id.det_nome_jog);
        nome.setText(extras.getString("nome"));
        TextView p_p = findViewById(R.id.det_pos_prim);
        TextView p_s = findViewById(R.id.det_pos_sec);
        TextView apelido = findViewById(R.id.det_apelido_jog);
        TextView idade = findViewById(R.id.det_idade_jog);
        TextView c_fisico = findViewById(R.id.det_cond_jog);
        TextView atk = findViewById(R.id.det_atk_jog);
        TextView def = findViewById(R.id.det_def_jog);
        TextView vel = findViewById(R.id.det_vel_jog);
        TextView marc = findViewById(R.id.det_marca_jog);
        TextView prec = findViewById(R.id.det_precisao_jog);
        TextView forc = findViewById(R.id.det_forca_jog);
        TextView drible = findViewById(R.id.det_drible_jog);
        TextView p_passe = findViewById(R.id.det_Ppasse_jog);
        TextView dominio = findViewById(R.id.det_dominio_jog);
        TextView p_lanc = findViewById(R.id.det_Plancamento_jog);
        TextView f_chute = findViewById(R.id.det_Fchute_jog);
        TextView p_chute = findViewById(R.id.det_Pchute_jog);

        try {
            Control retorno = new HttpService("?type=seach&ref=jogador&stype=id&id=" + extras.getString("id")).execute().get();
            if (retorno.getStatus().length() == 5){
            }else{
                String args[] = retorno.getResp().split(",");
                String[] posic = extras.getString("posicao").split("%21");
                p_p.setText("POS PRIM: " + posic[0]);
                p_s.setText("POS SEC: " + posic[1]);
                apelido.setText("APELIDO: " + args[0]);
                idade.setText("IDADE: " + args[1]);
                c_fisico.setText("COND FISICO: " + args[2]);
                atk.setText("ATAQUE: " + args[3]);
                def.setText("DEFESA: " + args[4]);
                vel.setText("VELOCIDADE: " + args[5]);
                marc.setText("MARCACÃO: " + args[6]);
                prec.setText("PRECISÃO: " + args[7]);
                forc.setText("FORÇA: " + args[8]);
                drible.setText("DRIBLE: " + args[9]);
                p_passe.setText("P. PASSE: " + args[10]);
                dominio.setText("DOMINIO: " + args[11]);
                p_lanc.setText("P. LANÇAMENTO: " + args[12]);
                f_chute.setText("F. CHUTE: " + args[13]);
                p_chute.setText("P. CHUTE: " + args[14]);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        startActivity(new Intent(this, dev.jonaslee.thegoodboys.Jogadores.class));

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
