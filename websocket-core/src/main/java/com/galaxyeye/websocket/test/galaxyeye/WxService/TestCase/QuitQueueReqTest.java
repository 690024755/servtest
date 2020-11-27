package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/1日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfChatBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.QuitQueueReqBO;
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
public class QuitQueueReqTest extends BaseTest {
    @Autowired
    private WebSocketClient webSocketClientCustom;

    private WebsocketResLoginPara websocketResLoginPara = null;

    @Autowired
    private KfConnTest kfConnTest;


    /**
     * 客户接入之前无排队客户，客户已登录但未接入选择退出排队
     */
    @Test
    public void quitQueueReqTestByExistQueueAndUserLoginAndNotConnect() {
        QuitQueueReqBO quitQueueReqBO = null;
        String quitQueueReqResult = null;
        try {
            quitQueueReqBO = new QuitQueueReqBO();
            quitQueueReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitQueueReqBO.setUid(websocketResLoginPara.getUid());
            quitQueueReqBO.setMethod("quitQueueReq");
            quitQueueReqBO.setWxAppid("1234567812345678");
            log.info("quitQueueReqBO 请求参数=" + JSON.toJSONString(quitQueueReqBO));
            quitQueueReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitQueueReqBO));
            log.info("quitQueueReqResult 返回结果=" + quitQueueReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入之前无排队客户，客户已登录但未接入选择退出排队");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitQueueReqBO));
            recordhttp.setResponse(quitQueueReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitQueueReqResult.contains("method"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"uid\":-1"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"strUid\":\"-1\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"result\":101"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"msg\":\"invalid data.\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"token\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"upgradePath\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"openid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"unionid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"txAiAPpid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"txAiAppid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"txAiAppkey\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"bdAiApiKey\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"bdAiSecretKey\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("timestamp"), true);
        }
    }

    /**
     * 客户接入之前无排队客户，客户已登录已接入选择退出排队
     */
    @Test
    public void quitQueueReqTestByExistQueueAndUserLoginAndUserConnect() {
        QuitQueueReqBO quitQueueReqBO = null;
        String quitQueueReqResult = null;
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            quitQueueReqBO = new QuitQueueReqBO();
            quitQueueReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitQueueReqBO.setUid(websocketResLoginPara.getUid());
            quitQueueReqBO.setMethod("quitQueueReq");
            quitQueueReqBO.setWxAppid("1234567812345678");
            log.info("quitQueueReqBO 请求参数=" + JSON.toJSONString(quitQueueReqBO));
            quitQueueReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitQueueReqBO));
            log.info("quitQueueReqResult 返回结果=" + quitQueueReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入之前无排队客户，客户已登录已接入选择退出排队");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitQueueReqBO));
            recordhttp.setResponse(quitQueueReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitQueueReqResult.contains("method"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"uid\":-1"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"strUid\":\"-1\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"result\":101"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"msg\":\"invalid data.\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"token\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"upgradePath\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"openid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"unionid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"txAiAPpid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"txAiAppid\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"txAiAppkey\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"bdAiApiKey\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"bdAiSecretKey\":\"\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("timestamp"), true);
        }
    }

    /**
     * 客户接入之前无排队客户，客户已登录且已接入且与客服在聊天，此时选择退出排队
     */
    @Test
    public void quitQueueReqTestByExistQueueAndUserLoginAndUserConnectAndUserChat() {
        QuitQueueReqBO quitQueueReqBO = null;
        String quitQueueReqResult = null;
        String chatPackSeq=null;
        chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            if(chatPackSeq!=null){
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
                quitQueueReqBO = new QuitQueueReqBO();
                quitQueueReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
                quitQueueReqBO.setUid(websocketResLoginPara.getUid());
                quitQueueReqBO.setMethod("quitQueueReq");
                quitQueueReqBO.setWxAppid("1234567812345678");
                log.info("quitQueueReqBO 请求参数=" + JSON.toJSONString(quitQueueReqBO));
                quitQueueReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitQueueReqBO));
                log.info("quitQueueReqResult 返回结果=" + quitQueueReqResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入之前无排队客户，客户已登录且已接入且与客服在聊天，此时选择退出排队");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitQueueReqBO));
            recordhttp.setResponse(quitQueueReqResult);
            initLog(recordhttp, new Object() {
            });
        }
    }



    /**
     * 客户接入之前有排队客户，客户已登录已接入选择退出排队
     */
    @Test
    public void quitQueueReqTestByNotExistQueueAndUserLoginAndUserConnect() {
        QuitQueueReqBO quitQueueReqBO = null;
        String quitQueueReqResult = null;
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            quitQueueReqBO = new QuitQueueReqBO();
            quitQueueReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            quitQueueReqBO.setUid(websocketResLoginPara.getUid());
            quitQueueReqBO.setMethod("quitQueueReq");
            quitQueueReqBO.setWxAppid("1234567812345678");
            log.info("quitQueueReqBO 请求参数=" + JSON.toJSONString(quitQueueReqBO));
            quitQueueReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitQueueReqBO));
            log.info("quitQueueReqResult 返回结果=" + quitQueueReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入之前有排队客户，客户已登录已接入选择退出排队");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitQueueReqBO));
            recordhttp.setResponse(quitQueueReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitQueueReqResult.contains("\"method\":\"exitQueueResp\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitQueueReqResult.contains("uid"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"msg\":\"\""), true);
        }
    }

    /**
     * 客户接入之前有排队客户，客户已登录且已接入且与客服在聊天，此时选择退出排队
     */
    @Test
    public void quitQueueReqTestByNotExistQueueAndUserLoginAndUserConnectAndUserChat() {
        QuitQueueReqBO quitQueueReqBO = null;
        String quitQueueReqResult = null;
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
                quitQueueReqBO = new QuitQueueReqBO();
                quitQueueReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
                quitQueueReqBO.setUid(websocketResLoginPara.getUid());
                quitQueueReqBO.setMethod("quitQueueReq");
                quitQueueReqBO.setWxAppid("1234567812345678");
                log.info("quitQueueReqBO 请求参数=" + JSON.toJSONString(quitQueueReqBO));
                quitQueueReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(quitQueueReqBO));
                log.info("quitQueueReqResult 返回结果=" + quitQueueReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入之前有排队客户，客户已登录且已接入且与客服在聊天，此时选择退出排队");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(quitQueueReqBO));
            recordhttp.setResponse(quitQueueReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(quitQueueReqResult.contains("\"method\":\"exitQueueResp\""), true);
            Assert.assertEquals(quitQueueReqResult.contains("bmAppid"), true);
            Assert.assertEquals(quitQueueReqResult.contains("uid"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(quitQueueReqResult.contains("\"msg\":\"\""), true);
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
