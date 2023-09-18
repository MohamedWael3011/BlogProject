package com.Blog.Project.Blog.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Blog.Project.Blog.model.User;
import java.util.Optional;

public interface UserRepository extends  JpaRepository<User,Integer>{
    Optional<User> findByEmailAndPassword(String username, String password);

}