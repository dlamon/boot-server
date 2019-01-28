package com.example.boot.server.exception;

public class BootException extends Exception {
    private static final long serialVersionUID = -3098531013726988040L;

    private String code;
    private String message;

    public BootException(Throwable cause) {
        super(cause);
    }

    public BootException(String code) {
        super(code);
        this.code = code;
    }

    public BootException(String code, String message) {
        super(code + "-" + message);
        this.code = code;
        this.message = message;
    }

    public BootException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
    }

    public BootException(String code, String message, Throwable cause) {
        super(code + "-" + message, cause);
        this.code = code;
        this.message = message;
    }

    String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
