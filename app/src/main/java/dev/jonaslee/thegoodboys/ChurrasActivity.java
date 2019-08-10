package dev.jonaslee.thegoodboys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChurrasActivity extends AppCompatActivity {

    EditText et_vod, et_cerv, et_ref, et_suco, et_baba;
    CheckBox cb_cal, cb_fran, cb_pic, cb_alc, cb_cup, cb_baba;
    Button btnsend;
    Boolean haveError = false;
    String error;
    Activity a = this;
    ArrayList itens = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_churras);
        final EditText et_pess = findViewById(R.id.qt_pes);

        et_pess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
             //   clear();
            }
        });

        btnsend = findViewById(R.id.btn_churras);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_baba = findViewById(R.id.qt_baba);
                et_vod = findViewById(R.id.qt_vodka);
                et_cerv = findViewById(R.id.qt_cerva);
                et_suco = findViewById(R.id.qt_suco);
                et_ref = findViewById(R.id.qt_refri);
                cb_alc = findViewById(R.id.cb_alcatara);
                cb_baba = findViewById(R.id.cb_baba);
                cb_cal = findViewById(R.id.cb_cal);
                cb_cup = findViewById(R.id.cb_cupim);
                cb_fran = findViewById(R.id.cb_frango);
                cb_pic = findViewById(R.id.cb_picanha);

                if (
                        et_pess.getText().toString().isEmpty()
                        || et_cerv.getText().toString().isEmpty()
                        || et_vod.getText().toString().isEmpty()
                        || et_ref.getText().toString().isEmpty()
                        || et_suco.getText().toString().isEmpty()
                        || et_baba.getText().toString().isEmpty()

                ){
                    haveError = true;
                    error += " Tem campo vazio lek! \n";
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                    error = "";
                    haveError = false;
                }else {
                    if (Integer.parseInt(et_cerv.getText().toString()) > Integer.parseInt(et_pess.getText().toString())) {
                        haveError = true;
                        error += " Mais cerveja que pessoas cabeça! \n";
                    }
                    if (Integer.parseInt(et_vod.getText().toString()) > Integer.parseInt(et_pess.getText().toString())) {
                        haveError = true;
                        error += " Mais Vodka que pessoas cabeça! \n";
                    }
                    if (Integer.parseInt(et_suco.getText().toString()) > Integer.parseInt(et_pess.getText().toString())) {
                        haveError = true;
                        error += " Mais Suco que pessoas cabeça! \n";
                    }
                    if (Integer.parseInt(et_ref.getText().toString()) > Integer.parseInt(et_pess.getText().toString())) {
                        haveError = true;
                        error += " Mais Refri que pessoas cabeça! \n";
                    }
                    if (Integer.parseInt(et_baba.getText().toString()) > Integer.parseInt(et_pess.getText().toString())) {
                        haveError = true;
                        error += " Mais Baba que pessoas cabeça! \n";
                    }
                    if (!cb_baba.isChecked() && Integer.parseInt(et_baba.getText().toString()) > 0) {
                        haveError = true;
                        error += "Se tem pessoas pro baba ativa a porra da Box Cabeça! \n";
                    }
                    if (Integer.parseInt(et_baba.getText().toString()) < 8 && cb_baba.isChecked()) {
                        haveError = true;
                        error += "Não tem pessoas o suficiente pro baba cabeça! \n";
                    }
                    if (!cb_fran.isChecked() && !cb_cup.isChecked() && !cb_alc.isChecked() && !cb_pic.isChecked() && !cb_cal.isChecked()) {
                        haveError = true;
                        error += "Vai Fazer churrasco sem carne seu fela da pota?! \n";
                    }
                    if (haveError) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        error = "";
                        haveError = false;
                    } else {
                        //numero de pessoas
                        itens.add(et_pess.getText().toString());

                        //adicionando quantas pessoas bebem cada coisa
                        itens.add(Integer.parseInt(et_vod.getText().toString()));
                        itens.add(Integer.parseInt(et_cerv.getText().toString()));
                        itens.add(Integer.parseInt(et_suco.getText().toString()));
                        itens.add(Integer.parseInt(et_ref.getText().toString()));
                        //passando as carne
                        itens.add(getIntFB(cb_cal.isChecked()));
                        itens.add(getIntFB(cb_fran.isChecked()));
                        itens.add(getIntFB(cb_cup.isChecked()));
                        itens.add(getIntFB(cb_pic.isChecked()));
                        itens.add(getIntFB(cb_alc.isChecked()));
                        //vendo se vai ter baba
                        itens.add(getIntFB(cb_baba.isChecked()));
                        itens.add(Integer.parseInt(et_baba.getText().toString()));
                        Intent intent = new Intent(a, dev.jonaslee.thegoodboys.ResChurras.class);
                        intent.putIntegerArrayListExtra("cont", itens);
                        startActivity(intent);
                    }
                }
            }
        });

    }
    public int getIntFB(boolean lol){
        int lala;
        if (lol){
            lala = 1;
        }else{
            lala = 0;
        }
        return lala;
    }

    public void clear(){
        et_baba = findViewById(R.id.qt_baba);
        et_vod = findViewById(R.id.qt_vodka);
        et_cerv = findViewById(R.id.qt_cerva);
        et_suco = findViewById(R.id.qt_suco);
        et_ref = findViewById(R.id.qt_refri);
        et_suco.setText("");
        et_ref.setText("");
        et_baba.setText("");
        et_vod.setText("");
        et_cerv.setText("");
    }
}
