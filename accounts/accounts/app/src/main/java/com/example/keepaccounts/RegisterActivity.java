package com.example.keepaccounts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @name: RegisterActivity
 * @date: 2020-05-18 11:11
 * @comment: 注册页面
 */
public class RegisterActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnRegister;
    private EditText ed_useName;
    private EditText ed_password;
    private DBHelper mDbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        mDbHelper=new DBHelper(this);
        ivBack= findViewById(R.id.iv_back);
        btnRegister=findViewById(R.id.btn_register);
        ed_useName=findViewById(R.id.ed_useName);
        ed_password=findViewById(R.id.ed_password);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_useName.getText().toString().trim();
                if(name==null|| TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = ed_password.getText().toString().trim();
                if(pwd==null|| TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isRit= mDbHelper.register(pwd,name);
                if(isRit){
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, "注册失败,请重新注册", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
