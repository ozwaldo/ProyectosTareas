package edu.itsur.proyectostareas;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Singleton
public class TareaRep {
    private static TareaRep sTareaRep;
    private List<Tarea> mTareas;

    public static TareaRep get(Context context) {
        if (sTareaRep == null) {
            sTareaRep = new TareaRep(context);
        }
        return sTareaRep;
    }
    private TareaRep(Context context) {
        mTareas = new ArrayList<>();
        for (int i = 0; i<100; i++) {
            Tarea tarea = new Tarea();
            tarea.setTitulo("Tarea " + i);
            tarea.setEntregada(i%2==0);
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
