package edu.itsur.proyectostareas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// android.support.v4.app.Fragment;
// android.support.v7.widget.Recyclerview

// ListView

public class TareaListFragment extends Fragment {

    private RecyclerView mTareasRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tarea_list,
                container,false);

        mTareasRecyclerView = (RecyclerView) view.
                findViewById(R.id.tarea_recycler_view);

        mTareasRecyclerView.setLayoutManager(new
                LinearLayoutManager(getActivity()));

        return view;
    }
}
