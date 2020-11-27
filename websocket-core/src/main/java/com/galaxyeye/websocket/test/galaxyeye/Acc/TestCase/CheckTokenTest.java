package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.CheckTokenBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
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
public class CheckTokenTest extends BaseTest {


    //普通用户注入类
    @Autowired
    private LoginTest loginTest;

    //企业用户登录注入类
    @Autowired
    private ELoginTest eLoginTest;


    /**
     * 1、检查普通用户的token是否过期，token过期了，则返回token_expire
     * 2、检查普通用户的token是否过期，token没过期，则返回token_expire具体过期日期与返回token值，uid,uname等信息
     * 3、检查企业用户的token是否过期，token过期了，则返回token_expire
     * 4、检查企业用户的token是否过期，token没过期，则返回token_expire具体过期日期与返回token值，uid,uname等信息
     *
     * @throws Exception
     */
    @Test
    public void Test() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            checkTokenBO = new CheckTokenBO();
            HashMap<String,String> hs=userLoginTest();
            checkTokenBO.setAppid("1.00002");
            //过期的token
//        checkTokenBO.setToken("6DQNyP5-
            checkTokenBO.setToken(hs.get("token"));
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一般性通用测试");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }



    /**
     * elogin登录（企业登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByEloginTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            checkTokenBO = new CheckTokenBO();
            HashMap<String,String> hs=EUserLoginTest();
            checkTokenBO.setToken(hs.get("token"));
            checkTokenBO.setAppid("1.00002");
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checkTokenmessage 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("elogin登录（企业登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（普通用户登录,使用Deviceid登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByloginByDeviceidTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0创建用户返回的uname
            userLoginBO.setUname("deviceid_wwzu3ras");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（普通用户登录,使用Deviceid登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（非普通用户登录,使用Deviceid登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByDeviceidByloginTest1() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0-->userLoginBO.setUname("unionid16")
            // 的unionid16值;
            userLoginBO.setUname("unionid16");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("deviceid");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（非普通用户登录,使用Deviceid登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }


    /**
     * login登录（普通用户登录,使用Openid登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByloginByOpenidTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0创建用户返回的uname
            userLoginBO.setUname("wx_dbkpd2vk");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（普通用户登录,使用Openid登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（非普通用户登录,使用Openid登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByOpenidByloginTest1() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0-->userLoginBO.setUname("unionid16")
            // 的unionid16值;
            userLoginBO.setUname("wx_dbkpd2vk");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（非普通用户登录,使用Openid登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }


    /**
     * login登录（普通用户登录,使用Unionid登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByloginByUnionidTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0创建用户返回的uname
            userLoginBO.setUname("wx_qagazbfy");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checkTokenmessage 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（普通用户登录,使用Unionid登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（非普通用户登录,使用Unionid登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByUnionidByloginTest1() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0-->userLoginBO.setUname("unionid16")
            // 的unionid16值;
            userLoginBO.setUname("wx_ihl6plfy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginBO 请求的参数="+ JSON.toJSONString(userLoginBO));
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checkTokenmessage 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（非普通用户登录,使用Unionid登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }


    /**
     * login登录（普通用户登录,使用Cacc登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByloginByCaccTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0创建用户返回的uname
            userLoginBO.setUname("cacc_c4w0wzgm");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checkTokenmessage 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（普通用户登录,使用Cacc登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（非普通用户登录,使用Cacc登录）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByCaccByloginTest1() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            //Uname值设置为LoginTest.java--》LoginByCreateUserTest0-->userLoginBO.setUname("unionid16")
            // 的unionid16值;
            userLoginBO.setUname("cacc_c4w0wzgm");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("cacc");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（非普通用户登录,使用Cacc登录）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（uname的值使用未过期token）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByTokenByloginTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            UserLoginBO userLoginBO=new UserLoginBO();
            HashMap<String,String> hs=userLoginTest();
            userLoginBO.setUname(hs.get("token"));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");
            userLoginBO.setKeytp("token");
            UserLoginBO.Keys keys=new UserLoginBO.Keys();
            keys.setInfo("bindNo1forOpenid");
            keys.setNickname("NBind_openid_0812_01");
            List<UserLoginBO.Keys> list=new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String result=loginTest.LoginByGernal(userLoginBO);
            log.info("UserLogin 请求的参数="+ JSON.toJSONString(userLoginBO));
            log.info("UserLogin 返回结果="+ result);
            String token=(String)JSON.parseObject(result).get("token");
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（uname的值使用未过期token）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }
    }

    /**
     * login登录（uname的值使用过期token）检查token接口
     * @throws Exception
     */
    @Test
    public void checkTokenByExpireTokenByloginTest() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            String token="6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu5yWAnFoFFOH21-eGogpffTOWgbtfSq4f9jI8JnRLD9cI3h6UMoPVux2RZGEvkdvM7ftKE10X50B3M7kAK5iL5Wg==";
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("1.00002");
            checkTokenBO.setToken(token);
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("login登录（uname的值使用过期token）检查token接口");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"token_expire\""),true);
        }
    }

    /**
     * 普通用户的uid与token登录刷新，接口名称=checktoken配置在userdb.appid.info字段里AuthoMethod
     * @throws Exception
     */
    @Test
    public void Test1() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            checkTokenBO = new CheckTokenBO();
            HashMap<String,String> hs=userLoginTest();
            checkTokenBO.setAppid("100.00002");
            checkTokenBO.setToken(hs.get("token"));
            checkTokenBO.setUid(Long.valueOf(hs.get("uid")));
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户的uid与token登录刷新，接口名称=checktoken配置在userdb.appid.info字段里AuthoMethod");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }

    }

    /**
     * 企业用户的uid与token登录刷新，接口名称=checktoken配置在userdb.appid.info字段里AuthoMethod
     * @throws Exception
     */
    @Test
    public void Test2() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            checkTokenBO = new CheckTokenBO();
            HashMap<String,String> hs=EUserLoginTest();
            checkTokenBO.setAppid("100.00002");
            checkTokenBO.setToken(hs.get("token"));
            checkTokenBO.setUid(Long.valueOf(hs.get("euid")));
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业用户的uid与token登录刷新，接口名称=checktoken配置在userdb.appid.info字段里AuthoMethod");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }

    }

    /**
     * 使用bmappid与accessToken登录刷新，接口名称=checktoken配置在userdb.appid.info字段里AuthoMethod
     * @throws Exception
     */
    @Test
    public void Test3() throws Exception {
        String checktokenUrl =null;
        CheckTokenBO checkTokenBO =null;
        String checktokenResult ="";
        try{
            checktokenUrl = url+"/AccService/checktoken";
            checkTokenBO = new CheckTokenBO();
            checkTokenBO.setAppid("100.00002");
            HashMap<String,String> hs=userLoginTest();
            checkTokenBO.setToken(hs.get("token"));
            checkTokenBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            checkTokenBO.setBmAppid("100.00002");
            log.info("checktokenUrl 请求的参数=" + checktokenUrl);
            log.info("checkTokenBO 请求的参数=" + JSON.toJSONString(checkTokenBO));
            checktokenResult = HttpUtil.postGeneralUrl(checktokenUrl, "application/json", JSON.toJSONString(checkTokenBO), "UTF-8");
            log.info("checktokenResult 返回结果=" + JSON.parseObject(checktokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用bmappid与accessToken登录刷新，接口名称=checktoken配置在userdb.appid.info字段里AuthoMethod");
            recordhttp.setUrl(checktokenUrl);
            recordhttp.setRequest(JSON.toJSONString(checkTokenBO));
            recordhttp.setResponse(checktokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checktokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(checktokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(checktokenResult.contains("uid"),true);
            Assert.assertEquals(checktokenResult.contains("uname"),true);
            Assert.assertEquals(checktokenResult.contains("createTime"),true);
            Assert.assertEquals(checktokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(checktokenResult.contains("info"),true);
            Assert.assertEquals(checktokenResult.contains("token"),true);
        }

    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
