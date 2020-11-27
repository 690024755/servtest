package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 10:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.*;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.*;
import com.galaxyeye.websocket.test.galaxyeye.Acc.Sign.Sign;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.encr.MD5Utils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;


@Component
@Slf4j
public class LoginTest extends BaseTest {

    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private ResetPassTest resetPassTest;

    @Autowired
    private AppidRepository appidRepository;

    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private SysAccFlowRepository sysAccFlowRepository;

    @Autowired
    private SysLogRepository sysLogRepository;

    @Autowired
    private LogOutTest logOutTest;

    @Autowired
    private TGuestUserRepository tGuestUserRepository;

    @Autowired
    private UpdAppidTest updAppidTest;

    @Autowired
    private ConfigRepository configRepository;

    /**
     * 通用登录接口
     * @param userLoginBO
     * @return
     * @throws Exception
     */
    public String LoginByGernal(UserLoginBO userLoginBO) {
        //用户登录
        String userLoginUrl = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用登录接口");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            return userloginResult;
        }
    }


//    public String Login(UserLoginBO userLoginBO) {
//        String userLoginUrl = null;
//        String userloginResult = "";
//        try {
//            //用户登录
//            userLoginUrl = url + "/AccService/userlogin";
//            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
//            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
//            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
//            log.info("userloginResult 返回结果=" + userloginResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HttpLog recordhttp = new HttpLog();
//            recordhttp.setCreateTime(new Date());
//            recordhttp.setCaseDescribe("一般性登录测试");
//            recordhttp.setUrl(userLoginUrl);
//            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
//            recordhttp.setResponse(userloginResult);
//            initLog(recordhttp, new Object() {
//            });
//            return userloginResult;
//        }
//    }

    @Test
    public void test() throws Exception {
        UserLoginBO userLoginBO = new UserLoginBO();
        userLoginBO.setUname("Unionid_zhouxingyu");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");//
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setOpenid("Unionid_zhouxingyu");
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq(UUID.randomUUID().toString());
        Map<String, String> statistics = new HashMap<>();
        statistics.put("channelNo", "微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
        statistics.put("维度一", "渠道编号");
        statistics.put("维度二", "28");
        userLoginBO.setStatistics(statistics);
        LoginByGernal(userLoginBO);
    }

    /**
     * Uname值在userdb.t_keyindex.keyvalue=uname且userdb.t_keyindex.keyid=keytp在表userdb.t_keyindex查询不到则新建用户
     * 使用登录接口绑定新用户
     * 创建普通用户向如下表插入数据：
     * userdb.t_user
     * userdb.t_userkeys
     * userdb.t_keyindex
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByUnionId() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {

            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新unionid13值+1
            userLoginBO.setUname("unionid17");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
            keys.setUnionid("unionid17");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("acc账号创建普通用户，绑定unionid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("unionid17"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
        }
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetPassResult = "";
        try {
            //重置默认密码为123456
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname((String) JSON.parseObject(userloginResult).get("uname"));
            resetpassUrl = url + "/AccService/resetpass";
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetPassResult = resetPassTest.resetPass(resetPassBO);
            log.info("resetPassResult 返回结果=" + resetPassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp1 = new HttpLog();
            recordhttp1.setCreateTime(new Date());
            recordhttp1.setCaseDescribe("重置acc用户的密码为123456");
            recordhttp1.setUrl(resetpassUrl);
            recordhttp1.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp1.setResponse(resetPassResult);
            initLog(recordhttp1, new Object() {
            });
            Assert.assertEquals(resetPassResult.contains("uname"), true);
            Assert.assertEquals(resetPassResult.contains("uid"), true);
            Assert.assertEquals(resetPassResult.contains("\"result\":1"), true);
            Assert.assertEquals(resetPassResult.contains("\"msg\":\"\""), true);
        }
    }

    /**
     * 根据unionid绑定nickname，如果不存在则新增，如果存在则直接登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByNickNameAndUnionId() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("nickname3");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setUnionid("nickname3");
            keys.setNickname("nickname3");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据unionid绑定nickname，如果不存在则新增，如果存在则直接登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("nickname3"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
        }
    }




    @Test
    public void LoginByCreateUserTest1() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {

            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("test");
//        keys.setDeviceid("xyw");//keys中的deviceid字段为userdb.t_keyinfo.keyname
            keys.setMobile("18888888888");//keys中的mobile字段为userdb.t_keyinfo.keyname
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用用户名与密码登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("mobile"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"18888888888\""), true);
            Assert.assertEquals(userloginResult.contains("\"info\":\"test\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("\"uid\":225825"), true);
            Assert.assertEquals(userloginResult.contains("\"uname\":\"cacc_8rhbukbq\""), true);

        }
        String resetpassUrl = null;
        ResetPassBO resetPassBO = null;
        String resetPassResult = "";
        try {
            //重置默认密码为123456
            resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname((String) JSON.parseObject(userloginResult).get("uname"));
            resetpassUrl = url + "/AccService/resetpass";
            log.info("resetpassUrl 请求的参数=" + resetpassUrl);
            log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
            resetPassResult = resetPassTest.resetPass(resetPassBO);
            log.info("resetPassResult 返回结果=" + resetPassResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp1 = new HttpLog();
            recordhttp1.setCreateTime(new Date());
            recordhttp1.setCaseDescribe("重置acc用户的密码为123456");
            recordhttp1.setUrl(resetpassUrl);
            recordhttp1.setRequest(JSON.toJSONString(resetPassBO));
            recordhttp1.setResponse(resetPassResult);
            initLog(recordhttp1, new Object() {
            });
            Assert.assertEquals(resetPassResult.contains(userLoginBO.getUname()), true);
            Assert.assertEquals(resetPassResult.contains("uid"), true);
            Assert.assertEquals(resetPassResult.contains("\"result\":1"), true);
            Assert.assertEquals(resetPassResult.contains("\"msg\":\"\""), true);
        }
    }


    /**
     * 使用非普通账号登录的sql
     * <p>
     * SELECT `appid`,`uid` FROM `userdb`.`t_keyindex` WHERE (`keyid`='10' and `keyvalue`='unionid5');//请求参数Keytp=keyid,Uname=keyvalue
     * 使用非普通账号登录，请求参数Uname、Keytp必填，Passwd不传或者传空
     * 请求参数Uname的值设置为userdb.t_keyindex.keyvalue，如果Keytp=cacc则userdb.t_keyindex.keyvalue=1.000:caccno80，那么需要去掉前缀，传递参数为caccno80
     * 请求参数Keytp的值设置为userdb.t_keyindex.keyid在表userdb.t_keyinfo.keyid查询到keyname
     * 请求参数Appid=userdb.t_keyindex.appid，如果传递错误则会新建用户而不是旧用户的登录
     * @throws Exception
     */
    @Test
    public void LoginTest1() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("deviceid9");//Uname的值设置为userdb.t_keyindex.keyvalue
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("deviceid");//Keytp的值设置为userdb.t_keyindex.keyid在表userdb.t_keyinfo.keyid查询到keyname
//token与uid或者BmAppid与AccessToken
//        userLoginBO.setToken("jW2NM94PkVXaBl4soCTDb8FyBjvdSpqDrdd9O6077aigR9o2S8z8PJCYbAAxo67lNm0uNokU3rcUy9ftFZYc5128oI3aMqqt0ez8jbx0AAH1bdLRYb0ukOsZnHCr1Idychp3XyEtuZrf9QcRqvKx3A==");
//        userLoginBO.setUid(237213L);
//        userLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
//        userLoginBO.setBmAppid("100.00002");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("deviceid9");
            keys.setDeviceid("deviceid9");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uname与keytp=deviceid方式登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"deviceid9\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
        }
    }


    /**
     * 每次生成一个新的用户，通过deviceid方式生成，返回的结果newUser=true
     * @throws Exception
     */
    @Test
    public void LoginTestByCreateUserAndGenerateNewUser() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Deviceid_yy");//Uname的值设置为userdb.t_keyindex.keyvalue
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("deviceid");//Keytp的值设置为userdb.t_keyindex.keyid在表userdb.t_keyinfo.keyid查询到keyname
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("每次生成一个新的用户，通过deviceid方式生成，返回的结果newUser=true");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("deviceid"), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"loginValue\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        }
    }


    /**
     * 使用非普通账号登录的sql
     * <p>
     * SELECT `appid`,`uid` FROM `userdb`.`t_keyindex` WHERE (`keyid`='10' and `keyvalue`='unionid5');//请求参数Keytp=keyid,Uname=keyvalue
     * 使用非普通账号登录，请求参数Uname、Keytp必填，Passwd不传或者传空
     * 请求参数Uname的值设置为userdb.t_keyindex.keyvalue，如果Keytp=cacc则userdb.t_keyindex.keyvalue=1.000:caccno80，那么需要去掉前缀，传递参数为caccno80
     * 请求参数Keytp的值设置为userdb.t_keyindex.keyid在表userdb.t_keyinfo.keyid查询到keyname
     * 请求参数Appid=userdb.t_keyindex.appid，如果传递错误则会新建用户而不是旧用户的登录
     * @throws Exception
     */
    @Test
    public void LoginTestByCaccAndKeytp() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("1.000:caccno80");//Uname的值设置为userdb.t_keyindex.keyvalue
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("cacc");//Keytp的值设置为userdb.t_keyindex.keyid在表userdb.t_keyinfo.keyid查询到keyname
//token与uid或者BmAppid与AccessToken
//        userLoginBO.setToken("jW2NM94PkVXaBl4soCTDb8FyBjvdSpqDrdd9O6077aigR9o2S8z8PJCYbAAxo67lNm0uNokU3rcUy9ftFZYc5128oI3aMqqt0ez8jbx0AAH1bdLRYb0ukOsZnHCr1Idychp3XyEtuZrf9QcRqvKx3A==");
//        userLoginBO.setUid(237213L);
//        userLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
//        userLoginBO.setBmAppid("100.00002");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//        keys.setCacc("cacc");

//        keys.setOpenid("unionidTest5");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uname与keytp=cacc方式登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"1.000:caccno80\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
        }
    }


    /**
     * 使用普通账号登录,此时的请求参数Uname与Passwd为表userdb.t_user的uname与password字段
     * 使用普通账号登录，请求参数appid不需要跟表userdb.t_user.appid值一致
     * 使用普通账号登录，Uname、Passwd必填，Keytp不传或者传空
     * @throws Exception
     */
    @Test
    public void LoginTest2() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("wx_0kp5rze1");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");


            //token与uid或者BmAppid与AccessToken
