package com.px;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnJia;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btnJian;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btnCheng;
    private Button btn0;
    private Button btnQing;
    private Button btnDengYu;
    private Button btnChu;
    private double result; //运算结果
    private double num1, num2;  //接收参数
    private String op = "%";    //操作符+ - * /
    private boolean isClickDeng = false;  //判断是否单击了=



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_result);
        btn7 = findViewById(R.id.btn_7);
        btn8  = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnJia = findViewById(R.id.btn_jia);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btnJian = findViewById(R.id.btn_jian);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btnCheng = findViewById(R.id.btn_cheng);
        btn0 = findViewById(R.id.btn_0);
        btnQing = findViewById(R.id.btn_qing);
        btnDengYu = findViewById(R.id.btn_dengyu);
        btnChu = findViewById(R.id.btn_chu);

        //按钮单击事件
        //数字0-9按钮代码
        btn0.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "0");
        });

        btn1.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "1");
        });

        btn2.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "2");
        });

        btn3.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "3");
        });

        btn4.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "4");
        });

        btn5.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "5");
        });

        btn6.setOnClickListener( view -> {
            //按钮单击逻辑
            if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                textView.setText("");   //重新计算，文本框清空
                isClickDeng = false;  //更改 = 状态
            }
            textView.setText(textView.getText().toString() + "6");
        });

        btn7.setOnClickListener( view -> {
                //按钮单击逻辑
                if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                    textView.setText("");   //重新计算，文本框清空
                    isClickDeng = false;  //更改 = 状态
                }
                textView.setText(textView.getText().toString() + "7");
                //1.单击7直接显示在文本控件
                //2.先单击别的，再单击7
        });

        btn8.setOnClickListener( view -> {
                //按钮单击逻辑
                if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                    textView.setText("");   //重新计算，文本框清空
                    isClickDeng = false;  //更改 = 状态
                }
                textView.setText(textView.getText().toString() + "8");
        });

        btn9.setOnClickListener( view -> {
                //按钮单击逻辑
                if (isClickDeng){   //说明刚单击了=，上一个运算刚结束
                    textView.setText("");   //重新计算，文本框清空
                    isClickDeng = false;  //更改 = 状态
                }
                textView.setText(textView.getText().toString() + "9");
        });

        //运算符+，-，*，/按钮单击事件
        btnJia.setOnClickListener(view -> {
                String s = textView.getText().toString();  //获取单击加号之前的字符串类型的数据
                if ("".equals(s)){
                    return;
                }
                num1 = Double.parseDouble(s);
                textView.setText("");
                op = "+";
                isClickDeng = false;
        });

        btnJian.setOnClickListener(view -> {
                String s = textView.getText().toString();
                if ("".equals(s)){
                    return;
                }
                num1 = Double.parseDouble(s);
                textView.setText("");
                op = "-";
                isClickDeng = false;
        });

        btnCheng.setOnClickListener(view -> {
                String s = textView.getText().toString();
                if ("".equals(s)){
                    return;
                }
                num1 = Double.parseDouble(s);
                textView.setText("");
                op = "*";
                isClickDeng = false;
        });

        btnChu.setOnClickListener(view -> {
                String s = textView.getText().toString();
                if ("".equals(s)){
                    return;
                }
                num1 = Double.parseDouble(s);
                textView.setText("");
                op = "/";
                isClickDeng = false;
        });

        //C代码
        btnQing.setOnClickListener( view -> textView.setText(""));

        //=按钮
        btnDengYu.setOnClickListener( view -> {
                String s2 = textView.getText().toString();  //获取文本框的数据
                if("".equals(s2)){
                    return;
                }
                num2 = Double.parseDouble(s2);
                textView.setText("");
                switch (op){
                    case "+" : result = num1 + num2;break;
                    case "-" : result = num1 - num2;break;
                    case "*" : result = num1 * num2;break;
                    case "/" : result = num1 / num2;break;
                    case "%" : result = num2;
                    break;
                    default:result = 0.0;
                    break;
                }

                textView.setText(result + ""); //将+-*/运算的结果转换为字符串显示到结果文本框中

                op = "%";
                isClickDeng = true;
        });



    }
}