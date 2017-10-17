package com.afomic.medium.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.afomic.medium.R;
import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Html;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.NormalText;

import java.util.ArrayList;

/**
 * Created by afomic on 10/17/17.
 *
 */




public class CreatePostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface CreatePostCallback{
        void onImageRemovePressed(int position);

    }

    private ArrayList<Html> htmlList;
    private Context mContext;
    private CreatePostCallback mListener;
    public CreatePostAdapter(Context context,ArrayList<Html> arrayList){
        htmlList=arrayList;
        mContext=context;
        mListener=(CreatePostCallback) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        switch (viewType){
            case Html.TAG_BIG_TEXT:
                View v= mInflater.inflate(R.layout.item_add_big_text,parent,false);
                return new AddBigTextHolder(v);
            case Html.TAG_IMAGE:
                View mView= mInflater.inflate(R.layout.item_add_image,parent,false);
                return new AddImageViewHolder(mView);
            case Html.TAG_NORMAL_TEXT:
                View normalTextView= mInflater.inflate(R.layout.item_add_normal_text,parent,false);
                return new AddNormalTextHolder(normalTextView);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Html htmlElement=htmlList.get(position);
        switch (htmlElement.getType()){
            case Html.TAG_BIG_TEXT:
                BigText mBigText=(BigText) htmlElement;
                AddBigTextHolder mBigTextHolder=(AddBigTextHolder) holder;
                mBigTextHolder.bindView(mBigText);
                break;
            case Html.TAG_IMAGE:
                Image mImage=(Image) htmlElement;
                AddImageViewHolder mImageViewHolder=(AddImageViewHolder) holder;
                mImageViewHolder.bindView(mImage);

                break;
            case Html.TAG_NORMAL_TEXT:
                NormalText mNormalText=(NormalText) htmlElement;
                AddNormalTextHolder mAddNormalTextHolder=(AddNormalTextHolder) holder;
                mAddNormalTextHolder.bindView(mNormalText);
                break;
        }

    }
    @Override
    public int getItemViewType(int position) {
        Html mHtml=htmlList.get(position);
        return mHtml.getType();
    }

    @Override
    public int getItemCount() {
        if ((htmlList==null)){
            return 0;
        }
        return htmlList.size();
    }

    public class AddImageViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView,removeButton;

        public AddImageViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView) itemView.findViewById(R.id.imv_add_image);
            removeButton=(ImageView) itemView.findViewById(R.id.imv_remove_btn);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onImageRemovePressed(getAdapterPosition());
                }
            });
        }

        public void bindView(Image image){
            Uri imageUri=Uri.parse(image.getImageUrl());

        }
    }
    public class AddNormalTextHolder extends RecyclerView.ViewHolder{
        EditText normalTextInput;

        public AddNormalTextHolder(View itemView) {
            super(itemView);
            normalTextInput=(EditText) itemView.findViewById(R.id.edt_add_normal_text);
            normalTextInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    NormalText mNormalText=(NormalText) htmlList.get(getAdapterPosition());
                    mNormalText.setTextBody(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }
        public void bindView(NormalText text){
            if(getAdapterPosition()==(htmlList.size()-1)){
                normalTextInput.requestFocus();
                showKeyboard(normalTextInput);

            }
            normalTextInput.setText(text.getTextBody());

        }
    }
    public class AddBigTextHolder extends RecyclerView.ViewHolder{
        EditText bigTextInput;

        public AddBigTextHolder(View itemView) {
            super(itemView);
            bigTextInput=(EditText) itemView.findViewById(R.id.edt_add_big_text);
            bigTextInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    BigText mBigText=(BigText) htmlList.get(getAdapterPosition());
                    mBigText.setBodyText(charSequence.toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
        public void bindView(BigText text){
            if(getAdapterPosition()==(htmlList.size()-1)){
                bigTextInput.requestFocus();
                showKeyboard(bigTextInput);
            }
            bigTextInput.setText(text.getBodyText());
        }
    }
    public void showKeyboard(final EditText editText){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            }
        },100);

    }

}
