package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class leftAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left);

        Button btn_r =(Button) findViewById(R.id.btn_r);

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束页面
                finish();
            }
        });
    }
}
