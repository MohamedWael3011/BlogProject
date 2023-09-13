package com.Blog.Project.Blog.model;
import com.Blog.Project.Blog.model.serializer.UserSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JsonSerialize(using = UserSerializer.class)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id1"),
            inverseJoinColumns = @JoinColumn(name = "user_id2"))
    private Set<User> friends = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Post> posts;


//    MessageDigest digest;
//
//    {
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }byte[] encodedhash = digest.digest(
//            password.getBytes(StandardCharsets.UTF_8));
}