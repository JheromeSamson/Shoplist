package database;

import com.academy.shoplist.intentConstant.DbConstant;

public class DatabaseTables {
    public static final String SQL_CREATE_PRODOTTO = "CREATE TABLE IF NOT EXISTS '" + DbConstant.PRODOTTI_TABLE + "' ("+
            DbConstant.PRODOTTI_TABLE_ID + " TEXT, " +
            DbConstant.PRODOTTI_TABLE_NOME + " TEXT, " +
            DbConstant.PRODOTTI_TABLE_DESCRIZIONE + " TEXT, " +
            DbConstant.PRODOTTI_TABLE_IMG + " BLOB " +
            " ); ";
}
