package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.Helpers.Hashing;
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
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.Blog.Project.Blog.Helpers.Hashing.getSHA;
import static com.Blog.Project.Blog.Helpers.Hashing.toHexString;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;




    @Override
    public User register(User u) throws NoSuchAlgorithmException {
        String Password = u.getPassword();
        u.setPassword(Hashing.toHexString(Hashing.getSHA(u.getPassword())));
        u = userRepository.save(u);
        HelperFunctions.setBase64(u.getId(),u.getPic(),"users");
        u.setPassword(Password);
         return u;
    }

    @Override
    public User login(User u) throws GeneralException, NoSuchAlgorithmException {
        String hashedPw = Hashing.toHexString(Hashing.getSHA(u.getPassword()));
        User valid = userRepository.findByEmailAndPassword(u.getEmail(),hashedPw).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"Invalid Email or Password");
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
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new GeneralException(ErrorCode.DO_NOT_EXIST,"Invalid Email or Password");
        });
        u.setId(id);
        u.setPassword(user.getPassword());
        u.setPic(user.getPic());
        userRepository.saveAndFlush(u);
        u.setPic(HelperFunctions.getBase64(u.getId(),"users"));
        return u;
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
        userRepository.saveAndFlush(u2);
        return userRepository.save(u1);
    }

    @Override
    public void reject(int id1, int id2) {
        User u1 = userRepository.findById(id1).get();
        User u2 = userRepository.findById(id2).get();
        u1.getFriends().remove(u2);
        u2.getFriends().remove(u1);
        userRepository.saveAndFlush(u1);
        userRepository.saveAndFlush(u2);
    }

    @Override
    public Set<User> getFriends(int uid) throws GeneralException {
        User u = getUser(uid);
        Set<User> friends = u.getFriends();
        friends.forEach(f ->{
            f.setPic(HelperFunctions.getBase64(f.getId(),"users"));
        });
        return friends;
    }

    @Override
    public boolean checkFriend(int uid, int fid) throws GeneralException {
        User a = getUser(uid);
        User b = getUser(fid);
        return a.getFriends().contains(b);
    }
}
