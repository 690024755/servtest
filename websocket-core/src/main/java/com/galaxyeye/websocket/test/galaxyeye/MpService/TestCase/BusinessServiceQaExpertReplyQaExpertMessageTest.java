package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 14:37
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TbProAccountRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.ReplyQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class BusinessServiceQaExpertReplyQaExpertMessageTest  extends CIcServiceResource implements IQaExpertResource {

    @Autowired
    private BusinessServiceQaExpertCreateQaExpertMessageTest businessServiceQaExpertCreateQaExpertMessageTest;

    /*
    通用性回复咨询者信息
     */
    public void ReplyQaExpertMessageTestByGernal(ReplyQaExpertMessageBO replyQaExpertMessageBO) throws Exception {
        String replyqaexpertmessageUrl = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            replyQaExpertMessageBO.setNickname(replyQaExpertMessageBO.getNickname());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(replyQaExpertMessageBO.getContent());
            replyQaExpertMessageBO.setQaId(replyQaExpertMessageBO.getQaId());
            replyQaExpertMessageBO.setReplyType(replyQaExpertMessageBO.getReplyType());
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用性回复咨询者信息");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 专家使用BmAppid与AccessToken给咨询者进行留言
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByBmAppidAndAccessToken() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(getExpertContent());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken给咨询者进行留言");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 参数Content留言内容为空
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterContentValueIsEmpty() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent("");
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Content留言内容为空");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"content is empty\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 给不存在的qaid进行留言
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterQaIdValueIsNotExist() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId()+"1");
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("给不存在的qaid进行留言");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"QAboard_notexist\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":500"), true);
        }
    }

    /**
     * 非专家用户留言与普通用户留言，设置type=5,提示信息“data_error”
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterTypeValueIsFive() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(5);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非专家用户留言与普通用户留言，设置type=5,提示信息“data_error”");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"replyType has wrong value\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 普通用户留言，设置ReplyType=2
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterTypeValueIsTwo() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            replyQaExpertMessageBO.setContent(getCommonContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getCommonUid()));
            replyQaExpertMessageBO.setNickname(getCommonCid());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(2);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户留言，设置type=2");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 专家留言，设置ReplyType=1
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterTypeValueIsOne() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");

            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("专家留言，设置ReplyType=1");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 留言消息内容太长
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterContentValueIsLarge() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(imgParam);
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("留言消息内容太长");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 必填参数Content校验
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByNotExistParameterContent() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
//            replyQaExpertMessageBO.setContent(expertContent);
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Content校验");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"content is missing\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 非必填参数ReplyType校验，不填默认后端设置为1
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByNotExistParameterReplyType() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
//            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数ReplyType校验，不填默认后端设置为1");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 必填参数QaId校验
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByNotExistParameterQaId() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");

            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
//            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数QaId校验");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数nickname校验
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByNotExistParameterNickname() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
//            replyQaExpertMessageBO.setNickname(expertCid);
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数nickname校验");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"nickname is empty\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数nickname的值为空
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterNicknameValueIsEmpty() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setNickname("");
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数nickname的值为空");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"nickname is empty\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 回复的消息Content内容是表情
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByParameterContentValueIsEmotion() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
//        replyQaExpertMessageBO.setToken(hs.get("token"));
//        replyQaExpertMessageBO.setUid(hs.get("uid"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            replyQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(str2);
            replyQaExpertMessageBO.setUid(String.valueOf(getExpertUid()));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("回复的消息Content内容是表情");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 专家使用uid与token进行回复留言信息
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByUidAndToken() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        String keyValue="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";
        userLoginBO.setUname(keyValue);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("4.00047");
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setUnionid(keyValue);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        HashMap<String, String> expertHs= userLoginTest(userLoginBO);
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setToken(expertHs.get("token"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            replyQaExpertMessageBO.setContent(getCommonContent());
            replyQaExpertMessageBO.setUid(expertHs.get("uid"));
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token进行回复留言信息");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }


    /**
     * 非必填参数uid校验
     * @throws Exception
     */
    @Test
    public void ReplyQaExpertMessageTestByNotExistParameterUid() throws Exception {
        String replyqaexpertmessageUrl = null;
        ReplyQaExpertMessageBO replyQaExpertMessageBO = null;
        String replyqaexpertmessageResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        String keyValue="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";
        userLoginBO.setUname(keyValue);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("4.00047");
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setUnionid(keyValue);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        HashMap<String, String> expertHs= userLoginTest(userLoginBO);
        try {
            replyqaexpertmessageUrl = url + "/BusinessService/qaexpert/replyqaexpertmessage";
            replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setToken(expertHs.get("token"));
            replyQaExpertMessageBO.setAppid("1.00002");
            replyQaExpertMessageBO.setBmAppid("1.00002");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
//            replyQaExpertMessageBO.setUid(expertHs.get("uid"));
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setNickname(getExpertCid());
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            replyQaExpertMessageBO.setQaId(businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId());
            replyQaExpertMessageBO.setReplyType(1);
            log.info("replyqaexpertmessageUrl 请求的参数=" + replyqaexpertmessageUrl);
            log.info("replyQaExpertMessageBO 请求的参数=" + JSON.toJSONString(replyQaExpertMessageBO));
            replyqaexpertmessageResult = HttpUtil.postGeneralUrl(replyqaexpertmessageUrl, "application/json", JSON.toJSONString(replyQaExpertMessageBO), "UTF-8");
            log.info("replyqaexpertmessageResult 返回结果=" + JSON.parseObject(replyqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数uid校验");
            recordhttp.setUrl(replyqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyQaExpertMessageBO));
            recordhttp.setResponse(replyqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replyqaexpertmessageResult.contains("id"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

}
