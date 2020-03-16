package database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "shoplist.db";
    private final static int CURRENT_DB_VERSION = 1;
    protected final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, CURRENT_DB_VERSION);
        this.myContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("createDatabase", "UPGRADE DB FROM " + oldVersion + "TO " + newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= 28)
            db.disableWriteAheadLogging();
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        String myPath = myContext.getDatabasePath(DB_NAME).getPath();
        return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        SQLiteDatabase db = this.getReadableDatabase();
        if (!dbExist)
            createShopListDB(db);
    }

    private boolean checkDataBase() {
        File database = myContext.getDatabasePath(DB_NAME);
        return database.exists();
    }

    private void createShopListDB(SQLiteDatabase database) {
        Log.d("createDataBase", "create database" + DB_NAME);
        database.execSQL(DatabaseTables.SQL_CREATE_PRODOTTO);
    }

    private void dropAllTable(SQLiteDatabase database) {
        Log.d("createDatabase", "DROP ALL TABLES");
    }

    private void dropTable(SQLiteDatabase database, String table) {
        try {
            String dropTable = "DROP TABLE IF EXISTS" + table + ";";
            database.execSQL(dropTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
