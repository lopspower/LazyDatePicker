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

/**
 * Copyright (C) 2018 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
public class LazyDatePicker extends RelativeLayout {

    private static final int LENGHT_DATE_COMPLETE = 8;
    private static final String FORMAT_DATE = "ddMMyyyy";

    private EditText editLazyDatePickerReal;
    private LinearLayout layoutLazyDatePicker;
    private TextView textLazyDatePickerDate;

    private TextView textLazyDateDay1;
    private View viewLazyDateDay1;
    private TextView textLazyDateDay2;
    private View viewLazyDateDay2;
    private TextView textLazyDateMonth1;
    private View viewLazyDateMonth1;
    private TextView textLazyDateMonth2;
    private View viewLazyDateMonth2;
    private TextView textLazyDateYear1;
    private View viewLazyDateYear1;
    private TextView textLazyDateYear2;
    private View viewLazyDateYear2;
    private TextView textLazyDateYear3;
    private View viewLazyDateYear3;
    private TextView textLazyDateYear4;
    private View viewLazyDateYear4;

    // Properties
    private String date;
    private boolean keyboardVisible = false;
    private int textColor;
    private int hintColor;
    private boolean shakeAnimationDoing = false;
    private Date minDate;
    private Date maxDate;
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

        textLazyDateDay1 = findViewById(R.id.text_lazy_date_day_1);
        viewLazyDateDay1 = findViewById(R.id.view_lazy_date_day_1);
        textLazyDateDay2 = findViewById(R.id.text_lazy_date_day_2);
        viewLazyDateDay2 = findViewById(R.id.view_lazy_date_day_2);
        textLazyDateMonth1 = findViewById(R.id.text_lazy_date_month_1);
        viewLazyDateMonth1 = findViewById(R.id.view_lazy_date_month_1);
        textLazyDateMonth2 = findViewById(R.id.text_lazy_date_month_2);
        viewLazyDateMonth2 = findViewById(R.id.view_lazy_date_month_2);

        textLazyDateYear1 = findViewById(R.id.text_lazy_date_year_1);
        viewLazyDateYear1 = findViewById(R.id.view_lazy_date_year_1);
        textLazyDateYear2 = findViewById(R.id.text_lazy_date_year_2);
        viewLazyDateYear2 = findViewById(R.id.view_lazy_date_year_2);
        textLazyDateYear3 = findViewById(R.id.text_lazy_date_year_3);
        viewLazyDateYear3 = findViewById(R.id.view_lazy_date_year_3);
        textLazyDateYear4 = findViewById(R.id.text_lazy_date_year_4);
        viewLazyDateYear4 = findViewById(R.id.view_lazy_date_year_4);

        viewLazyDateDay1.setBackgroundColor(hintColor);
        viewLazyDateDay1.setVisibility(View.GONE);
        viewLazyDateDay2.setBackgroundColor(hintColor);
        viewLazyDateDay2.setVisibility(View.GONE);
        viewLazyDateMonth1.setBackgroundColor(hintColor);
        viewLazyDateMonth1.setVisibility(View.GONE);
        viewLazyDateMonth2.setBackgroundColor(hintColor);
        viewLazyDateMonth2.setVisibility(View.GONE);
        viewLazyDateYear1.setBackgroundColor(hintColor);
        viewLazyDateYear1.setVisibility(View.GONE);
        viewLazyDateYear2.setBackgroundColor(hintColor);
        viewLazyDateYear2.setVisibility(View.GONE);
        viewLazyDateYear3.setBackgroundColor(hintColor);
        viewLazyDateYear3.setVisibility(View.GONE);
        viewLazyDateYear4.setBackgroundColor(hintColor);
        viewLazyDateYear4.setVisibility(View.GONE);

        textLazyDateDay1.setText("J");
        textLazyDateDay1.setTextColor(hintColor);
        textLazyDateDay2.setText("J");
        textLazyDateDay2.setTextColor(hintColor);
        textLazyDateMonth1.setText("M");
        textLazyDateMonth1.setTextColor(hintColor);
        textLazyDateMonth2.setText("M");
        textLazyDateMonth2.setTextColor(hintColor);
        textLazyDateYear1.setText("A");
        textLazyDateYear1.setTextColor(hintColor);
        textLazyDateYear2.setText("A");
        textLazyDateYear2.setTextColor(hintColor);
        textLazyDateYear3.setText("A");
        textLazyDateYear3.setTextColor(hintColor);
        textLazyDateYear4.setText("A");
        textLazyDateYear4.setTextColor(hintColor);

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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {

            addKeyboardVisibilityListener(this, new OnKeyboardVisibilityListener() {
                @Override
                public void onVisibilityChange(boolean isVisible) {
                    keyboardVisible = isVisible;
                }
            });

            editLazyDatePickerReal.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    showDate(date, hasFocus);
                    showFullDateLayout(hasFocus);
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
            textLazyDatePickerDate.setText(dateToString(getDate(), "dd MMM yyyy"));

        } else if (layoutLazyDatePicker.getVisibility() == View.INVISIBLE) {
            layoutLazyDatePicker.setVisibility(View.VISIBLE);
            textLazyDatePickerDate.setVisibility(View.GONE);
        }
    }

    private boolean charIsValid(String date, char unicodeChar) {
        if (!Character.isDigit(unicodeChar)) {
            return false;
        }

        int value = Character.getNumericValue(unicodeChar);
        int length = date.length();

        if (length == 0 && value > 3) { // J1
            return false;
        } else if (length == 1 && ((Character.getNumericValue(date.charAt(0)) == 3 && value > 1)
                || (Character.getNumericValue(date.charAt(0)) == 0 && value == 0))) { // J2
            return false;
        } else if (length == 2 && value > 1) { // M1
            return false;
        } else if (length == 3 && ((Character.getNumericValue(date.charAt(2)) == 1 && value > 2)
                || (Character.getNumericValue(date.charAt(2)) == 0 && value == 0))) { // M2
            return false;
        }

        if (length > 3 && minDate != null) {
            StringBuilder dateToCheckTmp = new StringBuilder(date + unicodeChar);
            while (dateToCheckTmp.length() < LENGHT_DATE_COMPLETE) {
                dateToCheckTmp.append("9");
            }
            Date realDateToCheckTmp = stringToDate(dateToCheckTmp.toString(), FORMAT_DATE);

            if (realDateToCheckTmp == null || realDateToCheckTmp.before(minDate)) {
                return false;
            }
        }

        if (length > 3 && maxDate != null) {
            StringBuilder dateToCheckTmp = new StringBuilder(date + unicodeChar);
            while (dateToCheckTmp.length() < LENGHT_DATE_COMPLETE) {
                dateToCheckTmp.append("0");
            }
            Date realDateToCheckTmp = stringToDate(dateToCheckTmp.toString(), FORMAT_DATE);
            return realDateToCheckTmp != null && !realDateToCheckTmp.after(maxDate);
        }

        return true;
    }

    private void showDate(String value, boolean hasFocus) {
        manageViewFocus(hasFocus, value.length());
        setDate(value);
    }

    private void manageViewFocus(boolean hasFocus, int valueLength) {
        if (hasFocus) {
            viewLazyDateDay1.setVisibility(View.VISIBLE);
            viewLazyDateDay2.setVisibility(View.VISIBLE);
            viewLazyDateMonth1.setVisibility(View.VISIBLE);
            viewLazyDateMonth2.setVisibility(View.VISIBLE);
            viewLazyDateYear1.setVisibility(View.VISIBLE);
            viewLazyDateYear2.setVisibility(View.VISIBLE);
            viewLazyDateYear3.setVisibility(View.VISIBLE);
            viewLazyDateYear4.setVisibility(View.VISIBLE);

            switch (valueLength) {
                case 0:
                    viewLazyDateDay1.setVisibility(View.VISIBLE);
                    viewLazyDateDay1.setBackgroundColor(textColor);
                    viewLazyDateDay2.setBackgroundColor(hintColor);
                    break;
                case 1:
                    viewLazyDateDay1.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateDay2.setVisibility(View.VISIBLE);
                    viewLazyDateDay2.setBackgroundColor(textColor);
                    viewLazyDateMonth1.setBackgroundColor(hintColor);
                    break;
                case 2:
                    viewLazyDateDay2.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateMonth1.setVisibility(View.VISIBLE);
                    viewLazyDateMonth1.setBackgroundColor(textColor);
                    viewLazyDateMonth2.setBackgroundColor(hintColor);
                    break;
                case 3:
                    viewLazyDateMonth1.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateMonth2.setVisibility(View.VISIBLE);
                    viewLazyDateMonth2.setBackgroundColor(textColor);
                    viewLazyDateYear1.setBackgroundColor(hintColor);
                    break;
                case 4:
                    viewLazyDateMonth2.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateYear1.setVisibility(View.VISIBLE);
                    viewLazyDateYear1.setBackgroundColor(textColor);
                    viewLazyDateYear2.setBackgroundColor(hintColor);
                    break;
                case 5:
                    viewLazyDateYear1.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateYear2.setVisibility(View.VISIBLE);
                    viewLazyDateYear2.setBackgroundColor(textColor);
                    viewLazyDateYear3.setBackgroundColor(hintColor);
                    break;
                case 6:
                    viewLazyDateYear2.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateYear3.setVisibility(View.VISIBLE);
                    viewLazyDateYear3.setBackgroundColor(textColor);
                    viewLazyDateYear4.setBackgroundColor(hintColor);
                    break;
                case 7:
                    viewLazyDateYear3.setBackgroundColor(Color.TRANSPARENT);
                    viewLazyDateYear4.setVisibility(View.VISIBLE);
                    viewLazyDateYear4.setBackgroundColor(textColor);
                    break;
                case 8:
                    viewLazyDateYear4.setBackgroundColor(Color.TRANSPARENT);
                    break;
            }
        } else {
            viewLazyDateDay1.setVisibility(View.GONE);
            viewLazyDateDay2.setVisibility(View.GONE);
            viewLazyDateMonth1.setVisibility(View.GONE);
            viewLazyDateMonth2.setVisibility(View.GONE);
            viewLazyDateYear1.setVisibility(View.GONE);
            viewLazyDateYear2.setVisibility(View.GONE);
            viewLazyDateYear3.setVisibility(View.GONE);
            viewLazyDateYear4.setVisibility(View.GONE);
        }
    }

    private void setDate(String value) {
        switch (value.length()) {
            case 0:
                textLazyDateDay1.setText("J");
                textLazyDateDay1.setTextColor(hintColor);
                break;
            case 1:
                textLazyDateDay1.setTextColor(textColor);
                textLazyDateDay1.setText(getLetterAt(0, value));
                textLazyDateDay2.setText("J");
                textLazyDateDay2.setTextColor(hintColor);
                break;
            case 2:
                textLazyDateDay2.setTextColor(textColor);
                textLazyDateDay2.setText(getLetterAt(1, value));
                textLazyDateMonth1.setText("M");
                textLazyDateMonth1.setTextColor(hintColor);
                break;
            case 3:
                textLazyDateMonth1.setTextColor(textColor);
                textLazyDateMonth1.setText(getLetterAt(2, value));
                textLazyDateMonth2.setText("M");
                textLazyDateMonth2.setTextColor(hintColor);
                break;
            case 4:
                textLazyDateMonth2.setTextColor(textColor);
                textLazyDateMonth2.setText(getLetterAt(3, value));
                textLazyDateYear1.setText("A");
                textLazyDateYear1.setTextColor(hintColor);
                break;
            case 5:
                textLazyDateYear1.setTextColor(textColor);
                textLazyDateYear1.setText(getLetterAt(4, value));
                textLazyDateYear2.setText("A");
                textLazyDateYear2.setTextColor(hintColor);

                break;
            case 6:
                textLazyDateYear2.setTextColor(textColor);
                textLazyDateYear2.setText(getLetterAt(5, value));
                textLazyDateYear3.setText("A");
                textLazyDateYear3.setTextColor(hintColor);
                break;
            case 7:
                textLazyDateYear3.setTextColor(textColor);
                textLazyDateYear3.setText(getLetterAt(6, value));
                textLazyDateYear4.setText("A");
                textLazyDateYear4.setTextColor(hintColor);
                break;
            case 8:
                textLazyDateYear4.setTextColor(textColor);
                textLazyDateYear4.setText(getLetterAt(7, value));
                break;
        }
    }

    //region PUBLIC METHOD
    public Date getDate() {
        if (date.length() == LENGHT_DATE_COMPLETE) {
            return stringToDate(date, FORMAT_DATE);
        }
        return null;
    }

    public boolean setDate(Date newDate) {
        String tmpDate = dateToString(newDate, FORMAT_DATE);

        if (tmpDate.length() != LENGHT_DATE_COMPLETE
                || (minDate != null && newDate.before(minDate))
                || (maxDate != null && newDate.after(maxDate))) {
            return false;
        }

        this.date = tmpDate;

        textLazyDateDay1.setTextColor(textColor);
        textLazyDateDay1.setText(getLetterAt(0, date));
        textLazyDateDay2.setTextColor(textColor);
        textLazyDateDay2.setText(getLetterAt(1, date));
        textLazyDateMonth1.setTextColor(textColor);
        textLazyDateMonth1.setText(getLetterAt(2, date));
        textLazyDateMonth2.setTextColor(textColor);
        textLazyDateMonth2.setText(getLetterAt(3, date));
        textLazyDateYear1.setTextColor(textColor);
        textLazyDateYear1.setText(getLetterAt(4, date));
        textLazyDateYear2.setTextColor(textColor);
        textLazyDateYear2.setText(getLetterAt(5, date));
        textLazyDateYear3.setTextColor(textColor);
        textLazyDateYear3.setText(getLetterAt(6, date));
        textLazyDateYear4.setTextColor(textColor);
        textLazyDateYear4.setText(getLetterAt(7, date));

        viewLazyDateDay1.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateDay2.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateMonth1.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateMonth2.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateYear1.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateYear2.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateYear3.setBackgroundColor(Color.TRANSPARENT);
        viewLazyDateYear4.setBackgroundColor(Color.TRANSPARENT);

        showFullDateLayout(editLazyDatePickerReal.isFocused());

        return true;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
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
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date stringToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    public interface OnDatePickListener {
        void onDatePick(Date dateSelected);
    }

}
