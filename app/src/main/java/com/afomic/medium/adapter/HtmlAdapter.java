package com.afomic.medium.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afomic.medium.model.NormalText;
import com.afomic.medium.R;
import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Html;
import com.afomic.medium.model.Image;

import java.util.ArrayList;

/**
 * Created by afomic on 10/16/17.
 */

public class HtmlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<Html> elementList;
    public HtmlAdapter(Context context, ArrayList<Html> htmlElements){
        mContext=context;
        elementList=htmlElements;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        switch (viewType){
            case Html.TAG_BIG_TEXT:
                View v= mInflater.inflate(R.layout.item_big_text,parent,false);
                return new BigTextViewHolder(v);

            case Html.TAG_IMAGE:
                View mView= mInflater.inflate(R.layout.item_image,parent,false);
                return new ImageViewHolder(mView);
            case Html.TAG_NORMAL_TEXT:
                View normalTextView= mInflater.inflate(R.layout.item_normal_text,parent,false);
                return new NormalTextHolder(normalTextView);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Html htmlElement=elementList.get(position);
        switch (htmlElement.getType()){
            case Html.TAG_BIG_TEXT:
                BigText mBigText=(BigText) htmlElement;
                break;
            case Html.TAG_IMAGE:
                Image mImage=(Image) htmlElement;
                ImageViewHolder mViewHolder=(ImageViewHolder) holder;
                break;
            case Html.TAG_NORMAL_TEXT:
                NormalText mNormalText=(NormalText) htmlElement;
                NormalTextHolder mNormalTextHolder=(NormalTextHolder) holder;
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Html mHtml=elementList.get(position);
        return mHtml.getType();
    }

    @Override
    public int getItemCount() {
        if(elementList==null){
            return 0;
        }
        return elementList.size();
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView) itemView.findViewById(R.id.imv_image);
        }
    }
    public class NormalTextHolder extends RecyclerView.ViewHolder{
        TextView mNormalTextView;
        public NormalTextHolder(View itemView) {
            super(itemView);
            mNormalTextView=(TextView) itemView.findViewById(R.id.tv_normal_text);
        }
    }
    public class BigTextViewHolder extends RecyclerView.ViewHolder{
        TextView mBigTextView;
        public BigTextViewHolder(View itemView) {
            super(itemView);
            mBigTextView=(TextView) itemView.findViewById(R.id.tv_big_text);
        }
    }
}
