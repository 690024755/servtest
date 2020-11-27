package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 15:02
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.AuditArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.GetArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 */
@Slf4j
@Component
public class AuditArticleTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 一次审核一篇文章
     * @throws Exception
     */
    @Test
    public void auditarticleTestByOne() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("613914239605673984");
            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次审核一篇文章");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("\"strIds\":[\"613914239605673984\"]"), true);
        }
    }

    /**
     * 一次审核二篇文章
     * @throws Exception
     */
    @Test
    public void auditarticleTestByTwo() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        initData();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("一次审核二篇文章");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[608898375646973952,613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[608898375646973952,613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("\"strIds\":[\"608898375646973952\",\"613914239605673984\"]"), true);
        }
    }

    /**
     * 审核不存在的文章
     * @throws Exception
     */
    @Test
    public void auditarticleTestByNotExistArticle() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        destroyData();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("审核不存在的文章");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"article_notexist\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":400"), true);
        }
    }

    /**
     * 使用uid与token进行文章审核
     * @throws Exception
     */
    @Test
    public void auditarticleTestByUidAndToken() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        HashMap<String, String> hs= userLoginTest();
        initData();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
            auditArticleBO.setToken(hs.get("token"));
            auditArticleBO.setUid(hs.get("uid"));
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token进行文章审核");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[608898375646973952,613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[608898375646973952,613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("\"strIds\":[\"608898375646973952\",\"613914239605673984\"]"), true);
        }
    }

    /**
     * 使用BmAppid与AccessToken进行文章审核
     * @throws Exception
     */
    @Test
    public void auditarticleTestByBmAppidAndAccessToken() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        initData();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken进行文章审核");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[608898375646973952,613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[608898375646973952,613914239605673984]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("\"strIds\":[\"608898375646973952\",\"613914239605673984\"]"), true);
        }
    }

    /**
     * 必填参数ids校验
     * @throws Exception
     */
    @Test
    public void auditarticleTestByNotExistParameterIds() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
