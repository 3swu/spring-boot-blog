package com.wulei.blog.model;

import java.util.ArrayList;
import java.util.List;

public class GlobalInfo {
    private String blogName;

    private int numsOfVisits;

    private List<Post> posts;

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public int getNumsOfVisits() {
        return numsOfVisits;
    }

    public void setNumsOfVisits(int numsOfVisits) {
        this.numsOfVisits = numsOfVisits;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
