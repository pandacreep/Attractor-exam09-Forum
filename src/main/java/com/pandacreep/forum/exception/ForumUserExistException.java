package com.pandacreep.forum.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForumUserExistException extends ForumException {
    private String message;

    public ForumUserExistException() {
        super();
    }

    public ForumUserExistException(String message) {
        super(message);
        this.message = message;
    }
}
