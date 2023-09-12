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
}
