package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase.XmlCase
 * @Date Create on 11:08
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/29日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.FetchArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
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
public class FetchArticleTest extends BaseTest {

    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;

    @Test
    public void fetchArticleTest() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setBmAppid("1.00002");
            fetchArticleBO.setAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("fetchArticle文章的分数");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 使用uid与token获取fetchArticle
     */
    @Test
    public void fetchArticleTestByUidAndToken() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setToken(hs.get("token"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token获取fetchArticle");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 使用BmAppid与AccessToken获取fetchArticle，此时参数uid必填
     */
    @Test
    public void fetchArticleTestByBmAppidAndAccessToken() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("1.00002");
            fetchArticleBO.setBmAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken获取fetchArticle，此时参数uid必填");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 方法fetcharticle配置在OpenMethod当中，不校验Token
     */
    @Test
    public void fetchArticleTestByOpenMethodAndUid() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setToken(hs.get("token"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法fetcharticle配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 方法fetcharticle配置在OpenMethod当中，不校验AccessToken
     */
    @Test
    public void fetchArticleTestByOpenMethodAndBmAppid() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            fetchArticleBO.setAppid("1.00002");
            fetchArticleBO.setBmAppid("1.00002");
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法fetcharticle配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 方法fetcharticle配置在authMethod当中，校验Token
     */
    @Test
    public void fetchArticleTestByAuthMethodAndUid() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setToken(hs.get("token"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("100.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法fetcharticle配置在authMethod当中，校验Token");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 方法fetcharticle配置在authMethod当中，不校验Token
     */
    @Test
    public void fetchArticleTestByAuthMethodAndUidAndNotExistParameterToken() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
//            fetchArticleBO.setToken(hs.get("token"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("100.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法fetcharticle配置在authMethod当中，不校验Token");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法fetcharticle配置在authMethod当中，校验AccessToken
     */
    @Test
    public void fetchArticleTestByAuthMethodAndBmAppid() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("100.00002");
            fetchArticleBO.setBmAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法fetcharticle配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }

    /**
     * 方法fetcharticle配置在authMethod当中，不校验AccessToken
     */
    @Test
    public void fetchArticleTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
//            fetchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("100.00002");
            fetchArticleBO.setBmAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法fetcharticle配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 所有文章处于维护状态，获取文章分数
     * 使用BmAppid与AccessToken获取fetchArticle，此时参数uid必填，此时该篇文章处于维护状态
     */
    @Test
    public void fetchArticleTestByBmAppidAndAccessTokenAndIsMaintained() {
        String fetcharticleUrl = null;
        FetchArticleBO fetchArticleBO = null;
        String fetcharticleResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            fetcharticleUrl = url + "/ArticleService/fetcharticle";
            fetchArticleBO = new FetchArticleBO();
            FetchArticleBO.Cond cond = new FetchArticleBO.Cond();
            List<String> lab = new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
            lab.add("test|广告");
            List<String> mainType = new ArrayList<>();
            //mainType.add("");
            List<String> subType = new ArrayList<>();
            subType.add("test3");
            List<String> titles = new ArrayList<>();
            //titles.add("test文章测试2");
            cond.setLab(lab);
            cond.setMainType(mainType);
            cond.setSubType(subType);
            cond.setTitles(titles);
            cond.setBegin(0);
            cond.setCount(1);
            cond.setReSort(true);
            //永久token
            fetchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            fetchArticleBO.setUid(hs.get("uid"));
            fetchArticleBO.setAppid("1.00002");
            fetchArticleBO.setBmAppid("1.00002");
            fetchArticleBO.setCond(cond);
            fetchArticleBO.setCmd("clear");
            log.info("fetcharticleUrl 请求的参数=" + fetcharticleUrl);
            log.info("fetchArticleBO 请求的参数=" + JSON.toJSONString(fetchArticleBO));
            fetcharticleResult = HttpUtil.postGeneralUrl(fetcharticleUrl, "application/json", JSON.toJSONString(fetchArticleBO), "UTF-8");
            log.info("fetcharticleResult 返回结果=" + fetcharticleResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken获取fetchArticle，此时参数uid必填");
            recordhttp.setUrl(fetcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchArticleBO));
            recordhttp.setResponse(fetcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetcharticleResult.contains("articles"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(fetcharticleResult.contains("scores"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(fetcharticleResult.contains("nextPos"), true);
            Assert.assertEquals(fetcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetcharticleResult.contains("info"), true);
            Assert.assertEquals(fetcharticleResult.contains("labList"), true);
            Assert.assertEquals(fetcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(fetcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(fetcharticleResult.contains("comment"), true);
            Assert.assertEquals(fetcharticleResult.contains("enable"), true);
            Assert.assertEquals(fetcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(fetcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(fetcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(fetcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(fetcharticleResult.contains("subType"), true);
            Assert.assertEquals(fetcharticleResult.contains("mainType"), true);
            Assert.assertEquals(fetcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(fetcharticleResult.contains("editor"), true);
            Assert.assertEquals(fetcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(fetcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(fetcharticleResult.contains("intro"), true);
            Assert.assertEquals(fetcharticleResult.contains("quality"), true);
            Assert.assertEquals(fetcharticleResult.contains("title"), true);
            Assert.assertEquals(fetcharticleResult.contains("strId"), true);
            Assert.assertEquals(fetcharticleResult.contains("id"), true);
            Assert.assertTrue(JSON.parseObject(fetcharticleResult).getJSONArray("articles").size()>0);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        try{
            applicationServiceManaged.restartArticlePid();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroyData() {

    }

}
