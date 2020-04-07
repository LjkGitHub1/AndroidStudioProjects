package com.ljk.subtract;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button Btn1;
    private Button Btn2;
    private Button Btn3;

    int g = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");


        //之前是没在外部声明Btn，而是在这里声明，出现了几个问题
        //1.app运行不了；2.在下面的点击监听中调用时需要把Btn设置为final
        Btn1 = (Button) findViewById(R.id.btn1);
        Btn2 = (Button) findViewById(R.id.btn2);
        Btn3 = (Button) findViewById(R.id.btn3);

        Subtract f1 = new Subtract(1, 0);
        Subtract f2 = new Subtract(1.1, 1.001);
        Subtract f3 = new Subtract(1.1, 1.00001, 0);

        Btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (g==1) {
                    Btn1.setText("1-0=1");
                    g = g-1;
                }
                else {
                    Btn1.setText("计算1-0");
                    g = g+1;
                }
            }

        });
        Btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (g==1) {
                    Btn2.setText("1.1-1.001=0.0990000000000002");
                    g = g-1;
                }
                else {
                    Btn2.setText("计算1.1-1.001");
                    g = g+1;
                }
            }

        });
        Btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (g==1) {
                    Btn3.setText("1.1-1.00001=0.09999000000000002");
                    g = g-1;
                }
                else {
                    Btn3.setText("计算1.1-1.00001");
                    g = g+1;
                }
            }

        });
    }

    class Subtract {
        /**
         * 减法类 自定义方法备注
         *
         * @param a,b 运算数
         */


        Subtract(int a, int b) {
            System.out.println("姓名=" + (a - b));
            Log.d("ljkk", a+"-"+b+"=" + (a - b));
        }

        Subtract(double a, double b) {
            System.out.println("姓名=" + (a - b));
            Log.d("ljkk", a+"-"+b+"=" + (a - b));
        }

        //这地方我
        // Subtract(double c, double d, int e) {
            System.out.println("姓名=" + (c - d));
            Log.d("ljkk", c+"-"+d+"=" + (c - d));
        }
    }
}