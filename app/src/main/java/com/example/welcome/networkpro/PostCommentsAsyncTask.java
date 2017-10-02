package com.example.welcome.networkpro;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by WELCOME on 02-10-2017.
 */

public class PostCommentsAsyncTask extends AsyncTask<String,Void,ArrayList<PostComments>> {

    PostCommentsDownloadListener postCommentsDownloadListener;

    public PostCommentsAsyncTask(PostCommentsDownloadListener postCommentsDownloadListener) {
        this.postCommentsDownloadListener = postCommentsDownloadListener;
    }

    @Override
    protected ArrayList<PostComments> doInBackground(String... params) {

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

                ArrayList<PostComments> postComments = parsePostComments(response);

                return postComments;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<PostComments> parsePostComments(String response) {

        ArrayList<PostComments> postComments = null;
        try {
            JSONArray rootArray = new JSONArray(response);
            if(rootArray != null){
                postComments = new ArrayList<>();
                for(int i = 0; i < rootArray.length(); i++){
                    JSONObject postCommentObject = rootArray.getJSONObject(i);
                    int postid = postCommentObject.getInt("postId");
                    int id = postCommentObject.getInt("id");
                    String name = postCommentObject.getString("name");
                    String email = postCommentObject.getString("email");
                    String body = postCommentObject.getString("body");
                    PostComments comments = new PostComments(postid,id,name,email,body);
                    postComments.add(comments);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postComments;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<PostComments> postComments) {
        super.onPostExecute(postComments);
        postCommentsDownloadListener.onDownload(postComments);
    }

    public static interface PostCommentsDownloadListener{
        void onDownload(ArrayList<PostComments> postComments);
    }
}
