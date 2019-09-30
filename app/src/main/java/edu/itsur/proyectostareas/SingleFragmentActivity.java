package edu.itsur.proyectostareas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity
        extends AppCompatActivity {

    protected abstract Fragment crearFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(
                R.id.contenedor_fragment);

        if (fragment == null) {
            fragment = crearFragment();
            fm.beginTransaction()
                    .add(R.id.contenedor_fragment, fragment)
                    .commit();
        }
    }
}
