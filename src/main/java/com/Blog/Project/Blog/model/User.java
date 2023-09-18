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
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String pic;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone")
    private String phone;

    @Column(name = "facebook_username")
    private String facebookUsername;



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


}