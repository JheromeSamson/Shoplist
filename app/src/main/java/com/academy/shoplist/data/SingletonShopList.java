package com.academy.shoplist.data;

import com.academy.shoplist.bean.Prodotto;

import java.util.ArrayList;


public class SingletonShopList {

    public ArrayList<Prodotto> prodotto = new ArrayList<>();
    private static SingletonShopList instance;

    public SingletonShopList(){}

    public static synchronized SingletonShopList getInstance() {
        if (instance == null){
            synchronized (SingletonShopList.class){
                if (instance == null)
                instance = new SingletonShopList();
            }
        }
        return instance;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto.add(prodotto);
    }

    public Prodotto getProdottoByPosition(int position){
        return prodotto.get(position);
    }

    public void removeProdotto(int position){
        this.prodotto.remove(position);
    }

    public void setEdit(String toString, String toString1, int position) {
    }
}
