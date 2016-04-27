package com.example.atmosfera.medicamentosapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.atmosfera.medicamentosapp.pojo.Aviso;
import com.example.atmosfera.medicamentosapp.pojo.Medicamento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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


    public ArrayList<Aviso> getAvisos() {

        ArrayList<Aviso> result = new ArrayList<>();
        Aviso iAviso;
        Medicamento iMedicamento;

        // Get access to the database in read mode
        SQLiteDatabase db = getReadableDatabase();

        // Query the table to get the text and author for all existing entries
        Cursor c = db.rawQuery("SELECT * " +
                        "FROM aviso A, medicamento M " +
                        "WHERE A.idMedicamento=M.idMedicamento " +
                        "ORDER BY A.fechaAviso ASC, A.horaAviso ASC, A.idMedicamento ASC;",
                null);

        // Go through the resulting cursor
        while (c.moveToNext()) {

            // Create a object for the given entry in the database
            iAviso = new Aviso();
            iMedicamento = new Medicamento();

            iMedicamento.setIdMedicamento(c.getInt(c.getColumnIndex("idMedicamento")));
            iMedicamento.setNombre(c.getString(c.getColumnIndex("nombre")));
            iMedicamento.setForma(c.getInt(c.getColumnIndex("forma")));
            iMedicamento.setVia(c.getString(c.getColumnIndex("via")));
            iMedicamento.setHoraPrimeraIngesta(c.getString(c.getColumnIndex("horaPrimeraIngesta")));
            iMedicamento.setFechaInicioIngesta(c.getString(c.getColumnIndex("fechaInicioIngesta")));
            iMedicamento.setDuracion(c.getInt(c.getColumnIndex("duracion")));
            iMedicamento.setIntervalo(c.getInt(c.getColumnIndex("intervalo")));

            iAviso.setIdAviso(c.getInt(c.getColumnIndex("idAviso")));
            iAviso.setIdMedicamento(c.getInt(c.getColumnIndex("idMedicamento")));
            iAviso.setFechaAviso(c.getString(c.getColumnIndex("fechaAviso")));
            iAviso.setHoraAviso(c.getString(c.getColumnIndex("horaAviso")));
            iAviso.setTomado(c.getInt(c.getColumnIndex("tomado")) == 1);

            iAviso.setMedicamento(iMedicamento);

            // Add the object to the result list
            result.add(iAviso);
        }

        // Close cursor and database helper
        c.close();
        db.close();

        return result;
    }


    public ArrayList<Aviso> getAvisosHoy() {

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        String fechaHoy = currentDate.format(today);

        ArrayList<Aviso> result = new ArrayList<>();
        Aviso iAviso;
        Medicamento iMedicamento;

        // Get access to the database in read mode
        SQLiteDatabase db = getReadableDatabase();

        // Query the table to get the text and author for all existing entries
        Cursor c = db.rawQuery("SELECT * " +
                        "FROM aviso A, medicamento M " +
                        "WHERE A.idMedicamento=M.idMedicamento AND A.fechaAviso=\"" + fechaHoy + "\" " +
                        "ORDER BY A.fechaAviso ASC, A.horaAviso ASC, A.idMedicamento ASC;",
                null);

        // Go through the resulting cursor
        while (c.moveToNext()) {

            // Create a object for the given entry in the database
            iAviso = new Aviso();
            iMedicamento = new Medicamento();

            iMedicamento.setIdMedicamento(c.getInt(c.getColumnIndex("idMedicamento")));
            iMedicamento.setNombre(c.getString(c.getColumnIndex("nombre")));
            iMedicamento.setForma(c.getInt(c.getColumnIndex("forma")));
            iMedicamento.setVia(c.getString(c.getColumnIndex("via")));
            iMedicamento.setHoraPrimeraIngesta(c.getString(c.getColumnIndex("horaPrimeraIngesta")));
            iMedicamento.setFechaInicioIngesta(c.getString(c.getColumnIndex("fechaInicioIngesta")));
            iMedicamento.setDuracion(c.getInt(c.getColumnIndex("duracion")));
            iMedicamento.setIntervalo(c.getInt(c.getColumnIndex("intervalo")));

            iAviso.setIdAviso(c.getInt(c.getColumnIndex("idAviso")));
            iAviso.setIdMedicamento(c.getInt(c.getColumnIndex("idMedicamento")));
            iAviso.setFechaAviso(c.getString(c.getColumnIndex("fechaAviso")));
            iAviso.setHoraAviso(c.getString(c.getColumnIndex("horaAviso")));
            iAviso.setTomado(c.getInt(c.getColumnIndex("tomado")) == 1);

            iAviso.setMedicamento(iMedicamento);

            // Add the object to the result list
            result.add(iAviso);
        }

        // Close cursor and database helper
        c.close();
        db.close();

        return result;
    }


    public int addMedicamento(String nombre, int forma, String via, String horaPrimeraIngesta, String fechaInicioIngesta, int duracion, int intervalo) {

        int id = -1;

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new item into the table (autoincremental id)
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("forma", forma);
        cv.put("via", via);
        cv.put("horaPrimeraIngesta", horaPrimeraIngesta);
        cv.put("fechaInicioIngesta", fechaInicioIngesta);
        cv.put("duracion", duracion);
        cv.put("intervalo", intervalo);
        id = (int) db.insert("medicamento", null, cv);

        // Close the database helper
        db.close();

        return id;
    }

    public void editMedicamento(int medicamento, String nombre, int forma, String via, String horaPrimeraIngesta, String fechaInicioIngesta, int duracion, int intervalo) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Update item
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("forma", forma);
        cv.put("via", via);
        cv.put("horaPrimeraIngesta", horaPrimeraIngesta);
        cv.put("fechaInicioIngesta", fechaInicioIngesta);
        cv.put("duracion", duracion);
        cv.put("intervalo", intervalo);

        db.update("medicamento", cv, "idMedicamento=" + medicamento, null);

        // Close the database helper
        db.close();

        deleteAvisosMedicamento(medicamento);
        createAvisosMedicamento(medicamento, horaPrimeraIngesta, fechaInicioIngesta, duracion, intervalo);
    }

    public void editAviso(int aviso, String fecha, String hora, boolean tomado) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Update item
        ContentValues cv = new ContentValues();
        cv.put("fechaAviso", fecha);
        cv.put("horaAviso", hora);
        cv.put("tomado", tomado);

        db.update("medicamento", cv, "idAviso=" + aviso, null);

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


    public void createAvisosMedicamento(int medicamento, String horaInicio, String fechaInicio, int duracion, int intervalo) {

        // Get access to the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        int anyo = Integer.valueOf(fechaInicio.substring(0, 4));
        int mes = Integer.valueOf(fechaInicio.substring(5, 7));
        int dia = Integer.valueOf(fechaInicio.substring(8, 10));
        int hora = Integer.valueOf(horaInicio.substring(0, 2));
        int min = Integer.valueOf(horaInicio.substring(3, 5));
//
//        System.out.println("anyo: " + fechaInicio.substring(0, 4));
//        System.out.println("mes: " + fechaInicio.substring(5, 7));
//        System.out.println("dia: " + fechaInicio.substring(8, 10));
//        System.out.println("hora: " + horaInicio.substring(0, 2));
//        System.out.println("min: " + horaInicio.substring(3, 5));


        String fechaDB, horaDB, auxM, auxD, auxH, auxMin;

        ContentValues cv;

        int i = 0;
        while (i < (duracion * 24)) {

            hora = hora + intervalo;
            if (hora >= 24) {

                hora = hora - 24;
                dia = dia + 1;

                if (dia > 31 && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)) {
                    dia = dia - 31;
                    mes = mes + 1;
                } else if (dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
                    dia = dia - 30;
                    mes = mes + 1;
                } else if (dia > 29 && mes == 2 && ((anyo % 4 == 0 && anyo % 100 != 0) || anyo % 400 == 0)) {
                    dia = dia - 29;
                    mes = mes + 1;
                } else if (dia > 28 && mes == 2 && !((anyo % 4 == 0 && anyo % 100 != 0) || anyo % 400 == 0)) {
                    dia = dia - 28;
                    mes = mes + 1;
                }

                if (mes > 12) {
                    mes = mes - 12;
                    anyo = anyo + 1;
                }

            }

            auxM = (mes < 10) ? "0" + mes : "" + mes;
            auxD = (dia < 10) ? "0" + dia : "" + dia;
            auxH = (hora < 10) ? "0" + hora : "" + hora;
            auxMin = (min < 10) ? "0" + min : "" + min;

            fechaDB = anyo + "/" + auxM + "/" + auxD;
            horaDB = auxH + ":" + auxMin;

            //System.out.println("VASIL: (medicamento, fechaDB, horaDB): (" + medicamento + ", " + fechaDB + ", " + horaDB + ")");

            cv = new ContentValues();
            cv.put("idMedicamento", medicamento);
            cv.put("fechaAviso", fechaDB);
            cv.put("horaAviso", horaDB);
            cv.put("tomado", 0);

            db.insert("aviso", null, cv);
            i = i + intervalo;
        }//fin while

        // Close the database helper
        db.close();
    }


    public void tester() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT fechaAviso, datetime(fechaAviso) FROM aviso;", null);
        int i = 0;
        while (c.moveToNext()) {
            System.out.println("Vasil: " + (i++) + " " + c.getString(0) + " " + c.getString(1));
        }
        c.close();
        db.close();
    }
}
