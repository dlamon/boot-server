package com.example.boot.server.util;

public class Result {
    private static Result result;
    private MessageUtil messageUtil;

    private String code;
    private String message;
    private Object data;


    public Result(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    public Result( String code, String message, Object data ) {
        this.code = code;
        this.message = message;
        this.data = data;
    }



    public static Result success(Object data) {
        return new Result("OK", "成功", data);
    }

}
