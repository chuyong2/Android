package com.pdx;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    private TextView txtGetName,txtGetBirth,txtContextStar;
    private ImageView imgStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtGetName = findViewById(R.id.textView_getname);
        txtGetBirth = findViewById(R.id.textView_getbirth);
        txtContextStar = findViewById(R.id.textView_contentstar);
        imgStar = findViewById(R.id.imageView_imgstar);

        //接收第一页传递过来的值并显示到对应的文本控件
        String s1 = getIntent().getStringExtra("name");
        int myYear = getIntent().getIntExtra("year",0);
        int myMonth1 = getIntent().getIntExtra("yue",0);
        int myMonth = myMonth1 + 1;
        int myDay = getIntent().getIntExtra("ri",0);
        txtGetName.setText("你好:" + s1);
        txtGetBirth.setText("您的出生日期为:" + myYear + "年" + myMonth + "月" + myDay + "日");

        //从12星座图片中选择与日期对应的一张图片
        int[] imgArr = {R.drawable.baiyang,R.drawable.jinniu,
                R.drawable.shuangzi,R.drawable.jvxie,R.drawable.shizi,
                R.drawable.chunv,R.drawable.tiancheng,R.drawable.tianxie,
                R.drawable.sheshou,R.drawable.mojie,R.drawable.shuiping,R.drawable.shuangyu};

        int[] contextArr = {R.string.白羊座,R.string.金牛座,
                R.string.双子座,R.string.巨蟹座,R.string.狮子座,
                R.string.处女座,R.string.天秤座,R.string.天蝎座,
                R.string.射手座,R.string.魔羯座,R.string.水瓶座,R.string.双鱼座};


        int i = find(myMonth,myDay);
        imgStar.setImageResource(imgArr[i]);
        txtContextStar.setText(contextArr[i]);
    }


    private int find(int myMonth,int myDay){
        int i = 0;
        if (myMonth == 3 && myDay >= 21  || myMonth == 4 && myDay <= 19) {i = 0;}
        if (myMonth == 4 && myDay >= 20  || myMonth == 5 && myDay <= 20) {i = 1;}
        if (myMonth == 5 && myDay >= 21  || myMonth == 6 && myDay <= 21) {i = 2;}
        if (myMonth == 6 && myDay >= 22  || myMonth == 7 && myDay <= 22) {i = 3;}
        if (myMonth == 7 && myDay >= 23  || myMonth == 8 && myDay <= 22) {i = 4;}
        if (myMonth == 8 && myDay >= 23  || myMonth == 9 && myDay <= 22) {i = 5;}
        if (myMonth == 9 && myDay >= 23  || myMonth == 10 && myDay <= 23) {i = 6;}
        if (myMonth == 10 && myDay >= 24  || myMonth == 11 && myDay <= 22) {i = 7;}
        if (myMonth == 11 && myDay >= 23  || myMonth == 12 && myDay <= 21) {i = 8;}
        if (myMonth == 12 && myDay >= 22  || myMonth == 1 && myDay <= 19) {i = 9;}
        if (myMonth == 1 && myDay >= 20  || myMonth == 2 && myDay <= 18) {i = 10;}
        if (myMonth == 2 && myDay >= 19  || myMonth == 3 && myDay <= 20) {i = 11;}
        return i;
    }
}