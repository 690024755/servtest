package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 9:59
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.SendImageUrlBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Slf4j
@Component
public class BusinessServiceTrafficPoliceSendImageUrlTest extends BaseTest {

    @Autowired
    private UploadImageFormTest uploadImageFormTest;

    public void sendimageurlTest(String returnUrl) throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
            urls.add(returnUrl);
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常测试，提问时候发送图片");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("\"result\":1"), true);
            Assert.assertEquals(sendimageurlResult.contains("\"msg\":\"ok\""), true);
        }
    }


    /**
     * 链接地址为空
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByEmptyUrls() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("urls链接地址为空");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("urls is empty"), true);
        }
    }

    /**
     * 参数urls不传
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByNotParameterUrls() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
//        sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数urls不传");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("urls is empty"), true);
        }
    }

    /**
     * 链接地址不为空
     *
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByNotEmptyUrls() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
            urls.add(uploadImageFormTest.getImageUrl());
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("urls链接地址不为空");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("\"result\":1"), true);
            Assert.assertEquals(sendimageurlResult.contains("\"msg\":\"ok\""), true);
        }
    }


    /**
     * 链接地址不为空
     *
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByNotEmptyUrlsByOrder() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
            urls.add(uploadImageFormTest.getImageUrl());
            urls.add(uploadImageFormTest.getImageUrl());
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("urls链接地址不为空");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("\"result\":1"), true);
            Assert.assertEquals(sendimageurlResult.contains("\"msg\":\"ok\""), true);
        }
    }


    /**
     * RoomId不存在
     *
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByNotExistRoomId() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            //设置不存在的RoomId
            sendImageUrlBO.setRoomId("===");
            List<String> urls = new ArrayList<>();
            urls.add(uploadImageFormTest.getImageUrl());
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数RoomId不存在");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("roomId is zero"), true);
        }
    }

    /**
     * 使用bmAppid与accessToken参数，提问发送图片接口
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByBmAppidAndAccessToken() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
//        sendImageUrlBO.setToken(hs.get("token"));
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
            urls.add(uploadImageFormTest.getImageUrl());
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用bmAppid与accessToken参数，提问发送图片接口");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("\"result\":1"), true);
            Assert.assertEquals(sendimageurlResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 使用uid与token参数，提问发送图片接口
     * @throws Exception
     */
    @Test
    public void sendimageurlTestByUidAndToken() throws Exception {
        String sendimageurlUrl = null;
        SendImageUrlBO sendImageUrlBO = null;
        String sendimageurlResult = "";
        try {
            sendimageurlUrl = url + "/BusinessService/trafficpolice/sendimageurl";
            sendImageUrlBO = new SendImageUrlBO();
            HashMap<String, String> hs = userLoginTest();
            sendImageUrlBO.setAppid("1.00002");
            sendImageUrlBO.setBmAppid("1.00002");
            sendImageUrlBO.setUid(Long.valueOf(hs.get("uid")));
            sendImageUrlBO.setToken(hs.get("token"));
            //设置不存在的RoomId
            sendImageUrlBO.setRoomId("1218074641600352256");
            List<String> urls = new ArrayList<>();
            urls.add(uploadImageFormTest.getImageUrl());
            sendImageUrlBO.setUrls(urls);
            log.info("sendimageurlUrl 请求的参数=" + sendimageurlUrl);
            log.info("sendImageUrlBO 请求的参数=" + JSON.toJSONString(sendImageUrlBO));
            sendimageurlResult = HttpUtil.postGeneralUrl(sendimageurlUrl, "application/json", JSON.toJSONString(sendImageUrlBO), "UTF-8");
            log.info("sendimageurlResult 返回结果=" + sendimageurlResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token参数，提问发送图片接口");
            recordhttp.setUrl(sendimageurlUrl);
            recordhttp.setRequest(JSON.toJSONString(sendImageUrlBO));
            recordhttp.setResponse(sendimageurlResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(sendimageurlResult.contains("\"result\":1"), true);
            Assert.assertEquals(sendimageurlResult.contains("\"msg\":\"ok\""), true);
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
