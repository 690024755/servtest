package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase;

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.GetArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
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


@Component("articleGetArticleTest")
@Slf4j
public class GetArticleTest extends BaseTest {
    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SaveArticleTest saveArticleTest;

    @Autowired
    private DeleteArticleTest deleteArticleTest;

    /**
     * 通用性接口获取文章信息
     * @param id
     * @param title
     * @throws Exception
     */
    public void getarticleByGernal(String id, String title) throws Exception {

        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId(id);
            getArticleBO.setTitle(title);
            getArticleBO.setDataFormat("body");
            getArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            getArticleBO.setSeq("abc");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用性接口获取文章信息");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
            //配置文件的env="prd",返回的图片带-slim，在表article.thumbnail,go代码自动添加
            //配置文件的env="dev",返回的图片不带-slim，原生返回数据图片地址
//            Assert.assertEquals(getarticleResult.contains("-slim"), true);
            Assert.assertEquals(getarticleResult.contains("img/article/"), true);

        }
    }

    /**
     * 使用bmappid与accessToken获取文章信息
     * @throws Exception
     */
    @Test
    public void getarticleTestByBmAppidAndAccessToken() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用bmappid与accessToken获取文章信息");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
            //配置文件的env="prd",返回的图片带-slim，在表article.thumbnail,go代码自动添加
            //配置文件的env="dev",返回的图片不带-slim，原生返回数据图片地址
//            Assert.assertEquals(getarticleResult.contains("-slim"), true);
            Assert.assertEquals(getarticleResult.contains("img/article/"), true);
        }
    }

    /**
     * 使用uid与Token获取文章信息
     * @throws Exception
     */
    @Test
    public void getarticleTestByUidAndToken() throws Exception {
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与Token获取文章信息");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
            //配置文件的env="prd",返回的图片带-slim，在表article.thumbnail,go代码自动添加
            //配置文件的env="dev",返回的图片不带-slim，原生返回数据图片地址
//            Assert.assertEquals(getarticleResult.contains("-slim"), true);
            Assert.assertEquals(getarticleResult.contains("img/article/"), true);
        }
    }

    /**
     * 参数Id传递,title传递，获取文章
     * @throws Exception
     */
    @Test
    public void getarticleTestByParameterIdIsExistAndParameterTitleIsExist() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Id传递,title传递，获取文章");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     * 参数Id传递,title不传，获取文章
     * @throws Exception
     */
    @Test
    public void getarticleTestByParameterIdIsExistAndParameterTitleIsNotExist() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
//            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Id传递,title不传，获取文章");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     * 参数Id不传,title传递，获取文章
     * @throws Exception
     */
    @Test
    public void getarticleTestByNotExistParameterIdAndParameterTitleIsExist() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//          getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Id不传,title传递，获取文章");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     * 获取不存在的文章
     * @throws Exception
     */
    @Test
    public void getarticleTestByGetNotExistArticle() throws Exception {
        destroyData();
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取不存在的文章");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"article_notexist\""), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":400"), true);
        }
    }

    /**
     * 参数DataFormat为list
     * @throws Exception
     */
    @Test
    public void getarticleTestByParameterDataFormatIsList() throws Exception {
        destroyData();
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("list");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数DataFormat为list");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("contents"), true);
            Assert.assertEquals(getarticleResult.contains("type"), true);
            Assert.assertEquals(getarticleResult.contains("\"type\":\"text\""), true);
            Assert.assertEquals(getarticleResult.contains("content"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
            Assert.assertTrue(JSON.parseObject(getarticleResult).getJSONArray("contents").size() > 0);
        }
    }

    /**
     * 参数DataFormat为body
     * @throws Exception
     */
    @Test
    public void getarticleTestByParameterDataFormatIsBody() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数DataFormat为body");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     *请求参数title为模糊查询
     * @throws Exception
     */
    @Test
    public void getarticleTestByTitleIsLike() throws Exception {
       destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数title为模糊查询");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     *方法getarticle配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getarticleTestByOpenMethodAndUid() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
//            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticle配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     *方法getarticle配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getarticleTestByOpenMethodAndBmAppid() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
//            getArticleBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("1.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticle配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     *方法getarticle配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void getarticleTestByAuthMethodAndUid() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setAppid("100.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticle配置在authMethod当中，校验Token");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     *方法getarticle配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getarticleTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setUid(hs.get("uid"));
//            getArticleBO.setToken(hs.get("token"));
            getArticleBO.setAppid("100.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticle配置在authMethod当中，不校验Token");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":101"), true);
        }
    }

    /**
     *方法getarticle配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void getarticleTestByAuthMethodAndBmAppid() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
            getArticleBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("100.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticle配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("assLabs"), true);
            Assert.assertEquals(getarticleResult.contains("assSubType"), true);
            Assert.assertEquals(getarticleResult.contains("collect"), true);
            Assert.assertEquals(getarticleResult.contains("comment"), true);
            Assert.assertEquals(getarticleResult.contains("body"), true);
            Assert.assertEquals(getarticleResult.contains("info"), true);
            Assert.assertEquals(getarticleResult.contains("createdAt"), true);
            Assert.assertEquals(getarticleResult.contains("editor"), true);
            Assert.assertEquals(getarticleResult.contains("enable"), true);
            Assert.assertEquals(getarticleResult.contains("favor"), true);
            Assert.assertEquals(getarticleResult.contains("id"), true);
            Assert.assertEquals(getarticleResult.contains("infoStr"), true);
            Assert.assertEquals(getarticleResult.contains("intro"), true);
            Assert.assertEquals(getarticleResult.contains("labs"), true);
            Assert.assertEquals(getarticleResult.contains("mainType"), true);
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleResult.contains("quality"), true);
            Assert.assertEquals(getarticleResult.contains("refUrl"), true);
            Assert.assertEquals(getarticleResult.contains("remoteHome"), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(getarticleResult.contains("srcSite"), true);
            Assert.assertEquals(getarticleResult.contains("strId"), true);
            Assert.assertEquals(getarticleResult.contains("subType"), true);
            Assert.assertEquals(getarticleResult.contains("thumbnail"), true);
            Assert.assertEquals(getarticleResult.contains("title"), true);
            Assert.assertEquals(getarticleResult.contains("uid"), true);
            Assert.assertEquals(getarticleResult.contains("updatedAt"), true);
        }
    }

    /**
     *方法getarticle配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getarticleTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        destroyData();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984","产后恢复",true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952","产后恢复1",true);
        String getarticleUrl = null;
        GetArticleBO getArticleBO = null;
        String getarticleResult = "";
        try {
            getarticleUrl = url + "/ArticleService/getarticle";
            getArticleBO = new GetArticleBO();
//            getArticleBO.setId("613914239605673984");
            getArticleBO.setTitle("产后恢复");
            getArticleBO.setDataFormat("body");
//            getArticleBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleBO.setBmAppid("1.00002");
            getArticleBO.setAppid("100.00002");
            log.info("getarticleUrl 请求的参数=" + getarticleUrl);
            log.info("getArticleBO 请求的参数=" + JSON.toJSONString(getArticleBO));
            getarticleResult = HttpUtil.postGeneralUrl(getarticleUrl, "application/json", JSON.toJSONString(getArticleBO), "UTF-8");
            log.info("getarticleResult 返回结果=" + getarticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticle配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(getarticleUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleBO));
            recordhttp.setResponse(getarticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getarticleResult.contains("\"result\":101"), true);
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
        deleteArticleTest.deleteArticleTestByGernal("613914239605673984");
        deleteArticleTest.deleteArticleTestByGernal("608898375646973952");
    }
}
