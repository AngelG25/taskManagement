package com.portfolio.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RepeatedTaskException extends RuntimeException{

    public RepeatedTaskException() {
        super();
    }

    public RepeatedTaskException(String message) {
        super(message);
    }
}
