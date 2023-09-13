package com.Blog.Project.Blog.model.CompositeKeys;
import java.io.Serializable;

public class Rid implements Serializable {
    private int Uid;
    private int Pid;
    public Rid(int user_id, int post_id) {
        this.Uid = user_id;
        this.Pid = post_id;
    }
}
