package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 18:08
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/6日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionRepository;
import com.galaxyeye.websocket.application.repository.TrafficpoliceQuestionUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceAnswerQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceGetQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceGetQuestionRankBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceTrafficPoliceSaveQuestioNavatarBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceTrafficPoliceGetQuestionDTO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@Component
public class BusinessServiceTrafficPoliceGetQuestionRankTest extends BusinessServiceTrafficPoliceAnswerQuestionTest {
    private List<HashMap<String, String>> genernateUserDataList = new ArrayList<>();

    @Autowired
    private BusinessServiceTrafficPoliceGetQuestionTest businessServiceTrafficPoliceGetQuestionTest;

    @Autowired
    private TrafficpoliceQuestionRepository trafficpoliceQuestionRepository;

    @Autowired
    private TrafficpoliceQuestionUserRepository trafficpoliceQuestionUserRepository;

    @Autowired
    private BusinessServiceTrafficPoliceSaveQuestioNavatarTest businessServiceTrafficPoliceSaveQuestioNavatarTest;

    @Autowired
    private BusinessServiceTrafficPoliceAnswerQuestionTest businessServiceTrafficPoliceAnswerQuestionTest;

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private JedisTemplate jedisTemplate;

    /**
     * 获取排行榜的通用方法
     *
     * @param businessServiceTrafficPoliceGetQuestionRankBO
     */
    public String BusinessServiceTrafficPoliceGetQuestionRankTestByGernal(BusinessServiceTrafficPoliceGetQuestionRankBO businessServiceTrafficPoliceGetQuestionRankBO) {
        String businessServiceTrafficPoliceGetQuestionRankUrl = null;
        String businessServiceTrafficPoliceGetQuestionRankResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            businessServiceTrafficPoliceGetQuestionRankUrl = url + "/BusinessService/trafficpolice/getquestionrank";
            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取排行榜的通用方法");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult);
            initLog(recordhttp, new Object() {
            });
            return businessServiceTrafficPoliceGetQuestionRankResult;
        }
    }


    /**
     * 无数据，获取用户的排行榜信息
     *
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceGetQuestionRankTestByNotExistUser() {
        initData();
        String businessServiceTrafficPoliceGetQuestionRankUrl = null;
        BusinessServiceTrafficPoliceGetQuestionRankBO businessServiceTrafficPoliceGetQuestionRankBO = null;
        String businessServiceTrafficPoliceGetQuestionRankResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            businessServiceTrafficPoliceGetQuestionRankUrl = url + "/BusinessService/trafficpolice/getquestionrank";
            businessServiceTrafficPoliceGetQuestionRankBO = new BusinessServiceTrafficPoliceGetQuestionRankBO();
            businessServiceTrafficPoliceGetQuestionRankBO.setAppid("1.00002");
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(hs.get("uid"));
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("表trafficpolice_question_user无数据，获取用户的排行榜信息");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"msg\":\"traffic_user_notexist\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"result\":652"), true);
        }
    }


    /**
     * 交规知识答题，只有一个用户参与且该用户答题满100道题且全部答对
     *
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceGetQuestionRankTestByOnlyOneUser() {
        initData();
        String businessServiceTrafficPoliceGetQuestionRankUrl = null;
        BusinessServiceTrafficPoliceGetQuestionRankBO businessServiceTrafficPoliceGetQuestionRankBO = null;
        String businessServiceTrafficPoliceGetQuestionRankResult = "";
        String businessServiceTrafficPoliceGetQuestionResult = "";
        HashMap<String, String> hs = userLoginTest();
        businessServiceTrafficPoliceAnswerQuestionTest.businessServiceTrafficPoliceAnswerQuestionTestByCircleAnswerQuestion5();
        try {
            businessServiceTrafficPoliceGetQuestionRankUrl = url + "/BusinessService/trafficpolice/getquestionrank";
            businessServiceTrafficPoliceGetQuestionRankBO = new BusinessServiceTrafficPoliceGetQuestionRankBO();
            businessServiceTrafficPoliceGetQuestionRankBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(hs.get("uid"));

            BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
            businessServiceTrafficPoliceGetQuestionBO.setAppid(hs.get("appid"));


            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult);

            businessServiceTrafficPoliceGetQuestionResult = businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("排名第一，返回“恭喜您夺得桂冠，不愧是您！”");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"hint\":\"恭喜您夺得桂冠，不愧是您！\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"rank\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("rankList"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"result\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"precision\":100"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"qcount\":100"), true);

            BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO = JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
            BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean questionBean = businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
            Assert.assertTrue(questionBean.getIsFinished());
            Assert.assertTrue(questionBean.getBeat().equals(100));
            Assert.assertTrue(questionBean.getComplete().equals(100));
            Assert.assertTrue(questionBean.getRight().equals(100));
            Assert.assertTrue(questionBean.getQcount().equals(100));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceGetQuestionResult, "$.question.id").equals(0));
            Assert.assertTrue(JsonPath.read(businessServiceTrafficPoliceGetQuestionResult, "$.question.idStr").equals(""));

        }
    }


    /**
     * 校验排行榜问题
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceGetQuestionRankTestByExistUserAndResponeParameterHintValidate() {
        String businessServiceTrafficPoliceGetQuestionRankUrl = null;
        BusinessServiceTrafficPoliceGetQuestionRankBO businessServiceTrafficPoliceGetQuestionRankBO = null;
        String businessServiceTrafficPoliceGetQuestionRankResult0 = "";
        String businessServiceTrafficPoliceGetQuestionRankResult2 = "";
        String businessServiceTrafficPoliceGetQuestionRankResult10 = "";

        try {
            //生成排行榜数据
            initData();
            genernateUserData();
            answerQuestion(genernateUserDataList);
            HashMap<String, String> hs1 = genernateUserDataList.get(0);
            HashMap<String, String> hs2 = genernateUserDataList.get(2);
            HashMap<String, String> hs10 = genernateUserDataList.get(10);

            businessServiceTrafficPoliceGetQuestionRankUrl = url + "/BusinessService/trafficpolice/getquestionrank";
            businessServiceTrafficPoliceGetQuestionRankBO = new BusinessServiceTrafficPoliceGetQuestionRankBO();
            businessServiceTrafficPoliceGetQuestionRankBO.setAppid(hs1.get("appid"));
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(hs1.get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(hs1.get("uid"));
            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult0 = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult0 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult0);

            businessServiceTrafficPoliceGetQuestionRankBO.setAppid(hs2.get("appid"));
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(hs2.get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(hs2.get("uid"));
            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult2 = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult2 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult2);


            businessServiceTrafficPoliceGetQuestionRankBO.setAppid(hs10.get("appid"));
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(hs10.get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(hs10.get("uid"));
            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult10 = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult10 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult10);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("第一名：恭喜您夺得桂冠，不愧是您！");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult0);
            initLog(recordhttp, new Object() {
            });

            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("2<=用户排名<=10，返回“距离NO.1你还差一丢丢的努力~”");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult2);
            initLog(recordhttp, new Object() {
            });

            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户排名>10，返回“您的答题数XXX题，正确率XX%，加油奥利给~”");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult10);
            initLog(recordhttp, new Object() {
            });

            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult0.contains("\"hint\":\"恭喜您夺得桂冠，不愧是您！\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult0.contains("\"rank\":1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult2.contains("\"hint\":\"距离NO.1你还差一丢丢的努力~\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult2.contains("\"rank\":3"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult10.contains("\"hint\":\"您的答题数100题，正确率90%，加油奥利给~\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult10.contains("\"rank\":11"), true);
        }
    }


    /**
     * 用户未答满50题，则不进入排行榜
     * @throws Exception
     */
    @Test
    public void BusinessServiceTrafficPoliceGetQuestionRankTestByExistUserAndAnswerLessThanFifty() {
        initData();
        String businessServiceTrafficPoliceGetQuestionRankUrl = null;
        BusinessServiceTrafficPoliceGetQuestionRankBO businessServiceTrafficPoliceGetQuestionRankBO = null;
        String businessServiceTrafficPoliceGetQuestionRankResult = "";
        String businessServiceTrafficPoliceGetQuestionResult = "";
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO = null;
        BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question = null;
        Long questionId = null;
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        HashMap<String, String> hs = userLoginTest();

        try {
            BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("yy");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid("1.00002");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);


            BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
            businessServiceTrafficPoliceGetQuestionBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceGetQuestionBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceGetQuestionBO.setReanswer("true");

            for (int i = 0; i < 45; i++) {
                businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                businessServiceTrafficPoliceGetQuestionResult = businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                businessServiceTrafficPoliceGetQuestionDTO = JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                question = businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                questionId = Long.valueOf(question.getIdStr());
                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();

                String rightAnswer = QuestionIdAndAnswer.get(questionId);
                List<Integer> answer = JSON.parseArray(rightAnswer, Integer.class);

                businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                businessServiceTrafficPoliceAnswerQuestionBO.setAppid(hs.get("appid"));
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(businessServiceTrafficPoliceSaveQuestioNavatarBO.getUid()));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(businessServiceTrafficPoliceSaveQuestioNavatarBO.getToken());
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }

            businessServiceTrafficPoliceGetQuestionRankUrl = url + "/BusinessService/trafficpolice/getquestionrank";
            businessServiceTrafficPoliceGetQuestionRankBO = new BusinessServiceTrafficPoliceGetQuestionRankBO();
            businessServiceTrafficPoliceGetQuestionRankBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(hs.get("uid"));


            log.info("businessServiceTrafficPoliceGetQuestionRankUrl 请求的参数=" + businessServiceTrafficPoliceGetQuestionRankUrl);
            log.info("businessServiceTrafficPoliceGetQuestionRankBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            businessServiceTrafficPoliceGetQuestionRankResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceGetQuestionRankUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO), "UTF-8");
            log.info("businessServiceTrafficPoliceGetQuestionRankResult 返回结果=" + businessServiceTrafficPoliceGetQuestionRankResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户未答满50题，则不进入排行榜");
            recordhttp.setUrl(businessServiceTrafficPoliceGetQuestionRankUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceTrafficPoliceGetQuestionRankBO));
            recordhttp.setResponse(businessServiceTrafficPoliceGetQuestionRankResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"hint\":\"您的答题数45题，正确率100%，加油奥利给~\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"rank\":-1"), true);
            Assert.assertEquals(businessServiceTrafficPoliceGetQuestionRankResult.contains("\"rankList\":[]"), true);


        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    /**
     * 生成11个用户用于答题
     */
    private void genernateUserData() {
        for (int i = 0; i < 11; i++) {
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("openid" + "_" + i);
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setNickname("openid" + "_" + i);
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            HashMap<String, String> hs = userLoginTest(userLoginBO);
            genernateUserDataList.add(hs);
            BusinessServiceTrafficPoliceSaveQuestioNavatarBO businessServiceTrafficPoliceSaveQuestioNavatarBO = new BusinessServiceTrafficPoliceSaveQuestioNavatarBO();
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAppid(hs.get("appid"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/nyz0Zjd4jIHtA2m8H3GHkaeRM7libK9VV0ENSBqJyTGhYxxs1Wibticwia5LHuGf2xt3Ql9FFfjxIOtmLjpKKDJ7BQ/132");
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setNickname("大饼子" + i);
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setToken(hs.get("token"));
            businessServiceTrafficPoliceSaveQuestioNavatarBO.setUid(hs.get("uid"));
            businessServiceTrafficPoliceSaveQuestioNavatarTest.BusinessServiceTrafficPoliceSaveQuestioNavatarTestByGernal(businessServiceTrafficPoliceSaveQuestioNavatarBO);
        }
    }

    //用户答题
    private void answerQuestion(List<HashMap<String, String>> genernateUserDataList) throws Exception {
        String businessServiceTrafficPoliceGetQuestionResult = null;
        BusinessServiceTrafficPoliceGetQuestionDTO businessServiceTrafficPoliceGetQuestionDTO = null;
        String businessServiceTrafficPoliceAnswerQuestionUrl = null;
        Long questionId = null;
        BusinessServiceTrafficPoliceAnswerQuestionBO businessServiceTrafficPoliceAnswerQuestionBO = null;
        String businessServiceTrafficPoliceAnswerQuestionResult = "";
        BusinessServiceTrafficPoliceGetQuestionDTO.QuestionBean question = null;

        Integer j = 0;
        for (int k = 0; k < genernateUserDataList.size(); k++, j = k
        ) {
            for (int i = 0; i < 101; i++) {
                BusinessServiceTrafficPoliceGetQuestionBO businessServiceTrafficPoliceGetQuestionBO = new BusinessServiceTrafficPoliceGetQuestionBO();
                businessServiceTrafficPoliceGetQuestionBO.setUid(genernateUserDataList.get(k).get("uid"));
                businessServiceTrafficPoliceGetQuestionBO.setToken(genernateUserDataList.get(k).get("token"));
                businessServiceTrafficPoliceGetQuestionBO.setAppid(genernateUserDataList.get(k).get("appid"));
                businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                businessServiceTrafficPoliceGetQuestionResult = businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                if (i == 100) {
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                    businessServiceTrafficPoliceGetQuestionResult = businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                    businessServiceTrafficPoliceGetQuestionDTO = JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                    question = businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                    if (question.getIdStr().isEmpty() && question.getId().equals(0)) {
                        questionId = -1L;
                        break;
                    }
                } else {
                    businessServiceTrafficPoliceGetQuestionBO.setReanswer("false");
                    businessServiceTrafficPoliceGetQuestionResult = businessServiceTrafficPoliceGetQuestionTest.businessServiceTrafficPoliceGetQuestionTestByGernal(businessServiceTrafficPoliceGetQuestionBO);
                    businessServiceTrafficPoliceGetQuestionDTO = JSON.parseObject(businessServiceTrafficPoliceGetQuestionResult, BusinessServiceTrafficPoliceGetQuestionDTO.class);
                    question = businessServiceTrafficPoliceGetQuestionDTO.getQuestion();
                    questionId = Long.valueOf(question.getIdStr());
                }

                businessServiceTrafficPoliceAnswerQuestionUrl = url + "/BusinessService/trafficpolice/answerquestion";
                businessServiceTrafficPoliceAnswerQuestionBO = new BusinessServiceTrafficPoliceAnswerQuestionBO();

                String rightAnswer = QuestionIdAndAnswer.get(questionId);
                List<Integer> answer = JSON.parseArray(rightAnswer, Integer.class);

                if (j <= 0) {
                    businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                } else {
                    answer.removeAll(answer);
                    answer.add(100);
                    businessServiceTrafficPoliceAnswerQuestionBO.setAnswer(answer);
                }
                j--;

                businessServiceTrafficPoliceAnswerQuestionBO.setAppid(genernateUserDataList.get(k).get("appid"));
                businessServiceTrafficPoliceAnswerQuestionBO.setId(Integer.valueOf(String.valueOf(questionId)));
                businessServiceTrafficPoliceAnswerQuestionBO.setUid(Integer.valueOf(genernateUserDataList.get(k).get("uid")));
                businessServiceTrafficPoliceAnswerQuestionBO.setToken(genernateUserDataList.get(k).get("token"));
                log.info("businessServiceTrafficPoliceAnswerQuestionUrl 请求的参数=" + businessServiceTrafficPoliceAnswerQuestionUrl);
                log.info("businessServiceTrafficPoliceAnswerQuestionBO 请求的参数=" + JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO));
                businessServiceTrafficPoliceAnswerQuestionResult = HttpUtil.postGeneralUrl(businessServiceTrafficPoliceAnswerQuestionUrl, "application/json", JSON.toJSONString(businessServiceTrafficPoliceAnswerQuestionBO), "UTF-8");
                log.info("businessServiceTrafficPoliceAnswerQuestionResult 返回结果=" + businessServiceTrafficPoliceAnswerQuestionResult);
            }
            BusinessServiceTrafficPoliceGetQuestionRankBO businessServiceTrafficPoliceGetQuestionRankBO = new BusinessServiceTrafficPoliceGetQuestionRankBO();
            businessServiceTrafficPoliceGetQuestionRankBO.setUid(genernateUserDataList.get(k).get("uid"));
            businessServiceTrafficPoliceGetQuestionRankBO.setToken(genernateUserDataList.get(k).get("token"));
            businessServiceTrafficPoliceGetQuestionRankBO.setAppid(genernateUserDataList.get(k).get("appid"));
            BusinessServiceTrafficPoliceGetQuestionRankTestByGernal(businessServiceTrafficPoliceGetQuestionRankBO);
        }
    }


    @Test
    public void genernateUserRankData() throws Exception {
        initData();
        genernateUserData();
        answerQuestion(genernateUserDataList);
    }


    @Test
    public void test() throws Exception {
        Set<ZSetOperations.TypedTuple<String>> set = jedisTemplate.zRangeWithScores("ms:wzjj:qsrank", 0, 15);
        System.out.println(JSON.toJSONString(set));
    }


    @Override
    public void initData() {
        super.initData();
        businessServiceTrafficPoliceAnswerQuestionTest.QuestionIdAndAnswer.putAll(QuestionIdAndAnswer);
        try {
            trafficpoliceQuestionUserRepository.deleteAll();
            applicationServiceManaged.restartMpServicePid();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void destroyData() {

    }


}
