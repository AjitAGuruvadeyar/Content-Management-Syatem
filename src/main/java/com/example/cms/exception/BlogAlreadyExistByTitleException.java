package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class BlogAlreadyExistByTitleException extends RuntimeException {
private String message;
}
