package com.Blog.Project.Blog.repository;

import com.Blog.Project.Blog.model.Post;
import com.Blog.Project.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

 // m7tag akt el cutom query 34an a handle lma ams7 post y4il el deleted mno
 @Query(value = "SELECT * FROM post WHERE shared_id = ?", nativeQuery = true)
 List<Post> findAllSharedPosts(int postID);

 @Query(value = "SELECT * FROM post WHERE user_id = ?",nativeQuery = true)
 List<Post> findByUserID(int user_id);
}
