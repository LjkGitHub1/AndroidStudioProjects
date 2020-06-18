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
 * @name: UserInfoActivity
 * @date: 2020-05-18 13:25
 * @comment: 用户信息修改页面
 */
public class UserInfoActivity extends AppCompatActivity {
    private ImageView ivBack;
    private Button btn_update;
    private EditText ed_useName;
    private EditText ed_password;
    private User user;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user);
        ivBack = findViewById(R.id.iv_back);
        btn_update = findViewById(R.id.btn_update);
        ed_useName = findViewById(R.id.ed_useName);
        ed_password = findViewById(R.id.ed_password);
        user = UserManager.instance.getUser();
        ed_useName.setText(user.name);
        ed_password.setText(user.pwd);
        dbHelper = new DBHelper(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_useName.getText().toString().trim();
                if (name == null || TextUtils.isEmpty(name)) {
                    Toast.makeText(UserInfoActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = ed_password.getText().toString().trim();
                if (pwd == null || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(UserInfoActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isRit = dbHelper.updateUser(pwd, name, user.id);
                if (isRit) {
                    finish();
                } else {
                    Toast.makeText(UserInfoActivity.this, "用户信息修改失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
