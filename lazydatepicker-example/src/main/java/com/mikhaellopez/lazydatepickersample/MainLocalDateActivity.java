package com.mikhaellopez.lazydatepickersample;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.lazydatepicker.LazyLocalDatePicker;

import org.threeten.bp.LocalDate;

public class MainLocalDateActivity extends AppCompatActivity {

    private static final String DATE_FORMAT = "MM-dd-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_date_main);

        // Define min & max date for sample
        LocalDate minDate = LazyLocalDatePicker.stringToLocalDate("01-01-2016", DATE_FORMAT);
        LocalDate maxDate = LazyLocalDatePicker.stringToLocalDate("12-31-2018", DATE_FORMAT);

        // Init View
        ((TextView) findViewById(R.id.textViewMinDate)).setText(LazyLocalDatePicker.dateToString(minDate, DATE_FORMAT));
        ((TextView) findViewById(R.id.textViewMaxDate)).setText(LazyLocalDatePicker.dateToString(maxDate, DATE_FORMAT));

        // Init LazyLocalDatePicker
        LazyLocalDatePicker lazyLocalDatePicker = findViewById(R.id.lazyLocalDatePicker);
        lazyLocalDatePicker.setMinLocalDate(minDate);
        lazyLocalDatePicker.setMaxLocalDate(maxDate);

        lazyLocalDatePicker.setOnLocalDatePickListener(new LazyLocalDatePicker.OnLocalDatePickListener() {
            @Override
            public void onLocalDatePick(LocalDate dateSelected) {
                Toast.makeText(MainLocalDateActivity.this,
                        "Selected date: " + LazyLocalDatePicker.dateToString(dateSelected, DATE_FORMAT),
                        Toast.LENGTH_SHORT).show();
            }
        });

        lazyLocalDatePicker.setOnLocalDateSelectedListener(new LazyLocalDatePicker.OnLocalDateSelectedListener() {
            @Override
            public void onLocalDateSelected(Boolean dateSelected) {
                Log.d(MainLocalDateActivity.class.getSimpleName(), "onLocalDateSelected: " + dateSelected);
            }
        });
    }

}
