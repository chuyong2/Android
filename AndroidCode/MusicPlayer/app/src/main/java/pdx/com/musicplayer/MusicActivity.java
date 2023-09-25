package pdx.com.musicplayer;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pdx.com.MyAdapter.MusicAdapter;
import pdx.com.entity.Music;

public class MusicActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private static final String TAG = "MusicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        initView();
        initData();
    }

    private void initData() {
        List<Music> musicList = new ArrayList<>();
        Cursor cursor = getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null, null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        Log.d(TAG, "initData:我查询获取到的歌曲共:" + cursor.getCount() + "首");
        while (cursor.moveToNext()){
            String myMusicTitle = cursor.
                    getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String singerName = cursor.
                    getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            Music music = new Music(myMusicTitle,singerName);
            musicList.add(music);
        }
        cursor.close();
        MusicAdapter musicAdapter = new MusicAdapter(musicList);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(musicAdapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
    }
}
