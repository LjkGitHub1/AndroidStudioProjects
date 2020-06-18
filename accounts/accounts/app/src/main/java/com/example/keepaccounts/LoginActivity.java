package com.example.keepaccounts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @name: LoginActivity
 * @date: 2020-05-18 10:18
 * @comment: 登录页面
 */
public class LoginActivity extends AppCompatActivity {
    private EditText login_name_et;
    private EditText login_code_et;
    private TextView tv_register;
    private Button btn_login;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        mDbHelper=new DBHelper(this);
        String oldName = (String) SPUtil.get(this, "name", "");
        String oldPwd = (String) SPUtil.get(this, "pwd", "");
        if (!TextUtils.isEmpty(oldName) && !TextUtils.isEmpty(oldPwd)) {

            User user = mDbHelper.login(oldName, oldPwd);
            if (user != null) {
                UserManager.instance.setUser(user);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                }
        }
        login_name_et = findViewById(R.id.login_name_et);
        login_code_et = findViewById(R.id.login_code_et);
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = login_name_et.getText().toString().trim();
                String pwd = login_code_et.getText().toString().trim();
                if(name==null|| TextUtils.isEmpty(name)){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd==null|| TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = mDbHelper.login(name, pwd);
                if (user != null) {
                    UserManager.instance.setUser(user);
                    SPUtil.put(LoginActivity.this, "name", name);
                    SPUtil.put(LoginActivity.this, "pwd", pwd);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "登录失败，未查询到此用户", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
