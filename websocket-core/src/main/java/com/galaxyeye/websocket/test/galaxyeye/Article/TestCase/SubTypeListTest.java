package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ArticleCatAssLabRepository;
import com.galaxyeye.websocket.application.repository.ArticleCatAssSubTypeRepository;
import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.application.repository.AssSubTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.MainTypeListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewSubTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.SubTypeListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.SubTypeListDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.Json.JsonPathUtils;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.TypeRef;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class SubTypeListTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;

    @Autowired
    private NewSubTypeTest newSubTypeTest;

    @Autowired
    private ArticleCatAssLabRepository articleCatAssLabRepository;

    private HashMap<String, String> hs=null;
    private List<MainType> mainTypeList= null;
    /**
     * 查询文章小类通用接口
     * @throws Exception
     */
    public String subTypeListTestByGernal(SubTypeListBO subTypeListBO) throws Exception {
        String subTypeListUrl = null;
        String subTypeListResult = "";
        try {
            subTypeListUrl = url + "/ArticleService/subtypelist";
//            subTypeListUrl = "http://172.16.0.25:28081" + "/ArticleService/subtypelist";
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询文章小类通用接口");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
           return subTypeListResult;
        }
    }



    /**
     * 获取文章小类信息
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNormal() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";

        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取文章小类信息");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 参数Count=0
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterCountValueIsZero() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(0);
            subTypeListBO.setPage(5);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Count=0");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("\"result\":101"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"count has a wrong value\""), true);
        }
    }


    /**
     * 参数Count=-1
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterCountValueIsNegative() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(-1);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Count=-1");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("\"result\":101"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"count has a wrong value\""), true);
        }
    }

    /**
     * 参数Count=10
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterCountValueIsTen() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Count=10");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 参数Page=0
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterPageValueIsZero() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(0);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Page=0");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("\"result\":101"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"page has a wrong value\""), true);
        }
    }


    /**
     * 参数Page=-1
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterPageValueIsNegative() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(-1);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Page=0");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("\"result\":101"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"page has a wrong value\""), true);
        }
    }

    /**
     * 参数Page=5
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterPageValueIsFive() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Page=5");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 必填参数Page校验
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNotExistParameterPage() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        String subName="yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
//            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Page校验");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("\"result\":101"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"page has a wrong value\""), true);
        }
    }

    /**
     * 必填参数Count校验
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNotExistParameterCount() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
//            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Count校验");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("\"result\":101"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"count has a wrong value\""), true);
        }
    }

    /**
     * 非必填参数Name校验
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNotExistParameterName() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        List<AssSubType> assSubTypeList=null;
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
//            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Name校验");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
            SubTypeListDTO subTypeListDTO=JsonPathUtils.parse(subTypeListResult, "$", new TypeRef<SubTypeListDTO>() {
            });
            List<SubTypeListDTO.ListBean> listBean=subTypeListDTO.getList();
            Assert.assertTrue(listBean.get(0).getAss_sub_type_code().equals(assSubTypeList.get(0).getAssSubTypeCode()));
            Assert.assertTrue(listBean.get(0).getMain_type_code().equals(mainTypeList.get(0).getMainTypeCode()));
        }
    }

    /**
     * 非必填参数Code校验
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNotExistParameterCode() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        List<AssSubType> assSubTypeList=null;
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
//            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Code校验");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
            SubTypeListDTO subTypeListDTO=JsonPathUtils.parse(subTypeListResult, "$", new TypeRef<SubTypeListDTO>() {
            });
            List<SubTypeListDTO.ListBean> listBean=subTypeListDTO.getList();
            Assert.assertTrue(listBean.get(0).getAss_sub_type_name().equals(subName));
            Assert.assertTrue(listBean.get(0).getMain_type_code().equals(mainTypeList.get(0).getMainTypeCode()));
        }
    }

    /**
     * 非必填参数Maintype校验
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNotExistParameterMaintype() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        List<AssSubType> assSubTypeList=null;
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
//            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Maintype校验");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
            SubTypeListDTO subTypeListDTO=JsonPathUtils.parse(subTypeListResult, "$", new TypeRef<SubTypeListDTO>() {
            });
            List<SubTypeListDTO.ListBean> listBean=subTypeListDTO.getList();
            Assert.assertTrue(listBean.get(0).getAss_sub_type_name().equals(subName));
            Assert.assertTrue(listBean.get(0).getAss_sub_type_code().equals(assSubTypeList.get(0).getAssSubTypeCode()));

        }
    }

    /**
     * 非必填参数Maintype校验与非必填参数Name及非必填参数Code，则查询全部小类信息
     * @throws Exception
     */
    @Test
    public void subTypeListTestByNotExistParameterMaintypeAndNotExistParameterNameAndNotExistParameterCode() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        List<AssSubType> assSubTypeList=null;
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
//            subTypeListBO.setName(subName);
//            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10000);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
//            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Maintype校验与非必填参数Name及非必填参数Code，则查询全部小类信息");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("ass_sub_type_name"), true);
            Assert.assertEquals(subTypeListResult.contains("id"), true);
            Assert.assertEquals(subTypeListResult.contains("main"), true);
            Assert.assertEquals(subTypeListResult.contains("main_type_code"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
            SubTypeListDTO subTypeListDTO=JsonPathUtils.parse(subTypeListResult, "$", new TypeRef<SubTypeListDTO>() {
            });
            List<SubTypeListDTO.ListBean> listBean=subTypeListDTO.getList();
            Assert.assertTrue(listBean.get(0).getAss_sub_type_name().equals(subName));
            Assert.assertTrue(listBean.get(0).getAss_sub_type_code().equals(assSubTypeList.get(0).getAssSubTypeCode()));
            Assert.assertTrue(listBean.get(0).getMain_type_code().equals(assSubTypeList.get(0).getMainTypeCode()));
        }
    }

    /**
     *  请求参数code是精确匹配，为模糊匹配则查询不到
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterCodeValueIsFuzzyMatching() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            List<AssSubType> assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName);
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10000);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode().substring(0,assSubTypeList.get(0).getAssSubTypeCode().length()-1));
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe(" 请求参数code是精确匹配，为模糊匹配则查询不到");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(subTypeListResult.contains("count"), true);
            Assert.assertEquals(subTypeListResult.contains("list"), true);
            Assert.assertEquals(subTypeListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(subTypeListResult.contains("\"result\":1"), true);
            Assert.assertEquals(subTypeListResult.contains("page"), true);
            SubTypeListDTO subTypeListDTO=JsonPathUtils.parse(subTypeListResult, "$", new TypeRef<SubTypeListDTO>() {
            });
            List<SubTypeListDTO.ListBean> listBean=subTypeListDTO.getList();
            Assert.assertTrue(listBean.size()==0);
        }
    }

    /**
     * 参数name为模糊查找
     * @throws Exception
     */
    @Test
    public void subTypeListTestByParameterNameValueIsFuzzyMatching() throws Exception {
        String subTypeListUrl = null;
        SubTypeListBO subTypeListBO = null;
        String subTypeListResult = "";
        HashMap<String, String> hs = userLoginTest();
        initData();
        String subName = "yy";
        List<AssSubType> assSubTypeList=null;
        try {
            NewSubTypeBO newSubTypeBO = new NewSubTypeBO();
            newSubTypeBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newSubTypeBO.setName(subName);
            newSubTypeBO.setToken(hs.get("token"));
            newSubTypeBO.setUid(hs.get("uid"));
            newSubTypeBO.setAppid("1.00002");
            String newSubTypeResult = newSubTypeTest.newSubTypeTestByGernal(newSubTypeBO);
            Assert.assertEquals(newSubTypeResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newSubTypeResult.contains("\"result\":1"), true);

            AssSubTypeExample example=new AssSubTypeExample();
            example.createCriteria().andAssSubTypeNameEqualTo(subName);
            assSubTypeList=assSubTypeRepository.selectByExample(example);

            subTypeListUrl = url + "/ArticleService/subtypelist";
            subTypeListBO = new SubTypeListBO();
            subTypeListBO.setName(subName.substring(0,subName.length()-1));
            subTypeListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            subTypeListBO.setCount(10000);
            subTypeListBO.setPage(5);
            subTypeListBO.setToken(hs.get("token"));
            subTypeListBO.setCode(assSubTypeList.get(0).getAssSubTypeCode());
            subTypeListBO.setUid(hs.get("uid"));
            subTypeListBO.setAppid("1.00002");
            log.info("subTypeListUrl 请求的参数=" + subTypeListUrl);
            log.info("subTypeListBO 请求的参数=" + JSON.toJSONString(subTypeListBO));
            subTypeListResult = HttpUtil.postGeneralUrl(subTypeListUrl, "application/json", JSON.toJSONString(subTypeListBO), "UTF-8");
            log.info("subTypeListResult 返回结果=" + subTypeListResult);
            destroyData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数name为模糊查找");
            recordhttp.setUrl(subTypeListUrl);
            recordhttp.setRequest(JSON.toJSONString(subTypeListBO));
            recordhttp.setResponse(subTypeListResult);
            initLog(recordhttp, new Object() {
            });
            SubTypeListDTO subTypeListDTO=JsonPathUtils.parse(subTypeListResult, "$", new TypeRef<SubTypeListDTO>() {
            });
            List<SubTypeListDTO.ListBean> listBean=subTypeListDTO.getList();
            Assert.assertTrue(listBean.get(0).getAss_sub_type_name().equals(subName));
            Assert.assertTrue(listBean.get(0).getAss_sub_type_code().equals(assSubTypeList.get(0).getAssSubTypeCode()));
            Assert.assertTrue(listBean.get(0).getMain_type_code().equals(assSubTypeList.get(0).getMainTypeCode()));
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        destroyData();
        newSubTypeTest.initData();
        hs=newSubTypeTest.getHs();
        mainTypeList=newSubTypeTest.getMainTypeList();
    }

    @Override
    public void destroyData() {
        newSubTypeTest.destroyData();
    }
}
