package com.Blog.Project.Blog.model;

import jakarta.persistence.*;
import lombok.*;

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
    int pid;
    @Column(name = "uid")
    int uid;
    @Column(name = "content")
    String content;
}
