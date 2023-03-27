package com.logistics.common.enums;

/**
 * 支付状态
 *
 * @author 2891517520@qq.com
 */
public enum OrderStatusEnum {

    //支付状态
    PaySTATUS_0("0","未支付"),
    PaySTATUS_1("1","已支付");

    private final String code;
    private final String info;

    OrderStatusEnum(String code, String info) {
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
