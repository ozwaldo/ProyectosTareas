package edu.itsur.proyectostareas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.itsur.proyectostareas.database.TareaDbSchema.TareaTabla;

public class TareaBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NOMBRE_DATABASE = "dbtarea.db";

    public TareaBaseHelper(@Nullable Context context) {
        super(context, NOMBRE_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TareaTabla.NOMBRE + "(" +
                "_id integer primary key autoincrement, " +
                TareaTabla.Cols.UUID + ", " +
                TareaTabla.Cols.TITULO + ", " +
                TareaTabla.Cols.FECHA + ", " +
                TareaTabla.Cols.ENTREGADA + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
