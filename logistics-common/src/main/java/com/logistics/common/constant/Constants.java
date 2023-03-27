package com.logistics.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 * 
 * @author 2891517520@qq.com
 */
public class Constants
{

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 登录类型(MA:小程序登录)
     */
    public static final String MINI_PROGRAM = "MA";

    /**
     * 小程序登录密码默认123456不可修改
     */
    public static final String MINI_PROGRAM_PASSWORD = "123456";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 用户信息
     */
    public static final String USERINFO = "userInfo";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi:";

    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap:";

    /**
     * LDAPS 远程方法调用
     */
    public static final String LOOKUP_LDAPS = "ldaps:";

    public static final String [] AVATARS = {
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2F36ad495ab9b41bd9b7ab10f5689bec04.jpeg",
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2F8a6be787cfd613657dd3d7be06a6b2dc.jpeg",
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2F96a33716bb7cc33e5a8df04152afb0ef.jpeg",
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2Fa51fa0e1b2a168905dcf9da648364ffb.jpeg",
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2Fd1b8ce0b032dac4bbafe6d4ba85ad309.jpeg",
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2Fd879c81dd7996146f6ae97e78c302bd5.jpeg",
            "https://petsfoster-1305669352.cos.ap-shanghai.myqcloud.com/image%2Favatar%2Fface.jpg"};
}
