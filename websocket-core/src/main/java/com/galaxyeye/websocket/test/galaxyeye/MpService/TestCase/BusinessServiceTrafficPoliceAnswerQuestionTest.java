package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:19
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionRepository;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestion;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceAnswerQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceGetQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceSaveQuestioNavatarBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceTrafficPoliceGetQuestionDTO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;


@Slf4j
@Component
public class BusinessServiceTrafficPoliceAnswerQuestionTest extends BaseTest {


    public  Map<Long,String> QuestionIdAndAnswer=new HashMap<>();

    @Autowired
    private BusinessServiceTrafficPoliceGetQuestionTest businessServiceTrafficPoliceGetQuestionTest;

    @Autowired
    private TrafficpoliceQuestionRepository trafficpoliceQuestionRepository;

    @Autowired
    private TrafficpoliceQuestionUserRepository trafficpoliceQuestionUserRepository;

    @Autowired
    private BusinessServiceTrafficPoliceSaveQuestioNavatarTest businessServiceTrafficPoliceSaveQuestioNavatarTest;


    /**
     * 用户答题通用方法
     * @throws Exception
     */
    public String businessServiceTrafficPoliceAnswerQuestionTestByGernal(BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO) {
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户答题通用方法");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            return businessServiceTrafficPoliceAnswerQuestionResult;
        }
    }

    /**
     * 必填参数Answer校验
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByNotExistParameterAnswer() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid(hs.get("appid"));
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
//            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Answer校验");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"answer is empty\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数id校验
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByNotExistParameterId() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
//            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数id校验");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"question id is empty\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 获取不存在的问题id
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByParameterIdValueIsNotExist() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId).concat("9999")));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取不存在的问题id");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"traffic_question_not_exist\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":657"), true);
        }
    }

    /**
     * 用户回答的问题id不匹配
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByParameterIdValueIsNotMatch() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId))+1);
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户回答的问题id不匹配");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"traffic_question_not_match\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":658"), true);
        }
    }

    /**
     * 已答过的题目，用户重复答题
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByRepeatAnswer() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            for (int i = 0; i <2 ; i++) {
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            }
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已答过的题目，用户重复答题");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"traffic_question_not_match\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":658"), true);
        }
    }


    /**
     * 交规知识答题,用户的答案为空
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByParameterAnswerValueIsEmpty() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(new ArrayList<>());
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("规知识答题,用户的答案为空");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"answer is empty\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 交规知识答题,用户答了一到题
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByAnswerOneQuestion() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);

        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
            businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();

            Long questionId=Long.valueOf(question.getIdStr());
            String rightAnswer=QuestionIdAndAnswer.get(questionId);
            List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
            businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
            businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
            businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
            businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
            businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
            log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
            log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户答了一到题");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
        }
    }

    /**
     * 交规知识答题,用户循环回答问题,5题全部答对
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion1() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            for (int i = 0; i < 5; i++) {
                String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
                BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                Long questionId=Long.valueOf(question.getIdStr());
                String rightAnswer=QuestionIdAndAnswer.get(questionId);
                List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
                businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                String filePath = getFilePath("3.jpg");
                String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户循环回答问题,5题全部答对");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.qcount").equals(5));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.complete").equals(5));
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> result=trafficpoliceQuestionUserRepository.selectByExample(example);
            Assert.assertTrue(result.get(0).getRight().equals(5));

        }
    }

    /**
     * 交规知识答题,用户循环回答问题,5题中3题答对
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion2() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            for (int i = 0; i < 5; i++) {
                String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
                BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                Long questionId=Long.valueOf(question.getIdStr());
                String rightAnswer=QuestionIdAndAnswer.get(questionId);
                List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
                if(i>=3){
                    answer.removeAll(answer);
                    answer.add(8);
                    businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                }else {
                    businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                }

                businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                String filePath = getFilePath("3.jpg");
                String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户循环回答问题,5题全部答对");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.qcount").equals(5));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.complete").equals(5));
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> result=trafficpoliceQuestionUserRepository.selectByExample(example);
            Assert.assertTrue(result.get(0).getRight().equals(3));
        }
    }

    /**
     * 交规知识答题,用户循环回答问题,答满100道题
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion3() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=null;
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            for (int i = 0; i < 100; i++) {
                businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
                BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                Long questionId=Long.valueOf(question.getIdStr());
                String rightAnswer=QuestionIdAndAnswer.get(questionId);
                List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);

                businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                String filePath = getFilePath("3.jpg");
                String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("规知识答题,用户循环回答问题,答满100道题");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.qcount").equals(100));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.complete").equals(100));
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> result=trafficpoliceQuestionUserRepository.selectByExample(example);
            Assert.assertTrue(result.get(0).getRight().equals(100));
            Assert.assertTrue(result.get(0).getQcount().equals(100));
            Assert.assertTrue(result.get(0).getQidToanswer().equals(0));
            Assert.assertTrue(result.get(0).getQid().equals(100));
        }
    }

    /**
     * 交规知识答题,用户循环回答问题,答满100道题，再次获取Reanswer=true返回的题目id应该是1
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion4() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=null;
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            for (int i = 0; i < 101; i++) {
                if(i==100 ){
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
                }
                businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
                BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                Long questionId=Long.valueOf(question.getIdStr());
                String rightAnswer=QuestionIdAndAnswer.get(questionId);
                List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);

                businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                String filePath = getFilePath("3.jpg");
                String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户循环回答问题,答满100道题，再次获取Reanswer=true返回的题目id应该是1");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.qcount").equals(1));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.complete").equals(1));
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> result=trafficpoliceQuestionUserRepository.selectByExample(example);
            Assert.assertTrue(result.get(0).getRight().equals(1));
        }
    }


    /**
     * 交规知识答题,用户循环回答问题,答满100道题，再次获取Reanswer=false返回的题目id应该是0
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion5() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=null;
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=null;
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        Long questionId=null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question=null;
        try {
            for (int i = 0; i < 101; i++) {
                if(i==100){
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                    businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                    businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                    question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                    if(question.getIdStr().isEmpty() && question.getId().equals(0)){
                        questionId=-1L;
                        break;
                    }
                }else {
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                    businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                    businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                    question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                    questionId=Long.valueOf(question.getIdStr());
                }
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();

                String rightAnswer=QuestionIdAndAnswer.get(questionId);
                List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);

                businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                String filePath = getFilePath("3.jpg");
                String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户循环回答问题,答满100道题，再次获取Reanswer=false返回的题目id应该是0");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);

            Assert.assertTrue(question.getIsFinished());

            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.qcount").equals(100));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.complete").equals(100));
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> result=trafficpoliceQuestionUserRepository.selectByExample(example);
            Assert.assertTrue(result.get(0).getRight().equals(100));
        }
    }

    /**
     * 交规知识答题,用户循环回答问题,答满100道题，再次获取Reanswer=test返回的题目id应该是0
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion6() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceGetQuestionResult=null;
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=null;
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        Long questionId=null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        try {
            for (int i = 0; i < 101; i++) {
                if(i==100){
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("test");
                    businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                    businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                    BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                    if(question.getIdStr().isEmpty() && question.getId().equals(0)){
                        questionId=-1L;
                        break;
                    }
                }else {
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                    businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                    businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                    BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                    questionId=Long.valueOf(question.getIdStr());
                }
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();

                String rightAnswer=QuestionIdAndAnswer.get(questionId);
                List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);

                businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                String filePath = getFilePath("3.jpg");
                String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户循环回答问题,答满100道题，再次获取Reanswer=false返回的题目id应该是0");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.qcount").equals(100));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceAnswerQuestionResult, "$.answer.complete").equals(100));
            TrafficpoliceQuestionUserExample example=new TrafficpoliceQuestionUserExample();
            TrafficpoliceQuestionUserExample.Criteria cr=example.createCriteria();
            cr.andUidEqualTo(Long.valueOf(hs.get("uid")));
            List<TrafficpoliceQuestionUser> result=trafficpoliceQuestionUserRepository.selectByExample(example);
            Assert.assertTrue(result.get(0).getRight().equals(100));
        }
    }


    /**
     * 交规知识答题,用户循环回答问题,答到第2题，把第三题删除
     * @throws Exception
     */
    @Test
    public void businessServiceTrafficPoliceAnswerQuestionTestByDeleteQuestion() {
        HashMap<String, String> hs=userLoginTest();
        BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO=new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceSaveQuestioNavatarTest.destroyData();
        businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);

        BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO=new BusinessServiceTrafficPoliceGetQuestionBO();
        businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
        businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
        businessServiceTrafficPoliceGetQuestionBO.setAppid("1.00002");
        businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        String businessServiceTrafficPoliceAnswerQuestionResult_delete = "";
        Long questionId=null;
        try {
            for (int i = 0; i < 5; i++) {
                String businessServiceTrafficPoliceGetQuestionResult=businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO=JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();
                BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question= businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                questionId=Long.valueOf(question.getIdStr());
                if(questionId.equals(Long.parseLong("2"))){
                    deleteQuestion(questionId);
                    String rightAnswer=QuestionIdAndAnswer.get(questionId);
                    List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
                    businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                    businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                    businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                    businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                    businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                    String filePath = getFilePath("3.jpg");
                    String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                    log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                    log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                    businessServiceTrafficPoliceAnswerQuestionResult_delete = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                    log.info("businessServiceTrafficPoliceAnswerQuestionResult_delete 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult_delete);
                    restoreDeleteQuestion(questionId);
                    break;
                }else {
                    String rightAnswer=QuestionIdAndAnswer.get(questionId);
                    List<Integer> answer=JSON.parseArray(rightAnswer,Integer.class);
                    businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                    businessServiceTrafficPoliceAnswerQuestionBO.setAppid("1.00002");
                    businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                    businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                    businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                    String filePath = getFilePath("3.jpg");
                    String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
                    log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                    log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                    businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                    log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交规知识答题,用户循环回答问题,答到第2题，把第三题删除");
            recordhttp.setUrl(businessServiceTrafficPoliceAnswerQuestionUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
            recordhttp.setResponse(businessServiceTrafficPoliceAnswerQuestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("complete"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("qcount"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("rightAnswer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("result"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult.contains("answer"), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult_delete.contains("\"msg\":\"traffic_question_not_exist\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceAnswerQuestionResult_delete.contains("\"result\":657"), true);
        }
    }

    /**
     * 根据问题id，获取问题答案
     * @return
     */
    private void getRightAnswer(){
        if(QuestionIdAndAnswer.size()==0){
            List<TrafficpoliceQuestion> list=trafficpoliceQuestionRepository.selectAll();
            for (TrafficpoliceQuestion trafficpoliceQuestion:list
            ) {
                QuestionIdAndAnswer.put(trafficpoliceQuestion.getId(),trafficpoliceQuestion.getAnswer());
            }
        }
    }

    /**
     * @param id  问题id
     */
    private void deleteQuestion(Long id){
        TrafficpoliceQuestion record=new TrafficpoliceQuestion();
        record.setDeletedAt(new Date());
        TrafficpoliceQuestionExample example=new TrafficpoliceQuestionExample();
        TrafficpoliceQuestionExample.Criteria cr=example.createCriteria();
        cr.andIdEqualTo(id);
        trafficpoliceQuestionRepository.updateByExampleSelective(record,example);
    }

    /**
     * @param id  问题id
     */
    private void restoreDeleteQuestion(Long id){
        TrafficpoliceQuestion record=new TrafficpoliceQuestion();
        record.setDeletedAt(null);
        record.setId(id);
        TrafficpoliceQuestionExample example=new TrafficpoliceQuestionExample();
        TrafficpoliceQuestionExample.Criteria cr=example.createCriteria();
        cr.andIdEqualTo(id);
        trafficpoliceQuestionRepository.updateByExampleSelective(record,example);
    }

    /**
     */
    private void restoreAllDeleteTimeIsNullQuestion(){
        trafficpoliceQuestionRepository.updateByAllDeleteTimeIsNull();
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        getRightAnswer();
        restoreAllDeleteTimeIsNullQuestion();
    }

    @Override
    public void destroyData() {

    }

}
