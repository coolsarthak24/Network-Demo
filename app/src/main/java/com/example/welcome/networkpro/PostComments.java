package com.example.welcome.networkpro;

/**
 * Created by WELCOME on 01-10-2017.
 */

public class PostComments {

    int postid;
    int id;
    String name;
    String email;
    String body;

    public PostComments(int post_id, int id, String name, String email, String body) {
        this.postid = post_id;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
