package com.example.boot.server.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Slf4j
@Component
public class HttpAspect {
    @Pointcut("execution(public * com.example.boot.server.controller.*.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("url", request.getRequestURL());
        requestMap.put("method", request.getMethod());
        requestMap.put("ip", request.getRemoteAddr());
        requestMap.put("call", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        requestMap.put("args", joinPoint.getArgs());
        log.info("request:{}", JSON.toJSONString(requestMap));
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("response:{}", JSON.toJSONString(object));
    }

}
