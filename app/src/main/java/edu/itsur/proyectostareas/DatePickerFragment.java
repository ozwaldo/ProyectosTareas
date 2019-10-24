package edu.itsur.proyectostareas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_FECHA = "fecha";

    public static final String EXTRA_FECHA =
            "edu.itsur.proyectostareas.fecha";

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

        Date date = (Date) getArguments().
                getSerializable(ARG_FECHA);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity())
            .inflate(R.layout.dialog_fecha,null);

        mDatePicker = (DatePicker) view.findViewById(
                R.id.dialog_fecha_date_picker);
        mDatePicker.init(year, mes, dia, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_titulo)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int year = mDatePicker.getYear();
                                int mes = mDatePicker.getMonth();
                                int dia = mDatePicker.getDayOfMonth();

                                Date fecha = new
                                        GregorianCalendar(year, mes, dia)
                                        .getTime();

                                sendResult(Activity.RESULT_OK, fecha);
                            }
                        }
                )
                .create();
    }

    private void sendResult(int resultCode, Date fecha) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_FECHA, fecha);

        getTargetFragment().onActivityResult(
                getTargetRequestCode(),resultCode,intent);
    }

}
