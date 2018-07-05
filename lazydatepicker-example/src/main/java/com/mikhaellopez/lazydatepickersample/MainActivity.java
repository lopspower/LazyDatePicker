package com.mikhaellopez.lazydatepickersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mikhaellopez.lazydatepicker.LazyDatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String minDate = "01/01/2016";
        String maxDate = "01/01/2018";

        ((TextView) findViewById(R.id.textViewMinDate)).setText(minDate);
        ((TextView) findViewById(R.id.textViewMaxDate)).setText(maxDate);

        LazyDatePicker lazyDatePicker = findViewById(R.id.lazyDatePicker);

        lazyDatePicker.setMinDate(LazyDatePicker.stringToDate(minDate, "dd/MM/yyyy"));
        lazyDatePicker.setMaxDate(LazyDatePicker.stringToDate(maxDate, "dd/MM/yyyy"));
    }

}
