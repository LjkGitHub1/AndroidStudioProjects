package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {

    Button btn_l,btn_r,commit;
    RadioGroup group1,group2;
    RadioButton day1,day2,day3,day4,date1,date2,date3,date4;
    CheckBox qiu1,qiu2,qiu3,qiu4,component1,component2,component3,component4;
    int t = 1;
    TextView asw1,asw2,asw3,asw4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");
        SQLiteStudioService.instance().start(this);



        btn_l = (Button) findViewById(R.id.btn_l);
        btn_r = (Button) findViewById(R.id.btn_r);
        commit = (Button) findViewById(R.id.commit);

        final LinearLayout l1 = (LinearLayout) findViewById(R.id.t1);
        final LinearLayout l2 = (LinearLayout) findViewById(R.id.t2);
        final LinearLayout l3 = (LinearLayout) findViewById(R.id.t3);
        final LinearLayout l4 = (LinearLayout) findViewById(R.id.t4);
        final LinearLayout aswLayout = (LinearLayout) findViewById(R.id.aswLayout);

        final RadioGroup group1 = (RadioGroup) findViewById(R.id.Group1);
        final RadioGroup group2 = (RadioGroup) findViewById(R.id.Group2);

        day1 = (RadioButton) findViewById(R.id.day1);
        day2 = (RadioButton) findViewById(R.id.day2);
        day3 = (RadioButton) findViewById(R.id.day3);
        day4 = (RadioButton) findViewById(R.id.day4);

        date1 = (RadioButton) findViewById(R.id.date1);
        date2 = (RadioButton) findViewById(R.id.date2);
        date3 = (RadioButton) findViewById(R.id.date3);
        date4 = (RadioButton) findViewById(R.id.date4);

        qiu1 = (CheckBox) findViewById(R.id.qiu1);
        qiu2 = (CheckBox) findViewById(R.id.qiu2);
        qiu3 = (CheckBox) findViewById(R.id.qiu3);
        qiu4 = (CheckBox) findViewById(R.id.qiu4);

        component1 = (CheckBox) findViewById(R.id.component1);
        component2 = (CheckBox) findViewById(R.id.component2);
        component3 = (CheckBox) findViewById(R.id.component3);
        component4 = (CheckBox) findViewById(R.id.component4);

        asw1 = (TextView) findViewById(R.id.asw1);
        asw2 = (TextView) findViewById(R.id.asw2);
        asw3 = (TextView) findViewById(R.id.asw3);
        asw4 = (TextView) findViewById(R.id.asw4);



        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t -= 1;
                btn_r.setVisibility(View.VISIBLE);
                commit.setVisibility(View.GONE);
                if (t>=1 && t<=4) {
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    l4.setVisibility(View.GONE);
                    switch (t) {
                        case 1:
                            btn_l.setVisibility(View.GONE);
                            l1.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            l2.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            l3.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            l4.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        });
        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( t==1 ){
                    if (day1.isChecked()) asw1.setText("1."+day1.getText().toString());
                    if (day2.isChecked()) asw1.setText("1."+day2.getText().toString());
                    if (day3.isChecked()) asw1.setText("1."+day3.getText().toString());
                    if (day4.isChecked()) asw1.setText("1."+day4.getText().toString());
                }
                if ( t==2 ){
                    if (date1.isChecked()) asw2.setText("2."+date1.getText().toString());
                    if (date2.isChecked()) asw2.setText("2."+date2.getText().toString());
                    if (date3.isChecked()) asw2.setText("2."+date3.getText().toString());
                    if (date4.isChecked()) asw2.setText("2."+date4.getText().toString());
                }
                if ( t==3 ){
                    if (qiu1.isChecked()) asw3.append(qiu1.getText().toString()+" ");
                    if (qiu2.isChecked()) asw3.append(qiu2.getText().toString()+" ");
                    if (qiu3.isChecked()) asw3.append(qiu3.getText().toString()+" ");
                    if (qiu4.isChecked()) asw3.append(qiu4.getText().toString()+" ");
                }
                t += 1;
                btn_l.setVisibility(View.VISIBLE);
                if (t>=1 && t<=4) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                switch (t) {
                    case 1:
                        l1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        l2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        l3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        l4.setVisibility(View.VISIBLE);
                        btn_r.setVisibility(View.GONE);
                        commit.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (component1.isChecked()) asw4.append(component1.getText().toString()+" ");
                if (component2.isChecked()) asw4.append(component2.getText().toString()+" ");
                if (component3.isChecked()) asw4.append(component3.getText().toString()+" ");
                if (component4.isChecked()) asw4.append(component4.getText().toString()+" ");

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                btn_l.setVisibility(View.GONE);
                commit.setVisibility(View.GONE);

                aswLayout.setVisibility(View.VISIBLE);
            }
        });

    }



}
