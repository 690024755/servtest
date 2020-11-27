package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 10:54
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.EUserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.ELoginDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


@Component
@Slf4j
public class ELoginTest extends BaseTest {


    public String eLogin(EUserLoginBO eUserLoginBO) throws Exception {
        //用户登录，需要修改企业用户的密码，请查看UpdateUserTest.Test方法
        String eUserLoginUrl=url+"/AccService/euserlogin";
        String eUserLoginResult = HttpUtil.postGeneralUrl(eUserLoginUrl,"application/json", JSON.toJSONString(eUserLoginBO),"UTF-8");
        log.info("eUserLoginUrl 请求的参数="+ eUserLoginUrl);
        log.info("eUserLoginBO 请求的参数=" + JSON.toJSONString(eUserLoginBO));
        return eUserLoginResult;
    }



    public ELoginDTO getToken() throws Exception {
        EUserLoginBO eUserLoginBO=new EUserLoginBO();
        eUserLoginBO.setEuid(1186531925544669184L);
        eUserLoginBO.setEuname("yangyi");
        eUserLoginBO.setPasswd("yangyi");
        eUserLoginBO.setAppid("100.00001");
        String eUserLoginResult = eLogin(eUserLoginBO);
        ELoginDTO eLoginDTO=JSON.parseObject(eUserLoginResult, ELoginDTO.class);
        log.info("eUserLoginResult 返回结果=" + eUserLoginResult);
        return eLoginDTO;
    }

