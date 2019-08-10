package dev.jonaslee.thegoodboys;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String lstrNomeArq;
        File arq;
        byte[] dados;
        try
        {
            //pega o nome do arquivo a ser gravado
            lstrNomeArq = "teste.txt";

            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
            FileOutputStream fos;

            //transforma o texto digitado em array de bytes
            dados = " joj joasjoajs oasj soaj asojas ".getBytes();

            fos = new FileOutputStream(arq);

            //escreve os dados e fecha o arquivo
            fos.write(dados);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.i("MSG", "Lendo arquivo " +  e.getMessage());
        }





        String lstrNomeArqs;
        File arqs;
        String lstrlinhas;
        try
        {
            //pega o nome do arquivo a ser lido
            lstrNomeArqs = "teste.txt";

            //limpa a caixa de texto que irá receber os dados do arquivo



            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArqs);
            BufferedReader br = new BufferedReader(new FileReader(arq));
            //efetua uma leitura linha a linha do arquivo a carrega
            //a caixa de texto com a informação lida
            while ((lstrlinhas = br.readLine()) != null) {

                Log.i("MSG ala o: ",  lstrlinhas);
            }

        } catch (Exception e) {
            Log.i("MSG", "Lendo arquivo " +  e.getMessage());
        }


    }
}
