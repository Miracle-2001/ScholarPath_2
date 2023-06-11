package com.example.bjtuview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bjtuview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 登录界面
 目前没有账号，因为没有配置数据库
 点击登录目前是直接跳转了。
 点击注册还没有处理。
 */

public class LoginPage extends AppCompatActivity {

    EditText pwdEt,nameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ScholarPath);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        pwdEt=findViewById(R.id.editTextTextPassword2);
        nameEt=findViewById(R.id.editTextTextPersonName);

//        createUserDB();
    }

    public void Signup(View view){//注册
        String name= nameEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.equals("")) {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
//        if(existName(name)){//用户存在
//            Toast.makeText(this,"该用户名已经注册",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        insert(name,pwd);
//        newTable(name);
    }
    public void Login(View view) {//登录
        String name= nameEt.getText().toString();
        String pwd = pwdEt.getText().toString();

        if (name.equals("")) {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        }
        else if (pwd.equals("")) {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
//        else if(existUser(name,pwd)) {//用户存在
//            Intent intent=new Intent(this,MainActivity.class);
//            Bundle bundle=new Bundle();
//            bundle.putString("name",name);
//            bundle.putString("pwd",pwd);
//
//            List<Integer>arrayList=findSpoAndExp(name,pwd);
//            Log.e("zky",arrayList.toString());
//            bundle.putInt("spo",arrayList.get(0));
//            bundle.putInt("exp",arrayList.get(1));
//            intent.putExtras(bundle);
//
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
//        }
        Intent intent=new Intent(this,MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("name","ROOT");
        bundle.putString("pwd","123456");

//        List<Integer>arrayList=findSpoAndExp(name,pwd);
//        Log.e("zky",arrayList.toString());
//        bundle.putInt("spo",arrayList.get(0));
//        bundle.putInt("exp",arrayList.get(1));
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}


//这里注释掉的是数据库，之前数据库是放在本地的。所以这里注释掉了。
//    public void createUserDB(){
//        SQLiteOpenHelper helper=MySqliteOpenHelper.getmInstance(this);
//
//        SQLiteDatabase readableDatabase= helper.getReadableDatabase();
//    }
//    public boolean existName(String name){
//        SQLiteOpenHelper helper =MySqliteOpenHelper.getmInstance(this);
//        SQLiteDatabase db=helper.getReadableDatabase();
//        name="'"+name+"'";
//        if(db.isOpen()){
//            Cursor cursor=db.rawQuery("select * from user where name="+name,null);
//            int count=0;
//            while(cursor.moveToNext()){
//                ++count;
//            }
//            cursor.close();
//            db.close();
//            if(count>0) return true;
//            return false;
//        }
//        return false;
//    }
//    public boolean existUser(String name,String pwd){
//        name="'"+name+"'";
//        pwd="'"+pwd+"'";
//        SQLiteOpenHelper helper =MySqliteOpenHelper.getmInstance(this);
//        SQLiteDatabase db=helper.getReadableDatabase();
//        if(db.isOpen()){
//            Cursor cursor=db.rawQuery("select * from user where name="+name+" and pwd="+pwd,null);
//            int count=0;
//            while(cursor.moveToNext()){
//                ++count;
//            }
//            cursor.close();
//            db.close();
//            return count > 0;
//        }
//        return false;
//    }
//    @SuppressLint("Range")
//    public List<Integer> findSpoAndExp(String name, String pwd){
//        name="'"+name+"'";
//        pwd="'"+pwd+"'";
//        SQLiteOpenHelper helper =MySqliteOpenHelper.getmInstance(this);
//        SQLiteDatabase db=helper.getReadableDatabase();
//        if(db.isOpen()){
//            Cursor cursor=db.rawQuery("select * from user where name="+name+" and pwd="+pwd,null);
////            int count=0;
////            while(cursor.moveToNext()){
////                ++count;
////            }
//            assert (cursor!=null);
//            int spo = 233,exp = 233;
//            while(cursor.moveToNext()) {
//                spo = Integer.parseInt(cursor.getString(cursor.getColumnIndex("spo")));
//                exp = Integer.parseInt(cursor.getString(cursor.getColumnIndex("exp")));
//            }
//            cursor.close();
//            db.close();
//            return  Arrays.asList(spo,exp);
//        }
//        return null;
//    }
//    public void query(){
//        SQLiteOpenHelper helper =MySqliteOpenHelper.getmInstance(this);
//        SQLiteDatabase db=helper.getReadableDatabase();
//
//        if(db.isOpen()){
//            Cursor cursor=db.rawQuery("select * from user ",null);
//
//            while(cursor.moveToNext()) {
//                @SuppressLint("Range") int _id =cursor.getInt(cursor.getColumnIndex("_id"));
//                @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex("name"));
//            }
//            cursor.close();
//            db.close();
//        }
//    }
//    public void newTable(String name){
//        SQLiteOpenHelper helper =MySqliteOpenHelper.getmInstance(this);
//        SQLiteDatabase db=helper.getWritableDatabase();
//        String sql="create table "+name+"(_id integer primary key autoincrement, task text, lastCheck text);";
//        db.execSQL(sql);
//
//    }
//    public void insert(String name,String pwd){
//        SQLiteOpenHelper helper =MySqliteOpenHelper.getmInstance(this);
//        SQLiteDatabase db=helper.getWritableDatabase();
//        name="'"+name+"'";
//        pwd="'"+pwd+"'";
//        if(db.isOpen()) {
//            String sql = "insert into user(name,pwd,spo,exp) values("+name+","+pwd+",'100','0')";
//            db.execSQL(sql);
//            db.close();
//        }
//    }