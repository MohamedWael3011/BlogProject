package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.Helpers.HelperFunctions;
import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.model.User;
import com.Blog.Project.Blog.response.GeneralResponse;
import com.Blog.Project.Blog.service.PostService;
import com.Blog.Project.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping({"/api"})
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
    public ResponseEntity<?> login(@RequestBody User user) throws GeneralException {
        return  new ResponseEntity<>(userService.login(user),HttpStatus.OK);
    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateuser(@RequestBody User u,@PathVariable("id") Integer id) throws GeneralException {
        return new ResponseEntity<>(userService.updateUser(u,id),HttpStatus.OK);
    }

    @PutMapping("/update_image/{id}")
    public Object updateimage(@PathVariable("id") Integer i,@RequestBody HashMap<String, String> img) throws GeneralException {
        HashMap<String,String> image = new HashMap<>();
        image.put("img", userService.update_image(i,img.get("img")));
        return image;
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
    public ResponseEntity<?> getUser(@PathVariable("idUser") Integer id) throws GeneralException {
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }

    @PostMapping("add-post/{userId}")
    public GeneralResponse<?> addPost(@PathVariable("userId") Integer id, @RequestBody Post p) throws GeneralException {
        GeneralResponse<Post> res = new GeneralResponse<>();
        Post post = postService.addPost(id,p);
        post.setImage(HelperFunctions.getBase64(post.getId(), "posts"));
        post.getUser().setPic(HelperFunctions.getBase64(post.getUser().getId(),"users"));
        res.setData(post);
        res.setSuccess(true);
        return res;
    }

    @PutMapping("/update-post/{postID}/{userID}")
    public GeneralResponse<?> updatepost(@PathVariable("postID") Integer postID,@PathVariable("userID") Integer userID, @RequestBody Post p) throws GeneralException {
        GeneralResponse<Post> res = new GeneralResponse<>();
        res.setData(postService.editPost(postID,p,userID));
        return res;
    }

    @DeleteMapping("/delete-post/{postId}/{userID}")
    public GeneralResponse<?> deletepost(@PathVariable("postId") Integer id,@PathVariable("userID") Integer userID) throws GeneralException {
        postService.delPost(id,userID);
        GeneralResponse<Post> res = new GeneralResponse<>();
        res.setSuccess(true);
        res.setMessage("Post has been deleted");
        return res;
    }

    @PostMapping("/share-post/{idPost}/{userID}")
    public GeneralResponse<?> sharePost(@PathVariable("userID") Integer userID,@PathVariable("idPost") Integer postID,@RequestBody Post newPost) throws GeneralException {
        GeneralResponse<Post> res = new GeneralResponse<>();
        Post post = postService.sharePost(userID,postID,newPost);
        post.setImage(HelperFunctions.getBase64(post.getId(), "posts"));
        post.getUser().setPic(HelperFunctions.getBase64(post.getUser().getId(),"users"));
        res.setData(post);
        res.setSuccess(true);
        return res;
    }

    @DeleteMapping("/delete-friend")
    public ResponseEntity<?> reject(@RequestParam("userID") int pid, @RequestParam("friendID") int sid) {
        userService.reject(pid, sid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-friend")
    public ResponseEntity<?> accept(@RequestParam("userID") int pid, @RequestParam("friendID") int sid){
        return new ResponseEntity<>(userService.accept(pid,sid),HttpStatus.OK);
    }


    @GetMapping("/get-friends/{idUser}")
    public ResponseEntity<?> getFriends(@PathVariable("idUser") Integer id) throws GeneralException {
        return new ResponseEntity<>(userService.getFriends(id),HttpStatus.OK);
    }

    @PutMapping("/check-friend")
    public ResponseEntity<?> checkFriend(@RequestParam("userId") int pid, @RequestParam("friendId") int sid) throws GeneralException {
        return new ResponseEntity<>(userService.checkFriend(pid,sid),HttpStatus.OK);
    }
}

