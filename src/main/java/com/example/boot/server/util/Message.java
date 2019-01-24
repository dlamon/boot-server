package com.example.boot.server.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {
    private MessageSource messageSource;

    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("locale:"+ locale);
        return messageSource.getMessage(code, null, code, locale);
    }

    public String getMessage(String code, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, code, locale);
    }
}

