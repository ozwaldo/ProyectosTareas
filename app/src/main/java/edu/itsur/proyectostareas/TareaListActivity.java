package edu.itsur.proyectostareas;

import androidx.fragment.app.Fragment;

public class TareaListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment crearFragment() {
        return new TareaListFragment();
    }
}
