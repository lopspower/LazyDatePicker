<img src="/preview/preview_2.gif" alt="sample" title="sample" width="257" height="379" align="right" vspace="50" />

LazyDatePicker
=================

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project to offer an alternative to the native Android Date Picker.

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

KOTLIN
-----

```kotlin
lazyDatePicker.setDateFormat(LazyDatePicker.DateFormat.MM_DD_YYYY)
lazyDatePicker.setMinDate(minDate)
lazyDatePicker.setMaxDate(maxDate)

lazyDatePicker.setOnDatePickListener { dateSelected ->
    //...
}
```

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

OVERRIDE
-----

You can override day, month & year if you want like this in your `strings.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <string name="ldp_day" tools:override="true">D</string>
    <string name="ldp_month" tools:override="true">M</string>
    <string name="ldp_year" tools:override="true">Y</string>
</resources>
```

You can also change the design of the picker by changing the dimensions like this in your `dimens.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <dimen name="lazy_date_picker_width_case" tools:override="true">12dp</dimen>
    <dimen name="lazy_date_picker_height_focus" tools:override="true">2.5dp</dimen>
    <dimen name="lazy_date_picker_width_margin" tools:override="true">1dp</dimen>
    <dimen name="lazy_date_picker_width_space" tools:override="true">6dp</dimen>
</resources>
```

LICENCE
-----

LazyDatePicker by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
