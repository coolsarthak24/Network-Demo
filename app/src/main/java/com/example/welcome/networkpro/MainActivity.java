package com.example.welcome.networkpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PostAsyncTask.PostsDownloadListener{

    ListView listView;
    ArrayList<Post> p;
    CustomAdapterPost adapterPost;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.listView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        p = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending your request", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                listView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                fetchposts();
            }
        });

        adapterPost = new CustomAdapterPost(this,p);
        listView.setAdapter(adapterPost);
    }

    private void fetchposts() {

        String urlString = "https://jsonplaceholder.typicode.com/posts";
        PostAsyncTask asyncTask = new PostAsyncTask(this);
        asyncTask.execute(urlString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDownload(final ArrayList<Post> posts) {

        Log.i("Reached", "onDownload: MainActivity");

        p.clear();

        for(Post post : posts){
            p.add(post);
        }

        adapterPost.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = posts.get(position);
                Intent intent = new Intent(MainActivity.this,Comments.class);
                intent.putExtra("ID",post.id);
                startActivity(intent);
            }
        });

    }
}
