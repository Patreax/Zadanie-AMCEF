package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

public class Post {
    @ApiModelProperty(notes = "id of user associated to this post")
    @NonNull
    private int userId;
    @ApiModelProperty(notes = "id of the post")
    @NonNull
    private int id;
    @ApiModelProperty(notes = "title of the post")
    @NonNull
    private String title;
    @ApiModelProperty(notes = "body of the post")
    @NonNull
    private String body;

    public Post() {
    }

    public Post(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
