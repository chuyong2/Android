package com.pdx;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button btnNext;
    private EditText editName,editAge,editSno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = findViewById(R.id.button_next);
        editName = findViewById(R.id.editText_name);
        editAge = findViewById(R.id.editText_age);
        editSno = findViewById(R.id.editText_sno);

        //单击按钮，跳转到下一页
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到下一页，指定了跳转的源位置和目的位置
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                //传值
                intent.putExtra("name",editName.getText().toString());
                intent.putExtra("age",Integer.valueOf(editAge.getText().toString()));
                intent.putExtra("sno",editSno.getText().toString());
                intent.putExtra("friend",true);
                //启动意图，完成跳转
                startActivity(intent);
            }
        });

    }
}