    //使用AccessToken登录
    @Test
    public void yy() throws Exception {
        String eUserLoginUrl =null;
        EUserLoginBO eUserLoginBO =null;
        String eUserLoginResult ="";
        try{
            eUserLoginUrl=url+"/AccService/euserlogin";
            eUserLoginBO=new EUserLoginBO();
            eUserLoginBO.setEuid(1186531925544669184L);
            eUserLoginBO.setAppid("100.00002");
            eUserLoginBO.setBmAppid("100.00002");
            eUserLoginBO.setPasswd("yangyi");
            eUserLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            log.info("eUserLoginUrl 请求的参数="+ eUserLoginUrl);
            log.info("eUserLoginBO 请求的参数=" + JSON.toJSONString(eUserLoginBO));
            eUserLoginResult = HttpUtil.postGeneralUrl(eUserLoginUrl,"application/json", JSON.toJSONString(eUserLoginBO),"UTF-8");
            log.info("eUserLoginResult 返回结果=" + eUserLoginResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业用户使用bmappid与accesstoken登录");
            recordhttp.setUrl(eUserLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(eUserLoginBO));
            recordhttp.setResponse(eUserLoginResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(eUserLoginResult.contains("\"result\":1"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"acctype\":\"admin\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"addr\":\"testacc13\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"email\":\"test3@qq.com\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euid\":1186531925544669184"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euname\":\"yangyi\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"mobile\":\"13093863588\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"status\":0"),true);
            Assert.assertEquals(eUserLoginResult.contains("token"),true);
            Assert.assertEquals(eUserLoginResult.contains("tokenExpire"),true);
        }
    }

    /**
     *企业用户登录
     * 请求参数Euid、Euname、Passwd、Appid在表userdb.euser
     * @throws Exception
     */
    @Test
    public void ELoginTest1() throws Exception {
        String eUserLoginUrl =null;
        EUserLoginBO eUserLoginBO =null;
        String eUserLoginResult ="";
        try{
            //用户登录
            eUserLoginUrl=url+"/AccService/euserlogin";
            eUserLoginBO=new EUserLoginBO();
            eUserLoginBO.setEuid(1186531925544669184L);
            eUserLoginBO.setEuname("yangyi");
            eUserLoginBO.setPasswd("yangyi");
            eUserLoginBO.setAppid("1.00002");
            log.info("eUserLoginUrl 请求的参数="+ eUserLoginUrl);
            log.info("eUserLoginBO 请求的参数="+ JSON.toJSONString(eUserLoginBO));
            eUserLoginResult = HttpUtil.postGeneralUrl(eUserLoginUrl,"application/json", JSON.toJSONString(eUserLoginBO),"UTF-8");
            log.info("eUserLoginResult 返回结果="+JSON.parseObject(eUserLoginResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("企业用户使用参数Euid、Euname、Passwd的用户名与密码进行登录");
            recordhttp.setUrl(eUserLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(eUserLoginBO));
            recordhttp.setResponse(eUserLoginResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(eUserLoginResult.contains("\"result\":1"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"acctype\":\"admin\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"addr\":\"testacc13\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"email\":\"test3@qq.com\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euid\":1186531925544669184"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euname\":\"yangyi\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"mobile\":\"13093863588\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"status\":0"),true);
            Assert.assertEquals(eUserLoginResult.contains("token"),true);
            Assert.assertEquals(eUserLoginResult.contains("tokenExpire"),true);
        }

    }

    /**
     * 接口方法=euserlogin配置在表userdb.appid.appid=100.00002,userdb.appid.info.AuthMethods段里
     * 则请求参数额外增加Uid与Token，其他参数同之前接口文档参数
     * @throws Exception
     */
    @Test
    public void ELoginTest2() throws Exception {
        String eUserLoginUrl =null;
        EUserLoginBO eUserLoginBO =null;
        String eUserLoginResult ="";
        try{
            eUserLoginUrl=url+"/AccService/euserlogin";
            eUserLoginBO=new EUserLoginBO();
            eUserLoginBO.setEuid(1186531925544669184L);
            eUserLoginBO.setUid(1186531925544669184L);
            eUserLoginBO.setToken(getToken().getToken());
            eUserLoginBO.setAppid("100.00003");
            eUserLoginBO.setEuname("yangyi");
            eUserLoginBO.setPasswd("yangyi");
            log.info("eUserLoginUrl 请求的参数="+ eUserLoginUrl);
            log.info("eUserLoginBO 请求的参数="+ JSON.toJSONString(eUserLoginBO));
            eUserLoginResult = HttpUtil.postGeneralUrl(eUserLoginUrl,"application/json", JSON.toJSONString(eUserLoginBO),"UTF-8");
            log.info("eUserLoginResult 返回结果="+JSON.parseObject(eUserLoginResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在AuthMethods段里，则请求参数额外增加Uid与Token，验证企业用户登录成功");
            recordhttp.setUrl(eUserLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(eUserLoginBO));
            recordhttp.setResponse(eUserLoginResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(eUserLoginResult.contains("\"result\":1"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"acctype\":\"admin\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"addr\":\"testacc13\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"email\":\"test3@qq.com\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euid\":1186531925544669184"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euname\":\"yangyi\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"mobile\":\"13093863588\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"status\":0"),true);
            Assert.assertEquals(eUserLoginResult.contains("token"),true);
            Assert.assertEquals(eUserLoginResult.contains("tokenExpire"),true);
        }

    }


    /**
     *  接口方法=euserlogin配置在表userdb.appid.appid=100.00002,userdb.appid.info.AuthMethods段里
     *  则请求参数额外增加BmAppid与AccessToken，其他参数同之前接口文档参数
     * @throws Exception
     */
    @Test
    public void ELoginTest3() throws Exception {
        String eUserLoginUrl =null;
        EUserLoginBO eUserLoginBO =null;
        String eUserLoginResult ="";
        try{
            eUserLoginUrl=url+"/AccService/euserlogin";
            eUserLoginBO=new EUserLoginBO();
            eUserLoginBO.setEuid(1186531925544669184L);
            eUserLoginBO.setBmAppid("100.00002");
            eUserLoginBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            eUserLoginBO.setAppid("100.00002");
            eUserLoginBO.setEuname("yangyi");
            eUserLoginBO.setPasswd("yangyi");
            log.info("eUserLoginUrl 请求的参数="+ eUserLoginUrl);
            log.info("eUserLoginBO 请求的参数="+ JSON.toJSONString(eUserLoginBO));
            eUserLoginResult = HttpUtil.postGeneralUrl(eUserLoginUrl,"application/json", JSON.toJSONString(eUserLoginBO),"UTF-8");
            log.info("eUserLoginResult 返回结果="+JSON.parseObject(eUserLoginResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在AuthMethods段里，则请求参数额外增加BmAppid与AccessToken，验证企业用户登录成功");
            recordhttp.setUrl(eUserLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(eUserLoginBO));
            recordhttp.setResponse(eUserLoginResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(eUserLoginResult.contains("\"result\":1"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"acctype\":\"admin\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"addr\":\"testacc13\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"email\":\"test3@qq.com\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euid\":1186531925544669184"),true);
            Assert.assertEquals(eUserLoginResult.contains("\"euname\":\"yangyi\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"mobile\":\"13093863588\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(eUserLoginResult.contains("\"status\":0"),true);
            Assert.assertEquals(eUserLoginResult.contains("token"),true);
            Assert.assertEquals(eUserLoginResult.contains("tokenExpire"),true);
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
