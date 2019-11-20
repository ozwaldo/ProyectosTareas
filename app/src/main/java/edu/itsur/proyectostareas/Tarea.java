package edu.itsur.proyectostareas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Tarea {
    private UUID mId;
    private String mTitulo;
    private Date mFecha;
    private Date mHora; //Tarea
    private boolean mEntregada;

//    public Tarea() {
//        this.mId = UUID.randomUUID();
//    }

    public Tarea(UUID id) {
        mId = id;
    }

    public Tarea(Date fecha) {
        this.mId = UUID.randomUUID();
        this.mFecha = fecha;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public Date getFecha() {
        return mFecha;
    }

    public boolean isEntregada() {
        return mEntregada;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public void setTitulo(String titulo) {
        this.mTitulo = titulo;
    }

    public void setFecha(Date fecha) {
        this.mFecha = fecha;
    }

    public void setEntregada(boolean entregada) {
        this.mEntregada = entregada;
    }

    public String getFechaString() {
        Locale esLocale = new Locale("es", "MX");
        return new SimpleDateFormat(
                "dd MMMM yyyy ", esLocale)
                .format(mFecha);
    }

    public String getFotoNombre() {
        // IMG_UUID.jpg
        return "IMG_" + getId().toString() + ".jpg";
    }
}


