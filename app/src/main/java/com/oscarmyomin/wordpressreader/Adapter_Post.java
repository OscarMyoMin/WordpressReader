package com.oscarmyomin.wordpressreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscarmyomin.wordpressreader.activity.PostDetialActivity;
import com.oscarmyomin.wordpressreader.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by philippe on 9/23/17.
 */

public class Adapter_Post extends RecyclerView.Adapter<Adapter_Post.ViewHolders>{
    private List<Post> itemList;
    private static Context context;
    Activity activity;

    public Adapter_Post(Context context, List<Post> itemList) {
        this.itemList = itemList;
        this.context = context;
    }
    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        ViewHolders rcv = new ViewHolders(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        Context mcontext = holder.image.getContext();
        Picasso.with(mcontext)
                .load(itemList.get(position).getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.image);
        holder.title.setText(itemList.get(position).getTitle());
        holder.content = itemList.get(position).getContent();
        holder.img = itemList.get(position).getImage();
        holder.auth_image = itemList.get(position).getAuthor_image();
        holder.auth_name = itemList.get(position).getAuthor_name();
        holder.time = itemList.get(position).getTime();
        holder.link = itemList.get(position).getLink();
    }
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public static class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        Activity ac;
        public TextView title;
        public ImageView image;
        public String content, img, auth_image, auth_name, time, link;
        public ViewHolders(final View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            image = (ImageView) itemView.findViewById(R.id.img_feature);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent detail = new Intent(context, PostDetialActivity.class);
            detail.putExtra("image", img);
            detail.putExtra("auth_image", auth_image);
            detail.putExtra("auth_name", auth_name);
            detail.putExtra("time", time);
            detail.putExtra("title", title.getText().toString());
            detail.putExtra("content", content);
            detail.putExtra("link", link);
            context.startActivity(detail);
        }
    }
}