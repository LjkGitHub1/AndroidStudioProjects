package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        final EditText text;
        Button btn;
        int hour;int mint;

        btn = (Button) findViewById(R.id.btn);
        text = (EditText) findViewById(R.id.text);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer hour;
                Integer mint;
                String timestr = text.getText().toString();
                Log.i("ljkbac",timestr);

//                //查看：的位置
//                Integer aaa = timestr.indexOf(":");
//                text.setText(aaa.toString());
                String t = timestr.substring(6,timestr.indexOf(":"));
                String m = timestr.substring(timestr.indexOf(":")+1,timestr.length());
                //对用户没有输入时间的容错处理
                if(!"".equals(t)&&!"".equals(m)) {
                    hour = Integer.parseInt(t);
                    mint = Integer.parseInt(m);
                    //时间选择器本身已经对超过的时间进行容错处理
//                    if (hour>=24){
//                        hour = 23;
//                    }
//                    else if (mint>=60){
//                        mint = 59;
//                    }
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this,"输入错误",Toast.LENGTH_LONG);
                    toast.show();
                    hour = 0;
                    mint = 0;
                }

                //弹窗
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override

                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                //将调整的时间显示到editText中

                                String date = String.format("当前时间为："+"%d:%d", hourOfDay, minute);
                                text.setText(date);

                            }
                        },hour,mint,true);
                timePickerDialog.show();
            }
        });
    }
}
