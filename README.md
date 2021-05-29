LazyDatePicker
=================

<img src="/preview/preview_2.gif" alt="sample" title="sample" width="257" height="379" align="right" vspace="50" />

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Maven Central](https://img.shields.io/maven-central/v/com.mikhaellopez/lazydatepicker.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.mikhaellopez/lazydatepicker)
<br>
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23317-orange.svg)](https://androidweekly.net/issues/issue-317)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project to offer an alternative to the native Android Date Picker.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a lazy date picker add LazyDatePicker in your layout XML and add LazyDatePicker library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:lazydatepicker:1.1.0'
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

| Properties              | Type                     | Default    |
| ----------------------- | -------------------------| ---------- |
| `app:ldp_text_color`    | color                    | BLACK      |
| `app:ldp_hint_color`    | color                    | GRAY       |
| `app:ldp_date_format`   | mm-dd-yyyy or dd-mm-yyyy | mm-dd-yyyy |
| `app:ldp_show_full_date`| boolean                  | true       |

KOTLIN
-----

```kotlin
lazyDatePicker.setDateFormat(LazyDatePicker.DateFormat.MM_DD_YYYY)
lazyDatePicker.setMinDate(minDate)
lazyDatePicker.setMaxDate(maxDate)

// The date when is selected
lazyDatePicker.setOnDatePickListener { dateSelected ->
    //...
}

// True or false when date is selected
lazyDatePicker.setOnDateSelectedListener { dateSelected ->
    //...
}
```

JAVA
-----

```java
LazyDatePicker lazyDatePicker = findViewById(R.id.lazyDatePicker);
lazyDatePicker.setDateFormat(LazyDatePicker.DateFormat.MM_DD_YYYY);
lazyDatePicker.setMinDate(minDate);
lazyDatePicker.setMaxDate(maxDate);

lazyDatePicker.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
    @Override
    public void onDatePick(Date dateSelected) {
        //...
    }
});

lazyDatePicker.setOnDateSelectedListener(new LazyDatePicker.OnDateSelectedListener() {
    @Override
    public void onDateSelected(Boolean dateSelected) {
        //...
    }
});
```

[LOCAL DATE](https://github.com/JakeWharton/ThreeTenABP)
-----

You can used `LazyLocalDatePicker` instead of `LazyDatePicker` to have all method with `LocalDate` instead of `Date`.

### XML:

```xml
<com.mikhaellopez.lazydatepicker.LazyLocalDatePicker
    android:id="@+id/lazyDatePicker"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:ldp_text_color="@color/primary"
    app:ldp_hint_color="@color/accent"
    app:ldp_date_format="mm-dd-yyyy" />
```

### KOTLIN:

```kotlin
lazyLocalDatePicker.setDateFormat(LazyDatePicker.DateFormat.MM_DD_YYYY)
lazyLocalDatePicker.setMinLocalDate(minDate)
lazyLocalDatePicker.setMaxLocalDate(maxDate)

// The localdate when is selected
lazyLocalDatePicker.setOnLocalDatePickListener { localDateSelected ->
    //...
}

// True or false when date is selected
lazyLocalDatePicker.setOnLocalDateSelectedListener { dateSelected ->
    //...
}
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

And to finish you can also completely redefine the layout by keeping the same name `layout_lazy_date_picker.xml` and keep all the ids. You can find the default one here: [**layout_lazy_date_picker.xml**](/lazydatepicker/src/main/res/layout/layout_lazy_date_picker.xml)

SUPPORT ❤️
-----

Find this library useful? Support it by joining [**stargazers**](https://github.com/lopspower/LazyDatePicker/stargazers) for this repository ⭐️
<br/>
And [**follow me**](https://github.com/lopspower?tab=followers) for my next creations 👍

LICENCE
-----

LazyDatePicker by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
