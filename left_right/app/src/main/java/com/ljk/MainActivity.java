package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        Button btn_l =(Button) findViewById(R.id.btn_l);//绑定左边按钮

        //对左边的按钮进行监听
        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                Intent i = new Intent(MainActivity.this,leftAcitivity.class);
                //启动
                startActivity(i);
            }
        });
    }
}
