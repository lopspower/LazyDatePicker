package com.mikhaellopez.lazydatepickersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String DATE_FORMAT = "MM-dd-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define min & max date for sample
        Date minDate = LazyDatePicker.stringToDate("01-01-2016", DATE_FORMAT);
        Date maxDate = LazyDatePicker.stringToDate("12-31-2018", DATE_FORMAT);

        // Init View
        ((TextView) findViewById(R.id.textViewMinDate)).setText(LazyDatePicker.dateToString(minDate, DATE_FORMAT));
        ((TextView) findViewById(R.id.textViewMaxDate)).setText(LazyDatePicker.dateToString(maxDate, DATE_FORMAT));

        // Init LazyDatePicker
        LazyDatePicker lazyDatePicker = findViewById(R.id.lazyDatePicker);
        lazyDatePicker.setMinDate(minDate);
        lazyDatePicker.setMaxDate(maxDate);

        lazyDatePicker.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
            @Override
            public void onDatePick(Date dateSelected) {
                Toast.makeText(MainActivity.this,
                        "Selected date: " + LazyDatePicker.dateToString(dateSelected, DATE_FORMAT),
                        Toast.LENGTH_SHORT).show();
            }
        });

        lazyDatePicker.setOnDateSelectedListener(new LazyDatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Boolean dateSelected) {
                Log.d(MainActivity.class.getSimpleName(), "onDateSelected: " + dateSelected);
            }
        });
    }

}
