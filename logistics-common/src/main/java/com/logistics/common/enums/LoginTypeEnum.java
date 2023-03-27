package com.logistics.common.enums;

public enum LoginTypeEnum {
    MA("MA", "小程序"), SYSTEM("SYSTEM", "后台管理系统"), OTHER("OTHER", "其他");
    private final String code;
    private final String info;

    LoginTypeEnum(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
