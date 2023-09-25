package com.pdx;

import android.graphics.Color;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //定义对象
    private TextView textView_11;
    private TextView textView_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        textView_11 = findViewById(R.id.textView_1);
        textView_12 = findViewById(R.id.textView_2);
        //控件方法的使用
        textView_11.setTextSize(40);
        textView_11.setTextColor(Color.rgb(255,0,0));
        String s = textView_12.getText().toString();   //获取控件内容
        textView_11.setText(s + "中国您好");
    }
}