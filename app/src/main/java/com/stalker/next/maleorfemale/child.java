package com.stalker.next.maleorfemale;

import android.content.Context;

import java.util.Calendar;

/**
 * Created by maslparu on 23.08.2016.
 */
public class child {
    private Calendar beginDate;
    private Calendar endDate;
    private String sex;

    public child(Context context, Calendar beginDate, Calendar endDate, int sex) {

        this.beginDate = beginDate;
        this.endDate = endDate;
        this.sex = context.getResources().getString(sex);
    }

    @Override
    public String toString() {
        return  sex + " " + (beginDate.get(Calendar.MONTH) + 1) + "." + beginDate.get(Calendar.YEAR) + " - " + (endDate.get(Calendar.MONTH) + 1) + "." + endDate.get(Calendar.YEAR);
    }
}
