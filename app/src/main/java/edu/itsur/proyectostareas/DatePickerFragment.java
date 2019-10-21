package edu.itsur.proyectostareas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_FECHA = "fecha";
    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(Date fecha){
        Bundle args = new Bundle();
        args.putSerializable(ARG_FECHA, fecha);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(
            @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity())
            .inflate(R.layout.dialog_fecha,null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_titulo)
                .setPositiveButton(android.R.string.ok,
                        null)
                .create();
    }



}
