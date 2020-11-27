package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 11:11
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.TestCase.UploadImageTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfChatBO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Slf4j
public class KfChatTest extends BaseTest {

    @Autowired
    private KfConnTest kfConnTest;

    @Autowired
    private UploadImageTest uploadImageTest;


    private WebSocketClient webSocketClientCustom=null;

    /**
     * 与客服聊天的通用方法
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByGernal() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(kfConnTest.KfConnTestByGernal());
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }


    /**
     * 与客服聊天的内容是文字
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsCommon() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(kfConnTest.KfConnTestByGernal());
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * 与客服聊天的内容是文字,且字符内容超级大
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsCommonAndLargeCommon() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        String filePathPng = getFilePath("2.png");
        String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
        kfChatBO.setMsgType(imgParamPng);
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(kfConnTest.KfConnTestByGernal());
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }


    /**
     * 与客服聊天的内容是图片,Content填写图片的连接地址
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsPicture() throws InterruptedException {
        String picUrl="";
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("pic");
        UploadImageBO uploadImageBO=new UploadImageBO();
        HashMap<String, String> hs=userLoginTest();
        String filePathGif = getFilePath("Article4.gif");
        String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
        uploadImageBO.setImage(imgParamGif);
        uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
        uploadImageBO.setUid(hs.get("uid"));
        uploadImageBO.setBmAppid("1.00002");
        uploadImageBO.setAppid("1.00002");
        uploadImageBO.setSeq("abc");
        uploadImageBO.setSource("article");
        try{
             picUrl=uploadImageTest.uploadImageGernal(uploadImageBO);
        }catch (Exception e){
            e.printStackTrace();
        }
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(kfConnTest.KfConnTestByGernal());
        kfChatBO.setContent(picUrl);
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * ChatPackSeq的值为随机值
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterChatPackSeqValueIsRadom() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * 与客服聊天，发送超级大的图片
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsPictureAndLargePicture() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * 与客服聊天，发送图片为JPG
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsPictureAndPictureTypeIsJPG() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }


    /**
     * 与客服聊天，发送图片为PNG
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsPictureAndPictureTypeIsPNG() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * 与客服聊天，发送图片为GIF
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsPictureAndPictureTypeIsGIF() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * 与客服聊天，发送表情
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsEmotion() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * ChatPackSeq的值为随机值
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterUidValueIsWrong() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(123456);
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }

    /**
     * 参数MsgType=test, 但是Content=是文字
     * @throws InterruptedException
     */
    @Test
    public void KfChatTestByParameterMsgTypeValueIsTestAndParameterContentValueIsCommon() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("test");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq("XXXXXXXX");
        kfChatBO.setContent("我无聊，我要找客服聊天" );
        log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
    }


    @Test
    public void KfChatTestByConcurent() throws InterruptedException {
        KfChatBO kfChatBO = new KfChatBO();
        kfChatBO.setBmAppid(kfConnTest.getWebsocketResLoginPara().getBmAppid());
        kfChatBO.setUid(kfConnTest.getWebsocketResLoginPara().getUid());
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("我无聊，我要找客服聊天");
        kfChatBO.setChatPackSeq(kfConnTest.KfConnTestByGernal());
        AtomicInteger i=new AtomicInteger(0);
        getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Boolean flag=false;
                while (!flag){
                    kfChatBO.setContent("我无聊，我要找客服聊天,已发送第"+i.incrementAndGet()+"条消息");
                    log.info("kfConnBO 请求参数=" + JSON.toJSONString(kfChatBO));
                    webSocketClientCustom.sendMessageAsy(JSON.toJSONString(kfChatBO));
                }
            }
        });

        Boolean flag1=false;
        while (!flag1){
            log.info("========");
            Thread.sleep(10000);
        }

    }


    public  ThreadPoolTaskExecutor getExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        return executor;
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }

    @Override
    public void initData() {
        kfConnTest.initData();
        webSocketClientCustom=kfConnTest.getWebSocketClientCustom();
    }

    @Override
    public void destroyData() {

    }
}
