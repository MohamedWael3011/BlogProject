package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.model.User;
import com.Blog.Project.Blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import com.Blog.Project.Blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Blog.Project.Blog.repository.PostRepository;
import com.Blog.Project.Blog.service.PostService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Post addPost(int userID,Post post) {
        post.setCreate_time(Date.from(Instant.now()));
        User user = userRepository.findById(userID).orElseThrow(() -> {
            return new RuntimeException("There is no User with this ID");
        });
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public void delPost(int postID) {
        postRepository.deleteById(postID);
    }

    @Override
    public Post editPost(Post post) {
        try {
            Post oldPost = postRepository.getReferenceById(post.getPost_id());
            postRepository.save(post);
        } catch (EntityNotFoundException var4) {
            throw new RuntimeException("There is no Post with that ID");
        } catch (Exception var5) {
            throw new RuntimeException("Please enter valid data.");
        }

        return post;
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }


    @Override
    public Post getPost(int postID) {
        return postRepository.findById(postID).orElseThrow(() -> {
            return new RuntimeException("There is no Post with this ID");
        });
    }

    @Override
    public Post sharePost(int postID,Post newPost) {
        Post targetPost= postRepository.findById(postID).orElseThrow(() -> {
            return new RuntimeException("There is no Post with this ID");
        });

        newPost.setCreate_time(Date.from(Instant.now()));

        System.out.println(targetPost.getSharedPost());
        if((Objects.isNull(targetPost.getSharedPost()))){
            newPost.setSharedPost(targetPost);
        }
        else{
            newPost.setSharedPost(targetPost.getSharedPost());
        }
        postRepository.save(newPost);
        return newPost;
    }
}
