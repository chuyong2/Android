package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText editInputName,editInputPwd;
    private CheckBox checkBoxRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        btnLoginClick();

        displayInfo();
    }

    /**
     * 下次期待，直接显示用户名和密码
     */
    private void displayInfo() {
        String strName = getSharedPreferences("myinfo", 0).getString("name", "");
        String strPwd = getSharedPreferences("myinfo", 0).getString("pwd", "");
        Boolean status = getSharedPreferences("myinfo", 0).getBoolean("st", false);
        if(status == true){
            editInputName.setText(strName);
            editInputPwd.setText(strPwd);
            checkBoxRemember.setChecked(status);
        }else{
            editInputName.setText("");
            editInputPwd.setText("");
            checkBoxRemember.setChecked(false);
        }
    }

    /**
     * 单击登录按钮，将用户名和密码保存起来
     */
    private void btnLoginClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存用户名和密码
                SharedPreferences.Editor editor = getSharedPreferences("myinfo",0).edit();
                editor.putString("name",editInputName.getText().toString());
                editor.putString("pwd",editInputPwd.getText().toString());
                editor.putBoolean("st",checkBoxRemember.isChecked());
                editor.commit();
                //跳转到第二页
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 绑定控件
     */
    private void initView() {
        editInputName = findViewById(R.id.editText_inputname);
        editInputPwd = findViewById(R.id.editText_inputpwd);
        checkBoxRemember = findViewById(R.id.checkbox_reme);
        btnLogin = findViewById(R.id.button_login);
    }
}
