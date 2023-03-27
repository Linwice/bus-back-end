package com.logistics.common.core.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentBody {

    // 商户订单号
    String outTradeNo;
    // 捐款金额
    BigDecimal totalAmount;
    // 标题
    String subject;
    // 组织或公司名
    String storeId = "爱心慈善公益平台";

}
