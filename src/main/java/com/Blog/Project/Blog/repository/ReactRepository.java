package com.Blog.Project.Blog.repository;

import com.Blog.Project.Blog.model.CompositeKeys.Rid;
import com.Blog.Project.Blog.model.Etc.ReactType;
import com.Blog.Project.Blog.model.React;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactRepository extends JpaRepository<React, Rid> {
    int countByRidPidAndType(Integer pid, ReactType type);
    List<React> findByRidPid(Integer pid);
    Page<React> findAllByRidPid(Integer pid,Pageable pageable);
}
