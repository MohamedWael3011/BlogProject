package com.Blog.Project.Blog.service;


import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User register (User u);
    User login(User u) throws GeneralException;
    String update_image(Integer id, String img) throws GeneralException;
    void deleteUser(Integer id);

    User updateUser(User u,int id) throws GeneralException;

    List<User> getAllUsers();
    User getUser(Integer id) throws GeneralException;

    User accept (int p_id, int s_id);

    void reject(int id1, int id2);
    Set<User> getFriends(int uid) throws GeneralException;
    boolean checkFriend(int uid, int fid) throws GeneralException;
}
