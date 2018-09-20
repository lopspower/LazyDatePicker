package com.mikhaellopez.lazydatepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.CycleInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright (C) 2018 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
public class LazyDatePicker extends RelativeLayout {

    private static final int LENGHT_DATE_COMPLETE = 8;

    private EditText editLazyDatePickerReal;
    private LinearLayout layoutLazyDatePicker;
    private TextView textLazyDatePickerDate;

    private TextView textLazyDate1;
    private View viewLazyDate1;
    private TextView textLazyDate2;
    private View viewLazyDate2;
    private TextView textLazyDate3;
    private View viewLazyDate3;
    private TextView textLazyDate4;
    private View viewLazyDate4;
    private TextView textLazyDate5;
    private View viewLazyDate5;
    private TextView textLazyDate6;
    private View viewLazyDate6;
    private TextView textLazyDate7;
    private View viewLazyDate7;
    private TextView textLazyDate8;
    private View viewLazyDate8;

    // Properties
    private String date;
    private int textColor;
    private int hintColor;
    private Date minDate;
    private Date maxDate;
    private DateFormat dateFormat;
    private boolean keyboardVisible = false;
    private boolean shakeAnimationDoing = false;
    private OnDatePickListener onDatePickListener;

    //region CONSTRUCTORS
    public LazyDatePicker(Context context) {
        this(context, null);
    }

