package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 18:57
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/20日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.TestCase.UploadImageTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfConnBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.WxServiceReqLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.DTO.KfConnReqDTO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase.Inter.IWxServiceResource;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.mq.TestProducer;
import com.galaxyeye.websocket.tool.mq.TestReciever;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import com.galaxyeye.websocket.tool.websocket.response.WebsocketResLoginPara;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Component
@Slf4j
public class KfConnTest extends BaseTest {
    @Autowired
    private WebSocketClient webSocketClientCustom;

    @Autowired
    private UploadImageTest uploadImageTest;

    private WebsocketResLoginPara websocketResLoginPara = null;

    @Autowired
    private TestReciever testReciever;

    @Autowired
    private TestProducer testProducer;

    public String KfConnTestByGernal() {
        KfConnBO kfConnBO = new KfConnBO();
        kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
        kfConnBO.setUid(websocketResLoginPara.getUid());
        kfConnBO.setMethod("kfConnReq");
        kfConnBO.setWxAppid("1234567812345678");
        List<KfConnBO.ContextBean> context = new ArrayList<>();
        KfConnBO.ContextBean contextBean = new KfConnBO.ContextBean();
        List<String> msg = new ArrayList<>();
        msg.add("接入消息");
        contextBean.setMsgType("common");
        contextBean.setType("user");
        contextBean.setMsg(msg);
        context.add(contextBean);
        kfConnBO.setContext(context);
        kfConnBO.setWxAppid("wxd996989304eaa926");
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
        String kfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
        log.info("kfConnResult 请求参数=" + kfConnResult);
        String chatPackSeq=null;
        try{
            Assert.assertTrue(kfConnResult.contains("method"));
            Assert.assertTrue(kfConnResult.contains("bmAppid"));
            Assert.assertTrue(kfConnResult.contains("uid"));
            Assert.assertTrue(kfConnResult.contains("result"));
            Assert.assertTrue(kfConnResult.contains("msg"));
            Assert.assertTrue(kfConnResult.contains("chatPackSeq"));
            Assert.assertTrue(kfConnResult.contains("kfNickname"));
            Assert.assertTrue(kfConnResult.contains("kfId"));
            Assert.assertTrue(kfConnResult.contains("kfId"));
            Assert.assertTrue(kfConnResult.contains("status"));
            chatPackSeq=JsonPath.read(kfConnResult, "$.chatPackSeq");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return chatPackSeq;
        }
    }

    /**
     * 调用该接口需要设置下BmAppid与Uid参数值
     * @param kfConnBO
     * @return
     */
    public AtomicReference<KfConnReqDTO> KfConnTestByGernal(KfConnBO kfConnBO) {
        AtomicReference<KfConnReqDTO> atomicReference=new AtomicReference<KfConnReqDTO>();
        String chatPackSeq = null;
        kfConnBO.setMethod("kfConnReq");
        kfConnBO.setWxAppid("1234567812345678");
        List<KfConnBO.ContextBean> context = new ArrayList<>();
        KfConnBO.ContextBean contextBean = new KfConnBO.ContextBean();
        List<String> msg = new ArrayList<>();
        msg.add("接入消息");
        contextBean.setMsgType("common");
        contextBean.setType("user");
        contextBean.setMsg(msg);
        context.add(contextBean);
        kfConnBO.setContext(context);
        kfConnBO.setWxAppid("wxd996989304eaa926");
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
        String kfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
        log.info("kfConnResult 请求参数=" + kfConnResult);
        KfConnReqDTO kfConnReqDTO=null;
        try {
            Assert.assertTrue(kfConnResult.contains("method"));
            Assert.assertTrue(kfConnResult.contains("bmAppid"));
            Assert.assertTrue(kfConnResult.contains("uid"));
            Assert.assertTrue(kfConnResult.contains("result"));
            Assert.assertTrue(kfConnResult.contains("msg"));
            Assert.assertTrue(kfConnResult.contains("chatPackSeq"));
            Assert.assertTrue(kfConnResult.contains("kfNickname"));
            Assert.assertTrue(kfConnResult.contains("kfId"));
            Assert.assertTrue(kfConnResult.contains("kfId"));
            Assert.assertTrue(kfConnResult.contains("status"));
            kfConnReqDTO=JSON.parseObject(kfConnResult, KfConnReqDTO.class );
        } catch (Exception e) {
            log.info("kfConnReqDTO=" + kfConnResult);
        } finally {
            atomicReference.set(kfConnReqDTO);
            return atomicReference;
        }
    }


