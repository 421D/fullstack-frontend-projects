package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DayData extends SQLiteOpenHelper {
    public DayData(Context context){super(context,"day.db",null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String data="create table 数据( 资讯标题 text, 资讯内容 text)";
        db.execSQL(data);
    }
     public void add(String name,String content){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues=new ContentValues();
         contentValues.put("资讯标题",name);
         contentValues.put(" 资讯内容",content);
         db.insert("数据",null,contentValues);
     }
    public Cursor showData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from 数据", null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
