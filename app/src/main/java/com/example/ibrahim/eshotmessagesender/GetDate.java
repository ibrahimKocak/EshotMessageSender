package com.example.ibrahim.eshotmessagesender;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDate {

    private static Calendar calendar;
    private static SimpleDateFormat sdf;


    @SuppressLint("SimpleDateFormat")
    public static String getDate(boolean withClock, String timestamp) {

        calendar = Calendar.getInstance();

        if(withClock)
            sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        else
            sdf = new SimpleDateFormat("dd-MM-yyyy");

        if(timestamp != null)
            calendar.setTimeInMillis(Long.valueOf(timestamp));

        return String.valueOf(sdf.format(calendar.getTime()));
    }
}
