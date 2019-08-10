package dev.jonaslee.thegoodboys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResChurras extends AppCompatActivity {
    ArrayList cont = new ArrayList<Integer>();
    String conteudo;
    TextView cont_churras;
    float quant;
    int  count, carne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_churras);
        final Bundle extras = getIntent().getExtras();

        cont = extras.getIntegerArrayList("cont");
        cont_churras = findViewById(R.id.cont_churras);
        conteudo = "CHURRASCO EM GRUPO É UMA DESGRAÇA MESMO \n\n";
        conteudo += "ACOMPANHAMENTOS \n";
        conteudo += "SAL(FAROFA): " + (Integer.parseInt(cont.get(0).toString()) * 110) + " g\n";
        conteudo += "PAPINHA(feijao tropeiro): " + (Integer.parseInt(cont.get(0).toString()) * 120) + " g\n";
        conteudo += "ARROZ(OPCIONAL): " + (Integer.parseInt(cont.get(0).toString()) * 150) + " g\n";
        conteudo += "VIRGEMGRET(SALDAVEL): " + (Integer.parseInt(cont.get(0).toString()) * 80) + " g\n\n";
        conteudo += "BEBIDAS \n\n";
        if (Integer.parseInt(cont.get(1).toString()) != 0) {
            conteudo += "Vodka: " + (Integer.parseInt(cont.get(1).toString()) * 200) + " ml \n";
            conteudo += "Limões: " + (Integer.parseInt(cont.get(1).toString()) * 1.5) + " lemoens \n";
            conteudo += "Poupa: " + (Integer.parseInt(cont.get(1).toString()) * 1.5) + " porpas \n";
            conteudo += "Leitinho: " + (Integer.parseInt(cont.get(1).toString()) * 150) + " ml \n\n";
        }
        if (Integer.parseInt(cont.get(2).toString()) != 0) {
            conteudo += "Cerva: " + (Integer.parseInt(cont.get(2).toString()) * 6) + " latas\n";
        }
        if (Integer.parseInt(cont.get(3).toString()) != 0) {
            conteudo += "Suco pra relaxar: " + (Integer.parseInt(cont.get(3).toString()) * 300) + " ml\n";
        }
        if (Integer.parseInt(cont.get(4).toString()) != 0) {
            conteudo += "Veneno com gas(refri): " + (Integer.parseInt(cont.get(4).toString()) * 300) + " ml\n";
        }
        if (Integer.parseInt(cont.get(10).toString()) != 0) {
            conteudo += "Agua pro BABAAAA: " + (Integer.parseInt(cont.get(11).toString()) * 500) + " ml\n\n";
        }
        count = 0;
        if (Integer.parseInt(cont.get(5).toString()) != 0){
            count++;
        }
        if (Integer.parseInt(cont.get(6).toString()) != 0){
            count++;
        }
        if (Integer.parseInt(cont.get(7).toString()) != 0){
            count++;
        }
        if (Integer.parseInt(cont.get(8).toString()) != 0){
            count++;
        }
        if (Integer.parseInt(cont.get(9).toString()) != 0){
            count++;
        }
        carne = Integer.parseInt(cont.get(0).toString()) * 400;

        if (Integer.parseInt(cont.get(5).toString()) != 0){
            conteudo += "Calabreuza: " + ((carne / count) * 0.8) + " g\n";
        }
        if (Integer.parseInt(cont.get(6).toString()) != 0){
            conteudo += "Frango: " + ((carne / count) * 0.8) + " g\n";
        }
        if (Integer.parseInt(cont.get(7).toString()) != 0){
            conteudo += "Cupim: " + (carne / count)* 1.3 + " g\n";
        }
        if (Integer.parseInt(cont.get(8).toString()) != 0){
            conteudo += "Picanha: " + (carne / count)* 1.1 + " g\n";
        }
        if (Integer.parseInt(cont.get(9).toString()) != 0){
            conteudo += "Alcatara: " + (carne / count)* 1.1 + " g\n\n";
        }
        conteudo += "em breve app tera o preço estimado <3";
        cont_churras.setText(conteudo);


    }
}
