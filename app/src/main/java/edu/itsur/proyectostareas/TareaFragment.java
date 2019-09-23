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

public class TareaFragment extends Fragment {
    private Tarea mTarea;
    private EditText mTituloTarea;
    private Button mFechaBoton;
    private CheckBox mTareaEntregada;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mFechaBoton.setText(mTarea.getFecha().toString());
        mFechaBoton.setEnabled(false);

        mTareaEntregada = (CheckBox)
                view.findViewById(R.id.tarea_entregada);

        mTareaEntregada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTarea.setEntregada(isChecked);
            }
        });

        return view;
    }
}
