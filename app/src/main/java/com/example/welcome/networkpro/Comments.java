package com.example.welcome.networkpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class Comments extends AppCompatActivity implements PostCommentsAsyncTask.PostCommentsDownloadListener{

    ListView listView2;
    ArrayList<PostComments> p;
    CustomAdapterPostComments adapterPostComments;
    ProgressBar progressBar2;
    Intent i;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        i = getIntent();

        ID = i.getIntExtra("ID",0) + "";

        listView2 = (ListView)findViewById(R.id.listview2);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        p = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Processing your request", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                listView2.setVisibility(View.GONE);
                progressBar2.setVisibility(View.VISIBLE);
                fetchComments();


            }
        });
        adapterPostComments = new CustomAdapterPostComments(this,p);
        listView2.setAdapter(adapterPostComments);
    }

    private void fetchComments() {

        String urlString = "https://jsonplaceholder.typicode.com/posts/" + ID + "/comments";
        PostCommentsAsyncTask postCommentsAsyncTask = new PostCommentsAsyncTask(this);
        postCommentsAsyncTask.execute(urlString);


    }

    @Override
    public void onDownload(ArrayList<PostComments> postComments) {

        p.clear();

        for(PostComments comments : postComments){
            p.add(comments);
        }

        adapterPostComments.notifyDataSetChanged();

        progressBar2.setVisibility(View.GONE);
        listView2.setVisibility(View.VISIBLE);


    }
}
