package com.example.administrator.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //添加选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);   //获取当前菜单对象，加载菜单布局文件
        return true;  //显示菜单
    }

    //给菜单项添加选中时的响应事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(MainActivity.this, "你单击了添加菜单", Toast.LENGTH_LONG).show();
                break;
            case R.id.delete_item:
                Toast.makeText(MainActivity.this, "你单击了删除菜单", Toast.LENGTH_LONG).show();
                break;
            case R.id.modify_item:
                Toast.makeText(MainActivity.this, "你单击了修改菜单", Toast.LENGTH_LONG).show();
                break;
            case R.id.exit_item:
                Toast.makeText(MainActivity.this, "你单击了退出菜单", Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
        }
                return true;
    }
}
