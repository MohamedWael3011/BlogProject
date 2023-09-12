package com.Blog.Project.Blog.service;


import com.Blog.Project.Blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register (User u);

    void deleteuser(Integer id);

    User updateuser(User u);

    List<User> getAllUsers();

    Optional<User> getUser(Integer id);
}
