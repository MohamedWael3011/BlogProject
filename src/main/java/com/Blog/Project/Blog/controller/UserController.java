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
@RequestMapping({"/api/users"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @PostMapping("/register")
    public User register(@RequestBody User u){
        return userService.register(u);
    }

    @PutMapping("/updateUser")
    public User updateuser(@RequestBody User u){
        return userService.updateuser(u);
    }

    @DeleteMapping("/deleteUser")
    public void deleteuser(@PathVariable Integer id){
        userService.deleteuser(id);
    }

    @GetMapping("/allUsers")
    public List<User> getAllusers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{idUser}")
    public Optional<User> getUser(@PathVariable Integer id){
        return  userService.getUser(id);
    }

    @PostMapping("/addpost")
    public Post addPost(@RequestBody Post p){
        return postService.addPost(p);
    }

    @PutMapping("/updatePost")
    public Post updatepost(@RequestBody Post p){
        return postService.editPost(p);
    }

    @DeleteMapping("/deletePost")
    public void deletepost(@PathVariable Integer id){
        postService.delPost(id);
    }

    @GetMapping("/allPosts")
    public List<Post> getAllPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{idPost}")
    public Post getPost(@PathVariable Integer id){
        return postService.getPost(id);
    }
}
