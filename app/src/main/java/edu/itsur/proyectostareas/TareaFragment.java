package edu.itsur.proyectostareas;


// Ejercicio 1. Actualizar checkbox en recyclerview y mainactivity.
// Ejercicio 2. Cambiar formato de fecha y mostrarla en button.
// 21/10/2019

// Práctica Hora: Agregar un botón para almacenar la hora de entrega de la tarea mediante un Time Picker.
// 30/10/2019
// Practica Eliminar: Eliminar una tarea de la
//                    base de datos y agregar hora
// 14/11/2019


//import android.support.v4.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TareaFragment extends Fragment {

    private static final String ARG_TAREA_ID = "tarea_id";
    private static final String DIALOG_FECHA = "DialogFecha";

    private static final int REQUEST_FECHA = 0;
        //****************
    private static final int REQUEST_FOTO = 2;
        //****************
    private Tarea mTarea;
    private EditText mTituloTarea;
    private Button mFechaBoton;
    private CheckBox mTareaEntregada;
    // *******************************
    private ImageButton mFotoBoton;
    private ImageView mFotoView;

    private File mFotoFile;
    // *******************************
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
            //***
            mFotoFile = TareaRep.get(getActivity()).getFotoFile(mTarea);
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
        mFechaBoton.setText(mTarea.getFechaString());
        //mFechaBoton.setText("Fecha de Entrega");
        //mFechaBoton.setEnabled(false);

        mFechaBoton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager =
                        getFragmentManager();
                /*DatePickerFragment dialog =
                        new DatePickerFragment();*/
                DatePickerFragment dialog =
                        DatePickerFragment
                                .newInstance(
                                        mTarea.getFecha());

                dialog.setTargetFragment(
                        TareaFragment.this,
                        REQUEST_FECHA);

                dialog.show(manager, DIALOG_FECHA);
            }
        });

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


        // ****************************************************

        mFotoBoton = (ImageButton) view.findViewById(R.id.tarea_camara);
        final Intent capturarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        PackageManager packageManager = getActivity().getPackageManager();

        boolean tomarFoto =
                mFotoFile != null &&
                        capturarFoto.resolveActivity(packageManager) != null;

        mFotoBoton.setEnabled(tomarFoto);

        if (tomarFoto) {

            Uri uri = FileProvider.getUriForFile(
                    getActivity(),
                    "edu.itsur.proyectostareas.fileprovider",
                    mFotoFile);
            //Uri uri = Uri.fromFile(mFotoFile);
            capturarFoto.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            List<ResolveInfo> camaraAcitivies = getActivity()
                    .getPackageManager().queryIntentActivities(
                            capturarFoto, PackageManager.MATCH_DEFAULT_ONLY
                    );

            for(ResolveInfo activity : camaraAcitivies) {
                getActivity().grantUriPermission(
                        activity.activityInfo.packageName,
                        uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        }

        mFotoBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(capturarFoto,REQUEST_FOTO);
            }
        });

        mFotoView = (ImageView) view.findViewById(R.id.tarea_foto);

        updateFotoView();

        //*****************************************************
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            TareaRep.get(getActivity()).
                    updateTarea(mTarea);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){return;}
        if (requestCode == REQUEST_FECHA){
            Date fecha = (Date) data.
                    getSerializableExtra(
                            DatePickerFragment.EXTRA_FECHA);
            mTarea.setFecha(fecha);
            mFechaBoton.setText(mTarea.getFechaString());
        }else if (requestCode == REQUEST_FOTO) {

            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "edu.itsur.proyectostareas.fileprovider",
                    mFotoFile);

            getActivity().revokeUriPermission(uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updateFotoView();
        }
    }

    private void updateFotoView(){
        if (mFotoFile == null || !mFotoFile.exists()) {
            mFotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = FotoUtils.getScaleBitmap(
                    mFotoFile.getPath(), getActivity());

            mFotoView.setImageBitmap(bitmap);
        }
    }
}
