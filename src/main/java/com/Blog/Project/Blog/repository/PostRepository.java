package com.Blog.Project.Blog.repository;

import com.Blog.Project.Blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Integer> {

 // m7tag akt el cutom query 34an a handle lma ams7 post y4il el deleted mno

}
