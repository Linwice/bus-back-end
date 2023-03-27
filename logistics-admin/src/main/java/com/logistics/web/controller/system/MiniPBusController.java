package com.logistics.web.controller.system;

import com.logistics.common.core.controller.BaseController;
import com.logistics.common.core.domain.AjaxResult;
import com.logistics.common.core.page.TableDataInfo;
import com.logistics.system.domain.PBus;
import com.logistics.system.service.IPBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公交车信息Controller
 * 
 * @author logistics
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/system/bus/mini")
public class MiniPBusController extends BaseController
{
    @Autowired
    private IPBusService pBusService;

    /**
     * 查询公交车信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PBus pBus)
    {
        startPage();
        List<PBus> list = pBusService.selectPBusList(pBus);
        return getDataTable(list);
    }

    /**
     * 获取公交车信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pBusService.selectPBusById(id));
    }
}
