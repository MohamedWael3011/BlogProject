package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.exceptions.ErrorCode;
import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.User;
import com.Blog.Project.Blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import com.Blog.Project.Blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Post addPost(int userID,Post post) throws GeneralException {
        post.setCreate_time(Date.from(Instant.now()));
        User user = userRepository.findById(userID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });
        post.setUser(user);
        post.setUserFirstName(post.getUser().getFirstName());
        post.setUserLastName(post.getUser().getLastName());
        post.setUserImage(post.getUser().getImage());
        return postRepository.save(post);
    }

    @Override
    public void delPost(int postID) {
        // Check first if its shared before
        List<Post> childPosts = postRepository.findAllSharedPosts(postID);
        if (!childPosts.isEmpty()){
            childPosts.forEach(p ->{
                postRepository.deleteById(p.getPost_id());
            });
        }


        postRepository.deleteById(postID);
    }

    @Override
    public Post editPost(Post post) throws GeneralException {
        try {
            Post oldPost = postRepository.getReferenceById(post.getPost_id());
            postRepository.save(post);
        } catch (EntityNotFoundException var4) {
            throw new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Post with that ID");
        } catch (Exception var5) {
            throw new GeneralException(ErrorCode.INVALID_DATA,"Invalid Data");
        }

        return post;
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> getPostsWithPage(int offset,int pageSize) {
        Page<Post> posts= postRepository.findAll(PageRequest.of(offset,pageSize));
        return posts;

    }


    @Override
    public Post getPost(int postID) throws GeneralException {
        return postRepository.findById(postID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Post with this ID");
        });
    }

    @Override
    public Post sharePost(int userID,int postID,Post newPost) throws GeneralException {
        Post targetPost= postRepository.findById(postID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST, "There is no Post with this ID");
        });
        User user = userRepository.findById(userID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });

        newPost.setCreate_time(Date.from(Instant.now()));
        if((Objects.isNull(targetPost.getSharedPost()))){
            newPost.setSharedPost(targetPost);
        }
        else{
            newPost.setSharedPost(targetPost.getSharedPost());
        }
        newPost.setUser(user);
        newPost.setUserFirstName(newPost.getUser().getFirstName());
        newPost.setUserLastName(newPost.getUser().getLastName());
        newPost.setUserImage(newPost.getUser().getImage());
//        newPost.setSharedPostID(newPost.getSharedPost().getPost_id());
        postRepository.save(newPost);
        return newPost;
    }
}
