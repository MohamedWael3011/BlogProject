package com.Blog.Project.Blog.model;


import com.Blog.Project.Blog.model.Etc.ReactType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    int id;
    @Column(name="title")
    String title;
    @Column(name="content")
    String content;
    @Column(name= "create_time")
//    @JsonFormat(pattern = "dd-MMM-yyyy")
    Date create_at;
    @Column(name= "image_src")
    String image;
    @Column(name= "privacy_status")
    String privacy_status;

    @OneToOne
    @JoinColumn(name = "shared_id")
    Post shared_post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

//    @Transient
//    String userFirstName;
//    @Transient
//    String userLastName;
//    @Transient
//    String userImage;
    @Transient
    Integer numberOfReacts;
    @Transient
    Integer numberOfComments;
    @Transient
    ReactType isReact;
//    @Transient
//    int sharedPostID;
}
