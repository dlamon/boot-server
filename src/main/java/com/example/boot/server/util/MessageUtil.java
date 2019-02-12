package com.example.boot.server.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 如果需要实现多语言配置，仅仅需要将i18n目录中messages.properties更改为messages_zh_CN.properties
 * @author LiaoWei
 */
@Component
public class MessageUtil {
    private static MessageSource messageSource;

    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    public static String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return MessageUtil.messageSource.getMessage(code, null, code, locale);
    }

    public static String getMessage(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return MessageUtil.messageSource.getMessage(code, args, code, locale);
    }
}

