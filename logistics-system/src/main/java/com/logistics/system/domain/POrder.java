package com.logistics.system.domain;

import java.math.BigDecimal;

import com.logistics.common.core.domain.BaseEntity;
import com.logistics.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订票记录信息对象 p_order
 * 
 * @author logistics
 * @date 2023-03-25
 */
public class POrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 公交车ID */
    private Long busId;

    /** 订票人ID */
    private Long userId;

    /** 价格 */
    private BigDecimal orderPrice;

    private String orderNo;

    private String status;

    private PBus pBus;

    private SysUser sysUser;

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PBus getpBus() {
        return pBus;
    }

    public void setpBus(PBus pBus) {
        this.pBus = pBus;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBusId(Long busId) 
    {
        this.busId = busId;
    }

    public Long getBusId() 
    {
        return busId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOrderPrice(BigDecimal orderPrice) 
    {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrice() 
    {
        return orderPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("busId", getBusId())
            .append("userId", getUserId())
            .append("orderPrice", getOrderPrice())
            .append("createTime", getCreateTime())
            .toString();
    }
}
