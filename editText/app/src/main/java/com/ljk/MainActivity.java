package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.ljk.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        //使用viewBinding
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //动态监听文本的输入
        binding.editName.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = binding.editName.getText().toString();
                //输入与名字一致，字体颜色变绿
                if("李金康".equals(name))
                    binding.editName.setTextColor(Color.rgb(0,255,0));
                else
                    binding.editName.setTextColor(Color.rgb(255,0,0));
            }
        });



        //对邮编输入焦点监听
        binding.editYoubian.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){}
                else {
                    String s = binding.editYoubian.getText().toString();//获取输入的邮编
                    int le = s.length();//邮编长度
                    //符合6位输入，输出姓名邮编
                    if(le == 6)
                        binding.text.setText(name+" "+s);
                    //不满足6位，输出错误信息
                    else {
                        if(le<6){
                            binding.text.setText("当前位数有"+le+"位，"+"比标准位数少"+(6-le)+"位。");
                        }
                        else
                            binding.text.setText("当前位数有"+le+"位，"+"比标准位数多"+(le-6)+"位。");
                    }
                }
            }
        });

    }
}
