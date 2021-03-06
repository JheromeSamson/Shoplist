package com.academy.shoplist.bean;

public class Prodotto {
    private String id;
    private String immagine;
    private String nome;
    private String descrizione;

    public Prodotto(String id, String immagine, String nome, String descrizione) {
        this.id = id;
        this.immagine = immagine;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Prodotto() {
        this.immagine = "";
        this.nome = "default";
        this.descrizione = "default";

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
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
