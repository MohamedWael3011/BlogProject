package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.response.GeneralResponse;
import com.Blog.Project.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class PostController {


    @Autowired
    PostService postService;

    @GetMapping("/all-posts/{loggedUser}")
    public GeneralResponse<List<Post>> getAllPosts(@PathVariable("loggedUser") Integer loggedUser){
        GeneralResponse<List<Post>> res = new GeneralResponse<>();
        res.setData(postService.getPosts(loggedUser));
        res.setSuccess(true);
        return res;

    }

    @GetMapping("/get-details/{posterId}/{loggedUser}")
    public GeneralResponse<?> getPost(@PathVariable("posterId") Integer posterID,@PathVariable("loggedUser") Integer loggedUser) throws GeneralException {
        GeneralResponse<Post> res = new GeneralResponse<>();
        res.setData(postService.getPost(posterID,loggedUser));
        res.setSuccess(true);
        return res;
    }

    @GetMapping("posts/{posterId}/{loggedUser}")
    public GeneralResponse<?> getPostByUser(@PathVariable("posterId") Integer posterId,@PathVariable("loggedUser") Integer loggedUser) throws GeneralException {
        GeneralResponse<List<Post>> res = new GeneralResponse<>();
        res.setData (postService.getPostByUser(posterId,loggedUser));
        res.setSuccess(true);
        return res;


    }

    @GetMapping("/page/{offset}/{pageSize}")
    public ResponseEntity<?> getPostWithPage(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pagesize) throws GeneralException {
        Page<Post> posts = postService.getPostsWithPage(offset,pagesize);
        return new ResponseEntity<>( posts, HttpStatus.OK);
    }

}
