package edu.itsur.proyectostareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.itsur.proyectostareas.database.TareaBaseHelper;
import edu.itsur.proyectostareas.database.TareaCursorWrapper;

import static edu.itsur.proyectostareas.database.TareaDbSchema.TareaTabla;

// Singleton
public class TareaRep {

    private static TareaRep sTareaRep;
    private List<Tarea> mTareas;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TareaRep get(Context context) throws ParseException {
        if (sTareaRep == null) {
            sTareaRep = new TareaRep(context);
        }
        return sTareaRep;
    }

    private TareaRep(Context context) throws ParseException {

        mContext = context.getApplicationContext();
        mDatabase = new TareaBaseHelper(context)
            .getWritableDatabase();

        //mTareas = new ArrayList<>();
        /*for (int i = 0; i<100; i++) {
            Tarea tarea = new Tarea();
            tarea.setTitulo("Tarea " + i);
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("dd-MM-yyyy");
            Date date = simpleDateFormat.parse("01-08-1999");
            tarea.setEntregada(i%2==0);
            tarea.setFecha(date);
            Log.d("DEPURAR", "Add Tarea");
            mTareas.add(tarea);
        }*/
    }

    public void addTarea(Tarea tarea) {
        // mTareas.add(tarea);
        ContentValues values = getContentValues(tarea);
        mDatabase.insert(TareaTabla.NOMBRE,
                null,values);
    }

    public void updateTarea(Tarea tarea){
        String uuidString  = tarea.getId().toString();
        ContentValues values = getContentValues(tarea);
        mDatabase.update(
                TareaTabla.NOMBRE,
                values,
                TareaTabla.Cols.UUID + "= ?",
                new String[]{uuidString});
    }

    /*private Cursor queryTareas(String where,
                               String[] argWhere) {*/
    private TareaCursorWrapper queryTareas(String where,
                                     String[] argWhere){
        // SELECT columnas FROM tabla WHERE comparacion GROUP BY .. ORDER BY ...
        Cursor cursor = mDatabase.query(
                TareaTabla.NOMBRE,
                null,
                where,
                argWhere,
                null,
                null,
                null
        );

        return new TareaCursorWrapper(cursor);
    }

    public List<Tarea> getTareas() {
        //return mTareas;
        List<Tarea> tareas = new ArrayList<>();
        TareaCursorWrapper cursor = queryTareas(null,
                null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tareas.add(cursor.getTarea());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tareas;
    }
    public Tarea getTarea(UUID id) {
//        for (Tarea tarea : mTareas){
//            if (tarea.getId().equals(id)) {
//                return tarea;
//            }
//        }
        TareaCursorWrapper cursor = queryTareas(

                TareaTabla.Cols.UUID + "= ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTarea();
        } finally {
            cursor.close();
        }
        // return null;
    }

    private static ContentValues
        getContentValues(Tarea tarea){
        ContentValues values =
                new ContentValues();

        values.put(
                TareaTabla.Cols.UUID,
                    tarea.getId().toString());
        values.put(TareaTabla.Cols.TITULO,
                tarea.getTitulo());
        values.put(TareaTabla.Cols.FECHA,
                tarea.getFecha().getTime());
        values.put(TareaTabla.Cols.ENTREGADA,
                tarea.isEntregada()?1:0);

        return values;
    }

}
