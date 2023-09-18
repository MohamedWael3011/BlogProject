package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.exceptions.ErrorCode;
import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.User;
import com.Blog.Project.Blog.repository.CommentRepository;
import com.Blog.Project.Blog.repository.ReactRepository;
import com.Blog.Project.Blog.repository.UserRepository;
import com.Blog.Project.Blog.service.ReactService;
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
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReactRepository reactRepository;
    @Autowired
    private ReactService reactService;

    @Override
    public Post addPost(int userID,Post post) throws GeneralException {
        post.setCreate_time(Date.from(Instant.now()));
        User user = userRepository.findById(userID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });
        post.setUser(user);
        post.setUserFirstName(post.getUser().getName());
        post.setUserImage(post.getUser().getName());
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

        List<Post> posts= postRepository.findAll();
        posts.forEach(p ->{
            p.setNumberOfReact(reactRepository.countByRidPid(p.getPost_id()));
            p.setNumberOfComments(commentRepository.findByPid(p.getPost_id()).size());
            p.setIsReact(reactService.getReactStatus(p.getPost_id(),p.getUser().getId()));
            p.setUserFirstName(p.getUser().getName());
            p.setUserImage(p.getUser().getPic());

        });

        return posts;
    }

    @Override
    public Page<Post> getPostsWithPage(int offset,int pageSize) {
        Page<Post> posts= postRepository.findAll(PageRequest.of(offset,pageSize));
        posts.forEach(p ->{
            p.setNumberOfReact(reactRepository.countByRidPid(p.getPost_id()));
            p.setNumberOfComments(commentRepository.findByPid(p.getPost_id()).size());
            p.setIsReact(reactService.getReactStatus(p.getPost_id(),p.getUser().getId()));
            p.setUserFirstName(p.getUser().getName());
            p.setUserImage(p.getUser().getPic());
        });
        return posts;

    }


    @Override
    public Post getPost(int postID) throws GeneralException {
        Post post =  postRepository.findById(postID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Post with this ID");
        });
        post.setNumberOfReact(reactRepository.countByRidPid(post.getPost_id()));
        post.setNumberOfComments(commentRepository.findByPid(post.getPost_id()).size());
        post.setIsReact(reactService.getReactStatus(post.getPost_id(),post.getUser().getId()));
        post.setUserFirstName(post.getUser().getName());
        post.setUserImage(post.getUser().getPic());
        return post;
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
        newPost.setUserFirstName(newPost.getUser().getName());
        newPost.setUserImage(newPost.getUser().getPic());

//        newPost.setSharedPostID(newPost.getSharedPost().getPost_id());
        postRepository.save(newPost);
        return newPost;
    }
}
