package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.*;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.ArticleListQueryBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.ArticleListQueryBySessionBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteLabBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.DeleteSubTypeBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
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


@Slf4j
@Component
public class ArticleListQueryBySessionTest extends BaseTest {

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    ArticleListQueryTest articleListQueryTest;

    @Autowired
    private ArticleCatAssSubTypeRepository articleCatAssSubTypeRepository;

    @Autowired
    private ArticleCatAssLabRepository articleCatAssLabRepository;

    @Autowired
    private DeleteLabTest deleteLabTest;

    @Autowired
    private DeleteSubTypeTest deleteSubTypeTest;

    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;

    /**
     * 根据session获取文章列表
     * @throws Exception
     */
    @Test
    public void articleListQueryBySessionTest() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
            articleListQueryBySessionBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBySessionBO.setUid("417941");
            articleListQueryBySessionBO.setBmAppid("1.00002");
            articleListQueryBySessionBO.setAppid("1.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章完成后，查询下该篇文章");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
        }
    }


    /**
     * 根据session获取文章列表,删除文章的标签与子类后，根据文章id再次查询文章,则返回的文章小类与文章标签为空数组
     * @throws Exception
     */
    @Test
    public void articleListQueryBySessionTestByDeleteLabAndDeleteSubTypeAndQueryByArticleID() throws Exception {
        HashMap<String, String> hs = userLoginTest();
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        List<String> labCodeList =null;
        List<String> subCodeList =null;
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(1);
            articleListQueryBySessionBO.setPerPageCount(100000);
            articleListQueryBySessionBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBySessionBO.setUid("417941");
            articleListQueryBySessionBO.setBmAppid("1.00002");
            articleListQueryBySessionBO.setAppid("1.00002");
            articleListQueryBySessionBO.setSeq("abc");
            //新增文章后，删除文章的标签与文章的子类
            labCodeList = new ArrayList() {
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
            subCodeList = new ArrayList() {
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
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
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
            recordhttp.setCaseDescribe("根据session获取文章列表,删除文章的标签与子类后，根据文章id再次查询文章,则返回的文章小类与文章标签为空数组");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
            //删除文章的标签与小类后，该文章的assLabs、assLabsName、assSubType返回均为空
            List<Object> assLabsResult = JsonPath.read(articlelistquerybysessionResult, "$.articles[*].assLabs");
            List<Object> assLabsNameResult = JsonPath.read(articlelistquerybysessionResult, "$.articles[*].assLabsName");
            List<Object> assSubTypeResult = JsonPath.read(articlelistquerybysessionResult, "$.articles[*].assSubType");
            Assert.assertTrue(((JSONArray) assLabsResult.get(0)).isEmpty());
            Assert.assertTrue(((JSONArray) assLabsNameResult.get(0)).isEmpty());
            Assert.assertTrue(((JSONArray) assSubTypeResult.get(0)).isEmpty());
            Assert.assertTrue(JSON.parseObject(articlelistquerybysessionResult).getJSONArray("articles").size() > 0);
        }
    }


    /**
     * 方法articlelistquerybysession配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void articleListQueryByOpenMethodAndUid() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
            articleListQueryBySessionBO.setToken(hs.get("token"));
            articleListQueryBySessionBO.setUid(hs.get("uid"));
            articleListQueryBySessionBO.setAppid("1.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquerybysession配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
        }
    }


    /**
     * 方法articlelistquerybysession配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void articleListQueryByOpenMethodAndBmAppid() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
            articleListQueryBySessionBO.setAccessToken(bmAppids.get("1.00002"));
            articleListQueryBySessionBO.setBmAppid("1.00002");
            articleListQueryBySessionBO.setAppid("1.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquerybysession配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
        }
    }


    /**
     * 方法articlelistquerybysession配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void articleListQueryByAuthMethodAndUid() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
            articleListQueryBySessionBO.setToken(hs.get("token"));
            articleListQueryBySessionBO.setUid(hs.get("uid"));
            articleListQueryBySessionBO.setAppid("100.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquerybysession配置在authMethod当中，校验Token");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
        }
    }


    /**
     * 方法articlelistquerybysession配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void articleListQueryByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
//            articleListQueryBySessionBO.setToken(hs.get("token"));
            articleListQueryBySessionBO.setUid(hs.get("uid"));
            articleListQueryBySessionBO.setAppid("100.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquerybysession配置在authMethod当中，不校验Token");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法articlelistquerybysession配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void articleListQueryByAuthMethodAndBmAppid() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
            articleListQueryBySessionBO.setAccessToken(bmAppids.get("1.00002"));
            articleListQueryBySessionBO.setBmAppid("1.00002");
            articleListQueryBySessionBO.setAppid("100.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquerybysession配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
        }
    }


    /**
     * 方法articlelistquerybysession配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void articleListQueryByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(15);
            articleListQueryBySessionBO.setPerPageCount(0);
//            articleListQueryBySessionBO.setAccessToken(bmAppids.get("1.00002"));
            articleListQueryBySessionBO.setBmAppid("1.00002");
            articleListQueryBySessionBO.setAppid("100.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法articlelistquerybysession配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 根据session获取文章列表,文章列表返回是id降序
     * @throws Exception
     */
    @Test
    public void articleListQueryBySessionTestByIdDesc() throws Exception {
        //改参数与articleListQueryTest.getSessionId()设置一致；
        String subType="02003033|02003034";
        String articlelistquerybysessionUrl = null;
        ArticleListQueryBySessionBO articleListQueryBySessionBO = null;
        String articlelistquerybysessionResult = "";
        try {
            articlelistquerybysessionUrl = url + "/ArticleService/articlelistquerybysession";
            articleListQueryBySessionBO = new ArticleListQueryBySessionBO();
            articleListQueryBySessionBO.setSessionId(articleListQueryTest.getSessionId());
            articleListQueryBySessionBO.setPageIndex(1);
            articleListQueryBySessionBO.setPerPageCount(100000);
            articleListQueryBySessionBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            articleListQueryBySessionBO.setUid("417941");
            articleListQueryBySessionBO.setBmAppid("1.00002");
            articleListQueryBySessionBO.setAppid("1.00002");
            articleListQueryBySessionBO.setSeq("abc");
            log.info("articlelistquerybysessionUrl 请求的参数=" + articlelistquerybysessionUrl);
            log.info("articleListQueryBySessionBO 请求的参数=" + JSON.toJSONString(articleListQueryBySessionBO));
            articlelistquerybysessionResult = HttpUtil.postGeneralUrl(articlelistquerybysessionUrl, "application/json", JSON.toJSONString(articleListQueryBySessionBO), "UTF-8");
            log.info("articlelistquerybysessionResult 返回结果=" + articlelistquerybysessionResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据session获取文章列表,文章列表返回是id降序");
            recordhttp.setUrl(articlelistquerybysessionUrl);
            recordhttp.setRequest(JSON.toJSONString(articleListQueryBySessionBO));
            recordhttp.setResponse(articlelistquerybysessionResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(articlelistquerybysessionResult.contains("articles"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("id"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("strId"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("title"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("quality"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("srcSite"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("intro"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("thumbnail"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("editor"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("refUrl"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabs"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assLabsName"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("assSubType"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("infoStr"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("createdAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("updatedAt"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("enable"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("comment"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("bodyContent"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("mainTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("subTypeList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("labList"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("info"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("count"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("pageIndex"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("perPageCount"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("\"result\":1"), true);
            Assert.assertEquals(articlelistquerybysessionResult.contains("session"), true);
            //判断返回的文章列表按照id降序返回
            ArticleExample example=new ArticleExample();
            example.setOrderByClause("id desc");
            example.createCriteria().andSubTypeEqualTo(subType);
            List<Article> queryList=articleRepository.selectByExample(example);
            List<Long> queryListTmp=queryList.stream().map(x->x.getId()).collect(Collectors.toList());
            List<Long> articlelistqueryResultList= JsonPath.read(articlelistquerybysessionResult,"$.articles..id");
            if(queryListTmp.size()==articlelistqueryResultList.size()){
                for (int i = 0; i < queryListTmp.size(); i++) {
                    Assert.assertTrue(queryListTmp.get(i).equals(articlelistqueryResultList.get(i)));
                }
            }else {
                Assert.assertTrue(false,"按照id降序返回校验失败");
            }
            ArticleExample exampleDelete=new ArticleExample();
            ArticleExample.Criteria cr=exampleDelete.createCriteria();
            cr.andSubTypeEqualTo(subType);
            articleRepository.deleteByExample(exampleDelete);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        Long articleId=613914239605673984L;
        try {
            Article article = articleRepository.getByArticleId(articleId);
            List<ArticleCatAssSubType> articleCatAssSubTypeList=articleCatAssSubTypeRepository.getByArticleId(articleId);
            List<ArticleCatAssLab> articleCatAssLabList=articleCatAssLabRepository.getByArticleId(articleId);

            if (article == null || articleCatAssSubTypeList.size()<=0 || articleCatAssLabList.size()<=0) {
                ArticleExample articleExample=new ArticleExample();
                ArticleExample.Criteria articleExampleCr=articleExample.createCriteria();
                articleExampleCr.andIdEqualTo(articleId);
                articleRepository.deleteByExample(articleExample);

                ArticleCatAssSubTypeExample articleCatAssSubTypeExample=new ArticleCatAssSubTypeExample();
                articleCatAssSubTypeExample.createCriteria().andArticleIdEqualTo(articleId);
                articleCatAssSubTypeRepository.deleteByExample(articleCatAssSubTypeExample);

                ArticleCatAssLabExample articleCatAssLabExample=new ArticleCatAssLabExample();
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

                ArticleCatAssSubType articleCatAssSubTypeRecord1=new ArticleCatAssSubType();
                articleCatAssSubTypeRecord1.setArticleId(articleId);
                articleCatAssSubTypeRecord1.setAssSubTypeCode("02000749");
                articleCatAssSubTypeRecord1.setCreatedAt(new Date());
                articleCatAssSubTypeRecord1.setUpdatedAt(new Date());
                ArticleCatAssSubType articleCatAssSubTypeRecord2=new ArticleCatAssSubType();
                articleCatAssSubTypeRecord2.setArticleId(articleId);
                articleCatAssSubTypeRecord2.setAssSubTypeCode("02000750");
                articleCatAssSubTypeRecord2.setCreatedAt(new Date());
                articleCatAssSubTypeRecord2.setUpdatedAt(new Date());
                articleCatAssSubTypeRepository.insertSelective(articleCatAssSubTypeRecord1);
                articleCatAssSubTypeRepository.insertSelective(articleCatAssSubTypeRecord2);

                ArticleCatAssLab articleCatAssLabRecord1=new ArticleCatAssLab();
                articleCatAssLabRecord1.setArticleId(articleId);
                articleCatAssLabRecord1.setAssLabCode("03000569");
                articleCatAssLabRecord1.setCreatedAt(new Date());
                articleCatAssLabRecord1.setUpdatedAt(new Date());
                ArticleCatAssLab articleCatAssLabRecord2=new ArticleCatAssLab();
                articleCatAssLabRecord2.setArticleId(articleId);
                articleCatAssLabRecord2.setAssLabCode("03000570");
                articleCatAssLabRecord2.setCreatedAt(new Date());
                articleCatAssLabRecord2.setUpdatedAt(new Date());
                articleCatAssLabRepository.insertSelective(articleCatAssLabRecord1);
                articleCatAssLabRepository.insertSelective(articleCatAssLabRecord2);

                //重启服务，缓存生效
                applicationServiceManaged.restartArticlePid();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Override
    public void destroyData() {
        List<String> labCodeList = new ArrayList() {
            {
                add("03000569");
                add("03000570");
            }
        };
        List<String> subCodeList = new ArrayList() {
            {
                add("02000749");
                add("02000750");
            }
        };
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
    }
}
