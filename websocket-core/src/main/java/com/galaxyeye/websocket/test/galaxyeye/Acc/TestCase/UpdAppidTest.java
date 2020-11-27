package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.AddAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UpdAppidBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
@Component
public class UpdAppidTest extends BaseTest {

    @Autowired
    private AppidRepository appidRepository;

    @Autowired
    private GetAppidTest getAppidTest;

    @Autowired
    private AddAppidTest addAppidTest;


    public void updAppidTestByGernal(UpdAppidBO updAppidBO) {
        String updappidUrl =null;
        String updappidResult ="";
        try{
            updappidUrl =url+ "/AccService/updappid";
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一条不存在的记录");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\""+updAppidBO.getAppkey()+"\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\""+updAppidBO.getApptype()+"\""),true);
            Assert.assertEquals(updappidResult.contains("bmAppid"),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\""+updAppidBO.getDesc()+"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":"+updAppidBO.getErid()+""),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":"+updAppidBO.getEuid()+""),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\""+updAppidBO.getGroupTag()+"\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\""+updAppidBO.getIdentity()+"\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("allowIps"),true);
            Assert.assertEquals(updappidResult.contains("openMethods"),true);
            Assert.assertEquals(updappidResult.contains("authMethods"),true);
            Assert.assertEquals(updappidResult.contains("allowTopics"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"pid\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 修改appid里的CallbackUrl与Desc的值
     */
    @Test
    public void updAppidTestByModifyParameterCallbackUrlAndDesc() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        String getappidResult="";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setCallbackUrl("https://update.galaxyeye.xyz/");
            updAppidBO.setDesc("自动化测试使用update");
            updAppidBO.setErid("1103548278986772480");
            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
            getappidResult=getAppidTest.getAppidTestByGernal("172.16.0.25","18080",bmAppid);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改appid里的CallbackUrl与Desc的值");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("appkey"),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用update\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"https://update.galaxyeye.xyz/\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);

            Assert.assertEquals(getappidResult.contains("appkey"),true);
            Assert.assertEquals(getappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(getappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(getappidResult.contains("\"desc\":\"自动化测试使用update\""),true);
            Assert.assertEquals(getappidResult.contains("\"callbackUrl\":\"https://update.galaxyeye.xyz/\""),true);
            Assert.assertEquals(getappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(getappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(getappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(getappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(getappidResult.contains("info"),true);
            Assert.assertEquals(getappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(getappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(getappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(getappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(getappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(getappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(getappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 修改appid里的Desc的值,但是不修改CallbackUrl值
     */
    @Test
    public void updAppidTestByNotModifyParameterCallbackUrlAndModifyParameterDesc() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
//            updAppidBO.setCallbackUrl("https://update.galaxyeye.xyz/");
            updAppidBO.setDesc("自动化测试使用update");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772480");
            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改appid里的Desc的值,但是不修改CallbackUrl值");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用update\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 修改appid里的Appkey值为12345678
     */
    @Test
    public void updAppidTestByModifyParameterAppkey() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772480");
            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改appid里的Appkey值为12345678");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * 修改Euid与Erid的值
     */
    @Test
    public void updAppidTestByModifyParameterEridAndEuid() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("修改appid里的Appkey值为12345678");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772480"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * Super=1，参数Euid传递且Erid不传
     */
    @Test
    public void updAppidTestByExistParameterEuidAndNotExistParameterEridAndSuperValueIsOne() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
//            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(1);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=1，参数Euid传递且Erid不传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592066"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * Super=1，参数Euid传递且Erid传递
     */
    @Test
    public void updAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsOne() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(1);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=1，参数Euid传递且Erid传递");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772488"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592066"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * Super=1，参数Euid不传递且Erid不传
     */
    @Test
    public void updAppidTestByNotExistParameterEuidAndNotExistParameterEridAndSuperValueIsOne() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
//            updAppidBO.setErid("1103548278986772488");
//            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(1);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=1，参数Euid不传递且Erid不传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * Super=1，参数Euid不传递且Erid传
     */
    @Test
    public void updAppidTestByNotExistParameterEuidAndExistParameterEridAndSuperValueIsOne() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
//            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(1);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=1，参数Euid不传递且Erid传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772488"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * Super=0，参数Euid不传递且Erid传,但是euid的值在数据库表erobot查询不到
     */
    @Test
    public void updAppidTestByParameterEuidValueIsNotExistAndNotExistParameterEridAndSuperValueIsZero() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
//            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(0);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=0，参数Euid不传递且Erid传,但是euid的值在数据库表erobot查询不到");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"msg\":\"euid does not exist\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":101"),true);
        }
    }

    /**
     * Super=0，参数Euid不传递且Erid传
     */
    @Test
    public void updAppidTestByExistParameterEuidAndNotExistParameterEridAndSuperValueIsZero() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
//            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(0);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=0，参数Euid不传递且Erid传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592064"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
        }
    }

    /**
     * Super=0，参数Euid不传递且Erid传
     */
    @Test
    public void updAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsZero() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
//            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(0);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=0，参数Euid不传递且Erid传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"msg\":\"euid cannot be empty！！！\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":101"),true);
        }
    }

    /**
     * Super=0，参数Euid不传递且Erid传
     */
    @Test
    public void updAppidTestByNotExistParameterEuidAndNotExistParameterEridAndSuperValueIsZero() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
//            updAppidBO.setErid("1103548278986772488");
//            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(0);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=0，参数Euid不传递且Erid传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"msg\":\"euid cannot be empty！！！\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":101"),true);
        }
    }


    /**
     * Super=0，参数Euid不传递且Erid传
     */
    @Test
    public void updAppidTestByNotExistParameterEuidAndExistParameterEridAndSuperValueIsZero() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
//            updAppidBO.setEuid("1103539011445592064");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(0);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=0，参数Euid不传递且Erid传");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"msg\":\"euid cannot be empty！！！\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":101"),true);
        }
    }

    /**
     * Super=1，参数Euid传递且Erid传递,增加请求参数guestUpperLimit、guestPeriodValid、normalUpperLimit
     */
    @Test
    public void updAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsOneAndAddParameterGuestUpperLimitAndAddParameterGuestPeriodValidAndAddParameterNormalUpperLimit() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(1);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            infoBean.setNormalUpperLimit(5);
            infoBean.setGuestPeriodValid(1);
            infoBean.setGuestUpperLimit(5);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=1，参数Euid传递且Erid传递,增加请求参数guestUpperLimit、guestPeriodValid、normalUpperLimit");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772488"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592066"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
            Assert.assertEquals(updappidResult.contains("protVer"),true);
        }
    }


    /**
     * Super=1，参数Euid传递且Erid传递,增加请求参数guestUpperLimit、guestPeriodValid、normalUpperLimit、ProtVer
     */
    @Test
    public void updAppidTestByExistParameterEuidAndExistParameterEridAndSuperValueIsOneAndAddParameterGuestUpperLimitAndAddParameterGuestPeriodValidAndAddParameterNormalUpperLimitAndAddparameterProtVer() {
        String updappidUrl =null;
        UpdAppidBO updAppidBO =null;
        String updappidResult ="";
        String bmAppid="110.00003";
        try{
            updappidUrl =url+ "/AccService/updappid";
            updAppidBO = new UpdAppidBO();
            updAppidBO.setAppid("1.00002");
            updAppidBO.setApptype("2");
            //需要跟原来的appid值一样，否则修改失败
            updAppidBO.setBmAppid(bmAppid);
            updAppidBO.setDesc("自动化测试使用1");
            updAppidBO.setAppkey("12345678");
            updAppidBO.setErid("1103548278986772488");
            updAppidBO.setEuid("1103539011445592066");
            updAppidBO.setGroupTag("test");
            updAppidBO.setIdentity("110.00003");
            updAppidBO.set___super(1);
            updAppidBO.setProtVer(10);
            UpdAppidBO.InfoBean infoBean=new UpdAppidBO.InfoBean();
            List<String> allowIps=new ArrayList<>();
            allowIps.add("*.*.*.*");
            List<String> openMethods=new ArrayList<>();
            openMethods.add("updeuser");
            infoBean.setAllowIps(allowIps);
            infoBean.setOpenMethods(openMethods);
            infoBean.setPrivilegeLevel(1);
            infoBean.setNormalUpperLimit(5);
            infoBean.setGuestPeriodValid(1);
            infoBean.setGuestUpperLimit(5);
            updAppidBO.setInfo(infoBean);
            updAppidBO.setSeq("abc");
            log.info("updappidUrl 请求的参数=" + updappidUrl);
            log.info("updAppidBO 请求的参数=" + JSON.toJSONString(updAppidBO));
            updappidResult = HttpUtil.postGeneralUrl(updappidUrl, "application/json", JSON.toJSONString(updAppidBO), "UTF-8");
            log.info("updappidResult 返回结果=" + updappidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Super=1，参数Euid传递且Erid传递,增加请求参数guestUpperLimit、guestPeriodValid、normalUpperLimit、ProtVer");
            recordhttp.setUrl(updappidUrl);
            recordhttp.setRequest(JSON.toJSONString(updAppidBO));
            recordhttp.setResponse(updappidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(updappidResult.contains("\"appkey\":\"12345678\""),true);
            Assert.assertEquals(updappidResult.contains("\"apptype\":\"2\""),true);
            Assert.assertEquals(updappidResult.contains("\"bmAppid\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("\"desc\":\"自动化测试使用1\""),true);
            Assert.assertEquals(updappidResult.contains("\"callbackUrl\":\"\""),true);
            Assert.assertEquals(updappidResult.contains("\"erid\":1103548278986772488"),true);
            Assert.assertEquals(updappidResult.contains("\"euid\":1103539011445592066"),true);
            Assert.assertEquals(updappidResult.contains("\"groupTag\":\"test\""),true);
            Assert.assertEquals(updappidResult.contains("\"identity\":\"110.00003\""),true);
            Assert.assertEquals(updappidResult.contains("info"),true);
            Assert.assertEquals(updappidResult.contains("\"allowIps\":[\"*.*.*.*\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"openMethods\":[\"updeuser\"]"),true);
            Assert.assertEquals(updappidResult.contains("\"authMethods\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"allowTopics\":null"),true);
            Assert.assertEquals(updappidResult.contains("\"privilegeLevel\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(updappidResult.contains("\"result\":1"),true);
            Assert.assertEquals(updappidResult.contains("\"status\":0"),true);
            Assert.assertEquals(updappidResult.contains("\"protVer\":10"),true);
        }
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {
        destroyData();
        AddAppidBO addAppidBO = new AddAppidBO();
        String bmAppid="110.00003";
        addAppidBO.setBmAppid(bmAppid);//在表userdb.appid.appid，该参数不填写则该表的userdb.appid.appid随机生成
        addAppidBO.setApptype("7");
        //其中参数Erid与Euid在表userdb.erobot.erid与userdb.erobot.euid
        addAppidBO.setErid(1103548278986772480L);
        addAppidBO.setEuid(1103539011445592064L);
        addAppidBO.setIdentity(bmAppid);
        addAppidBO.setGroupTag("test");
        addAppidBO.setDesc("自动化测试使用");
        addAppidBO.setCallbackUrl("http://test.galaxyeye.xyz/");
        //通过原先的Appid=1.00002，接口方法=addappid插入一条appid记录，主要是验证权限，判断接口方法addappid在appid=1.00002里的info字段的openmethod里是否存在，存在则允许该接口方法执行
        addAppidBO.setAppid("1.00002");
        AddAppidBO.InfoBean infoBean=new AddAppidBO.InfoBean();
        List<String> allowIps=new ArrayList<>();
        allowIps.add("*.*.*.*");
        infoBean.setAllowIps(allowIps);
        List<String> openMethods=new ArrayList<>();
        openMethods.add("createroom");
        infoBean.setOpenMethods(openMethods);
        infoBean.setPrivilegeLevel(1);
        addAppidBO.setInfo(infoBean);
        addAppidTest.addAppidTestByGernal(addAppidBO);
    }

    @Override
    public void destroyData() {
        AppidExample example=new AppidExample();
        AppidExample.Criteria cr=example.createCriteria();
        List<String> Appids=new ArrayList<>();
        Appids.add("110.00003");
        Appids.add("110.00004");
        cr.andAppidIn(Appids);
        appidRepository.deleteByExample(example);
    }


}
