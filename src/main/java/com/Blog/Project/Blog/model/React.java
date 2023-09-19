package com.Blog.Project.Blog.model;

import com.Blog.Project.Blog.model.Etc.ReactType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reacts")
//@IdClass(Rid.class)
@IdClass(React.CompositeKey.class)
public class React {
    @Id
    private int pid;
    @Id
    private int uid;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private ReactType type=ReactType.NONE;

    public static class CompositeKey implements Serializable {
        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        private int pid;
        private int uid;

        public CompositeKey(final int pid,final int uid) {
            this.pid = pid;
            this.uid = uid;
        }

        public CompositeKey() {
        }
    }
    }
