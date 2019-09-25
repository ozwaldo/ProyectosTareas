package edu.itsur.proyectostareas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(
                R.id.contenedor_fragment);

        if (fragment == null) {
            fragment = new TareaFragment();
            fm.beginTransaction()
                    .add(R.id.contenedor_fragment, fragment)
                    .commit();
        }

    }
}
