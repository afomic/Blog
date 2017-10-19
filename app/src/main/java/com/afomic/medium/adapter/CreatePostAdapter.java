package com.afomic.medium.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afomic.medium.R;
import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Html;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.NormalText;
import com.squareup.picasso.Picasso;

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
        LayoutInflater mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                BigText bigText=(BigText) htmlElement;
                AddBigTextHolder bigTextHolder=(AddBigTextHolder) holder;
                bigTextHolder.bigTextInput.setTag(position);
                bigTextHolder.bigTextInput.setText(bigText.getBodyText());
                getFocus(bigTextHolder.bigTextInput,position);
                break;
            case Html.TAG_IMAGE:
                Image htmlImage=(Image) htmlElement;
                AddImageViewHolder imageHolder=(AddImageViewHolder) holder;
                Uri imageUri=Uri.parse(htmlImage.getImageUrl());
                Picasso.with(mContext)
                        .load(imageUri)
                        .placeholder(R.drawable.image_placeholder)
                        .into(imageHolder.mImageView);
                break;
            case Html.TAG_NORMAL_TEXT:
                NormalText normalText=(NormalText) htmlElement;
                AddNormalTextHolder normalTextHolder=(AddNormalTextHolder)holder;
                normalTextHolder.normalTextInput.setTag(position);
                normalTextHolder.normalTextInput.setText(normalText.getTextBody());
                getFocus(normalTextHolder.normalTextInput,position);
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
            mImageView= itemView.findViewById(R.id.imv_add_image);
            removeButton= itemView.findViewById(R.id.imv_remove_btn);
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
            normalTextInput=itemView.findViewById(R.id.edt_add_normal_text);
            normalTextInput.addTextChangedListener(new MyTextWatch(normalTextInput));

        }

    }
    public class AddBigTextHolder extends RecyclerView.ViewHolder{
        EditText bigTextInput;

        public AddBigTextHolder(View itemView) {
            super(itemView);
            bigTextInput=itemView.findViewById(R.id.edt_add_big_text);
            bigTextInput.addTextChangedListener(new MyTextWatch(bigTextInput) );
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
    public class MyTextWatch implements TextWatcher{
        EditText editText;
        public MyTextWatch(EditText view){
            this.editText=view;

        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position=(int)editText.getTag();
            Html html=htmlList.get(position);
            switch (html.getType()){
                case Html.TAG_BIG_TEXT:
                    BigText bigText=(BigText) html;
                    bigText.setBodyText(s.toString());
                    break;
                case Html.TAG_NORMAL_TEXT:
                    NormalText normalText=(NormalText) html;
                    normalText.setTextBody(s.toString());
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    public void logData(){
        for(int i=0;i<htmlList.size();i++){
            Html html=htmlList.get(i);
            switch (html.getType()){
                case Html.TAG_BIG_TEXT:
                    BigText bigText=(BigText) html;
                    Log.e("medium", "logData: Big Text position: "+i+" content:"+bigText.getBodyText());
                    break;
                case Html.TAG_NORMAL_TEXT:
                    NormalText normalText=(NormalText) html;
                    Log.e("medium", "logData: normal position: "+i+" content:"+normalText.getTextBody() );
                    break;
            }

        }
    }
    public void getFocus(EditText edt,int position){
        if(position==(htmlList.size()-1)){
            edt.requestFocus();
            showKeyboard(edt);
        }

    }
}
