package com.ljk.hello;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");
        Log.d("ljk1", "hello");
        Log.i("ljk1", "hello");
        Log.e("ljk1", "hello");
        Log.w("ljk1", "hello");
    }
}
