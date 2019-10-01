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

import java.util.List;

// android.support.v4.app.Fragment;
// android.support.v7.widget.Recyclerview

// ListView

public class TareaListFragment extends Fragment {

    private RecyclerView mTareasRecyclerView;
    private TareaAdapter mAdapter;

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


    private void updateIU() {
        TareaRep tareaRep = TareaRep.get(getActivity());
        List<Tarea> tareas = tareaRep.getTareas();

        mAdapter = new TareaAdapter(tareas);
        mTareasRecyclerView.setAdapter(mAdapter);
    }

    private class TareaHolder extends RecyclerView.ViewHolder {

        public TareaHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class TareaAdapter
        extends RecyclerView.Adapter<TareaHolder> {

        private List<Tarea> mTareas;

        public TareaAdapter(List<Tarea> tareas) {
            mTareas = tareas;
        }

        @NonNull
        @Override
        public TareaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(getActivity());

            return new TareaHolder(parent);
        }
        @Override
        public void onBindViewHolder(@NonNull TareaHolder holder, int position) {

        }
        @Override
        public int getItemCount() {
            return mTareas.size();
        }
    }
}
