package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Comment;
import com.Blog.Project.Blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/comments"})
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/{idPost}")
    public ResponseEntity<?> getComments(@PathVariable("idPost") Integer id) {
        return new ResponseEntity<>(commentService.ListComment(id),HttpStatus.OK);
    }

    @PostMapping("/{idPost}")
    public ResponseEntity<?> addComment(@PathVariable("idPost") Integer id,@RequestBody Comment comment){
        comment.setPid(id);
        return new ResponseEntity<>(commentService.CreateComment(comment),HttpStatus.OK);
    }

    @DeleteMapping("/{idPost}/{idComment}")
    public ResponseEntity<?> delComment(@PathVariable("idPost") Integer pid,@PathVariable("idComment") Integer cid){
        return new ResponseEntity<>(commentService.DeleteComment(pid,cid),HttpStatus.OK);
    }

    @PutMapping("/{idPost}")
    public ResponseEntity<?> editComment(@PathVariable("idPost") Integer pid,@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.EditComment(pid,comment),HttpStatus.OK);
    }

    @GetMapping("comments")
    public ResponseEntity<?> getAllComments() {
        return new ResponseEntity<>(commentService.ListAllComment(),HttpStatus.OK);
    }

    @GetMapping("/{idPost}/{offset}/{pageSize}")
    public ResponseEntity<?> getCommentsWithPage(@PathVariable("idPost") Integer pid, @PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pagesize) throws GeneralException {
        Page<Comment> comments = commentService.getCommentsWithPage(pid,offset,pagesize);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
