package com.Blog.Project.Blog.controller;


import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/post"})
public class PostController {
    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping({"/{id}"})
    public Post getPost(@PathVariable int id){
        return postService.getPost(id);
    }

    @PostMapping({"/add"})
    public Post addPost(@RequestBody Post Post){
        return postService.addPost(Post);
    }

    @PutMapping({"/edit"})
    public void editPost(@RequestBody Post Post){
        postService.editPost(Post);
    }
    @DeleteMapping({"del/{id}"})
    public void delPost(@PathVariable int id){
        postService.delPost(id);
    }
}
