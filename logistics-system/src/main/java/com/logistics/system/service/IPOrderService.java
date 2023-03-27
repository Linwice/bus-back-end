package com.logistics.system.service;

import java.util.List;
import com.logistics.system.domain.POrder;

/**
 * 订票记录信息Service接口
 * 
 * @author logistics
 * @date 2023-03-25
 */
public interface IPOrderService 
{
    /**
     * 查询订票记录信息
     * 
     * @param id 订票记录信息主键
     * @return 订票记录信息
     */
    public POrder selectPOrderById(Long id);

    /**
     * 查询订票记录信息列表
     * 
     * @param pOrder 订票记录信息
     * @return 订票记录信息集合
     */
    public List<POrder> selectPOrderList(POrder pOrder);

    /**
     * 新增订票记录信息
     * 
     * @param pOrder 订票记录信息
     * @return 结果
     */
    public int insertPOrder(POrder pOrder);

    /**
     * 修改订票记录信息
     * 
     * @param pOrder 订票记录信息
     * @return 结果
     */
    public int updatePOrder(POrder pOrder);

    /**
     * 批量删除订票记录信息
     * 
     * @param ids 需要删除的订票记录信息主键集合
     * @return 结果
     */
    public int deletePOrderByIds(Long[] ids);

    /**
     * 删除订票记录信息信息
     * 
     * @param id 订票记录信息主键
     * @return 结果
     */
    public int deletePOrderById(Long id);
}
