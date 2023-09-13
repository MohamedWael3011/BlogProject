package com.Blog.Project.Blog.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public record ExceptionData(String code, String msg) {}
