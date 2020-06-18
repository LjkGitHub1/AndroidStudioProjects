package com.example.keepaccounts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddKeppAccountActivity extends AppCompatActivity {

    private static final String[] m = {"支出", "收入"};
    private static final String[] t = {"餐饮", "文体", "娱乐", "日用"};
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private EditText et_money;
    private EditText et_location;
    private Button bt_add;
    private int positionChecked = 1;

    private long id = -999;
    private int type;
    private double moneyChange;
    private String location;
    private ArrayAdapter<String> tAdapter;
    private Spinner spinner_cost_type;
    private int positionCost;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_keep_account);
        mDbHelper = new DBHelper(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner_cost_type = (Spinner) findViewById(R.id.spinner_cost_type);
        et_money = (EditText) findViewById(R.id.et_money);
        et_location = (EditText) findViewById(R.id.et_location);
        bt_add = (Button) findViewById(R.id.bt_add);


        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = et_location.getText().toString().trim();
                String money = et_money.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    Toast.makeText(AddKeppAccountActivity.this, "金额不能为空", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(AddKeppAccountActivity.this, "地点不能为空", Toast.LENGTH_LONG).show();
                }
                if (id == -999) {

                    mDbHelper.insertData(positionChecked, Double.parseDouble(money), t[positionCost], location,UserManager.instance.getUser().id);
                    Toast.makeText(AddKeppAccountActivity.this, "添加成功", Toast.LENGTH_LONG).show();

                } else {
                    mDbHelper.modifyData(id, positionChecked, Double.parseDouble(money), location, t[positionCost]);
                }
                finish();
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        tAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, t);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner_cost_type.setAdapter(tAdapter);

        id = getIntent().getLongExtra("id", -999);
        if (id == -999) {
            bt_add.setText("添加");
        } else {
            type = getIntent().getIntExtra("type", -999);
            moneyChange = getIntent().getDoubleExtra("money", 0);
            location = getIntent().getStringExtra("location");
            String costType = getIntent().getStringExtra("costType");
            spinner.setSelection(type - 1, true);
            spinner_cost_type.setSelection(getIndex(costType), true);
            et_money.setText(moneyChange + "");
            et_location.setText(location);
            bt_add.setText("修改");
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionChecked = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_cost_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionCost = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private int getIndex(String costType) {
        for (int i = 0; i < t.length; i++) {
            if (t[i].equals(costType))
                return i;
        }
        return 0;
    }
}
