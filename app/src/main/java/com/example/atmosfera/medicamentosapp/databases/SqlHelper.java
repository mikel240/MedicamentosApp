package com.example.atmosfera.medicamentosapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.atmosfera.medicamentosapp.pojo.Aviso;

import java.util.ArrayList;

public class SqlHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_MEDICATION = "CREATE TABLE medicamento(" +
            "idMedicamento INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "forma INTEGER, " +
            "via TEXT, " +
            "horaPrimeraIngesta TEXT, " +
            "fechaInicioIngesta TEXT, " +
            "duracion INTEGER, " +
            "intervalo INTEGER);";

    private static final String SQL_CREATE_ALERT = "CREATE TABLE aviso(" +
            "idAviso INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idMedicamento INTEGER, " +
            "fechaAviso TEXT, " +
            "horaAviso TEXT, " +
            "tomado INTEGER);";

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


    public ArrayList<Aviso> getAvisosFecha(String fecha) {

        ArrayList<Aviso> result = new ArrayList<>();
        Aviso item;

        // Get access to the database in read mode
        SQLiteDatabase db = getReadableDatabase();

        // Query the table to get the text and author for all existing entries
        Cursor cursor = db.rawQuery("SELECT * FROM medicamento WHERE fechaAviso='" + fecha + "';", null);

        // Go through the resulting cursor
        while (cursor.moveToNext()) {

            // Create a object for the given entry in the database
            item = new Aviso();
            item.setIdAviso(cursor.getInt(0));
            item.setIdMedicamento(cursor.getInt(1));
            item.setFechaAviso(cursor.getString(2));
            item.setHoraAviso(cursor.getString(3));
            item.setTomado(cursor.getInt(4) == 1);

            // Add the object to the result list
            result.add(item);
        }

        // Close cursor and database helper
        cursor.close();
        db.close();

        return result;
    }


    public void addMedicamento(String nombre, int forma, String via, String horaPrimeraIngesta, String fechaInicioIngesta, int duracion, int intervalo) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new item into the table (autoincremental id)
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("forma", forma);
        values.put("via", via);
        values.put("horaPrimeraIngesta", horaPrimeraIngesta);
        values.put("fechaInicioIngesta", fechaInicioIngesta);
        values.put("duracion", duracion);
        values.put("intervalo", intervalo);
        db.insert("medicamento", null, values);

        // Close the database helper
        db.close();
    }

    public void editMedicamento(int medicamento, String nombre, int forma, String via, String horaPrimeraIngesta, String fechaInicioIngesta, int duracion, int intervalo) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Update item
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("forma", forma);
        values.put("via", via);
        values.put("horaPrimeraIngesta", horaPrimeraIngesta);
        values.put("fechaInicioIngesta", fechaInicioIngesta);
        values.put("duracion", duracion);
        values.put("intervalo", intervalo);

        db.update("medicamento", values, "idMedicamento=" + medicamento, null);

        // Close the database helper
        db.close();

        deleteAvisosMedicamento(medicamento);
        createAvisosMedicamento(medicamento, fechaInicioIngesta, duracion, intervalo);
    }

    public void addAvisos(int medicamento, String fecha, String hora, boolean tomado) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new item into the table (autoincremental id)
        ContentValues values = new ContentValues();
        values.put("idMedicamento", medicamento);
        values.put("fechaAviso", fecha);
        values.put("horaAviso", hora);
        values.put("tomado", tomado);

        db.insert("aviso", null, values);

        // Close the database helper
        db.close();
    }

    private void deleteAvisosMedicamento(int medicamento) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Delete all entries from the table
        db.delete("aviso", "idMedicamento=" + medicamento, null);

        // Close the database helper
        db.close();
    }


    private void createAvisosMedicamento(int medicamento, String inicio, int duracion, int intervalo) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values;


        System.out.println("");


        values = new ContentValues();
        values.put("idMedicamento", medicamento);
        values.put("fechaAviso", medicamento);
        values.put("horaAviso", medicamento);
        values.put("tomado", 0);

        db.insert("aviso", null, values);

        // Close the database helper
        db.close();
    }


    public void tester() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT fechaAviso, datetime(fechaAviso) FROM aviso;", null);
        int i = 0;
        while (cursor.moveToNext()) {
            System.out.println("Vasil: " + (i++) + " " + cursor.getString(0) + " " + cursor.getString(1));
        }
        cursor.close();
        db.close();
    }
}
