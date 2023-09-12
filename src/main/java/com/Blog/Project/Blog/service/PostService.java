package com.Blog.Project.Blog.service;

import com.Blog.Project.Blog.model.Post;

import java.util.List;

public interface PostService {

    Post addPost(Post post);
    void delPost(int PostID);
    Post editPost(Post post);
    List<Post> getPosts();
    Post getPost(int PostID);


}
