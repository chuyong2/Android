package pdx.com.musicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView textView;
    private Timer timer;    //创建定时器
    private TimerTask timerTask;  //创建定时器任务
    private int count = 5;
    private Handler handler; //消息处理器，专门发送和接收消息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initDate();
        initStatus();
    }

    /**
     * 第一次登录App时显示欢迎界面倒计时，第二次登录App时直接进入
     */
    private void initStatus() {
        //读取保存的登录状态值
        Boolean status = getSharedPreferences("mystatus",MODE_PRIVATE)
                .getBoolean("firstlogin_status",false);
        //将第一次登录的状态True保存起来，下次登录后的判断状态，如果为True直接跳转到主界面，否则倒计时
        SharedPreferences.Editor editor = getSharedPreferences("mystatus",MODE_PRIVATE).edit();
        editor.putBoolean("firstlogin_status",true);
        editor.commit();
        if(status){
            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            timer.cancel();
            timerTask.cancel();
        }
    }

    /**
     * 数据初始化
     */
    private void initDate() {
        //创建定时器
        timer = new Timer();
        //明确定时器要执行的任务
        timerTask = new TimerTask() {
            @Override
            /**
             * run里面指指示器要完成的任务，都是耗时的操作，耗时的都放在子线程中执行
             */
            public void run() {
                //让子线程给主线程发送消息信号，主线程接收到消息信号后就可以更新主界面中的数字显示信息
                if (count != 0) {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            }
        };
        //开启定时器，参数(定时器任务，延迟，变化周期)
        timer.schedule(timerTask, 0, 1000);
        //主线程接受到消息信号对主界面数字显示进行更新
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //主线程根据接受的消息进行判断
                switch (msg.what) {
                    case 1:
                        count--;
                        textView.setText(count + "");  //让变化的数字显示到主界面
                        break;
                    case 0:
                        Intent intent = new Intent(WelcomeActivity.this,
                                MainActivity.class);  //倒计时结束，跳转到主界面
                        startActivity(intent);
                        finish();
                        timer.cancel();
                        timerTask.cancel();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    /**
     * 控件初始化
     */
    private void initView() {
        textView = findViewById(R.id.text1);
    }
}
