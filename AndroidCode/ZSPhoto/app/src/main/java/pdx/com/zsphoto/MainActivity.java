package pdx.com.zsphoto;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView imgCamer;
    private Button btnCamer;
    //定义路径  1.拍照的临时路径  2.显示照片的最终路径
    String tmpPath,displayPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnCamOnClick();
    }

    /**
     * 绑定控件
     */
    private void initView() {
        imgCamer = findViewById(R.id.imageView_cam);
        btnCamer = findViewById(R.id.button_cam);
    }

    /**
     * 拍照按钮单击事件
     */
    private void btnCamOnClick() {
        btnCamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick:1" + "单击了按钮");
                //拍照代码
                //前期准备：路径
                tmpPath = Environment.getExternalStorageDirectory() +
                        "/img_" + readFileName() + ".jpg";
                File imgFile = new File(tmpPath);  //创建保存照片的文件，照片都保存到本路径下
                try {
                    if(imgFile.exists()){
                        imgFile.delete();
                    }
                    imgFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //创建一个打开相机的Intent
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //告诉相机图片的保存位置
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imgFile));
                //打开相机
                startActivityForResult(intent,11);
            }
        });
    }

    /**
     * 拍完照后，接收照片并显示，系统回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 11:
                if (resultCode == RESULT_OK){
                    //根据照片的真实路径来显示照片
                    displayPath = tmpPath;
                    //显示照片
                    Glide.with(MainActivity.this).load(displayPath).into(imgCamer);
                }
        }
    }
}