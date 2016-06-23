package com.titinkurniati.imagemachine;



import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Titin Kurniati on 23-Jun-16.
 */
public class DatePickerCustom extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private TextView textView;

    public DatePickerCustom(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.textView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public void setTextView(TextView textView) {
        this.textView = textView;

    }
}
