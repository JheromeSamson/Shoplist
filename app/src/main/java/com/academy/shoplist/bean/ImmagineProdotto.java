package com.academy.shoplist.bean;

public class ImmagineProdotto {
    private String id;
    private byte[] codImmagine;
    private String nome;

    public ImmagineProdotto(String id,String nome, byte[] codImmagine) {
        this.id=id;
        this.nome=nome;
        this.codImmagine = codImmagine;
    }


    public byte[] getCodImmagine() {
        return codImmagine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodImmagine(byte[] codImmagine) {
        this.codImmagine = codImmagine;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