//        userLoginBO.setAppid("100.00002");
//        userLoginBO.setToken("6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu5kVfWaWQGqWYA_5LgaMeqr6Rps3rqiDbv38W3cvODk94jhjvkesSrAHCLgSKKYO0D7lStSPYmFLs1qyifulj2AA==");
//        userLoginBO.setUid(225858L);
//        userLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
//        userLoginBO.setBmAppid("100.00002");

            userLoginBO.setKeytp("");//token设置为具体值
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
//        keys.setOpenid("unionidTest5");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uname=deviceid_wwzu3ras与Passwd方式登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("unionid17"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"uname\":\"wx_0kp5rze1\""), true);
            Assert.assertEquals(userloginResult.contains("\"uid\":239140"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"unionid17\""), true);
            Assert.assertEquals(userloginResult.contains("\"info\":\"unionidTest7\""), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
        }

    }

    /**
     * 使用普通账号登录,此时的请求参数Uname与Passwd为表userdb.t_user的uname与password字段
     * 使用普通账号登录，请求参数appid不需要跟表userdb.t_user.appid值一致
     * 使用普通账号登录，Uname、Passwd必填，Keytp不传或者传空
     * userlogin方法配置在userdb.appid.info字段里AuthMethods，使用AccessToken与BmAppid进行登录
     * @throws Exception
     */
    @Test
    public void LoginTest4() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("deviceid_wwzu3ras");
            userLoginBO.setPasswd("123456");


            //token与uid或者BmAppid与AccessToken人选其一方式
//        userLoginBO.setToken("6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu5kVfWaWQGqWYA_5LgaMeqr6Rps3rqiDbv38W3cvODk94jhjvkesSrAHCLgSKKYO0D7lStSPYmFLs1qyifulj2AA==");
//        userLoginBO.setUid(225858L);
            userLoginBO.setAppid("100.00002");
            userLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            userLoginBO.setBmAppid("100.00002");

            userLoginBO.setKeytp("");//token设置为具体值
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("unionidTest7");
//        keys.setOpenid("unionidTest5");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uname=deviceid_wwzu3ras与Passwd方式登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"uid\":225858"), true);
            Assert.assertEquals(userloginResult.contains("\"uname\":\"deviceid_wwzu3ras\""), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"NBind_openid_0812_01\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("\"info\":\"bindNo1forOpenid\""), true);
            Assert.assertEquals(userloginResult.contains("\"uid\":225858"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"unionid9\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("\"uname\":\"deviceid_wwzu3ras\""), true);
        }
    }


    /**
     * 使用uname=token（token的值过期）与keytp=token方式登录acc账号系统
     * Uname设置token
     * @throws Exception
     */
    @Test
    public void LoginByTokenTest5() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
//        userLoginBO.setUname("3acBd4uQo-q7FlxbGz8grzpSiaV_e-GsRFD8VH5MUqAFQQkNtxB7-EBcsO7Uyohjc675-h53rTZ7brskMc7AjvNxn4M85MkL1D0vbTfuw5CDeNZswWsFHUrmPXuDJ1jR");
            userLoginBO.setUname("6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu5kVfWaWQGqWYA_5LgaMeqr6Rps3rqiDbv38W3cvODk94jhjvkesSrAHCLgSKKYO0D7lStSPYmFLs1qyifulj2AA==");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");

            //token与uid或者BmAppid与AccessToken
//        userLoginBO.setAppid("100.00002");
//        userLoginBO.setToken("6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu5kVfWaWQGqWYA_5LgaMeqr6Rps3rqiDbv38W3cvODk94jhjvkesSrAHCLgSKKYO0D7lStSPYmFLs1qyifulj2AA==");
//        userLoginBO.setUid(225858L);
//        userLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
//        userLoginBO.setBmAppid("100.00002");

            userLoginBO.setKeytp("token");//如果使用token登录，则Keytp设置为token
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("bindNo1forOpenid");
            keys.setNickname("NBind_openid_0812_01");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uname=token（token的值过期）与keytp=token方式登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"token_expire\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":133"), true);
        }
    }


    /**
     * 使用uname=token（token的值未过期）与keytp=token方式登录acc账号系统
     * @throws Exception
     */
    @Test
    public void LoginByTokenTest6() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        //重置下密码
        ResetPassBO resetPassBO = new ResetPassBO();
        resetPassBO.setAppid("1.00002");
        resetPassBO.setPasswd("123456");
        resetPassBO.setUname("wx_fbqejw0x");
        String resetpassUrl = url + "/AccService/resetpass";
        log.info("resetpassUrl 请求的参数=" + resetpassUrl);
        log.info("resetPassBO 请求的参数=" + JSON.toJSONString(resetPassBO));
        String resetPassResult = resetPassTest.resetPass(resetPassBO);

        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            HashMap<String, String> hs = userLoginTest();
//        userLoginBO.setUname("3acBd4uQo-q7FlxbGz8grzpSiaV_e-GsRFD8VH5MUqAFQQkNtxB7-EBcsO7Uyohjc675-h53rTZ7brskMc7AjvNxn4M85MkL1D0vbTfuw5CDeNZswWsFHUrmPXuDJ1jR");
            userLoginBO.setUname(hs.get("token"));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");

            //token与uid或者BmAppid与AccessToken
