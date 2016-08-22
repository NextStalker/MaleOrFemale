package com.stalker.next.maleorfemale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int DIALOG_DATE_MALE = 1;
    private final int DIALOG_DATE_FEMALE = 2;

    private Button btnOK;
    private TextView tvResult;
    private TextView tvDateMale;
    private TextView tvDateFemale;

    private Date maleDate;
    private Date femaleDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = (Button)findViewById(R.id.btnOK);

        tvResult = (TextView) findViewById(R.id.tvResult);
        tvDateMale = (TextView) findViewById(R.id.tvDateMale);
        tvDateFemale = (TextView) findViewById(R.id.tvDateFemale);

        btnOK.setOnClickListener(this);
        tvDateMale.setOnClickListener(this);
        tvDateFemale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tvDateFemale: {
                showDialog(DIALOG_DATE_FEMALE);
                break;
            }
            case R.id.tvDateMale: {
                showDialog(DIALOG_DATE_MALE);
                break;
            }
            case R.id.btnOK: {
                calc();
                break;
            }
        }
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE_MALE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBackMale, 1987, 1, 28);
            return tpd;
        } else if (id == DIALOG_DATE_FEMALE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBackFemale, 1990, 4, 5);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBackMale = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            maleDate = new Date(year, monthOfYear, dayOfMonth);

            tvDateMale.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
        }
    };

    DatePickerDialog.OnDateSetListener myCallBackFemale = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            femaleDate = new Date(year, monthOfYear, dayOfMonth);

            tvDateFemale.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
        }
    };

    private void calc() {
        int i = 0, j = 0;

        String text = "";

        while (i < 15) {
            Calendar calMale = Calendar.getInstance();
            calMale.set(Calendar.YEAR, 1987 + (3 * j));
            calMale.set(Calendar.MONTH, Calendar.JANUARY);
            calMale.set(Calendar.DAY_OF_MONTH, 28);

            Calendar calFemale = Calendar.getInstance();
            calFemale.set(Calendar.YEAR, 1990 + (4 * i));
            calFemale.set(Calendar.MONTH, Calendar.APRIL);
            calFemale.set(Calendar.DAY_OF_MONTH, 5);

            if (calMale.compareTo(calFemale) == -1) {
                //male
                if (i > 2) {
                    text += "male " + (calMale.getTime().getMonth() + 1) + "." + calMale.getTime().getYear() + " - " + (calFemale.getTime().getMonth() + 1) + "." + calFemale.getTime().getYear() + "\n";
                }
                j++;
            } else if (calMale.compareTo(calFemale) == 1) {
                //female
                if (i > 2) {
                    text += "female " + (calFemale.getTime().getMonth() + 1) + "." + calFemale.getTime().getYear() + " - " + (calMale.getTime().getMonth() + 1) + "." + calMale.getTime().getYear() + "\n";
                }
                i++;
            } else {
                i++;
                j++;
            }
        }

        tvResult.setText(text);
    }
}
