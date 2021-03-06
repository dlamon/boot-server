package com.example.boot.server.exception;

import com.example.boot.server.pojo.vo.ResultVO;
import com.example.boot.server.util.MessageUtil;
import com.example.boot.server.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author LiaoWei
 */
@RestControllerAdvice
@Slf4j
class ExceptionHandle {

    private <E> ResultVO<E> returnByBindingResult(Exception e) {
        String code = "PRM0001";
        StringBuilder sb = new StringBuilder();
        sb.append(MessageUtil.getMessage(code));
        sb.append("[");

        BindingResult bindingResult = null;
        if (e instanceof BindException) {
            bindingResult = ((BindException)e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
        }
        if (bindingResult == null) {
            log.error("{}:{}", code, e);
            return ResultUtil.error("PRM0001");
        } else {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage());
            }
            sb.append("]");
            String message = sb.toString();

            log.error("{}:{}", code, message, e);
            return ResultUtil.error(code, message);
        }
    }

    @ExceptionHandler(value = BootException.class)
    public <E> ResultVO<E> handleBootException(BootException e) {
        String code = e.getCode();
        String message = e.getMessage();

        log.error("{}:{}", code, message, e);
        return ResultUtil.error(code, message);
    }

    @ExceptionHandler(value = BindException.class)
    public <E> ResultVO<E> handleBindException(BindException e) {
        return this.returnByBindingResult(e);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public <E> ResultVO<E> handleConstraintViolationException(ConstraintViolationException e) {
        String code = "PRM0001";
        String message = e.getMessage();
        int length = message.length();
        message = message.substring(message.lastIndexOf(":")+1, length).trim();
        String newMessage = MessageUtil.getMessage(code) + "[" + message + "]";

        log.error("{}:{}", code, newMessage, e);
        return ResultUtil.error(code, newMessage);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public <E> ResultVO<E> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return this.returnByBindingResult(e);
    }

    @ExceptionHandler(value = Exception.class)
    public <E> ResultVO<E> handleBootException(Exception e) {
        String code = "E000009";
        String message = MessageUtil.getMessage("E000009");

        log.error("{}:{}", code, message, e);
        return ResultUtil.error("E000009", message);
    }
}
