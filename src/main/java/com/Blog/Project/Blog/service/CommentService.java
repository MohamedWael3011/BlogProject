package com.Blog.Project.Blog.service;

import com.Blog.Project.Blog.model.Comment;

import java.util.List;

public interface CommentService {
    boolean CreateComment(Comment comment);
    boolean EditComment(int pid, Comment comment);
    boolean DeleteComment(int post_id, int comment_id);
    List<Comment> ListComment(int Post_id);
    List<Comment> ListAllComment();

}
