package com.academy.shoplist.bean;

public class Prodotto {
    private String id;
    private int immagine;
    private String nome;
    private String descrizione;

    public Prodotto(String id, int immagine, String nome, String descrizione) {
        this.id = id;
        this.immagine = immagine;
        this.nome = nome;
        this.descrizione = descrizione;
    }
    public Prodotto(){
        this.immagine =0;
        this.nome = "default";
        this.descrizione = "default";

    }

    public String getId() {
        return id;
    }

    public int getImmagine() {
        return immagine;
    }

    public void setImmagine(int immagine) {
        this.immagine = immagine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


}
