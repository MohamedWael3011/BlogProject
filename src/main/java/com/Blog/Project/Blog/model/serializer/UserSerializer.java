package com.Blog.Project.Blog.model.serializer;

import com.Blog.Project.Blog.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserSerializer extends StdSerializer<Set<User>> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<Set<User>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<User> friends = new ArrayList<>();
        users.forEach((u) -> {
            u.setFriends(null);
//            System.out.friends.add(u);
        });

        jsonGenerator.writeObject(users);
    }
}
