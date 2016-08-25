package com.stalker.next.maleorfemale;

import android.content.Context;

import java.util.Calendar;

/**
 * Created by maslparu on 23.08.2016.
 */
public class child {
    private String beginDate;
    private String endDate;
    private String sex;

    public child(Context context, Calendar beginDate, Calendar endDate, int sex) {

        String month = getMonthString(beginDate.get(Calendar.MONTH));
        this.beginDate = month + "." + beginDate.get(Calendar.YEAR);

        month = getMonthString(endDate.get(Calendar.MONTH));
        this.endDate = month + "." + endDate.get(Calendar.YEAR);

        this.sex = context.getResources().getString(sex);
    }

    private String getMonthString(int month) {
        month++;
        return (month < 10) ? "0" + month : "" + month;
    }

    @Override
    public String toString() {
        return  sex + " " + beginDate + " - " + endDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
