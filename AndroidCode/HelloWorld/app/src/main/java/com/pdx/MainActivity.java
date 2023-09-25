package com.pdx;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //在程序创建时输出不同类型的日志信息
        Log.v("MainActivity","这是一条Verbose信息");
        Log.d("MainActivity","这是一条Debug信息");
        Log.i("MainActivity","这是一条Info信息");
        Log.w("MainActivity","这是一条Warning信息");
        Log.e("MainActivity","这是一条Error信息");

    }
}