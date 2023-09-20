package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Comment;
import com.Blog.Project.Blog.response.GeneralResponse;
import com.Blog.Project.Blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("all-comment/{idPost}")
    public GeneralResponse<List<Comment>> getComments(@PathVariable("idPost") Integer pid) {
        GeneralResponse<List<Comment>> res = new GeneralResponse<List<Comment>>();
        res.setData(commentService.ListComment(pid));
        res.setSuccess(true);
        return res;
    }

    @PostMapping("add-comment/{idPost}")
    public GeneralResponse<?> addComment(@PathVariable("idPost") Integer id,@RequestBody Comment comment){
        GeneralResponse<Comment> res = new GeneralResponse<>();
        comment.setPostId(id);
        res.setData(commentService.CreateComment(comment));
        res.setSuccess(true);
        return res;
    }

    @DeleteMapping("delete-comment/{idComment}/{idUser}")
    public GeneralResponse<?> delComment(@PathVariable("idUser") Integer uid,@PathVariable("idComment") Integer cid) throws GeneralException{
        commentService.DeleteComment(uid,cid);
        GeneralResponse<Comment> res = new GeneralResponse<>();
        res.setMessage("Comment has been deleted");
        res.setSuccess(true);
        return res;
    }

    @PutMapping("edit-comment/{idPost}")
    public GeneralResponse<?> editComment(@PathVariable("idPost") Integer pid,@RequestBody Comment comment) throws GeneralException{
        GeneralResponse<Comment> res = new GeneralResponse<>();
        int uid = comment.getUser().getId();
        res.setData(commentService.EditComment(pid,uid,comment));
        res.setSuccess(true);
        return res;
    }

    @GetMapping("all-comment/all")
    public GeneralResponse<List<Comment>> getAllComments() {
        GeneralResponse<List<Comment>> res = new GeneralResponse<List<Comment>>();
        res.setData(commentService.ListAllComment());
        res.setSuccess(true);
        return res;
    }

//    @GetMapping("all-comment/{idPost}/{offset}/{pageSize}")
//    public ResponseEntity<?> getCommentsWithPage(@PathVariable("idPost") Integer pid, @PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pagesize) throws GeneralException {
//        Page<Comment> comments = commentService.getCommentsWithPage(pid,offset,pagesize);
//        return new ResponseEntity<>(comments, HttpStatus.OK);
//    }
}
