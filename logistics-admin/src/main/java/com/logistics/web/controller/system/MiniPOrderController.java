package com.logistics.web.controller.system;

import com.logistics.common.annotation.Log;
import com.logistics.common.core.controller.BaseController;
import com.logistics.common.core.domain.AjaxResult;
import com.logistics.common.core.page.TableDataInfo;
import com.logistics.common.enums.BusinessType;
import com.logistics.system.domain.POrder;
import com.logistics.system.service.IPOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订票记录信息Controller
 * 
 * @author logistics
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/system/orderBus/mini")
public class MiniPOrderController extends BaseController
{
    @Autowired
    private IPOrderService pOrderService;

    /**
     * 查询订票记录信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(POrder pOrder)
    {
        startPage();
        List<POrder> list = pOrderService.selectPOrderList(pOrder);
        return getDataTable(list);
    }

    /**
     * 获取订票记录信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pOrderService.selectPOrderById(id));
    }

    /**
     * 新增订票记录信息
     */
    @Log(title = "订票记录信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody POrder pOrder)
    {
        return toAjax(pOrderService.insertPOrder(pOrder));
    }

    /**
     * 修改订票记录信息
     */
    @Log(title = "订票记录信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody POrder pOrder)
    {
        return toAjax(pOrderService.updatePOrder(pOrder));
    }

    /**
     * 删除订票记录信息
     */
    @Log(title = "订票记录信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pOrderService.deletePOrderByIds(ids));
    }
}
