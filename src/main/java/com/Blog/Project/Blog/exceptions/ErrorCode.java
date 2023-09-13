package com.Blog.Project.Blog.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DO_NOT_EXIST("6");
    private final String code;
    private ErrorCode(String code) {
        this.code = code;
    }
}