    public LazyDatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LazyDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.layout_lazy_date_picker, this);

        // Load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.LazyDatePicker, defStyleAttr, 0);
        textColor = attributes.getColor(R.styleable.LazyDatePicker_ldp_text_color, Color.BLACK);
        hintColor = attributes.getColor(R.styleable.LazyDatePicker_ldp_hint_color, Color.GRAY);

        int dateFormatValue = attributes.getInteger(R.styleable.LazyDatePicker_ldp_date_format, DateFormat.MM_DD_YYYY.getAttrValue());
        dateFormat = DateFormat.fromValue(dateFormatValue);

        attributes.recycle();
    }
    //endregion

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        date = "";

        editLazyDatePickerReal = findViewById(R.id.edit_lazy_date_picker_real);
        layoutLazyDatePicker = findViewById(R.id.layout_lazy_date_picker);
        textLazyDatePickerDate = findViewById(R.id.text_lazy_date_picker_date);

        textLazyDate1 = findViewById(R.id.text_lazy_date_1);
        viewLazyDate1 = findViewById(R.id.view_lazy_date_1);
        textLazyDate2 = findViewById(R.id.text_lazy_date_2);
        viewLazyDate2 = findViewById(R.id.view_lazy_date_2);
        textLazyDate3 = findViewById(R.id.text_lazy_date_3);
        viewLazyDate3 = findViewById(R.id.view_lazy_date_3);
        textLazyDate4 = findViewById(R.id.text_lazy_date_4);
        viewLazyDate4 = findViewById(R.id.view_lazy_date_4);
        textLazyDate5 = findViewById(R.id.text_lazy_date_5);
        viewLazyDate5 = findViewById(R.id.view_lazy_date_5);
        textLazyDate6 = findViewById(R.id.text_lazy_date_6);
        viewLazyDate6 = findViewById(R.id.view_lazy_date_6);
        textLazyDate7 = findViewById(R.id.text_lazy_date_7);
        viewLazyDate7 = findViewById(R.id.view_lazy_date_7);
        textLazyDate8 = findViewById(R.id.text_lazy_date_8);
        viewLazyDate8 = findViewById(R.id.view_lazy_date_8);

        textLazyDate1.setTextColor(hintColor);
        viewLazyDate1.setBackgroundColor(hintColor);
        viewLazyDate1.setVisibility(View.GONE);
        textLazyDate2.setTextColor(hintColor);
        viewLazyDate2.setBackgroundColor(hintColor);
        viewLazyDate2.setVisibility(View.GONE);
        textLazyDate3.setTextColor(hintColor);
        viewLazyDate3.setBackgroundColor(hintColor);
        viewLazyDate3.setVisibility(View.GONE);
        textLazyDate4.setTextColor(hintColor);
        viewLazyDate4.setBackgroundColor(hintColor);
        viewLazyDate4.setVisibility(View.GONE);
        textLazyDate5.setTextColor(hintColor);
        viewLazyDate5.setBackgroundColor(hintColor);
        viewLazyDate5.setVisibility(View.GONE);
        textLazyDate6.setTextColor(hintColor);
        viewLazyDate6.setBackgroundColor(hintColor);
        viewLazyDate6.setVisibility(View.GONE);
        textLazyDate7.setTextColor(hintColor);
        viewLazyDate7.setBackgroundColor(hintColor);
        viewLazyDate7.setVisibility(View.GONE);
        textLazyDate8.setTextColor(hintColor);
        viewLazyDate8.setBackgroundColor(hintColor);
        viewLazyDate8.setVisibility(View.GONE);

        if (dateFormat == DateFormat.MM_DD_YYYY) {
            textLazyDate1.setText(getContext().getString(R.string.ldp_month));
            textLazyDate2.setText(getContext().getString(R.string.ldp_month));
            textLazyDate3.setText(getContext().getString(R.string.ldp_day));
            textLazyDate4.setText(getContext().getString(R.string.ldp_day));
        } else if (dateFormat == DateFormat.DD_MM_YYYY) {
            textLazyDate1.setText(getContext().getString(R.string.ldp_day));
            textLazyDate2.setText(getContext().getString(R.string.ldp_day));
            textLazyDate3.setText(getContext().getString(R.string.ldp_month));
            textLazyDate4.setText(getContext().getString(R.string.ldp_month));
        }

        textLazyDate5.setText(getContext().getString(R.string.ldp_year));
        textLazyDate6.setText(getContext().getString(R.string.ldp_year));
        textLazyDate7.setText(getContext().getString(R.string.ldp_year));
        textLazyDate8.setText(getContext().getString(R.string.ldp_year));

        textLazyDatePickerDate.setTextColor(textColor);

        findViewById(R.id.btn_lazy_date_picker_on_focus).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editLazyDatePickerReal.requestFocus();
                if (!keyboardVisible) {
                    showKeyboard(getContext());
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus) // onPause() called
        {
            hideKeyBoard(getContext());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {

            addKeyboardVisibilityListener(this, new OnKeyboardVisibilityListener() {
                @Override
                public void onVisibilityChange(boolean isVisible) {
                    if (keyboardVisible != isVisible) {
                        keyboardVisible = isVisible;
                        if (!keyboardVisible && editLazyDatePickerReal.isFocused()) {
                            editLazyDatePickerReal.clearFocus();
                        }
                    }
                }
            });

            editLazyDatePickerReal.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    showDate(date, hasFocus);
                    showFullDateLayout(hasFocus);
                    if (!hasFocus) hideKeyBoard(getContext());
                }
            });

            editLazyDatePickerReal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!shakeAnimationDoing) {
                        if (before > 0) {
                            // Remove last char
                            if (date.length() > 0) {
                                date = date.substring(0, date.length() - 1);
                            }
                        } else if (date.length() < LENGHT_DATE_COMPLETE && s.length() > 0 && charIsValid(date, s.charAt(s.length() - 1))) {
                            char unicodeChar = s.charAt(s.length() - 1);
                            date += unicodeChar;
                            if (date.length() == LENGHT_DATE_COMPLETE && onDatePickListener != null) {
                                onDatePickListener.onDatePick(getDate());
                            }
                        } else {
                            shakeView(layoutLazyDatePicker);
                        }

                        showDate(date, true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    private void showFullDateLayout(boolean hasFocus) {
        if (!hasFocus && date.length() == LENGHT_DATE_COMPLETE) {
            layoutLazyDatePicker.setVisibility(View.INVISIBLE);
            textLazyDatePickerDate.setVisibility(View.VISIBLE);
            textLazyDatePickerDate.setText(dateToString(getDate(), dateFormat.getCompleteFormatValue()));

        } else if (layoutLazyDatePicker.getVisibility() == View.INVISIBLE) {
            layoutLazyDatePicker.setVisibility(View.VISIBLE);
            textLazyDatePickerDate.setVisibility(View.GONE);
        }
    }

    private boolean charIsValid(String date, char unicodeChar) {
        // Check if char is a digit
        if (!Character.isDigit(unicodeChar)) {
            return false;
        }

        int length = date.length();

        // Check Month & Day by dateFormat selected
        int value = Character.getNumericValue(unicodeChar);
        if (dateFormat == DateFormat.MM_DD_YYYY) {
            if (length == 0 && value > 1) { // M1
                return false;
            } else if (length == 1 && ((Character.getNumericValue(date.charAt(0)) == 1 && value > 2)
                    || (Character.getNumericValue(date.charAt(0)) == 0 && value == 0))) { // M2
                return false;
            } else if (length == 2 && value > 3) { // D1
                return false;
            } else if (length == 3 && ((Character.getNumericValue(date.charAt(2)) == 3 && value > 1)
                    || (Character.getNumericValue(date.charAt(2)) == 0 && value == 0))) { // D2
                return false;
            }

        } else if (dateFormat == DateFormat.DD_MM_YYYY) {
            if (length == 0 && value > 3) { // D1
                return false;
            } else if (length == 1 && ((Character.getNumericValue(date.charAt(0)) == 3 && value > 1)
                    || (Character.getNumericValue(date.charAt(0)) == 0 && value == 0))) { // D2
                return false;
            } else if (length == 2 && value > 1) { // M1
                return false;
            } else if (length == 3 && ((Character.getNumericValue(date.charAt(2)) == 1 && value > 2)
                    || (Character.getNumericValue(date.charAt(2)) == 0 && value == 0))) { // M2
                return false;
            }
        }

        // Check if date is between min & max date
        if (length > 3 && minDate != null) {
            StringBuilder dateToCheckTmp = new StringBuilder(date + unicodeChar);
            while (dateToCheckTmp.length() < LENGHT_DATE_COMPLETE) {
                dateToCheckTmp.append("9");
            }
            Date realDateToCheckTmp = stringToDate(dateToCheckTmp.toString(), dateFormat.getValue());

            if (realDateToCheckTmp == null || realDateToCheckTmp.before(minDate)) return false;
        }

        if (length > 3 && maxDate != null) {
            StringBuilder dateToCheckTmp = new StringBuilder(date + unicodeChar);
            while (dateToCheckTmp.length() < LENGHT_DATE_COMPLETE) {
                dateToCheckTmp.append("0");
            }
            Date realDateToCheckTmp = stringToDate(dateToCheckTmp.toString(), dateFormat.getValue());
            if (realDateToCheckTmp == null || realDateToCheckTmp.after(maxDate)) return false;
        }

        if (length > 6) {
            StringBuilder dateToCheckTmp = new StringBuilder(date + unicodeChar);
            while (dateToCheckTmp.length() < LENGHT_DATE_COMPLETE) {
                dateToCheckTmp.append("9");
            }
            Date realDateToCheckTmp = stringToDate(dateToCheckTmp.toString(), dateFormat.getValue());
            return dateToString(realDateToCheckTmp, dateFormat.getValue()).equals(dateToCheckTmp.toString());
        }

        return true;
    }

    private void showDate(String value, boolean hasFocus) {
        manageViewFocus(hasFocus, value.length());
        switch (value.length()) {
            case 0:
                textLazyDate1.setText(getContext().getString(dateFormat == DateFormat.MM_DD_YYYY ? R.string.ldp_month : R.string.ldp_day));
                textLazyDate1.setTextColor(hintColor);
                break;
            case 1:
                textLazyDate1.setTextColor(textColor);
                textLazyDate1.setText(getLetterAt(0, value));
                textLazyDate2.setText(getContext().getString(dateFormat == DateFormat.MM_DD_YYYY ? R.string.ldp_month : R.string.ldp_day));
                textLazyDate2.setTextColor(hintColor);
                break;
            case 2:
                textLazyDate2.setTextColor(textColor);
                textLazyDate2.setText(getLetterAt(1, value));
                textLazyDate3.setText(getContext().getString(dateFormat == DateFormat.MM_DD_YYYY ? R.string.ldp_day : R.string.ldp_month));
                textLazyDate3.setTextColor(hintColor);
                break;
            case 3:
                textLazyDate3.setTextColor(textColor);
                textLazyDate3.setText(getLetterAt(2, value));
                textLazyDate4.setText(getContext().getString(dateFormat == DateFormat.MM_DD_YYYY ? R.string.ldp_day : R.string.ldp_month));
                textLazyDate4.setTextColor(hintColor);
                break;
            case 4:
                textLazyDate4.setTextColor(textColor);
                textLazyDate4.setText(getLetterAt(3, value));
                textLazyDate5.setText(getContext().getString(R.string.ldp_year));
                textLazyDate5.setTextColor(hintColor);
                break;
            case 5:
                textLazyDate5.setTextColor(textColor);
                textLazyDate5.setText(getLetterAt(4, value));
                textLazyDate6.setText(getContext().getString(R.string.ldp_year));
                textLazyDate6.setTextColor(hintColor);

                break;
            case 6:
                textLazyDate6.setTextColor(textColor);
                textLazyDate6.setText(getLetterAt(5, value));
                textLazyDate7.setText(getContext().getString(R.string.ldp_year));
                textLazyDate7.setTextColor(hintColor);
                break;
            case 7:
                textLazyDate7.setTextColor(textColor);
                textLazyDate7.setText(getLetterAt(6, value));
                textLazyDate8.setText(getContext().getString(R.string.ldp_year));
                textLazyDate8.setTextColor(hintColor);
                break;
            case 8:
                textLazyDate8.setTextColor(textColor);
                textLazyDate8.setText(getLetterAt(7, value));
                break;
        }
    }

    private void manageViewFocus(boolean hasFocus, int valueLength) {
        if (hasFocus) {
            viewLazyDate1.setVisibility(View.VISIBLE);
            viewLazyDate2.setVisibility(View.VISIBLE);
            viewLazyDate3.setVisibility(View.VISIBLE);
            viewLazyDate4.setVisibility(View.VISIBLE);
            viewLazyDate5.setVisibility(View.VISIBLE);
            viewLazyDate6.setVisibility(View.VISIBLE);
            viewLazyDate7.setVisibility(View.VISIBLE);
            viewLazyDate8.setVisibility(View.VISIBLE);

            switch (valueLength) {
                case 0:
                    viewLazyDate1.setVisibility(View.VISIBLE);
                    viewLazyDate1.setBackgroundColor(textColor);
                    viewLazyDate2.setBackgroundColor(hintColor);
                    break;
                case 1:
                    viewLazyDate1.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate2.setVisibility(View.VISIBLE);
                    viewLazyDate2.setBackgroundColor(textColor);
                    viewLazyDate3.setBackgroundColor(hintColor);
                    break;
                case 2:
                    viewLazyDate2.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate3.setVisibility(View.VISIBLE);
                    viewLazyDate3.setBackgroundColor(textColor);
                    viewLazyDate4.setBackgroundColor(hintColor);
                    break;
                case 3:
                    viewLazyDate3.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate4.setVisibility(View.VISIBLE);
                    viewLazyDate4.setBackgroundColor(textColor);
                    viewLazyDate5.setBackgroundColor(hintColor);
                    break;
                case 4:
                    viewLazyDate4.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate5.setVisibility(View.VISIBLE);
                    viewLazyDate5.setBackgroundColor(textColor);
                    viewLazyDate6.setBackgroundColor(hintColor);
                    break;
                case 5:
                    viewLazyDate5.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate6.setVisibility(View.VISIBLE);
                    viewLazyDate6.setBackgroundColor(textColor);
                    viewLazyDate7.setBackgroundColor(hintColor);
                    break;
                case 6:
                    viewLazyDate6.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate7.setVisibility(View.VISIBLE);
                    viewLazyDate7.setBackgroundColor(textColor);
                    viewLazyDate8.setBackgroundColor(hintColor);
                    break;
                case 7:
                    viewLazyDate7.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDate8.setVisibility(View.VISIBLE);
                    viewLazyDate8.setBackgroundColor(textColor);
                    break;
                case 8:
                    viewLazyDate8.setBackgroundColor(Color.TRANSPARENT);
                    break;
            }
        } else {
            viewLazyDate1.setVisibility(View.GONE);
            viewLazyDate2.setVisibility(View.GONE);
            viewLazyDate3.setVisibility(View.GONE);
            viewLazyDate4.setVisibility(View.GONE);
            viewLazyDate5.setVisibility(View.GONE);
            viewLazyDate6.setVisibility(View.GONE);
            viewLazyDate7.setVisibility(View.GONE);
            viewLazyDate8.setVisibility(View.GONE);
        }
    }

    //region PUBLIC METHOD
    public Date getDate() {
        if (date.length() == LENGHT_DATE_COMPLETE) {
            return stringToDate(date, dateFormat.getValue());
        }
        return null;
    }

    public boolean setDate(Date newDate) {
        String tmpDate = dateToString(newDate, dateFormat.getValue());

        if (tmpDate.length() != LENGHT_DATE_COMPLETE
                || (minDate != null && newDate.before(minDate))
                || (maxDate != null && newDate.after(maxDate))) {
            return false;
        }

        this.date = tmpDate;

        textLazyDate1.setTextColor(textColor);
        textLazyDate1.setText(getLetterAt(0, date));
        textLazyDate2.setTextColor(textColor);
        textLazyDate2.setText(getLetterAt(1, date));
        textLazyDate3.setTextColor(textColor);
        textLazyDate3.setText(getLetterAt(2, date));
        textLazyDate4.setTextColor(textColor);
        textLazyDate4.setText(getLetterAt(3, date));
        textLazyDate5.setTextColor(textColor);
        textLazyDate5.setText(getLetterAt(4, date));
        textLazyDate6.setTextColor(textColor);
        textLazyDate6.setText(getLetterAt(5, date));
        textLazyDate7.setTextColor(textColor);
        textLazyDate7.setText(getLetterAt(6, date));
        textLazyDate8.setTextColor(textColor);
        textLazyDate8.setText(getLetterAt(7, date));

        viewLazyDate1.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate2.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate3.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate4.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate5.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate6.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate7.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDate8.setBackgroundColor(Color.TRANSPARENT);

        showFullDateLayout(editLazyDatePickerReal.isFocused());

        return true;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
        clear();
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
        clear();
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        clear();
    }

    public void clear() {
        initView();
    }

    public void shake() {
        shakeView(layoutLazyDatePicker);
    }

    public void setOnDatePickListener(OnDatePickListener onDatePickListener) {
        this.onDatePickListener = onDatePickListener;
    }
    //endregion

    //region KEYBOARD
    private void addKeyboardVisibilityListener(final View rootLayout, final OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootLayout.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                boolean isVisible = keypadHeight > screenHeight * 0.15; // 0.15 ratio is perhaps enough to determine keypad height.
                onKeyboardVisibilityListener.onVisibilityChange(isVisible);
            }
        });
    }

    private interface OnKeyboardVisibilityListener {
        void onVisibilityChange(boolean isVisible);
    }

    private void showKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void hideKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
    }
    //endregion

    //region UTILS
    private String getLetterAt(int position, String value) {
        return String.valueOf(value.charAt(position));
    }

    private void shakeView(View view) {
        shakeAnimationDoing = true;
        view.animate()
                .translationX(-15).translationX(15)
                .setDuration(30)
                .setInterpolator(new CycleInterpolator(150 / 30))
                .setDuration(150)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        shakeAnimationDoing = false;
                    }
                })
                .start();
    }

    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    public static Date stringToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format, Locale.getDefault()).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    public interface OnDatePickListener {
        void onDatePick(Date dateSelected);
    }

    public enum DateFormat {
        MM_DD_YYYY,
        DD_MM_YYYY;

        public String getValue() {
            switch (this) {
                case MM_DD_YYYY:
                    return "MMddyyyy";
                case DD_MM_YYYY:
                    return "ddMMyyyy";
            }
            throw new IllegalArgumentException("Not value available for this DateFormat: " + this);
        }

        public String getCompleteFormatValue() {
            switch (this) {
                case MM_DD_YYYY:
                    return "MMM dd yyyy";
                case DD_MM_YYYY:
                    return "dd MMM yyyy";
            }
            throw new IllegalArgumentException("Not value available for this DateFormat: " + this);
        }

        public int getAttrValue() {
            switch (this) {
                case MM_DD_YYYY:
                    return 1;
                case DD_MM_YYYY:
                    return 2;
            }
            throw new IllegalArgumentException("Not value available for this DateFormat: " + this);
        }

        public static DateFormat fromValue(int value) {
            switch (value) {
                case 1:
                    return MM_DD_YYYY;
                case 2:
                    return DD_MM_YYYY;
            }
            throw new IllegalArgumentException("This value is not supported for DateFormat: " + value);
        }
    }

}
