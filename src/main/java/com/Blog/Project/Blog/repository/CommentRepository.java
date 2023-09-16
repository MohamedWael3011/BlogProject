package com.Blog.Project.Blog.repository;

import com.Blog.Project.Blog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByPid(Integer post_id);
    Page<Comment> findAllByPid(Integer post_id, Pageable pageable);
}
