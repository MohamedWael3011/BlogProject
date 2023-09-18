package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.model.User;
import com.Blog.Project.Blog.service.PostService;
import com.Blog.Project.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/user"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @PostMapping("/add-user")
    public ResponseEntity<?> register(@RequestBody User u){
        return  new ResponseEntity<>(userService.register(u),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Integer id){
        return  new ResponseEntity<>(userService.login(id),HttpStatus.OK);
    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateuser(@RequestBody User u,@PathVariable("id") Integer id) throws GeneralException {
        return new ResponseEntity<>(userService.updateUser(u,id),HttpStatus.OK);
    }

    @PostMapping("/update_image/{id}")
    public String updateimage(@PathVariable("id") Integer i,@RequestBody String img) throws GeneralException {
        return userService.update_image(i,img);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteuser(@PathVariable Integer id){
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-user/all")
    public ResponseEntity<?> getAllusers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/get-user/{idUser}")
    public ResponseEntity<?> getUser(@PathVariable("isUser") Integer id) throws GeneralException {
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }

    @PostMapping("{idUser}/add/post")
    public ResponseEntity<?> addPost(@PathVariable("idUser") Integer id,@RequestBody Post p) throws GeneralException {
        return new ResponseEntity<>(postService.addPost(id,p),HttpStatus.OK);
    }

    @PutMapping("/update/post")
    public ResponseEntity<?> updatepost(@RequestBody Post p) throws GeneralException {
        return new ResponseEntity<>(postService.editPost(p),HttpStatus.OK);
    }

    @DeleteMapping("/delete/post/{idPost}")
    public ResponseEntity<?> deletepost(@PathVariable("idPost") Integer id){
        postService.delPost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{userID}/share/{idPost}")
    public ResponseEntity<?> sharePost(@PathVariable("userID") Integer userID,@PathVariable("idPost") Integer postID,@RequestBody Post newPost) throws GeneralException {
        return new ResponseEntity<>(postService.sharePost(userID,postID,newPost),HttpStatus.OK);
    }

    @DeleteMapping("/reject/{pid}/{sid}")
    public ResponseEntity<?> reject(@RequestParam("pid") int pid, @RequestParam("sid") int sid) {
        userService.reject(pid, sid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/accept/{pid}/{sid}")
    public ResponseEntity<?> accept(@PathVariable("pid") int pid, @PathVariable("sid") int sid){
        return new ResponseEntity<>(userService.accept(pid,sid),HttpStatus.OK);
    }


}

