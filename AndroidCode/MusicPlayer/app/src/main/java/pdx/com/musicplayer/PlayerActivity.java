package pdx.com.musicplayer;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends AppCompatActivity {
    private static final String TAG = "PlayerActivity";
    private TextView title;
    private TextView name;
    private Cursor cursor;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView currentTime;
    private TextView totalTime;
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initView();
        initData();
        initPlay();
        initSeek();
        moveSeek();
        initUpdate();
    }

    /**
     * 按返回键停止播放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    /**
     * 实时更新滑动条的当前时间
     */
    private void initUpdate() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 11;
                myHandler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,0,1000);
        myHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 11:
                        initSeek();  //重新执行进度条的初始化代码
                        break;
                        default:
                            break;
                }
            }
        };
    }

    /**
     * 拖动滑动条
     */
    private void moveSeek() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int position, boolean b) {
                if(b){
                    mediaPlayer.seekTo(position);
                    initSeek();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 初始化进度条
     */
    private void initSeek() {
        seekBar.setMax(mediaPlayer.getDuration());   //获取音频文件总时长
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        totalTime.setText(toTime(mediaPlayer.getDuration()));
        currentTime.setText(toTime(mediaPlayer.getCurrentPosition()));
    }

    private String toTime(int duration) {
        int time = duration / 1000;
        int minute = time / 60;
        int second = time % 60;
        String mm = String.format("%01d:%02d",minute,second);
        return mm;
    }

    /**
     * 播放歌曲
     */
    private void initPlay() {
        mediaPlayer = new MediaPlayer();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        mediaPlayer.reset();   //清空里面的其他歌曲
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();  //准备就绪
            mediaPlayer.start();  //开始唱歌
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取并显示歌曲标题和歌手歌名
     */
    private void initData() {
        int position = getIntent().getIntExtra("my",0);
        Log.d(TAG,"initData:" + position);
        cursor = getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null,null,null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        cursor.moveToPosition(position);
        String myTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        String myName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
        title.setText(myTitle);
        name.setText(myName);
    }

    private void initView() {
        title = findViewById(R.id.title);
        name = findViewById(R.id.name);
        seekBar = findViewById(R.id.seek_bar);
        currentTime = findViewById(R.id.current_time);
        totalTime = findViewById(R.id.total_time);
    }
}
