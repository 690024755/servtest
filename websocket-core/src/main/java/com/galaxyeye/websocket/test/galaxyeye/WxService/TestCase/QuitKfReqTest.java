package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 18:05
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/2日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfChatBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.QuitKfReqBO;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import com.galaxyeye.websocket.tool.websocket.response.WebsocketResLoginPara;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;


@Component
@Slf4j
public class QuitKfReqTest extends BaseTest {
    @Autowired
    private WebSocketClient webSocketClientCustom;

    private WebsocketResLoginPara websocketResLoginPara = null;

    @Autowired
    private KfConnTest kfConnTest;


    /**
     *客户在聊天时候，退出客服聊天系统
     */
    @Test
    public void quitKfReqTestByExistKfConnReqAndExistKfchatReqAndExistQuitKfReq() {
        QuitKfReqBO quitKfReqBO = null;
        String quitKfReqResult = null;
        String chatPackSeq=null;
        chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            KfChatBO kfChatBO = new KfChatBO();
            kfChatBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfChatBO.setUid(Integer.valueOf(websocketResLoginPara.getStrUid()));
            kfChatBO.setMethod("kfchatReq");
            kfChatBO.setWxAppid("1234567812345678");
            kfChatBO.setMsgType("common");
            kfChatBO.setContent("我无聊，我要找客服聊天");
            kfChatBO.setChatPackSeq(chatPackSeq);
            log.info("kfChatBO 请求参数="+JSON.toJSONString(kfChatBO));
            webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
            quitKfReqBO = new QuitKfReqBO();
            quitKfReqBO.setChatPackSeq(chatPackSeq);
            quitKfReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitKfReqBO.setUid(websocketResLoginPara.getUid());
            quitKfReqBO.setMethod("quitKfReq");
            quitKfReqBO.setWxAppid("1234567812345678");
            log.info("quitKfReqBO 请求参数=" + JSON.toJSONString(quitKfReqBO));
            quitKfReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitKfReqBO));
            log.info("quitKfReqResult 返回结果=" + quitKfReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户在聊天时候，退出客服聊天系统");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitKfReqBO));
            recordhttp.setResponse(quitKfReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitKfReqResult.contains("\"method\":\"quitKfResp\""), true);
            Assert.assertEquals(quitKfReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitKfReqResult.contains("uid"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(quitKfReqResult.contains("feedback"), true);
        }
    }



    @Test
    public void quitKfReqTestByExistKfConnReqAndNotKfchatReqAndExistQuitKfReq() {
        QuitKfReqBO quitKfReqBO = null;
        String quitKfReqResult = null;
        String chatPackSeq=null;
        chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            KfChatBO kfChatBO = new KfChatBO();
            kfChatBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfChatBO.setUid(Integer.valueOf(websocketResLoginPara.getStrUid()));
            kfChatBO.setMethod("kfchatReq");
            //不存在，随便设置
            kfChatBO.setWxAppid("1234567812345678");
            kfChatBO.setMsgType("common");
            kfChatBO.setContent("我无聊，我要找客服聊天");
            kfChatBO.setChatPackSeq(chatPackSeq);
            log.info("kfChatBO 请求参数="+JSON.toJSONString(kfChatBO));
//            webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
            quitKfReqBO = new QuitKfReqBO();
            quitKfReqBO.setChatPackSeq(chatPackSeq);
            quitKfReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitKfReqBO.setUid(websocketResLoginPara.getUid());
            quitKfReqBO.setMethod("quitKfReq");
            quitKfReqBO.setWxAppid("1234567812345678");
            log.info("quitKfReqBO 请求参数=" + JSON.toJSONString(quitKfReqBO));
            quitKfReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitKfReqBO));
            log.info("quitKfReqResult 返回结果=" + quitKfReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入后未聊天，退出客服聊天系统");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitKfReqBO));
            recordhttp.setResponse(quitKfReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitKfReqResult.contains("\"method\":\"quitKfResp\""), true);
            Assert.assertEquals(quitKfReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitKfReqResult.contains("uid"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(quitKfReqResult.contains("feedback"), true);
        }
    }



    @Test
    public void quitKfReqTestByNotKfConnReqAndExistQuitKfReq() {
        QuitKfReqBO quitKfReqBO = null;
        String quitKfReqResult = null;
        String chatPackSeq=null;
        try {
            KfChatBO kfChatBO = new KfChatBO();
            kfChatBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfChatBO.setUid(Integer.valueOf(websocketResLoginPara.getStrUid()));
            kfChatBO.setMethod("kfchatReq");
            kfChatBO.setWxAppid("1234567812345678");
            kfChatBO.setMsgType("common");
            kfChatBO.setContent("我无聊，我要找客服聊天");
            kfChatBO.setChatPackSeq(chatPackSeq);
            log.info("kfChatBO 请求参数="+JSON.toJSONString(kfChatBO));
//            webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
            quitKfReqBO = new QuitKfReqBO();
            quitKfReqBO.setChatPackSeq("75101613769254502411");
            quitKfReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitKfReqBO.setUid(websocketResLoginPara.getUid());
            quitKfReqBO.setMethod("quitKfReq");
            quitKfReqBO.setWxAppid("1234567812345678");
            log.info("quitKfReqBO 请求参数=" + JSON.toJSONString(quitKfReqBO));
            quitKfReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitKfReqBO));
            log.info("quitKfReqResult 返回结果=" + quitKfReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户未接入客服系统，退出客服聊天系统");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitKfReqBO));
            recordhttp.setResponse(quitKfReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitKfReqResult.contains("\"method\":\"quitKfResp\""), true);
            Assert.assertEquals(quitKfReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitKfReqResult.contains("uid"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"result\":-1"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(quitKfReqResult.contains("feedback"), true);
        }
    }


    /**
     *客户接入客服系统之前，已有排队客户存在，客户退出客服聊天系统
     */
    @Test
    public void quitKfReqTestByExistQueueAndNotKfConnReqAndNotQuitKfReq() {
        QuitKfReqBO quitKfReqBO = null;
        String quitKfReqResult = null;
        String chatPackSeq=null;
        chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            KfChatBO kfChatBO = new KfChatBO();
            kfChatBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfChatBO.setUid(Integer.valueOf(websocketResLoginPara.getStrUid()));
            kfChatBO.setMethod("kfchatReq");
            kfChatBO.setWxAppid("1234567812345678");
            kfChatBO.setMsgType("common");
            kfChatBO.setContent("我无聊，我要找客服聊天");
            kfChatBO.setChatPackSeq(chatPackSeq);
            log.info("kfChatBO 请求参数="+JSON.toJSONString(kfChatBO));
//            webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
            quitKfReqBO = new QuitKfReqBO();
            quitKfReqBO.setChatPackSeq("75101613769254502411");
            quitKfReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitKfReqBO.setUid(websocketResLoginPara.getUid());
            quitKfReqBO.setMethod("quitKfReq");
            quitKfReqBO.setWxAppid("1234567812345678");
            log.info("quitKfReqBO 请求参数=" + JSON.toJSONString(quitKfReqBO));
            quitKfReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitKfReqBO));
            log.info("quitKfReqResult 返回结果=" + quitKfReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入客服系统之前，已有排队客户存在，客户退出客服聊天系统");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitKfReqBO));
            recordhttp.setResponse(quitKfReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitKfReqResult.contains("\"method\":\"quitKfResp\""), true);
            Assert.assertEquals(quitKfReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitKfReqResult.contains("uid"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(quitKfReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(quitKfReqResult.contains("feedback"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }


    @Override
    public void initData() {
        kfConnTest.initData();
        webSocketClientCustom=kfConnTest.getWebSocketClientCustom();
        websocketResLoginPara=kfConnTest.getWebsocketResLoginPara();
    }

    @Override
    public void destroyData() {

    }
}
