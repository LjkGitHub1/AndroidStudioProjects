package com.example.scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.scrollview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=1;i<=50;i++){
            binding.text.append("这是第"+i+"行,别删除我！删除我一行，还有千千万万行"+"\n");
        }

    }
}
