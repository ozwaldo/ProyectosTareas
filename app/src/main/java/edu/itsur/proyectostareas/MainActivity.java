package edu.itsur.proyectostareas;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {
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
