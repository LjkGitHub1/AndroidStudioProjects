package com.ljk;

import androidx.annotation.Size;
import androidx.appcompat.app.AppCompatActivity;

import javax.script.*;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ljk.databinding.ActivityMainBinding;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public String input = "";
    public String input1 = "";
    public int str = 0;
    public String res = "0";

    //使用javax.script,需要在app的gradle中添加依赖项implementation 'io.apisense:rhino-android:1.0'
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用viewBinding ，需要在app的gradle中添加viewBinding{enabled = true}
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //获取屏幕信息，因为不会传值到xml中，就暂时没用到
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
//        int widthPixels = outMetrics.widthPixels;
//        int heightPixels = outMetrics.heightPixels;

        //添加所有的点击事件
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //只输入数字就加上去
                    case R.id.s0:
                    case R.id.s1:
                    case R.id.s2:
                    case R.id.s3:
                    case R.id.s4:
                    case R.id.s5:
                    case R.id.s6:
                    case R.id.s7:
                    case R.id.s8:
                    case R.id.s9:
                    case R.id.s_dian:
                        binding.top.append(((Button)v).getText());//显示输入的内容
                        input += ((Button)v).getText().toString();
                        //使用input1来判断学号姓名的输出
                        input1 += ((Button)v).getText().toString();

                        //开始 str==0 方便输出与获取“=”
                        if(str==0){ res = input;}
                        else {
                            try {
                                //使用js中的eval将input中的字符串转化为计算式子，直接得出结果
                                res = engine.eval(input).toString();

                                //res结果总是带小数，比如结果是5，得到的是5.0 为了得到整数5，将其分为左右两部分
                                //右边只有一位小数并且是0的话，就输出左边的整数部分
                                String res_l = res.substring(0,res.indexOf("."));
                                String res_r = res.substring(res.indexOf(".")+1,res.length());
                                if((res_r.length()==1)&&("0".equals(res_r))){

                                    res = res_l;
                                }
                                //提高精度，最多保留5位小数
                                else if(res_r.length()>5){
                                    BigDecimal resr = new BigDecimal(res).setScale(5, BigDecimal.ROUND_HALF_UP);
                                    res = resr.toString();
                                }

                            } catch (ScriptException e) {
                                //eavl中错误则抛出异常
                                e.printStackTrace();
                            }
                        }
                        binding.result.setText("");//只输入数字，结果等于本身
                        binding.result.append("="+res);

                        //输入=1或者=0来输出学号姓名
                        if("=1".equals(input1)){
                            binding.result.setTextSize(30);
                            binding.top.setText("");
                            binding.result.setText("1743205000210");
                        }
                        else if("=0".equals(input1)){
                            binding.top.setText("");
                            binding.result.setText("李金康");
                        }
                        break;
                    case R.id.s_a:
                    case R.id.s_j:
                    case R.id.s_cheng:
                    case R.id.s_chu:
                        binding.top.append(((Button)v).getText());//符号也显示到textview中
                        //输入符号之后 str变化，输出改变
                        str += 2;
                        input = input+((Button)v).getText();//算式字符串也跟着改变

                        //输入符号之后，字体，颜色再变回来，重新点击等号再重新改变
                        binding.top.setTextSize(50);
                        binding.result.setTextSize(40);
                        binding.top.setTextColor(Color.parseColor("#000000"));
                        binding.result.setTextColor(Color.parseColor("#acacac"));
                        break;
                    case R.id.back:
                        //删除上一个输入的内容，就显示input中第一个到倒数第二个字符即可，同时重新赋值input
                        input = input.substring(0,input.length()-1);
                        binding.top.setText(input);

                        //删除上一个数字的时候，重新计算之前的式子输出
                        try {
                            String s = input.substring(input.length()-1,input.length());
                            if(("+".equals(s))|("-".equals(s))|("*".equals(s))|("/".equals(s))) {
                                res =  engine.eval(input+0).toString();
                            }
                            res =  engine.eval(input).toString();
                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }
                        //删除输入之后，字体，颜色再变回来，重新点击等号再重新改变
                        binding.top.setTextSize(50);
                        binding.result.setTextSize(40);
                        binding.top.setTextColor(Color.parseColor("#000000"));
                        binding.result.setTextColor(Color.parseColor("#acacac"));
                        binding.result.setText("");//只输入数字，结果等于本身
                        binding.result.append("="+res);
                        break;
                    //清空当前所有内容
                    case R.id.clear:
                        //清除显示
                        binding.top.setText("");
                        //输出置为初始状态0
                        binding.result.setText("0");
                        //清空接收输入的input和显示学号姓名的input1
                        input = "";
                        input1 = "";
                        //str回到初值
                        str = 0;
                        //字体颜色恢复
                        binding.top.setTextSize(50);
                        binding.result.setTextSize(40);
                        binding.top.setTextColor(Color.parseColor("#000000"));
                        binding.result.setTextColor(Color.parseColor("#acacac"));
                        break;
                    case R.id.s_deng:
                        //一来就输入等号（通过str是否等于0判断），存到input1中
                        if(str==0){
                            input1 += ((Button)v).getText().toString();
                            binding.result.setText(input1);
                            str += 1;
                        }
                        //对于结算结果点击等号的时候，上下文本框交换颜色与字体大小
                        else{
                            binding.top.setTextSize(40);
                            binding.result.setTextSize(50);
                            binding.top.setTextColor(Color.parseColor("#acacac"));
                            binding.result.setTextColor(Color.parseColor("#000000"));//不可以简写“000” 程序会闪退
                        }
                        break;
                }



            }
        };
        //加入所有的id
        binding.s0.setOnClickListener(onClickListener);
        binding.s1.setOnClickListener(onClickListener);
        binding.s2.setOnClickListener(onClickListener);
        binding.s3.setOnClickListener(onClickListener);
        binding.s4.setOnClickListener(onClickListener);
        binding.s5.setOnClickListener(onClickListener);
        binding.s6.setOnClickListener(onClickListener);
        binding.s7.setOnClickListener(onClickListener);
        binding.s8.setOnClickListener(onClickListener);
        binding.s9.setOnClickListener(onClickListener);
        binding.sDian.setOnClickListener(onClickListener);
        binding.sA.setOnClickListener(onClickListener);
        binding.sJ.setOnClickListener(onClickListener);
        binding.sCheng.setOnClickListener(onClickListener);
        binding.sChu.setOnClickListener(onClickListener);
        binding.sDeng.setOnClickListener(onClickListener);
        binding.back.setOnClickListener(onClickListener);
        binding.clear.setOnClickListener(onClickListener);
        binding.top.setOnClickListener(onClickListener);
        binding.result.setOnClickListener(onClickListener);
    }
}
