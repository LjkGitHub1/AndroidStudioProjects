package com.ljk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private ArrayAdapter<String> adapter;
    private List<String> all;
    private Button btn,city_btn;
    private EditText edit;
    String delProvince;

    ArrayAdapter<CharSequence> cityAdapter = null;
    public void city(String province) {
        switch (province){
            case "云南": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.yn, android.R.layout.simple_spinner_dropdown_item); break;
            case "北京": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.bj, android.R.layout.simple_spinner_dropdown_item); break;
            case "广西": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.gs, android.R.layout.simple_spinner_dropdown_item); break;
            case "广东": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.gd, android.R.layout.simple_spinner_dropdown_item); break;
            case "贵州": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.gz, android.R.layout.simple_spinner_dropdown_item); break;
            case "四川": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.sc, android.R.layout.simple_spinner_dropdown_item); break;
            case "河北": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.hb, android.R.layout.simple_spinner_dropdown_item); break;
            case "江苏": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.js, android.R.layout.simple_spinner_dropdown_item); break;
            case "山东": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.sd, android.R.layout.simple_spinner_dropdown_item); break;
            case "山西": cityAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.sx, android.R.layout.simple_spinner_dropdown_item); break;
            default: cityAdapter = null; break;
        }
    }

    ArrayList<String> newCity = new ArrayList<String>();
    public void addCity(String city) {
        newCity.add(city);
        final ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, newCity);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(cityAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("1743205000210李金康");

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btn = (Button) findViewById(R.id.btn);
        city_btn = (Button) findViewById(R.id.city_btn);
        edit = (EditText) findViewById(R.id.edit);

        String[] arr1 = {"云南", "北京", "广西", "广东", "贵州", "四川", "河北", "江苏", "山东", "山西"};

        //将字符数组中的对象导入到list中，才能进行动态控制
        all = new ArrayList<String>();
        for (int i = 0; i < arr1.length; i++) {
            all.add(arr1[i]);
        }

//        String[] arr1 = getResources().getStringArray(R.array.province);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, all);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner s = (Spinner) parent;//得到选中的内容
                String province = (String) s.getItemAtPosition(position);
                city(province);
                spinner2.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //添加城市
        city_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edit.getText().toString();
                addCity(city);
            }
        });

        //添加省份
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addProvince = edit.getText().toString();
                //判断添加的省份是否已经存在
                boolean flag = false;
                if (addProvince.equals("")) {
                    Toast.makeText(MainActivity.this, "请输入正确的内容！", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < all.size(); i++) {
                        if (addProvince.equals(all.get(i))) {
                            Toast.makeText(MainActivity.this, "该省份已存在！", Toast.LENGTH_LONG).show();
                            flag = true;
                        }
                    }
                    if (!flag) adapter.add(addProvince);
                }
            }
        });

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                DeleteDialog();
                delProvince = edit.getText().toString();
                int m = 1;
                for (int i = 0; i < all.size(); i++) {
                    if (delProvince.equals(all.get(i))) {
                        m = 0;
                    }
                }
                if (m == 1) {
                    Toast.makeText(MainActivity.this, "该省份不存在列表中！", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("删除省份");
                    dialog.setMessage("\n是否删除" + delProvince);
                    dialog.setCancelable(true);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            all.remove(delProvince); //省份列表删除
                            adapter.remove(delProvince);
                            city(all.get(0));  //城市显示为列表中的第一个 主要是用于删除第一个省份的显示
                            spinner2.setAdapter(cityAdapter);
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
                return true;

            }
        });
    }
}