//            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数ids校验");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"ids\":[]"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("\"strIds\":[]"), true);
        }
    }

    /**
     * 重复审核同一篇文章
     * @throws Exception
     */
    @Test
    public void auditarticleTestByRepeatAudit() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        initData();
        for (int i = 0; i < 2; i++) {
            try {
                auditarticleUrl = url + "/ArticleService/auditarticle";
                auditArticleBO = new AuditArticleBO();
                List<String> ids = new ArrayList<>();
                ids.add("613914239605673984");
                auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
                auditArticleBO.setBmAppid("1.00002");
                auditArticleBO.setAppid("1.00002");
                auditArticleBO.setSeq("abc");
                auditArticleBO.setEnable(true);
                auditArticleBO.setIds(ids);
                log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
                log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
                auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
                log.info("auditarticleResult 返回结果=" + auditarticleResult);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("重复审核同一篇文章");
                recordhttp.setUrl(auditarticleUrl);
                recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
                recordhttp.setResponse(auditarticleResult);
                initLog(recordhttp, new Object() {
                });
                Assert.assertEquals(auditarticleResult.contains("\"ids\":[613914239605673984]"), true);
                Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
                Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
                Assert.assertEquals(auditarticleResult.contains("\"strIds\":[\"613914239605673984\"]"), true);
            }
        }
    }


    /**
     * 方法auditarticle配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void auditarticleTestByOpenMethodAndUid() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
//            auditArticleBO.setToken(hs.get("token"));
            auditArticleBO.setUid(hs.get("uid"));
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法auditarticle配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("ids"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("strIds"), true);
            List<Long> list=JsonPath.read(auditarticleResult,"$.ids[*]");
            Assert.assertTrue(list.size()==2);
            Assert.assertEquals(auditarticleResult.contains("strIds"), true);
        }
    }

    /**
     * 方法auditarticle配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void auditarticleTestByOpenMethodAndBmAppid() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
//            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("1.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法auditarticle配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            List<Long> list=JsonPath.read(auditarticleResult,"$.ids[*]");
            Assert.assertTrue(list.size()==2);
            Assert.assertEquals(auditarticleResult.contains("ids"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("strIds"), true);
        }
    }


    /**
     * 方法auditarticle配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void auditarticleTestByAuthMethodAndUid() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
            auditArticleBO.setToken(hs.get("token"));
            auditArticleBO.setUid(hs.get("uid"));
            auditArticleBO.setAppid("100.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法auditarticle配置在authMethod当中，校验Token");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            List<Long> list=JsonPath.read(auditarticleResult,"$.ids[*]");
            Assert.assertTrue(list.size()==2);
            Assert.assertEquals(auditarticleResult.contains("ids"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("strIds"), true);
        }
    }


    /**
     * 方法auditarticle配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void auditarticleTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
//            auditArticleBO.setToken(hs.get("token"));
            auditArticleBO.setUid(hs.get("uid"));
            auditArticleBO.setAppid("100.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法auditarticle配置在authMethod当中，不校验Token");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法auditarticle配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void auditarticleTestByAuthMethodAndBmAppid() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("100.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法auditarticle配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            List<Long> list=JsonPath.read(auditarticleResult,"$.ids[*]");
            Assert.assertTrue(list.size()==2);
            Assert.assertEquals(auditarticleResult.contains("ids"), true);
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(auditarticleResult.contains("strIds"), true);
        }
    }


    /**
     * 方法auditarticle配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void auditarticleTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String auditarticleUrl = null;
        AuditArticleBO auditArticleBO = null;
        String auditarticleResult = "";
        try {
            auditarticleUrl = url + "/ArticleService/auditarticle";
            auditArticleBO = new AuditArticleBO();
            List<String> ids = new ArrayList<>();
            ids.add("608898375646973952");
            ids.add("613914239605673984");
//            auditArticleBO.setAccessToken(bmAppids.get("1.00002"));
            auditArticleBO.setBmAppid("1.00002");
            auditArticleBO.setAppid("100.00002");
            auditArticleBO.setSeq("abc");
            auditArticleBO.setEnable(true);
            auditArticleBO.setIds(ids);
            log.info("auditarticleUrl 请求的参数=" + auditarticleUrl);
            log.info("auditArticleBO 请求的参数=" + JSON.toJSONString(auditArticleBO));
            auditarticleResult = HttpUtil.postGeneralUrl(auditarticleUrl, "application/json", JSON.toJSONString(auditArticleBO), "UTF-8");
            log.info("auditarticleResult 返回结果=" + auditarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法auditarticle配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(auditarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(auditArticleBO));
            recordhttp.setResponse(auditarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(auditarticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(auditarticleResult.contains("\"result\":101"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        try {
            ArticleExample example = new ArticleExample();
            ArticleExample.Criteria cr = example.createCriteria();
            List<Long> ids = new ArrayList<>();
            ids.add(613914239605673984L);
            ids.add(608898375646973952L);
            cr.andIdIn(ids);
            articleRepository.deleteByExample(example);
            Article record1 = new Article();
            record1.setArticleType(null);
            record1.setAssLabs("assLabName4434");
            record1.setComment("新增");
            record1.setCreatedAt(DateTool.toDate("2019-08-22 11:10:42", DateTool.DATE_FMT_5));
            record1.setDeletedAt(null);
            record1.setEditor("李梦颖1");
            record1.setEnable(0);
            record1.setHtmltext("article/200529/613914239605673984/613914239605673984.body");
            record1.setId(613914239605673984L);
            record1.setInfo("无");
            record1.setIntro("10月怀胎，体质、生活习惯的不同，妈妈们的身体状况也让不尽相同。但造物主是公平的，在产后会给每个妈妈都提供一段心理、生理修复的最佳时期，让你重回少女时代。");
            record1.setLabs("03004435|03004434");
            record1.setMainType("母婴");
            record1.setQuality("优");
            record1.setRefUrl("https://baike.pcbaby.com.cn/qzbd/1136412.html");
            record1.setSrcSite("PCbaby百科");
            record1.setSubType("02003033|02003034");
            record1.setThumbnail("http://47.96.10.106/img/article/200529/1266192839914557440.jpg");
            record1.setTitle("产后恢复");
            record1.setUpdatedAt(DateTool.toDate("2020-05-29 10:22:40", DateTool.DATE_FMT_5));
            articleRepository.insertSelective(record1);
            Article record2 = new Article();
            record2.setArticleType(null);
            record2.setAssLabs(null);
            record2.setComment("已改");
            record2.setCreatedAt(DateTool.toDate("2019-08-08 13:44:48", DateTool.DATE_FMT_5));
            record2.setDeletedAt(null);
            record2.setEditor("帅楠");
            record2.setEnable(0);
            record2.setHtmltext("article/190808/608898375646973952/608914247325323265.html");
            record2.setId(608898375646973952L);
            record2.setInfo(null);
            record2.setIntro("产后恢复体操可以帮助产后妈妈进行骨盆韧带排列恢复、腹部和骨盆肌肉群的功能恢复，使产后妈妈及早恢复体形，树立信心。");
            record2.setLabs("产后|产后恢复体操");
            record2.setMainType("母婴");
            record2.setQuality("良");
            record2.setRefUrl("https://baike.pcbaby.com.cn/qzbd/772.html");
            record2.setSrcSite("PCbaby");
            record2.setSubType("哺乳期");
            record2.setThumbnail("article/190808/608898375646973952/thumbnail_608914246155112448.jpg");
            record2.setTitle("产后恢复体操");
            record2.setUpdatedAt(DateTool.toDate("2019-08-09 15:38:08", DateTool.DATE_FMT_5));
            articleRepository.insertSelective(record2);
            //重启服务，缓存生效
            applicationServiceManaged.restartArticlePid();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public void destroyData() {
        try{
            ArticleExample example = new ArticleExample();
            ArticleExample.Criteria cr = example.createCriteria();
            List<Long> ids = new ArrayList<>();
            ids.add(613914239605673984L);
            ids.add(608898375646973952L);
            cr.andIdIn(ids);
            articleRepository.deleteByExample(example);
            //重启服务，缓存生效
            applicationServiceManaged.restartArticlePid();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
