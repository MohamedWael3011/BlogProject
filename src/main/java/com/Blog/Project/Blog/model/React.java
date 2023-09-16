package com.Blog.Project.Blog.model;

import com.Blog.Project.Blog.model.CompositeKeys.Rid;
import com.Blog.Project.Blog.model.Etc.ReactType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reacts")
//@IdClass(Rid.class)
public class React {
    @EmbeddedId
    private Rid rid;

    @AttributeOverrides({
            @AttributeOverride(name="uid",
                    column = @Column(name="uid")),
            @AttributeOverride(name="pid",
                    column = @Column(name="pid"))
    })

        @Enumerated(EnumType.ORDINAL)
        @Column(name = "type")
        private ReactType type=ReactType.NONE;
}
