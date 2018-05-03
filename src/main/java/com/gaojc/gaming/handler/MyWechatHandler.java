package com.gaojc.gaming.handler;

import io.github.elkan1788.mpsdk4j.core.WechatDefHandler;
import io.github.elkan1788.mpsdk4j.vo.event.BasicEvent;
import io.github.elkan1788.mpsdk4j.vo.message.*;

import java.util.Arrays;

/**
 * Created by gaojc on 2018/4/25.
 */
public class MyWechatHandler extends WechatDefHandler {

    /**
     * 关注、订阅公众号
     *
     * @param be
     * @return
     */
    @Override
    public BasicMsg eSub(BasicEvent be) {
        TextMsg tm = new TextMsg(be);
        tm.setContent("亲，终于等到你，欢迎关注竞彩赢家。");
        return tm;
    }

    /**
     * 接收处理文本消息
     *
     * @param tm
     * @return
     */
    @Override
    public BasicMsg text(TextMsg tm) {
        String rp = tm.getContent();
        switch (rp) {
            case "1":
                tm.setContent("今日推荐:http://www.okooo.com/circle/");
                break;
            case "2":
                tm.setContent("点击进入比分直播:http://www.okooo.com/livecenter/football/");
                break;
            case "3":
                tm.setContent("看直播:http://www.wuchajian.net/");
                break;
            case "4":
                tm.setContent("活动:http://8.lemicp.com/");
                break;
            default:
                tm.setContent("请回复以下数字进入相应功能:\n" +
                        "1:每日推荐\n" +
                        "2:比分直播\n" +
                        "3:直播地址\n" +
                        "4:福利活动");
                break;
        }
        return tm;
    }

    /**
     * 发送图文消息
     *
     * @param lm
     * @return
     */
    @Override
    public BasicMsg link(LinkMsg lm) {
        NewsMsg news = new NewsMsg(lm);
        Article art = new Article();
        art.setTitle(lm.getTitle());
        art.setDescription(lm.getDescription());
        art.setPicUrl("http://5b0988e595225.cdn.sohucs.com/images/20180503/82ddb0fa074c465392bc82efbe116d47.jpeg");
        art.setUrl(lm.getUrl());
        news.setArticles(Arrays.asList(art));
//        lm.setUrl(art.getUrl());
        return news;
    }
}
