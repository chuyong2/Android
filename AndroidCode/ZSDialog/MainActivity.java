package pdx.com.zsalertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建AlertDialog的构造器
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //用构造器构建AlertDialog的每个组成部分
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要退出吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //添加“确定”按钮后的代码
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //添加“取消”按钮后的代码
                    }
                });
                //组合AlertDialog的各部分并显示
                builder.create().show();
            }
        });
        //创建一个单选AlertDialog
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建AlertDialog的构造器
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //用构造器构建AlertDialog的每个组成部分
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle("请选择城市");
                final String[] cities = {"上海","苏州","杭州","香港"};
                builder.setSingleChoiceItems(cities, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //添加“确定”按钮后的代码
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //添加“取消”按钮后的代码
                    }
                });
                //组合AlertDialog的各部分并显示
                builder.create().show();
            }
        });
    }
}
