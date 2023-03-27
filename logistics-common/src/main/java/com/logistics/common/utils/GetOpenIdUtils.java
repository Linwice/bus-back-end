package com.logistics.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

public class GetOpenIdUtils {

    @Value("${wx.app-id}")
    private static String appId;

    @Value("{wx.app-secret}")
    private static String appSecret;

    /**
     * 获取openId 长期授权字符串
     *
     * @param code 临时登陆凭证
     * @return openId 长期授权字符串
     */
    public static String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, map);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        String openId = jsonObject.getStr("openid");
//        if (openId == null || openId.length() == 0) {
//            throw new RuntimeException("临时登陆凭证错误");
//        }
        return openId;
    }
}
