package com.oscarmyomin.wordpressreader.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscarmyomin.wordpressreader.R;
import com.oscarmyomin.wordpressreader.activity.PostByCategory;
import com.oscarmyomin.wordpressreader.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by philippe on 9/30/17.
 */

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.ViewHolders>{

    private List<Category> itemList;
    private static Context context;
    Activity activity;

    public Adapter_Category(Context context, List<Category> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        ViewHolders rcv = new ViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        holder.title.setText(itemList.get(position).getName());
        holder.id = itemList.get(position).getId();
        holder.mcount.setText(String.valueOf(itemList.get(position).getCount()));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public static class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        Activity ac;
        public TextView title, mcount;
        public int id, count;
        public ViewHolders(final View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_cate_title);
            mcount = itemView.findViewById(R.id.txt_cate_count);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent post = new Intent(context, PostByCategory.class);
            post.putExtra("id", String.valueOf(id));
            context.startActivity(post);
        }
    }
}
