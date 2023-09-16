package com.Blog.Project.Blog.service;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.CompositeKeys.Rid;
import com.Blog.Project.Blog.model.Etc.ReactType;
import com.Blog.Project.Blog.model.React;
import com.Blog.Project.Blog.model.ReturnModels.ReactCount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReactService {
    ReactCount getReactCount(int pid);
    List<React> listReacts(int pid);
    React addReact(React react);
    void delReact(Rid rid);
    React editReact(React react) throws GeneralException;
    Page<React> getReactsWithPage(int pid,int offset, int pageSize);
    ReactType getReactStatus(int pid,int uid);
}
