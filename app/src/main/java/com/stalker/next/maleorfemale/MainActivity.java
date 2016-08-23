package com.stalker.next.maleorfemale;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOK;
    private TextView tvDateMale;
    private TextView tvDateFemale;

    private ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = (Button)findViewById(R.id.btnOK);

        tvDateMale = (TextView) findViewById(R.id.tvDateMale);
        tvDateFemale = (TextView) findViewById(R.id.tvDateFemale);

        lvMain = (ListView) findViewById(R.id.lvMain);

        btnOK.setOnClickListener(this);
        tvDateMale.setOnClickListener(this);
        tvDateFemale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tvDateFemale: {
                createDialog(R.id.tvDateFemale);
                break;
            }
            case R.id.tvDateMale: {
                createDialog(R.id.tvDateMale);
                break;
            }
            case R.id.btnOK: {
                String[] array =  calc();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
                lvMain.setAdapter(adapter);
                break;
            }
        }
    }

    private void createDialog(int textViewResult) {
        Bundle bundle = new Bundle();
        bundle.putInt("field", textViewResult);
        DialogFragment dateDialog = new com.stalker.next.maleorfemale.fragments.DatePicker();
        dateDialog.setArguments(bundle);
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    private String[] calc() {

        List<child> list = new ArrayList<>();

        Calendar calMale = Calendar.getInstance();
        Calendar calFemale = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd M yyyy");

        try {
            Date date = format.parse(tvDateMale.getText().toString());
            calMale.setTime(date);

            date = format.parse(tvDateFemale.getText().toString());
            calFemale.setTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int i = 0, j = 0;

        while (i < 15) {
            if (calMale.compareTo(calFemale) == -1) {
                //male
                if (i > 2) {
                    list.add(new child(this, calMale, calFemale, R.string.male));
                }
                j++;
            } else if (calMale.compareTo(calFemale) == 1) {
                //female
                if (i > 2) {
                    list.add(new child(this, calFemale, calMale, R.string.female));
                }
                i++;
            } else {
                i++;
                j++;
            }
        }

        String[] listString = new String[list.size()];

        i = 0;

        for(child item : list) {
            listString[i] = item.toString();
            i++;
        }

        return listString;
    }
}
