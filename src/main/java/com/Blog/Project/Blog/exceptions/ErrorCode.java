package com.Blog.Project.Blog.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_DATA("5"),
    DO_NOT_EXIST("6"),
    ACCESS_DENIED("7");
    private final String code;
    private ErrorCode(String code) {
        this.code = code;
    }
}
