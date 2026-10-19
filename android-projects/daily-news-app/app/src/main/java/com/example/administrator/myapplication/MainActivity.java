package com.example.administrator.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
     List<Map<String,Object>> dataList;
     ListView listView;
     EditText name,content;
     SimpleAdapter mSA;
     DayData db;
    static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DayData(getApplicationContext());
        name=(EditText)findViewById(R.id.name);
        content=(EditText)findViewById(R.id.content);
        listView=(ListView)findViewById(R.id.listview1);
        SystemClock.sleep(1000);
        defaultData();
        dataList=getData();
        mSA=new SimpleAdapter(this,dataList,R.layout.item,
                new String[]{"资讯标题","资讯内容","图片"},new int[]{R.id.item_name,R.id.item_content,R.id.imageview});
        listView.setAdapter(mSA);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> data=new ArrayList<>();
        Cursor cursor=db.showData();
        int pics[]={R.drawable.no1,R.drawable.no2,R.drawable.no3,R.drawable.no4,R.drawable.no5,R.drawable.no6};

        while (cursor.moveToNext()){
            Map<String,Object> map=new HashMap<>();
            map.put("资讯标题","资讯标题:"+cursor.getString(0));
            map.put("资讯内容","资讯内容:"+cursor.getString(1));
            map.put("图片",pics[i]);
            data.add(map);
            i++;
            if(i==6)i=0;
        }
        return data;
    }

    public void add(View view){
        String name1=name.getText().toString();
        String content1=content.getText().toString();
        db.add(name1,content1);
        dataList=getData();
        mSA=new SimpleAdapter(this,dataList,R.layout.item,
                new String[]{"资讯标题","资讯内容","图片"},new int[]{R.id.item_name,R.id.item_content,R.id.imageview});
        listView.setAdapter(mSA);
    }
    private void defaultData(){
        String content[]={"特斯拉烦恼：产能仍不及预期 今年中国市场竞争更激烈","大唐电信上市20年募57亿亏大半 变卖资产作价疑点多"
           ,"江河集团打折出售优质资产遭受质疑 现金流压力凸显","苹果重挫近10% 市值损失了一个Facebook 拖累巴菲特",
                "广发否认公募分仓佣金率降低 证券业热议机构佣金率","腾信股份连续亏两年 3次卖子回笼1.45亿被疑调节利润"};
        for(int i=0;i<content.length;i++)db.add(i+"",content[i]);
    }
}
