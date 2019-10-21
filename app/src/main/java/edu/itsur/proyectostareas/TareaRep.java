package edu.itsur.proyectostareas;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// Singleton
public class TareaRep {
    private static TareaRep sTareaRep;
    private List<Tarea> mTareas;

    public static TareaRep get(Context context) throws ParseException {
        if (sTareaRep == null) {
            sTareaRep = new TareaRep(context);
        }
        return sTareaRep;
    }
    private TareaRep(Context context) throws ParseException {
        mTareas = new ArrayList<>();
        for (int i = 0; i<100; i++) {

            Tarea tarea = new Tarea();
            tarea.setTitulo("Tarea " + i);
           // DateFormat formatoFecha = new
           //         SimpleDateFormat("DD/MM/YYYY");
            //Date fecha = formatoFecha.parse("01-01-2019");
            //tarea.setFecha(fecha);
            tarea.setEntregada(i%2==0);
            tarea.setFecha(new Date());
            Log.d("DEPURAR", "Add Tarea");
            mTareas.add(tarea);
        }
    }
    public List<Tarea> getTareas() {
        return mTareas;
    }
    public Tarea getTarea(UUID id) {
        for (Tarea tarea : mTareas){
            if (tarea.getId().equals(id)) {
                return tarea;
            }
        }
        return null;
    }



}
