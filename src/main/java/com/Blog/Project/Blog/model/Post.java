package com.Blog.Project.Blog.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    int post_id;
    @Column(name="title")
    String title;
    @Column(name="content")
    String content;
    @Column(name= "create_time")
//    @JsonFormat(pattern = "dd-MMM-yyyy")
    Date create_time;
    @Column(name= "image_src")
    String image_src;
    @Column(name= "privacy_status")
    String privacy_status;

    @OneToOne
    @JoinColumn(name = "shared_id")
    Post sharedPost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
