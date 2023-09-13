package com.Blog.Project.Blog.controller;
import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.model.User;
import com.Blog.Project.Blog.service.PostService;
import com.Blog.Project.Blog.service.UserService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping({"/api/user"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @PostMapping("/register")
    public User register(@RequestBody User u){
        return userService.register(u);
    }

    @PutMapping("/update")
    public User updateuser(@RequestBody User u){
        return userService.updateuser(u);
    }

    @DeleteMapping("/delete")
    public void deleteuser(@PathVariable Integer id){
        userService.deleteuser(id);
    }

    @GetMapping("/get/all")
    public List<User> getAllusers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/{idUser}")
    public Optional<User> getUser(@PathVariable("isUser") Integer id){
        return  userService.getUser(id);
    }

    @PostMapping("{idUser}/addpost")
    public Post addPost(@PathVariable("idUser") Integer id,@RequestBody Post p){
        return postService.addPost(id,p);
    }

    @PutMapping("/updatePost")
    public Post updatepost(@RequestBody Post p){
        return postService.editPost(p);
    }

    @DeleteMapping("/deletePost/{idPost}")
    public void deletepost(@PathVariable("idPost") Integer id){
        postService.delPost(id);
    }

    @PostMapping("/share/{idPost}")
    public Post sharePost(@PathVariable("idPost") Integer postID,@RequestBody Post newPost){
        return postService.sharePost(postID,newPost);
    }
}
