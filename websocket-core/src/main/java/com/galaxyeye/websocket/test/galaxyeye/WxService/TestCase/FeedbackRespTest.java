package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 14:58
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/3日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.FeedbackRespBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfChatBO;
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
public class FeedbackRespTest extends BaseTest {

    @Autowired
    private WebSocketClient webSocketClientCustom;

    private WebsocketResLoginPara websocketResLoginPara = null;

    @Autowired
    private KfConnTest kfConnTest;


    /**
     * 参数Evaluate值为1，不满意评价
     * @throws InterruptedException
     */
    @Test
    public void feedbackRespTestByParameterEvaluateValueIsOne() throws InterruptedException {
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(chatPackSeq);
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
        Thread.sleep(2000);
        String feedbackRespResult="";
        FeedbackRespBO feedbackRespBO=null;
        try{
            feedbackRespBO=new FeedbackRespBO();
            feedbackRespBO.setMethod("feedbackReq");
            feedbackRespBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
            feedbackRespBO.setChatPackSeq(chatPackSeq);
            feedbackRespBO.setEvaluate(1);
            feedbackRespBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
            feedbackRespBO.setWxAppid("1234567812345678");
            log.info("feedbackRespBO 请求参数=" + JSON.toJSONString(feedbackRespBO));
            feedbackRespResult=webSocketClientCustom.sendMessageSy(JSON.toJSONString(feedbackRespBO));
            log.info("feedbackRespResult 返回结果=" + feedbackRespResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Evaluate值为1，不满意评价");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(feedbackRespBO));
            recordhttp.setResponse(feedbackRespResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(feedbackRespResult.contains("\"method\":\"feedbackResp\""), true);
            Assert.assertEquals(feedbackRespResult.contains("bmAppid"), true);
            Assert.assertEquals(feedbackRespResult.contains("uid"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"result\":1"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"msg\":\"\""), true);
        }
    }

    /**
     * 参数Evaluate值为2，一般评价
     * @throws InterruptedException
     */
    @Test
    public void feedbackRespTestByParameterEvaluateValueIsTwo() throws InterruptedException {
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(chatPackSeq);
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
        Thread.sleep(2000);
        String feedbackRespResult="";
        FeedbackRespBO feedbackRespBO=null;
        try{
            feedbackRespBO=new FeedbackRespBO();
            feedbackRespBO.setMethod("feedbackReq");
            feedbackRespBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
            feedbackRespBO.setChatPackSeq(chatPackSeq);
            feedbackRespBO.setEvaluate(2);
            feedbackRespBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
            feedbackRespBO.setWxAppid("1234567812345678");
            log.info("feedbackRespBO 请求参数=" + JSON.toJSONString(feedbackRespBO));
            feedbackRespResult=webSocketClientCustom.sendMessageSy(JSON.toJSONString(feedbackRespBO));
            log.info("feedbackRespResult 返回结果=" + feedbackRespResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Evaluate值为1，不满意评价");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(feedbackRespBO));
            recordhttp.setResponse(feedbackRespResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(feedbackRespResult.contains("\"method\":\"feedbackResp\""), true);
            Assert.assertEquals(feedbackRespResult.contains("bmAppid"), true);
            Assert.assertEquals(feedbackRespResult.contains("uid"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"result\":1"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"msg\":\"\""), true);
        }
    }

    /**
     * 参数Evaluate值为3，满意评价
     * @throws InterruptedException
     */
    @Test
    public void feedbackRespTestByParameterEvaluateValueIsThree() throws InterruptedException {
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(chatPackSeq);
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
        Thread.sleep(2000);
        String feedbackRespResult="";
        FeedbackRespBO feedbackRespBO=null;
        try{
            feedbackRespBO=new FeedbackRespBO();
            feedbackRespBO.setMethod("feedbackReq");
            feedbackRespBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
            feedbackRespBO.setChatPackSeq(chatPackSeq);
            feedbackRespBO.setEvaluate(3);
            feedbackRespBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
            feedbackRespBO.setWxAppid("1234567812345678");
            log.info("feedbackRespBO 请求参数=" + JSON.toJSONString(feedbackRespBO));
            feedbackRespResult=webSocketClientCustom.sendMessageSy(JSON.toJSONString(feedbackRespBO));
            log.info("feedbackRespResult 返回结果=" + feedbackRespResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Evaluate值为1，不满意评价");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(feedbackRespBO));
            recordhttp.setResponse(feedbackRespResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(feedbackRespResult.contains("\"method\":\"feedbackResp\""), true);
            Assert.assertEquals(feedbackRespResult.contains("bmAppid"), true);
            Assert.assertEquals(feedbackRespResult.contains("uid"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"result\":1"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"msg\":\"\""), true);
        }
    }

    /**
     * 参数Evaluate值为其他值，未知评价
     * @throws InterruptedException
     */
    @Test
    public void feedbackRespTestByParameterEvaluateValueIsOther() throws InterruptedException {
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(chatPackSeq);
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
        Thread.sleep(2000);
        String feedbackRespResult="";
        FeedbackRespBO feedbackRespBO=null;
        try{
            feedbackRespBO=new FeedbackRespBO();
            feedbackRespBO.setMethod("feedbackReq");
            feedbackRespBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
            feedbackRespBO.setChatPackSeq(chatPackSeq);
            feedbackRespBO.setEvaluate(1000);
            feedbackRespBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
            feedbackRespBO.setWxAppid("1234567812345678");
            log.info("feedbackRespBO 请求参数=" + JSON.toJSONString(feedbackRespBO));
            feedbackRespResult=webSocketClientCustom.sendMessageSy(JSON.toJSONString(feedbackRespBO));
            log.info("feedbackRespResult 返回结果=" + feedbackRespResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Evaluate值为1，不满意评价");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(feedbackRespBO));
            recordhttp.setResponse(feedbackRespResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(feedbackRespResult.contains("\"method\":\"errorbackResp\""), true);
            Assert.assertEquals(feedbackRespResult.contains("bmAppid"), true);
            Assert.assertEquals(feedbackRespResult.contains("uid"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"result\":0"), true);
            Assert.assertEquals(feedbackRespResult.contains("\"msg\":\"\""), true);
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
