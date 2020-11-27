package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 10:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AuthTokenBO;
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
public class AuthTokenTest extends BaseTest {

    @Test
    public void authTokenTestByNotExpireToken() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00002");
            HashMap<String,String> hs=userLoginTest();
            authTokenBO.setToken(hs.get("token"));
            authTokenBO.setUid(Long.valueOf(hs.get("uid")));
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在AuthMethod当中，校验普通用户登录后，使用未过期token校验Token生效");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(authTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(authTokenResult.contains("tokenExpire"),true);
        }
    }

    /**
     * 普通用户使用过期的token，检验接口=authtoken，返回不生效
     * @throws Exception
     */
    @Test
    public void authTokenTestByExpireTokenViaNormalUser1() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00002");
            authTokenBO.setToken("Vj0qsbTj88l6K5v4vdul2xb4PGTqp_T1TDl8myvOWoT4Xc7HkLITXVhMoc_LDzCqXd6WLV2hmadCx56ria_2wV5d0lTGFqTXvV5dtSW-YlYOPuvxVvJwKRGq9J_xDqE0");
            authTokenBO.setUid(Long.valueOf(225839));
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在AuthMethod当中，普通用户使用过期的token，检验接口=authtoken，返回不生效");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"access_deny\""),true);
        }
    }

    /**
     * 1、使用参数AccessToken不过期检验，不区分企业用户与普通用户
     * 2、AccessToken不存在过期问题
     * @throws Exception
     */
    @Test
    public void authTokenTestByNotExpireAccessToken() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            authTokenBO.setBmAppid("100.00001");
            authTokenBO.setAccessToken("6ec287b5ef320ddd268520ffae176be2159adabf75e6f8dd5ef4e01ad4af496d96884e169383e0affa8a091c91f73a93");
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用参数AccessToken不过期检验，不区分企业用户与普通用户");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(authTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(authTokenResult.contains("tokenExpire"),true);
        }


    }

    /**
     * 1、使用参数AccessToken为过期值，不区分企业用户与普通用户
     * @throws Exception
     */
    @Test
    public void authTokenTestByExpireAccessToken() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            authTokenBO.setBmAppid("100.00001");
            authTokenBO.setAccessToken("6ec287b5ef320ddd268520ffae176be276fb793739c15e2dd9c7616e7583e801f3e0ce1c12b8fc7a7d68f587b99af4cd");
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用参数AccessToken为过期值，不区分企业用户与普通用户");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"token_error\""),true);
        }
    }

    /**
     * 1、使用参数AccessToken为不存在值，不区分企业用户与普通用户
     * @throws Exception
     */
    @Test
    public void authTokenTestByNotExistAccessToken() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            authTokenBO.setBmAppid("100.00001");
            authTokenBO.setAccessToken("1111");
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用参数AccessToken为不存在值，不区分企业用户与普通用户");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"token_error\""),true);
        }
    }


    /**
     * 企业用户同时使用uid、token、BmAppid、accessToken，那么优先使用uid和token，不校验bmAppid和AccessToken了
     * @throws Exception
     */
    @Test
    public void authTokenTestByUidAndTokenAndBmAppidAndAccessTokenViaEnterpriseUser() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            authTokenBO.setBmAppid("100.00001");
            HashMap<String,String> hs=EUserLoginTest();
            authTokenBO.setToken(hs.get("token"));
            authTokenBO.setUid(Long.valueOf(hs.get("euid")));
            authTokenBO.setAccessToken("6ec287b5ef320ddd268520ffae176be275f48d54c7cc984b48fdba5a7d554bc193fb03dad2366386a1d48f1917328a2d");
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业用户同时使用uid、token、BmAppid、accessToken，那么优先使用uid和token，不校验bmAppid和AccessToken了");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(authTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(authTokenResult.contains("tokenExpire"),true);
        }
    }

    /**
     * 普通用户同时使用uid、token、BmAppid、accessToken，那么优先使用uid和token，不校验bmAppid和AccessToken了
     * @throws Exception
     */
    @Test
    public void authTokenTestByUidAndTokenAndBmAppidAndAccessTokenViaNormalUser() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            authTokenBO.setBmAppid("100.00001");
            HashMap<String,String> hs=userLoginTest();
            authTokenBO.setToken(hs.get("token"));
            authTokenBO.setUid(Long.valueOf(hs.get("uid")));
            authTokenBO.setAccessToken("6ec287b5ef320ddd268520ffae176be275f48d54c7cc984b48fdba5a7d554bc193fb03dad2366386a1d48f1917328a2d");
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户同时使用uid、token、BmAppid、accessToken，那么优先使用uid和token，不校验bmAppid和AccessToken了");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(authTokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(authTokenResult.contains("tokenExpire"),true);
        }
    }

    /**
     * 接口方法配置在OpenMethod当中，普通用户使用过期的token，校验authtoken，结果返回“token_error”
     * @throws Exception
     */
    @Test
    public void authTokenTestByExpireTokenViaNormalUser2() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            HashMap<String,String> hs=userLoginTest();
            authTokenBO.setToken("Vj0qsbTj88l6K5v4vdul2xb4PGTqp_T1TDl8myvOWoT4Xc7HkLITXVhMoc_LDzCquBJ-dsawaPpDf7FztOV-hAKpp-U3ZI_L124cgM4ZVfuBZsak3REmMabeIMXy64ti");
            authTokenBO.setUid(Long.valueOf(hs.get("uid")));
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在OpenMethod当中，普通用户使用过期的token，校验authtoken，结果返回“token_error”");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"token_error\""),true);
        }
    }

    /**
     * 企业用户使用过期的token,校验authtoken，结果返回“token_error”
     * @throws Exception
     */
    @Test
    public void authTokenTestByExpireTokenViaEnterpriseUser() throws Exception {
        String authTokenUrl =null;
        AuthTokenBO authTokenBO =null;
        String authTokenResult ="";
        try{
            authTokenUrl = url+"/AccService/authtoken";
            authTokenBO = new AuthTokenBO();
            authTokenBO.setAppid("100.00001");
            HashMap<String,String> hs=EUserLoginTest();
            authTokenBO.setToken("7cSbIg5f92KDmOdFfDVgVElgxBfV11ogIfSshytxbF1sRBdUFMbCJTJbXoD_rSkQwDbRqOPOYoLLqtLU0-GHxs91jm7QYy66dGqm4477D8dYWrI1GI99BcJdv7ooajN8u8eAuf8JTJfYHkDjrODlgA==");
            authTokenBO.setUid(Long.valueOf(hs.get("euid")));
            authTokenBO.setSeq("abc");
            log.info("authTokenUrl 请求的参数=" + authTokenUrl);
            log.info("authTokenBO 请求的参数=" + JSON.toJSONString(authTokenBO));
            authTokenResult = HttpUtil.postGeneralUrl(authTokenUrl, "application/json", JSON.toJSONString(authTokenBO), "UTF-8");
            log.info("authTokenResult 返回结果=" + JSON.parseObject(authTokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业用户使用过期的token,校验authtoken，结果返回“token_error”");
            recordhttp.setUrl(authTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(authTokenBO));
            recordhttp.setResponse(authTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(authTokenResult.contains("\"msg\":\"token_error\""),true);
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
