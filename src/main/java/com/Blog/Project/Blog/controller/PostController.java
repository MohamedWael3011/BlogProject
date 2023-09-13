package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/allPosts")
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/{idPost}")
    public Post getPost(@PathVariable("idPost") Integer id){
        return postService.getPost(id);
    }

    @GetMapping("1/{id}")
    public ResponseEntity<?> x(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("2/{id}")
    public ResponseEntity<?> y(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("3/{id}")
    public ResponseEntity<?> z(@PathVariable("id") Integer id) throws GeneralException {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }


}