//        userLoginBO.setAppid("100.00002");
//        userLoginBO.setToken("6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu5kVfWaWQGqWYA_5LgaMeqr6Rps3rqiDbv38W3cvODk94jhjvkesSrAHCLgSKKYO0D7lStSPYmFLs1qyifulj2AA==");
//        userLoginBO.setUid(225858L);
//        userLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
//        userLoginBO.setBmAppid("100.00002");

            userLoginBO.setKeytp("token");//如果使用token登录，则Keytp设置为token
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setInfo("bindNo1forOpenid");
            keys.setNickname("NBind_openid_0812_01");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(userLoginBO), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uname=token（token的值未过期）与keytp=token方式登录acc账号系统");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"uid\":237671"), true);
            Assert.assertEquals(userloginResult.contains("\"uname\":\"wx_fbqejw0x\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"NBind_openid_0812_01\""), true);
            Assert.assertEquals(userloginResult.contains("\"info\":\"bindNo1forOpenid\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
        }
    }


    /**
     * 增加对cacc登录方式的签名校验
     * 生成的MD5的值与页面提供的http://chandao.galaxyeye.com/task-view-1261.html，生成的md5不一样，需要咨询下孙彬
     * @throws Exception
     */
    @Test
    public void LoginBySignTest() throws Exception {
        String userLoginUrl = null;
        Map<String, String> hs = null;
        String userloginResult = "";
        try {
//用户登录
            userLoginUrl = url + "/AccService/userlogin";
            hs = new HashMap<>();
//        hs.put("uname","deviceid_wwzu3ras");
            hs.put("uname", "changyiger");
            hs.put("keytp", "cacc");
            hs.put("bmAppid", "9.00004");
            Long timestamp = System.currentTimeMillis();
            hs.put("timestamp", String.valueOf(timestamp));
            hs.put("nonceStr", "abc123");
            //MD5 在线生成 https://www.qqxiuzi.cn/bianma/md5.htm

            String MD5Sign = MD5Utils.getMD5(Sign.getSign(hs, "bmAppkey", "ljjLZDdMwIZA49fJ", false), "utf-8").toLowerCase();
            hs.put("sign", MD5Sign);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(hs));
            userloginResult = HttpUtil.postGeneralUrl(userLoginUrl, "application/json", JSON.toJSONString(hs), "UTF-8");
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("增加对cacc登录方式的签名校验");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(hs));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });

        }
    }

    /**
     * 使用cacc进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestBySameEuidAndSameGroupTagAndDifferentAppidAndParameterKeytpValueIsCaccAndParameterUnameValueIsCacc() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = "";
        String userloginResult2 = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("Cacc_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("cacc");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
//            keys.setUnionid("Unionid_yy");
            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            //重置用户uid=239098的t_userkeys表的appid值为1.00002
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname("wx_wnkiehde");
            resetPassTest.resetPass(resetPassBO);
            UserLoginBO restoreUserLoginBO = new UserLoginBO();
            restoreUserLoginBO.setUname("wx_wnkiehde");
            restoreUserLoginBO.setPasswd("123456");
            restoreUserLoginBO.setAppid("1.00002");
            restoreUserLoginBO.setKeytp("");
            restoreUserLoginBO.setKeys(list);
            restoreUserLoginBO.setThirdlogin(false);
            userloginResult1 = Login(restoreUserLoginBO);

            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            //第二次请求前，修改appid=1.00002的euid与GroupTag值与appid=1.00003的euid与GroupTag值一样
            changeEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult2 返回结果=" + JSON.parseObject(userloginResult2));
            //请求完成后，恢复appid=1.00002默认的euid与GroupTag值
            restoreEuidAndGroupTagByAppid();
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用cacc进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult2, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult2.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult2, UserBindBO.class).getKeys().get(0);
            String CaccAppid = JSON.parseObject(keysBean.getCacc(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(CaccAppid.equals("1.00003"));
            Assert.assertTrue(JSON.parseObject(userloginResult2).get("uid").equals(JSON.parseObject(userloginResult1).get("uid")));
        }
    }

    /**
     * 使用cacc进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByDifferentEuidAndDifferentGroupTagAndDifferentAppidAndParameterKeytpValueIsCaccAndParameterUnameValueIsCacc() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = "";
        String userloginResult2 = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("Cacc_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("cacc");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
//            keys.setUnionid("Unionid_yy");
            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            //重置用户uid=239098的t_userkeys表的appid值为1.00002
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname("wx_wnkiehde");
            resetPassTest.resetPass(resetPassBO);
            UserLoginBO restoreUserLoginBO = new UserLoginBO();
            restoreUserLoginBO.setUname("wx_wnkiehde");
            restoreUserLoginBO.setPasswd("123456");
            restoreUserLoginBO.setAppid("1.00002");
            restoreUserLoginBO.setKeytp("");
            restoreUserLoginBO.setKeys(list);
            restoreUserLoginBO.setThirdlogin(false);
            userloginResult1 = Login(restoreUserLoginBO);

            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult2 返回结果=" + JSON.parseObject(userloginResult2));
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用cacc进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是新增用户");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult2, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult2.contains("newUser"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult2, UserBindBO.class).getKeys().get(0);
            String CaccAppid = JSON.parseObject(keysBean.getCacc(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(CaccAppid.equals("1.00003"));
            Assert.assertTrue(!JSON.parseObject(userloginResult2).get("uid").equals(JSON.parseObject(userloginResult1).get("uid")));
        }
    }


    /**
     * 该条测试用例如下：
     * 使用openid进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     * <p>
     * 创建5种类型的登录方式deviceid、openid、unionid、cacc
     * 请求参数appid变更后，登录则直接变更t_userkeys表的appid的值
     * 1、appid属于同一个企业下同一个分组下，原先登录appid=1.00002，后续变更为appid=1.00003,且appid=1.00002与appid=1.00003属于同一个企业下同一个分组，则第一次登录是新增，第二次登录是变更appid
     * 2、appid不属于同一个企业下同一个分组下，原先登录appid=1.00002，后续变更为appid=1.00003,且appid=1.00002与appid=1.00003不属于同一个企业下同一个分组，则第一次登录是新增，第二次登录是同意新增用户
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestBySameEuidAndSameGroupTagAndDifferentAppidAndParameterKeytpValueIsOpenidAndParameterUnameValueIsOpenid() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = "";
        String userloginResult2 = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("Openid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
//            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            //重置用户uid=239098的t_userkeys表的appid值为1.00002
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname("wx_wnkiehde");
            resetPassTest.resetPass(resetPassBO);
            UserLoginBO restoreUserLoginBO = new UserLoginBO();
            restoreUserLoginBO.setUname("wx_wnkiehde");
            restoreUserLoginBO.setPasswd("123456");
            restoreUserLoginBO.setAppid("1.00002");
            restoreUserLoginBO.setKeytp("");
            restoreUserLoginBO.setKeys(list);
            restoreUserLoginBO.setThirdlogin(false);
            userloginResult1 = Login(restoreUserLoginBO);

            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            //第二次请求前，修改appid=1.00002的euid与GroupTag值与appid=1.00003的euid与GroupTag值一样
            changeEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult2 返回结果=" + JSON.parseObject(userloginResult2));
            //请求完成后，恢复appid=1.00002默认的euid与GroupTag值
            restoreEuidAndGroupTagByAppid();
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用openid进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录,结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult2, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult2.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult2, UserBindBO.class).getKeys().get(0);
            String OpenidAppid = JSON.parseObject(keysBean.getOpenid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(OpenidAppid.equals("1.00003"));
            Assert.assertTrue(JSON.parseObject(userloginResult2).get("uid").equals(JSON.parseObject(userloginResult1).get("uid")));
        }
    }

    /**
     * 使用openid进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByDifferentEuidAndDifferentGroupTagAndDifferentAppidAndParameterKeytpValueIsOpenidAndParameterUnameValueIsOpenid() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = "";
        String userloginResult2 = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("Openid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
//            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            //重置用户uid=239098的t_userkeys表的appid值为1.00002
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname("wx_wnkiehde");
            resetPassTest.resetPass(resetPassBO);
            UserLoginBO restoreUserLoginBO = new UserLoginBO();
            restoreUserLoginBO.setUname("wx_wnkiehde");
            restoreUserLoginBO.setPasswd("123456");
            restoreUserLoginBO.setAppid("1.00002");
            restoreUserLoginBO.setKeytp("");
            restoreUserLoginBO.setKeys(list);
            restoreUserLoginBO.setThirdlogin(false);
            userloginResult1 = Login(restoreUserLoginBO);

            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult2 返回结果=" + JSON.parseObject(userloginResult2));
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用openid进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录,结果是新增用户");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult2, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult2.contains("newUser"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult2, UserBindBO.class).getKeys().get(0);
            String OpenidAppid = JSON.parseObject(keysBean.getOpenid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(OpenidAppid.equals("1.00003"));
            Assert.assertTrue(!JSON.parseObject(userloginResult2).get("uid").equals(JSON.parseObject(userloginResult1).get("uid")));
        }
    }

    /**
     * 使用unionid进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更appid值
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestBySameEuidAndSameGroupTagAndDifferentAppidAndParameterKeytpValueIsUnionidAndParameterUnameValueIsUnionid() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = "";
        String userloginResult2 = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            //重置用户uid=239098的t_userkeys表的appid值为1.00002
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname("wx_wnkiehde");
            resetPassTest.resetPass(resetPassBO);
            UserLoginBO restoreUserLoginBO = new UserLoginBO();
            restoreUserLoginBO.setUname("wx_wnkiehde");
            restoreUserLoginBO.setPasswd("123456");
            restoreUserLoginBO.setAppid("1.00002");
            restoreUserLoginBO.setKeytp("");
            restoreUserLoginBO.setKeys(list);
            restoreUserLoginBO.setThirdlogin(false);
            userloginResult1 = Login(restoreUserLoginBO);
            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            //第二次请求前，修改appid=1.00002的euid与GroupTag值与appid=1.00003的euid与GroupTag值一样
            changeEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult2 返回结果=" + JSON.parseObject(userloginResult2));
            //请求完成后，恢复appid=1.00002默认的euid与GroupTag值
            restoreEuidAndGroupTagByAppid();
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用unionid进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult2, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult2.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult2, UserBindBO.class).getKeys().get(0);
            String UnionidAppid = JSON.parseObject(keysBean.getUnionid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
            Assert.assertTrue(JSON.parseObject(userloginResult2).get("uid").equals(JSON.parseObject(userloginResult1).get("uid")));
        }
    }

    /**
     * 使用unionid进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录,结果是新增
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByDifferentEuidAndDifferentGroupTagAndDifferentAppidAndParameterKeytpValueIsUnionidAndParameterUnameValueIsUnionid() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = "";
        String userloginResult2 = "";
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            //重置用户uid=239098的t_userkeys表的appid值为1.00002
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setAppid("1.00002");
            resetPassBO.setPasswd("123456");
            resetPassBO.setUname("wx_wnkiehde");
            resetPassTest.resetPass(resetPassBO);
            UserLoginBO restoreUserLoginBO = new UserLoginBO();
            restoreUserLoginBO.setUname("wx_wnkiehde");
            restoreUserLoginBO.setPasswd("123456");
            restoreUserLoginBO.setAppid("1.00002");
            restoreUserLoginBO.setKeytp("");
            restoreUserLoginBO.setKeys(list);
            restoreUserLoginBO.setThirdlogin(false);
            userloginResult1 = Login(restoreUserLoginBO);

            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult2 返回结果=" + JSON.parseObject(userloginResult2));
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用unionid进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录,结果是返回新增用户");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult2, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00003\""), true);
            Assert.assertEquals(userloginResult2.contains("newUser"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult2, UserBindBO.class).getKeys().get(0);
            String UnionidAppid = JSON.parseObject(keysBean.getUnionid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
            Assert.assertTrue(!JSON.parseObject(userloginResult2).get("uid").equals(JSON.parseObject(userloginResult1).get("uid")));
        }
    }

    /**
     * 使用普通的用户名与密码进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestBySameEuidAndSameGroupTagAndDifferentAppidAndParameterUnameAndParameterPasswd() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";

        ResetPassBO resetPassBO = new ResetPassBO();
        //重置用户uid=239098的密码
        resetPassBO.setAppid("1.00002");
        resetPassBO.setPasswd("123456");
        resetPassBO.setUname("wx_wnkiehde");
        resetPassTest.resetPass(resetPassBO);
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("wx_wnkiehde");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            userloginResult = Login(userLoginBO);
            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            //第二次请求前，修改appid=1.00002的euid与GroupTag值与appid=1.00003的euid与GroupTag值一样
            changeEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
            //请求完成后，恢复appid=1.00002默认的euid与GroupTag值
            restoreEuidAndGroupTagByAppid();
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用普通的用户名与密码进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);

            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult, UserBindBO.class).getKeys().get(0);
            String UnionidAppid = JSON.parseObject(keysBean.getUnionid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
        }
    }

    /**
     * 使用普通的用户名与密码进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     *
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByDifferentEuidAndDifferentGroupTagAndDifferentAppidAndParameterUnameAndParameterPasswd() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";

        ResetPassBO resetPassBO = new ResetPassBO();
        //重置用户uid=239098的密码
        resetPassBO.setAppid("1.00002");
        resetPassBO.setPasswd("123456");
        resetPassBO.setUname("wx_wnkiehde");
        resetPassTest.resetPass(resetPassBO);
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname("wx_wnkiehde");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
//            changeEuidAndGroupTagByAppid();
            userloginResult = Login(userLoginBO);
            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用普通的用户名与密码进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);

            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult, UserBindBO.class).getKeys().get(0);
            String UnionidAppid = JSON.parseObject(keysBean.getUnionid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
        }
    }

    /**
     * 使用token进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     *
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestBySameEuidAndSameGroupTagAndDifferentAppidAndParameterUnameValueIsTokenAndParameterKeytpValueIsToken() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";

        ResetPassBO resetPassBO = new ResetPassBO();
        //重置用户uid=239098的密码
        resetPassBO.setAppid("1.00002");
        resetPassBO.setPasswd("123456");
        resetPassBO.setUname("wx_wnkiehde");
        resetPassTest.resetPass(resetPassBO);
        userLoginBO = new UserLoginBO();
        userLoginBO.setUname("wx_wnkiehde");
        userLoginBO.setPasswd("123456");
        userLoginBO.setAppid("1.00002");
        userLoginBO.setKeytp("");
        userLoginBO.setKeys(new ArrayList<UserLoginBO.Keys>() {{
            new UserLoginBO.Keys();
        }});
        userLoginBO.setThirdlogin(false);
        userloginResult = Login(userLoginBO);
        String token = (String) JSON.parseObject(userloginResult).get("token");
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname(token);
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("token");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
            userloginResult = Login(userLoginBO);
            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            //第二次请求前，修改appid=1.00002的euid与GroupTag值与appid=1.00003的euid与GroupTag值一样
            changeEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
            //请求完成后，恢复appid=1.00002默认的euid与GroupTag值
            restoreEuidAndGroupTagByAppid();
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用token进行登录，appid=1.00002与appid=1.00003均属于同一个企业euid下相同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);

            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult, UserBindBO.class).getKeys().get(0);
            String UnionidAppid = JSON.parseObject(keysBean.getUnionid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
        }
    }

    /**
     * 使用token进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录
     *
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByDifferentEuidAndDifferentGroupTagAndDifferentAppidAndParameterUnameValueIsTokenAndParameterKeytpValueIsToken() throws Exception {
        initData_();
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = "";
        ResetPassBO resetPassBO = new ResetPassBO();
        //重置用户uid=239098的密码
        resetPassBO.setAppid("1.00002");
        resetPassBO.setPasswd("123456");
        resetPassBO.setUname("wx_wnkiehde");
        resetPassTest.resetPass(resetPassBO);
        userLoginBO = new UserLoginBO();
        userLoginBO.setUname("wx_wnkiehde");
        userLoginBO.setPasswd("123456");
        userLoginBO.setAppid("1.00002");
        userLoginBO.setKeytp("");
        userLoginBO.setKeys(new ArrayList<UserLoginBO.Keys>() {{
            new UserLoginBO.Keys();
        }});
        userLoginBO.setThirdlogin(false);
        userloginResult = Login(userLoginBO);
        String token = (String) JSON.parseObject(userloginResult).get("token");

        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            //每次创建，在更新nickname1值+1
            userLoginBO.setUname(token);
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            //4种形式值：deviceid、openid、unionid、cacc
            userLoginBO.setKeytp("token");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setNickname("yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            //首次登录使用appid=1.00002
//            changeEuidAndGroupTagByAppid();
            userloginResult = Login(userLoginBO);
            userLoginBO.setAppid("1.00003");
            //第二次登录使用appid=1.00003
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
            destroyData_();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用token进行登录，appid=1.00002与appid=1.00003分别属于不用企业euid下不同分组group_tag,第一次使用appid=1.00002登录，第二次使用appid=1.00003登录，结果是变更表t_userkeys的appid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //判断登录后，流水是否存在
            SysAccFlowExample example = new SysAccFlowExample();
            SysAccFlowExample.Criteria cr = example.createCriteria();
            cr.andSessionCodeEqualTo(JsonPath.read(userloginResult, "$.sessionCode"));
            List<SysAccFlow> sysAccFlowList = sysAccFlowRepository.selectByExample(example);
            Assert.assertTrue(sysAccFlowList.size() == 1);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);

            UserBindBO.KeysBean keysBean = JSON.parseObject(userloginResult, UserBindBO.class).getKeys().get(0);
            String UnionidAppid = JSON.parseObject(keysBean.getUnionid(), UserBindTest.JsonRootBean.class).getValue().get(0).getAppid();
            Assert.assertTrue(UnionidAppid.equals("1.00003"));
        }
    }


    /**
     * 新增用户注册，增加流水记录,登录携带参数statistics，channelNo长度太长内且不为空
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByAddNewUserAndParameterStatisticsNotEmptyAndChannelNoIsLong() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam);
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增用户注册，增加流水记录,登录携带参数statistics，channelNo长度太长内且不为空");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //新增失败断言
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":100"), true);
        }
    }

    /**
     * 用户登录，增加流水记录,登录携带参数statistics，channelNo长度正常
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByParameterStatisticsNotEmptyAndChannelNoIsNormal() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            statistics.put("channelNo", "channelNo长度正常");
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户登录，增加流水记录,登录携带参数statistics，channelNo长度正常");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //新增失败断言
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 用户登录，增加流水记录,登录携带参数statistics，channelNo长度正常,模拟在线时长生成,在表userdb.sys_acc_flow.online_time不为0
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByParameterStatisticsNotEmptyAndChannelNoIsNormalAndGenerateOnlineTime() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            statistics.put("channelNo", "channelNo长度正常");
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
            String sessionCode = JsonPath.read(userloginResult, "$.sessionCode");
            Long uid = JsonPath.read(userloginResult, "$.uid");
            Thread.sleep(60000);
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setAppid(userLoginBO.getAppid());
            logOutBO.setSessionCode(sessionCode);
            logOutBO.setUid(uid);
            logOutTest.logoutTestByGernal(logOutBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户登录，增加流水记录,登录携带参数statistics，channelNo长度正常,模拟在线时长生成,在表userdb.sys_acc_flow.online_time不为0");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //新增失败断言
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 新增用户注册，增加流水记录,登录携带参数statistics且channelNo长度正常范围内且不为空
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserTestByAddNewUserAndParameterStatisticsNotEmptyAndChannelNoIsShort() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增用户注册，增加流水记录,登录携带参数statistics且channelNo长度正常范围内且不为空");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //新增用户数据删除
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            Long uid = Long.valueOf(uidStr);
            TUserExample tUserExample = new TUserExample();
            TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
            tUserExamplecr.andUidEqualTo(uid);
            List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
            //新增用户登录时候携带参数statistics，则Channelno不为空
            Assert.assertNotNull(listTUser.get(0).getChannelNo());

            tUserRepository.deleteByExample(tUserExample);

            TKeyindexExample tKeyindexExample = new TKeyindexExample();
            TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
            tKeyindexExamplecr.andUidEqualTo(uid);
            tKeyindexRepository.deleteByExample(tKeyindexExample);

            TUserkeysExample tUserkeysExample = new TUserkeysExample();
            TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
            tUserkeysExamplecr.andUidEqualTo(uid);
            tUserkeysRepository.deleteByExample(tUserkeysExample);

            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("newUser"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
        }
    }

    /**
     * 普通用户设置账户数量到达上限，通过unionid新增普通用户注册，则新增失败
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndReacheUpperLimit1() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过unionid新增普通用户注册，则新增失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            //新增用户数据删除
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(uidStr!=null){
                if(!uidStr.equals(0)){
                    Long uid = Long.valueOf(uidStr);
                    TUserExample tUserExample = new TUserExample();
                    TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                    tUserExamplecr.andUidEqualTo(uid);
                    List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                    //新增用户登录时候携带参数statistics，则Channelno不为空
                    Assert.assertNotNull(listTUser.get(0).getChannelNo());

                    tUserRepository.deleteByExample(tUserExample);

                    TKeyindexExample tKeyindexExample = new TKeyindexExample();
                    TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                    tKeyindexExamplecr.andUidEqualTo(uid);
                    tKeyindexRepository.deleteByExample(tKeyindexExample);

                    TUserkeysExample tUserkeysExample = new TUserkeysExample();
                    TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                    tUserkeysExamplecr.andUidEqualTo(uid);
                    tUserkeysRepository.deleteByExample(tUserkeysExample);
                }
            }
            Assert.assertEquals(userloginResult.contains("\"result\":139"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"user reaches upper limit\""), true);
        }
    }


    /**
     * 普通用户设置账户数量到达上限，通过账号与密码登录，则登录成功
     * @throws Exception
     */
    @Test
    public void LoginByExistUserAndParameterNormalUpperLimitAndReacheUpperLimit1() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
