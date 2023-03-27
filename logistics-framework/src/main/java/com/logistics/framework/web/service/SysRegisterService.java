package com.logistics.framework.web.service;

import cn.hutool.core.util.ObjectUtil;
import com.logistics.framework.manager.AsyncManager;
import com.logistics.framework.manager.factory.AsyncFactory;
import com.logistics.system.domain.SysUserRole;
import com.logistics.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.logistics.common.constant.Constants;
import com.logistics.common.constant.UserConstants;
import com.logistics.common.core.domain.entity.SysUser;
import com.logistics.common.core.domain.model.RegisterBody;
import com.logistics.common.core.redis.RedisCache;
import com.logistics.common.exception.user.CaptchaException;
import com.logistics.common.exception.user.CaptchaExpireException;
import com.logistics.common.utils.MessageUtils;
import com.logistics.common.utils.SecurityUtils;
import com.logistics.common.utils.StringUtils;
import com.logistics.system.service.ISysConfigService;
import com.logistics.system.service.ISysUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 注册校验方法
 * 
 * @author 2891517520@qq.com
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 注册
     */
    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();

        if(ObjectUtil.isNotNull(registerBody.getLoginType())){
            if(Constants.MINI_PROGRAM.equals(registerBody.getLoginType())){

            }else {
                boolean captchaOnOff = configService.selectCaptchaOnOff();
                // 验证码开关
                if (captchaOnOff)
                {
                    validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
                }
            }
        }else {
            boolean captchaOnOff = configService.selectCaptchaOnOff();
            // 验证码开关
            if (captchaOnOff)
            {
                validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
            }
        }


        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            SysUser sysUser = new SysUser();
            if(ObjectUtil.isNotNull(registerBody.getLoginType())){
                if(Constants.MINI_PROGRAM.equals(registerBody.getLoginType())){
                    sysUser.setUserName(username);
                    sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
                    sysUser.setNickName(registerBody.getNickName());
                    // 随机用户头像
                    Random random = new Random();
                    int i = random.nextInt(Constants.AVATARS.length);
                    sysUser.setAvatar(Constants.AVATARS[i]);
                    sysUser.setUserSource(registerBody.getLoginType());
                }else {
                    sysUser.setUserName(username);
                    sysUser.setNickName(username);
                    sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
                }
            }else {
                sysUser.setUserName(username);
                sysUser.setNickName(username);
                sysUser.setForgetPassword(registerBody.getPassword());
                sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            }

            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                // 根据用户名查询用户ID
                SysUser sysUser1 = userService.selectUserByUserName(username);
                // 新增用户角色
                Long[] roles = {2L};
                // 新增用户与角色管理
                List<SysUserRole> list = new ArrayList<SysUserRole>();
                for (Long roleId : roles)
                {
                    SysUserRole ur = new SysUserRole();
                    ur.setUserId(sysUser1.getUserId());
                    ur.setRoleId(roleId);
                    list.add(ur);
                }
                if (list.size() > 0)
                {
                    userRoleMapper.batchUserRole(list);
                }

                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER,
                        MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }

    /**
     * @title: 找回密码
     * @author: 28915
     * @params: email 邮箱
     * @date: 2022/4/15 16:08
     */
    public boolean retrievePassword(String email){

        boolean flag = false;
        SysUser sysUser = userService.selectUserByEmail(email);
        if(ObjectUtil.isNotNull(sysUser)){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(sysUser.getEmail());
            message.setSubject("找回密码");
            message.setText("密码："+sysUser.getForgetPassword());
            javaMailSender.send(message);
            flag = true;
        }
        return flag;
    }
}
