package com.afomic.medium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afomic.medium.adapter.HtmlAdapter;
import com.afomic.medium.model.Html;
import com.afomic.medium.model.Post;
import com.afomic.medium.util.HtmlParser;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {
    RecyclerView postContent;
    Post mPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        postContent=(RecyclerView) findViewById(R.id.rv_post_content);

        mPost=getIntent().getParcelableExtra(Constant.EXTRA_POST);

        ArrayList<Html> htmlElements= HtmlParser.fromHtml(mPost.getHtml());

        HtmlAdapter adapter=new HtmlAdapter(this,htmlElements);

        postContent.setLayoutManager(new LinearLayoutManager(this));

        postContent.setAdapter(adapter);

    }
}
