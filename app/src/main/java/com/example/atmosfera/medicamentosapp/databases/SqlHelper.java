package com.example.atmosfera.medicamentosapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SqlHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ALERT = "CREATE TABLE aviso(_idAviso INTEGER PRIMARY KEY AUTOINCREMENT, idMedicamento INTEGER, fechaHora TEXT);";
    private static final String SQL_CREATE_MEDICATION = "CREATE TABLE medicamento(_idMedicamento INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, tipo INTEGER, color INTEGER, intervalo INTEGER, fechaInicio TEXT, fechaFin TEXT);";

    // Singleton pattern to centralize access to the database
    private static SqlHelper instance;

    public synchronized static SqlHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SqlHelper(context.getApplicationContext());
        }
        return instance;
    }

    /*
    Create a helper object to manage a database
    */
    private SqlHelper(Context context) {
        // Parameters:
        // context
        // filename of the database, or null for in-memory database
        // factory to create cursor objects, default if null
        // version of the database (upgrades/downgrades existing ones)
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    This method is only called to create the database the first time it is accessed
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create a table
        db.execSQL(SQL_CREATE_MEDICATION);
        db.execSQL(SQL_CREATE_ALERT);
    }

    /*
    This is method is only called when the database needs to be upgraded,
    so it has been left blank
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Upgrading DB from version oldVersion to newVersion
        //db.execSQL("DROP TABLE IF EXISTS aviso");
        //db.execSQL("DROP TABLE IF EXISTS medicamento");
        //onCreate(db);
    }

    /*
    Get ArrayList<HashMap<String,String>> object with all the items stored
    in the database to generate the data source to be later linked to a ListView:
    */
    public ArrayList<HashMap<String, String>> getMedicamentos(Context context) {

        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        HashMap<String, String> item;

        // Get access to the database in read mode
        SQLiteDatabase db = getReadableDatabase();

        // Query the table to get the text and author for all existing entries
        Cursor cursor = db.rawQuery("SELECT * FROM medicamentos;", null);

        // Go through the resulting cursor
        while (cursor.moveToNext()) {

            // Create a HashMap<String, String> object for the given entry in the database
            item = new HashMap<>();
            item.put("name", cursor.getString(0));
            item.put("medicamento", cursor.getString(1));

            // Add the object to the result list
            result.add(item);
        }

        // Close cursor and database helper
        cursor.close();
        db.close();

        return result;
    }

    /*
    Insert a new item into the database
    */
    public void addMedicamento(String name, int medicamento) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new item into the table (autoincremental id)
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("medicamento", medicamento);
        db.insert("medicamentos", null, values);

        // Close the database helper
        db.close();
    }
    /*
    Insert a new item into the database
    */
    public void addAvisos(int medicamento) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new item into the table (autoincremental id)
        ContentValues values = new ContentValues();
        values.put("medicamento", medicamento);
        db.insert("medicamentos", null, values);

        // Close the database helper
        db.close();
    }


    /*
    Remove all entries from the database
    */
    public void deleteAllMedicamentos(Context context) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Delete all entries from the table
        db.execSQL("DELETE FROM medicamentos;");

        // Close the database helper
        db.close();
    }

}
