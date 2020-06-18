package com.example.keepaccounts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ImageView iv_menu;
    private ArrayList<KeepAccountBean> selectis;
    private ListView listView;
    private EditText et_search;
    private Button bt_search;
    private MyAdapter myAdapter;
    private NavigationView nav_view;
    private User user;
    private DBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        et_search = (EditText) findViewById(R.id.et_search);
        bt_search = (Button) findViewById(R.id.bt_search);
        ImageView iv_add = (ImageView) findViewById(R.id.iv_add);
        drawer = findViewById(R.id.drawer);
        iv_menu = findViewById(R.id.iv_menu);
        nav_view = findViewById(R.id.nav_view);
        user = UserManager.instance.getUser();
        mDbHelper=new DBHelper(this);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
        TextView textView = nav_view.getHeaderView(0).findViewById(R.id.tv_app_name);
        textView.setText(user.name);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_exit) {
                    cloaseDrawer();
                    AlertDialog.Builder bulder = new AlertDialog.Builder(MainActivity.this);
                    bulder.setTitle("提示");
                    bulder.setMessage("是否退出登录?");
                    bulder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    bulder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SPUtil.put(MainActivity.this, "name", "");
                            SPUtil.put(MainActivity.this, "pwd", "");
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            dialog.dismiss();
                        }
                    });
                    bulder.create().show();

                } else if (menuItem.getItemId() == R.id.nav_update) {
                    cloaseDrawer();
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddKeppAccountActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KeepAccountBean keepAccountBean = selectis.get(position);
                Intent intent = new Intent(MainActivity.this, AddKeppAccountActivity.class);
                intent.putExtra("id", keepAccountBean.getId());
                intent.putExtra("type", keepAccountBean.getType());
                intent.putExtra("money", keepAccountBean.getMoney());
                intent.putExtra("location", keepAccountBean.getLocation());
                intent.putExtra("costType", keepAccountBean.getCostType());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final KeepAccountBean keepAccountBean = selectis.get(position);
                AlertDialog.Builder bulder = new AlertDialog.Builder(MainActivity.this);
                bulder.setTitle("提示");
                bulder.setMessage("是否删除?");
                bulder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                bulder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDbHelper.delData(keepAccountBean.getId());
                        getDbData();
                        dialog.dismiss();
                    }
                });
                bulder.create().show();

                return true;
            }
        });
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = et_search.getText().toString().trim();
                if (TextUtils.isEmpty(time)) {
                    getDbData();
                } else {
                    Date date = TimeDateUtils.string2Date(time, TimeDateUtils.FORMAT_TYPE_1);
                    if (null != selectis && selectis.size() > 0) {
                        List<KeepAccountBean> x = new ArrayList<>();
                        for (KeepAccountBean selecti : selectis) {
                            String createTime = selecti.getCreateTime();
                            String s = createTime.split(" ")[0].replaceAll("-", "");
                            if (s.equals(time))
                                x.add(selecti);
                        }
                        myAdapter = new MyAdapter(MainActivity.this, x);
                        listView.setAdapter(myAdapter);
                    }
                }
            }
        });
    }
    /**
     * 打开侧滑
     */
    private void openDrawer() {
        drawer.openDrawer(nav_view);
    }

    /**
     * 关闭侧滑
     */
    private void cloaseDrawer() {
        drawer.closeDrawers();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDbData();
    }

    private void getDbData() {
        selectis = mDbHelper.selectis(UserManager.instance.getUser().id);
        Collections.reverse(selectis);
        myAdapter = new MyAdapter(this, selectis);
        listView.setAdapter(myAdapter);
    }
}
