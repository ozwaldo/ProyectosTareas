package edu.itsur.proyectostareas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Date;
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
        try {
            updateIU();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            updateIU();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_tarea_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tarea_nueva:
                Tarea tarea = new Tarea(new Date());
                try {
                    TareaRep.get(getActivity()).addTarea(tarea);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent =
                        TareaPagerActivity.newIntent(
                                getActivity(), tarea.getId());
                startActivity(intent);
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }

    }

    private void updateIU() throws ParseException {
        TareaRep tareaRep = TareaRep.get(getActivity());
        List<Tarea> tareas = tareaRep.getTareas();

        if (mAdapter == null) {
            mAdapter = new TareaAdapter(tareas);
            mTareasRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }


    }

    private class TareaHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTituloTareaView;
        private TextView mFechaTareaView;
        private CheckBox mTareaEntregada;
        private Tarea mTarea;

        public TareaHolder(LayoutInflater inflater,
                           ViewGroup parent){
            super(inflater.inflate(R.layout.lista_item_tarea,
                    parent, false));

            itemView.setOnClickListener(this);

            mTituloTareaView = (TextView)
                    itemView.findViewById(R.id.tarea_titulo);
            mFechaTareaView = (TextView)
                    itemView.findViewById(R.id.fecha_tarea);
            mTareaEntregada = (CheckBox)
                    itemView.findViewById(R.id.check_tarea_entregada_item);

            mTareaEntregada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mTarea.setEntregada(isChecked);
                }
            });
        }

        @Override
        public void onClick(View v) {

//            Intent intent = new Intent(getActivity(),
//                    MainActivity.class);

//            Intent intent = MainActivity.newIntent(
//                    getActivity(), mTarea.getId());

            Intent intent = TareaPagerActivity.newIntent(
                    getActivity(),
                    mTarea.getId()
            );
            startActivity(intent);

//            Toast.makeText(getActivity(),
//                    mTarea.getTitulo(),
//                    Toast.LENGTH_SHORT).show();
        }


        public void bind(Tarea tarea) {
            mTarea = tarea;
            Log.d("DEPURAR",mTarea.getTitulo());
            mTituloTareaView.setText(mTarea.getTitulo());
            mTareaEntregada.setChecked(tarea.isEntregada());
            mFechaTareaView.setText(mTarea.getFechaString());
            /*mFechaTareaView.setText(mTarea.getFecha()
            .toString());*/
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

            return new TareaHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull TareaHolder holder,
                                     int position) {
            Tarea tarea = mTareas.get(position);
            holder.bind(tarea);
        }
        @Override
        public int getItemCount() {
            return mTareas.size();
        }
    }


}
