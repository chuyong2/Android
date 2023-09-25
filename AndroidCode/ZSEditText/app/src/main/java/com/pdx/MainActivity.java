package com.pdx;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText edit_1,edit_4;
    private Button btn_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //把定义的对象edit_1与界面中的控件ID为editText_1的控件关联起来
        edit_1 = findViewById(R.id.editText_1);
        edit_4 = findViewById(R.id.editText_4);
        //EidtText控件的方法
        edit_1.setTextSize(35);
        edit_1.setTextColor(Color.rgb(0,130,255));
        edit_4.setText("梅花");
        String s = edit_4.getText().toString();
        edit_1.setText(s + "您好吗？");


        btn_one = findViewById(R.id.button_one);
        btn_one.setOnClickListener(new View.OnClickListener() {
            //匿名内部类
            @Override
            public void onClick(View view) {
                btn_one.setText("按钮1已被单击");
            }
        });


    }
}