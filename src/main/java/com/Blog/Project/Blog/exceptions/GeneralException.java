package com.Blog.Project.Blog.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralException extends Exception {
    private final ExceptionData exceptionData;

    public GeneralException(ErrorCode code, String msg) {
        exceptionData = new ExceptionData(code.getCode(), msg);
    }
}
