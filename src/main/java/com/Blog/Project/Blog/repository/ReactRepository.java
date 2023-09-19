package com.Blog.Project.Blog.repository;

import com.Blog.Project.Blog.model.Etc.ReactType;
import com.Blog.Project.Blog.model.React;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactRepository extends JpaRepository<React, React.CompositeKey> {
    int countByPidAndType(Integer pid, ReactType type);
    int countByPid(Integer pid);
    List<React> findByPid(Integer pid);


    React getReferenceByUidAndPid(Integer uid,Integer pid);
    React findByUidAndPid(Integer uid, Integer pid);

    void deleteByUidAndPid(Integer uid,Integer pid);

}
