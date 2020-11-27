package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.HealthUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthUserExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.AnswerQuestionBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Slf4j
@Component
public class AnswerQuestionTest extends BaseTest {

    @Autowired
    private HealthUserRepository healthUserRepository;

    @Autowired
    private GetUserInfoTest getUserInfoTest;

    /**
     * 答对题的数目超过答题数量
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByParameterRightIsOutOfRange() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(10);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("答对题的数目超过答题数量");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"right count is out of range\""), true);
            Assert.assertEquals(answerquestionResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 新用户首次答题，则表health_user无数据时候，先执行接口=BusinessService/health/getuserinfo，自动插入一条数据
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByNewUser() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新用户首次答题，则表health_user无数据时候，先执行接口=BusinessService/health/getuserinfo，自动插入一条数据");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(answerquestionResult.contains("signedDays"), true);
            Assert.assertEquals(answerquestionResult.contains("todayAnswered"), true);
            Assert.assertEquals(answerquestionResult.contains("right"), true);
            Assert.assertEquals(answerquestionResult.contains("title"), true);
        }
    }


    /**
     * 老用户答题
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByOldUser() throws Exception {
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("老用户答题");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(answerquestionResult.contains("signedDays"), true);
            Assert.assertEquals(answerquestionResult.contains("todayAnswered"), true);
            Assert.assertEquals(answerquestionResult.contains("right"), true);
            Assert.assertEquals(answerquestionResult.contains("title"), true);
        }
    }


    /**
     * 用户答第3题
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByAnswerThreeQuestion() throws Exception {
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户答第3题");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(answerquestionResult.contains("signedDays"), true);
            Assert.assertEquals(answerquestionResult.contains("todayAnswered"), true);
            Assert.assertEquals(answerquestionResult.contains("right"), true);
            Assert.assertEquals(answerquestionResult.contains("title"), true);
        }
    }


    /**
     * 用户答第4题
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByAnswerFourthQuestion() throws Exception {
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户答题及更新用户答题信息");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"health_answer_error\""), true);
            Assert.assertEquals(answerquestionResult.contains("\"result\":550"), true);
        }
    }

    /**
     * 使用uid与token答题
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByUidAndToken() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token答题");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(answerquestionResult.contains("signedDays"), true);
            Assert.assertEquals(answerquestionResult.contains("todayAnswered"), true);
            Assert.assertEquals(answerquestionResult.contains("right"), true);
            Assert.assertEquals(answerquestionResult.contains("title"), true);
        }
    }

    /**
     * 使用bmAppid与accessToken答题
     *
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByBmAppidAndAccessToken() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            answerQuestionBO.setUid("237671");
            answerQuestionBO.setAccessToken(bmAppids.get("4.00090"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用bmAppid与accessToken答题");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(answerquestionResult.contains("signedDays"), true);
            Assert.assertEquals(answerquestionResult.contains("todayAnswered"), true);
            Assert.assertEquals(answerquestionResult.contains("right"), true);
            Assert.assertEquals(answerquestionResult.contains("title"), true);
        }
    }


    @Test
    public void answerQuestionTestByExpireToken() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = getuserExpireToken();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
//            answerQuestionBO.setBmAppid("4.00090");
//            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setBmAppid("100.00002");
            answerQuestionBO.setAppid("100.00002");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户token过期答题,方法配置在openMethod当中，已经不验证token过期了，用户token过期答题,方法配置在authMethod当中，需要验证token过期");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"token_error\""), true);
            Assert.assertEquals(answerquestionResult.contains("\"result\":115"), true);
        }
    }

    /**
     * 必填参数Right校验,不填写参数Right，默认是0
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByNotExistParameterRight() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
            answerQuestionBO.setAppid("4.00090");
//        answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Right校验,不填写参数Right，默认是0");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"result\":1"), true);
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(answerquestionResult.contains("signedDays"), true);
            Assert.assertEquals(answerquestionResult.contains("todayAnswered"), true);
            Assert.assertEquals(answerquestionResult.contains("right"), true);
            Assert.assertEquals(answerquestionResult.contains("title"), true);
        }
    }

    /**
     * 必填参数Appid校验
     * @throws Exception
     */
    @Test
    public void answerQuestionTestByNotExistParameterAppid() throws Exception {
        action();
        String answerquestionUrl = null;
        AnswerQuestionBO answerQuestionBO = null;
        String answerquestionResult = "";
        try {
            answerquestionUrl = url + "/BusinessService/health/answerquestion";
            answerQuestionBO = new AnswerQuestionBO();
            HashMap<String, String> userLogin = userLoginTest();
            answerQuestionBO.setToken(userLogin.get("token"));
            answerQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionBO.setBmAppid("4.00090");
//            answerQuestionBO.setAppid("4.00090");
            answerQuestionBO.setRight(1);
            log.info("answerquestionUrl 请求的参数=" + answerquestionUrl);
            log.info("answerQuestionBO 请求的参数=" + JSON.toJSONString(answerQuestionBO));
            answerquestionResult = HttpUtil.postGeneralUrl(answerquestionUrl, "application/json", JSON.toJSONString(answerQuestionBO), "UTF-8");
            log.info("answerquestionResult 返回结果=" + JSON.parseObject(answerquestionResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(answerquestionUrl);
            recordhttp.setRequest(JSON.toJSONString(answerQuestionBO));
            recordhttp.setResponse(answerquestionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(answerquestionResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(answerquestionResult.contains("\"result\":106"), true);
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

    public void action() throws Exception {
        HealthUserExample example = new HealthUserExample();
        HealthUserExample.Criteria cr = example.createCriteria();
        cr.andUidEqualTo(237671L);
        healthUserRepository.deleteByExample(example);
        getUserInfoTest.getUserInfoTestByNewUser();
    }
}
