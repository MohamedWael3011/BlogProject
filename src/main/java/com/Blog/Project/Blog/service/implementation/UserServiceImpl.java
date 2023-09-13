package com.Blog.Project.Blog.service.implementation;

import com.Blog.Project.Blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Blog.Project.Blog.service.UserService;
import com.Blog.Project.Blog.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public User register(User u) {
        return userRepository.save(u);
    }



    @Override
    public void deleteuser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateuser(User u) {
        return userRepository.save(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Integer id) {
        Optional<User> userOpt =  userRepository.findById(id);
        if(userOpt.isPresent())
            return Optional.of(userOpt.get());
        throw new RuntimeException("Id does not exist");
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

}
