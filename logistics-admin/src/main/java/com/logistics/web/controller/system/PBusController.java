package com.logistics.web.controller.system;

import com.logistics.common.annotation.Log;
import com.logistics.common.core.controller.BaseController;
import com.logistics.common.core.domain.AjaxResult;
import com.logistics.common.core.page.TableDataInfo;
import com.logistics.common.enums.BusinessType;
import com.logistics.system.domain.PBus;
import com.logistics.system.service.IPBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公交车信息Controller
 * 
 * @author logistics
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/system/bus")
public class PBusController extends BaseController
{
    @Autowired
    private IPBusService pBusService;

    /**
     * 查询公交车信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:bus:list')")
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
    @PreAuthorize("@ss.hasPermi('system:bus:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pBusService.selectPBusById(id));
    }

    /**
     * 新增公交车信息
     */
    @PreAuthorize("@ss.hasPermi('system:bus:add')")
    @Log(title = "公交车信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PBus pBus)
    {
        return toAjax(pBusService.insertPBus(pBus));
    }

    /**
     * 修改公交车信息
     */
    @PreAuthorize("@ss.hasPermi('system:bus:edit')")
    @Log(title = "公交车信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PBus pBus)
    {
        return toAjax(pBusService.updatePBus(pBus));
    }

    /**
     * 删除公交车信息
     */
    @PreAuthorize("@ss.hasPermi('system:bus:remove')")
    @Log(title = "公交车信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pBusService.deletePBusByIds(ids));
    }
}
