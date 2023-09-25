package pdx.com.zsfilesave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editInput;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        onButtonClick();
        //下次打开页面时，读取文件内容，显示出来
        readFile();
        //完善工程，自动显示当前时间
        displayTime();
    }

    private void displayTime() {
        Time time = new Time();
        time.setToNow();
        editInput.append("\n\n" + time.year + "年" + (time.month + 1) + "月" + time.monthDay
        + "日" + "\n");
        editInput.setSelection(editInput.getText().length()); //将光标移动到文字末尾
    }

    private void readFile() {
        try {
            FileInputStream fin = openFileInput("zs.txt");
            byte[] arr = new byte[fin.available()];  //缓冲区，存放读取的很多字节数据
            fin.read();
            fin.close();
            editInput.setText(new String(arr)); //数据显示出来，byte类型的数据转为字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onButtonClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //把输入的内容保存到文件里面
                try {
                    //context类中提供了一个方法openFileOutput可以实现向文件写入数据
                    FileOutputStream fout = openFileOutput("zs.txt",0);
                    fout.write(editInput.getText().toString().getBytes());//数据写入文件中
                    fout.close();
                    Toast.makeText(MainActivity.this,
                            "提交成功",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        editInput = findViewById(R.id.editText_input1);
        btnSave = findViewById(R.id.button_save1);
    }
}
