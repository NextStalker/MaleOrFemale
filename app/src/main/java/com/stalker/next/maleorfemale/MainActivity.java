package com.stalker.next.maleorfemale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOK;
    private EditText etDateMale;
    private EditText etDateFemale;

    private ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = (Button)findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);

        etDateMale = (EditText) findViewById(R.id.etDateMale);
        etDateFemale = (EditText) findViewById(R.id.etDateFemale);

        lvMain = (ListView) findViewById(R.id.lvMain);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnOK: {
                String[] array =  calc();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
                lvMain.setAdapter(adapter);
                break;
            }
        }
    }

    private String[] calc() {

        Calendar calMale = GregorianCalendar.getInstance();
        Calendar calFemale = GregorianCalendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date date = format.parse(etDateMale.getText().toString());
            calMale.setTime(date);

            date = format.parse(etDateFemale.getText().toString());
            calFemale.setTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int i = 0, j = 0;

        childList childListObject = new childList();

        while (i < 12) {
            if (calMale.compareTo(calFemale) == -1) {
                //male
                if (i > 3) {
                    childListObject.add(new child(this, calMale, calFemale, R.string.male));
                }
                j++;
                calMale.add(Calendar.YEAR, 3);
            } else if (calMale.compareTo(calFemale) == 1) {
                //female
                if (i > 3) {
                    childListObject.add(new child(this, calFemale, calMale, R.string.female));
                }
                i++;
                calFemale.add(Calendar.YEAR, 4);
            } else {
                i++;
                j++;
                calMale.add(Calendar.YEAR, 3);
                calFemale.add(Calendar.YEAR, 4);
            }
        }

        String[] listString = new String[childListObject.getList().size()];

        i = 0;

        for(child item : childListObject.getList()) {
            listString[i] = item.toString();
            i++;
        }

        return listString;
    }
}
