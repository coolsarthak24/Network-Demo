package com.example.welcome.networkpro;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by WELCOME on 02-10-2017.
 */

public class PostAsyncTask extends AsyncTask<String,Void,ArrayList<Post>> {

    PostsDownloadListener postsDownloadListener;

    public PostAsyncTask(PostsDownloadListener postsDownloadListener) {
        this.postsDownloadListener = postsDownloadListener;
    }

    @Override
    protected ArrayList<Post> doInBackground(String... params) {

        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                String response = "";
                while (scanner.hasNext()){
                    response += scanner.nextLine();
                }

                ArrayList<Post> posts = parsePost(response);
                Log.i("TAG", "doInBackground: ended");

                return posts;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Post> parsePost(String response) {

        ArrayList<Post> posts = null;
        try {
            JSONArray rootArray = new JSONArray(response);
            if(rootArray != null){
                posts = new ArrayList<>();
                for(int i = 0; i < rootArray.length(); i++){
                    JSONObject postObject = rootArray.getJSONObject(i);
                    int userid = postObject.getInt("userId");
                    int id = postObject.getInt("id");
                    String title = postObject.getString("title");
                    String body = postObject.getString("body");
                    Post post = new Post(userid,id,title,body);
                    posts.add(post);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("TAG", "parsePost: end");
        return posts;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        super.onPostExecute(posts);
        postsDownloadListener.onDownload(posts);
    }

    public static interface PostsDownloadListener{
        void onDownload(ArrayList<Post> posts);
    }
}
