package pdx.com.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pdx.com.entity.Music;
import pdx.com.musicplayer.PlayerActivity;
import pdx.com.musicplayer.R;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    Context myContext;
    List<Music> musicList;

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.music_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        //单击任意歌曲跳转到播放界面
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition(); //返回数据在适配器的位置
                Intent intent = new Intent(view.getContext(),PlayerActivity.class);
                intent.putExtra("my",position);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    /**
     * 对RecycleView中子项的数据进行赋值
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Music musicInfo = musicList.get(position);
        viewHolder.musicTitle.setText(musicInfo.getMusicTitle());
        viewHolder.singerName.setText(musicInfo.getSingerName());
    }

    /**
     * 返回数据源长度
     * @return
     */
    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View musicView;
        TextView musicTitle;
        TextView singerName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            musicView = itemView;
            musicTitle = itemView.findViewById(R.id.music_title);
            singerName = itemView.findViewById(R.id.singer_name);
        }
    }
}
