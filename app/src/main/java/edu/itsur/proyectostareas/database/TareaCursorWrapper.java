package edu.itsur.proyectostareas.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import edu.itsur.proyectostareas.Tarea;
import edu.itsur.proyectostareas.database.TareaDbSchema.TareaTabla;

public class TareaCursorWrapper extends CursorWrapper {

    public TareaCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Tarea getTarea(){
        String uuidString = getString(getColumnIndex(TareaTabla.Cols.UUID));
        String titulo = getString(getColumnIndex(TareaTabla.Cols.TITULO));
        long fecha = getLong(getColumnIndex(TareaTabla.Cols.FECHA));
        int isEntregada = getInt(getColumnIndex(TareaTabla.Cols.ENTREGADA));

        Tarea tarea = new Tarea(UUID.fromString(uuidString));
        //tarea.setId(UUID.fromString(uuidString));
        tarea.setTitulo(titulo);
        tarea.setFecha(new Date(fecha));
        tarea.setEntregada(isEntregada!=0);

        return tarea;
    }
}









