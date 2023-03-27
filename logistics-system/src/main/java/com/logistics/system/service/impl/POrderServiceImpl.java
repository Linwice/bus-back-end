package com.logistics.system.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.logistics.common.core.domain.entity.SysUser;
import com.logistics.common.exception.base.BaseException;
import com.logistics.common.utils.DateUtils;
import com.logistics.system.mapper.PBusMapper;
import com.logistics.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logistics.system.mapper.POrderMapper;
import com.logistics.system.domain.POrder;
import com.logistics.system.service.IPOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订票记录信息Service业务层处理
 * 
 * @author logistics
 * @date 2023-03-25
 */
@Service
public class POrderServiceImpl implements IPOrderService 
{
    @Autowired
    private POrderMapper pOrderMapper;

    @Autowired
    private PBusMapper pBusMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询订票记录信息
     * 
     * @param id 订票记录信息主键
     * @return 订票记录信息
     */
    @Override
    public POrder selectPOrderById(Long id)
    {
        return pOrderMapper.selectPOrderById(id);
    }

    /**
     * 查询订票记录信息列表
     * 
     * @param pOrder 订票记录信息
     * @return 订票记录信息
     */
    @Override
    public List<POrder> selectPOrderList(POrder pOrder)
    {
        return pOrderMapper.selectPOrderList(pOrder);
    }

    /**
     * 新增订票记录信息
     * 
     * @param pOrder 订票记录信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPOrder(POrder pOrder)
    {
        SysUser sysUser = sysUserMapper.selectUserById(pOrder.getUserId());
        if(sysUser.getBalance().compareTo(pOrder.getOrderPrice()) < 0){
            throw new BaseException("余额不足，请充值!");
        }
        SysUser sysUser1 = new SysUser();
        sysUser1.setUserId(sysUser.getUserId());
        sysUser1.setBalance(sysUser.getBalance().subtract(pOrder.getOrderPrice()));
        sysUserMapper.updateUser(sysUser1);
        pOrder.setOrderNo(IdUtil.getSnowflake().nextIdStr());
        pOrder.setCreateTime(DateUtils.getNowDate());
        return pOrderMapper.insertPOrder(pOrder);
    }

    /**
     * 修改订票记录信息
     * 
     * @param pOrder 订票记录信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePOrder(POrder pOrder)
    {
        if("2".equals(pOrder.getStatus())){
            SysUser sysUser = sysUserMapper.selectUserById(pOrder.getUserId());
            POrder pOrder1 = pOrderMapper.selectPOrderById(pOrder.getId());
            SysUser sysUser1 = new SysUser();
            sysUser1.setUserId(sysUser.getUserId());
            sysUser1.setBalance(sysUser.getBalance().add(pOrder1.getOrderPrice()));
            sysUserMapper.updateUser(sysUser1);
        }
        return pOrderMapper.updatePOrder(pOrder);
    }

    /**
     * 批量删除订票记录信息
     * 
     * @param ids 需要删除的订票记录信息主键
     * @return 结果
     */
    @Override
    public int deletePOrderByIds(Long[] ids)
    {
        return pOrderMapper.deletePOrderByIds(ids);
    }

    /**
     * 删除订票记录信息信息
     * 
     * @param id 订票记录信息主键
     * @return 结果
     */
    @Override
    public int deletePOrderById(Long id)
    {
        return pOrderMapper.deletePOrderById(id);
    }
}
