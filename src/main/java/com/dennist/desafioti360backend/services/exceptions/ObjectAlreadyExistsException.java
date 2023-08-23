package com.dennist.desafioti360backend.services.exceptions;

import java.io.Serial;

public class ObjectAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectAlreadyExistsException(String msg) {
        super(msg);
    }

    public ObjectAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
