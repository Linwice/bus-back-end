package com.logistics.system.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.logistics.common.exception.base.BaseException;
import com.logistics.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logistics.system.mapper.PBusMapper;
import com.logistics.system.domain.PBus;
import com.logistics.system.service.IPBusService;

/**
 * 公交车信息Service业务层处理
 * 
 * @author logistics
 * @date 2023-03-25
 */
@Service
public class PBusServiceImpl implements IPBusService 
{
    @Autowired
    private PBusMapper pBusMapper;

    /**
     * 查询公交车信息
     * 
     * @param id 公交车信息主键
     * @return 公交车信息
     */
    @Override
    public PBus selectPBusById(Long id)
    {
        return pBusMapper.selectPBusById(id);
    }

    /**
     * 查询公交车信息列表
     * 
     * @param pBus 公交车信息
     * @return 公交车信息
     */
    @Override
    public List<PBus> selectPBusList(PBus pBus)
    {
        return pBusMapper.selectPBusList(pBus);
    }

    /**
     * 新增公交车信息
     * 
     * @param pBus 公交车信息
     * @return 结果
     */
    @Override
    public int insertPBus(PBus pBus)
    {
        PBus pBus1 = pBusMapper.selectPBusByBusNo(pBus.getBusNo());
        if(ObjectUtil.isNotNull(pBus1)){
            throw new BaseException("公交车编号已存在!");
        }
        pBus.setCreateTime(DateUtils.getNowDate());
        return pBusMapper.insertPBus(pBus);
    }

    /**
     * 修改公交车信息
     * 
     * @param pBus 公交车信息
     * @return 结果
     */
    @Override
    public int updatePBus(PBus pBus)
    {
        PBus pBus1 = pBusMapper.selectPBusByIdBusNo(pBus.getId(), pBus.getBusNo());
        if(ObjectUtil.isNotNull(pBus1)){
            throw new BaseException("公交车编号已存在!");
        }
        return pBusMapper.updatePBus(pBus);
    }

    /**
     * 批量删除公交车信息
     * 
     * @param ids 需要删除的公交车信息主键
     * @return 结果
     */
    @Override
    public int deletePBusByIds(Long[] ids)
    {
        return pBusMapper.deletePBusByIds(ids);
    }

    /**
     * 删除公交车信息信息
     * 
     * @param id 公交车信息主键
     * @return 结果
     */
    @Override
    public int deletePBusById(Long id)
    {
        return pBusMapper.deletePBusById(id);
    }
}