    /**
     * 客户接入聊天系统,参数context不存在
     */
    @Test
    public void KfConnTestByNotExistParameterContext() {
        KfConnBO kfConnBO = null;
        String KfConnResult = null;
        try {
            kfConnBO = new KfConnBO();
            kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfConnBO.setUid(websocketResLoginPara.getUid());
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean = new KfConnBO.ContextBean();
            List<String> msg = new ArrayList<>();
            msg.add("消息");
            contextBean.setMsgType("common");
            contextBean.setType("user");
            contextBean.setMsg(msg);
            context.add(contextBean);
//        kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
            KfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
            log.info("KfConnResult 返回结果=" + KfConnResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入聊天系统,参数context不存在");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(kfConnBO));
            recordhttp.setResponse(KfConnResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(KfConnResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(KfConnResult.contains("bmAppid"), true);
            Assert.assertEquals(KfConnResult.contains("uid"), true);
            Assert.assertEquals(KfConnResult.contains("\"result\":1"), true);
            Assert.assertEquals(KfConnResult.contains("chatPackSeq"), true);
            Assert.assertEquals(KfConnResult.contains("kfNickname"), true);
            Assert.assertEquals(KfConnResult.contains("kfId"), true);
            Assert.assertEquals(KfConnResult.contains("status"), true);
        }
    }


    /**
     * 客户接入聊天系统,参数context存在且发送的内容是文字
     */
    @Test
    public void KfConnTestByExistParameterContextAndParameterMsgTypeValueIsCommon() {
        KfConnBO kfConnBO = null;
        String KfConnResult = null;
        try {
            kfConnBO = new KfConnBO();
            kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfConnBO.setUid(websocketResLoginPara.getUid());
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean = new KfConnBO.ContextBean();
            List<String> msg = new ArrayList<>();
            msg.add("消息");
            contextBean.setMsgType("common");
            contextBean.setType("user");
            contextBean.setMsg(msg);
            context.add(contextBean);
            kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
            KfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
            log.info("KfConnResult 返回结果=" + KfConnResult);
            webSocketClientCustom.onClose();
            initData();//此时微信服务端会发送reconnect给icservice

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入聊天系统,参数context存在且发送的内容是文字");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(kfConnBO));
            recordhttp.setResponse(KfConnResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(KfConnResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(KfConnResult.contains("bmAppid"), true);
            Assert.assertEquals(KfConnResult.contains("uid"), true);
            Assert.assertEquals(KfConnResult.contains("\"result\":1"), true);
            Assert.assertEquals(KfConnResult.contains("chatPackSeq"), true);
            Assert.assertEquals(KfConnResult.contains("kfNickname"), true);
            Assert.assertEquals(KfConnResult.contains("kfId"), true);
            Assert.assertEquals(KfConnResult.contains("status"), true);
        }
    }

    /**
     * 客户接入聊天系统,参数context存在且发送的内容是图片
     */
    @Test
    public void KfConnTestByExistParameterContextAndParameterMsgTypeValueIsPicture() {
        KfConnBO kfConnBO = null;
        String KfConnResult = null;
        String picUrl = "";
        try {
            kfConnBO = new KfConnBO();
            kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfConnBO.setUid(websocketResLoginPara.getUid());
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean = new KfConnBO.ContextBean();
            List<String> msg = new ArrayList<>();
            HashMap<String, String> hs = userLoginTest();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathPng = getFilePath("2.png");
            String imgParamPng = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            uploadImageBO.setImage(imgParamPng);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            picUrl = uploadImageTest.uploadImageGernal(uploadImageBO);
            msg.add(picUrl);
            contextBean.setMsgType("pic");
            contextBean.setType("user");
            contextBean.setMsg(msg);
            context.add(contextBean);
            kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
            KfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
            log.info("KfConnResult 返回结果=" + KfConnResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入聊天系统,参数context存在且发送的内容是图片");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(kfConnBO));
            recordhttp.setResponse(KfConnResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(KfConnResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(KfConnResult.contains("bmAppid"), true);
            Assert.assertEquals(KfConnResult.contains("uid"), true);
            Assert.assertEquals(KfConnResult.contains("\"result\":1"), true);
            Assert.assertEquals(KfConnResult.contains("chatPackSeq"), true);
            Assert.assertEquals(KfConnResult.contains("kfNickname"), true);
            Assert.assertEquals(KfConnResult.contains("kfId"), true);
            Assert.assertEquals(KfConnResult.contains("status"), true);
        }
    }


    /**
     * 客户接入聊天系统,参数context为文本，发送多条消息
     */
    @Test
    public void KfConnTestByParameterContextValueIsMutiAndParameterMsgTypeValueIsCommon() {
        KfConnBO kfConnBO = null;
        String KfConnResult = null;
        try {
            kfConnBO = new KfConnBO();
            kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfConnBO.setUid(websocketResLoginPara.getUid());
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean1 = new KfConnBO.ContextBean();
            List<String> msg1 = new ArrayList<>();
            msg1.add("消息1");
            contextBean1.setMsgType("common");
            contextBean1.setType("user");
            contextBean1.setMsg(msg1);
            context.add(contextBean1);

            KfConnBO.ContextBean contextBean2 = new KfConnBO.ContextBean();
            List<String> msg2 = new ArrayList<>();
            msg2.add("消息2");
            contextBean2.setMsgType("common");
            contextBean2.setType("user");
            contextBean2.setMsg(msg2);
            context.add(contextBean2);

            kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
            KfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
            log.info("KfConnResult 返回结果=" + KfConnResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入聊天系统,参数context为文本，发送多条消息");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(kfConnBO));
            recordhttp.setResponse(KfConnResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(KfConnResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(KfConnResult.contains("bmAppid"), true);
            Assert.assertEquals(KfConnResult.contains("uid"), true);
            Assert.assertEquals(KfConnResult.contains("\"result\":1"), true);
            Assert.assertEquals(KfConnResult.contains("chatPackSeq"), true);
            Assert.assertEquals(KfConnResult.contains("kfNickname"), true);
            Assert.assertEquals(KfConnResult.contains("kfId"), true);
            Assert.assertEquals(KfConnResult.contains("status"), true);
        }
    }

    /**
     * 客户接入聊天系统,参数context为图片，发送多张图片信息
     */
    @Test
    public void KfConnTestByParameterContextValueIsMutiAndParameterMsgTypeValueIsPicture() {
        KfConnBO kfConnBO = null;
        String KfConnResult = null;
        String picUrl1 = null;
        String picUrl2 = null;
        try {
            kfConnBO = new KfConnBO();
            kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfConnBO.setUid(websocketResLoginPara.getUid());
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean1 = new KfConnBO.ContextBean();
            HashMap<String, String> hs = userLoginTest();
            UploadImageBO uploadImageBO1 = new UploadImageBO();
            String filePathPng1 = getFilePath("2.png");
            String imgParamPng1 = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng1));
            uploadImageBO1.setImage(imgParamPng1);
            uploadImageBO1.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO1.setUid(hs.get("uid"));
            uploadImageBO1.setBmAppid("1.00002");
            uploadImageBO1.setAppid("1.00002");
            uploadImageBO1.setSeq("abc");
            uploadImageBO1.setSource("article");
            picUrl1 = uploadImageTest.uploadImageGernal(uploadImageBO1);

            List<String> msg1 = new ArrayList<>();
            msg1.add(picUrl1);
            contextBean1.setMsgType("pic");
            contextBean1.setType("user");
            contextBean1.setMsg(msg1);
            context.add(contextBean1);

            UploadImageBO uploadImageBO2 = new UploadImageBO();
            String filePathGif2 = getFilePath("4.gif");
            String imgParamGif2 = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif2));
            uploadImageBO2.setImage(imgParamGif2);
            uploadImageBO2.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO2.setUid(hs.get("uid"));
            uploadImageBO2.setBmAppid("1.00002");
            uploadImageBO2.setAppid("1.00002");
            uploadImageBO2.setSeq("abc");
            uploadImageBO2.setSource("article");
            picUrl2 = uploadImageTest.uploadImageGernal(uploadImageBO2);
            KfConnBO.ContextBean contextBean2 = new KfConnBO.ContextBean();
            List<String> msg2 = new ArrayList<>();
            msg2.add(picUrl2);
            contextBean2.setMsgType("pic");
            contextBean2.setType("user");
            contextBean2.setMsg(msg2);
            context.add(contextBean2);

            kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
            KfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
            log.info("KfConnResult 返回结果=" + KfConnResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入聊天系统,参数context为文本，发送多条消息");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(kfConnBO));
            recordhttp.setResponse(KfConnResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(KfConnResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(KfConnResult.contains("bmAppid"), true);
            Assert.assertEquals(KfConnResult.contains("uid"), true);
            Assert.assertEquals(KfConnResult.contains("\"result\":1"), true);
            Assert.assertEquals(KfConnResult.contains("chatPackSeq"), true);
            Assert.assertEquals(KfConnResult.contains("kfNickname"), true);
            Assert.assertEquals(KfConnResult.contains("kfId"), true);
            Assert.assertEquals(KfConnResult.contains("status"), true);
        }
    }


    /**
     * 客户接入聊天系统,参数context为图片与文本信息，发送1张图片信息与一个文本消息
     */
    @Test
    public void KfConnTestByParameterContextValueIsMutiAndParameterMsgTypeValueIsPictureAndParameterMsgTypeValueIsCommon() {
        KfConnBO kfConnBO = null;
        String KfConnResult = null;
        String picUrl2 = null;
        try {
            kfConnBO = new KfConnBO();
            kfConnBO.setBmAppid(websocketResLoginPara.getBmAppid());
            kfConnBO.setUid(websocketResLoginPara.getUid());
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean1 = new KfConnBO.ContextBean();
            HashMap<String, String> hs = userLoginTest();
            List<String> msg1 = new ArrayList<>();
            msg1.add("消息1");
            contextBean1.setMsgType("common");
            contextBean1.setType("user");
            contextBean1.setMsg(msg1);
            context.add(contextBean1);

            UploadImageBO uploadImageBO2 = new UploadImageBO();
            String filePathGif2 = getFilePath("4.gif");
            String imgParamGif2 = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif2));
            uploadImageBO2.setImage(imgParamGif2);
            uploadImageBO2.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO2.setUid(hs.get("uid"));
            uploadImageBO2.setBmAppid("1.00002");
            uploadImageBO2.setAppid("1.00002");
            uploadImageBO2.setSeq("abc");
            uploadImageBO2.setSource("article");
            picUrl2 = uploadImageTest.uploadImageGernal(uploadImageBO2);
            KfConnBO.ContextBean contextBean2 = new KfConnBO.ContextBean();
            List<String> msg2 = new ArrayList<>();
            msg2.add(picUrl2);
            contextBean2.setMsgType("pic");
            contextBean2.setType("user");
            contextBean2.setMsg(msg2);
            context.add(contextBean2);

            kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfConnBO));
            KfConnResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(kfConnBO));
            log.info("KfConnResult 返回结果=" + KfConnResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户接入聊天系统,参数context为文本，发送多条消息");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(kfConnBO));
            recordhttp.setResponse(KfConnResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(KfConnResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(KfConnResult.contains("bmAppid"), true);
            Assert.assertEquals(KfConnResult.contains("uid"), true);
            Assert.assertEquals(KfConnResult.contains("\"result\":1"), true);
            Assert.assertEquals(KfConnResult.contains("chatPackSeq"), true);
            Assert.assertEquals(KfConnResult.contains("kfNickname"), true);
            Assert.assertEquals(KfConnResult.contains("kfId"), true);
            Assert.assertEquals(KfConnResult.contains("status"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }


    @Override
    public void initData() {
        String wsUrlSy = url;
        webSocketClientCustom = new WebSocketClient(wsUrlSy);
        webSocketClientCustom.setTestProducer(testProducer);
        WxServiceReqLoginBO wxServiceReqLoginBO = new WxServiceReqLoginBO();
        Map<String, String> statistics = new HashMap<>();
        statistics.put("channelNo", "小程序服务端渠道编号");
        statistics.put("维度一", "渠道编号");
        statistics.put("维度二", "28");
        wxServiceReqLoginBO.setBmAppid("1.00002");
        wxServiceReqLoginBO.setMethod("loginReq");
        wxServiceReqLoginBO.setPasswd("123456");
        wxServiceReqLoginBO.setUname("hxw002");
        wxServiceReqLoginBO.setStatistics(statistics);
        log.info("websocket登录请求参数" + JSON.toJSONString(wxServiceReqLoginBO));
        String websocketLoginObj = webSocketClientCustom.sendMessageSy(JSON.toJSONString(wxServiceReqLoginBO));
        log.info("websocket登录返回消息" + websocketLoginObj);
        websocketResLoginPara = JSON.parseObject(websocketLoginObj, WebsocketResLoginPara.class);
    }


    public WebsocketResLoginPara getWebsocketResLoginPara() {
        return websocketResLoginPara;
    }


    @Override
    public void destroyData() {
    }

    public WebSocketClient getWebSocketClientCustom() {
        return webSocketClientCustom;
    }

    public void setWebSocketClientCustom(WebSocketClient webSocketClientCustom) {
        this.webSocketClientCustom = webSocketClientCustom;
    }

}
