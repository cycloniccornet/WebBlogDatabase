package com.example.demo.Model;

public class Post {

    private int postId;
    private String postTitle;
    private String postContent;
    private String auther;
    private String date;

    public Post() {
    }

    public Post(int postId, String postTitle, String postContent, String auther, String date) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.auther = auther;
        this.date = date;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
