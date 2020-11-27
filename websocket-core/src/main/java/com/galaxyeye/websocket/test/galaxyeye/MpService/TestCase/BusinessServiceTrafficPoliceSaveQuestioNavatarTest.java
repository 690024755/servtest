package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:19
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceSaveQuestioNavatarBO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class BusinessServiceTrafficPoliceSaveQuestioNavatarTest extends BaseTest {

    @Autowired
    private TrafficpoliceQuestionUserRepository trafficpoliceQuestionUserRepository;


    /**
     * 交规答题获取用户信息
     * @throws Exception
     */
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO) {
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规答题获取用户信息");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 不存在用户保存用户,则新增一条流水记录
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByNotExistUser() {
        destroyData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        HashMap<String, String> hs=userLoginTest();
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子1");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不存在用户保存用户,则新增一条流水记录");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> list= trafficpoliceQuestionUserRepository.selectByExample(example);
            TrafficpoliceQuestionUser trafficpoliceQuestionUser=list.get(0);
            //用户首次答题时，初始化数据均为0
            Assert.assertTrue(trafficpoliceQuestionUser.getQid().equals(0));
            Assert.assertTrue(trafficpoliceQuestionUser.getQidToanswer().equals(0));
            Assert.assertTrue(trafficpoliceQuestionUser.getQcount().equals(0));
            Assert.assertTrue(trafficpoliceQuestionUser.getRight().equals(0));
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
        }
    }


    /**
     * 已存在的uid用户，修改uid属于新增用户
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUser() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已存在的uid用户，修改uid属于新增用户");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 必填参数Avatar校验
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByNotExistParameterAvatar() {
        destroyData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
//            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Avatar校验");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"avatar is empty\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":101"), true);
        }
    }

    /**
     * Avatar为空格
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByNotExistUserAndParameterAvatarValueIsTable() {
        destroyData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("    ");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Avatar为空格");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 已存在的uid用户，可以修改用户的头像信息
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndModifyAvatar() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("http://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已存在的uid用户，可以修改用户的头像信息");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 已存在的uid用户，可以修改用户的昵称信息
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndModifyNickname() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子测试");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已存在的uid用户，可以修改用户的昵称信息");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 已存在的uid用户，修改用户的昵称过长
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndModifyNicknameValueIsLong() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname(imgParam);
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已存在的uid用户，修改用户的昵称过长");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 已存在的uid用户，修改用户的昵称属于表情
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndModifyNicknameValueIsEmotion() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已存在的uid用户，修改用户的昵称属于表情");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 修改微信头像为gif
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndAvatarIsGif() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://image.so.com/view?q=gif%E5%9B%BE%E7%89%87&listsrc=sobox&listsign=e26363ae8c44ffdd82d39736efb9da8a&src=360pic_strong&correct=gif%E5%9B%BE%E7%89%87&ancestor=list&cmsid=ea6af6b2a296222aaabd45b732838d45&cmras=6&cn=0&gn=0&kn=31&crn=0&bxn=10&fsn=101&cuben=0&adstar=0&clw=247#id=e26363ae8c44ffdd82d39736efb9da8a&currsn=0&ps=88&pc=88");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子测试");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改微信头像为gif");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 修改微信头像为jpg
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndAvatarIsJpg() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://image.so.com/view?q=jpg%E5%9B%BE%E7%89%87&src=srp&correct=jpg%E5%9B%BE%E7%89%87&ancestor=list&cmsid=185bd595e40cd70118054af4aa9e4116&cmras=0&cn=0&gn=0&kn=47&crn=0&bxn=10&fsn=117&cuben=0&adstar=0&clw=247#id=eaeb449682344c0040f19c8ab8d98a12&currsn=0&ps=105&pc=105");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子测试");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改微信头像为jpg");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 修改微信头像为png
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceSaveQuestioNavatarTestByExistUserAndAvatarIsPng() {
        destroyData();
        initData();
        String businessServiceTrafficPoliceSaveQuestioNavatarUrl = null;
        String businessServiceTrafficPoliceSaveQuestioNavatarResult = "";
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=null;
        try {
            businessServiceTrafficPoliceSaveQuestioNavatarUrl = url + "/BusinessService/trafficpolice/savequestionavatar";
            businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            HashMap<String, String> hs=userLoginTest();
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://image.so.com/view?q=png%E5%9B%BE%E7%89%87&src=srp&correct=png%E5%9B%BE%E7%89%87&ancestor=list&cmsid=6c922452d1d6946d32e4534fc9709154&cmras=0&cn=0&gn=0&kn=40&crn=0&bxn=10&fsn=110&cuben=0&adstar=0&clw=247#id=c9eba6af4a36d37c69edcb630d47eef3&currsn=0&ps=88&pc=88");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子测试");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarUrl 请求的参数=" + businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionUrlBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            businessServiceTrafficPoliceSaveQuestioNavatarResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO), "UTF-8");
            log.info("businessServiceTrafficPoliceSaveQuestioNavatarResult 返回结果=" + businessServiceTrafficPoliceSaveQuestioNavatarResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改微信头像为png");
            recordhttp.setUrl(businessServiceTrafficPoliceSaveQuestioNavatarUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceSaveQuestioNavatarBO));
            recordhttp.setResponse(businessServiceTrafficPoliceSaveQuestioNavatarResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceSaveQuestioNavatarResult.contains("\"result\":1"), true);
        }
    }




    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        destroyData();
        TrafficpoliceQuestionUser record=new TrafficpoliceQuestionUser();
        record.setUid(237671L);
        record.setAppid("1.00002");
        record.setNickname("大饼子1");
        record.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        record.setQid(0);
        record.setQidToanswer(0);
        record.setQcount(0);
        record.setRight(0);
        record.setAnswerAt(new Date());
        record.setCreatedAt(new Date());
        trafficpoliceQuestionUserRepository.insert(record);
    }

    @Override
    public void destroyData() {
        TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
        TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
        cr.andUidEqualTo(237671L);
        trafficpoliceQuestionUserRepository.deleteByExample(example);
    }

}