//普通用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("wx_fbqejw0x");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//        keys.setInfo("bindNo1forOpenid");
//        keys.setNickname("NBind_openid_0812_01");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过账号与密码登录，则登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("nickname"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":true"), true);
        }
    }


    /**
     * 普通用户设置账户数量到达上限，通过unionid登录，则登录成功
     * @throws Exception
     */
    @Test
    public void LoginByExistUserAndParameterNormalUpperLimitAndReacheUpperLimit2() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
//普通用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setUnionid("Unionid_yy");
//            keys.setUnionid("Cacc_yy");
//            keys.setUnionid("Openid_yy");
//            keys.setUnionid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + JSON.parseObject(userloginResult));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过unionid登录，则登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(uidStr!=null){
                if(!uidStr.equals(0)){
                    Long uid = Long.valueOf(uidStr);
                    TUserExample tUserExample = new TUserExample();
                    TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                    tUserExamplecr.andUidEqualTo(uid);
                    List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                    //新增用户登录时候携带参数statistics，则Channelno不为空
                    Assert.assertNotNull(listTUser.get(0).getChannelNo());

                    tUserRepository.deleteByExample(tUserExample);

                    TKeyindexExample tKeyindexExample = new TKeyindexExample();
                    TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                    tKeyindexExamplecr.andUidEqualTo(uid);
                    tKeyindexRepository.deleteByExample(tKeyindexExample);

                    TUserkeysExample tUserkeysExample = new TUserkeysExample();
                    TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                    tUserkeysExamplecr.andUidEqualTo(uid);
                    tUserkeysRepository.deleteByExample(tUserkeysExample);
                }
            }
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":true"), true);
        }
    }


    /**
     * 普通用户设置账户数量到达上限，通过cacc登录，则登录成功
     * @throws Exception
     */
    @Test
    public void LoginByExistUserAndParameterNormalUpperLimitAndReacheUpperLimit3() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
//普通用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Cacc_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("cacc");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setUnionid("Unionid_yy");
            keys.setCacc("Cacc_yy");
//            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + JSON.parseObject(userloginResult));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过cacc登录，则登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(uidStr!=null){
                if(!uidStr.equals(0)){
                    Long uid = Long.valueOf(uidStr);
                    TUserExample tUserExample = new TUserExample();
                    TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                    tUserExamplecr.andUidEqualTo(uid);
                    List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                    //新增用户登录时候携带参数statistics，则Channelno不为空
                    Assert.assertNotNull(listTUser.get(0).getChannelNo());

                    tUserRepository.deleteByExample(tUserExample);

                    TKeyindexExample tKeyindexExample = new TKeyindexExample();
                    TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                    tKeyindexExamplecr.andUidEqualTo(uid);
                    tKeyindexRepository.deleteByExample(tKeyindexExample);

                    TUserkeysExample tUserkeysExample = new TUserkeysExample();
                    TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                    tUserkeysExamplecr.andUidEqualTo(uid);
                    tUserkeysRepository.deleteByExample(tUserkeysExample);
                }
            }
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":true"), true);
        }
    }

    /**
     * 普通用户设置账户数量到达上限，通过openid登录，则登录成功
     * @throws Exception
     */
    @Test
    public void LoginByExistUserAndParameterNormalUpperLimitAndReacheUpperLimit4() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
