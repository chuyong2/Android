package pdx.com.zssharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editName,editPwd;
    CheckBox checkName;
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_inputname);
        editPwd = findViewById(R.id.editText_inputpwd);
        checkName = findViewById(R.id.checkBox_name);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单击按钮将输入的用户名，密码，复选框的状态保存下来
                SharedPreferences.Editor editor = getSharedPreferences("myfile",0).edit();
                editor.putString("name",editName.getText().toString());
                editor.putString("pwd",editPwd.getText().toString());
                editor.putBoolean("st",checkName.isChecked());
                editor.commit();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //如果选中了记住密码复选框，下一次启动，能够获取用户名和密码并显示
        String myName = getSharedPreferences("myfile", 0).getString("name", "");
        String myPwd = getSharedPreferences("myfile", 0).getString("pwd", "");
        boolean mySt = getSharedPreferences("myfile", 0).getBoolean("st", false);
        if(mySt == true){
            editName.setText("");
            editPwd.setText("");
            checkName.setChecked(false);
        }
    }
}
