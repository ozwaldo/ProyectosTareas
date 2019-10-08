package edu.itsur.proyectostareas;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    public static final String EXTRA_TAREA_ID =
            "edu.itsur.proyectostareas.tarea_id";

    public static Intent newIntent(
            Context context, UUID tareaId) {

        Intent intent = new Intent(
                context, MainActivity.class);
        intent.putExtra(EXTRA_TAREA_ID, tareaId);
        return intent;

    }

    @Override
    protected Fragment crearFragment() {
        return new TareaFragment();
    }



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(
                R.id.contenedor_fragment);

        if (fragment == null) {
            fragment = new TareaFragment();
            fm.beginTransaction()
                    .add(R.id.contenedor_fragment, fragment)
                    .commit();
        }

    }*/
}
