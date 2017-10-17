package com.afomic.medium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afomic.medium.adapter.CreatePostAdapter;
import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Html;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.NormalText;

import java.util.ArrayList;

public class CreatePostActivity extends AppCompatActivity implements CreatePostAdapter.CreatePostCallback{
    ImageView addImageButton,addBigTextButton,addNormalTextButton;
    RecyclerView postEditor;
    CreatePostAdapter mAdapter;

    ArrayList<Html> htmlList;

    private static final int GET_IMAGE_REQUEST_CODE=102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        postEditor=(RecyclerView) findViewById(R.id.rv_post_editor);

        addBigTextButton=(ImageView) findViewById(R.id.imv_add_big_text);

        addNormalTextButton=(ImageView) findViewById(R.id.imv_add_normal_text);

        addImageButton=(ImageView) findViewById(R.id.imv_add_image);

        htmlList=new ArrayList<>();

        mAdapter=new CreatePostAdapter(this,htmlList);

        postEditor.setLayoutManager(new LinearLayoutManager(this));

        postEditor.setAdapter(mAdapter);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent=new Intent(Intent.ACTION_GET_CONTENT);
                startActivityForResult(mIntent,GET_IMAGE_REQUEST_CODE);

            }
        });
        addNormalTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NormalText mNormalText=new NormalText("");
                htmlList.add(mNormalText);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(CreatePostActivity.this,""+htmlList.size(),Toast.LENGTH_SHORT).show();

            }
        });
        addBigTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BigText mBigText=new BigText("");
                htmlList.add(mBigText);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(CreatePostActivity.this,""+htmlList.size(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==GET_IMAGE_REQUEST_CODE && resultCode==RESULT_OK){
            Image newImage=new Image(data.getData().toString());
            htmlList.add(newImage);
            mAdapter.notifyDataSetChanged();
            Toast.makeText(CreatePostActivity.this,""+htmlList.size(),Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImageRemovePressed(int position) {
        htmlList.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
