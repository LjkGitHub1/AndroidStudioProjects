package com.example.keepaccounts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * @name: DBHelper
 * @author: jiangcy
 * @email: jcy0177@woozoom.net
 * @date: 2020-05-28 15:06
 * @comment:
 */
public class DBHelper  extends SQLiteOpenHelper {

    private String TAG = "DBHelper";

    //用户表
    /*表名*/
    private final String TABLE_NAME_USER = "_user";
    /*id字段*/
    private final String VALUE_ID = "_id";
    private final String VALUE_NAME = "subject";
    private final String VALUE_PWD = "body";
    /*创建表语句 语句对大小写不敏感 create table 表名(字段名 类型，字段名 类型，…)*/
    private final String CREATE_USER = "create table " + TABLE_NAME_USER + "(" +
            VALUE_ID + " integer primary key," +
            VALUE_NAME + " text ," +
            VALUE_PWD + " text" +
            ")";



    public DBHelper(Context context) {
        this(context, "keepaccounts.db", null, 1);

        Log.e(TAG, "-------> MySqliteHelper");
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        Log.e(TAG, "-------> MySqliteHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists t_account" +
                "(id integer primary key autoincrement," +
                "type int(1) " +
                ",createTime text(100)" +
                ",money double(10)" +
                ",user_id integer" +
                ",costType text(100)" +
                ",location text(100))";
        db.execSQL(sql);//创建表
        //创建表
        db.execSQL(CREATE_USER);

        Log.e(TAG, "-------> onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.e(TAG, "-------> onUpgrade" + "  oldVersion = " + oldVersion + "   newVersion = " + newVersion);

    }

    /**
     * 注册
     *
     * @param pwd
     * @param name
     * @return
     */
    public boolean register(String pwd, String name) {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_USER,
                null, VALUE_NAME + "=?" + " and " + VALUE_PWD + "=?",
                new String[]{name, pwd}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(VALUE_PWD, pwd);
        values.put(VALUE_NAME, name);
        //添加数据到数据库
        long index = getWritableDatabase().insert(TABLE_NAME_USER, null, values);
        getWritableDatabase().close();
        if (index != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @return
     */

    public User login(String name, String pwd) {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_USER,
                null, VALUE_NAME + "=?" + " and " + VALUE_PWD + "=?",
                new String[]{name, pwd}, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        User user = new User();
        user.id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
        user.name = cursor.getString(cursor.getColumnIndex(VALUE_NAME));
        user.pwd = cursor.getString(cursor.getColumnIndex(VALUE_PWD));
        cursor.close();
        getWritableDatabase().close();
        return user;
    }

    public boolean updateUser(String name, String pwd, int id) {
        ContentValues values = new ContentValues();
        values.put(VALUE_NAME, name);
        values.put(VALUE_PWD, pwd);

        //修改model的数据
        long index = getWritableDatabase().update(TABLE_NAME_USER, values, VALUE_ID + "=?", new String[]{"" + id});
        getWritableDatabase().close();
        if (index != -1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 查询数据
     * 返回List
     */
    public ArrayList<KeepAccountBean> selectis(int userId) {

        ArrayList<KeepAccountBean> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("t_account", null,  "user_id =?",
                new String[]{""+userId}, null, null, null);
        while (cursor.moveToNext()) {
            KeepAccountBean userBean = new KeepAccountBean();
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
            double money = cursor.getDouble(cursor.getColumnIndex("money"));
            String costType = cursor.getString(cursor.getColumnIndex("costType"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            userBean.setId(id);
            userBean.setType(type);
            userBean.setMoney(money);
            userBean.setCostType(costType);
            userBean.setCreateTime(createTime);
            userBean.setLocation(location);
            list.add(userBean);
//            Log.e("--Main--", "==============selectis======"+id+"================"+name+"================"+bsid);
        }
        if (cursor != null) {
            cursor.close();
        }
        getReadableDatabase().close();
        return list;
    }

    /**
     * 根据ID删除数据
     * id 删除id
     */
    public int delData(long id) {
        int inde = getReadableDatabase().delete("t_account", "id = ?", new String[]{String.valueOf(id)});
        Log.e("--Main--", "==============删除了======================" + inde);
        getReadableDatabase().close();
        return inde;
    }

    /**
     * 根据ID修改数据
     * id 修改条码的id
     * bsid 修改的ID
     * name 修改的数据库
     */
    public int modifyData(long id, int type, double money, String location,String costType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("money", money);
        contentValues.put("createTime", TimeDateUtils.date2String(new Date(), TimeDateUtils.FORMAT_TYPE_3));
        contentValues.put("location", location);
        contentValues.put("costType", costType);
        int index = getReadableDatabase().update("t_account", contentValues, "id = ?", new String[]{String.valueOf(id)});
        Log.e("--Main--", "==============修改了======================" + index);
        getReadableDatabase().close();
        return index;
    }

    /**
     * 添加数据
     * bsid 添加的数据ID
     * name 添加数据名称
     */
    public long insertData(int type, double money, String costType, String location,int userId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("money", money);
        contentValues.put("costType", costType);
        contentValues.put("createTime", TimeDateUtils.date2String(new Date(), TimeDateUtils.FORMAT_TYPE_3));
        contentValues.put("location", location);
        contentValues.put("user_id", userId);
        long dataSize = getReadableDatabase().insert("t_account", null, contentValues);
//        Log.e("--Main--", "==============insertData======================"+name+"================"+bsid);
        getReadableDatabase().close();
        return dataSize;
    }

    /**
     * 查询名字单个数据
     *
     * @param names
     * @return
     */
    public boolean selectisData(String names) {
        //查询数据库
        Cursor cursor = getReadableDatabase().query("t_account", null, "name = ?", new String[]{names}, null, null, null);
        while (cursor.moveToNext()) {
            getReadableDatabase().close();
            return true;
        }
        getReadableDatabase().close();
        return false;
    }
}

