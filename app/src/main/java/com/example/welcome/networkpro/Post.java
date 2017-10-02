package com.example.welcome.networkpro;

/**
 * Created by WELCOME on 01-10-2017.
 */

public class Post {

    int userid;
    int id;
    String title;
    String body;

    public Post(int user_id, int id, String title, String body) {
        this.userid = user_id;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
