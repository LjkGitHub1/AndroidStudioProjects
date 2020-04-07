package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        Button btn_l = (Button) findViewById(R.id.btn_l);//绑定左边按钮
        Button btn_r = (Button) findViewById(R.id.btn_r);//绑定右边按钮
        final LinearLayout layout_l = (LinearLayout) findViewById(R.id.layout_l);//绑定左边布局
        final LinearLayout layout_r = (LinearLayout) findViewById(R.id.layout_r);//绑定右边布局

        //点击左边按钮，右边隐藏，左边出现
        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_l.setVisibility(View.VISIBLE);
                layout_r.setVisibility(View.GONE);
            }
        });

        //点击右边按钮，左边隐藏，右边出现
        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_r.setVisibility(View.VISIBLE);
                layout_l.setVisibility(View.GONE);
            }
        });
    }
}
