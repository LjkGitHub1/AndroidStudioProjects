package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    //记录ProgressBar的完成进度
    private int status1 = 0;
    private int status2 = 0;
    private int status3 = 0;
    private int status4 = 0;
    private int status5 = 0;
    private ProgressBar pro1,pro2,pro3,pro4,pro5;
    private  TextView text1,text2,text3,text4,text5;

    //设置随机数
    Random ran = new Random();
//    ran.nextInt(10)+1);//1到10的随机整数


    //创建一个负责更新进度的Handler
    @SuppressLint("HandlerLeak")
    private Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //表明消息是由该程序发送的
            if(msg.what==0x111){
                pro1.setProgress(status1);
                pro2.setProgress(status2);
                pro3.setProgress(status3);
                pro4.setProgress(status4);
                pro5.setProgress(status5);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        Button start = (Button) findViewById(R.id.start);
        final TextView text1 = (TextView) findViewById(R.id.text1);
        final TextView text2 = (TextView) findViewById(R.id.text2);
        final TextView text3 = (TextView) findViewById(R.id.text3);
        final TextView text4 = (TextView) findViewById(R.id.text4);
        final TextView text5 = (TextView) findViewById(R.id.text5);
        pro1 = (ProgressBar) findViewById(R.id.progress1);
        pro2 = (ProgressBar) findViewById(R.id.progress2);
        pro3 = (ProgressBar) findViewById(R.id.progress3);
        pro4 = (ProgressBar) findViewById(R.id.progress4);
        pro5 = (ProgressBar) findViewById(R.id.progress5);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动线程来执行任务
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if (status1>0){
                            status1 = 0;
                            text1.setText("");
                        }
                        while(status1<100){
                            doWork();
                            status1 += ran.nextInt(3)+1;;
                            if (status1>=100){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                                //获取当前时间
                                Date date = new Date(System.currentTimeMillis());
                                text1.setText(simpleDateFormat.format(date)+" 1743205000210");
                            }
                            //发送消息
                            mHandler.sendEmptyMessage(0x111);
                        }
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if (status2>0){
                            status2 = 0;
                            text2.setText("");
                        }
                        while(status2<100){
                            doWork();
                            status2 += ran.nextInt(5)+1;
                            if (status2>=100){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                                //获取当前时间
                                Date date = new Date(System.currentTimeMillis());
                                text2.setText(simpleDateFormat.format(date)+" 李金康");
                            }
                            //发送消息
                            mHandler.sendEmptyMessage(0x111);
                        }
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if (status3>0){
                            status3 = 0;
                            text3.setText("");
                        }
                        while(status3<100){
                            doWork();
                            status3 += ran.nextInt(4)+1;
                            if (status3>=100){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                                //获取当前时间
                                Date date = new Date(System.currentTimeMillis());
                                text3.setText(simpleDateFormat.format(date)+" 1743205000210");
                            }
                            //发送消息
                            mHandler.sendEmptyMessage(0x111);
                        }
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if (status4>0){
                            status4 = 0;
                            text4.setText("");
                        }
                        while(status4<100){
                            doWork();
                            status4 += ran.nextInt(3)+1;
                            if (status4>=100){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                                //获取当前时间
                                Date date = new Date(System.currentTimeMillis());
                                text4.setText(simpleDateFormat.format(date)+" 李金康");
                            }
                            //发送消息
                            mHandler.sendEmptyMessage(0x111);
                        }
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if (status5>0){
                            status5 = 0;
                            text5.setText("");
                        }
                        while(status5<100){
                            doWork();
                            status5 += ran.nextInt(5)+1;
                            if (status5>=100){
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                                //获取当前时间
                                Date date = new Date(System.currentTimeMillis());
                                text5.setText(simpleDateFormat.format(date)+" 1743205000210");
                            }
                            //发送消息
                            mHandler.sendEmptyMessage(0x111);
                        }
                    }
                }.start();

            }
        });
    }

    //做任何事都可，只是演示，按需实现，如果只是展现，不需要该方法也可
    private void doWork(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
