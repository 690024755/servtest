package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.MainTypeListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.MainTypeListDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.Json.JsonPathUtils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@Component
public class MainTypeListTest extends BaseTest {

    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private NewMainTypeTest newMainTypeTest;

    @Autowired
    private MainTypeRepository mainTypeRepository;



    /**
     * 查询文章的大类信息通用测试方法
     * @throws Exception
     */
    public String mainTypeListTestByGernal(MainTypeListBO mainTypeListBO) throws Exception {
        String mainTypeListUrl = null;
        String mainTypeListResult = "";
        try {
            mainTypeListUrl = url + "/ArticleService/maintypelist";
//            mainTypeListUrl = "http://172.16.0.25:28081" + "/ArticleService/maintypelist";
            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询文章的大类信息通用测试方法");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            return mainTypeListResult;
        }
    }

    /**
     * 查询文章的大类信息通用测试方法
     * @throws Exception
     */
    public String mainTypeListTestByGernal(String url,MainTypeListBO mainTypeListBO) throws Exception {
        String mainTypeListUrl = null;
        String mainTypeListResult = "";
        try {
            mainTypeListUrl = url + "/ArticleService/maintypelist";
//            mainTypeListUrl = "http://172.16.0.25:28081" + "/ArticleService/maintypelist";
            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询文章的大类信息通用测试方法");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            return mainTypeListResult;
        }
    }


