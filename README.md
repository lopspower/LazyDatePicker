LazyDatePicker
=================

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project to offer an alternative to the native Android Date Picker.

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="516" align="right" vspace="50" />

USAGE
-----

To make a lazy date picker add LazyDatePicker in your layout XML and add LazyDatePicker library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:lazydatepicker:1.0.0'
```

XML
-----

```xml
<com.mikhaellopez.lazydatepicker.LazyDatePicker
        android:id="@+id/lazyDatePicker"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:ldp_text_color="@color/primary"
        app:ldp_hint_color="@color/accent"
        app:ldp_date_format="mm-dd-yyyy" />
```

You must use the following properties in your XML to change your LazyDatePicker.


##### Properties:

* `app:ldp_text_color`      (color)     -> default BLACK
* `app:ldp_hint_color`      (color)     -> default GRAY
* `app:ldp_date_format`     (mm-dd-yyyy or dd-mm-yyyy) -> default mm-dd-yyyy

JAVA
-----

```java
LazyDatePicker lazyDatePicker = findViewById(R.id.lazyDatePicker);
lazyDatePicker.setDateFormat(LazyDatePicker.DateFormat.DD_MM_YYYY);
lazyDatePicker.setMinDate(minDate);
lazyDatePicker.setMaxDate(maxDate);

lazyDatePicker.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
    @Override
    public void onDatePick(Date dateSelected) {
        //...
    }
});
```

LICENCE
-----

LazyDatePicker by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
