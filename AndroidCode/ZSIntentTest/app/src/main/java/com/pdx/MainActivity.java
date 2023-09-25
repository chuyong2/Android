package com.pdx;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button btnView,btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = findViewById(R.id.button_view);
        btnPhoto = findViewById(R.id.button_photo);

        btnView.setOnClickListener( view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        );

        btnPhoto.setOnClickListener( view -> {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent,123);
                }
        );
    }

    //重写onActivityResult方法

    /**
     *
     * @param requestCode  请求活动时传入的请求码
     * @param resultCode   返回数据时传入的处理结果码
     * @param data   携带着返回数据的Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 123:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(MainActivity.this,
                            data.getData().getPath(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}