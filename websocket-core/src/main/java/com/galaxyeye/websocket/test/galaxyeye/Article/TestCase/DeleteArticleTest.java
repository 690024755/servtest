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
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.SaveArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Slf4j
@Component
public class DeleteArticleTest extends BaseTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private SaveArticleTest saveArticleTest;

    @Autowired
    private ArticleStatisticsTest articleStatisticsTest;

    /**
     * 通用性删除文章
     */
    public void deleteArticleTestByGernal(String id) {
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        try {
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除存在文章,参数是文章id");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
        }
    }

    /**
     * 删除不存在文章,参数是文章id
     */
    @Test
    public void deleteArticleTestByNotExistArticle1() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        try {
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId("613914239605673984");
//            deleteArticleBO.setTitle("食道癌术后护理方法有哪些");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在文章,参数是文章id");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"article_notexist\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":400"), true);
        }
    }

    /**
     * 删除不存在文章,参数是文章Title
     */
    @Test
    public void deleteArticleTestByNotExistArticle2() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        try {
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
//            deleteArticleBO.setId("613914239605673984");
            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在文章,参数是文章Title");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"article_notexist\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":400"), true);
        }
    }

    /**
     * 删除不存在，文章的请求参数id与title不匹配，以id为准，不校验title
     */
    @Test
    public void deleteArticleTestByNotExistArticle3() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        try {
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId("613914239605673984");
            deleteArticleBO.setTitle("产后恢复1");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除文章的请求参数id与title不匹配，以id为准，不校验title");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"article_notexist\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":400"), true);
        }
    }

    /**
     * 删除存在文章,参数是文章id
     */
    @Test
    public void deleteArticleTestByExistArticle1() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除存在文章,参数是文章id");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 删除存在文章,参数是文章Title
     */
    @Test
    public void deleteArticleTestByExistArticle2() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
//            deleteArticleBO.setId("613914239605673984");
            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除存在文章,参数是文章Title");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 删除已存在文章，请求参数id与title不匹配，以id为准，不校验title
     */
    @Test
    public void deleteArticleTestByExistArticle3() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId("613914239605673984");
            deleteArticleBO.setTitle("食道癌术后护理方法有哪些");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除已存在文章，请求参数id与title不匹配，以id为准，不校验title");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 删除已存在文章，请求参数id与title均匹配
     */
    @Test
    public void deleteArticleTestByExistArticle4() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId("613914239605673984");
            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除已存在文章，请求参数id与title均匹配");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 删除已存在文章，请求参数id与title均匹配,用户read、push、share、collect、favor文章
     */
    @Test
    public void deleteArticleTestByExistArticle5() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String tilte="产后恢复";
        String appid="1.00002";
        String articleId="613914239605673984";
        HashMap<String, String> hs=userLoginTest();
        try {
            //删除文章信息与文章行为记录
            saveArticleTest.SaveArticleTestByParameter(articleId,tilte,true);
            articleStatisticsTest.ArticleStatisticsByParameter(appid,articleId,hs.get("uid"),hs.get("token"));
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(articleId);
            deleteArticleBO.setTitle(tilte);
            deleteArticleBO.setToken(hs.get("token"));
             deleteArticleBO.setUid(hs.get("uid"));
            deleteArticleBO.setAppid(appid);
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除已存在文章，请求参数id与title均匹配,用户read、push、share、collect、favor文章");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 方法deletearticle配置在OpenMethod当中，不校验Token
     */
    @Test
    public void deleteArticleTestByOpenMethodAndUid() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        HashMap<String, String> hs=userLoginTest();
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
//            deleteArticleBO.setToken(hs.get("token"));
            deleteArticleBO.setUid(hs.get("uid"));
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法deletearticle配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 方法deletearticle配置在OpenMethod当中，不校验AccessToken
     */
    @Test
    public void deleteArticleTestByOpenMethodAndBmAppid() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
//            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("1.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法deletearticle配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 方法deletearticle配置在authMethod当中，校验Token
     */
    @Test
    public void deleteArticleTestByAuthMethodAndUid() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        HashMap<String, String> hs=userLoginTest();
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setToken(hs.get("token"));
            deleteArticleBO.setUid(hs.get("uid"));
            deleteArticleBO.setAppid("100.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法deletearticle配置在authMethod当中，校验Token");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 方法deletearticle配置在authMethod当中，不校验Token
     */
    @Test
    public void deleteArticleTestByAuthMethodAndUidAndNotExistParameterToken() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        HashMap<String, String> hs=userLoginTest();
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
//            deleteArticleBO.setToken(hs.get("token"));
            deleteArticleBO.setUid(hs.get("uid"));
            deleteArticleBO.setAppid("100.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法deletearticle配置在authMethod当中，不校验Token");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":101"), true);
        }
    }



    /**
     * 方法deletearticle配置在authMethod当中，校验AccessToken
     */
    @Test
    public void deleteArticleTestByAuthMethodAndBmAppid() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("100.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法deletearticle配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 方法deletearticle配置在authMethod当中，不校验AccessToken
     */
    @Test
    public void deleteArticleTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() {
        destroyData();
        String deletearticleUrl = null;
        DeleteArticleBO deleteArticleBO = null;
        String deletearticleResult = "";
        String id="613914239605673984";
        String tilte="产后恢复";
        try {
            saveArticleTest.SaveArticleTestByParameter(id,tilte,true);
            deletearticleUrl = url + "/ArticleService/deletearticle";
            deleteArticleBO = new DeleteArticleBO();
            deleteArticleBO.setId(id);
//            deleteArticleBO.setTitle("产后恢复");
//            deleteArticleBO.setAccessToken(bmAppids.get("1.00002"));
            deleteArticleBO.setBmAppid("1.00002");
            deleteArticleBO.setAppid("100.00002");
            deleteArticleBO.setSeq("abc");
            log.info("deletearticleUrl 请求的参数=" + deletearticleUrl);
            log.info("deleteArticleBO 请求的参数=" + JSON.toJSONString(deleteArticleBO));
            deletearticleResult = HttpUtil.postGeneralUrl(deletearticleUrl, "application/json", JSON.toJSONString(deleteArticleBO), "UTF-8");
            log.info("deletearticleResult 返回结果=" + deletearticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法deletearticle配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(deletearticleUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteArticleBO));
            recordhttp.setResponse(deletearticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(deletearticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(deletearticleResult.contains("\"result\":101"), true);
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
        deleteArticleTestByGernal("613914239605673984");
        deleteArticleTestByGernal("608898375646973952");
    }
}
