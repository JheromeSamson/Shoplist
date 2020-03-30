package com.academy.shoplist.bean;

public class ImmagineProdotto {
    private String id;
    private byte[] codImmagine;

    public ImmagineProdotto(String id, byte[] codImmagine) {
        this.id=id;
        this.codImmagine = codImmagine;
    }


    public byte[] getCodImmagine() {
        return codImmagine;
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

