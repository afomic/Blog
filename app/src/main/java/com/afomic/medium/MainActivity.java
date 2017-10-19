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

import com.afomic.medium.adapter.PostListAdapter;
import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Html;
import com.afomic.medium.adapter.HtmlAdapter;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.NormalText;
import com.afomic.medium.model.Post;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView postList;
    FloatingActionButton mFab;
    ArrayList<Post> mPosts;
    PostListAdapter adapter;

    DatabaseReference blogRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        mFab=(FloatingActionButton) findViewById(R.id.fab);

        postList=(RecyclerView) findViewById(R.id.rv_post_list);

        blogRef= FirebaseDatabase.getInstance().getReference("blog");

        mPosts=new ArrayList<>();

        adapter=new PostListAdapter(this,mPosts);

        postList.setLayoutManager(new LinearLayoutManager(this));

        postList.setAdapter(adapter);


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent=new Intent(MainActivity.this,CreatePostActivity.class);
                startActivity(mIntent);
            }
        });

        blogRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post post=dataSnapshot.getValue(Post.class);
                mPosts.add(0,post);
                adapter.notifyItemInserted(0);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
