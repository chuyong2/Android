package pdx.com.myapplication;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int i = 0; //记录打到地鼠的个数
    private ImageView mouse;
    private Handler handler;
    public int[][] position = new int[][]{
            {360,250},{750,250},{1200,250},
            {360,450},{750,450},{1200,450},
            {360,650},{750,650},{1200,650}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置为横屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mouse = findViewById(R.id.imageView_1);

        //地鼠随机出现
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //需要处理的消息
                int index;
                if(msg.what == 0x101){
                    index = msg.arg1; //获取位置索引值
                    mouse.setX(position[index][0]);
                    mouse.setY(position[index][1]);
                    mouse.setVisibility(View.VISIBLE); //设置地鼠显示
                }
                super.handleMessage(msg);
            }
        };

        //线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0; //记录地鼠位置的索引值
                while (!Thread.currentThread().isInterrupted()){
                    index = new Random().nextInt(position.length);
                    Message message = handler.obtainMessage();  //创建消息对象
                    message.what = 0x101;  //设置消息坐标
                    message.arg1 = index;  //保存地鼠标位置的索引值
                    handler.sendMessage(message);  //发送消息通知Handler处理

                    try {
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        mouse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setVisibility(View.INVISIBLE);  //设置地鼠不显示
                i++;
                Toast.makeText(MainActivity.this, "打到[ " + i + " ]只地鼠",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
