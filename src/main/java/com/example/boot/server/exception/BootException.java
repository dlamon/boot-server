package com.example.boot.server.exception;

import com.example.boot.server.util.MessageUtil;
import lombok.Getter;

/**
 * @author LiaoWei
 */
public class BootException extends RuntimeException {
    @Getter
    private String code;
    @Getter
    private String message;

    private String getMessage(String code) {
        return MessageUtil.getMessage(code);
    }

    private String getMessage(String code, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(MessageUtil.getMessage(code));
        if(message != null && !"".equals(message)) {
            sb.append("[").append(message).append("]");
        }
        return sb.toString();
    }

    public BootException(Throwable cause) {
        super(cause);
    }

    public BootException(String code) {
        super(code);
        this.code = code;
        this.message = this.getMessage(code);
    }

    public BootException(String code, String message) {
        super(code);
        this.code = code;
        this.message = this.getMessage(code, message);
    }

    public BootException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.message = this.getMessage(code);
    }

    public BootException(String code, String message, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.message = this.getMessage(code, message);
    }
}
