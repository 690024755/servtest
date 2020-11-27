package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteSubTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.LabListBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewLabBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.NewMainTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.LabListDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.LabServiceCon;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class LabListTest extends LabServiceCon {

    @Autowired
    private NewLabTest newLabTest;

    @Autowired
    private AssLabRepository assLabRepository;

    /**
     * 查询lab通用接口
     * @throws Exception
     */
    @Test
    public String labListTestByGernal(LabListBO labListBO) throws Exception {
        String labListUrl = null;
        String labListResult = "";
        try {
//            labListUrl = "http://172.16.0.25:28081" + "/ArticleService/lablist";
            labListUrl = url + "/ArticleService/lablist";
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询lab通用接口");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
           return labListResult;
        }
    }


    /**
     * 正常情况下获取lab信息
     * @throws Exception
     */
    @Test
    public void labListTestByNormal() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(5);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常情况下获取lab信息");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 参数count=0
     * @throws Exception
     */
    @Test
    public void labListTestByParameterCountValueIsZero() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(5);
            labListBO.setCount(0);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数count=0");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("\"msg\":\"count has a wrong value\""), true);
            Assert.assertEquals(labListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数count=-1
     * @throws Exception
     */
    @Test
    public void labListTestByParameterCountValueIsNegative() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(5);
            labListBO.setCount(-1);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数count=-1");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("\"msg\":\"count has a wrong value\""), true);
            Assert.assertEquals(labListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数count=10
     * @throws Exception
     */
    @Test
    public void labListTestByParameterCountValueIsTen() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(5);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数count=10");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Page=0
     * @throws Exception
     */
    @Test
    public void labListTestByParameterPageValueIsZero() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(0);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Page=0");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("\"msg\":\"page has a wrong value\""), true);
            Assert.assertEquals(labListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数Page=-1
     * @throws Exception
     */
    @Test
    public void labListTestByParameterPageValueIsNegative() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(-1);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Page=0");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("\"msg\":\"page has a wrong value\""), true);
            Assert.assertEquals(labListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数Page=10
     * @throws Exception
     */
    @Test
    public void labListTestByParameterPageValueIsTen() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Page=0");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数count校验
     * @throws Exception
     */
    @Test
    public void labListTestByNotExistParameterCount() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
//            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数count校验");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("\"msg\":\"count has a wrong value\""), true);
            Assert.assertEquals(labListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数page校验
     * @throws Exception
     */
    @Test
    public void labListTestByNotExistParameterPage() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
//            labListBO.setPage(10);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数page校验");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("\"msg\":\"page has a wrong value\""), true);
            Assert.assertEquals(labListResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 非必填参数code校验
     * @throws Exception
     */
    @Test
    public void labListTestByNotExistParameterCode() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
//            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数code校验");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 非必填参数Name校验
     * @throws Exception
     */
    @Test
    public void labListTestByNotExistParameterName() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
//            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Name校验");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 非必填参数Maintype校验
     * @throws Exception
     */
    @Test
    public void labListTestByNotExistParameterMaintype() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
//            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Maintype校验");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 根据name进行模糊匹配查找
     * @throws Exception
     */
    @Test
    public void labListTestByParameterNameIsFuzzyMatching() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
//            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName().substring(0,assLabList.get(0).getAssLabName().length()-1));
//            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据name进行模糊匹配查找");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 根据code进行精确匹配查找
     * @throws Exception
     */
    @Test
    public void labListTestByParameterCOdeIsNotFuzzyMatching() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
            labListBO.setName(assLabList.get(0).getAssLabName());
            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据name进行模糊匹配查找");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 请求参数Name非必填校验与请求参数Code非必填校验与请求参数Maintype请求参数，则查询所有lab信息
     * @throws Exception
     */
    @Test
    public void labListTestByNotExistParameterMaintypeAndNotExistParameterNameAndNotExistParameterCode() throws Exception {
        String labListUrl = null;
        LabListBO labListBO = null;
        String labListResult = "";
        String labName="yy";
        initData();
        try {
            NewLabBO newLabBO=new NewLabBO();
            newLabBO.setName(labName);
            newLabBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            newLabBO.setToken(hs.get("token"));
            newLabBO.setUid(hs.get("uid"));
            newLabBO.setAppid("1.00002");
            String newLabResult =newLabTest.newLabTestByGernal(newLabBO);
            Assert.assertEquals(newLabResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(newLabResult.contains("\"result\":1"), true);

            AssLabExample example=new AssLabExample();
            example.createCriteria().andAssLabNameEqualTo(labName);
            List<AssLab> assLabList=assLabRepository.selectByExample(example);

            labListUrl = url + "/ArticleService/lablist";
            labListBO = new LabListBO();
            labListBO.setToken(hs.get("token"));
            labListBO.setUid(hs.get("uid"));
            labListBO.setAppid("1.00002");
//            labListBO.setMaintype(mainTypeList.get(0).getMainTypeCode());
            labListBO.setPage(10);
            labListBO.setCount(10);
//            labListBO.setName(assLabList.get(0).getAssLabName());
//            labListBO.setCode(assLabList.get(0).getAssLabCode());
            log.info("labListUrl 请求的参数=" + labListUrl);
            log.info("labListBO 请求的参数=" + JSON.toJSONString(labListBO));
            labListResult = HttpUtil.postGeneralUrl(labListUrl, "application/json", JSON.toJSONString(labListBO), "UTF-8");
            log.info("labListResult 返回结果=" + labListResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroyData();
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Name非必填校验与请求参数Code非必填校验与请求参数Maintype请求参数，则查询所有lab信息");
            recordhttp.setUrl(labListUrl);
            recordhttp.setRequest(JSON.toJSONString(labListBO));
            recordhttp.setResponse(labListResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(labListResult.contains("count"), true);
            Assert.assertEquals(labListResult.contains("list"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_code"), true);
            Assert.assertEquals(labListResult.contains("ass_lab_name"), true);
            Assert.assertEquals(labListResult.contains("id"), true);
            Assert.assertEquals(labListResult.contains("main"), true);
            Assert.assertEquals(labListResult.contains("main_type_code"), true);
            Assert.assertEquals(labListResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(labListResult.contains("page"), true);
            Assert.assertEquals(labListResult.contains("\"result\":1"), true);
            List<LabListDTO.LabDTO> list=JsonPathUtils.parse(labListResult, "$.list[*]", new TypeRef<List<LabListDTO.LabDTO>>(){
            });
            Assert.assertTrue(list.size()>5);
        }
    }

}
