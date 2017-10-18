package com.afomic.medium;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Html;
import com.afomic.medium.adapter.HtmlAdapter;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.NormalText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView postContent;
    FloatingActionButton mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab=(FloatingActionButton) findViewById(R.id.fab);
        postContent=(RecyclerView) findViewById(R.id.rv_post_content);
        postContent.setLayoutManager(new LinearLayoutManager(this));
        HtmlAdapter mAdapter=new HtmlAdapter(this,getDummyPost());
        postContent.setAdapter(mAdapter);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent=new Intent(MainActivity.this,CreatePostActivity.class);
                startActivity(mIntent);
            }
        });
        requestPermission();

    }
    public ArrayList<Html> getDummyPost(){
        ArrayList<Html> content=new ArrayList<>();
        for(int i=10;i>=0;i--){
            if(i%2==0){
                content.add(new Image(""));
            }else if(i%3==0){
                content.add(new BigText(""));
            }else {
                content.add(new NormalText(""));
            }

        }
        return content;
    }
    public void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permissionCheck = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permissionCheck!=PackageManager.PERMISSION_GRANTED){
                requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},110);
            }
        }

    }

}
