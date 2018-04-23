package com.gaojc.gaming.controller;

import io.github.elkan1788.mpsdk4j.core.WechatDefHandler;
import io.github.elkan1788.mpsdk4j.mvc.WechatWebSupport;
import io.github.elkan1788.mpsdk4j.util.ConfigReader;
import io.github.elkan1788.mpsdk4j.vo.MPAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC环境接入
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RequestMapping("/springmvc")
@Controller
public class WechatCoreController extends WechatWebSupport {

    private static final Logger log = LoggerFactory.getLogger(WechatCoreController.class);

    private static final ConfigReader _cr = new ConfigReader("/config/gaming.properties");

    @Override
    public void init() {
        log.info("====== SpringMVC环境 =======");
        super.init();
        MPAccount mpact = new MPAccount();
        mpact.setMpId(_cr.get("wechat.mpId"));
        mpact.setAppId(_cr.get("wechat.appId"));
        mpact.setAppSecret(_cr.get("wechat.appSecret"));
        mpact.setToken(_cr.get("wechat.token"));
//        mpact.setAESKey(_cr.get("aesKey"));
        _wk.setMpAct(mpact);
        _wk.setWechatHandler(new WechatDefHandler());
    }

    @RequestMapping("/wechatcore")
    public void wechatCore(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        this.interact(req, resp);
    }

}
