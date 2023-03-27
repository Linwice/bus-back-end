package com.logistics.system.domain;

import java.math.BigDecimal;

import com.logistics.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公交车信息对象 p_bus
 * 
 * @author pipilin
 * @date 2023-03-25
 */
public class PBus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 公交车编号 */
    private String busNo;

    /** 线路信息 */
    private String line;

    /** 站点信息 */
    private String site;

    /** 线路详情信息 */
    private String lineDetail;

    /** 价格 */
    private BigDecimal price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBusNo(String busNo) 
    {
        this.busNo = busNo;
    }

    public String getBusNo() 
    {
        return busNo;
    }
    public void setLine(String line) 
    {
        this.line = line;
    }

    public String getLine() 
    {
        return line;
    }
    public void setSite(String site) 
    {
        this.site = site;
    }

    public String getSite() 
    {
        return site;
    }
    public void setLineDetail(String lineDetail) 
    {
        this.lineDetail = lineDetail;
    }

    public String getLineDetail() 
    {
        return lineDetail;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("busNo", getBusNo())
            .append("line", getLine())
            .append("site", getSite())
            .append("lineDetail", getLineDetail())
            .append("createTime", getCreateTime())
            .append("price", getPrice())
            .toString();
    }
}
