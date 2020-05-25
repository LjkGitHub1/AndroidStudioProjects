package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.RatingBar);
        final EditText RatingText = (EditText) findViewById(R.id.RatingText);
        final Button btn = (Button) findViewById(R.id.btn);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.Toggle);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.SeekBar);
        final TextView seekText = (TextView) findViewById(R.id.SeekText);

        //监听RatingBar进度
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    RatingText.setText(String.valueOf(rating));
            }
        });

        //点击确认按钮，显示相应的星星
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对输入异常进行处理
                try {
                    float rat = Float.parseFloat(RatingText.getText().toString());
                    ratingBar.setRating(rat);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this,"请输入正确的内容！",Toast.LENGTH_LONG).show();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (toggleButton.isChecked()){
                    //开的时候显示绿色字体
                    seekText.setTextColor(Color.rgb(0,255,0));
                    seekText.setText("当前进度为："+progress+"%");
                }
                if (!toggleButton.isChecked()){
                    //关的时候显示红色字体
                    seekText.setTextColor(Color.rgb(255,0,0));
                    seekText.setText("当前进度为："+progress+"%");
//                    seekText.setText("");
//                    Toast.makeText(MainActivity.this,"进度显示已关闭！",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
