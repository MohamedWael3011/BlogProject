package com.Blog.Project.Blog.service;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    Comment CreateComment(Comment comment);
    Comment EditComment(int pid,int uid, Comment comment) throws GeneralException;
    void DeleteComment(int post_id, int comment_id) throws GeneralException;
    List<Comment> ListComment(int Post_id);
    List<Comment> ListAllComment();
    Page<Comment> getCommentsWithPage(int Post_id, int offset, int pageSize);

}
