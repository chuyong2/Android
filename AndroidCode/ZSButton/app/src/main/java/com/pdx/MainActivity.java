package com.pdx;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText editName,editPwd;
    private Button btnLogin,btnCancel;
    private TextView txtDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        editName = findViewById(R.id.editText_inputname);
        editPwd = findViewById(R.id.editText_inputpwd);
        btnLogin = findViewById(R.id.button_login);
        btnCancel = findViewById(R.id.button_cancel);
        txtDisplay = findViewById(R.id.textView_display);

        //给登录添加单击事件
        //1.谁发生了事件（按钮）
        //2.发生了什么事（被单击）
        //3.谁来管理（Listener）

        //改写Lambda表达式
        btnLogin.setOnClickListener( view  -> {
                String strName = editName.getText().toString();
                String strPwd = editPwd.getText().toString();
                txtDisplay.setVisibility(View.VISIBLE);   //让文本控件变得可见
                txtDisplay.setText("哈哈，我偷偷地窃取了你的秘密信息\n用户名为：" + strName + "\n密码" +
                        "为:" + strPwd);
                //取消按钮单击事件
            }
        );

        btnCancel.setOnClickListener( view  -> {
                txtDisplay.setVisibility(View.GONE);
                editName.setText("");
                editPwd.setText("");
            }
        );
    }
}