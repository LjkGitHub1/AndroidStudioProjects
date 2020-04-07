package com.ljk.shiwuzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取屏幕尺寸
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //自定义layout组件
        RelativeLayout layout = new RelativeLayout(this);

        //创建16个按钮，四行四列
        Button Btn[] = new Button[16];
        int j = 1;
        for (int i=0; i<=15; i++){
            Btn[i] = new Button(this);
            Btn[i].setId(2000+i);
            Btn[i].setText(i+1);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((width-50)/4,40);
            //四个一行
            if(i%4 == 0)
                j++;
            btParams.leftMargin = 10 + ((width-50)/4+10)*(i%4);//横坐标定位
            btParams.topMargin = 20+55*j;//纵坐标定位
            layout.addView(Btn[i],btParams);//将按钮放入layout组件中

        }
        this.setContentView(layout);
        for (int k = 0; k <= Btn.length-1; k++) {
            //这里不需要findId，因为创建的时候已经确定哪个按钮对应哪个Id
            Btn[k].setTag(k);    //为按钮设置一个标记，来确认是按下了哪一个按钮

            Btn[k].setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = (Integer) v.getTag();

                    Intent intent = new Intent();
//                    intent.setClass(Work_01.this, Work_02.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("count", i);
                    intent.putExtras(bundle);
                    startActivity(intent);
//                    Work_01.this.finish();
                }
            });
        }
    }
}