//普通用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Openid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            keys.setOpenid("Openid_yy");
//            keys.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + JSON.parseObject(userloginResult));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过openid登录，则登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(uidStr!=null){
                if(!uidStr.equals(0)){
                    Long uid = Long.valueOf(uidStr);
                    TUserExample tUserExample = new TUserExample();
                    TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                    tUserExamplecr.andUidEqualTo(uid);
                    List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                    //新增用户登录时候携带参数statistics，则Channelno不为空
                    Assert.assertNotNull(listTUser.get(0).getChannelNo());

                    tUserRepository.deleteByExample(tUserExample);

                    TKeyindexExample tKeyindexExample = new TKeyindexExample();
                    TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                    tKeyindexExamplecr.andUidEqualTo(uid);
                    tKeyindexRepository.deleteByExample(tKeyindexExample);

                    TUserkeysExample tUserkeysExample = new TUserkeysExample();
                    TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                    tUserkeysExamplecr.andUidEqualTo(uid);
                    tUserkeysRepository.deleteByExample(tUserkeysExample);
                }
            }
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":true"), true);
        }
    }


    /**
     * 普通用户设置账户数量到达上限，通过deviceid登录，则登录成功
     * @throws Exception
     */
    @Test
    public void LoginByExistUserAndParameterNormalUpperLimitAndReacheUpperLimit5() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
//普通用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Deviceid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("deviceid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
//            keys.setOpenid("Openid_yy");
            keys.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + JSON.parseObject(userloginResult));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过deviceid登录，则登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(uidStr!=null){
                if(!uidStr.equals(0)){
                    Long uid = Long.valueOf(uidStr);
                    TUserExample tUserExample = new TUserExample();
                    TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                    tUserExamplecr.andUidEqualTo(uid);
                    List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                    //新增用户登录时候携带参数statistics，则Channelno不为空
                    Assert.assertNotNull(listTUser.get(0).getChannelNo());

                    tUserRepository.deleteByExample(tUserExample);

                    TKeyindexExample tKeyindexExample = new TKeyindexExample();
                    TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                    tKeyindexExamplecr.andUidEqualTo(uid);
                    tKeyindexRepository.deleteByExample(tKeyindexExample);

                    TUserkeysExample tUserkeysExample = new TUserkeysExample();
                    TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                    tUserkeysExamplecr.andUidEqualTo(uid);
                    tUserkeysRepository.deleteByExample(tUserkeysExample);
                }
            }
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":true"), true);
        }
    }


    /**
     * 普通用户设置账户数量未达到上限，通过unionid新增普通用户注册，则新增成功
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndNotReacheUpperLimit1() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 999999);
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量未达到上限，新增普通用户注册，则新增成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            //新增用户数据删除
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(!uidStr.equals(0)){
                Long uid = Long.valueOf(uidStr);
                TUserExample tUserExample = new TUserExample();
                TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                tUserExamplecr.andUidEqualTo(uid);
                List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                //新增用户登录时候携带参数statistics，则Channelno不为空
                Assert.assertNotNull(listTUser.get(0).getChannelNo());

                tUserRepository.deleteByExample(tUserExample);

                TKeyindexExample tKeyindexExample = new TKeyindexExample();
                TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                tKeyindexExamplecr.andUidEqualTo(uid);
                tKeyindexRepository.deleteByExample(tKeyindexExample);

                TUserkeysExample tUserkeysExample = new TUserkeysExample();
                TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                tUserkeysExamplecr.andUidEqualTo(uid);
                tUserkeysRepository.deleteByExample(tUserkeysExample);
            }
            //普通用户数量超过上限后，继续新增普通用户上限则注册失败
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":false"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        }
    }


    /**
     * 普通用户设置账户数量到达上限，通过cacc新增普通用户注册，则新增失败
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndReacheUpperLimit2() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Cacc_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("cacc");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过cacc新增普通用户注册，则新增失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"user reaches upper limit\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":139"), true);
        }
    }

    /**
     * 普通用户设置账户数量未达到上限，通过cacc新增普通用户注册，则新增成功
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndNotReacheUpperLimit2() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Cacc_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("cacc");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 999999);
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量未达到上限，通过cacc新增普通用户注册，则新增成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            //新增用户数据删除
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(!uidStr.equals(0)){
                Long uid = Long.valueOf(uidStr);
                TUserExample tUserExample = new TUserExample();
                TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                tUserExamplecr.andUidEqualTo(uid);
                List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                //新增用户登录时候携带参数statistics，则Channelno不为空
                Assert.assertNotNull(listTUser.get(0).getChannelNo());

                tUserRepository.deleteByExample(tUserExample);

                TKeyindexExample tKeyindexExample = new TKeyindexExample();
                TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                tKeyindexExamplecr.andUidEqualTo(uid);
                tKeyindexRepository.deleteByExample(tKeyindexExample);

                TUserkeysExample tUserkeysExample = new TUserkeysExample();
                TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                tUserkeysExamplecr.andUidEqualTo(uid);
                tUserkeysRepository.deleteByExample(tUserkeysExample);
            }
            //普通用户数量超过上限后，继续新增普通用户上限则注册失败
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":false"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        }
    }

    /**
     * 普通用户设置账户数量到达上限，通过openid新增普通用户注册，则新增失败
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndReacheUpperLimit3() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Openid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过openid新增普通用户注册，则新增失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"user reaches upper limit\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":139"), true);
        }
    }

    /**
     * 普通用户设置账户数量未达到上限，通过openid新增普通用户注册，则新增成功
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndNotReacheUpperLimit3() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Openid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("openid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 999999);
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量未达到上限，通过openid新增普通用户注册，则新增成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            //新增用户数据删除
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(!uidStr.equals(0)){
                Long uid = Long.valueOf(uidStr);
                TUserExample tUserExample = new TUserExample();
                TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                tUserExamplecr.andUidEqualTo(uid);
                List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                //新增用户登录时候携带参数statistics，则Channelno不为空
                Assert.assertNotNull(listTUser.get(0).getChannelNo());

                tUserRepository.deleteByExample(tUserExample);

                TKeyindexExample tKeyindexExample = new TKeyindexExample();
                TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                tKeyindexExamplecr.andUidEqualTo(uid);
                tKeyindexRepository.deleteByExample(tKeyindexExample);

                TUserkeysExample tUserkeysExample = new TUserkeysExample();
                TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                tUserkeysExamplecr.andUidEqualTo(uid);
                tUserkeysRepository.deleteByExample(tUserkeysExample);
            }
            //普通用户数量超过上限后，继续新增普通用户上限则注册失败
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":false"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        }
    }


    /**
     * 普通用户设置账户数量到达上限，通过deviceid新增普通用户注册，则新增失败
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndReacheUpperLimit4() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Deviceid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("deviceid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);

            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量到达上限，通过deviceid新增普通用户注册，则新增失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"user reaches upper limit\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":139"), true);
        }
    }

    /**
     * 普通用户设置账户数量未达到上限，通过deviceid新增普通用户注册，则新增成功
     * @throws Exception
     */
    @Test
    public void LoginByAddNewUserAndParameterNormalUpperLimitAndNotReacheUpperLimit4() throws Exception {
        String logoutResult = null;
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Deviceid_yy");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00003");//
            userLoginBO.setKeytp("deviceid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
//            keys.setOpenid("Openid_yy");
        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//            keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(getFilePath("2.png")));
            statistics.put("channelNo", imgParam.substring(0, 20));
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 999999);
            //设置appid=1.00002与appid=1.00003属于不同企业下与不同分组。此时换作不同的appid就是新增用户
            restoreEuidAndGroupTagByAppid();
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量未达到上限，通过deviceid新增普通用户注册，则新增成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            //新增用户数据删除
            Integer uidStr = (Integer) JSON.parseObject(userloginResult).get("uid");
            if(!uidStr.equals(0)){
                Long uid = Long.valueOf(uidStr);
                TUserExample tUserExample = new TUserExample();
                TUserExample.Criteria tUserExamplecr = tUserExample.createCriteria();
                tUserExamplecr.andUidEqualTo(uid);
                List<TUser> listTUser = tUserRepository.selectByExample(tUserExample);
                //新增用户登录时候携带参数statistics，则Channelno不为空
                Assert.assertNotNull(listTUser.get(0).getChannelNo());

                tUserRepository.deleteByExample(tUserExample);

                TKeyindexExample tKeyindexExample = new TKeyindexExample();
                TKeyindexExample.Criteria tKeyindexExamplecr = tKeyindexExample.createCriteria();
                tKeyindexExamplecr.andUidEqualTo(uid);
                tKeyindexRepository.deleteByExample(tKeyindexExample);

                TUserkeysExample tUserkeysExample = new TUserkeysExample();
                TUserkeysExample.Criteria tUserkeysExamplecr = tUserkeysExample.createCriteria();
                tUserkeysExamplecr.andUidEqualTo(uid);
                tUserkeysRepository.deleteByExample(tUserkeysExample);
            }
            //普通用户数量超过上限后，继续新增普通用户上限则注册失败
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":false"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        }
    }


    /**
     * 普通用户设置账户数量上限，通过用户名与密码登录检查upperLimitNum与upperLimitState
     * @throws Exception
     */
    @Test
    public void LoginTestByLoginExistUserAndParameterNormalUpperLimitAndUnameLoginAndPasswdLogin() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("wx_fbqejw0x");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 5);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户设置账户数量上限，通过用户名与密码登录检查upperLimitNum与upperLimitState");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), null, null, 0);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("\"upperLimitState\":true"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
        }
    }

    /**
     * 首次登录uname为空，ketp为anonymity，使用雪花算法生成uid，在t_temp_uid 中记录该记录
     * 雪花首次登录，会在表t_temp_user生成一条记录
     * 雪花首次登录，新增用户，uid为19位，雪花登录不支持参数Statistics传递
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserXueHuaAndNotExistParameterStatistics() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("anonymity");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_xuehua");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("雪花首次登录，新增用户，uid为19位，雪花登录不支持参数Statistics传递");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //新增用户数据删除
            Long uidLong = JsonPath.read(userloginResult, "$.uid");
            String uidStr = String.valueOf(uidLong);
            String token =JsonPath.read(userloginResult, "$.token");
            if(token.isEmpty()|| token==null){
                Assert.assertTrue(false,"雪花登录获取token失败");
            }
            jedisTemplate.set("XueHuaToken",token,24*3600);
            Assert.assertTrue(uidStr.length() == 19);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
        }
    }


    /**
     * 雪花非首次登录，使用Keytp=anonymity登录，返回登录成功
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserXueHuaAndNotExistParameterStatisticsAndRepeatLogin() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname((String) jedisTemplate.get("XueHuaToken"));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("anonymity");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_xuehua");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("雪花非首次登录，使用Keytp=anonymity登录，返回登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            //新增用户数据删除
            Long uidLong = JsonPath.read(userloginResult, "$.uid");
            String uidStr = String.valueOf(uidLong);
            Assert.assertTrue(uidStr.length() == 19);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
        }
    }

    /**
     * 雪花非首次登录，使用Keytp=token登录,返回登录失败
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserXueHuaAndNotExistParameterStatisticsAndParameterKeytpValueIsWrong() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname((String) jedisTemplate.get("XueHuaToken"));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("token");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_xuehua");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("雪花非首次登录，使用Keytp=token登录,返回登录失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Long uidLong = JsonPath.read(userloginResult, "$.uid");
            String uidStr = String.valueOf(uidLong);

            Assert.assertTrue(uidStr.length() == 19);
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
        }
    }


    /**
     * 雪花登录，传递参数Statistics，不记录账户流水，表userdb.sys_acc_flow、userdb.sys_log无记录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserXueHuaAndExistParameterStatisticsAndParameterKeytpValueIsWrong() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname((String) jedisTemplate.get("XueHuaToken"));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("token");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_xuehua");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            Map<String, String> statistics = new HashMap<>();
            statistics.put("channelNo", "微信小程序客户端登录，使用Unionid进行登录，然后自动新增用户");
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            userLoginBO.setStatistics(statistics);
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("雪花非首次登录，使用Keytp=token登录,返回登录失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Long uidLong = JsonPath.read(userloginResult, "$.uid");
            String uidStr = String.valueOf(uidLong);
            Assert.assertTrue(uidStr.length() == 19);
            SysAccFlowExample sysAccFlowExample=new SysAccFlowExample();
            sysAccFlowExample.createCriteria().andUidEqualTo(uidLong);
            List<SysAccFlow> sysAccFlowList=sysAccFlowRepository.selectByExample(sysAccFlowExample);
            Assert.assertTrue(sysAccFlowList.size()==0);
            Assert.assertTrue(sysAccFlowList.isEmpty());
            SysLogExample sysLogExample=new SysLogExample();
            sysLogExample.createCriteria().andUidEqualTo(uidLong);
            List<SysLog> sysLogList=sysLogRepository.selectByExample(sysLogExample);
            Assert.assertTrue(sysLogList.size()==0);
            Assert.assertTrue(sysLogList.isEmpty());
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
        }
    }




    /**
     * 游客登录，使用Keytp=guest登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserByParameterKeytpValueIsGuest() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;

        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("guest");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            tGuestUserRepository.deleteAll();
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客登录，使用Keytp=guest登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });

            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("newUser"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        }
    }

    /**
     * 未过期的游客，可以登录
     * @throws Exception
     */
    @Test
    public void LoginByExistUserByParameterKeytpValueIsGuest() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
            userLoginBO.setUname("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            userLoginBO.setKeytp("guest");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_yy");
