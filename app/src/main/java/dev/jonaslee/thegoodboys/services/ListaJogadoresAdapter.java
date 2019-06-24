package dev.jonaslee.thegoodboys.services;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.jonaslee.thegoodboys.R;

public class ListaJogadoresAdapter extends ArrayAdapter<Jogadores_serv> {

    private Activity context;
    private ArrayList<Jogadores_serv> jogadores;


    public ListaJogadoresAdapter(Activity context, ArrayList<Jogadores_serv> jogadores) {
        super(context,R.layout.lista_jogadores , jogadores);
        this.context = context;
        this.jogadores = jogadores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = context.getLayoutInflater().inflate(R.layout.lista_jogadores, parent, false);
        Jogadores_serv jogador = jogadores.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_jog);
        TextView nome = (TextView) view.findViewById(R.id.nome_jo);
        TextView posi = (TextView) view.findViewById(R.id.pos_jog);
        String[] posic = jogadores.get(position).getPosicao().split("%21");
        String nposi = "Principal: " + posic[0] + "   Secundario: " + posic[1];
        nome.setText(jogadores.get(position).getNome());
        posi.setText(nposi);
        imageView.setImageBitmap(jogadores.get(position).getFoto());
        return view;
    }

}
