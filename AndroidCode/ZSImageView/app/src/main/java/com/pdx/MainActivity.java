package com.pdx;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ImageView imgOne;
    private TextView txtOne;
    private Button btnOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        imgOne = findViewById(R.id.imageView_1);
        txtOne = findViewById(R.id.textView_1);
        btnOne = findViewById(R.id.button_1);
        //按钮单击事件
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgOne.setImageResource(R.drawable.bbb);  //更改ImgView控件中的图片
                txtOne.setText("动物");
            }
        });


    }
}