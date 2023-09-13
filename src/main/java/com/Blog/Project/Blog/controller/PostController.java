package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Post> getAllPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{idPost}")
    public Post getPost(@PathVariable("idPost") Integer id){
        return postService.getPost(id);
    }

}