//        keys.setDeviceid("Deviceid_yy");
//        keys.setUnionid("Unionid_yy");
//        keys.setCacc("Cacc_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + JSON.parseObject(userloginResult));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + JSON.parseObject(userloginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客登录，使用Keytp=guest登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Long uidStr = (Long) JSON.parseObject(userloginResult).get("uid");
            if(!uidStr.equals(0)){
                TGuestUserExample tGuestUserExample = new TGuestUserExample();
                TGuestUserExample.Criteria tGuestUserExamplecr = tGuestUserExample.createCriteria();
                tGuestUserExamplecr.andUidEqualTo(uidStr);
                tGuestUserRepository.deleteByExample(tGuestUserExample);
            }
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("newUser"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        }
    }


    /**
     * 游客登录，使用Keytp=guest登录,游客登录后在登出,然后再次登录
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserByGuestLogInAndGuestLogoutAndLogin() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        List<TGuestUser> userloginQueryResult = null;
        List<TGuestUser> userlogoutQueryResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
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
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            tGuestUserRepository.deleteAll();
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));
            Long uid = JsonPath.read(userloginResult, "$.uid");
            TGuestUserExample selectExample = new TGuestUserExample();
            selectExample.createCriteria().andUnameEqualTo(userLoginBO.getUname());
            userloginQueryResult = tGuestUserRepository.selectByExample(selectExample);
            LogOutBO logOutBO = new LogOutBO();
            logOutBO.setUid(uid);
            logOutBO.setSessionCode(null);
            logOutBO.setAppid(userLoginBO.getAppid());
            logOutBO.setKeytp("guest");
            logOutTest.logoutTestByGernal(logOutBO);
            userlogoutQueryResult = tGuestUserRepository.selectByExample(selectExample);
            userloginResult = Login(userLoginBO);
            userloginQueryResult = tGuestUserRepository.selectByExample(selectExample);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客登录，使用Keytp=guest登录,游客登录后在登出,然后再次登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("newUser"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertTrue(userloginQueryResult.get(0).getStatus().equals(1));
            Assert.assertTrue(userlogoutQueryResult.get(0).getStatus().equals(-1));
        }
    }

    /**
     * 游客首次登录,返回结果newUser=true
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserAndGuestFirstLogIn() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        List<TGuestUser> userloginQueryResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
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

            TGuestUserExample deleteExample = new TGuestUserExample();
            deleteExample.createCriteria().andUnameEqualTo(userLoginBO.getUname());
            tGuestUserRepository.deleteByExample(deleteExample);

            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + JSON.parseObject(userloginResult));

            userloginQueryResult = tGuestUserRepository.selectByExample(deleteExample);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客首次登录,返回结果newUser=true");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertTrue(userloginQueryResult.get(0).getStatus().equals(1));
        }
    }

    /**
     * 游客非首次登录,返回结果newUser=false
     * @throws Exception
     */
    @Test
    public void LoginByGuestNotFirstLogIn() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult1 = null;
        String userloginResult2 = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            //用户登录
            userLoginBO = new UserLoginBO();
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
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult1 = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + userloginResult1);
            userloginResult2 = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + userloginResult2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("游客非首次登录,返回结果newUser=false");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            Long uid1=JsonPath.read(userloginResult1,"$.uid");
            Long uid2=JsonPath.read(userloginResult2,"$.uid");
            Assert.assertTrue(uid1.equals(uid2));
            Assert.assertEquals(userloginResult2.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult2.contains("loginTime"), true);
            Assert.assertEquals(userloginResult2.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            Assert.assertEquals(userloginResult2.contains("total"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult2.contains("upperLimitState"), true);
        }
    }

    /**
     * 参数guestUpperLimit未设置或者设置为0，游客新增累加，则返回的total值为累加
     *
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserAndSummationAndParameterGuestUpperLimitNotSetOrSetZero() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_guest");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), 0, 0, null);
            tGuestUserRepository.deleteAll();
            for (int i = 0; i < 10; i++) {
                userLoginBO.setUname("guest" + i);
                userloginResult = Login(userLoginBO);
                log.info("userloginResult 返回结果1=" + userloginResult);
                Integer total = JsonPath.read(userloginResult, "$.total");
                Integer tmp = i;
                Assert.assertTrue(tmp.equals(total - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数guestUpperLimit未设置或者设置为0，游客新增累加，则返回的total值为累加");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        }
    }


    /**
     * 参数guestUpperLimit设置为5，超过设置上限后新增游客则失败
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserAndParameterGuestUpperLimitSetFive() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_guest");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), 5, null, null);
            tGuestUserRepository.deleteAll();
            for (int i = 0; i < 10; i++) {
                if(i==6){
                    break;
                }
                userLoginBO.setUname("guest" + i);
                userloginResult = Login(userLoginBO);
                log.info("userloginResult 返回结果1=" + userloginResult);
                Integer total = JsonPath.read(userloginResult, "$.total");
                Integer tmp = i;
                Assert.assertTrue(tmp.equals(total - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数guestUpperLimit设置为5，超过设置上限后新增游客则失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"user reaches upper limit\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":139"), true);
        }
    }



    /**
     * 参数guestUpperLimit设置为5，超过设置上限后新增游客则失败，未过期的原来游客可以登录成功
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserAndParameterGuestUpperLimitSetFiveAndBeforeGuestLogin1() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_guest");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), 5, null, null);
            tGuestUserRepository.deleteAll();
            Integer total =0;
            for (int i = 0; i < 10; i++) {
                userLoginBO.setUname("guest" + i);
                userloginResult = Login(userLoginBO);
                log.info("userloginResult 返回结果1=" + userloginResult);
                if(userloginResult.contains("total")){
                    total = JsonPath.read(userloginResult, "$.total");
                }
                Integer tmp = i;
                if (!tmp.equals(total - 1)) {
                    break;
                }
            }
            userLoginBO.setUname("guest0");
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数guestUpperLimit设置为5，超过设置上限后新增游客则失败，未过期的原来游客可以登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
        }
    }

    /**
     * 参数guestUpperLimit设置为5，超过设置上限后新增游客则失败
     * @throws Exception
     */
    @Test
    public void LoginByCreateUserAndParameterGuestUpperLimitSetFiveAndBeforeGuestLogin2() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_guest");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), 5, null, null);
            tGuestUserRepository.deleteAll();
            Integer total =0;
            for (int i = 0; i < 10; i++) {
                userLoginBO.setUname("guest" + i);
                userloginResult = Login(userLoginBO);
                log.info("userloginResult 返回结果1=" + userloginResult);
                if(userloginResult.contains("total")){
                    total = JsonPath.read(userloginResult, "$.total");
                }
                Integer tmp = i;
                if (!tmp.equals(total - 1)) {
                    break;
                }
            }
            log.info("userloginResult 返回结果=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数guestUpperLimit设置为5，超过设置上限后新增游客则失败，未过期的原来游客可以登录成功");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"user reaches upper limit\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":139"), true);
        }
    }

    /**
     * 超过有效期的游客未被清理登录失败
     * @throws Exception
     */
    @Test
    public void LoginByParameterGuestPeriodValidSetOneAndNowMoreThanGuestPeriodValidValue1() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            tGuestUserRepository.deleteAll();
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_guest");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userLoginBO.setUname("guest");
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), 10, 1, null);
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + userloginResult);
            Date beforeDate = DateTool.parseDateStr(DateTool.addDays(new Date(), -20), DateTool.DATE_FMT_5);

            TGuestUser updateRecord=new TGuestUser();
            updateRecord.setLoginTime(beforeDate);
            updateRecord.setCreateTime(beforeDate);
            updateRecord.setLogoutTime(beforeDate);
            TGuestUserExample updateExample=new TGuestUserExample();
            updateExample.createCriteria().andUnameEqualTo(userLoginBO.getUname());
            tGuestUserRepository.updateByExampleSelective(updateRecord,updateExample);
            userLoginBO.setUname("guest");
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超过有效期的游客未被清理登录失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
        }
    }


    /**
     * 超过有效期的游客已被清理登录失败
     * @throws Exception
     */
    @Test
    public void LoginByParameterGuestPeriodValidSetOneAndNowMoreThanGuestPeriodValidValue2() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            tGuestUserRepository.deleteAll();
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("guest");
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");//
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Openid_guest");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userLoginBO.setUname("guest");
            modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(userLoginBO.getAppid(), 10, 1, null);
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + userloginResult);
            Date beforeDate = DateTool.parseDateStr(DateTool.addDays(new Date(), -20), DateTool.DATE_FMT_5);

            TGuestUser updateRecord=new TGuestUser();
            updateRecord.setLoginTime(beforeDate);
            updateRecord.setCreateTime(beforeDate);
            updateRecord.setLogoutTime(beforeDate);
            updateRecord.setDeleteTime(beforeDate);
            TGuestUserExample updateExample=new TGuestUserExample();
            updateExample.createCriteria().andUnameEqualTo(userLoginBO.getUname());
            tGuestUserRepository.updateByExampleSelective(updateRecord,updateExample);
            userLoginBO.setUname("guest");
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("超过有效期的游客未被清理登录失败");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
        }
    }


    /**
     * 通过keytp=tencent方式进行登录,同时使用unionid与openid登录,首次登录
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndNewUserAndFirstLogin() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO.setUname(JSON.toJSONString(unameJson));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录,同时使用unionid与openid登录,首次登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("unionid"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("openid"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("deviceid"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("cacc"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Cacc_yy\""), true);
        }
    }



    /**
     * 通过keytp=tencent方式进行登录,同时使用unionid与openid登录.非首次登录
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndExistUserAndNotFirstLogin() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO.setUname(JSON.toJSONString(unameJson));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + userloginResult);
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录,同时使用unionid与openid登录.非首次登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult.contains("uid"), true);
            Assert.assertEquals(userloginResult.contains("total"), true);
            Assert.assertEquals(userloginResult.contains("loginTime"), true);
            Assert.assertEquals(userloginResult.contains("loginValue"), true);
            Assert.assertEquals(userloginResult.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult.contains("uname"), true);
            Assert.assertEquals(userloginResult.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult.contains("token"), true);
            Assert.assertEquals(userloginResult.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult.contains("unionid"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult.contains("openid"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("deviceid"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult.contains("keys"), true);
            Assert.assertEquals(userloginResult.contains("cacc"), true);
            Assert.assertEquals(userloginResult.contains("\"value\":\"Cacc_yy\""), true);
        }
    }

    /**
     * 通过keytp=tencent方式进行登录,参数uname的值为空字符串
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndParameterUnameIsEmptyAndNotFirstLogin() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO = null;
        String userloginResult = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO = new UserLoginBO();
            userLoginBO.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO.setUname(JSON.toJSONString(unameJson));
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO 请求的参数=" + JSON.toJSONString(userLoginBO));
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果1=" + userloginResult);
            userLoginBO.setUname("");
            userloginResult = Login(userLoginBO);
            log.info("userloginResult 返回结果2=" + userloginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录,参数uname的值为空字符串");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO));
            recordhttp.setResponse(userloginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult.contains("\"msg\":\"uname_error\""), true);
            Assert.assertEquals(userloginResult.contains("\"result\":102"), true);
        }
    }


    /**
     * 通过keytp=tencent方式进行登录后,然后使用token进行登录
     * @throws Exception
     */
    @Test
    public void LoginByParameterUnameValueIsTokenAndParameterKeytpValueIsTencent() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO1 = null;
        UserLoginBO userLoginBO2 =null;
        String userloginResult1 = null;
        String userloginResult2 = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO1 = new UserLoginBO();
            userLoginBO1.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO1.setUname(JSON.toJSONString(unameJson));
            userLoginBO1.setPasswd("");
            userLoginBO1.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO1.setKeys(list);
            userLoginBO1.setThirdlogin(false);
            userLoginBO1.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO1 请求的参数=" + JSON.toJSONString(userLoginBO1));
            userloginResult1 = Login(userLoginBO1);
            log.info("userloginResult1 返回结果1=" + userloginResult1);

            String token=JsonPath.read(userloginResult1,"$.token");
            userLoginBO2 = new UserLoginBO();
            userLoginBO2.setKeytp("token");
            userLoginBO2.setUname(token);
            userLoginBO2.setPasswd("");
            userLoginBO2.setAppid("1.00002");
            UserLoginBO.Keys keys3 = new UserLoginBO.Keys();
            keys3.setCacc("Cacc_yy");
            UserLoginBO.Keys keys4 = new UserLoginBO.Keys();
            keys4.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list2 = new ArrayList<>();
            list2.add(keys3);
            list2.add(keys4);
            userLoginBO2.setKeys(list2);
            userLoginBO2.setThirdlogin(false);
            userLoginBO2.setSeq(UUID.randomUUID().toString());
            userloginResult2 = Login(userLoginBO2);
            log.info("userloginResult2 返回结果2=" + userloginResult2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录,同时使用unionid与openid登录");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO2));
            recordhttp.setResponse(userloginResult2);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult2.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult2.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult2.contains("uid"), true);
            Assert.assertEquals(userloginResult2.contains("total"), true);
            Assert.assertEquals(userloginResult2.contains("loginTime"), true);
            Assert.assertEquals(userloginResult2.contains("loginValue"), true);
            Assert.assertEquals(userloginResult2.contains("\"newUser\":false"), true);
            Assert.assertEquals(userloginResult2.contains("uname"), true);
            Assert.assertEquals(userloginResult2.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult2.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult2.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult2.contains("token"), true);
            Assert.assertEquals(userloginResult2.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult2.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult2.contains("deviceid"), true);
            Assert.assertEquals(userloginResult2.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult2.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult2.contains("keys"), true);
            Assert.assertEquals(userloginResult2.contains("cacc"), true);
            Assert.assertEquals(userloginResult2.contains("\"value\":\"Cacc_yy\""), true);
        }
    }

    /**
     * 通过keytp=tencent方式进行登录后,uname使用参数的值为一个，只传递openid
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndNotExistValueUnionid() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO1 = null;
        String userloginResult1 = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO1 = new UserLoginBO();
            userLoginBO1.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
//            unameJson.setUnionid(unionidBean);
            userLoginBO1.setUname(JSON.toJSONString(unameJson));
            userLoginBO1.setPasswd("");
            userLoginBO1.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO1.setKeys(list);
            userLoginBO1.setThirdlogin(false);
            userLoginBO1.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO1 请求的参数=" + JSON.toJSONString(userLoginBO1));
            userloginResult1 = Login(userLoginBO1);
            log.info("userloginResult1 返回结果1=" + userloginResult1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录后,uname使用参数的值为一个，只传递openid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO1));
            recordhttp.setResponse(userloginResult1);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult1.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult1.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult1.contains("uid"), true);
            Assert.assertEquals(userloginResult1.contains("total"), true);
            Assert.assertEquals(userloginResult1.contains("loginTime"), true);
            Assert.assertEquals(userloginResult1.contains("loginValue"), true);
            Assert.assertEquals(userloginResult1.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult1.contains("uname"), true);
            Assert.assertEquals(userloginResult1.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult1.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult1.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult1.contains("token"), true);
            Assert.assertEquals(userloginResult1.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult1.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult1.contains("deviceid"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult1.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult1.contains("keys"), true);
            Assert.assertEquals(userloginResult1.contains("cacc"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Cacc_yy\""), true);
        }
    }


    /**
     * 通过keytp=tencent方式进行登录后,uname使用参数的值为一个，只传递unionid
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndNotExistValueOpenid() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO1 = null;
        String userloginResult1 = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO1 = new UserLoginBO();
            userLoginBO1.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
//            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO1.setUname(JSON.toJSONString(unameJson));
            userLoginBO1.setPasswd("");
            userLoginBO1.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO1.setKeys(list);
            userLoginBO1.setThirdlogin(false);
            userLoginBO1.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO1 请求的参数=" + JSON.toJSONString(userLoginBO1));
            userloginResult1 = Login(userLoginBO1);
            log.info("userloginResult1 返回结果1=" + userloginResult1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录后,uname使用参数的值为一个，只传递openid");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO1));
            recordhttp.setResponse(userloginResult1);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult1.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult1.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult1.contains("uid"), true);
            Assert.assertEquals(userloginResult1.contains("total"), true);
            Assert.assertEquals(userloginResult1.contains("loginTime"), true);
            Assert.assertEquals(userloginResult1.contains("loginValue"), true);
            Assert.assertEquals(userloginResult1.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult1.contains("uname"), true);
            Assert.assertEquals(userloginResult1.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult1.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult1.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult1.contains("token"), true);
            Assert.assertEquals(userloginResult1.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult1.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(userloginResult1.contains("deviceid"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult1.contains("modifyTime"), true);
            Assert.assertEquals(userloginResult1.contains("keys"), true);
            Assert.assertEquals(userloginResult1.contains("cacc"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Cacc_yy\""), true);
        }
    }

    /**
     * 通过keytp=tencent方式进行登录后,uname使用参数的值一个也不传
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndNotExistValueOpenidAndNotExistValueUnionid() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO1 = null;
        String userloginResult1 = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO1 = new UserLoginBO();
            userLoginBO1.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
//            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
//            unameJson.setUnionid(unionidBean);
            userLoginBO1.setUname(JSON.toJSONString(unameJson));
            userLoginBO1.setPasswd("");
            userLoginBO1.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO1.setKeys(list);
            userLoginBO1.setThirdlogin(false);
            userLoginBO1.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO1 请求的参数=" + JSON.toJSONString(userLoginBO1));
            userloginResult1 = Login(userLoginBO1);
            log.info("userloginResult1 返回结果1=" + userloginResult1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录后,uname使用参数的值一个也不传");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO1));
            recordhttp.setResponse(userloginResult1);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult1.contains("\"msg\":\"data_error\""), true);
            Assert.assertEquals(userloginResult1.contains("\"result\":111"), true);
        }
    }

    /**
     * 通过keytp=tencent方式进行登录后,uname使用参数的值与keys的值出现重复
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndParameterKeysValuesParameterUnameValuwIsRepeate() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO1 = null;
        String userloginResult1 = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO1 = new UserLoginBO();
            userLoginBO1.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO1.setUname(JSON.toJSONString(unameJson));
            userLoginBO1.setPasswd("");
            userLoginBO1.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setOpenid("Openid_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setUnionid("Unionid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO1.setKeys(list);
            userLoginBO1.setThirdlogin(false);
            userLoginBO1.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO1 请求的参数=" + JSON.toJSONString(userLoginBO1));
            userloginResult1 = Login(userLoginBO1);
            log.info("userloginResult1 返回结果1=" + userloginResult1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录后,uname使用参数的值与keys的值出现重复");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO1));
            recordhttp.setResponse(userloginResult1);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult1.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(userloginResult1.contains("\"result\":112"), true);
            Assert.assertEquals(userloginResult1.contains("uname"), true);
        }
    }

    /**
     * 通过keytp=tencent方式进行登录后,uname使用参数的值与keys的值未出现重复
     * @throws Exception
     */
    @Test
    public void LoginByParameterKeytpValueIsTencentAndParameterKeysValuesParameterUnameValuwIsNotRepeate() throws Exception {
        String userLoginUrl = null;
        UserLoginBO userLoginBO1 = null;
        String userloginResult1 = null;
        destroyData_();
        try {
            userLoginUrl = url + "/AccService/userlogin";
            userLoginBO1 = new UserLoginBO();
            userLoginBO1.setKeytp("tencent");
            //设置uname值
            TencentBO unameJson=new TencentBO();
            TencentBO.OpenidBean openidBean=new TencentBO.OpenidBean();
            openidBean.setValue("Openid_yy");
            openidBean.setInfo("openid");
            unameJson.setOpenid(openidBean);
            TencentBO.UnionidBean unionidBean=new TencentBO.UnionidBean();
            unionidBean.setValue("Unionid_yy");
            unionidBean.setInfo("unionid");
            unameJson.setUnionid(unionidBean);
            userLoginBO1.setUname(JSON.toJSONString(unameJson));
            userLoginBO1.setPasswd("");
            userLoginBO1.setAppid("1.00002");
            UserLoginBO.Keys keys1 = new UserLoginBO.Keys();
            keys1.setCacc("Cacc_yy");
            UserLoginBO.Keys keys2 = new UserLoginBO.Keys();
            keys2.setDeviceid("Deviceid_yy");
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys1);
            list.add(keys2);
            userLoginBO1.setKeys(list);
            userLoginBO1.setThirdlogin(false);
            userLoginBO1.setSeq(UUID.randomUUID().toString());
            log.info("userLoginUrl 请求的参数=" + userLoginUrl);
            log.info("userLoginBO1 请求的参数=" + JSON.toJSONString(userLoginBO1));
            userloginResult1 = Login(userLoginBO1);
            log.info("userloginResult1 返回结果1=" + userloginResult1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过keytp=tencent方式进行登录后,uname使用参数的值与keys的值未出现重复");
            recordhttp.setUrl(userLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(userLoginBO1));
            recordhttp.setResponse(userloginResult1);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(userloginResult1.contains("loginTime"), true);
            Assert.assertEquals(userloginResult1.contains("loginValue"), true);
            Assert.assertEquals(userloginResult1.contains("\"msg\":\"\""), true);
            Assert.assertEquals(userloginResult1.contains("\"newUser\":true"), true);
            Assert.assertEquals(userloginResult1.contains("\"result\":1"), true);
            Assert.assertEquals(userloginResult1.contains("sessionCode"), true);
            Assert.assertEquals(userloginResult1.contains("token"), true);
            Assert.assertEquals(userloginResult1.contains("tokenExpire"), true);
            Assert.assertEquals(userloginResult1.contains("total"), true);
            Assert.assertEquals(userloginResult1.contains("uid"), true);
            Assert.assertEquals(userloginResult1.contains("uname"), true);
            Assert.assertEquals(userloginResult1.contains("upperLimitNum"), true);
            Assert.assertEquals(userloginResult1.contains("upperLimitState"), true);
            Assert.assertEquals(userloginResult1.contains("unionid"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(userloginResult1.contains("openid"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(userloginResult1.contains("deviceid"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(userloginResult1.contains("keys"), true);
            Assert.assertEquals(userloginResult1.contains("cacc"), true);
            Assert.assertEquals(userloginResult1.contains("\"value\":\"Cacc_yy\""), true);
        }
    }


    /**
     * @param appid
     * @param guestUpperLimit
     * @param guestPeriodValid
     * @param normalUpperLimit
     */
    public void modifyGuestUpperLimitAndModifyGuestPeriodValidAndModifyNormalUpperLimit(String appid, Integer guestUpperLimit, Integer guestPeriodValid, Integer normalUpperLimit) {
        try {
            AppidExample appidExample = new AppidExample();
            appidExample.createCriteria().andAppidEqualTo(appid);
            List<Appid> appidList = appidRepository.selectByExample(appidExample);
            UpdAppidBO.InfoBean infoBean = JSON.parseObject(appidList.get(0).getInfo(), UpdAppidBO.InfoBean.class);
            infoBean.setGuestUpperLimit(guestUpperLimit);
            infoBean.setGuestPeriodValid(guestPeriodValid);
            infoBean.setNormalUpperLimit(normalUpperLimit);
            Appid record = new Appid();
            record.setInfo(JSON.toJSONString(infoBean));
            AppidExample updateExample = new AppidExample();
            updateExample.createCriteria().andAppidEqualTo(appid);
            appidRepository.updateByExampleSelective(record, updateExample);
            Config configRecord = new Config();
            configRecord.setValue(String.valueOf(System.currentTimeMillis()));
            ConfigExample configExample = new ConfigExample();
            configExample.createCriteria().andNameEqualTo("appid");
            configRepository.updateByExampleSelective(configRecord, configExample);
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改appid=1.00002的GroupTag与Euid与appid=1.00003一样的GroupTag与Euid值
     */
    private void changeEuidAndGroupTagByAppid() throws IOException, InterruptedException {
        Appid record = new Appid();
        record.setEuid(1115813658564235264L);
        record.setGroupTag("g35");
        AppidExample example = new AppidExample();
        AppidExample.Criteria cr = example.createCriteria();
        cr.andIdEqualTo(1105700271196999680L);
        appidRepository.updateByExampleSelective(record, example);
        //修改完appid的配置，需要重新启动下服务使得配置生效
        applicationServiceManaged.restartAccPid();
    }

    /**
     * 恢复appid=1.00002的GroupTag与Euid默认值
     */
    private void restoreEuidAndGroupTagByAppid() throws IOException, InterruptedException {
        Appid record = new Appid();
        record.setEuid(1103539011445592064L);
        record.setGroupTag("1.00002yy81");
        AppidExample example = new AppidExample();
        AppidExample.Criteria cr = example.createCriteria();
        cr.andIdEqualTo(1105700271196999680L);
        appidRepository.updateByExampleSelective(record, example);
        //修改完appid的配置，需要重新启动下服务使得配置生效
        applicationServiceManaged.restartAccPid();
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


    void initData_() {
        destroyData_();
        Long uid = 239098L;
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria tUserCr = tUserExample.createCriteria();
        tUserCr.andUidEqualTo(uid);
        List<TUser> tUserList = tUserRepository.selectByExample(tUserExample);
        if (tUserList.size() <= 0) {
//            tUserRepository.deleteByExample(tUserExample);
            TUser recordTUser = new TUser();
            recordTUser.setUid(239098L);
            recordTUser.setUname("wx_wnkiehde");
            recordTUser.setBlockedtimeout(0L);
            recordTUser.setPassword("BkSxQaiQYwHIYpZoOGCtWx8pcwtnsY+Hc8nAMsPq/0I=");
            recordTUser.setChannelNo("");
            recordTUser.setActivityno("");
            recordTUser.setCreatetime(1595235711L);
            recordTUser.setModifytime(1595300984L);
            recordTUser.setBlocked(0);
            recordTUser.setStatus(1);
            recordTUser.setAppid("1.00002");
            recordTUser.setUc(85L);
            recordTUser.setIp("172.16.2.36");
            recordTUser.setLastlogintime(1595300984L);
            recordTUser.setLastlogouttime(0L);
            tUserRepository.insert(recordTUser);
        }
    }


    void destroyData_() {
        Long uid = 239098L;
        TKeyindexExample tKeyindexExample = new TKeyindexExample();
        TKeyindexExample.Criteria tKeyindexCr = tKeyindexExample.createCriteria();
        tKeyindexCr.andUidEqualTo(uid);
        tKeyindexRepository.deleteByExample(tKeyindexExample);

        TKeyindexExample tKeyindexExample1 = new TKeyindexExample();
        TKeyindexExample.Criteria tKeyindexCr1 = tKeyindexExample1.createCriteria();
        List<String> values = new ArrayList<>();
        values.add("Cacc_yy");
        values.add("Deviceid_yy");
        values.add("yy@163.com");
        values.add("13093863511");
        values.add("yy");
        values.add("Openid_yy");
        values.add("Unionid_yy");
        tKeyindexCr1.andKeyvalueIn(values);
        tKeyindexRepository.deleteByExample(tKeyindexExample1);
        TUserkeysExample tUserkeysExample = new TUserkeysExample();
        TUserkeysExample.Criteria tUserkeysCr = tUserkeysExample.createCriteria();
        tUserkeysCr.andUidEqualTo(uid);
        tUserkeysRepository.deleteByExample(tUserkeysExample);
    }

    public  void caccCalTimeBylogin(){
            Long start=System.currentTimeMillis();

            Long end=System.currentTimeMillis();
        System.out.println("end-start="+(end-start)/1000l);
    }



}
