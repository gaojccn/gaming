package com.gaojc;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.gaojc.config.AccessToken;
import com.gaojc.servlet.MyServer;
import com.gaojc.util.HttpClientUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gaojc on 2018/4/19.
 */

public class AccessTokenTest {
    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    private static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" +
            "client_credential&appid=APPID&secret=APPSECRET";

    private String appid = "";
    private String appsecret = "";

    @Before
    public void init() {
        appid = "wx462586dab64245ec";
        appsecret = "2fcd0b46529bfc2dfecfdeccd9099745";
    }

    /**
     * 获取access_token
     * access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
     */
    @Test
    public void testGetAccessToken() {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
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
