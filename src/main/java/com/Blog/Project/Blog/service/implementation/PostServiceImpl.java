package com.Blog.Project.Blog.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import com.Blog.Project.Blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Blog.Project.Blog.repository.PostRepository;
import com.Blog.Project.Blog.service.PostService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        post.setCreate_time(Date.from(Instant.now()));
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
}
