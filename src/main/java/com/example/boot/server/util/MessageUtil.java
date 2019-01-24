package com.example.boot.server.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * 如果需要实现多语言配置，仅仅需要将i18n目录中messages.properties更改为messages_zh_CN.properties
 * @author liaowei
 */
@Component
public class MessageUtil {
    private static MessageUtil messageUtil;
    private MessageSource messageSource;

    public MessageUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {
        messageUtil = this;
        messageUtil.messageSource = this.messageSource;
    }

    public static String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageUtil.messageSource.getMessage(code, null, code, locale);
    }

    public static String getMessage(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageUtil.messageSource.getMessage(code, args, code, locale);
    }
}

