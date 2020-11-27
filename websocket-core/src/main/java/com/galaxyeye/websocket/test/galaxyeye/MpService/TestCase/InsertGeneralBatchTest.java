package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 10:59
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/6日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ManualConfigRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchConfigBO;
import com.galaxyeye.websocket.tool.date.DateTool;
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
public class InsertGeneralBatchTest extends BaseTest {


    @Autowired
    private ManualConfigRepository manualConfigRepository;


    /**
     * 添加通用性配置信息
     * @throws Exception
     */
    public void insertgeneralbatchTestByGernal(InsertGeneralBatchBO insertGeneralBatchBO,List<InsertGeneralBatchConfigBO> config) {
        String insertGeneralBatchUrl =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("添加通用性配置信息");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 一次性添加2条通用配置信息
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByTwo() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
             insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
             insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_Two");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO1=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO1.setComment("备注1");
            insertGeneralBatchConfigBO1.setKey("test1");
            String data1="{\"indexBannerLock1\":false,\"indexBannerUrl1\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO1.setData(data1);
            insertGeneralBatchConfigBO1.setEditor("yy_小编1");
            insertGeneralBatchConfigBO1.setEnable(1);
            insertGeneralBatchConfigBO1.setVerify(1);
            config.add(insertGeneralBatchConfigBO1);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
             insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次性添加2条通用配置信息");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 一次性添加1条通用配置信息
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByOne() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
             insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
             insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_One");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
             insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次性添加1条通用配置信息");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 根据uid与token添加一条通用配置信息
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByUidAndToken() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
             insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
             insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_UidAndToken");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
             insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token添加一条通用配置信息");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 根据BmAppid与AccessToken添加一条通用配置信息
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByBmAppidAndAccessToken() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            insertGeneralBatchBO.setAccessToken("d4c430ebc03debd8a00e15c1fef36110368c314c03cc9a7aa586af7f9e55da87");
            insertGeneralBatchBO.setBmAppid("4.00090");
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_BmAppidAndAccessToken");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据BmAppid与AccessToken添加一条通用配置信息");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 1条通用配置信息被重复添加多次
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByRepeatAdd() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            ManualConfigExample example=new ManualConfigExample();
            ManualConfigExample.Criteria cr=example.createCriteria();
            cr.andConfEnvEqualTo("yy_single");
            log.info("删除manual_config配置记录信息，其中conf_env=yy_single，删除的记录条数=" + manualConfigRepository.deleteByExample(example));
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_single");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            for (int i = 0; i < 2; i++) {
                log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
                log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
                insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
                log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
                if(i==1){
                    HttpLog recordhttp=new HttpLog();
                    recordhttp.setCreateTime(new Date());
                    recordhttp.setCaseDescribe("1条通用配置信息被重复添加多次");
                    recordhttp.setUrl(insertGeneralBatchUrl);
                    recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
                    recordhttp.setResponse(insertGeneralBatchResult);
                    initLog(recordhttp,new Object(){});
                    Assert.assertEquals(insertGeneralBatchResult.contains("config_exists"),true);
                }
            }
        }
    }

    /**
     * 一次性添加1条通用配置信息,且配置信息是空
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByAddEmptyConfig() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_AddEmptyConfig");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次性添加1条通用配置信息,且配置信息是空");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 参数newApp=1，属于新建小程序,在表manual_config根据Env=yy_NewApp未查询到
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNewApp() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NewApp");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数newApp=1，属于新建小程序");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 参数newApp=0，属于已存在小程序
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByOldApp() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_OldApp");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(0);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数newApp=0，属于已存在小程序");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 参数newApp=0，属于已存在小程序，但是数据不存在该条记录，则报错config_notexist
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByOldAppAndConfigNotExist() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_OldAppAndConfigNotExist");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(0);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数newApp=0，属于已存在小程序，但是数据不存在该条记录，则报错config_notexist");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"config_notexist\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":700"),true);
        }


    }

    /**
     * 参数newApp的值除0与1外的其他值如10
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNewAppValue() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_OldApp");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(10);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数newApp的值除0与1外的其他值如10");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"newApp is out of range\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":101"),true);
        }


    }


    /**
     * 必填参数newApp校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistNewApp() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistNewApp");
            insertGeneralBatchBO.setVersion("5.0.0");
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数newApp校验,且配置信息是空");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"newApp is missing\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":101"),true);
        }


    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistAppid() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistAppid");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":106"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"access_deny\""),true);
        }


    }

    /**
     * 必填参数confAppid校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistConfAppid() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistConfAppid");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数confAppid校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"confAppid is empty\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":101"),true);
        }


    }

    /**
     * 必填参数env校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistenvEnv() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数env校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"env is empty\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":101"),true);
        }


    }

    /**
     * 必填参数Version校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistVersion() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistVersion");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Version校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"version is empty\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":101"),true);
        }


    }


    /**
     * 必填参数config校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistConfig() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistConfig");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数config校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
        }


    }

    /**
     * 必填参数key校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistKey() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistKey");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数key校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 必填参数data校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistData() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistData");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数data校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 必填参数comment校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistComment() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistComment");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数comment校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 必填参数verify校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistVerify() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistVerify");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setEnable(0);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数verify校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    /**
     * 必填参数enable校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistEnable() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistEnable");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEditor("yy_小编");
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数enable校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 必填参数editor校验
     * @throws Exception
     */
    @Test
    public void insertgeneralbatchTestByNotExistEditor() throws Exception {
        String insertGeneralBatchUrl =null;
        InsertGeneralBatchBO insertGeneralBatchBO =null;
        String insertGeneralBatchResult ="";
        try{
            insertGeneralBatchUrl = url+"/BusinessService/config/insertgeneralbatch";
            insertGeneralBatchBO = new InsertGeneralBatchBO();
            HashMap<String,String> userLogin=userLoginTest();
            insertGeneralBatchBO.setToken(userLogin.get("token"));
            insertGeneralBatchBO.setUid(userLogin.get("uid"));
            insertGeneralBatchBO.setAppid("4.00090");
            insertGeneralBatchBO.setConfAppid("4.00090");
            insertGeneralBatchBO.setEnv("yy_NotExistEditor");
            insertGeneralBatchBO.setVersion("5.0.0");
            insertGeneralBatchBO.setNewApp(1);
            List<InsertGeneralBatchConfigBO> config=new ArrayList<>();
            InsertGeneralBatchConfigBO insertGeneralBatchConfigBO=new InsertGeneralBatchConfigBO();
            insertGeneralBatchConfigBO.setComment("备注");
            insertGeneralBatchConfigBO.setKey("test");
            String data="{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}";
            insertGeneralBatchConfigBO.setData(data);
            insertGeneralBatchConfigBO.setEnable(1);
            insertGeneralBatchConfigBO.setVerify(1);
            config.add(insertGeneralBatchConfigBO);
            insertGeneralBatchBO.setConfig(config);
            log.info("getdetaillistUrl 请求的参数=" + insertGeneralBatchUrl);
            log.info("insertGeneralBatchBO 请求的参数=" + JSON.toJSONString(insertGeneralBatchBO));
            insertGeneralBatchResult = HttpUtil.postGeneralUrl(insertGeneralBatchUrl, "application/json", JSON.toJSONString(insertGeneralBatchBO), "UTF-8");
            log.info("insertGeneralBatchResult 返回结果=" + JSON.parseObject(insertGeneralBatchResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数editor校验");
            recordhttp.setUrl(insertGeneralBatchUrl);
            recordhttp.setRequest(JSON.toJSONString(insertGeneralBatchBO));
            recordhttp.setResponse(insertGeneralBatchResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(insertGeneralBatchResult.contains("\"result\":1"),true);
            Assert.assertEquals(insertGeneralBatchResult.contains("\"msg\":\"ok\""),true);
        }


    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        ManualConfigExample exampleQ=new ManualConfigExample();
        ManualConfigExample.Criteria crQ=exampleQ.createCriteria();
        List<Long> ids=new ArrayList<>();
        ids.add(1259041747946704896L);
        crQ.andIdIn(ids);
        List<ManualConfig> list= manualConfigRepository.selectByExample(exampleQ);
        if(Integer.valueOf(list.size()).equals(1)){
            log.info("已经存在初始化数据");
        }else {
            ManualConfig record=new ManualConfig ();
            record.setId(1259041747946704896L);
            record.setAppid("4.00090");
            record.setComment("备注");
            record.setConfEnv("yy_OldApp");
            record.setConfKey("test");
            record.setCreatedAt(DateTool.parseDateStr("2020-05-09 16:45:19",DateTool.TIME_PATTERN));
            record.setData("{\"indexBannerLock\":false,\"indexBannerUrl\": \"../../res/image/rb-banner.png\"}");
            record.setEditor("yy_小编");
            record.setEnable(1);
            record.setUpdatedAt(DateTool.parseDateStr("2020-05-09 16:45:19",DateTool.TIME_PATTERN));
            record.setVer("5.0.0");
            record.setVerify(1);
            manualConfigRepository.insertSelective(record);
        }
    }

    @Override
    public void destroyData() {
        ManualConfigExample example=new ManualConfigExample();
        ManualConfigExample.Criteria cr=example.createCriteria();
        List<String> confEnv=new ArrayList<>();
        confEnv.add("yy_Two");
        confEnv.add("yy_One");
        confEnv.add("yy_single");
        confEnv.add("yy_OldApp");
        confEnv.add("yy_AddEmptyConfig");
        confEnv.add("yy_UidAndToken");
        confEnv.add("yy_BmAppidAndAccessToken");
        confEnv.add("yy_NewApp");
        confEnv.add("yy_NotExistEditor");
        confEnv.add("yy_NotExistEnable");
        confEnv.add("yy_NotExistVerify");
        confEnv.add("yy_NotExistComment");
        confEnv.add("yy_NotExistData");
        confEnv.add("yy_NotExistKey");
        cr.andConfEnvIn(confEnv);
        manualConfigRepository.deleteByExample(example);
    }
}