    /**
     * 请求参数Count=0
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterCountValueIsZero() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);


            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(0);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Count=0");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"count has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数Count=-1,负数
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterCountValueIsNegative() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(-1);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Count=-1,负数");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"count has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数Count必填校验
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterCount() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
//            mainTypeListBO.setCount(1);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Count必填校验");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"count has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数Page=0
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterPageValueIsZero() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(999);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(0);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Page=0");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"page has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 请求参数Page=-2,负数
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterPageValueIsNegative() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);


            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(-2);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Page=-2,负数");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"page has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 请求参数Page=5
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterPageValueIsFive() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Page=5");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 请求参数Page必填校验
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterPage() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
//            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Page=5");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"page has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数Name为模糊匹配
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterNameValueIsFuzzyMatching() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName("y");
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name为模糊匹配");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 请求参数Name与code不匹配
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterNameValueIsNotMatchingParameterCodeValue() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName("yty");
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name与code不匹配");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"count\":0"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"page\":0"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 请求参数code是精确匹配，为模糊匹配则查询不到
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterCodeValueIsFuzzyMatching() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode().substring(0,list.get(0).getMainTypeCode().length()-1));
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数code是精确匹配，为模糊匹配则查询不到");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"count\":0"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"list\":[]"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"page\":0"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 请求参数Maintain=0
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterMaintainValueIsZero() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=0;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Maintain=0");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 请求参数Maintain=2
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterMaintainValueIsTwo() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        Integer Maintain=2;
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Maintain=2");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 请求参数Maintain=10
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByParameterMaintainValueIsTen() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(1);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(10);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Maintain=10");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"maintain has a wrong value\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数Maintain非必填校验
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterMaintain() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
//            mainTypeListBO.setMaintain(1);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Maintain非必填校验");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 请求参数Code非必填校验
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterCode() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
//            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Code非必填校验");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 请求参数Name非必填校验
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterName() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            mainTypeListBO.setMaintain(Maintain);
            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
//            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(5);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name非必填校验");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 请求参数Name非必填校验与请求参数Code非必填校验，则查询所有的大类且Maintain=0包括所有维护与不维护的大类
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterNameAndNotExistParameterCodeAndParameterMaintainValueIsZero() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            //设置为0默认查询全部大类包括维护与正常的
            mainTypeListBO.setMaintain(0);
//            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
//            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(1);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name非必填校验与请求参数Code非必填校验，则查询所有的大类且Maintain=0包括所有维护与不维护的大类");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
            List<Object> list=JsonPath.read(mainTypeListResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
        }
    }

    /**
     * 请求参数Name非必填校验与请求参数Code非必填校验，则查询所有的大类且Maintain=1正常
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterNameAndNotExistParameterCodeAndParameterMaintainValueIsOne() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            //设置为0默认查询全部大类包括维护与正常的
            mainTypeListBO.setMaintain(1);
//            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
//            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(1);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name非必填校验与请求参数Code非必填校验，则查询所有的大类且Maintain=1正常");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
            List<MainTypeListDTO.MainTypeDTO> mainTypeList =JsonPathUtils.parse(mainTypeListResult,"$.list[*]",new TypeRef<List<MainTypeListDTO.MainTypeDTO>>(){});
            Assert.assertTrue(mainTypeList.size()>0);
            Boolean flag=true;
            for (MainTypeListDTO.MainTypeDTO mainTypeBean:mainTypeList
                 ) {
                if(!mainTypeBean.getIs_maintained().equals("1")){
                    flag=false;
                }
            }
            Assert.assertTrue(flag);
        }
    }

    public MainTypeListTest (){

    }
    /**
     * 请求参数Name非必填校验与请求参数Code非必填校验，则查询所有的大类且Maintain=2维护大类
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByNotExistParameterNameAndNotExistParameterCodeAndParameterMaintainValueIsTwo() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=2;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            //设置为0默认查询全部大类包括维护与正常的
            mainTypeListBO.setMaintain(2);
//            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
//            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(1);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name非必填校验与请求参数Code非必填校验，则查询所有的大类且Maintain=2维护大类");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
            List<MainTypeListDTO.MainTypeDTO> mainTypeList =JsonPathUtils.parse(mainTypeListResult,"$.list[*]",new TypeRef<List<MainTypeListDTO.MainTypeDTO>>(){});
            Assert.assertTrue(mainTypeList.size()>0);
            Boolean flag=true;
            for (MainTypeListDTO.MainTypeDTO mainTypeBean:mainTypeList
            ) {
                if(!mainTypeBean.getIs_maintained().equals("2")){
                    flag=false;
                }
            }
            Assert.assertTrue(flag);
        }
    }

    /**
     * 查询不存在的分页，则返回实际存在的最后一页数据
     * @throws Exception
     */
    @Test
    public void mainTypeListTestByQueryNotExistPage() throws Exception {
        String mainTypeListUrl = null;
        MainTypeListBO mainTypeListBO = null;
        String mainTypeListResult = "";
        HashMap<String, String> hs=userLoginTest();
        destroyData();
        Integer Maintain=1;
        try {
            NewMainTypeBO newMainTypeBO = new NewMainTypeBO();
            newMainTypeBO.setUid(hs.get("uid"));
            newMainTypeBO.setToken(hs.get("token"));
            newMainTypeBO.setName("yy");
            newMainTypeBO.setMaintain(Maintain);
            newMainTypeBO.setAppid("1.00002");
            newMainTypeTest.newMainTypeTestByGernal(newMainTypeBO);

            MainTypeExample example=new MainTypeExample();
            example.createCriteria().andMainTypeNameEqualTo("yy");
            List<MainType> list=mainTypeRepository.selectByExample(example);

            mainTypeListUrl = url + "/ArticleService/maintypelist";
            mainTypeListBO = new MainTypeListBO();
            mainTypeListBO.setUid(hs.get("uid"));
            mainTypeListBO.setToken(hs.get("token"));
            mainTypeListBO.setAppid("1.00002");
            mainTypeListBO.setCount(10);
            //设置为0默认查询全部大类包括维护与正常的
            mainTypeListBO.setMaintain(0);
//            mainTypeListBO.setCode(list.get(0).getMainTypeCode());
//            mainTypeListBO.setName(list.get(0).getMainTypeName());
            mainTypeListBO.setPage(999999);

            log.info("mainTypeListUrl 请求的参数=" + mainTypeListUrl);
            log.info("mainTypeListBO 请求的参数=" + JSON.toJSONString(mainTypeListBO));
            mainTypeListResult = HttpUtil.postGeneralUrl(mainTypeListUrl, "application/json", JSON.toJSONString(mainTypeListBO), "UTF-8");
            log.info("mainTypeListResult 返回结果=" + mainTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询不存在的分页，则返回实际存在的最后一页数据");
            recordhttp.setUrl(mainTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(mainTypeListBO));
            recordhttp.setResponse(mainTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(mainTypeListResult.contains("count"), true);
            Assert.assertEquals(mainTypeListResult.contains("list"), true);
            Assert.assertEquals(mainTypeListResult.contains("id"), true);
            Assert.assertEquals(mainTypeListResult.contains("is_maintained"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(mainTypeListResult.contains("main_type_name"), true);
            Assert.assertEquals(mainTypeListResult.contains("page"), true);
            Assert.assertEquals(mainTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(mainTypeListResult.contains("\"result\":1"), true);
            List<MainTypeListDTO.MainTypeDTO> list=JsonPath.read(mainTypeListResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
        }
    }




    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {
        MainTypeExample example=new MainTypeExample();
        example.createCriteria().andMainTypeNameEqualTo("yy");
        mainTypeRepository.deleteByExample(example);
    }


}
