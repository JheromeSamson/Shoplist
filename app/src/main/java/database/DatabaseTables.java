package database;

import com.academy.shoplist.intentConstant.DbConstant;

public class DatabaseTables {
    public static final String SQL_CREATE_PRODOTTO = "CREATE TABLE IF NOT EXISTS '" + DbConstant.PRODOTTI_TABLE + "' ("+
            DbConstant.PRODOTTI_TABLE_ID + " TEXT, " +
            DbConstant.PRODOTTI_TABLE_NOME + " TEXT, " +
            DbConstant.PRODOTTI_TABLE_DESCRIZIONE + " TEXT, " +
            DbConstant.PRODOTTI_TABLE_IMG + " TEXT " +
            " ); ";

            DbConstant.FOREIGNKEY_ID + "TEXT" +
    public static final String SQL_CREATE_TABLE_IMAGE ="CREATE TABLE IF NOT EXISTS '" + DbConstant.NAME_TABLE+ "("+
            DbConstant.KEY_NAME + " TEXT," +
            DbConstant.KEY_IMAGE + " BLOB);";

}
