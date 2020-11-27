package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 15:07
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/14日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TGuestUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.LogOutBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Component
@Slf4j
public class LogOutTest extends BaseTest {

    @Autowired
    private LoginTest loginTest;

    @Autowired
    private TGuestUserRepository tGuestUserRepository;

    /**
     * 普通用户登录后，选择登出操作
     *
     * @throws Exception
     */
    public String logoutTestByGernal(LogOutBO logOutBO) throws Exception {
        String logoutUrl = null;
        String logoutResult = "";
        try {
            logoutUrl = url + "/AccService/logout";
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户登录后，选择登出操作");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("uid"), true);
            return logoutResult;
        }
    }

    /**
     * 普通用户登录后，选择登出操作，不传参数sessionCode
     *
     * @throws Exception
     */
    @Test
    public void logoutTestByNotExistParameterSessionCode() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setAppid("1.00002");
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSeq("abc");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户登录后，选择登出操作，不传参数sessionCode");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("\"uid\":" + hs.get("uid")), true);
        }
    }

    /**
     * 普通用户登录后，选择登出操作，传参数sessionCode
     * @throws Exception
     */
    @Test
    public void logoutTestByExistParameterSessionCode() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setAppid("1.00002");
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSeq("abc");
            logOutBO.setSessionCode(hs.get("sessionCode"));
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户登录后，选择登出操作，传参数sessionCode");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("\"uid\":" + hs.get("uid")), true);
        }
    }


    /**
     * 不存在的用户选择登出操作
     * @throws Exception
     */
    @Test
    public void logoutByNotExistUser() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setAppid("1.00002");
            logOutBO.setUid(999999999L);
            logOutBO.setSeq("abc");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不存在的用户选择登出操作");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":104"), true);
        }
    }


    /**
     * 已登出用户重复登出操作，目前支持的
     *
     * @throws Exception
     */
    @Test
    public void logoutByReapeatLogOut() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            for (int i = 0; i < 2; i++) {
                logoutUrl = url + "/AccService/logout";
                logOutBO = new LogOutBO();
                logOutBO.setAppid("1.00002");
                logOutBO.setUid(Long.valueOf(hs.get("uid")));
                logOutBO.setSeq("abc");
                log.info("logoutUrl 请求的参数=" + logoutUrl);
                log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
                logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
                log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已登出用户重复登出操作，目前支持的");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("\"uid\":" + hs.get("uid")), true);
        }
    }

    /**
     * 必填参数uid校验
     *
     * @throws Exception
     */
    @Test
    public void logoutByNotExistParameterUid() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setAppid("1.00002");
//            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSeq("abc");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数uid校验");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":104"), true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void logoutByNotExistParameterAppid() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
//            logOutBO.setAppid("1.00002");
            logOutBO.setUid(Long.valueOf(hs.get("uid")));
            logOutBO.setSeq("abc");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":106"), true);
        }
    }


    /**
     * token过期的用户选择登出操作，目前支持该业务流程
     * @throws Exception
     */
    @Test
    public void logoutByExpireToken() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setAppid("1.00002");
            logOutBO.setUid(100001L);
            logOutBO.setSeq("abc");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("\"uid\":100001"), true);
        }
    }

    /**
     * 游客用户先登录，在登出操作
     * @throws Exception
     */
    @Test
    public void logoutByGuestAndLoginAndLogOut() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUname("guest");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("guest");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setOpenid("Openid_guest");
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        tGuestUserRepository.deleteAll();
        String userloginResult = loginTest.LoginByGernal(userLoginBO);
        Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
        Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
        Assert.assertEquals(userloginResult.contains("uid"), true);
        Assert.assertEquals(userloginResult.contains("total"), true);
        Assert.assertEquals(userloginResult.contains("loginTime"), true);
        Assert.assertEquals(userloginResult.contains("uname"), true);
        Assert.assertEquals(userloginResult.contains("newUser"), true);
        Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
        Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        Assert.assertEquals(userloginResult.contains("token"), true);
        Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        Long uid = JsonPath.read(userloginResult, "$.uid");
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setUid(uid);
            logOutBO.setAppid(userLoginBO.getAppid());
            logOutBO.setKeytp("guest");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客用户先登录，在登出操作");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("uid"), true);
        }
    }

    /**
     * 游客用户重复登出
     * @throws Exception
     */
    @Test
    public void logoutByGuestAndRepeatLogOut() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUname("guest");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("guest");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setOpenid("Openid_guest");
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        tGuestUserRepository.deleteAll();
        String userloginResult = loginTest.LoginByGernal(userLoginBO);
        Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
        Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
        Assert.assertEquals(userloginResult.contains("uid"), true);
        Assert.assertEquals(userloginResult.contains("total"), true);
        Assert.assertEquals(userloginResult.contains("loginTime"), true);
        Assert.assertEquals(userloginResult.contains("uname"), true);
        Assert.assertEquals(userloginResult.contains("newUser"), true);
        Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
        Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        Assert.assertEquals(userloginResult.contains("token"), true);
        Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        Long uid = JsonPath.read(userloginResult, "$.uid");
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setUid(uid);
            logOutBO.setAppid(userLoginBO.getAppid());
            logOutBO.setKeytp("guest");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客用户重复登出");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":1"), true);
            Assert.assertEquals(logoutResult.contains("uid"), true);
        }
    }

    /**
     * 游客用户登出的uid不存在
     * @throws Exception
     */
    @Test
    public void logoutByGuestAndLogOutAndNotExistUser() throws Exception {
        String logoutUrl = null;
        LogOutBO logOutBO = null;
        String logoutResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUname("guest");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("guest");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setOpenid("Openid_guest");
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        tGuestUserRepository.deleteAll();
        String userloginResult = loginTest.LoginByGernal(userLoginBO);
        Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
        Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
        Assert.assertEquals(userloginResult.contains("uid"), true);
        Assert.assertEquals(userloginResult.contains("total"), true);
        Assert.assertEquals(userloginResult.contains("loginTime"), true);
        Assert.assertEquals(userloginResult.contains("uname"), true);
        Assert.assertEquals(userloginResult.contains("newUser"), true);
        Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
        Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        Assert.assertEquals(userloginResult.contains("token"), true);
        Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        Long uid = JsonPath.read(userloginResult, "$.uid");
        try {
            logoutUrl = url + "/AccService/logout";
            logOutBO = new LogOutBO();
            logOutBO.setUid(99999999999L);
            logOutBO.setAppid(userLoginBO.getAppid());
            logOutBO.setKeytp("guest");
            log.info("logoutUrl 请求的参数=" + logoutUrl);
            log.info("logOutBO 请求的参数=" + JSON.toJSONString(logOutBO));
            logoutResult = HttpUtil.postGeneralUrl(logoutUrl, "application/json", JSON.toJSONString(logOutBO), "UTF-8");
            log.info("logoutResult 返回结果=" + JSON.parseObject(logoutResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客用户登出的uid不存在");
            recordhttp.setUrl(logoutUrl);
            recordhttp.setRequest(JSON.toJSONString(logOutBO));
            recordhttp.setResponse(logoutResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(logoutResult.contains("\"msg\":\"guest user not exist\""), true);
            Assert.assertEquals(logoutResult.contains("\"result\":138"), true);
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
