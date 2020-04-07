package com.ljk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LinearLayout mainLinearLayout = (LinearLayout) this.findViewById(R.id.myText);
//        TextView textView = new TextView(this);
//        textView.setText("ni");
//        textView.setBackgroundColor(Color.rgb(255,100,0));

//        textView.setGravity(Gravity.RIGHT);
//        textView.setHeight(300);
//        textView.setWidth(300);
//        LinearLayout.LayoutParams params_1= (LinearLayout.LayoutParams) textView.getLayoutParams();
//        params_1.height = 300;
//        textView.setLayoutParams(params_1);
//        textView.append("\nihso");
//        textView.setTextAppearance(this,R.style.text_style);
//        mainLinearLayout.addView(textView);

        LinearLayout p_layout = (LinearLayout) findViewById(R.id.myText);
        LinearLayout layout = new LinearLayout(this); //自定义layout组件
//        TextView textView = new TextView(this);
//        LinearLayout layout[] = new LinearLayout[4];
        layout.setOrientation(LinearLayout.VERTICAL); //设置layout布局为垂直
        for(int i=1;i<=4;i++){
            
            for(int j=1;j<=i;j++){
                add(layout,i,j);
            }
        }

        p_layout.addView(layout);


////        向layout中添加textview
//        TextView textView = new TextView(this);
//        textView.setText("1x2=2");
//        textView.setHeight(100);
//        textView.setGravity(Gravity.LEFT);
//        textView.setBackgroundColor(Color.BLUE);
//        layout.addView(textView);
//        textView.setTextAppearance(R.style.text_style);

    }

//    LinearLayout p_layout = (LinearLayout) findViewById(R.id.myText);
//    LinearLayout layout;

    private void add(LinearLayout layout,int i, int j) {
        TextView textView = new TextView(this);
        String t = String.valueOf(j+"x"+i+"="+(i*j));
        textView.setText(t);
        textView.setHeight(150);
        textView.setGravity(Gravity.LEFT);
        textView.setMinWidth(300);
        textView.setBackgroundColor(Color.rgb(50*j,10*j,10*i*j));
        layout.addView(textView);
        textView.setTextAppearance(R.style.text_style);
    }

}


