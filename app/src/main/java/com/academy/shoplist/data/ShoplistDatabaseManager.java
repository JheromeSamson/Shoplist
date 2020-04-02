package com.academy.shoplist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.academy.shoplist.bean.ImmagineProdotto;
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
            values.put(DbConstant.PRODOTTI_TABLE_ID, prodotto.getId());

            values.put(DbConstant.PRODOTTI_TABLE_NOME, prodotto.getNome());
            values.put(DbConstant.PRODOTTI_TABLE_DESCRIZIONE, prodotto.getDescrizione());
            values.put(DbConstant.PRODOTTI_TABLE_IMG, prodotto.getImmagine());
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
                prodotto.setId(cursore.getString(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_ID)));
                prodotto.setNome(cursore.getString(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_NOME)));
                prodotto.setDescrizione(cursore.getString(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_DESCRIZIONE)));
                prodotto.setImmagine(cursore.getString(cursore.getColumnIndex(DbConstant.PRODOTTI_TABLE_IMG)));
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

    public byte[] selectImg(String idImmagine){
        byte[] risultatoQuery={0};

        database.beginTransaction();
        try {

            Cursor c = database.rawQuery(" SELECT " + DbConstant.KEY_IMAGE + " FROM " + DbConstant.NAME_TABLE +
                    " WHERE " + DbConstant.KEY_ID+ " = '" + idImmagine +  "' ;",null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                risultatoQuery= c.getBlob(0);
                c.moveToNext();
            }
                          c.close();

            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
        return risultatoQuery;
    }

    public void deleteProdottoById(String id){
        database.beginTransaction();
        try {
            // database.delete(DbCostants.PRODOTTI_TABLE,DbCostants.PRODOTTI_TABLE_NOME,new String[]{nomeProdotto});
            Log.i("Elemento Eliminato ", "Numero prodotti eliminati: " + database.delete(DbConstant.PRODOTTI_TABLE, DbConstant.PRODOTTI_TABLE_ID+"=?", new String[] {id}));
                    database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }
    public void updateProdotto(Prodotto vecchio,Prodotto nuovo){
        database.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(DbConstant.PRODOTTI_TABLE_NOME, nuovo.getNome());
            values.put(DbConstant.PRODOTTI_TABLE_DESCRIZIONE, nuovo.getDescrizione());
            database.update(DbConstant.PRODOTTI_TABLE, values,  "id = ?",new String []{vecchio.getId()});

            database.setTransactionSuccessful();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            database.endTransaction();
        }
    }
    public void addImmagineProdotto(ImmagineProdotto img){


        try {
            database.beginTransaction();
            ContentValues cv = new  ContentValues();
            cv.put(DbConstant.KEY_ID, img.getId());
            cv.put(DbConstant.KEY_IMAGE,   img.getCodImmagine());
            database.insert( DbConstant.NAME_TABLE, null, cv );
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }


}
