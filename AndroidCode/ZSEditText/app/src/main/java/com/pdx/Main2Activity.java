package com.pdx;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    private Button btn_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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