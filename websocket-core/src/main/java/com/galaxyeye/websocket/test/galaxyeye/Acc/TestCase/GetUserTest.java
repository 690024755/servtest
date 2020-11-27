package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TKeyindexRepository;
import com.galaxyeye.websocket.application.repository.TUserRepository;
import com.galaxyeye.websocket.application.repository.TUserkeysRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUserkeys;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TKeyindexExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserkeysExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.GetUserBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UpdPassBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.UserLoginDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@Component
public class GetUserTest extends BaseTest {

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private UserBindTest UserBindTest;

    @Autowired
    private TKeyindexRepository tKeyindexRepository;

    @Autowired
    private TUserkeysRepository tUserkeysRepository;

    @Autowired
    private LoginTest loginTest;

    /**
     * 使用用户名与密码查询用户信息
     * 则参数Uname与Passwd从表t_user的uname与password字段
     *
     * @throws Exception
     */
    @Test
    public void getUserTestByUnameAndPasswd() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
            getUserBO.setUname("cacc_8rhbukbq");
            getUserBO.setPasswd("123456");
            getUserBO.setKeytp("");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + getuserResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用用户名与密码查询用户信息");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("keys"), true);
            Assert.assertEquals(getuserResult.contains("cacc"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(getuserResult.contains("deviceid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("email"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(getuserResult.contains("mobile"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(getuserResult.contains("nickname"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(getuserResult.contains("openid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("unionid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":1"), true);
            Assert.assertEquals(getuserResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 使用Keytp与Uname方式获取普通用户信息，uname的值为cacc
     * 使用非普通用户获取getuser信息
     * @throws Exception
     */
    @Test
    public void getUserTestByKeytpAndUname() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
            getUserBO.setUname("Cacc_yy");
            getUserBO.setPasswd("");
            getUserBO.setKeytp("cacc");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用Keytp与Uname方式获取普通用户信息，uname的值为cacc");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("keys"), true);
            Assert.assertEquals(getuserResult.contains("cacc"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(getuserResult.contains("deviceid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("openid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("unionid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":1"), true);
            Assert.assertEquals(getuserResult.contains("uid"), true);
        }
    }

    /**
     * 请求参数Uname的值设置为uid查询用户信息
     *
     * @throws Exception
     */
    @Test
    public void getUserTestByParameterUnameValueIsuid() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
            getUserBO.setUname("!225825");
            getUserBO.setPasswd("");
            getUserBO.setKeytp("");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Uname的值设置为uid查询用户信息");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("keys"), true);
            Assert.assertEquals(getuserResult.contains("cacc"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(getuserResult.contains("deviceid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("email"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(getuserResult.contains("mobile"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(getuserResult.contains("nickname"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(getuserResult.contains("openid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("unionid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":1"), true);
            Assert.assertEquals(getuserResult.contains("\"uid\":225825"), true);
        }
    }


    /**
     * 使用未过期token获取用户信息
     *
     * @throws Exception
     */
    @Test
    public void getUserTestByNotExpireToken() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");//token设置为具体值
//        UserLoginBO.Keys keys=new UserLoginBO.Keys();
//        List<UserLoginBO.Keys> list=new ArrayList<>();
//        list.add(keys);
            userLoginBO.setKeys(new ArrayList<>());
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String userloginResult = Login(userLoginBO);
            UserLoginDTO userLoginDTO = JSON.parseObject(userloginResult, UserLoginDTO.class);
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
            getUserBO.setUname(userLoginDTO.getToken());
            getUserBO.setPasswd("");
            getUserBO.setKeytp("token");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用未过期token获取用户信息");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("keys"), true);
            Assert.assertEquals(getuserResult.contains("cacc"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(getuserResult.contains("deviceid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("email"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(getuserResult.contains("mobile"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(getuserResult.contains("nickname"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(getuserResult.contains("openid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("unionid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":1"), true);
            Assert.assertEquals(getuserResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * appid必填参数校验
     *
     * @throws Exception
     */
    @Test
    public void getUserTestByNotExistParameterAppid() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");//token设置为具体值
//        UserLoginBO.Keys keys=new UserLoginBO.Keys();
//        List<UserLoginBO.Keys> list=new ArrayList<>();
//        list.add(keys);
            userLoginBO.setKeys(new ArrayList<>());
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String userloginResult = Login(userLoginBO);
            UserLoginDTO userLoginDTO = JSON.parseObject(userloginResult, UserLoginDTO.class);
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
//            getUserBO.setAppid("1.00002");
            getUserBO.setUname(userLoginDTO.getToken());
            getUserBO.setPasswd("");
            getUserBO.setKeytp("token");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用未过期token获取用户信息");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":106"), true);
        }
    }

    /**
     * Uname必填参数校验
     *
     * @throws Exception
     */
    @Test
    public void getUserTestByNotExistParameterUname() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");//token设置为具体值
//        UserLoginBO.Keys keys=new UserLoginBO.Keys();
//        List<UserLoginBO.Keys> list=new ArrayList<>();
//        list.add(keys);
            userLoginBO.setKeys(new ArrayList<>());
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String userloginResult = Login(userLoginBO);
            UserLoginDTO userLoginDTO = JSON.parseObject(userloginResult, UserLoginDTO.class);
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
//            getUserBO.setUname(userLoginDTO.getToken());
            getUserBO.setPasswd("");
            getUserBO.setKeytp("token");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Uname必填参数校验");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("\"msg\":\"uname_error\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":102"), true);
        }
    }

    /**
     * 通过token登录Passwd非必填参数校验
     *
     * @throws Exception
     */
    @Test
    public void getUserTestByNotExistParameterPasswdByTokenLogin() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");//token设置为具体值
//        UserLoginBO.Keys keys=new UserLoginBO.Keys();
//        List<UserLoginBO.Keys> list=new ArrayList<>();
//        list.add(keys);
            userLoginBO.setKeys(new ArrayList<>());
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String userloginResult = Login(userLoginBO);
            UserLoginDTO userLoginDTO = JSON.parseObject(userloginResult, UserLoginDTO.class);
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
            getUserBO.setUname(userLoginDTO.getToken());
//            getUserBO.setPasswd("");
            getUserBO.setKeytp("token");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通过token登录Passwd非必填参数校验");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("keys"), true);
            Assert.assertEquals(getuserResult.contains("cacc"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(getuserResult.contains("deviceid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("email"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(getuserResult.contains("mobile"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(getuserResult.contains("nickname"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(getuserResult.contains("openid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("unionid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":1"), true);
            Assert.assertEquals(getuserResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 使用过期token获取用户信息
     * @throws Exception
     */
    @Test
    public void getUserTestByExpireToken() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");//token设置为具体值
//        UserLoginBO.Keys keys=new UserLoginBO.Keys();
//        List<UserLoginBO.Keys> list=new ArrayList<>();
//        list.add(keys);
            userLoginBO.setKeys(new ArrayList<>());
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String userloginResult = Login(userLoginBO);
            UserLoginDTO userLoginDTO = JSON.parseObject(userloginResult, UserLoginDTO.class);
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
//            getUserBO.setUname(userLoginDTO.getToken());
            //uid=225825,uname=cacc_8rhbukbq生成过期的token
            getUserBO.setUname("rIjPEyuKPs7YUIavh-pE3eqA8C7IBFJg1uX7nDd4GfAJbAwbtc7zCkXH0IATVxDJhaixoBm0hkagwSEJKJKkc8y3Jt--L5wpQBpm_9PZUp9xT4IecSudLUYJpwNAL_1b");
            getUserBO.setPasswd("");
            getUserBO.setKeytp("token");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用过期token获取用户信息");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("\"msg\":\"token_expire\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":133"), true);
        }
    }

    /**
     * 检查openid的返回结果存在2个value
     * @throws Exception
     */
    @Test
    public void getUserTestByResultCantainsTwoOpenid() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            UserBindTest.userbindTestByRepeatBindOpenid();
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("cacc_8rhbukbq");
            userLoginBO.setPasswd("123456");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("");//token设置为具体值
//        UserLoginBO.Keys keys=new UserLoginBO.Keys();
//        List<UserLoginBO.Keys> list=new ArrayList<>();
//        list.add(keys);
            userLoginBO.setKeys(new ArrayList<>());
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq("abc");
            String userloginResult = Login(userLoginBO);
            UserLoginDTO userLoginDTO = JSON.parseObject(userloginResult, UserLoginDTO.class);
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
//            getUserBO.setUname(userLoginDTO.getToken());
            //uid=225825,uname=cacc_8rhbukbq生成过期的token
            getUserBO.setUname(userLoginDTO.getToken());
            getUserBO.setPasswd("");
            getUserBO.setKeytp("token");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + JSON.parseObject(getuserResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("检查openid的返回结果存在2个value");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("keys"), true);
            Assert.assertEquals(getuserResult.contains("cacc"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Cacc_yy\""), true);
            Assert.assertEquals(getuserResult.contains("deviceid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Deviceid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("email"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy@163.com\""), true);
            Assert.assertEquals(getuserResult.contains("mobile"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"13093863511\""), true);
            Assert.assertEquals(getuserResult.contains("nickname"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"yy\""), true);
            Assert.assertEquals(getuserResult.contains("openid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Openid_yy1\""), true);
            Assert.assertEquals(getuserResult.contains("unionid"), true);
            Assert.assertEquals(getuserResult.contains("\"value\":\"Unionid_yy\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":1"), true);
            Assert.assertEquals(getuserResult.contains("\"uid\":225825"), true);
        }
    }

    /**
     * 获取不存在用户信息
     * @throws Exception
     */
    @Test
    public void getUserTestByNotExistUname() throws Exception {
        String getuserUrl = null;
        GetUserBO getUserBO = null;
        String getuserResult = "";
        initData();
        try {
            getuserUrl = url + "/AccService/getuser";
            getUserBO = new GetUserBO();
            getUserBO.setAppid("1.00002");
            getUserBO.setUname("cacc_8rhbukbq===tes");
            getUserBO.setPasswd("123456");
            getUserBO.setKeytp("");
            List<String> keys = new ArrayList<>();
            keys.add("openid");
            keys.add("unionid");
            keys.add("cacc");
            keys.add("nickname");
            keys.add("deviceid");
            keys.add("email");
            keys.add("mobile");
            getUserBO.setKeys(keys);
            getUserBO.setSeq("abc");
            log.info("getuserUrl 请求的参数=" + getuserUrl);
            log.info("getUserBO 请求的参数=" + JSON.toJSONString(getUserBO));
            getuserResult = HttpUtil.postGeneralUrl(getuserUrl, "application/json", JSON.toJSONString(getUserBO), "UTF-8");
            log.info("getuserResult 返回结果=" + getuserResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取不存在用户信息");
            recordhttp.setUrl(getuserUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserBO));
            recordhttp.setResponse(getuserResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getuserResult.contains("\"msg\":\"user_not_found\""), true);
            Assert.assertEquals(getuserResult.contains("\"result\":104"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {
        destroyData();
        Long uid = 225825L;
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria tUserCr = tUserExample.createCriteria();
        tUserCr.andUidEqualTo(uid);
        List<TUser> tUserList = tUserRepository.selectByExample(tUserExample);
        if (tUserList.size() <=0) {
//            tUserRepository.deleteByExample(tUserExample);
            TUser recordTUser = new TUser();
            recordTUser.setUid(225825L);
            recordTUser.setUname("cacc_8rhbukbq");
            recordTUser.setBlockedtimeout(0L);
            recordTUser.setPassword("a2ql6nsAUN5icIPkrlJxAaJQB/BNXSBmByzvm6QQKuQ=");
            recordTUser.setChannelNo("");
            recordTUser.setActivityno("");
            recordTUser.setCreatetime(1568019693L);
            recordTUser.setModifytime(1589512339L);
            recordTUser.setBlocked(0);
            recordTUser.setStatus(1);
            recordTUser.setAppid("1.00003");
            recordTUser.setUc(67L);
            recordTUser.setIp("172.16.5.6");
            recordTUser.setLastlogintime(1589512339L);
            recordTUser.setLastlogouttime(0L);
            tUserRepository.insert(recordTUser);
        }
        try{
            UserBindTest.userbindTestByOne1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void destroyData() {
        Long uid = 225825L;
        TKeyindexExample tKeyindexExample = new TKeyindexExample();
        TKeyindexExample.Criteria tKeyindexCr = tKeyindexExample.createCriteria();
        tKeyindexCr.andUidEqualTo(uid);
        tKeyindexRepository.deleteByExample(tKeyindexExample);
        TUserkeysExample tUserkeysExample = new TUserkeysExample();
        TUserkeysExample.Criteria tUserkeysCr = tUserkeysExample.createCriteria();
        tUserkeysCr.andUidEqualTo(uid);
        tUserkeysRepository.deleteByExample(tUserkeysExample);
//        TUserExample tUserExample=new TUserExample();
//        tUserExample.createCriteria().andUidEqualTo(uid);
//        tUserRepository.deleteByExample(tUserExample);
    }

}
