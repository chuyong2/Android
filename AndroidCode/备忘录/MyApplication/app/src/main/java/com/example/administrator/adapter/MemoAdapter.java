package com.example.administrator.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.domain.MemoBean;
import com.example.administrator.myapplication.R;


import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private Context myContext;
    private List<MemoBean> array;
    //负责加载item布局
    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.recy_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //加载item数据
    @Override
    public void onBindViewHolder(@NonNull final MemoAdapter.ViewHolder viewHolder, int i) {
        final MemoBean memoBean = array.get(i);
        viewHolder.itemTile.setText(memoBean.getTitle());
        viewHolder.itemContent.setText(memoBean.getContent());
        viewHolder.itemTime.setText(memoBean.getTime());
        Glide.with(myContext).load(memoBean.getImgPath()).into(viewHolder.itemImage);

        //设置RecyclerView中每一个子项的颜色和形状
        Random random = new Random();
        int color = Color.argb(255,random.nextInt(256),random.nextInt(256)
        ,random.nextInt(256));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(10f);  //圆角Radius
        gradientDrawable.setColor(color);
        viewHolder.itemLayout.setBackground(gradientDrawable);

        //单击其中一个的子项，弹出删除功能
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出对话框
                AlertDialog.Builder dialog = new AlertDialog.Builder(viewHolder);
                dialog.setMessage("确定删除吗?");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
            }
        });
    }

    //recyView一共有多少子项
    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTile,itemContent,itemTime;
        ImageView itemImage;
        LinearLayout itemLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTile = itemView.findViewById(R.id.item_title);
            itemContent = itemView.findViewById(R.id.item_content);
            itemTime = itemView.findViewById(R.id.item_time);
            itemImage = itemView.findViewById(R.id.item_image);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
