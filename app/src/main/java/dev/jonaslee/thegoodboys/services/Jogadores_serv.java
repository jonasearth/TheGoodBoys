package dev.jonaslee.thegoodboys.services;

import android.graphics.Bitmap;

public class Jogadores_serv {
    private int id;
    private Bitmap foto;
    private String nome;
    private String posicao;

    public Jogadores_serv(int id, Bitmap foto, String nome, String posicao){
        this.id = id;
        this.foto = foto;
        this.nome = nome;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}