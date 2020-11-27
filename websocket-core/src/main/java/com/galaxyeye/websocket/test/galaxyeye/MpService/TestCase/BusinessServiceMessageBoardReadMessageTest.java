package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:48
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/23日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.ReadMessageBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;


@Component
@Slf4j
@Deprecated
public class BusinessServiceMessageBoardReadMessageTest extends BaseTest {

    @Autowired
    private BusinessServiceMessageBoardCreateMessageTest businessServiceMessageBoardCreateMessageTest;

    /**
     * 对未读的消息标识为已读消息,通用方法
     * @throws Exception
     */
    @Test
    @Deprecated
    public void readmessageTestByGernal(ReadMessageBO readMessageBO) {
        String readmessageUrl =null;
        String readmessageResult ="";
        try{
            readmessageUrl = url+"/BusinessService/messageboard/readmessage";
            log.info("readmessageUrl 请求的参数=" + readmessageUrl);
            log.info("readMessageBO 请求的参数=" + JSON.toJSONString(readMessageBO));
            readmessageResult = HttpUtil.postGeneralUrl(readmessageUrl, "application/json", JSON.toJSONString(readMessageBO), "UTF-8");
            log.info("readmessageResult 返回结果=" + JSON.parseObject(readmessageResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("对未读的消息标识为已读消息,通用方法");
            recordhttp.setUrl(readmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(readMessageBO));
            recordhttp.setResponse(readmessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(readmessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(readmessageResult.contains("\"msg\":\"ok\""),true);
        }
    }



    /**
     * 对未读的消息标识为已读消息
     * @throws Exception
     */
    @Test
    @Deprecated
    public void readmessageTestByReaded() {
        String readmessageUrl =null;
        ReadMessageBO readMessageBO =null;
        String readmessageResult ="";
        try{
            readmessageUrl = url+"/BusinessService/messageboard/readmessage";
            readMessageBO = new ReadMessageBO();
            readMessageBO.setId(String.valueOf(businessServiceMessageBoardCreateMessageTest.getMessageIDTest().getId()));
            readMessageBO.setAppid("1.00002");
            readMessageBO.setBmAppid("3.00014");
            readMessageBO.setAccessToken(bmAppids.get("3.00014"));
            log.info("readmessageUrl 请求的参数=" + readmessageUrl);
            log.info("readMessageBO 请求的参数=" + JSON.toJSONString(readMessageBO));
            readmessageResult = HttpUtil.postGeneralUrl(readmessageUrl, "application/json", JSON.toJSONString(readMessageBO), "UTF-8");
            log.info("readmessageResult 返回结果=" + JSON.parseObject(readmessageResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("对未读的消息标识为已读消息");
            recordhttp.setUrl(readmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(readMessageBO));
            recordhttp.setResponse(readmessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(readmessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(readmessageResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 已读的消息重复标识为已读，目前是允许重复阅读咨询者的留言信息
     * @throws Exception
     */
    @Test
    @Deprecated
    public void readmessageTestByRepeat() {
        String readmessageUrl =null;
        ReadMessageBO readMessageBO =null;
        String readmessageResult ="";
        try{
                readmessageUrl = url+"/BusinessService/messageboard/readmessage";
                readMessageBO = new ReadMessageBO();
                readMessageBO.setId(String.valueOf(businessServiceMessageBoardCreateMessageTest.getMessageIDTest().getId()));
                readMessageBO.setAppid("1.00002");
                readMessageBO.setBmAppid("3.00014");
                readMessageBO.setAccessToken("ae6013a81c61813b3d31a0d54ca8948fb4135216952d40cedc95bda8c5224452");
                log.info("readmessageUrl 请求的参数=" + readmessageUrl);
                log.info("readMessageBO 请求的参数=" + JSON.toJSONString(readMessageBO));
            for (int i = 0; i < 2; i++) {
                //每隔1秒阅读一次
                Thread.sleep(1000);
                readmessageResult = HttpUtil.postGeneralUrl(readmessageUrl, "application/json", JSON.toJSONString(readMessageBO), "UTF-8");
            }
            log.info("readmessageResult 返回结果=" + JSON.parseObject(readmessageResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已读的消息重复标识为已读，目前是允许重复阅读咨询者的留言信息");
            recordhttp.setUrl(readmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(readMessageBO));
            recordhttp.setResponse(readmessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(readmessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(readmessageResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 阅读一条不存在的消息
     * @throws Exception
     */
    @Test
    @Deprecated
    public void readmessageTestByNotExistMessage() {
        String readmessageUrl =null;
        ReadMessageBO readMessageBO =null;
        String readmessageResult ="";
        try{
            readmessageUrl = url+"/BusinessService/messageboard/readmessage";
            readMessageBO = new ReadMessageBO();
            readMessageBO.setId(String.valueOf(""));
            readMessageBO.setAppid("1.00002");
            readMessageBO.setBmAppid("3.00014");
            readMessageBO.setAccessToken(bmAppids.get("3.00014"));
            log.info("readmessageUrl 请求的参数=" + readmessageUrl);
            log.info("readMessageBO 请求的参数=" + JSON.toJSONString(readMessageBO));
            readmessageResult = HttpUtil.postGeneralUrl(readmessageUrl, "application/json", JSON.toJSONString(readMessageBO), "UTF-8");
            log.info("readmessageResult 返回结果=" + JSON.parseObject(readmessageResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("阅读一条不存在的消息");
            recordhttp.setUrl(readmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(readMessageBO));
            recordhttp.setResponse(readmessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(readmessageResult.contains("\"msg\":\"messageboard_notexist\""),true);
            Assert.assertEquals(readmessageResult.contains("\"result\":400"),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
