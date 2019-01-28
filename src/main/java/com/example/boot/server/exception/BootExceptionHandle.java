package com.example.boot.server.exception;

import com.example.boot.server.pojo.vo.ResultVO;
import com.example.boot.server.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BootExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public <E> ResultVO<E> handle(Exception e) {
        if (e instanceof BootException) {
            BootException bootException = (BootException) e;
            String code = bootException.getCode();
            String message = bootException.getMessage();
            log.error("{}:{}", code, message, e);
            return ResultUtil.error(code, message);
        } else {
            log.error("{}", "E000009", e);
            return ResultUtil.error("E000009");
        }
    }
}
