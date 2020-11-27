package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:15
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/23日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetMessaGeListBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
@Deprecated
public class BusinessServiceMessageBoardGetMessageListTest extends BaseTest {

    /**
     * 根据uid与token获取所有用户的咨询问题
     * @throws Exception
     */
    @Test
    @Deprecated
    public void getmessagelistTestByUidAndToken() throws Exception {
        String getmessagelistUrl = null;
        GetMessaGeListBO getMessaGeListBO = null;
        String getmessagelistResult = "";
        try {
            getmessagelistUrl = url + "/BusinessService/messageboard/getmessagelist";
            getMessaGeListBO = new GetMessaGeListBO();
            getMessaGeListBO.setAppid("1.00002");
            HashMap<String, String> hs = userLoginTest();
            getMessaGeListBO.setToken(hs.get("token"));
            getMessaGeListBO.setUid(hs.get("uid"));
            log.info("getmessagelistUrl 请求的参数=" + getmessagelistUrl);
            log.info("getMessaGeListBO 请求的参数=" + JSON.toJSONString(getMessaGeListBO));
            getmessagelistResult = HttpUtil.postGeneralUrl(getmessagelistUrl, "application/json", JSON.toJSONString(getMessaGeListBO), "UTF-8");
            log.info("getmessagelistResult 返回结果=" + JSON.parseObject(getmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token获取所有用户的咨询问题");
            recordhttp.setUrl(getmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getMessaGeListBO));
            recordhttp.setResponse(getmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getmessagelistResult.contains("list"), true);
            Assert.assertTrue(JSON.parseObject(getmessagelistResult).getJSONArray("list").size()>0);
        }
    }


    /**
     * 根据bmappid与accesstoken获取所有用户的咨询问题
     * @throws Exception
     */
    @Test
    @Deprecated
    public void getmessagelistTestByBmAppidAndAccessToken() throws Exception {
        String getmessagelistUrl = null;
        GetMessaGeListBO getMessaGeListBO = null;
        String getmessagelistResult = "";
        try {
            getmessagelistUrl = url + "/BusinessService/messageboard/getmessagelist";
            getMessaGeListBO = new GetMessaGeListBO();
            getMessaGeListBO.setAppid("1.00002");
            getMessaGeListBO.setBmAppid("3.00014");
            getMessaGeListBO.setAccessToken(bmAppids.get("3.00014"));
            log.info("getmessagelistUrl 请求的参数=" + getmessagelistUrl);
            log.info("getMessaGeListBO 请求的参数=" + JSON.toJSONString(getMessaGeListBO));
            getmessagelistResult = HttpUtil.postGeneralUrl(getmessagelistUrl, "application/json", JSON.toJSONString(getMessaGeListBO), "UTF-8");
            log.info("getmessagelistResult 返回结果=" + JSON.parseObject(getmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据bmappid与accesstoken获取所有用户的咨询问题");
            recordhttp.setUrl(getmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getMessaGeListBO));
            recordhttp.setResponse(getmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getmessagelistResult.contains("list"), true);
            Assert.assertTrue(JSON.parseObject(getmessagelistResult).getJSONArray("list").size()>0);
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
