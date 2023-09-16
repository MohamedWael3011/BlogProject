package com.Blog.Project.Blog.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "pid")
    int pid;
    @Column(name = "uid")
    int uid;
    @Column(name = "content")
    String content;
}
