package com.Blog.Project.Blog.service;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Post;

import java.util.List;

public interface PostService {

    Post addPost(int userID,Post post) throws GeneralException;
    void delPost(int postID);
    Post editPost(Post post) throws GeneralException;
    List<Post> getPosts();
    Post getPost(int postID) throws GeneralException;
    Post sharePost(int postID,Post newPost) throws GeneralException;


}
