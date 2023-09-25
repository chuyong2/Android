package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.administrator.adapter.MemoAdapter;
import com.example.administrator.db.MyDbHelper;
import com.example.administrator.domain.MemoBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private RecyclerView recyclerView;
    private MyDbHelper myDbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnOnclickNext();

        recyDisplay();
    }

    /**
     * 从数据库中获取数据，显示到RecyclerView控件中
     */
    private void recyDisplay() {
        //准备数据 -- 标题，内容，图片，时间
        List<MemoBean> array = new ArrayList<>();
        //从数据库取数据
        Cursor cursor = database.rawQuery("select * from tb_memory",null);
        while (cursor.moveToNext()){
            String myTitle = cursor.getString(cursor.getColumnIndex("title"));
            String myContent = cursor.getString(cursor.getColumnIndex("content"));
            String myImgPath = cursor.getString(cursor.getColumnIndex("imgPath"));
            String myTime = cursor.getString(cursor.getColumnIndex("myTime"));
            MemoBean memoBean = new MemoBean(myTitle,myContent,myImgPath,myTime);
            array.add(memoBean);
        }
        cursor.close();
        //子布局recy_item
        //数据------桥（适配器MemoAdapter）-------子布局
        MemoAdapter adapter = new MemoAdapter(MainActivity.this,array);
        //确定显示的方式
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //显示数据
        recyclerView.setAdapter(adapter);
    }

    /**
     * 对按钮添加单击事件
     */
    private void btnOnclickNext() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单击后跳转到下一页
                Intent intent = new Intent(MainActivity.this,AddInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 绑定控件
     */
    private void initView() {
        btnAdd = findViewById(R.id.button_add);
        recyclerView = findViewById(R.id.recy_view);
        myDbHelper = new MyDbHelper(MainActivity.this);
        database = myDbHelper.getWritableDatabase();
    }
}
