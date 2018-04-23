package com.gaojc.gaming.config;

import com.SpringTest;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.gaojc.gaming.servlet.MyServer;
import com.gaojc.gaming.util.HttpClientUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by gaojc on 2018/4/19.
 */

public class AccessTokenTest extends SpringTest {
    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    @Value("${wechat.accessTokenUrl}")
    private String accessTokenUrl;

    @Value("${wechat.appId}")
    private String appid;

    @Value("${wechat.appSecret}")
    private String appsecret;

    /**
     * 获取access_token
     * access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
     */
    @Test
    public void testGetAccessToken() {
        System.out.println(appid + "," + appsecret + "," + accessTokenUrl);

        AccessToken accessToken = null;
        String requestUrl = accessTokenUrl.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = HttpClientUtils.httpGet(requestUrl);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInteger("expires_in"));

                logger.info(accessToken.toString());
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

}
