package com.Blog.Project.Blog.service;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.Etc.ReactType;
import com.Blog.Project.Blog.model.React;
import com.Blog.Project.Blog.model.ReturnModels.ReactCount;

import java.util.List;

public interface ReactService {
    ReactCount getReactCount(int pid);
    List<React> listReacts(int pid);
    React addReact(int uid,React react);
    void delReact(int uid,int pid);
    React editReact(React react,int pid,int uid) throws GeneralException;
//    Page<React> getReactsWithPage(int pid,int offset, int pageSize);
    ReactType getReactStatus(int pid,int uid);
}
