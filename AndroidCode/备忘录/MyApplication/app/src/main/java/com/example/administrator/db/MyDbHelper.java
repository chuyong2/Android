package com.example.administrator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    //定义数据库名和版本号
    private static String DBNAME = "zsmemo.db";
    private static int VERSION = 1;
    //构造方法
    public MyDbHelper(Context context){
        super(context,DBNAME,null,VERSION);
    }
    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("" +
                "create table tb_memory" +
                "(_id Integer primary key,title String(200),content String(2000)," +
                "imgpath String (200),mtime String(50))");
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
