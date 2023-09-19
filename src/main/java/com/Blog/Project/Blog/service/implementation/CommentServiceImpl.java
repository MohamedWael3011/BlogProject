package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.exceptions.ErrorCode;
import com.Blog.Project.Blog.exceptions.GeneralException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.Blog.Project.Blog.repository.CommentRepository;
import com.Blog.Project.Blog.service.CommentService;
import com.Blog.Project.Blog.model.Comment;

import java.time.Instant;
import java.util.Date;
import java.util.List;


//todo validation (add) and authorization (get) if any
@Service
public class CommentServiceImpl implements CommentService {
    int ADMIN = 0;
    private CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public Comment CreateComment(Comment comment){
    comment.setCommentDate(Date.from(Instant.now()));
            commentRepository.save(comment);
            return comment;

    }
    @Override
    public Comment EditComment(int pid,int uid, Comment comment) throws GeneralException{
        try {
            Comment oldComment = commentRepository.getReferenceById(comment.getCid());
            if (uid != ADMIN && oldComment.getUserId() != uid) {
                throw new GeneralException(ErrorCode.ACCESS_DENIED, "You cannot edit a comment that is not done by you");
            }
            commentRepository.save(comment);
        }catch (EntityNotFoundException var4) {
            throw new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Comment with that ID");
        } catch (Exception var5) {
            throw new GeneralException(ErrorCode.INVALID_DATA, "Invalid Data");
        }
        return comment;
    }
    @Override
    public void DeleteComment(int uid,int comment_id) throws GeneralException{
        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Comment with this ID");
        });
            if (uid != ADMIN && uid != comment.getUserId()) {
                throw new GeneralException(ErrorCode.ACCESS_DENIED, "You cannot delete a comment that is not done by you");
            }
            commentRepository.deleteById(comment_id);
    }
    @Override
    public List<Comment> ListComment(int post_id){
        return (commentRepository.findByPostId(post_id));
    }
    @Override
    public List<Comment> ListAllComment(){
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> getCommentsWithPage(int pid, int offset, int pageSize) {
        return commentRepository.findAllByPostId(pid,PageRequest.of(offset,pageSize));
    }
}
