package com.pdx;

import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private TextView YDate;  //原来的时间
    private TextView GDate; //更改后的时间
    private ImageView imageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker_11);
        YDate = findViewById(R.id.textView_11);
        GDate = findViewById(R.id.textView_12);
        imageDisplay = findViewById(R.id.img_1);

        //获取时间 获取日期控件默认的日期
        int YYear = datePicker.getYear();
        int YMonth = datePicker.getMonth() + 1;
        int YDay = datePicker.getDayOfMonth();

        //把获取的时间显示-文本控件
        YDate.setText(YYear + "年" + YMonth + "月" + YDay + "日");

        //获取当前系统时间
        Time time = new Time();
        time.setToNow();
        //能够获取修改后的日期---给日期添加监听器
        datePicker.init(time.year, time.month, time.monthDay, new DatePicker.OnDateChangedListener() {
            /**
             * 更改后的年月日
             * @param datePicker
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                GDate.setText(i + "年" + (i1 + 1) + "月" + i2 + "日");
                imageDisplay.setVisibility(View.GONE);   //让图片控件不可见
                if((i1 + 1) == 3 && i2 == 12){
                    GDate.setText(i + "年" + (i1 + 1) + "月" + i2 + "日" + ",植树节");
                    imageDisplay.setVisibility(View.VISIBLE);
                    imageDisplay.setImageResource(R.drawable.bbb);
                } else if ((i1 + 1 == 3 && i2 ==15)) {
                    GDate.setText(i + "年" + (i1 + 1) + "月" + i2 + "日" + ",国际消费者权益日");
                    imageDisplay.setVisibility(View.VISIBLE);
                    imageDisplay.setImageResource(R.drawable.aaa);
                }
            }
        });

    }
}