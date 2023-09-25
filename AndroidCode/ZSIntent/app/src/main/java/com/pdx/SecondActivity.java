package com.pdx;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    private TextView txtName,txtAge,txtSno,txtFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtName = findViewById(R.id.textView_name);
        txtAge = findViewById(R.id.textView_age);
        txtSno = findViewById(R.id.textView_sno);
        txtFriend = findViewById(R.id.textView_friend);

        //接收值
        String myName = getIntent().getStringExtra("name");
        int myAge = getIntent().getIntExtra("age",0);
        String mySno = getIntent().getStringExtra("sno");
        Boolean myFriend = getIntent().getBooleanExtra("friend",false);

        //显示
        txtName.setText("姓名" + myName);
        txtAge.setText("年龄" + myAge);
        txtSno.setText("学号" + mySno);
        if(myFriend){
            txtFriend.setText("是否有对象：有");
        }else{
            txtFriend.setText("是否有对象：无");
        }

    }
}