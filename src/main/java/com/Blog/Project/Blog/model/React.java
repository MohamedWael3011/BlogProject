package com.Blog.Project.Blog.model;

import com.Blog.Project.Blog.model.CompositeKeys.Rid;
import jakarta.persistence.*;

@Entity
@IdClass(Rid.class)
public class React {
    @Id
    @Column(name = "Uid")
    private int Uid;
    @Id
    @Column(name = "Pid")
    private int Pid;
    @Column(name = "reaction_type")
    private String type;
}
