package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:09
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 获取文章列表
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.*;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.ArticleListQueryBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteLabBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteSubTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.SaveArticleDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.ArticleListQueryDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SELECT * from articledb.article where id In (
 * SELECT t.article_id from (
 * SELECT article_id from articledb.article_cat_ass_lab where ass_lab_code in(03000569,03000570) GROUP BY article_id union all
 * SELECT article_id from articledb.article_cat_ass_sub_type where ass_sub_type_code in(02000749,02000750) GROUP BY article_id)t)
 * and  labs ='03004435|03004434' and editor LIKE '%1%' and main_type='母婴' and sub_type='02003033|02003034';
 * <p>
 * 编辑文章后，在查询
 * 不编辑文章，直接查询
 */
@Slf4j
@Component
public class ArticleListQueryTest extends BaseTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCatAssSubTypeRepository articleCatAssSubTypeRepository;

    @Autowired
    private ArticleCatAssLabRepository articleCatAssLabRepository;

    @Autowired
    private SaveArticleTest saveArticleTest;

    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private DeleteLabTest deleteLabTest;

    @Autowired
    private DeleteSubTypeTest deleteSubTypeTest;

    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;

    public String getSessionId() throws Exception {
        String sessionId = "";
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(1);
            articleListQueryBO.setPerPageCount(1000000);
            articleListQueryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章完成后，查询下该篇文章");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            ArticleListQueryDTO articleListQueryDTO = JSON.parseObject(articlelistqueryResult, ArticleListQueryDTO.class);
            sessionId = articleListQueryDTO.getSessionId();
        }
        return sessionId;
    }


    /**
     * 获取文章列表，不分页，显示所有全部符合条件的文章
     * @throws Exception
     */
    @Test
    public void articleListQueryTest() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取文章列表");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);
        }
    }

    /**
     * 删除文章的标签与子类后，根据文章id再次查询文章,则返回的文章小类与文章标签为空数组
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByDeleteLabAndDeleteSubTypeAndQueryByArticleID() throws Exception {
        HashMap<String, String> hs = userLoginTest();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        //新增文章后，删除文章的标签与文章的子类
        List<String> labCodeList = new ArrayList() {
            {
                add("03000569");
                add("03000570");
            }
        };
        AssLabExample assLabExample = new AssLabExample();
        assLabExample.createCriteria().andAssLabCodeIn(labCodeList);
        List<AssLab> assLabList = assLabRepository.selectByExample(assLabExample);
        for (AssLab assLab : assLabList
        ) {
            DeleteLabBO deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLab.getAssLabCode());
            deleteLabTest.deleteLabTestByGernal(deleteLabBO);
        }

        List<String> subCodeList = new ArrayList() {
            {
                add("02000749");
                add("02000750");
            }
        };
        AssSubTypeExample assSubTypeExample = new AssSubTypeExample();
        assSubTypeExample.createCriteria().andAssSubTypeCodeIn(subCodeList);
        List<AssSubType> assSubTypeList = assSubTypeRepository.selectByExample(assSubTypeExample);
        for (AssSubType assSubType : assSubTypeList
        ) {
            DeleteSubTypeBO deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubType.getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            deleteSubTypeTest.deleteSubTypeTestByGernal(deleteSubTypeBO);
        }
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";

//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
            articleListQueryBO.setId("613914239605673984");
//            List<String> mainType = new ArrayList<>();
//            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
//            List<String> assLabs = new ArrayList<>();
//            assLabs.add("03000569");
//            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
//            List<String> assSubType = new ArrayList<>();
//            assSubType.add("02000749");
//            assSubType.add("02000750");

//            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
//            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
//            articleListQueryBO.setLabs("03004435|03004434");
//            articleListQueryBO.setAssLabs(assLabs);
//            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
//            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 修改小类与标签的delete为null
            AssLab AssLab_update = new AssLab();
            AssLab_update.setDeletedAt(null);
            AssLabExample AssLabExample_update = new AssLabExample();
            AssLabExample_update.createCriteria().andAssLabCodeIn(labCodeList);
            assLabRepository.updateByExampleSelective(AssLab_update, AssLabExample_update);
            AssSubType AssSubType_update = new AssSubType();
            AssSubType_update.setDeletedAt(null);
            AssSubTypeExample AssSubTypeExample_update = new AssSubTypeExample();
            AssSubTypeExample_update.createCriteria().andAssSubTypeCodeIn(subCodeList);
            assSubTypeRepository.updateByExampleSelective(AssSubType_update, AssSubTypeExample_update);

            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除文章的标签与子类后，根据文章id再次查询文章,则返回的文章小类与文章标签为空数组");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            //删除文章的标签与小类后，该文章的assLabs、assLabsName、assSubType返回均为空
            List<Object> assLabsResult = JsonPath.read(articlelistqueryResult, "$.articles[*].assLabs");
            List<Object> assLabsNameResult = JsonPath.read(articlelistqueryResult, "$.articles[*].assLabsName");
            List<Object> assSubTypeResult = JsonPath.read(articlelistqueryResult, "$.articles[*].assSubType");
            Assert.assertTrue(((JSONArray) assLabsResult.get(0)).isEmpty());
            Assert.assertTrue(((JSONArray) assLabsNameResult.get(0)).isEmpty());
            Assert.assertTrue(((JSONArray) assSubTypeResult.get(0)).isEmpty());
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);

        }
    }

    /**
     * 删除文章的标签与子类后，根据文章的大类、小类、标签再次查询文章,则查询文章为空
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByDeleteLabAndDeleteSubTypeAndQueryByArticleAssLabAndArticleAssSubTypeAndArticleMainType() throws Exception {
        HashMap<String, String> hs = userLoginTest();
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        //新增文章后，删除文章的标签与文章的子类
        List<String> labCodeList = new ArrayList() {
            {
                add("03000569");
                add("03000570");
            }
        };
        AssLabExample assLabExample = new AssLabExample();
        assLabExample.createCriteria().andAssLabCodeIn(labCodeList);
        List<AssLab> assLabList = assLabRepository.selectByExample(assLabExample);
        for (AssLab assLab : assLabList
        ) {
            DeleteLabBO deleteLabBO = new DeleteLabBO();
            deleteLabBO.setToken(hs.get("token"));
            deleteLabBO.setUid(hs.get("uid"));
            deleteLabBO.setAppid("1.00002");
            deleteLabBO.setCode(assLab.getAssLabCode());
            deleteLabTest.deleteLabTestByGernal(deleteLabBO);
        }
        List<String> subCodeList = new ArrayList() {
            {
                add("02000749");
                add("02000750");
            }
        };
        AssSubTypeExample assSubTypeExample = new AssSubTypeExample();
        assSubTypeExample.createCriteria().andAssSubTypeCodeIn(subCodeList);
        List<AssSubType> assSubTypeList = assSubTypeRepository.selectByExample(assSubTypeExample);
        for (AssSubType assSubType : assSubTypeList
        ) {
            DeleteSubTypeBO deleteSubTypeBO = new DeleteSubTypeBO();
            deleteSubTypeBO.setCode(assSubType.getAssSubTypeCode());
            deleteSubTypeBO.setToken(hs.get("token"));
            deleteSubTypeBO.setUid(hs.get("uid"));
            deleteSubTypeBO.setAppid("1.00002");
            deleteSubTypeTest.deleteSubTypeTestByGernal(deleteSubTypeBO);
        }
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
            articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
            articleListQueryBO.setQuality("差");
            articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
            articleListQueryBO.setComment("1");
            articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 修改小类与标签的delete为null
            AssLab AssLab_update = new AssLab();
            AssLab_update.setDeletedAt(null);
            AssLabExample AssLabExample_update = new AssLabExample();
            AssLabExample_update.createCriteria().andAssLabCodeIn(labCodeList);
            assLabRepository.updateByExampleSelective(AssLab_update, AssLabExample_update);
            AssSubType AssSubType_update = new AssSubType();
            AssSubType_update.setDeletedAt(null);
            AssSubTypeExample AssSubTypeExample_update = new AssSubTypeExample();
            AssSubTypeExample_update.createCriteria().andAssSubTypeCodeIn(subCodeList);
            assSubTypeRepository.updateByExampleSelective(AssSubType_update, AssSubTypeExample_update);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除文章的标签与子类后，根据文章的大类、小类、标签再次查询文章,则查询文章为空");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"count\":0"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            //删除文章的标签与小类后，根据文章的assLabs、assLabsName、assSubType查询返回文章为空
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() == 0);
        }
    }


    /**
     * 方法articlelistquery配置在OpenMethod当中，不校验Token
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByOpenMethodAndUid() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setToken(hs.get("token"));
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquery配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);

        }
    }


    /**
     * 方法articlelistquery配置在OpenMethod当中，不校验AccessToken
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByOpenMethodAndBmAppid() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setAccessToken(bmAppids.get("1.00002"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquery配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);

        }
    }


    /**
     * 方法articlelistquery配置在authMethod当中，校验Token
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByAuthMethodAndUid() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setToken(hs.get("token"));
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setAppid("100.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquery配置在authMethod当中，校验Token");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);

        }
    }


    /**
     * 方法articlelistquery配置在authMethod当中，不校验Token
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
//            articleListQueryBO.setToken(hs.get("token"));
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setAppid("100.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquery配置在authMethod当中，不校验Token");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法articlelistquery配置在authMethod当中，校验AccessToken
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByAuthMethodAndBmAppid() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setAccessToken(bmAppids.get("1.00002"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("100.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquery配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);

        }
    }


    /**
     * 方法articlelistquery配置在authMethod当中，不校验AccessToken
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        saveArticleTest.SaveArticleTestByParameter("608898375646973952", "产后恢复1", true);
        saveArticleTest.SaveArticleTestByParameter("613914239605673984", "产后恢复", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
//            articleListQueryBO.setAccessToken(bmAppids.get("1.00002"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("100.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquery配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 获取文章列表，返回的文章按照文章id降序返回
     *
     * @throws Exception
     */
    @Test
    public void articleListQueryTestByIdDesc() throws Exception {
        String subType = "02003033|02003034";
        ArticleExample exampleDelete = new ArticleExample();
        ArticleExample.Criteria cr = exampleDelete.createCriteria();
        cr.andSubTypeEqualTo(subType);
        articleRepository.deleteByExample(exampleDelete);
        saveArticleTest.SaveArticleTestByParameter("0", "yy1", true);
        saveArticleTest.SaveArticleTestByParameter("0", "yy2", true);
        saveArticleTest.SaveArticleTestByParameter("0", "yy3", true);
        saveArticleTest.SaveArticleTestByParameter("0", "yy4", true);
        SaveArticleDTO savearticleResult = saveArticleTest.SaveArticleTestByParameter("0", "yy5", true);
        String articlelistqueryUrl = null;
        ArticleListQueryBO articleListQueryBO = null;
        String articlelistqueryResult = "";
        HashMap<String, String> hs = userLoginTest();
//        saveArticleTest.saveArticleTestByUpdateArticle();
        try {
            //修改文章的数据库，需要重启文章服务
            articlelistqueryUrl = url + "/ArticleService/articlelistquery";
            articleListQueryBO = new ArticleListQueryBO();
            //非必填选项
//        articleListQueryBO.setId("613914239605673984");
            List<String> mainType = new ArrayList<>();
            mainType.add("母婴");
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            assLabs.add("03000569");
            assLabs.add("03000570");
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
            assSubType.add("02000749");
            assSubType.add("02000750");

            articleListQueryBO.setMainType(mainType);
            //tes小类，对应表article.sub_type
            articleListQueryBO.setSubType(subType);
            //tes标签，对应表article.labs
            articleListQueryBO.setLabs("03004435|03004434");
            articleListQueryBO.setAssLabs(assLabs);
            articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
            articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
            articleListQueryBO.setPageIndex(0);
            articleListQueryBO.setPerPageCount(10000000);
            articleListQueryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBO.setUid(hs.get("uid"));
            articleListQueryBO.setBmAppid("1.00002");
            articleListQueryBO.setAppid("1.00002");
            log.info("articlelistqueryUrl 请求的参数=" + articlelistqueryUrl);
            log.info("articleListQueryBO 请求的参数=" + JSON.toJSONString(articleListQueryBO));
            articlelistqueryResult = HttpUtil.postGeneralUrl(articlelistqueryUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
            log.info("articlelistqueryResult 返回结果=" + articlelistqueryResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取文章列表，返回的文章按照文章id降序返回");
            recordhttp.setUrl(articlelistqueryUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBO));
            recordhttp.setResponse(articlelistqueryResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistqueryResult.contains("articles"), true);
            Assert.assertEquals(articlelistqueryResult.contains("id"), true);
            Assert.assertEquals(articlelistqueryResult.contains("strId"), true);
            Assert.assertEquals(articlelistqueryResult.contains("title"), true);
            Assert.assertEquals(articlelistqueryResult.contains("quality"), true);
            Assert.assertEquals(articlelistqueryResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistqueryResult.contains("intro"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistqueryResult.contains("editor"), true);
            Assert.assertEquals(articlelistqueryResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistqueryResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistqueryResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistqueryResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistqueryResult.contains("enable"), true);
            Assert.assertEquals(articlelistqueryResult.contains("comment"), true);
            Assert.assertEquals(articlelistqueryResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistqueryResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("labList"), true);
            Assert.assertEquals(articlelistqueryResult.contains("info"), true);
            Assert.assertEquals(articlelistqueryResult.contains("count"), true);
            Assert.assertEquals(articlelistqueryResult.contains("imgHost"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistqueryResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistqueryResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistqueryResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistqueryResult.contains("sessionId"), true);
            Assert.assertTrue(JSON.parseObject(articlelistqueryResult).getJSONArray("articles").size() > 0);
            //增加返回的文章是否逆序检查
            ArticleExample example = new ArticleExample();
            example.setOrderByClause("id desc");
            cr = example.createCriteria();
            cr.andSubTypeEqualTo(subType);
            List<Article> queryList = articleRepository.selectByExample(example);
            List<Long> queryListTmp = queryList.stream().map(x -> x.getId()).collect(Collectors.toList());
            List<Long> articlelistqueryResultList = JsonPath.read(articlelistqueryResult, "$.articles..id");
            if (queryList.size() == articlelistqueryResultList.size()) {
                for (int i = 0; i < queryListTmp.size(); i++) {
                    Assert.assertTrue(queryListTmp.get(i).equals(articlelistqueryResultList.get(i)));
                }
            } else {
                Assert.assertTrue(false, "按照id降序返回校验失败");
            }
            articleRepository.deleteByExample(exampleDelete);
        }
    }

    /**
     * 获取文章列表
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        //修改文章的数据库，需要重启文章服务
        applicationServiceManaged.restartArticlePid();
        String articleUrl = url + "/ArticleService/articlelistquery";
        ArticleListQueryBO articleListQueryBO = new ArticleListQueryBO();
        //非必填选项
//        articleListQueryBO.setId("613914239605673984");
        List<String> mainType = new ArrayList<>();
        mainType.add("mainTypeName1013");
        List<String> subType = new ArrayList<>();
        subType.add("02003033");
        subType.add("02003034");

        List<String> labs = new ArrayList<>();
        labs.add("03004435");
        labs.add("03004434");
        List<String> assLabs = new ArrayList<>();
        assLabs.add("03004434");
        assLabs.add("03004435");

        List<String> assSubType = new ArrayList<>();
        assSubType.add("02003033");
        assSubType.add("02003034");

        articleListQueryBO.setMainType(mainType);
        articleListQueryBO.setSubType("02003033|02003034");
        articleListQueryBO.setLabs("03004435|03004434");
        articleListQueryBO.setAssLabs(assLabs);
        articleListQueryBO.setAssSubType(assSubType);
//        articleListQueryBO.setQuality("差");
//        articleListQueryBO.setSrcSite("1");
        articleListQueryBO.setEditor("1");
//        articleListQueryBO.setComment("1");
//        articleListQueryBO.setEnable(true);
        articleListQueryBO.setPageIndex(5);
        articleListQueryBO.setPerPageCount(10);
        articleListQueryBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
        articleListQueryBO.setUid("417941");
        articleListQueryBO.setBmAppid("1.00002");
        articleListQueryBO.setAppid("1.00002");
//        articleListQueryBO.setSeq("abc");
        log.info("articlelistquery 请求的参数=" + JSON.toJSONString(articleListQueryBO));
        String resultArticle = HttpUtil.postGeneralUrl(articleUrl, "application/json", JSON.toJSONString(articleListQueryBO), "UTF-8");
        log.info("articlelistquery 返回结果=" + JSON.parseObject(resultArticle));

        //接口执行的sql
        /**
         * SELECT * from articledb.article where id In (
         * SELECT t.article_id from (
         * SELECT article_id from articledb.article_cat_ass_lab where ass_lab_code in(03004434,03004435) GROUP BY article_id union all
         * SELECT article_id from articledb.article_cat_ass_sub_type where ass_sub_type_code in(02003033,02003034) GROUP BY article_id)t)
         * and  labs ='03004435|03004434' and editor LIKE '%1%' and main_type='mainTypeName1013' and sub_type='02003033|02003034';
         */
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        Long articleId = 613914239605673984L;
        try {
            Article article = articleRepository.getByArticleId(articleId);
            List<ArticleCatAssSubType> articleCatAssSubTypeList = articleCatAssSubTypeRepository.getByArticleId(articleId);
            List<ArticleCatAssLab> articleCatAssLabList = articleCatAssLabRepository.getByArticleId(articleId);

            if (article == null || articleCatAssSubTypeList.size() <= 0 || articleCatAssLabList.size() <= 0) {
                ArticleExample articleExample = new ArticleExample();
                ArticleExample.Criteria articleExampleCr = articleExample.createCriteria();
                articleExampleCr.andIdEqualTo(articleId);
                articleRepository.deleteByExample(articleExample);

                ArticleCatAssSubTypeExample articleCatAssSubTypeExample = new ArticleCatAssSubTypeExample();
                articleCatAssSubTypeExample.createCriteria().andArticleIdEqualTo(articleId);
                articleCatAssSubTypeRepository.deleteByExample(articleCatAssSubTypeExample);

                ArticleCatAssLabExample articleCatAssLabExample = new ArticleCatAssLabExample();
                articleCatAssLabExample.createCriteria().andArticleIdEqualTo(articleId);
                articleCatAssLabRepository.deleteByExample(articleCatAssLabExample);


                Article articleRecord = new Article();
                articleRecord.setArticleType(null);
                articleRecord.setAssLabs("assLabName4434");
                articleRecord.setComment("新增");
                articleRecord.setCreatedAt(DateTool.toDate("2019-08-22 11:10:42", DateTool.DATE_FMT_5));
                articleRecord.setDeletedAt(null);
                articleRecord.setEditor("李梦颖1");
                articleRecord.setEnable(0);
                articleRecord.setHtmltext("article/200529/613914239605673984/613914239605673984.body");
                articleRecord.setId(articleId);
                articleRecord.setInfo("无");
                articleRecord.setIntro("10月怀胎，体质、生活习惯的不同，妈妈们的身体状况也让不尽相同。但造物主是公平的，在产后会给每个妈妈都提供一段心理、生理修复的最佳时期，让你重回少女时代。");
                articleRecord.setLabs("03004435|03004434");
                articleRecord.setMainType("母婴");
                articleRecord.setQuality("优");
                articleRecord.setRefUrl("https://baike.pcbaby.com.cn/qzbd/1136412.html");
                articleRecord.setSrcSite("PCbaby百科");
                articleRecord.setSubType("02003033|02003034");
                articleRecord.setThumbnail("http://47.96.10.106/img/article/200529/1266192839914557440.jpg");
                articleRecord.setTitle("产后恢复");
                articleRecord.setUpdatedAt(DateTool.toDate("2020-05-29 10:22:40", DateTool.DATE_FMT_5));
                articleRepository.insertSelective(articleRecord);

                ArticleCatAssSubType articleCatAssSubTypeRecord1 = new ArticleCatAssSubType();
                articleCatAssSubTypeRecord1.setArticleId(articleId);
                articleCatAssSubTypeRecord1.setAssSubTypeCode("02000749");
                articleCatAssSubTypeRecord1.setCreatedAt(new Date());
                articleCatAssSubTypeRecord1.setUpdatedAt(new Date());
                ArticleCatAssSubType articleCatAssSubTypeRecord2 = new ArticleCatAssSubType();
                articleCatAssSubTypeRecord2.setArticleId(articleId);
                articleCatAssSubTypeRecord2.setAssSubTypeCode("02000750");
                articleCatAssSubTypeRecord2.setCreatedAt(new Date());
                articleCatAssSubTypeRecord2.setUpdatedAt(new Date());
                articleCatAssSubTypeRepository.insertSelective(articleCatAssSubTypeRecord1);
                articleCatAssSubTypeRepository.insertSelective(articleCatAssSubTypeRecord2);

                ArticleCatAssLab articleCatAssLabRecord1 = new ArticleCatAssLab();
                articleCatAssLabRecord1.setArticleId(articleId);
                articleCatAssLabRecord1.setAssLabCode("03000569");
                articleCatAssLabRecord1.setCreatedAt(new Date());
                articleCatAssLabRecord1.setUpdatedAt(new Date());
                ArticleCatAssLab articleCatAssLabRecord2 = new ArticleCatAssLab();
                articleCatAssLabRecord2.setArticleId(articleId);
                articleCatAssLabRecord2.setAssLabCode("03000570");
                articleCatAssLabRecord2.setCreatedAt(new Date());
                articleCatAssLabRecord2.setUpdatedAt(new Date());
                articleCatAssLabRepository.insertSelective(articleCatAssLabRecord1);
                articleCatAssLabRepository.insertSelective(articleCatAssLabRecord2);

                //重启服务，缓存生效
                applicationServiceManaged.restartArticlePid();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public void destroyData() {

    }
}
