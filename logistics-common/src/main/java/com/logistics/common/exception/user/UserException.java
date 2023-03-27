package com.logistics.common.exception.user;

import com.logistics.common.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author 2891517520@qq.com
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
