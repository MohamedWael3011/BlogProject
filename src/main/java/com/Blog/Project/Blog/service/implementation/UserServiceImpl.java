package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.Helpers.HelperFunctions;
import com.Blog.Project.Blog.exceptions.ErrorCode;
import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Blog.Project.Blog.service.UserService;
import com.Blog.Project.Blog.repository.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;




    @Override
    public User register(User u) {
        return userRepository.save(u);
    }

    @Override
    public User login(User u) throws GeneralException {
        User valid = userRepository.findByEmailAndPassword(u.getEmail(),u.getPassword()).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });
        return userRepository.getReferenceById(valid.getId());
    }

    @Override
    public String update_image(Integer id,String img) throws GeneralException {
        User user = getUser(id);
//        User user = userRepository.findById(id).get();
        user.setPic(HelperFunctions.setBase64(id,img,"users"));
        userRepository.save(user);

        return img;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User u,int id) throws GeneralException {
        User user = getUser(id);
        u.setId(id);
        return userRepository.saveAndFlush(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public User getUser(Integer id) throws GeneralException {
        User user =  userRepository.findById(id).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"There is no User with this ID");
        });
        user.setPic(HelperFunctions.getBase64(id,"users"));
        return  user;
    }

    @Override
    public User accept(int id1,int id2) {
        User u1 = userRepository.findById(id1).get();
        User u2 = userRepository.findById(id2).get();
        u1.getFriends().add(u2);
        u2.getFriends().add(u1);
        return userRepository.save(u1);
    }

    @Override
    public void reject(int id1, int id2) {
        User u1 = userRepository.findById(id1).get();
        User u2 = userRepository.findById(id2).get();
        u1.getFriends().remove(u2);
        u2.getFriends().remove(u1);
    }

    @Override
    public Set<User> getFriends(int uid) throws GeneralException {
        User u = getUser(uid);
        return u.getFriends();
    }

    @Override
    public boolean checkFriend(int uid, int fid) throws GeneralException {
        User a = getUser(uid);
        User b = getUser(fid);
        return a.getFriends().contains(b);
    }
}
