package com.example.boot.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AcctStatusEnum {
    /**
     * 性别：男
     */
    NORMAL(Short.valueOf("0"), "正常"),
    /**
     * 性别：女
     */
    ABNORMAL(Short.valueOf("1"), "异常");

    @Getter
    private final Short key;
    @Getter
    private final String value;
}
