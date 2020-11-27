package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 13:32
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/23日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetMessageBoxListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.ReadMessageBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
@Deprecated
public class BusinessServiceMessageBoardGetMessageBoxListTest extends BaseTest {
    @Autowired
    private BusinessServiceMessageBoardCreateMessageTest businessServiceMessageBoardCreateMessageTest;

    @Autowired
    private BusinessServiceMessageBoardReadMessageTest businessServiceMessageBoardReadMessageTest;


    /**
     * 根据uid与token获取咨询者个人留言信息
     * @throws Exception
     */
    @Test
    @Deprecated
    public void getmessageboxlistTestByUidAndToken() throws Exception {

        String getmessageboxlistUrl =null;
        GetMessageBoxListBO getMessageBoxListBO =null;
        String getmessageboxlistResult ="";
        try{
            getmessageboxlistUrl = url+"/BusinessService/messageboard/getmessageboxlist";
            getMessageBoxListBO = new GetMessageBoxListBO();
            getMessageBoxListBO.setAppid("1.00002");
            getMessageBoxListBO.setBmAppid("3.00014");
            HashMap<String,String> hs=userLoginTest();
            getMessageBoxListBO.setToken(hs.get("token"));
            getMessageBoxListBO.setUid(hs.get("uid"));
            log.info("getmessageboxlistUrl 请求的参数=" + getmessageboxlistUrl);
            log.info("getMessageBoxListBO 请求的参数=" + JSON.toJSONString(getMessageBoxListBO));
            getmessageboxlistResult = HttpUtil.postGeneralUrl(getmessageboxlistUrl, "application/json", JSON.toJSONString(getMessageBoxListBO), "UTF-8");
            log.info("getmessageboxlistResult 返回结果=" + JSON.parseObject(getmessageboxlistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token获取咨询者个人留言信息");
            recordhttp.setUrl(getmessageboxlistUrl);
            recordhttp.setRequest(JSON.toJSONString(getMessageBoxListBO));
            recordhttp.setResponse(getmessageboxlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getmessageboxlistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getmessageboxlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getmessageboxlistResult.contains("list"),true);
            JSONObject jSONObject= (JSONObject)JSON.parseObject(getmessageboxlistResult).getInnerMap().get("list");
            Assert.assertTrue(jSONObject.getJSONArray("notRead").size()>0);
            Assert.assertTrue(jSONObject.getJSONArray("read").size()>0);
            Assert.assertEquals(getmessageboxlistResult.contains("uid"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("createdAt"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("contact"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("appid"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("nickname"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("id"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("title"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("readAt"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("content"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("status"),true);
        }
    }

    /**
     * 根据bmappid与AccessToken获取咨询者个人留言信息
     * @throws Exception
     */
    @Test
    @Deprecated
    public void getmessageboxlistTestByBmAppidAndAccessToken() throws Exception {
        String getmessageboxlistUrl =null;
        GetMessageBoxListBO getMessageBoxListBO =null;
        String getmessageboxlistResult ="";
        try{
            getmessageboxlistUrl = url+"/BusinessService/messageboard/getmessageboxlist";
            getMessageBoxListBO = new GetMessageBoxListBO();
            getMessageBoxListBO.setAppid("1.00002");
            getMessageBoxListBO.setAccessToken(bmAppids.get("3.00014"));
            getMessageBoxListBO.setBmAppid("3.00014");
            log.info("getmessageboxlistUrl 请求的参数=" + getmessageboxlistUrl);
            log.info("getMessageBoxListBO 请求的参数=" + JSON.toJSONString(getMessageBoxListBO));
            getmessageboxlistResult = HttpUtil.postGeneralUrl(getmessageboxlistUrl, "application/json", JSON.toJSONString(getMessageBoxListBO), "UTF-8");
            log.info("getmessageboxlistResult 返回结果=" + JSON.parseObject(getmessageboxlistResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token获取咨询者个人留言信息");
            recordhttp.setUrl(getmessageboxlistUrl);
            recordhttp.setRequest(JSON.toJSONString(getMessageBoxListBO));
            recordhttp.setResponse(getmessageboxlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getmessageboxlistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getmessageboxlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getmessageboxlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getmessageboxlistResult.contains("list"),true);
            JSONObject jSONObject= (JSONObject)JSON.parseObject(getmessageboxlistResult).getInnerMap().get("list");
            Assert.assertTrue(jSONObject.getJSONArray("notRead").size()>0);
            Assert.assertTrue(jSONObject.getJSONArray("read").size()>0);
        }
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        ReadMessageBO readMessageBO = new ReadMessageBO();
        readMessageBO.setId(String.valueOf(businessServiceMessageBoardCreateMessageTest.getMessageIDTest().getId()));
        readMessageBO.setAppid("1.00002");
        readMessageBO.setBmAppid("3.00014");
        readMessageBO.setAccessToken(bmAppids.get("3.00014"));
        businessServiceMessageBoardReadMessageTest.readmessageTestByGernal(readMessageBO);


    }

    @Override
    public void destroyData() {

    }
}
