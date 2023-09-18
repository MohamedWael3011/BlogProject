package com.Blog.Project.Blog.service;


import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register (User u);
    User login(Integer id);
    String update_image(Integer id, String img) throws GeneralException;
    void deleteUser(Integer id);

    User updateUser(User u,int id) throws GeneralException;

    List<User> getAllUsers();

    User getUser(Integer id) throws GeneralException;

    User accept (int p_id, int s_id);

    void reject(int id1, int id2);
}
