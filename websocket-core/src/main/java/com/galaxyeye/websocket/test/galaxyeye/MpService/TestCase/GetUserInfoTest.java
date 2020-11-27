package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 13:20
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
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetUserInfoBO;
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
public class GetUserInfoTest extends BaseTest {
    @Autowired
    private HealthUserRepository healthUserRepository;

    /**
     * 新用户查询用户答题称号
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByNewUser() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setBmAppid("4.00090");
            getUserInfoBO.setAppid("4.00090");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新用户查询用户答题称号");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 老用户查询用户答题称号
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByOldUser() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setBmAppid("4.00090");
            getUserInfoBO.setAppid("4.00090");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新用户查询用户答题称号");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 根据uid与token查询用户答题称号
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByUidAndToken() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setBmAppid("4.00090");
            getUserInfoBO.setAppid("4.00090");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token查询用户答题称号");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 根据AccessToken与BmAppid查询用户答题称号
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByBmAppidAndAccessToken() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            getUserInfoBO.setUid("237671");
            getUserInfoBO.setAccessToken("d4c430ebc03debd8a00e15c1fef36110368c314c03cc9a7aa586af7f9e55da87");
            getUserInfoBO.setBmAppid("4.00090");
            getUserInfoBO.setAppid("4.00090");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据AccessToken与BmAppid查询用户答题称号");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 用户过期的token查询用户答题称号
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByExpireToken() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=getuserExpireToken();
            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
//            getUserInfoBO.setBmAppid("4.00090");
//            getUserInfoBO.setAppid("4.00090");
            getUserInfoBO.setBmAppid("1.00002");
            getUserInfoBO.setAppid("1.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户过期的token查询用户答题称号");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByNotExistParameterAppid() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setBmAppid("4.00090");
//            getUserInfoBO.setAppid("4.00090");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(getuserinfoesult.contains("\"result\":106"),true);
        }
    }

    /**
     * 方法getuserinfo配置在openMethod当中，不校验token
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByOpenMethodAndUid() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
//            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setBmAppid("1.00002");
            getUserInfoBO.setAppid("1.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getuserinfo配置在openMethod当中，不校验token");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 方法getuserinfo配置在openMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByOpenMethodAndBmAppid() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
//            getUserInfoBO.setAccessToken(bmAppids.get("1.00002"));
            getUserInfoBO.setBmAppid("1.00002");
            getUserInfoBO.setAppid("1.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getuserinfo配置在openMethod当中，不校验AccessToken");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 方法getuserinfo配置在authMethod当中，参数Token传递
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByAuthMethodAndUid() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setAppid("100.00002");
            getUserInfoBO.setBmAppid("100.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getuserinfo配置在authMethod当中，参数Token传递");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 方法getuserinfo配置在authMethod当中，参数Token不传递
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            HashMap<String,String> userLogin=userLoginTest();
//            getUserInfoBO.setToken(userLogin.get("token"));
            getUserInfoBO.setUid(userLogin.get("uid"));
            getUserInfoBO.setAppid("100.00002");
            getUserInfoBO.setBmAppid("100.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getuserinfo配置在authMethod当中，参数Token不传递");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(getuserinfoesult.contains("\"result\":101"),true);
        }
    }

    /**
     * 方法getuserinfo配置在authMethod当中，参数AccessToken传递
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByAuthMethodAndBmAppid() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
            getUserInfoBO.setAccessToken(bmAppids.get("100.00002"));
            getUserInfoBO.setAppid("100.00002");
            getUserInfoBO.setBmAppid("100.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getuserinfo配置在authMethod当中，参数AccessToken传递");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"result\":1"),true);
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getuserinfoesult.contains("signedDays"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayAnswered"),true);
            Assert.assertEquals(getuserinfoesult.contains("right"),true);
            Assert.assertEquals(getuserinfoesult.contains("lastSigned"),true);
            Assert.assertEquals(getuserinfoesult.contains("title"),true);
            Assert.assertEquals(getuserinfoesult.contains("point"),true);
            Assert.assertEquals(getuserinfoesult.contains("todayRight"),true);
        }
    }

    /**
     * 方法getuserinfo配置在authMethod当中，参数AccessToken传递
     * @throws Exception
     */
    @Test
    public void getUserInfoTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String getuserinfoUrl =null;
        GetUserInfoBO getUserInfoBO =null;
        String getuserinfoesult ="";
        try{
            getuserinfoUrl = url+"/BusinessService/health/getuserinfo";
            getUserInfoBO = new GetUserInfoBO();
//            getUserInfoBO.setAccessToken(bmAppids.get("100.00002"));
            getUserInfoBO.setAppid("100.00002");
            getUserInfoBO.setBmAppid("100.00002");
            log.info("getuserinfoUrl 请求的参数=" + getuserinfoUrl);
            log.info("getUserInfoBO 请求的参数=" + JSON.toJSONString(getUserInfoBO));
            getuserinfoesult = HttpUtil.postGeneralUrl(getuserinfoUrl, "application/json", JSON.toJSONString(getUserInfoBO), "UTF-8");
            log.info("getuserinfoesult 返回结果=" + JSON.parseObject(getuserinfoesult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getuserinfo配置在authMethod当中，参数AccessToken传递");
            recordhttp.setUrl(getuserinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserInfoBO));
            recordhttp.setResponse(getuserinfoesult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getuserinfoesult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(getuserinfoesult.contains("\"result\":101"),true);
        }
    }




    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        HealthUserExample example=new HealthUserExample();
        HealthUserExample.Criteria cr=example.createCriteria();
        cr.andUidEqualTo(237671L);
        healthUserRepository.deleteByExample(example);
    }

    @Override
    public void destroyData() {

    }

}
