package com.logistics.system.mapper;

import java.util.List;
import com.logistics.system.domain.PBus;
import org.apache.ibatis.annotations.Param;

/**
 * 公交车信息Mapper接口
 * 
 * @author pipilin
 * @date 2023-03-25
 */
public interface PBusMapper 
{
    /**
     * 查询公交车信息
     * 
     * @param id 公交车信息主键
     * @return 公交车信息
     */
    public PBus selectPBusById(Long id);

    public PBus selectPBusByBusNo(String busNo);
    public PBus selectPBusByIdBusNo(@Param("id") Long id,@Param("busNo") String busNo);

    /**
     * 查询公交车信息列表
     * 
     * @param pBus 公交车信息
     * @return 公交车信息集合
     */
    public List<PBus> selectPBusList(PBus pBus);

    /**
     * 新增公交车信息
     * 
     * @param pBus 公交车信息
     * @return 结果
     */
    public int insertPBus(PBus pBus);

    /**
     * 修改公交车信息
     * 
     * @param pBus 公交车信息
     * @return 结果
     */
    public int updatePBus(PBus pBus);

    /**
     * 删除公交车信息
     * 
     * @param id 公交车信息主键
     * @return 结果
     */
    public int deletePBusById(Long id);

    /**
     * 批量删除公交车信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePBusByIds(Long[] ids);
}
