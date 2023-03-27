package com.logistics.web.controller.system;

import com.logistics.common.core.controller.BaseController;
import com.logistics.common.core.domain.AjaxResult;
import com.logistics.common.core.domain.entity.SysUser;
import com.logistics.common.core.page.TableDataInfo;
import com.logistics.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息
 * 
 * @author 2891517520@qq.com
 */
@RestController
@RequestMapping("/system/user/mini")
public class MiniUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 删除用户
     */
    @GetMapping("/remove/{userId}")
    public AjaxResult remove(@PathVariable Long userId)
    {
        return toAjax(userService.deleteUserById(userId));
    }
}
