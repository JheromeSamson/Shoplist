package com.academy.shoplist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.intentConstant.DbConstant;

import java.util.ArrayList;

import database.DatabaseManager;

public class ShoplistDatabaseManager extends DatabaseManager {

    private static ShoplistDatabaseManager instance;

    private ShoplistDatabaseManager(Context context) {
        super(context);
    }

    public static synchronized ShoplistDatabaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ShoplistDatabaseManager.class) {
                if (instance == null) {
                    instance = new ShoplistDatabaseManager(context);
                    instance.open();
                }
            }
        }
        return instance;
    }

    public void addProdotto(Prodotto prodotto) {
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DbConstant.PRODOTTI_TABLE_NOME, prodotto.getNome());
            values.put(DbConstant.PRODOTTI_TABLE_DESCRIZIONE, prodotto.getDescrizione());
            database.insert(DbConstant.PRODOTTI_TABLE, null, values);
            Log.i("Elemento inserito ", "Prodotto con nome: " + prodotto.getNome());
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }
  /*  public void modProdotto(Prodotto prodotto)
    {
        ContentValues values = new ContentValues();

    }*/

    public ArrayList<Prodotto> getProdottiByCursor(Cursor cursore) {
        ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
        if (cursore != null &&
                cursore.getCount() != 0) {

            while (cursore.moveToNext()) {
                Prodotto prodotto = new Prodotto();

                prodotto.setNome(cursore.getString(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_NOME)));
                prodotto.setDescrizione(cursore.getString(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_DESCRIZIONE)));
                prodotto.setImmagine(cursore.getInt(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_IMG)));
                listaProdotti.add(prodotto);

            }
            cursore.close();

        } else if (cursore != null) {
            cursore.close();
        }
        return listaProdotti;
    }

    public Cursor getAllProdotti() {
        Cursor cursore = database.query(DbConstant.PRODOTTI_TABLE, null, null, null, null, null, null);
        return cursore;
    }

    public void deleteProdotto(Prodotto prodotto){
        database.beginTransaction();
        try {
            // database.delete(DbCostants.PRODOTTI_TABLE,DbCostants.PRODOTTI_TABLE_NOME,new String[]{nomeProdotto});
            database.delete(DbConstant.PRODOTTI_TABLE, DbConstant.PRODOTTI_TABLE_NOME+"=?", new String[] {prodotto.getNome()});
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }


}
