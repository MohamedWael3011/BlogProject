package com.Blog.Project.Blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    int cid;
    @Column(name = "pid")
    int postId;
//    @Column(name = "uid")
//    int userId;
    @Column(name = "content")
    String content;
    @Column(name = "create_time")
    Date commentDate;
    @ManyToOne
    @JoinColumn(name = "uid")
    User user;

}
