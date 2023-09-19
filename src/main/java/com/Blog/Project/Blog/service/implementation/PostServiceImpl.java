package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.Helpers.HelperFunctions;
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
        post.setCreate_at(Date.from(Instant.now()));
        User user = userRepository.findById(userID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });
        post.setUser(user);
        String img = post.getImage();
        post.setImage("");
        post = postRepository.save(post);

        post.setImage(HelperFunctions.setBase64(post.getId(), img,"posts"));

        postRepository.save(post);
//        post.setUserFirstName(post.getUser().getName());
//        post.setUserImage(post.getUser().getName());
        post.setImage(img);
        return post;
    }

    @Override
    public void delPost(int postID,int userID) throws GeneralException {
        Post post = postRepository.findById(postID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });
        if (post.getUser().getId() != userID){
            throw new GeneralException(ErrorCode.DO_NOT_EXIST,"You cannot delete this post");
        }
        // Check first if its shared before
        List<Post> childPosts = postRepository.findAllSharedPosts(postID);
        if (!childPosts.isEmpty()){
            childPosts.forEach(p ->{
                postRepository.deleteById(p.getId());
            });
        }


        postRepository.deleteById(postID);
    }

    @Override
    public Post editPost(int postId,Post post,int userID) throws GeneralException {
        try {
            Post oldPost = postRepository.getReferenceById(post.getId());
            if(oldPost.getUser().getId() != userID) {
                throw new GeneralException(ErrorCode.DO_NOT_EXIST, "You cannot edit a post that is not done by you");
            }
            postRepository.save(post);
        } catch (EntityNotFoundException var4) {
            throw new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Post with that ID");
        } catch (Exception var5) {
            throw new GeneralException(ErrorCode.INVALID_DATA,"Invalid Data");
        }

        return post;
    }

    @Override
    public List<Post> getPosts(int loggedUser) {

        List<Post> posts= postRepository.findAll();
        posts.forEach(p ->{
            p.setNumberOfReacts(reactRepository.countByPid(p.getId()));
            p.setNumberOfComments(commentRepository.findByPostId(p.getId()).size());
            p.setIsReact(reactService.getReactStatus(p.getId(),p.getUser().getId()));
            p.setImage(HelperFunctions.getBase64(p.getId(), "posts"));
            p.getUser().setPic(HelperFunctions.getBase64(p.getUser().getId(),"users"));
//            p.setUserFirstName(p.getUser().getName());
//            p.setUserImage(p.getUser().getPic());

        });

        return posts;
    }

    @Override
    public Page<Post> getPostsWithPage(int offset,int pageSize) {
        Page<Post> posts= postRepository.findAll(PageRequest.of(offset,pageSize));
        posts.forEach(p ->{
            p.setNumberOfReacts(reactRepository.countByPid(p.getId()));
            p.setNumberOfComments(commentRepository.findByPostId(p.getId()).size());
            p.setIsReact(reactService.getReactStatus(p.getId(),p.getUser().getId()));
//            p.setUserFirstName(p.getUser().getName());
//            p.setUserImage(p.getUser().getPic());
        });
        return posts;

    }


    @Override
    public Post getPost(int posterID,int loggedUser) throws GeneralException {
        Post post =  postRepository.findById(posterID).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no Post with this ID");
        });
        post.setNumberOfReacts(reactRepository.countByPid(post.getId()));
        post.setNumberOfComments(commentRepository.findByPostId(post.getId()).size());
        post.setIsReact(reactService.getReactStatus(post.getId(),loggedUser));
        post.setImage(HelperFunctions.getBase64(post.getId(), "posts"));

//        post.setUserFirstName(post.getUser().getName());
//        post.setUserImage(post.getUser().getPic());
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
        String image64 ="";
        newPost.setCreate_at(Date.from(Instant.now()));
        if((Objects.isNull(targetPost.getShared_post()))){
            newPost.setShared_post(targetPost);
            image64 = HelperFunctions.getBase64(targetPost.getId(),"posts" );
        }
        else{
            newPost.setShared_post(targetPost.getShared_post());
            image64 = HelperFunctions.getBase64(targetPost.getShared_post().getId(),"posts" );
        }
        newPost.setUser(user);


//        newPost.setSharedPostID(newPost.getSharedPost().getPost_id());
        newPost = postRepository.save(newPost);
        newPost.getShared_post().setImage(image64);
        newPost.getUser().setPic(HelperFunctions.getBase64(newPost.getUser().getId(),"users"));
        newPost.getShared_post().getUser().setPic(HelperFunctions.getBase64(newPost.getShared_post().getUser().getId(),"users"));


        return newPost;
    }

    @Override
    public List<Post> getPostByUser(int posterID,int loggedUser) {

        List<Post> posts= postRepository.findByUserID(posterID);
        posts.forEach(p ->{
            p.setNumberOfReacts(reactRepository.countByPid(p.getId()));
            p.setNumberOfComments(commentRepository.findByPostId(p.getId()).size());
            p.setIsReact(reactService.getReactStatus(p.getId(),loggedUser));
            p.setImage(HelperFunctions.getBase64(p.getId(), "posts"));
            p.getUser().setPic(HelperFunctions.getBase64(p.getUser().getId(),"users"));


//            p.setUserFirstName(p.getUser().getName());
//            p.setUserImage(p.getUser().getPic());

        });
        return posts;
    }
}
