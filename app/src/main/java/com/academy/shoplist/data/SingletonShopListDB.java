package com.academy.shoplist.data;

import com.academy.shoplist.bean.Prodotto;

import java.util.ArrayList;


public class SingletonShopListDB {

    public ArrayList<Prodotto> prodotto = new ArrayList<>();
    private static SingletonShopListDB instance;

    public SingletonShopListDB() {
    }

    public static synchronized SingletonShopListDB getInstance() {
        if (instance == null) {
            synchronized (SingletonShopListDB.class) {
                if (instance == null)
                    instance = new SingletonShopListDB();
            }
        }
        return instance;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto.add(prodotto);
    }

    public Prodotto getProdottoByPosition(int position) {
        return prodotto.get(position);
    }

    public void removeProdotto(int position) {
        this.prodotto.remove(position);
    }
}
