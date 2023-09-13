package com.Blog.Project.Blog.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_DIAL_NUMBER("10"),
    REQUIRED_DIAL_NUMBER("2");

    private final String code;
    private ErrorCode(String code) {
        this.code = code;
    }
}
