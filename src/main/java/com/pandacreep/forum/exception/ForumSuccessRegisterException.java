package com.pandacreep.forum.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForumSuccessRegisterException extends ForumException {
    public String message;

    public ForumSuccessRegisterException() {
        super();
    }

    public ForumSuccessRegisterException(String message) {
        super(message);
        this.message = message;
    }
}
