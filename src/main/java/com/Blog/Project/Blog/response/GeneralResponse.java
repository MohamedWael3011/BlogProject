package com.Blog.Project.Blog.response;

import lombok.Data;

@Data
public class GeneralResponse<T> {
    boolean success;
    T data;
    String message;
    int pageNumber;
    int totPages;
    int itemsPerPage;
}