package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.UriUtils;
import com.bumptech.glide.Glide;
import com.example.administrator.db.MyDbHelper;

import java.io.File;
import java.io.IOException;

public class AddInfoActivity extends AppCompatActivity {
    private EditText editTitle,editContent;
    private Button btnCamera,btnPhoto,btnSave;
    private ImageView imgPreview;
    private String tmpPath,displayPath;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        initView();

        btnOnClick();

        btnSave();
    }

    /**
     * 把信息保存到数据库中
     */
    private void btnSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存信息到数据库代码
                Time time = new Time();
                time.setToNow();
                ContentValues contentValues = new ContentValues(); //1行
                contentValues.put("title",editTitle.getText().toString());  //title列
                contentValues.put("content",editContent.getText().toString());  //content列
                contentValues.put("imgPath",displayPath);  //imgPath列
                contentValues.put("myTime",time.year + "/" + (time.month + 1) + "/" + time.monthDay);
                //myTime列
                database.insert("tb_memory",null,contentValues); //将该行数据保存到数据表中
                Toast.makeText(AddInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                //跳转主页面
                Intent intent = new Intent(AddInfoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 单击按钮拍照
     */
    private void btnOnClick() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //拍照
                Time time = new Time();
                time.setToNow();
                String randTime = time.year + (time.month + 1) + time.monthDay + time.hour + time.minute
                        + time.second + "";
                tmpPath = Environment.getExternalStorageDirectory() + "/image" + randTime + ".jpg";
                File imageFile = new File(tmpPath);
                try {
                    imageFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(intent,11);
            }
        });
        //从相册中选择图片
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选择photo
                Intent intent = new Intent("android.media.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,22);
            }
        });
    }
    //接受拍好照片，接受从图库选择  --- 系统回调


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 11:
                if(requestCode == RESULT_OK){
                    displayPath = tmpPath;
                    Glide.with(AddInfoActivity.this).load(displayPath).into(imgPreview);
                }
                break;
            case 22:
                Uri imageUri = data.getData();
                if(imageUri == null){
                    return;
                }
                displayPath = UriUtils.uri2File(imageUri).getPath();
                Glide.with(AddInfoActivity.this).load(displayPath).into(imgPreview);
                break;
                default:
                    break;
        }
    }

    /**
     * 绑定控件
     */
    private void initView() {
        editTitle = findViewById(R.id.editText_title);
        editContent = findViewById(R.id.editText_content);
        btnCamera = findViewById(R.id.button_camera);
        btnPhoto = findViewById(R.id.button_photo);
        btnSave = findViewById(R.id.button_save);
        imgPreview = findViewById(R.id.imageView_preview);
        myDbHelper = new MyDbHelper(AddInfoActivity.this);
        database = myDbHelper.getWritableDatabase();
    }


}
