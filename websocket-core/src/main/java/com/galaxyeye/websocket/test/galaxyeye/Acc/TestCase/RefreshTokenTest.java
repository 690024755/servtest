package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.RefreshTokenBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class RefreshTokenTest extends BaseTest {

    /**
     *
     * 1、普通用户登录,接口=refreshtoken配置在userdb.appid.info字段里的openMethods
     * 2、企业用户登录,接口=refreshtoken配置在userdb.appid.info字段里的openMethods
     * 3、主要作用是产生一个新token,防止token过期，参数里的token必须在过期前才能调用该接口
     * @throws Exception
     */
    @Test
    public void refreshtokenTestByNotExpireToken() throws Exception {
        String refreshtokenUrl =null;
        RefreshTokenBO refreshTokenBO =null;
        String refreshtokenResult ="";
        try{
            refreshtokenUrl = url+"/AccService/refreshtoken";
            refreshTokenBO = new RefreshTokenBO();
            HashMap<String,String> hs=userLoginTest();
            refreshTokenBO.setAppid("1.00002");
            refreshTokenBO.setTokenExpire(100000);
            refreshTokenBO.setToken(hs.get("token"));
            refreshTokenBO.setSeq("abc");
            log.info("refreshtokenUrl 请求的参数=" + refreshtokenUrl);
            log.info("refreshTokenBO 请求的参数=" + JSON.toJSONString(refreshTokenBO));
            refreshtokenResult = HttpUtil.postGeneralUrl(refreshtokenUrl, "application/json", JSON.toJSONString(refreshTokenBO), "UTF-8");
            log.info("refreshtokenResult 返回结果=" + JSON.parseObject(refreshtokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用测试方法");
            recordhttp.setUrl(refreshtokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshTokenBO));
            recordhttp.setResponse(refreshtokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshtokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshtokenResult.contains("createTime"),true);
            Assert.assertEquals(refreshtokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshtokenResult.contains("token"),true);
            Assert.assertEquals(refreshtokenResult.contains("uname"),true);
        }
    }


    /**
     * 1、普通用户登录,接口=refreshtoken配置在userdb.appid.info字段里的AuthMethods，使用Token与uid刷新
     * 2、
     * @throws Exception
     */
    @Test
    public void refreshtokenTestByNotExpireTokenViaNormalUser() throws Exception {
        String refreshtokenUrl =null;
        RefreshTokenBO refreshTokenBO =null;
        String refreshtokenResult ="";
        try{
            refreshtokenUrl = url+"/AccService/refreshtoken";
            refreshTokenBO = new RefreshTokenBO();
            HashMap<String,String> hs=userLoginTest();
            refreshTokenBO.setAppid("100.00002");
            refreshTokenBO.setUid(Long.valueOf(hs.get("uid")));
            refreshTokenBO.setTokenExpire(100000);
            refreshTokenBO.setToken(hs.get("token"));
            refreshTokenBO.setSeq("abc");
            log.info("refreshtokenUrl 请求的参数=" + refreshtokenUrl);
            log.info("refreshTokenBO 请求的参数=" + JSON.toJSONString(refreshTokenBO));
            refreshtokenResult = HttpUtil.postGeneralUrl(refreshtokenUrl, "application/json", JSON.toJSONString(refreshTokenBO), "UTF-8");
            log.info("refreshtokenResult 返回结果=" + JSON.parseObject(refreshtokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshtoken配置在AuthMethods当中，使用未过期的Token与uid，执行refreshtoken");
            recordhttp.setUrl(refreshtokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshTokenBO));
            recordhttp.setResponse(refreshtokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshtokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshtokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshtokenResult.contains("uid"),true);
            Assert.assertEquals(refreshtokenResult.contains("uname"),true);
            Assert.assertEquals(refreshtokenResult.contains("createTime"),true);
            Assert.assertEquals(refreshtokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(refreshtokenResult.contains("info"),true);
            Assert.assertEquals(refreshtokenResult.contains("token"),true);
        }
    }


    /**
     * 1、普通用户登录,接口=refreshtoken配置在userdb.appid.info字段里的AuthMethods，使用BmAppid与AccessToken刷新
     * 2、接口方法=refreshtoken配置在AuthMethods当中，使用未过期的AccessToken与BmAppid，执行refreshtoken刷新普通用户的token
     * @throws Exception
     */
    @Test
    public void refreshtokenTestByAccessTokenAndBmAppid() throws Exception {
        String refreshtokenUrl =null;
        RefreshTokenBO refreshTokenBO =null;
        String refreshtokenResult ="";
        try{
            refreshtokenUrl = url+"/AccService/refreshtoken";
            refreshTokenBO = new RefreshTokenBO();
            HashMap<String,String> hs=userLoginTest();
            refreshTokenBO.setAppid("100.00002");
            refreshTokenBO.setBmAppid("100.00002");
            refreshTokenBO.setTokenExpire(100000);
            refreshTokenBO.setToken(hs.get("token"));
            refreshTokenBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            refreshTokenBO.setSeq("abc");
            log.info("refreshtokenUrl 请求的参数=" + refreshtokenUrl);
            log.info("refreshTokenBO 请求的参数=" + JSON.toJSONString(refreshTokenBO));
            refreshtokenResult = HttpUtil.postGeneralUrl(refreshtokenUrl, "application/json", JSON.toJSONString(refreshTokenBO), "UTF-8");
            log.info("refreshtokenResult 返回结果=" + JSON.parseObject(refreshtokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshtoken配置在AuthMethods当中，使用未过期的AccessToken与BmAppid，执行refreshtoken刷新普通用户的token");
            recordhttp.setUrl(refreshtokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshTokenBO));
            recordhttp.setResponse(refreshtokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshtokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshtokenResult.contains("createTime"),true);
            Assert.assertEquals(refreshtokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshtokenResult.contains("token"),true);
            Assert.assertEquals(refreshtokenResult.contains("uname"),true);
            Assert.assertEquals(refreshtokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(refreshtokenResult.contains("uid"),true);
        }
    }

    /**
     * 1、接口方法=refreshtoken配置在OpenMethods当中，使用未过期的企业Token，执行refreshtoken
     * @throws Exception
     */
    @Test
    public void refreshtokenTestByNotExpireTokenViaEnterpriseUser() throws Exception {
        String refreshtokenUrl =null;
        RefreshTokenBO refreshTokenBO =null;
        String refreshtokenResult ="";
        try{
            refreshtokenUrl = url+"/AccService/refreshtoken";
            refreshTokenBO = new RefreshTokenBO();
            HashMap<String,String> hs=EUserLoginTest();
            refreshTokenBO.setAppid("100.00001");
            refreshTokenBO.setTokenExpire(100000);
            refreshTokenBO.setToken(hs.get("token"));
            refreshTokenBO.setSeq("abc");
            log.info("refreshtokenUrl 请求的参数=" + refreshtokenUrl);
            log.info("refreshTokenBO 请求的参数=" + JSON.toJSONString(refreshTokenBO));
            refreshtokenResult = HttpUtil.postGeneralUrl(refreshtokenUrl, "application/json", JSON.toJSONString(refreshTokenBO), "UTF-8");
            log.info("refreshtokenResult 返回结果=" + JSON.parseObject(refreshtokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshtoken配置在OpenMethods当中，使用未过期的企业Token，执行refreshtoken");
            recordhttp.setUrl(refreshtokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshTokenBO));
            recordhttp.setResponse(refreshtokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshtokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshtokenResult.contains("createTime"),true);
            Assert.assertEquals(refreshtokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshtokenResult.contains("token"),true);
            Assert.assertEquals(refreshtokenResult.contains("uname"),true);
            Assert.assertEquals(refreshtokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(refreshtokenResult.contains("uid"),true);
        }
    }

    /**
     * 1、接口方法=refreshtoken配置在AuthMethods当中，使用未过期的企业Token，执行refreshtoken
     * 2、
     * @throws Exception
     */
    @Test
    public void refreshtokenTestByEnterpriseUidAndEnterpriseToken() throws Exception {
        String refreshtokenUrl =null;
        RefreshTokenBO refreshTokenBO =null;
        String refreshtokenResult ="";
        try{
            refreshtokenUrl = url+"/AccService/refreshtoken";
            refreshTokenBO = new RefreshTokenBO();
            HashMap<String,String> hs=EUserLoginTest();
            refreshTokenBO.setAppid("100.00002");
            refreshTokenBO.setTokenExpire(100000);
            refreshTokenBO.setToken(hs.get("token"));
            refreshTokenBO.setUid(Long.valueOf(hs.get("euid")));
            refreshTokenBO.setSeq("abc");
            log.info("refreshtokenUrl 请求的参数=" + refreshtokenUrl);
            log.info("refreshTokenBO 请求的参数=" + JSON.toJSONString(refreshTokenBO));
            refreshtokenResult = HttpUtil.postGeneralUrl(refreshtokenUrl, "application/json", JSON.toJSONString(refreshTokenBO), "UTF-8");
            log.info("refreshtokenResult 返回结果=" + JSON.parseObject(refreshtokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshtoken配置在AuthMethods当中，使用未过期的企业Token与uid，执行refreshtoken");
            recordhttp.setUrl(refreshtokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshTokenBO));
            recordhttp.setResponse(refreshtokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshtokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshtokenResult.contains("createTime"),true);
            Assert.assertEquals(refreshtokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshtokenResult.contains("token"),true);
            Assert.assertEquals(refreshtokenResult.contains("uname"),true);
            Assert.assertEquals(refreshtokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(refreshtokenResult.contains("uid"),true);
        }
    }

    /**
     * 1、企业用户登录,接口=refreshtoken配置在userdb.appid.info字段里的AuthMethods，使用bmappid与accessToken刷新
     * 2、
     * @throws Exception
     */
    @Test
    public void refreshtokenTestByBmAppidAndAccessTokenRefreshEnterpriseToken() throws Exception {
        String refreshtokenUrl =null;
        RefreshTokenBO refreshTokenBO =null;
        String refreshtokenResult ="";
        try{
            refreshtokenUrl = url+"/AccService/refreshtoken";
            refreshTokenBO = new RefreshTokenBO();
            HashMap<String,String> hs=EUserLoginTest();
            refreshTokenBO.setAppid("100.00002");
            refreshTokenBO.setTokenExpire(100000);
            refreshTokenBO.setToken(hs.get("token"));
            refreshTokenBO.setBmAppid("100.00002");
            refreshTokenBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            refreshTokenBO.setSeq("abc");
            log.info("refreshtokenUrl 请求的参数=" + refreshtokenUrl);
            log.info("refreshTokenBO 请求的参数=" + JSON.toJSONString(refreshTokenBO));
            refreshtokenResult = HttpUtil.postGeneralUrl(refreshtokenUrl, "application/json", JSON.toJSONString(refreshTokenBO), "UTF-8");
            log.info("refreshtokenResult 返回结果=" + JSON.parseObject(refreshtokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshtoken配置在AuthMethods当中，使用bmappid与accessToken刷新企业用户的token");
            recordhttp.setUrl(refreshtokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshTokenBO));
            recordhttp.setResponse(refreshtokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshtokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshtokenResult.contains("createTime"),true);
            Assert.assertEquals(refreshtokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshtokenResult.contains("token"),true);
            Assert.assertEquals(refreshtokenResult.contains("uname"),true);
            Assert.assertEquals(refreshtokenResult.contains("tokenExpire"),true);
            Assert.assertEquals(refreshtokenResult.contains("uid"),true);
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
