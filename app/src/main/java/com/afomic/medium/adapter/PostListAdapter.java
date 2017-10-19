package com.afomic.medium.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afomic.medium.R;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by afomic on 10/19/17.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {
    private Context mContext;
    private ArrayList<Post> posts;
    public PostListAdapter(Context ctx, ArrayList<Post> posts){
        mContext=ctx;
        this.posts=posts;
    }
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_post_list,parent,false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post postItem=posts.get(position);
        holder.postTitle.setText(postItem.getTitle());
        Picasso.with(mContext)
                .load(postItem.getPictureUrl())
                .placeholder(R.drawable.image_placeholder)
                .into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        if(posts==null){
            return 0;
        }
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        ImageView postImage;
        TextView postTitle;

        public PostViewHolder(View itemView) {
            super(itemView);
            postImage=itemView.findViewById(R.id.imv_post_image);
            postTitle=itemView.findViewById(R.id.tv_post_title);
        }
    }
}
