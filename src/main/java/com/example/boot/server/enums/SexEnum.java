package com.example.boot.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiaoWei
 */
@AllArgsConstructor
public enum SexEnum {
    /**
     * 性别：男
     */
    MALE("1", "男"),
    /**
     * 性别：女
     */
    FEMALE("2", "女");

    @Getter
    private final String key;
    @Getter
    private final String value;

    public static String getValueByKey(String key) {
        if(null == key) {
            return "";
        }
        for(SexEnum temp: SexEnum.values()) {
            if(temp.getKey().equals(key)){
                return temp.getValue();
            }
        }
        return "";
    }
}
