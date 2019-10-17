package edu.itsur.proyectostareas;

//import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.util.UUID;

public class TareaFragment extends Fragment {

    private static final String ARG_TAREA_ID = "tarea_id";

    private Tarea mTarea;
    private EditText mTituloTarea;
    private Button mFechaBoton;
    private CheckBox mTareaEntregada;

    public static TareaFragment newInstance(UUID tareaId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TAREA_ID, tareaId);

        TareaFragment fragment = new TareaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mTarea = new Tarea();
        /*UUID tareaId = (UUID)
                getActivity().getIntent()
                        .getSerializableExtra(
                                MainActivity.EXTRA_TAREA_ID
                        );*/

        UUID tareaId = (UUID) getArguments().
                getSerializable(ARG_TAREA_ID);

        try {
            mTarea =
                    TareaRep.get(getActivity())
                            .getTarea(tareaId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarea,
                container,false);
        mTituloTarea = (EditText)
                view.findViewById(R.id.tarea_titulo);

        mTituloTarea.setText(mTarea.getTitulo());

        mTituloTarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start,
                    int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start,
                    int before, int count) {
                mTarea.setTitulo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mFechaBoton = (Button)
                view.findViewById(R.id.fecha_entrega_tarea);
       // mFechaBoton.setText(mTarea.getFecha().toString());
        mFechaBoton.setText("Fecha de Entrega");
        mFechaBoton.setEnabled(false);

        mTareaEntregada = (CheckBox)
                view.findViewById(R.id.tarea_entregada);
        mTareaEntregada.setChecked(mTarea.isEntregada());
        mTareaEntregada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // Log.d("DEPURAR", entregada + "");
                mTarea.setEntregada(isChecked);
            }
        });

        return view;
    }
}
