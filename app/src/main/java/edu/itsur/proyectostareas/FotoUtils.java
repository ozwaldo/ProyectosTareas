package edu.itsur.proyectostareas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class FotoUtils {
    public static Bitmap getScaleBitmap(String ruta,
                        Activity activity) {
        Point size = new Point();

        activity.getWindowManager().
                getDefaultDisplay().
                getSize(size);

        return getScaleBitmap(ruta, size.x, size.y);
    }

    public static Bitmap getScaleBitmap(
            String ruta, int ancho, int alto){

        BitmapFactory.Options options = new
                BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(ruta, options);

        float srcAlto = options.outHeight;
        float srcAncho = options.outWidth;

        int inSize = 1;
        if (srcAlto > alto || srcAncho > ancho) {
            if (srcAncho > srcAlto) {
                inSize = Math.round(srcAlto/srcAncho);
            } else {
                inSize = Math.round(srcAncho/srcAlto);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSize;

        return BitmapFactory.decodeFile(ruta, options);
    }
}
