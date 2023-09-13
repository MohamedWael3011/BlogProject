package com.Blog.Project.Blog.service.implementation;

import org.springframework.stereotype.Service;
import com.Blog.Project.Blog.repository.CommentRepository;
import com.Blog.Project.Blog.service.CommentService;
import com.Blog.Project.Blog.model.Comment;
import java.util.List;


//todo Validation and data restrictions
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public boolean CreateComment(Comment comment){
        try {
            commentRepository.save(comment);
            return true;
        }
        catch (Exception e){
            System.out.print(e.toString());
            return false;
        }
    }
    @Override
    public boolean EditComment(int pid, Comment comment) {
        try {
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            System.out.print(e.toString());
            return false;
        }
    }
    @Override
    public boolean DeleteComment(int pid,int comment_id) {
        try {
            commentRepository.deleteById(comment_id);
            return true;
        } catch (Exception e) {
            System.out.print(e.toString());
            return false;
        }
    }
    @Override
    public List<Comment> ListComment(int post_id){
        return (commentRepository.findByPid(post_id));
    }
    public List<Comment> ListAllComment(){
        return commentRepository.findAll();
    }
}
