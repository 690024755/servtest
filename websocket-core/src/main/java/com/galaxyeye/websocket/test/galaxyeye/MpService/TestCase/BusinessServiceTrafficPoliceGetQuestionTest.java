package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 17:56
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/6日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceAnswerQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceGetQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceSaveQuestioNavatarBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceTrafficPoliceGetQuestionDTO;
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

@Slf4j
@Component
public class BusinessServiceTrafficPoliceGetQuestionTest extends BaseTest {
    @Autowired
    private TrafficpoliceQuestionUserRepository trafficpoliceQuestionUserRepository;

    @Autowired
    private BusinessServiceTrafficPoliceSaveQuestioNavatarTest businessServiceTrafficPoliceSaveQuestioNavatarTest;

    @Autowired
    private BusinessServiceTrafficPoliceAnswerQuestionTest businessServiceTrafficPoliceAnswerQuestionTest;


    /**
     * 通用获取答题信息
     * @throws Exception
     */
    public String businessServiceTrafficPoliceGetQuestionTestByGernal(BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO) {
        String businessServiceTrafficPoliceGetQuestionUrl = null;
        String businessServiceTrafficPoliceGetQuestionResult = "";

        try {
            businessServiceTrafficPoliceGetQuestionUrl = url + "/BusinessService/trafficpolice/getquestion";
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceGetQuestionUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionUrl);
            log.info("businessServiceTrafficPoliceGetQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionResult 返回结果=" + businessServiceTrafficPoliceGetQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增用户首次获取答题信息,参数Reanswer=true");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("question"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("idStr"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("id"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("type"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("statement"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("image"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("option"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("right"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("isFinished"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("beat"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("rank"), true);
            return businessServiceTrafficPoliceGetQuestionResult;
        }
    }


    /**
     * 新增用户首次获取答题信息,参数Reanswer=true
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceGetQuestionTestByNewUserAndParameterReanswerValueIsTrue() {
        destroyData();
        String businessServiceTrafficPoliceGetQuestionUrl = null;
        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = null;
        String businessServiceTrafficPoliceGetQuestionResult = "";
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);
        try {
            businessServiceTrafficPoliceGetQuestionUrl = url + "/BusinessService/trafficpolice/getquestion";
            businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceGetQuestionUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionUrl);
            log.info("businessServiceTrafficPoliceGetQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionResult 返回结果=" + businessServiceTrafficPoliceGetQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增用户首次获取答题信息,参数Reanswer=true");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("question"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"idStr\":\"1\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"id\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("type"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("statement"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("image"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("option"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"qcount\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"right\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"complete\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"isFinished\":false"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"beat\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"rank\":0"), true);
        }
    }

    /**
     * 新增用户首次获取答题信息,参数Reanswer=false
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceGetQuestionTestByNewUserAndParameterReanswerValueIsFalse() {
        destroyData();
        String businessServiceTrafficPoliceGetQuestionUrl = null;
        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = null;
        String businessServiceTrafficPoliceGetQuestionResult = "";
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);
        try {
            businessServiceTrafficPoliceGetQuestionUrl = url + "/BusinessService/trafficpolice/getquestion";
            businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceGetQuestionUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionUrl);
            log.info("businessServiceTrafficPoliceGetQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionResult 返回结果=" + businessServiceTrafficPoliceGetQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增用户首次获取答题信息,参数Reanswer=false");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("question"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"idStr\":\"1\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"id\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("type"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("statement"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("image"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("option"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"qcount\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"right\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"complete\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"isFinished\":false"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"beat\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"rank\":0"), true);
        }
    }

    /**
     * 新增用户首次获取答题信息,参数Reanswer=test
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceGetQuestionTestByNewUserAndParameterReanswerValueIsTest() {
        destroyData();
        String businessServiceTrafficPoliceGetQuestionUrl = null;
        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = null;
        String businessServiceTrafficPoliceGetQuestionResult = "";
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);
        try {
            businessServiceTrafficPoliceGetQuestionUrl = url + "/BusinessService/trafficpolice/getquestion";
            businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("test");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceGetQuestionUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionUrl);
            log.info("businessServiceTrafficPoliceGetQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionResult 返回结果=" + businessServiceTrafficPoliceGetQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增用户首次获取答题信息,参数Reanswer=test");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("question"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"idStr\":\"1\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"id\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("type"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("statement"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("image"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("option"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"qcount\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"right\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"complete\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"isFinished\":false"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"beat\":0"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"rank\":0"), true);
        }
    }


    /**
     * 不存在用户获取答题信息
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceGetQuestionTestByNotExistUser() {
        String businessServiceTrafficPoliceGetQuestionUrl = null;
        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = null;
        String businessServiceTrafficPoliceGetQuestionResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            businessServiceTrafficPoliceGetQuestionUrl = url + "/BusinessService/trafficpolice/getquestion";
            businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid").concat("0011"));
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceGetQuestionUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionUrl);
            log.info("businessServiceTrafficPoliceGetQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionResult 返回结果=" + businessServiceTrafficPoliceGetQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不存在用户获取答题信息");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"msg\":\"traffic_user_notexist\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"result\":652"), true);
        }
    }




    /**
     * 用户已答1道题，用户再次获取答题信息
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceGetQuestionTestByAnswered() {
        destroyData();
        String businessServiceTrafficPoliceGetQuestionUrl = null;
        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = null;
        String businessServiceTrafficPoliceGetQuestionResult = "";
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid(hs.get("appid"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);
        try {
            businessServiceTrafficPoliceGetQuestionUrl = url + "/BusinessService/trafficpolice/getquestion";
            businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
            Long questionId=Long.valueOf(question.getIdStr());
            BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO=new BusinessServiceTrafficPoliceAnswerQuestionBO();
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(hs.get("uid")));
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(new ArrayList(){
                {add(100);}
            });
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
            businessServiceTrafficPoliceAnswerQuestionTest.businessServiceTrafficPoliceAnswerQuestionTestByGernal(businessServiceTrafficPoliceAnswerQuestionBO);
            log.info("businessServiceTrafficPoliceGetQuestionUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionUrl);
            log.info("businessServiceTrafficPoliceGetQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            businessServiceTrafficPoliceGetQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionResult 返回结果=" + businessServiceTrafficPoliceGetQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户已答1道题，用户再次获取答题信息");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("question"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"idStr\":\"2\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"id\":2"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("type"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("statement"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("image"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("option"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("right"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("\"isFinished\":false"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("beat"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionResult.contains("rank"), true);
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
        record.setNickname("yy");
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
