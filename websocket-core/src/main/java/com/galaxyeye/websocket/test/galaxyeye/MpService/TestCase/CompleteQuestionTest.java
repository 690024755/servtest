package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 14:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CompleteQuestionBO;
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
public class CompleteQuestionTest extends BaseTest {
    @Autowired
    private AnswerQuestionTest answerQuestionTest;

    /**
     * 用户当天答满3道题后，打卡
     * @throws Exception
     */
    @Test
    public void completequestionTestByAnswerThreeQuestion() throws Exception {
        String completequestionUrl =null;
        CompleteQuestionBO completeQuestionBO =null;
        String completequestionResult ="";
        try{
            completequestionUrl = url+"/BusinessService/health/completequestion";
            completeQuestionBO = new CompleteQuestionBO();
            HashMap<String,String> userLogin=userLoginTest();
            completeQuestionBO.setToken(userLogin.get("token"));
            completeQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionTest.answerQuestionTestByNewUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            answerQuestionTest.answerQuestionTestByOldUser();
//        completeQuestionBO.setAccessToken("d4c430ebc03debd8a00e15c1fef361103d479c5960235f72b417827f2f8e524e");
//        completeQuestionBO.setUid("111111");
            completeQuestionBO.setBmAppid("4.00090");
            completeQuestionBO.setAppid("4.00090");
            log.info("completequestionUrl 请求的参数=" + completequestionUrl);
            log.info("completeQuestionBO 请求的参数=" + JSON.toJSONString(completeQuestionBO));
            completequestionResult = HttpUtil.postGeneralUrl(completequestionUrl, "application/json", JSON.toJSONString(completeQuestionBO), "UTF-8");
            log.info("completequestionResult 返回结果=" + JSON.parseObject(completequestionResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户当天答满3道题后，打卡");
            recordhttp.setUrl(completequestionUrl);
            recordhttp.setRequest(JSON.toJSONString(completeQuestionBO));
            recordhttp.setResponse(completequestionResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completequestionResult.contains("\"result\":1"),true);
            Assert.assertEquals(completequestionResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(completequestionResult.contains("point"),true);
            Assert.assertEquals(completequestionResult.contains("right"),true);
            Assert.assertEquals(completequestionResult.contains("signedDays"),true);
            Assert.assertEquals(completequestionResult.contains("title"),true);
            Assert.assertEquals(completequestionResult.contains("todayRight"),true);
        }
    }

    /**
     * 用户当天未答满3道题，打卡
     * @throws Exception
     */
    @Test
    public void completequestionTestByAnswerTowQuestion() throws Exception {
        String completequestionUrl =null;
        CompleteQuestionBO completeQuestionBO =null;
        String completequestionResult ="";
        try{
            completequestionUrl = url+"/BusinessService/health/completequestion";
            completeQuestionBO = new CompleteQuestionBO();
            HashMap<String,String> userLogin=userLoginTest();
            completeQuestionBO.setToken(userLogin.get("token"));
            completeQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionTest.answerQuestionTestByNewUser();
            answerQuestionTest.answerQuestionTestByOldUser();
//        completeQuestionBO.setAccessToken("d4c430ebc03debd8a00e15c1fef361103d479c5960235f72b417827f2f8e524e");
//        completeQuestionBO.setUid("111111");
            completeQuestionBO.setBmAppid("4.00090");
            completeQuestionBO.setAppid("4.00090");
            log.info("completequestionUrl 请求的参数=" + completequestionUrl);
            log.info("completeQuestionBO 请求的参数=" + JSON.toJSONString(completeQuestionBO));
            completequestionResult = HttpUtil.postGeneralUrl(completequestionUrl, "application/json", JSON.toJSONString(completeQuestionBO), "UTF-8");
            log.info("completequestionResult 返回结果=" + JSON.parseObject(completequestionResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户当天未答满3道题，打卡");
            recordhttp.setUrl(completequestionUrl);
            recordhttp.setRequest(JSON.toJSONString(completeQuestionBO));
            recordhttp.setResponse(completequestionResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completequestionResult.contains("\"msg\":\"health_sign_error\""),true);
            Assert.assertEquals(completequestionResult.contains("\"result\":551"),true);
        }
    }

    /**
     * 使用uid与token打卡
     * @throws Exception
     */
    @Test
    public void completequestionTestByUIdAndToken() throws Exception {
        String completequestionUrl =null;
        CompleteQuestionBO completeQuestionBO =null;
        String completequestionResult ="";
        try{
            completequestionUrl = url+"/BusinessService/health/completequestion";
            completeQuestionBO = new CompleteQuestionBO();
            HashMap<String,String> userLogin=userLoginTest();
            completeQuestionBO.setToken(userLogin.get("token"));
            completeQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionTest.answerQuestionTestByNewUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            completeQuestionBO.setBmAppid("4.00090");
            completeQuestionBO.setAppid("4.00090");
            log.info("completequestionUrl 请求的参数=" + completequestionUrl);
            log.info("completeQuestionBO 请求的参数=" + JSON.toJSONString(completeQuestionBO));
            completequestionResult = HttpUtil.postGeneralUrl(completequestionUrl, "application/json", JSON.toJSONString(completeQuestionBO), "UTF-8");
            log.info("completequestionResult 返回结果=" + JSON.parseObject(completequestionResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token打卡");
            recordhttp.setUrl(completequestionUrl);
            recordhttp.setRequest(JSON.toJSONString(completeQuestionBO));
            recordhttp.setResponse(completequestionResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completequestionResult.contains("\"result\":1"),true);
            Assert.assertEquals(completequestionResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(completequestionResult.contains("point"),true);
            Assert.assertEquals(completequestionResult.contains("right"),true);
            Assert.assertEquals(completequestionResult.contains("signedDays"),true);
            Assert.assertEquals(completequestionResult.contains("title"),true);
            Assert.assertEquals(completequestionResult.contains("todayRight"),true);
        }
    }

    /**
     * 使用过期的token打卡,方法completequestion配置在openMethod当中，不验证过期token
     * 使用过期的token打卡,方法completequestion配置在authMethod当中，验证token是否过期
     * @throws Exception
     */
    @Test
    public void completequestionTestByExpireToken() throws Exception {
        String completequestionUrl =null;
        CompleteQuestionBO completeQuestionBO =null;
        String completequestionResult ="";
        try{
            completequestionUrl = url+"/BusinessService/health/completequestion";
            completeQuestionBO = new CompleteQuestionBO();
            HashMap<String,String> userLogin=getuserExpireToken();
            completeQuestionBO.setToken(userLogin.get("token"));
            completeQuestionBO.setUid(userLogin.get("uid"));
            answerQuestionTest.answerQuestionTestByNewUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            completeQuestionBO.setBmAppid("4.00090");
            completeQuestionBO.setAppid("4.00090");
            log.info("completequestionUrl 请求的参数=" + completequestionUrl);
            log.info("completeQuestionBO 请求的参数=" + JSON.toJSONString(completeQuestionBO));
            completequestionResult = HttpUtil.postGeneralUrl(completequestionUrl, "application/json", JSON.toJSONString(completeQuestionBO), "UTF-8");
            log.info("completequestionResult 返回结果=" + JSON.parseObject(completequestionResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用过期的token打卡");
            recordhttp.setUrl(completequestionUrl);
            recordhttp.setRequest(JSON.toJSONString(completeQuestionBO));
            recordhttp.setResponse(completequestionResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completequestionResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(completequestionResult.contains("\"result\":1"),true);
            Assert.assertEquals(completequestionResult.contains("signedDays"),true);
            Assert.assertEquals(completequestionResult.contains("right"),true);
            Assert.assertEquals(completequestionResult.contains("title"),true);
            Assert.assertEquals(completequestionResult.contains("point"),true);
            Assert.assertEquals(completequestionResult.contains("todayRight"),true);
        }
    }

    /**
     * 使用BmAppid与AccessToken打卡
     * @throws Exception
     */
    @Test
    public void completequestionTestByBmAppidAndAccessToken() throws Exception {
        String completequestionUrl =null;
        CompleteQuestionBO completeQuestionBO =null;
        String completequestionResult ="";
        try{
            completequestionUrl = url+"/BusinessService/health/completequestion";
            completeQuestionBO = new CompleteQuestionBO();
            completeQuestionBO.setAccessToken(bmAppids.get("4.00090"));
            completeQuestionBO.setUid("237671");
            answerQuestionTest.answerQuestionTestByNewUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            completeQuestionBO.setBmAppid("4.00090");
            completeQuestionBO.setAppid("4.00090");
            log.info("completequestionUrl 请求的参数=" + completequestionUrl);
            log.info("completeQuestionBO 请求的参数=" + JSON.toJSONString(completeQuestionBO));
            completequestionResult = HttpUtil.postGeneralUrl(completequestionUrl, "application/json", JSON.toJSONString(completeQuestionBO), "UTF-8");
            log.info("completequestionResult 返回结果=" + JSON.parseObject(completequestionResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken打卡");
            recordhttp.setUrl(completequestionUrl);
            recordhttp.setRequest(JSON.toJSONString(completeQuestionBO));
            recordhttp.setResponse(completequestionResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completequestionResult.contains("\"result\":1"),true);
            Assert.assertEquals(completequestionResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(completequestionResult.contains("point"),true);
            Assert.assertEquals(completequestionResult.contains("right"),true);
            Assert.assertEquals(completequestionResult.contains("signedDays"),true);
            Assert.assertEquals(completequestionResult.contains("title"),true);
            Assert.assertEquals(completequestionResult.contains("todayRight"),true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void completequestionTestByNotExistAppid() throws Exception {
        String completequestionUrl =null;
        CompleteQuestionBO completeQuestionBO =null;
        String completequestionResult ="";
        try{
            completequestionUrl = url+"/BusinessService/health/completequestion";
            completeQuestionBO = new CompleteQuestionBO();
            completeQuestionBO.setAccessToken(bmAppids.get("4.00090"));
            completeQuestionBO.setUid("237671");
            answerQuestionTest.answerQuestionTestByNewUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            answerQuestionTest.answerQuestionTestByOldUser();
            completeQuestionBO.setBmAppid("4.00090");
//            completeQuestionBO.setAppid("4.00090");
            log.info("completequestionUrl 请求的参数=" + completequestionUrl);
            log.info("completeQuestionBO 请求的参数=" + JSON.toJSONString(completeQuestionBO));
            completequestionResult = HttpUtil.postGeneralUrl(completequestionUrl, "application/json", JSON.toJSONString(completeQuestionBO), "UTF-8");
            log.info("completequestionResult 返回结果=" + JSON.parseObject(completequestionResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(completequestionUrl);
            recordhttp.setRequest(JSON.toJSONString(completeQuestionBO));
            recordhttp.setResponse(completequestionResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(completequestionResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(completequestionResult.contains("\"result\":106"),true);
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
