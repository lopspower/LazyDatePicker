package com.mikhaellopez.lazydatepickersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String minDate = "01/01/2016";
        String maxDate = "12/31/2018";

        ((TextView) findViewById(R.id.textViewMinDate)).setText(minDate);
        ((TextView) findViewById(R.id.textViewMaxDate)).setText(maxDate);

        LazyDatePicker lazyDatePicker = findViewById(R.id.lazyDatePicker);

        lazyDatePicker.setMinDate(LazyDatePicker.stringToDate(minDate, "MM/dd/yyyy"));
        lazyDatePicker.setMaxDate(LazyDatePicker.stringToDate(maxDate, "MM/dd/yyyy"));

        lazyDatePicker.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
            @Override
            public void onDatePick(Date dateSelected) {
                Toast.makeText(MainActivity.this,
                        "Selected date: " + LazyDatePicker.dateToString(dateSelected, "MM/dd/yyyy"),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
