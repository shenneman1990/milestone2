package com.example.ratnabarot.recipeapp.models;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class Comment {

    private String content;
    private String user_id;
    private @ServerTimestamp Date timestamp;
    private String comment_id;
    private String recipeName;

    public Comment(String content, String user_id, Date timestamp, String comment_id, String recipeName) {
        this.content = content;
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.comment_id = comment_id;
        this.recipeName = recipeName;
    }

    public Comment(){

    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
