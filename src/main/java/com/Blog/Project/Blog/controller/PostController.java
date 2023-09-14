package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/post"})
public class PostController {


    @Autowired
    PostService postService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<?> getPost(@PathVariable("idPost") Integer id) throws GeneralException {
        return new ResponseEntity<>( postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/page/{offset}/{pageSize}")
    public ResponseEntity<?> getPostWithPage(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pagesize) throws GeneralException {
        Page<Post> posts = postService.getPostsWithPage(offset,pagesize);
        return new ResponseEntity<>( posts, HttpStatus.OK);
    }

}
