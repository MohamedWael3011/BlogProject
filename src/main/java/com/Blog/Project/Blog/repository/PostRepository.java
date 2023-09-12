package com.Blog.Project.Blog.repository;

import com.Blog.Project.Blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
