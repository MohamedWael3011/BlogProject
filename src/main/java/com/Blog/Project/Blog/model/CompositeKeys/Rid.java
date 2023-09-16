package com.Blog.Project.Blog.model.CompositeKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Rid implements Serializable {
    static final long serialVersionUID = 1L;

    private int uid;
    private int pid;
}
