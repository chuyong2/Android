package com.example.administrator.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText editInput1;
    private Button btnSave1,btnDelete;
    private String path;
    private File file;
    private String filename = "note.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        initView();

        //按钮单击事件，把输入的内容保存到文件里面
        onButtonClick();

        //下次打开页面，读取文件内容并显示
        readFile();

        //完善 自动显示当前时间
        displayTime();
    }

    private void initView() {
        editInput1 = findViewById(R.id.editText_input1);
        btnSave1 = findViewById(R.id.button_save1);
        btnDelete = findViewById(R.id.button_delete);
    }

    private void onButtonClick() {
        btnSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //把输入的内容保存到文件
                try{
                    //利用外部 getExternalStorageDirectory 获取根目录，直接在后面加上想创建的文件
                    path = Environment.getExternalStorageDirectory() + "/" + filename;
                    // 路径：/storage/emulated/0/note.txt
                    file = new File(path);  //创建文件
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = openFileOutput(filename,0);  //创建文件输入流
                    fileOutputStream.write(editInput1.getText().toString().getBytes());
                    fileOutputStream.close();
                    //弹出提示
                    Toast.makeText(MainActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFile(filename);
                Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readFile() {
        //检查SD卡的状态，Environment.MEDIA_MOUNTED表示SD卡在手机上正常使用状态
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //可用，读取数据
            try{
                FileInputStream fileInputStream = openFileInput(filename);
                byte[] arr = new byte[fileInputStream.available()];  //数组缓冲区
                fileInputStream.read(arr);
                fileInputStream.close();
                editInput1.setText(new String(arr));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(MainActivity.this,"SD卡不可用",Toast.LENGTH_SHORT).show();
        }
    }

    private void displayTime() {
        Time time = new Time();
        time.setToNow();
        editInput1.append("\n\n" + time.year + "年" +
                (time.month + 1) + "月" + time.monthDay + "日" + "\n");
        editInput1.setSelection(editInput1.getText().length());   //光标移动到文字末尾
    }
}
