package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.galaxyeye.websocket.application.repository.ArticleRepository;
import com.galaxyeye.websocket.application.repository.AssLabRepository;
import com.galaxyeye.websocket.application.repository.AssSubTypeRepository;
import com.galaxyeye.websocket.application.repository.MainTypeRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.ArticlesBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.MatchArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.SaveArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.MatchArticleDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.SaveArticleDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.File.FileUtil;
import com.galaxyeye.websocket.tool.date.DateTool;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.sort.SortUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.apache.commons.math3.analysis.FunctionUtils.add;


@Slf4j
@Component
public class MatchArticleTest extends BaseTest {
    private SaveArticleBO saveArticleBO=null;

    @Autowired
    private UploadImageTest uploadImageTest;

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Autowired
    private SaveArticleTest saveArticleTest;

    @Autowired
    private DeleteArticleTest deleteArticleTest;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MainTypeRepository mainTypeRepository;

    @Autowired
    private AssLabRepository assLabRepository;

    @Autowired
    private AssSubTypeRepository assSubTypeRepository;

    /**
     * 通用匹配接口
     */
    @Test
    public void matchArticleTestByGernal(){
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        try{
             matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> addmainType=new ArrayList<>();
//        addmainType.add("测试yy");
            List<String> addsubType=new ArrayList<>();
//        addsubType.add("1");
            List<String> andLab=new ArrayList<>();
            andLab.add("1");
            List<String> mainType=new ArrayList<>();
            //mainType.add("测试yy");
            List<String> subType=new ArrayList<>();
            subType.add("1");
            List<String> titles=new ArrayList<>();
//        for (int i=20;i<34;i++){
//            titles.add("这是真的吗？我不信"+String.valueOf(i));
//        }
            //titles.add("test文章测试1");
            cond.setTitles(titles);
            cond.setAddmainType(addmainType);
            cond.setAddsubType(addsubType);
//        cond.setAndLab(andLab);
//        cond.setOrLab(new ArrayList<>());
            cond.setSubType(subType);
            cond.setMainType(mainType);
            cond.setMaxCount(5);
            cond.setMinCount(2);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("3.00011");
            matchArticleBO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            matchArticleBO.setSeq("abc");
            matchArticleBO.setToken("");
            matchArticleBO.setUid("");
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
             matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("新增一篇文章完成后，查询下该篇文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
        }
    }

    /**
     * cond的请求参数为"cond": {}，提示返回文章
     */
    @Test
    public void matchArticleTestByParameterCondValueIsEmpty(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        try{
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("3.00011");
            matchArticleBO.setAccessToken(bmAppids.get("3.00007"));
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            matchArticleBO.setSeq("abc");
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond的请求参数为\"cond\": {}，提示返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":0"), true);
        }
    }

    /**
     * 使用Uid与Token获取匹配文章
     */
    @Test
    public void matchArticleTestByUidAndToken(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTO=null;
        HashMap<String, String> hs=userLoginTest();
        String title=getTitle();
        try{
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleDTO=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> ids=new ArrayList<>();
            ids.add(String.valueOf(saveArticleDTO.getId()));
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setCond(cond);
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用Uid与Token获取匹配文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+hs.get("uid")), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("\"id\":"+saveArticleDTO.getId()), true);
            Assert.assertEquals(matcharticleResult.contains("\"strId\":\""+String.valueOf(saveArticleDTO.getId())+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"title\":\""+title+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 文章ids为空，返回空文章列表
     */
    @Test
    public void matchArticleTestByCondOnlyIdsIsEmpty(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTO=null;
        String title=getTitle();
        try{
            //参数true为上架文章
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleDTO=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> ids=new ArrayList<>();
//            ids.add(String.valueOf(saveArticleDTO.getId()));
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("文章ids为空，返回空文章列表");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":0"), true);
        }
    }

    /**
     * 文章ids数组为重复值，返回文章列表去重
     */
    @Test
    public void matchArticleTestByCondOnlyIdsIsRepeat(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTO=null;
        String title=getTitle();
        try{
            //参数true为上架文章
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleDTO=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> ids=new ArrayList<>();
            ids.add(String.valueOf(saveArticleDTO.getId()));
            ids.add(String.valueOf(saveArticleDTO.getId()));
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("文章ids数组为重复值，返回文章列表去重");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+0), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("\"id\":"+saveArticleDTO.getId()), true);
            Assert.assertEquals(matcharticleResult.contains("\"strId\":\""+String.valueOf(saveArticleDTO.getId())+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"title\":\""+title+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
        }
    }


    /**
     * 匹配上架上架的文章，不是上架文章则不返回
     * cond里单独ids查询，无其他字段返回ids的精确查找，
     * 不够count的数量文章，则补充规则按照文章质量优先，
     * 然后在随机返回; cond里单独ids查询，无其他字段返回ids的精确查找，
     * 超过maxCount的数量文章，则返回ids里文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyIdsByArticleIsPutOn(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTO=null;
        String title=getTitle();
        try{
            //参数true为上架文章
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleDTO=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> ids=new ArrayList<>();
            ids.add(String.valueOf(saveArticleDTO.getId()));
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("匹配上架上架的文章，不是上架文章则不返回");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+0), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("\"id\":"+saveArticleDTO.getId()), true);
            Assert.assertEquals(matcharticleResult.contains("\"strId\":\""+String.valueOf(saveArticleDTO.getId())+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"title\":\""+title+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }



    /**
     * 对下架的文章进行匹配，结果返回为空
     * cond里单独ids查询，无其他字段返回ids的精确查找，
     * 不够count的数量文章，则补充规则按照文章质量优先，
     * 然后在随机返回; cond里单独ids查询，无其他字段返回ids的精确查找，
     * 超过maxCount的数量文章，则返回ids里文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyIdsByArticleIsPutOff(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTO=null;
        String title=getTitle();
        try{
            //参数false为下架文章
            saveArticleBO.setEnable(false);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleDTO=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> ids=new ArrayList<>();
            ids.add(String.valueOf(saveArticleDTO.getId()));
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("对下架的文章进行匹配，结果返回为空");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":0"), true);
        }
    }

    /**
     * cond里单独ids查询，无其他字段返回ids的精确查找，超过maxCount的数量文章，则返回ids里文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyIdsByMoreThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> ids=null;
        try{
             ids=new ArrayList<>();
            saveArticleBO.setQuality("差");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            saveArticleBO.setQuality("精");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            saveArticleBO.setQuality("优");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            saveArticleBO.setQuality("良");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            saveArticleBO.setQuality("合");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(2);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独ids查询，无其他字段返回ids的精确查找，超过maxCount的数量文章，则返回ids里文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量大于MaxCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==ids.size());
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);

        }
    }


    /**
     * cond里单独ids查询，无其他字段返回ids的精确查找，小于maxCount的数量文章但是大于MinCount数量，则返回ids里文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyIdsByLessThanMaxCountMoreThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> ids=null;
        try{
             ids=new ArrayList<>();
            saveArticleBO.setQuality("差");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            saveArticleBO.setQuality("精");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独ids查询，无其他字段返回ids的精确查找，小于maxCount的数量文章但是大于MinCount数量，则返回ids里文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量在{MinCount，MaxCount}之间，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==ids.size());
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }




    /**
     * cond里单独ids查询，无其他字段返回ids的精确查找，小于MinCount的数量文章，
     * 但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量
     */
    @Test
    public void matchArticleTestByCondOnlyIdsByLessThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> ids=null;
        try{
             ids=new ArrayList<>();
            saveArticleBO.setQuality("差");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独ids查询，无其他字段返回ids的精确查找，超过maxCount的数量文章，则返回ids里文章数量,但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==ids.size());
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<2);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里单独ids查询，无其他字段返回ids的精确查找，小于MinCount的数量文章，
     * 但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量
     * ids+title的文章数量小于MaxCount且大于MinCount，则返回ids+title的文章数量
     */
    @Test
    public void matchArticleTestByCondIsIdsAndTitleByMoreThanMinCountAndLessThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        try{
            List<String> ids=new ArrayList<>();
            List<String> titles=new ArrayList<>();
            saveArticleBO.setQuality("差");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            for (int i = 0; i <2 ; i++) {
                String titleTmp=getTitle();
                saveArticleBO.setQuality("差");
                saveArticleBO.setTitle(titleTmp);
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                titles.add(titleTmp);
            }
            cond.setTitles(titles);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独ids查询，无其他字段返回ids的精确查找，超过maxCount的数量文章，则返回ids里文章数量,但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量，ids+title的文章数量小于MaxCount且大于MinCount，则返回ids+title的文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里单独ids查询，无其他字段返回ids的精确查找，小于MinCount的数量文章，
     * 但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量
     * ids+title的文章数量大于MaxCount，则返回ids+title的文章数量
     */
    @Test
    public void matchArticleTestByCondIsIdsAndTitleByMoreThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        try{
            List<String> ids=new ArrayList<>();
            List<String> titles=new ArrayList<>();
            saveArticleBO.setQuality("差");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            for (int i = 0; i <5 ; i++) {
                String titleTmp=getTitle();
                saveArticleBO.setQuality("差");
                saveArticleBO.setTitle(titleTmp);
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                titles.add(titleTmp);
            }
            cond.setTitles(titles);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独ids查询，无其他字段返回ids的精确查找，超过maxCount的数量文章，则返回ids里文章数量,但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量，ids+title的文章数量大于MaxCount，则返回ids+title的文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==6);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }



    /**
     * cond里单独ids查询，无其他字段返回ids的精确查找，小于MinCount的数量文章，
     * 但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量
     * ids+title的文章数量小于MinCount，则返回ids+title的文章数量
     */
    @Test
    public void matchArticleTestByCondIsIdsAndTitleByLessThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        try{
            List<String> ids=new ArrayList<>();
            List<String> titles=new ArrayList<>();
            saveArticleBO.setQuality("差");
            saveArticleBO.setTitle(getTitle());
            ids.add(String.valueOf(saveArticleTest.SaveArticleTestByParameter(saveArticleBO).getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            for (int i = 0; i <1 ; i++) {
                String titleTmp=getTitle();
                saveArticleBO.setQuality("差");
                saveArticleBO.setTitle(titleTmp);
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                titles.add(titleTmp);
            }
            cond.setTitles(titles);
            cond.setMinCount(3);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独ids查询，无其他字段返回ids的精确查找，超过maxCount的数量文章，则返回ids里文章数量,但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量，ids+title的文章数量小于MinCount，则返回ids+title的文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<3);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==2);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里单独title查询，无其他字段返回title的精确查找，超过maxCount的数量文章，则返回title里文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyTitleByMoreThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> titles=null;
        try{
            titles=new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                titles.add(getTitle());
                saveArticleBO.setTitle(titles.get(i));
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setTitles(titles);
            cond.setMinCount(1);
            cond.setMaxCount(2);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独title查询，无其他字段返回title的精确查找，超过maxCount的数量文章，则返回title里文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量大于MaxCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==titles.size());
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);

        }
    }


    /**
     * cond里单独title查询，无其他字段返回title的精确查找，小于maxCount的数量文章但是大于MinCount数量，则返回title里文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyTitleByLessThanMaxCountMoreThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> titles=null;
        try{
            titles=new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                titles.add(getTitle());
                saveArticleBO.setTitle(titles.get(i));
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setTitles(titles);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独title查询，无其他字段返回title的精确查找，小于maxCount的数量文章但是大于MinCount数量，则返回title里文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量在{MinCount，MaxCount}之间，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==titles.size());
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }




    /**
     * cond里单独title查询，无其他字段返回title的精确查找，小于MinCount的数量文章，
     * 但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量
     */
    @Test
    public void matchArticleTestByCondOnlyTitleByLessThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> titles=null;
        try{
            titles=new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                titles.add(getTitle());
                saveArticleBO.setTitle(titles.get(i));
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setTitles(titles);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独title查询，无其他字段返回title的精确查找，小于MinCount的数量文章，但是mainType、subType、andLab、orLab、addmainType、addsubType参数值均不填写，则不继续补充规则至最少MinCount数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==titles.size());
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<2);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里单独title查询，无其他字段返回title的精确查找，tilte参数数组里重复值,则返回的结果是去重的
     */
    @Test
    public void matchArticleTestByCondOnlyTitleIsRepeat(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> titles=null;
        String titleTmp=getTitle();
        try{
            titles=new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                titles.add(titleTmp);
                if(i==0){
                    saveArticleBO.setTitle(titleTmp);
                    saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                }
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setTitles(titles);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里单独title查询，无其他字段返回title的精确查找，小于maxCount的数量文章但是大于MinCount数量，则返回title里文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量在{MinCount，MaxCount}之间，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()!=titles.size());
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * title为空，返回空文章列表
     */
    @Test
    public void matchArticleTestByCondOnlyTitleIsEmpty(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> titles=null;
        try{
            titles=new ArrayList<>();
            for (int i = 0; i < 1; i++) {
//                titles.add(getTitle());
                saveArticleBO.setTitle(getTitle());
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setTitles(titles);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("title为空，返回空文章列表");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
        }
    }

    /**
     * 大类只填写一个值，小类为空，返回的文章规则是在该大类下随机选择所有小类文章,返回的文章的大类都是同一个值
     */
    @Test
    public void matchArticleTestByCondOnlyMainTypeAndParameterMainTypeValueIsOne(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,1));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章标签数量
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章小类数量
                saveArticleBO.setMainType(mainType.get(0));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(mainType);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("大类只填写一个值，小类为空，返回的文章规则是在该大类下随机选择所有小类文章,返回的文章的大类都是同一个值");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>1);

            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
                 ) {
                Assert.assertTrue(articlesBean.getMainType().equals(mainType.get(0)));
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * 大类只填写5个值，小类为空，返回的文章规则是在每个大类下随机选择一个小类文章组合返回
     */
    @Test
    public void matchArticleTestByCondOnlyMainTypeAndParameterMainTypeValueIsFour(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,5));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章标签数量
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章小类数量
                saveArticleBO.setMainType(mainType.get(i));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(mainType);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("大类只填写5个值，小类为空，返回的文章规则是在每个大类下随机选择一个小类文章组合返回");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==5);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>1);

            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            Set<String> set=new HashSet<>();
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                set.add(articlesBean.getMainType());
            }
            Assert.assertTrue(set.size()==ArticlesBeanList.size());
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }



    /**
     * 如果大类或小类为空数组或者不填，查找主类与小类属于精确查找,则不进行任何匹配查找
     */
    @Test
    public void matchArticleTestByCondOnlySubType(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 2; i++) {
                saveArticleBO.setMainType(mainType.get(i));
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(subType.get(i));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setSubType(subType);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("如果大类或小类为空数组或者不填，查找主类与小类属于精确查找,则不进行任何匹配查找");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==0);
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
        }
    }

    /**
     * 主类与子类均填写为空,不进行任何匹配文章查找，则返回空文章
     */
    @Test
    public void matchArticleTestByCondParameterMainTypeValueIsEmptyAndParameterSubTypeValueIsEmpty(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(new ArrayList<>());
            cond.setSubType(new ArrayList<>());
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("主类与子类均填写为空,不进行任何匹配文章查找，则返回空文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==0);
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
        }
    }

    /**
     * cond里只填写MainType与SubType查询文章数量小于MinCount，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndLessThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里只填写MainType与SubType查询文章数量小于MinCount，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<2);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里混合填写MainType与SubType查询文章数量大于MinCount且小于MaxCount，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndMoreThanMinCountAndLessThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 3; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里混合填写MainType与SubType查询文章数量大于MinCount且小于MaxCount，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<4);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里混合填写MainType与SubType查询文章数量大于MaxCount，查找主类与小类属于精确查找,返回max数量
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndMoreThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里混合填写MainType与SubType查询文章数量大于MaxCount，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * cond里混合填写MainType与SubType查询文章数量,存在符合文章数量且MinCount=0，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndLessThanMinCountAndLessThanMaxCountAndParameterMinCountValueIsZero(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);
            cond.setMinCount(0);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里混合填写MainType与SubType查询文章数量,存在符合文章数量且MinCount=0，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<4);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里混合填写MainType与SubType查询文章数量,存在符合文章数量且MinCount=0，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndLessThanMinCountAndMoreThanMaxCountAndParameterMinCountValueIsZero(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 3; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);
            cond.setMinCount(0);
            cond.setMaxCount(2);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里混合填写MainType与SubType查询文章数量,存在符合文章数量且MinCount=0，查找主类与小类属于精确查找,返回cond里混合填写MainType与SubType查询文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()>0);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * 附加大类只填写一个值，附加小类为空，返回的文章规则每篇文章都是同一个附加大类下
     */
    @Test
    public void matchArticleTestByCondOnlyAddmainTypeAndParameterAddmainTypeValueIsOne(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                condAddmainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddmainType(condAddmainType.subList(0,1));
            cond.setMinCount(4);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("附加大类只填写一个值，附加小类为空，返回的文章规则每篇文章都是同一个附加大类下");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<5);

            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                Assert.assertTrue(articlesBean.getMainType().equals(mainType.get(0)));
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * 附加大类只填写4个值，附加小类为空，返回的文章有可能存在4个附加大类均不同文章或者部分相同附加大类文章或者部分不同附加大类文章
     */
    @Test
    public void matchArticleTestByCondOnlyAddmainTypeAndParameterAddmainTypeValueIsFour(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                condAddmainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddmainType(condAddmainType);
            cond.setMinCount(4);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("附加大类只填写4个值，附加小类为空，返回的文章有可能存在4个附加大类均不同文章或者部分相同附加大类文章或者部分不同附加大类文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });

            //文章主类都在附加主类里
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            Boolean condition=true;
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(!condAddmainType.contains(articlesBean.getMainType())){
                    condition=false;
                }
            }
            Assert.assertTrue(condition);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<5);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * cond里仅填写AddSubType查询文章，返回空文章数量
     */
    @Test
    public void matchArticleTestByCondOnlyAddSubType(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddsubType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                condAddsubType.add(selectSubType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddsubType(condAddsubType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里仅填写AddSubType查询文章，返回空文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==0);
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
        }
    }

    /**
     * 附加主类与附加子类均填写为空数组，不进行任何匹配文章查找，则返回空文章
     */
    @Test
    public void matchArticleTestByCondParameterAddMainTypeValueIsEmptyAndParameterAddSubTypeValueIsEmpty(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        List<String> condAddsubType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 3; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condMainType.add(selectmainType);
                condSubType.add(selectSubType);
                condAddmainType.add(selectmainType);
                condAddsubType.add(selectSubType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddsubType(new ArrayList<>());
            cond.setAddmainType(new ArrayList<>());
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("附加主类与附加子类均填写为空数组，不进行任何匹配文章查找，则返回空文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==0);
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
        }
    }


    /**
     * cond里只填写AddmainType与AddSubType查询文章，符合的文章数量大于min且min!=0,则返回min文章数量
     */
    @Test
    public void matchArticleTestByCondAddMainTypeAndAddSubTypeAndMinCountIsNotZeroAndMoreThanMinCountAndLessThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> subType=new ArrayList<>();

        List<String> condAddmainType=new ArrayList<>();
        List<String> condAddsubType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 3; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condAddmainType.add(selectmainType);
                condAddsubType.add(selectSubType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddsubType(condAddsubType);
            cond.setAddmainType(condAddmainType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里只填写AddmainType与AddSubType查询文章，符合的文章数量大于min,则返回min文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==2);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<=2);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里只填写AddmainType与AddSubType查询文章，符合的文章数量大于min且min!=0,则返回min文章数量
     */
    @Test
    public void matchArticleTestByCondAddMainTypeAndAddSubTypeAndMinCountIsNotZeroAndLessThanMinCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        List<String> condAddsubType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condMainType.add(selectmainType);
                condSubType.add(selectSubType);
                condAddmainType.add(selectmainType);
                condAddsubType.add(selectSubType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddsubType(condAddsubType);
            cond.setAddmainType(condAddmainType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里只填写AddmainType与AddSubType查询文章，符合的文章数量大于min,则返回min文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<=1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond里只填写AddmainType与AddSubType查询文章，符合的文章数量大于max且min!=0,则返回min文章数量
     */
    @Test
    public void matchArticleTestByCondAddMainTypeAndAddSubTypeAndMinCountIsNotZeroAndMoreThanMaxCount(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        List<String> condAddsubType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 4; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condMainType.add(selectmainType);
                condSubType.add(selectSubType);
                condAddmainType.add(selectmainType);
                condAddsubType.add(selectSubType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddsubType(condAddsubType);
            cond.setAddmainType(condAddmainType);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里只填写AddmainType与AddSubType查询文章，符合的文章数量大于min,则返回min文章数量");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<=1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * cond查询条件只填写附加主类与附加子类，存在符合文章数量且min等于0,则返回min=0即返回空文章
     */
    @Test
    public void matchArticleTestByCondAddMainTypeAndAddSubTypeAndLessThanMinCountAndParameterMinCountValueIsZero(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        List<String> condAddsubType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 4; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condMainType.add(selectmainType);
                condSubType.add(selectSubType);
                condAddmainType.add(selectmainType);
                condAddsubType.add(selectSubType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddsubType(condAddsubType);
            cond.setAddmainType(condAddmainType);
            cond.setMinCount(0);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond查询条件只填写附加主类与附加子类，存在符合文章数量且min等于0,则返回min=0即返回空文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==0);
            Assert.assertEquals(matcharticleResult.contains("\"articles\":[]"), true);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
        }
    }

    /**
     * cond里填写mainType与SubType及AddmainType与AddSubType查询文章，当mainType与SubType查询的文章数量不足MinCount，则根据AddmainType与AddSubType查询的文章继续补充MinCount或者最终仍无法满足MinCount则按实际匹配数量进行返回
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndAddmainTypeAndAddSubType(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condMainType=new ArrayList<>();
        List<String> condSubType=new ArrayList<>();


        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condMainType.add(selectmainType);
                condSubType.add(selectSubType);

                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }

            //添加附加文章,主类是母婴，子类是yy
            List<String> condAddmainType=new ArrayList<>();
            List<String> condAddsubType=new ArrayList<>();
            String mainTypeName="母婴";
            String MainTypeCode=getMainTypeCodeByMainTypeName(mainTypeName);
            condAddmainType.add(mainTypeName);
            condAddsubType.add("yy");
            for (int i = 0; i < 5; i++) {

                saveArticleBO.setAssLabs(getAssLabs(MainTypeCode).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(MainTypeCode).subList(0,2));
                saveArticleBO.setSubType("yy");
                saveArticleBO.setMainType(mainTypeName);
                saveArticleBO.setTitle(getTitle().replace("title","yy"));
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);

            cond.setAddmainType(condAddmainType);
            cond.setAddsubType(condAddsubType);
            cond.setMinCount(5);
            cond.setMaxCount(8);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
//            Thread.sleep(61000);//等待文章刷新
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("cond里填写mainType与SubType及AddmainType与AddSubType查询文章，当mainType与SubType查询的文章数量不足MinCount，则根据AddmainType与AddSubType查询的文章继续补充MinCount或者最终仍无法满足MinCount则按实际匹配数量进行返回");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==5);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<8);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("\"title\":\"title"), true);
            Assert.assertEquals(matcharticleResult.contains("\"title\":\"yy"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * 根据文章的更新时间，按照降序排序后显示匹配文章，参数SortByUpd=True，返回的文章是按照文章修改时间降序返回
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndParameterSortByUpdValueIsTrue(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();

        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            ArticleExample example = new ArticleExample();
            ArticleExample.Criteria cr = example.createCriteria();
            cr.andTitleLike("%title%");
            List<Article> updateList = articleRepository.selectByExample(example);
            log.info("修改文章更新时间前记录="+JSON.toJSONString(updateList));
            for (int i = 0; i < updateList.size(); i++) {
                Article updateRecord=new Article();
                BeanUtils.copyProperties(updateList.get(i),updateRecord);
                updateRecord.setUpdatedAt(DateTool.addMilliseconds(updateRecord.getUpdatedAt(),i*60000L));
                ArticleExample updateExample=new ArticleExample();
                ArticleExample.Criteria updateCr=updateExample.createCriteria();
                updateCr.andIdEqualTo(updateList.get(i).getId());
                articleRepository.updateByExampleSelective(updateRecord,updateExample);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(condMainType);
            cond.setSubType(condSubType);
            cond.setSortByUpd(true);
            cond.setMinCount(2);
            cond.setMaxCount(6);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据文章的更新时间，按照降序排序后显示匹配文章，参数SortByUpd=True，返回的文章是按照文章修改时间降序返回");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==5);
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            List<String> sortDate=new ArrayList<>();
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
                 ) {
                sortDate.add(articlesBean.getUpdatedAt());
            }
            //通过则文章修改时间降序返回否则非文章修改时间降序返回
            Assert.assertTrue(SortUtil.IsSortByDateStr(sortDate, sortDate.size()));

            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 根据文章的更新时间，按照降序排序后显示匹配文章,参数SortByUpd=False，返回的文章不是按照文章修改时间降序返回
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndParameterSortByUpdValueIsFalse(){
        Boolean stop=false;
        for (int k = 0; k < 5; k++) {
            log.info("===========k============="+k);
            initData();
            destroyData();
            String matcharticleUrl = null;
            MatchArticleBO matchArticleBO = null;
            String matcharticleResult = "";

            Map<String,List<String>> hs=getMainType();
            List<String> mainType=new ArrayList<>();
            mainType.addAll(hs.get("mainTypeName"));

            List<String> subType=new ArrayList<>();

            List<String> condSubType=new ArrayList<>();
            List<String> condMainType=new ArrayList<>();

            //小类用主类的名称随机替代
            subType.addAll(hs.get("mainTypeName"));
            try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
                for (int i = 0; i < 5; i++) {
                    String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                    String selectmainType=subType.get(i);
                    condSubType.add(selectSubType);
                    condMainType.add(selectmainType);
                    saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                    saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                    saveArticleBO.setSubType(selectSubType);
                    saveArticleBO.setMainType(selectmainType);
                    saveArticleBO.setTitle(getTitle());
                    saveArticleBO.setId("0");
                    saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                }
                ArticleExample example = new ArticleExample();
                ArticleExample.Criteria cr = example.createCriteria();
                cr.andTitleLike("%title%");
                List<Article> updateList = articleRepository.selectByExample(example);
                log.info("修改文章更新时间前记录="+JSON.toJSONString(updateList));
                for (int i = 0; i < updateList.size(); i++) {
                    Article updateRecord=new Article();
                    BeanUtils.copyProperties(updateList.get(i),updateRecord);
                    updateRecord.setUpdatedAt(DateTool.addMilliseconds(updateRecord.getUpdatedAt(),i*60000L));
                    ArticleExample updateExample=new ArticleExample();
                    ArticleExample.Criteria updateCr=updateExample.createCriteria();
                    updateCr.andIdEqualTo(updateList.get(i).getId());
                    articleRepository.updateByExampleSelective(updateRecord,updateExample);
                }
                applicationServiceManaged.restartArticlePid();
                matcharticleUrl=url+"/ArticleService/matcharticle";
                MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
                cond.setMainType(condMainType);
                cond.setSubType(condSubType);
                cond.setSortByUpd(false);
                cond.setMinCount(2);
                cond.setMaxCount(6);
                matchArticleBO=new MatchArticleBO();
                matchArticleBO.setAppid("1.00002");
                matchArticleBO.setToken(saveArticleBO.getToken());
                matchArticleBO.setUid(saveArticleBO.getUid());
                matchArticleBO.setCond(cond);
                log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
                log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
                matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
                log.info("matcharticleResult 返回结果=" + matcharticleResult);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("根据文章的更新时间，按照降序排序后显示匹配文章,参数SortByUpd=False，返回的文章不是按照文章修改时间降序返回");
                recordhttp.setUrl(matcharticleUrl);
                recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
                recordhttp.setResponse(matcharticleResult);
                initLog(recordhttp, new Object() {
                });
                //返回的文章数量小于MinCount，则认为成功
                Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==5);
                JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
                List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
                List<String> sortDate=new ArrayList<>();
                for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
                ) {
                    sortDate.add(articlesBean.getUpdatedAt());
                }
                //通过则文章修改时间降序返回否则非文章修改时间降序返回

                if(!SortUtil.IsSortByDateStr(sortDate, sortDate.size())){
                    stop=true;
                }
                Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
                Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
                Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
                Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
                Assert.assertEquals(matcharticleResult.contains("articles"), true);
                Assert.assertEquals(matcharticleResult.contains("editor"), true);
                Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
                Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
                Assert.assertEquals(matcharticleResult.contains("id"), true);
                Assert.assertEquals(matcharticleResult.contains("strId"), true);
                Assert.assertEquals(matcharticleResult.contains("title"), true);
                Assert.assertEquals(matcharticleResult.contains("quality"), true);
                Assert.assertEquals(matcharticleResult.contains("intro"), true);
                Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
                Assert.assertEquals(matcharticleResult.contains("mainType"), true);
                Assert.assertEquals(matcharticleResult.contains("subType"), true);
                Assert.assertEquals(matcharticleResult.contains("lab"), true);
                Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
                Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
                Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
                Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
                Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
                Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
                Assert.assertEquals(matcharticleResult.contains("enable"), true);
                Assert.assertEquals(matcharticleResult.contains("comment"), true);
                Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
                Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
                Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
                Assert.assertEquals(matcharticleResult.contains("labList"), true);
            }
            if(stop){
                break;
            }
        }

    }

    /**
     * 根据文章的更新时间，按照降序排序后显示匹配文章,参数SortByUpd不传，返回的文章不是按照文章修改时间降序返回
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndParameterSortByUpdIsNotExist(){
        Boolean stop=false;
        for (int k = 0; k < 5; k++) {
            log.info("===========k============="+k);
            initData();
            destroyData();
            String matcharticleUrl = null;
            MatchArticleBO matchArticleBO = null;
            String matcharticleResult = "";

            Map<String,List<String>> hs=getMainType();
            List<String> mainType=new ArrayList<>();
            mainType.addAll(hs.get("mainTypeName"));

            List<String> subType=new ArrayList<>();

            List<String> condSubType=new ArrayList<>();
            List<String> condMainType=new ArrayList<>();

            //小类用主类的名称随机替代
            subType.addAll(hs.get("mainTypeName"));
            try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
                for (int i = 0; i < 5; i++) {
                    String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                    String selectmainType=subType.get(i);
                    condSubType.add(selectSubType);
                    condMainType.add(selectmainType);
                    saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                    saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                    saveArticleBO.setSubType(selectSubType);
                    saveArticleBO.setMainType(selectmainType);
                    saveArticleBO.setTitle(getTitle());
                    saveArticleBO.setId("0");
                    saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                }
                ArticleExample example = new ArticleExample();
                ArticleExample.Criteria cr = example.createCriteria();
                cr.andTitleLike("%title%");
                List<Article> updateList = articleRepository.selectByExample(example);
                log.info("修改文章更新时间前记录="+JSON.toJSONString(updateList));
                for (int i = 0; i < updateList.size(); i++) {
                    Article updateRecord=new Article();
                    BeanUtils.copyProperties(updateList.get(i),updateRecord);
                    updateRecord.setUpdatedAt(DateTool.addMilliseconds(updateRecord.getUpdatedAt(),i*60000L));
                    ArticleExample updateExample=new ArticleExample();
                    ArticleExample.Criteria updateCr=updateExample.createCriteria();
                    updateCr.andIdEqualTo(updateList.get(i).getId());
                    articleRepository.updateByExampleSelective(updateRecord,updateExample);
                }
                applicationServiceManaged.restartArticlePid();
                matcharticleUrl=url+"/ArticleService/matcharticle";
                MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
                cond.setMainType(condMainType);
                cond.setSubType(condSubType);
                cond.setMinCount(2);
                cond.setMaxCount(6);
                matchArticleBO=new MatchArticleBO();
                matchArticleBO.setAppid("1.00002");
                matchArticleBO.setToken(saveArticleBO.getToken());
                matchArticleBO.setUid(saveArticleBO.getUid());
                matchArticleBO.setCond(cond);
                log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
                log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
                matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
                log.info("matcharticleResult 返回结果=" + matcharticleResult);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("根据文章的更新时间，按照降序排序后显示匹配文章,参数SortByUpd不传，返回的文章不是按照文章修改时间降序返回");
                recordhttp.setUrl(matcharticleUrl);
                recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
                recordhttp.setResponse(matcharticleResult);
                initLog(recordhttp, new Object() {
                });
                //返回的文章数量小于MinCount，则认为成功
                Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==5);
                JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
                List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
                List<String> sortDate=new ArrayList<>();
                for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
                ) {
                    sortDate.add(articlesBean.getUpdatedAt());
                }
                if(!SortUtil.IsSortByDateStr(sortDate, sortDate.size())){
                    stop=true;
                }
                Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
                Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
                Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
                Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
                Assert.assertEquals(matcharticleResult.contains("articles"), true);
                Assert.assertEquals(matcharticleResult.contains("editor"), true);
                Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
                Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
                Assert.assertEquals(matcharticleResult.contains("id"), true);
                Assert.assertEquals(matcharticleResult.contains("strId"), true);
                Assert.assertEquals(matcharticleResult.contains("title"), true);
                Assert.assertEquals(matcharticleResult.contains("quality"), true);
                Assert.assertEquals(matcharticleResult.contains("intro"), true);
                Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
                Assert.assertEquals(matcharticleResult.contains("mainType"), true);
                Assert.assertEquals(matcharticleResult.contains("subType"), true);
                Assert.assertEquals(matcharticleResult.contains("lab"), true);
                Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
                Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
                Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
                Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
                Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
                Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
                Assert.assertEquals(matcharticleResult.contains("enable"), true);
                Assert.assertEquals(matcharticleResult.contains("comment"), true);
                Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
                Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
                Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
                Assert.assertEquals(matcharticleResult.contains("labList"), true);
            }
            if(stop){
                break;
            }
        }
    }


    /**
     * 根据文章的更新时间，按照降序排序后显示匹配文章，参数SortByUpd=test，返回的文章不是按照文章修改时间降序返回
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndParameterSortByUpdValueIsTest(){
        Boolean stop=false;
        for (int k = 0; k < 5; k++) {
            log.info("===========k============="+k);
            initData();
            destroyData();
            String matcharticleUrl = null;
            MatchArticleBO matchArticleBO = null;
            String matcharticleResult = "";

            Map<String,List<String>> hs=getMainType();
            List<String> mainType=new ArrayList<>();
            mainType.addAll(hs.get("mainTypeName"));

            List<String> subType=new ArrayList<>();

            List<String> condSubType=new ArrayList<>();
            List<String> condMainType=new ArrayList<>();

            //小类用主类的名称随机替代
            subType.addAll(hs.get("mainTypeName"));
            try{
//            applicationServiceManaged.restartArticlePid();
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
                for (int i = 0; i < 5; i++) {
                    String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                    String selectmainType=subType.get(i);
                    condSubType.add(selectSubType);
                    condMainType.add(selectmainType);
                    saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                    saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                    saveArticleBO.setSubType(selectSubType);
                    saveArticleBO.setMainType(selectmainType);
                    saveArticleBO.setTitle(getTitle());
                    saveArticleBO.setId("0");
                    saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
                }
                ArticleExample example = new ArticleExample();
                ArticleExample.Criteria cr = example.createCriteria();
                cr.andTitleLike("%title%");
                List<Article> updateList = articleRepository.selectByExample(example);
                log.info("修改文章更新时间前记录="+JSON.toJSONString(updateList));
                for (int i = 0; i < updateList.size(); i++) {
                    Article updateRecord=new Article();
                    BeanUtils.copyProperties(updateList.get(i),updateRecord);
                    updateRecord.setUpdatedAt(DateTool.addMilliseconds(updateRecord.getUpdatedAt(),i*60000L));
                    ArticleExample updateExample=new ArticleExample();
                    ArticleExample.Criteria updateCr=updateExample.createCriteria();
                    updateCr.andIdEqualTo(updateList.get(i).getId());
                    articleRepository.updateByExampleSelective(updateRecord,updateExample);
                }
                applicationServiceManaged.restartArticlePid();
                matcharticleUrl=url+"/ArticleService/matcharticle";
                MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
                cond.setMainType(condMainType);
                cond.setSubType(condSubType);
                cond.setSortByUpd("test");
                cond.setMinCount(2);
                cond.setMaxCount(6);
                matchArticleBO=new MatchArticleBO();
                matchArticleBO.setAppid("1.00002");
                matchArticleBO.setToken(saveArticleBO.getToken());
                matchArticleBO.setUid(saveArticleBO.getUid());
                matchArticleBO.setCond(cond);
                log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
                log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
                matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
                log.info("matcharticleResult 返回结果=" + matcharticleResult);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                HttpLog recordhttp = new HttpLog();
                recordhttp.setCreateTime(new Date());
                recordhttp.setCaseDescribe("根据文章的更新时间，按照降序排序后显示匹配文章，参数SortByUpd=test，返回的文章不是按照文章修改时间降序返回");
                recordhttp.setUrl(matcharticleUrl);
                recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
                recordhttp.setResponse(matcharticleResult);
                initLog(recordhttp, new Object() {
                });
                //返回的文章数量小于MinCount，则认为成功
                Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==5);
                JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
                List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
                List<String> sortDate=new ArrayList<>();
                for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
                ) {
                    sortDate.add(articlesBean.getUpdatedAt());
                }
                //通过则文章修改时间降序返回否则非文章修改时间降序返回

                if(!SortUtil.IsSortByDateStr(sortDate, sortDate.size())){
                    stop=true;
                }
                Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
                Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
                Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
                Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
                Assert.assertEquals(matcharticleResult.contains("articles"), true);
                Assert.assertEquals(matcharticleResult.contains("editor"), true);
                Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
                Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
                Assert.assertEquals(matcharticleResult.contains("id"), true);
                Assert.assertEquals(matcharticleResult.contains("strId"), true);
                Assert.assertEquals(matcharticleResult.contains("title"), true);
                Assert.assertEquals(matcharticleResult.contains("quality"), true);
                Assert.assertEquals(matcharticleResult.contains("intro"), true);
                Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
                Assert.assertEquals(matcharticleResult.contains("mainType"), true);
                Assert.assertEquals(matcharticleResult.contains("subType"), true);
                Assert.assertEquals(matcharticleResult.contains("lab"), true);
                Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
                Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
                Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
                Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
                Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
                Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
                Assert.assertEquals(matcharticleResult.contains("enable"), true);
                Assert.assertEquals(matcharticleResult.contains("comment"), true);
                Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
                Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
                Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
                Assert.assertEquals(matcharticleResult.contains("labList"), true);
            }
            if(stop){
                break;
            }
        }
    }

    /**
     * 填写ids与tilte,填写主类与子类，添加附加主类与附加子类,返回文章
     */
    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubType(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();;

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();
        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleTest.SaveArticleTestByParameter(saveArticleBO);


            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);



            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte,填写主类与子类，添加附加主类与附加子类,返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 填写ids与tilte,填写主类与子类，添加附加主类与附加子类及Orlab，返回文章
     */
    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndOrlab() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> orLab =new ArrayList<>();
        //对应表article.labs
        orLab.add("04004435");

        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);


            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setOrLab(orLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte,填写主类与子类，添加附加主类与附加子类及Orlab，返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
                 ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     *  填写ids与tilte,填写主类与子类，添加附加主类与附加子类及IncludeOrSubType，返回文章
     */
    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndIncludeOrSubType() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> includeOrSubType =new ArrayList<>();


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
//        对应表article.sub_type
            includeOrSubType.add(selectSubType);

            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setIncludeOrSubType(includeOrSubType);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte,填写主类与子类，添加附加主类与附加子类及IncludeOrSubType，返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 填写ids与tilte,填写主类与子类，添加附加主类与附加子类及IncludeOrLab，返回文章
     */
    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndIncludeOrLab() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> IncludeOrLab =new ArrayList<>();

        IncludeOrLab.add("======");

        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setIncludeOrLab(IncludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte,填写主类与子类，添加附加主类与附加子类及IncludeOrLab，返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndExcludeOrSubType() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrSubType =new ArrayList<>();



        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ExcludeOrSubType.add(selectSubType);



            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrSubType(ExcludeOrSubType);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte,填写主类与子类，添加附加主类与附加子类及IncludeOrLab，返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndExcludeOrLab() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte,填写主类与子类，添加附加主类与附加子类及IncludeOrLab，返回文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 方法matcharticle配置在OpenMethod当中，不校验Token
     */
    @Test
    public void matchArticleTestByOpenMethodAndUid() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
//            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法matcharticle配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 方法matcharticle配置在OpenMethod当中，不校验AccessToken
     */
    @Test
    public void matchArticleTestByOpenMethodAndBmAppid() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
//            matchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            matchArticleBO.setBmAppid("1.00002");
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法matcharticle配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 方法matcharticle配置在authMethod当中，校验Token
     */
    @Test
    public void matchArticleTestByAuthMethodAndUid() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("100.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法matcharticle配置在authMethod当中，校验Token");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 方法matcharticle配置在authMethod当中，不校验Token
     */
    @Test
    public void matchArticleTestByAuthMethodAndUidAndNotExistParameterToken() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("100.00002");
//            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法matcharticle配置在authMethod当中，不校验Token");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法matcharticle配置在authMethod当中，校验AccessToken
     */
    @Test
    public void matchArticleTestByAuthMethodAndBmAppid() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("100.00002");
            matchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            matchArticleBO.setBmAppid("1.00002");
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法matcharticle配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
//            resultId.add(saveArticleDTOMainAndSub.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 方法matcharticle配置在authMethod当中，不校验AccessToken
     */
    @Test
    public void matchArticleTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> ExcludeOrLab =new ArrayList<>();
        ExcludeOrLab.add("03004434");


        try{
            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));

            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);




            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置ids
            cond.setIds(ids);
            //设置title
            cond.setTitles(titles);
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setExcludeOrLab(ExcludeOrLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("100.00002");
//            matchArticleBO.setAccessToken(bmAppids.get("1.00002"));
            matchArticleBO.setBmAppid("1.00002");
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法matcharticle配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":101"), true);

        }
    }

    /**
     *当该文章处于维护状态且文章处于上架状态，此时根据该文章id匹配文章
     */
    @Test
    public void matchArticleTestByCondOnlyIdsAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTO=null;
        String title=getTitle();
        try{
            //参数true为上架文章
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(title);
            saveArticleDTO=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            List<String> ids=new ArrayList<>();
            ids.add(String.valueOf(saveArticleDTO.getId()));
            cond.setIds(ids);
            cond.setMinCount(1);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setAccessToken(bmAppids.get("3.00007"));
            matchArticleBO.setBmAppid("3.00007");
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(saveArticleBO.getMainType(),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(saveArticleBO.getMainType(),0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("当该文章处于维护状态且文章处于上架状态，此时根据该文章id匹配文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+0), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("\"id\":"+saveArticleDTO.getId()), true);
            Assert.assertEquals(matcharticleResult.contains("\"strId\":\""+String.valueOf(saveArticleDTO.getId())+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"title\":\""+title+"\""), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     *当该文章处于维护状态且文章处于上架状态，此时根据该文章title匹配文章
     */
    @Test
    public void matchArticleTestByCondOnlyTitleAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        List<String> titles=null;
        try{
            saveArticleBO.setTitle(getTitle());
            saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            titles=new ArrayList<>();
            titles.add(saveArticleBO.getTitle());
            cond.setTitles(titles);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(saveArticleBO.getMainType(),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(saveArticleBO.getMainType(),0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("当该文章处于维护状态且文章处于上架状态，此时根据该文章title匹配文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("uid"), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
            List<Object> list=JsonPath.read(matcharticleResult,"$.articles[*]");
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(list.size()<2);
        }
    }

    /**
     * Cond条件包含MainType，当某个大类处于维护状态且文章处于上架状态，此时根据大类匹配文章
     */
    @Test
    public void matchArticleTestByCondOnlyMainTypeAndParameterMainTypeValueIsOneAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,1));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章标签数量
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章小类数量
                saveArticleBO.setMainType(mainType.get(0));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(mainType);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(saveArticleBO.getMainType(),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(saveArticleBO.getMainType(),0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Cond条件包含MainType，当某个大类处于维护状态且文章处于上架状态，此时根据大类匹配文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);

            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                //文章大类处于维护了，匹配返回的文章大类与请求参数的文章大类不是同一个
                Assert.assertTrue(!articlesBean.getMainType().equals(mainType.get(0)));
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     *id与cond组合查询，文章id不处于维护状态但某个文章大类处于维护状态（文章id与大类处于上架状态），请求参数包含多个大类但其中一个大类处于维护状态，此时返回的文章除id外的其他文章的大类是非维护大类
     */
    @Test
    public void matchArticleTestByCondContainMainTypeAndIdsAndParameterMainTypeValueIsOneAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,5));
        SaveArticleBO saveArticleBOIds=new SaveArticleBO();
        SaveArticleBO saveArticleBOMainType=new SaveArticleBO();
        BeanUtils.copyProperties(saveArticleBO,saveArticleBOIds);
        BeanUtils.copyProperties(saveArticleBO,saveArticleBOMainType);
        SaveArticleDTO saveArticleDTOIds=null;
        List<String> mainTypeResult=new ArrayList<>();
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                saveArticleBOMainType.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章标签数量
                saveArticleBOMainType.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章小类数量
                saveArticleBOMainType.setMainType(mainType.get(i));
                saveArticleBOMainType.setTitle(getTitle());
                saveArticleBOMainType.setId("0");
                mainTypeResult.add(saveArticleBOMainType.getMainType());
                saveArticleTest.SaveArticleTestByParameter(saveArticleBOMainType);
            }
            saveArticleBOIds.setEnable(true);
            saveArticleBOIds.setId("0");
            saveArticleBOIds.setTitle(getTitle());
            saveArticleBOIds.setMainType(mainTypeResult.get(1));
            saveArticleBOIds.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(1)).subList(0,2));
            saveArticleBOIds.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(1)).subList(0,2));
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBOIds);
            List<String> ids=new ArrayList<>();
            ids.add(String.valueOf(saveArticleDTOIds.getId()));
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setIds(ids);
            cond.setMainType(mainType);
            cond.setMinCount(2);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            //第0个大类处于维护状态
            modifyIsMaintainedByMainTypeName(mainTypeResult.get(0),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(mainTypeResult.get(0),0);
//            删除处于维护状态的大类
            mainTypeResult.remove(0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("id与cond组合查询，文章id不处于维护状态但某个文章大类处于维护状态（文章id与大类处于上架状态），请求参数包含多个大类但其中一个大类处于维护状态，此时返回的文章除id外的其他文章的大类是非维护大类");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(!articlesBean.getId().equals(saveArticleDTOIds.getId())){
                    Assert.assertTrue(mainTypeResult.contains(articlesBean.getMainType()));
                }
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     *title与cond组合查询，文章tilte不处于维护状态但某个文章大类处于维护状态（文章title与大类处于上架状态），请求参数包含多个大类但其中一个大类处于维护状态，此时返回的文章除title外的其他文章的大类是非维护大类
     */
    @Test
    public void matchArticleTestByCondContainMainTypeAndTitleAndParameterMainTypeValueIsOneAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,5));
        SaveArticleBO saveArticleBOIds=new SaveArticleBO();
        SaveArticleBO saveArticleBOMainType=new SaveArticleBO();
        BeanUtils.copyProperties(saveArticleBO,saveArticleBOIds);
        BeanUtils.copyProperties(saveArticleBO,saveArticleBOMainType);
        SaveArticleDTO saveArticleDTOIds=null;
        List<String> mainTypeResult=new ArrayList<>();
        String title=getTitle();
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                saveArticleBOMainType.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章标签数量
                saveArticleBOMainType.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章小类数量
                saveArticleBOMainType.setMainType(mainType.get(i));
                saveArticleBOMainType.setTitle(getTitle());
                saveArticleBOMainType.setId("0");
                mainTypeResult.add(saveArticleBOMainType.getMainType());
                saveArticleTest.SaveArticleTestByParameter(saveArticleBOMainType);
            }
            saveArticleBOIds.setEnable(true);
            saveArticleBOIds.setId("0");
            saveArticleBOIds.setTitle(title);
            saveArticleBOIds.setMainType(mainTypeResult.get(1));
            saveArticleBOIds.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(1)).subList(0,2));
            saveArticleBOIds.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(1)).subList(0,2));
            saveArticleTest.SaveArticleTestByParameter(saveArticleBOIds);
            List<String> titles=new ArrayList<>();
            titles.add(title);
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setTitles(titles);
            cond.setMainType(mainType);
            cond.setMinCount(2);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            //第0个大类处于维护状态
            modifyIsMaintainedByMainTypeName(mainTypeResult.get(0),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(mainTypeResult.get(0),0);
//            删除处于维护状态的大类
            mainTypeResult.remove(0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("id与cond组合查询，文章id不处于维护状态但某个文章大类处于维护状态（文章id与大类处于上架状态），请求参数包含多个大类但其中一个大类处于维护状态，此时返回的文章除id外的其他文章的大类是非维护大类");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==3);
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(!articlesBean.getTitle().equals(title)){
                    Assert.assertTrue(mainTypeResult.contains(articlesBean.getMainType()));
                }
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * Cond条件包含MainType，当所有大类处于维护状态且文章处于上架状态，此时返回配置里配置的维护文章,维护文章在文章库存在
     */
    @Test
    public void matchArticleTestByCondOnlyMainTypeAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,1));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章标签数量
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章小类数量
                saveArticleBO.setMainType(mainType.get(0));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(mainType);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(null,1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(null,0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Cond条件包含MainType，当所有大类处于维护状态且文章处于上架状态，此时返回配置里配置的维护文章,维护文章在文章库存在");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);

            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                //文章大类处于维护了，匹配返回的文章大类与请求参数的文章大类不是同一个
                Assert.assertTrue(!articlesBean.getMainType().equals(mainType.get(0)));
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);

        }
    }


    /**
     * Cond条件包含MainType与subType，当某个大类处于维护状态且文章处于上架状态，参数subType的值不归属于参数mainType值下，则返回维护文章
     */
    @Test
    public void matchArticleTestByCondOnlyMainTypeAndSubTypeAndIsMaintainedAndMainTypeNotMatchSubType(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,1));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 1; i++) {
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章标签数量
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));//控制文章小类数量
                saveArticleBO.setMainType(mainType.get(0));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(mainType);
            List<String> subType=new ArrayList<>();
            subType.add("孕早期");
            cond.setSubType(subType);
            cond.setMinCount(1);
            cond.setMaxCount(3);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(mainType.get(0),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(mainType.get(0),0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Cond条件包含MainType与subType，当某个大类处于维护状态且文章处于上架状态，参数subType的值不归属于参数mainType值下，则返回维护文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            Assert.assertTrue(ArticlesBeanList.size()==1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * Cond条件包含MainType，当某个大类处于维护状态且文章处于上架状态，其余大类不处于维护状态，返回的文章列表不包括维护的文章
     */
    @Test
    public void matchArticleTestByCondOnlyMainTypeAndParameterMainTypeValueIsMoreAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        List<String> mainTypeResult=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName").subList(0,5));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章标签数量
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));//控制文章小类数量
                saveArticleBO.setMainType(mainType.get(i));
                mainTypeResult.add(mainType.get(i));
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setMainType(mainType);
            cond.setMinCount(2);
            cond.setMaxCount(4);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(mainTypeResult.get(0),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(mainTypeResult.get(0),0);
            mainTypeResult.remove(0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Cond条件包含MainType，当某个大类处于维护状态且文章处于上架状态，其余大类不处于维护状态，返回的文章列表不包括维护的文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                //某个文章大类处于维护了，匹配返回的文章大类都不属于维护类文章
                Assert.assertTrue(mainTypeResult.contains(articlesBean.getMainType()));
            }
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * 附加大类只填写一个值且该值处于维护状态，附加小类为空，返回的文章为配置文件里的维护文章
     */
    @Test
    public void matchArticleTestByCondOnlyAddmainTypeAndParameterAddmainTypeValueIsOneAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                condAddmainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddmainType(condAddmainType.subList(0,1));
            cond.setMinCount(4);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            modifyIsMaintainedByMainTypeName(condAddmainType.subList(0,1).get(0),1);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(condAddmainType.subList(0,1).get(0),0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("附加大类只填写一个值且该值处于维护状态，附加小类为空，返回的文章为配置文件里的维护文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    /**
     * 附加大类为部分处于维护状态与部分不处于维护状态，附加小类为空，返回的文章均不是维护文章即可
     */
    @Test
    public void matchArticleTestByCondOnlyAddmainTypeAndParameterAddmainTypeValueIsFourAndIsMaintained(){
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";

        Map<String,List<String>> hs=getMainType();
        List<String> mainType=new ArrayList<>();
        mainType.addAll(hs.get("mainTypeName"));

        List<String> subType=new ArrayList<>();

        List<String> condSubType=new ArrayList<>();
        List<String> condMainType=new ArrayList<>();
        List<String> condAddmainType=new ArrayList<>();
        //小类用主类的名称随机替代
        subType.addAll(hs.get("mainTypeName"));
        try{
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            for (int i = 0; i < 5; i++) {
                String selectSubType=subType.get(i)+UUID.randomUUID().toString();
                String selectmainType=subType.get(i);
                condSubType.add(selectSubType);
                condMainType.add(selectmainType);
                condAddmainType.add(selectmainType);
                saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(i)).subList(0,2));
                saveArticleBO.setSubType(selectSubType);
                saveArticleBO.setMainType(selectmainType);
                saveArticleBO.setTitle(getTitle());
                saveArticleBO.setId("0");
                saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            }
            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            cond.setAddmainType(condAddmainType);
            cond.setMinCount(4);
            cond.setMaxCount(5);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            modifyIsMaintainedByMainTypeName(condAddmainType.get(0),1);
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(condAddmainType.get(0),0);
            condAddmainType.remove(0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("附加大类为部分处于维护状态与部分不处于维护状态，附加小类为空，返回的文章均不是维护文章即可");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });

            //文章主类都在附加主类里
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            Assert.assertTrue(ArticlesBeanList.size()==4);
            Boolean condition=true;
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(!condAddmainType.contains(articlesBean.getMainType())){
                    condition=false;
                }
            }
            Assert.assertTrue(condition);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()<5);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 填写主类与子类且主类处于维护状态，添加附加主类与附加子类及Orlab，返回配置文件里的维护文章
     */
    @Test
    public void matchArticleTestByCondMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndOrlabAndIsMaintained() {
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> orLab =new ArrayList<>();
        //对应表article.labs
        orLab.add("03004435");
        try{

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setOrLab(orLab);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            modifyIsMaintainedByMainTypeName(mainType_MainAndSub.get(0),1);
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(mainType_MainAndSub.get(0),0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写主类与子类且主类处于维护状态，添加附加主类与附加子类及Orlab，返回配置文件里的维护文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==1);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }

    /**
     * 填写ids与tilte且该文章的id与title这2篇文章处于维护状态,填写主类与子类，添加附加主类与附加子类及Orlab，返回文章包括ids文章与title文章及不维护的文章
     */
    @Test
    public void matchArticleTestByCondIdsAndTitleAndMainTypeAndSubTypeAndAddmainTypeAndAddSubTypeAndOrlabAndIsMaintained() {
        Boolean stop=false;
        initData();
        destroyData();
        String matcharticleUrl = null;
        MatchArticleBO matchArticleBO = null;
        String matcharticleResult = "";
        SaveArticleDTO saveArticleDTOIds=null;
        SaveArticleDTO saveArticleDTOTitle=null;
        SaveArticleDTO saveArticleDTOMainAndSub=null;
        List<String> ids=new ArrayList<>();
        List<String> titles=new ArrayList<>();
        SaveArticleDTO saveArticleDTOAddmainTypeAndAddsubType=null;
        Map<String,List<String>> hs=getMainType();
        List<String> mainType_MainAndSub=new ArrayList<>();
        List<String> SubType_MainAndSub=new ArrayList<>();

        List<String> subType_MainAndSub_tmp=new ArrayList<>();

        List<String> condAddmainType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> condAddsubType_AddmainTypeAndAddsubType=new ArrayList<>();

        List<String> orLab =new ArrayList<>();
        //对应表article.labs
        orLab.add("03004435");
        String MainTypeNameIsMaintained="";
        try{

            //根据ids查询一篇
            saveArticleBO.setEnable(true);
            saveArticleBO.setId("0");
            saveArticleBO.setTitle(getTitle());
            saveArticleDTOIds=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);
            ids.add(String.valueOf(saveArticleDTOIds.getId()));
            MainTypeNameIsMaintained=saveArticleBO.getMainType();


            //根据title查询一篇
            titles.add(getTitle());
            saveArticleBO.setTitle(titles.get(0));
            saveArticleDTOTitle=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            //根据一个主类与一个子类查询一篇
//            maintype与ass_lab需要对应
//            maintype与ass_sub_type需要对应
            //小类用主类的名称随机替代
            subType_MainAndSub_tmp.addAll(hs.get("mainTypeName"));
            String selectSubType=subType_MainAndSub_tmp.get(0)+UUID.randomUUID().toString();
            String selectmainType=subType_MainAndSub_tmp.get(0);
            SubType_MainAndSub.add(selectSubType);
            mainType_MainAndSub.add(selectmainType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType);
            saveArticleBO.setMainType(selectmainType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOMainAndSub=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);


            //根据一个附加主类与附加子类查询一篇文章
            List<String> subType_AddmainTypeAndAddsubType=new ArrayList<>();
            //小类用主类的名称随机替代
            subType_AddmainTypeAndAddsubType.addAll(hs.get("mainTypeName"));
            String selectSubType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0)+UUID.randomUUID().toString();
            String selectmainType_AddmainTypeAndAddsubType=subType_AddmainTypeAndAddsubType.get(0);
            condAddmainType_AddmainTypeAndAddsubType.add(selectmainType_AddmainTypeAndAddsubType);
            condAddsubType_AddmainTypeAndAddsubType.add(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setAssLabs(getAssLabs(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setAssSubType(getAssSubType(hs.get("mainTypeCode").get(0)).subList(0,2));
            saveArticleBO.setSubType(selectSubType_AddmainTypeAndAddsubType);
            saveArticleBO.setMainType(selectmainType_AddmainTypeAndAddsubType);
            saveArticleBO.setTitle(getTitle());
            saveArticleBO.setId("0");
            saveArticleDTOAddmainTypeAndAddsubType=saveArticleTest.SaveArticleTestByParameter(saveArticleBO);

            applicationServiceManaged.restartArticlePid();
            matcharticleUrl=url+"/ArticleService/matcharticle";
            MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
            //设置主类与子类
            cond.setMainType(mainType_MainAndSub);
            cond.setSubType(SubType_MainAndSub);
            //设置附加主类与附加子类
            cond.setAddmainType(condAddmainType_AddmainTypeAndAddsubType);
            cond.setAddsubType(condAddsubType_AddmainTypeAndAddsubType);
            cond.setOrLab(orLab);
            cond.setTitles(titles);
            cond.setIds(ids);
            cond.setMinCount(5);
            cond.setMaxCount(7);
            matchArticleBO=new MatchArticleBO();
            matchArticleBO.setAppid("1.00002");
            matchArticleBO.setToken(saveArticleBO.getToken());
            matchArticleBO.setUid(saveArticleBO.getUid());
            matchArticleBO.setCond(cond);
            log.info("matcharticleUrl 请求的参数=" + matcharticleUrl);
            log.info("matchArticleBO 请求的参数=" + JSON.toJSONString(matchArticleBO));
            modifyIsMaintainedByMainTypeName(MainTypeNameIsMaintained,1);
            matcharticleResult = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleBO),"UTF-8");
            log.info("matcharticleResult 返回结果=" + matcharticleResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            modifyIsMaintainedByMainTypeName(MainTypeNameIsMaintained,0);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("填写ids与tilte且该文章的id与title这2篇文章处于维护状态,填写主类与子类，添加附加主类与附加子类及Orlab，返回文章包括ids文章与title文章及不维护的文章");
            recordhttp.setUrl(matcharticleUrl);
            recordhttp.setRequest(JSON.toJSONString(matchArticleBO));
            recordhttp.setResponse(matcharticleResult);
            initLog(recordhttp, new Object() {
            });
            //返回的文章数量小于MinCount，则认为成功
            Assert.assertTrue(JSON.parseObject(matcharticleResult).getJSONArray("articles").size()==4);
            List<Long> resultId=new ArrayList<>();
            resultId.add(saveArticleDTOIds.getId());
            resultId.add(saveArticleDTOTitle.getId());
            resultId.add(saveArticleDTOMainAndSub.getId());
            resultId.add(saveArticleDTOAddmainTypeAndAddsubType.getId());
            JSONArray jSONArray=JSON.parseObject(matcharticleResult).getJSONArray("articles");
            List<MatchArticleDTO.ArticlesBean> ArticlesBeanList=jSONArray.toJavaList(MatchArticleDTO.ArticlesBean.class);
            for (MatchArticleDTO.ArticlesBean articlesBean:ArticlesBeanList
            ) {
                if(resultId.contains(articlesBean.getId())){
                    stop=true;
                }
            }
            Assert.assertTrue(stop);
            Assert.assertEquals(matcharticleResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(matcharticleResult.contains("\"result\":1"), true);
            Assert.assertEquals(matcharticleResult.contains("remoteHome"), true);
            Assert.assertEquals(matcharticleResult.contains("\"uid\":"+saveArticleBO.getUid()), true);
            Assert.assertEquals(matcharticleResult.contains("articles"), true);
            Assert.assertEquals(matcharticleResult.contains("editor"), true);
            Assert.assertEquals(matcharticleResult.contains("thumbnail"), true);
            Assert.assertEquals(matcharticleResult.contains("srcSite"), true);
            Assert.assertEquals(matcharticleResult.contains("id"), true);
            Assert.assertEquals(matcharticleResult.contains("strId"), true);
            Assert.assertEquals(matcharticleResult.contains("title"), true);
            Assert.assertEquals(matcharticleResult.contains("quality"), true);
            Assert.assertEquals(matcharticleResult.contains("intro"), true);
            Assert.assertEquals(matcharticleResult.contains("refUrl"), true);
            Assert.assertEquals(matcharticleResult.contains("mainType"), true);
            Assert.assertEquals(matcharticleResult.contains("subType"), true);
            Assert.assertEquals(matcharticleResult.contains("lab"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabs"), true);
            Assert.assertEquals(matcharticleResult.contains("assLabsName"), true);
            Assert.assertEquals(matcharticleResult.contains("assSubType"), true);
            Assert.assertEquals(matcharticleResult.contains("infoStr"), true);
            Assert.assertEquals(matcharticleResult.contains("createdAt"), true);
            Assert.assertEquals(matcharticleResult.contains("updatedAt"), true);
            Assert.assertEquals(matcharticleResult.contains("enable"), true);
            Assert.assertEquals(matcharticleResult.contains("comment"), true);
            Assert.assertEquals(matcharticleResult.contains("bodyContent"), true);
            Assert.assertEquals(matcharticleResult.contains("mainTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("subTypeList"), true);
            Assert.assertEquals(matcharticleResult.contains("labList"), true);
        }
    }


    private String getTitle(){
        UUID uUID=UUID.randomUUID();
        return "title"+uUID.toString();
    }

    /**
     *
     * @return
     */
    private Map<String,List<String>> getMainType(){
        List<String> mainTypeNameList=new ArrayList<>();
        List<String> mainTypeCodeList=new ArrayList<>();
        Map<String,List<String>> hs=new HashMap<>();
        List<MainType> tmp=mainTypeRepository.selectAll();
        if(tmp.size()>0){
            for (MainType mainType:tmp
            ) {
                mainTypeNameList.add(mainType.getMainTypeName());
                mainTypeCodeList.add(mainType.getMainTypeCode());
            }
        }
        hs.put("mainTypeName",mainTypeNameList);
        hs.put("mainTypeCode",mainTypeCodeList);
        return hs;
    }

    private String getMainTypeCodeByMainTypeName(String MainTypeName){
        MainTypeExample example=new MainTypeExample();
        MainTypeExample.Criteria cr=example.createCriteria();
        cr.andMainTypeNameEqualTo(MainTypeName);
        List<MainType> tmp=mainTypeRepository.selectByExample(example);
        if(tmp.size()>0){
            return tmp.get(0).getMainTypeCode();
        }
        return null;
    }

    private List<String> getAssLabs(String mainTypeCode){

        List<String> AssLabsList=new ArrayList<>();
        AssLabExample assLabExample=new AssLabExample();
        AssLabExample.Criteria assLabCr=assLabExample.createCriteria();
        assLabCr.andMainTypeCodeEqualTo(mainTypeCode);
        //查询标签未删除的，DeletedAt=null表示未删除，DeletedAt=Not null表示已删除
        assLabCr.andDeletedAtIsNull();
        List<AssLab> tmpList=assLabRepository.selectByExample(assLabExample);
        for (AssLab assLab:tmpList
             ) {
            AssLabsList.add(assLab.getAssLabCode());
        }
        return AssLabsList;
    }


    private List<String> getAssSubType(String mainTypeCode){
        AssSubTypeExample assSubTypeExample=new AssSubTypeExample();
        AssSubTypeExample.Criteria assSubTypeCr=assSubTypeExample.createCriteria();
        List<String> AssSubTypeList=new ArrayList<>();
        assSubTypeCr.andMainTypeCodeEqualTo(mainTypeCode);
        //查询未删除的子类，DeletedAt=null表示未删除，DeletedAt=Not null表示已删除
        assSubTypeCr.andDeletedAtIsNull();
        List<AssSubType> tmpList=assSubTypeRepository.selectByExample(assSubTypeExample);
        for (AssSubType assSubType:tmpList
        ) {
            AssSubTypeList.add(assSubType.getAssSubTypeCode());
        }
        return AssSubTypeList;
    }


    /**
     * 文章资源库匹配接口测试
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //测试环境
        String matcharticleUrl=url+"/ArticleService/matcharticle";
        //生产环境
//        String url="https://acc-proxy.galaxyeye-api.com/ArticleService/matcharticle";
        MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
        List<String> addmainType=new ArrayList<>();
//        addmainType.add("测试yy");
        List<String> addsubType=new ArrayList<>();
//        addsubType.add("1");
        List<String> andLab=new ArrayList<>();
        andLab.add("1");
        List<String> mainType=new ArrayList<>();
        //mainType.add("测试yy");
        List<String> subType=new ArrayList<>();
        subType.add("1");
        List<String> titles=new ArrayList<>();
//        for (int i=20;i<34;i++){
//            titles.add("这是真的吗？我不信"+String.valueOf(i));
//        }
        //titles.add("test文章测试1");
        cond.setTitles(titles);
        cond.setAddmainType(addmainType);
        cond.setAddsubType(addsubType);
//        cond.setAndLab(andLab);
//        cond.setOrLab(new ArrayList<>());
        cond.setSubType(subType);
        cond.setMainType(mainType);
        cond.setMaxCount(5);
        cond.setMinCount(2);
        MatchArticleBO matchArticleDTO=new MatchArticleBO();
        matchArticleDTO.setAppid("3.00011");
        matchArticleDTO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
        matchArticleDTO.setBmAppid("3.00007");
        matchArticleDTO.setCond(cond);
        matchArticleDTO.setSeq("abc");
        matchArticleDTO.setToken("");
        matchArticleDTO.setUid("");
        log.info("请求的参数="+JSON.toJSONString(matchArticleDTO));
        String result = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleDTO),"UTF-8");
        log.info("返回结果    get key=articles="+JSON.parseObject(result).getString("articles"));
        log.info("返回结果="+JSON.parseObject(result));
    }



    public String test2(Boolean status) throws Exception {
        //测试环境
        String matcharticleUrl=url+"/ArticleService/matcharticle";
        //生成环境
//        String url="https://acc-proxy.galaxyeye-api.com/ArticleService/matcharticle";
        MatchArticleBO.Cond cond=new MatchArticleBO.Cond();
        List<String> addmainType=new ArrayList<>();
//        addmainType.add("测试yy");
        List<String> addsubType=new ArrayList<>();
//        addsubType.add("1");
        List<String> andLab=new ArrayList<>();
        andLab.add("1");
        List<String> mainType=new ArrayList<>();
        //===================
        //mainType.add("测试yy");
        mainType.add("疾病");
        List<String> subType=new ArrayList<>();
        subType.add("1");
        List<String> titles=new ArrayList<>();
//        for (int i=20;i<34;i++){
//            titles.add("这是真的吗？我不信"+String.valueOf(i));
//        }
        //=================
        //titles.add("test文章测试1");
        cond.setTitles(titles);
        cond.setAddmainType(addmainType);
        cond.setAddsubType(addsubType);
//        cond.setAndLab(andLab);
//        cond.setOrLab(new ArrayList<>());


        //cond.setSubType(subType);
        cond.setMainType(mainType);
        cond.setMaxCount(10);
        cond.setMinCount(1);
        MatchArticleBO matchArticleDTO=new MatchArticleBO();

        if(status){
            //true，执行账号1
            matchArticleDTO.setAppid("3.00011");
            matchArticleDTO.setAccessToken("72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9");
            matchArticleDTO.setBmAppid("3.00007");
            matchArticleDTO.setCond(cond);
            matchArticleDTO.setSeq("abc");
            matchArticleDTO.setToken("");
            matchArticleDTO.setUid("");
        }else {
            //false,执行账号2
            matchArticleDTO.setAppid("1.00003");
            matchArticleDTO.setAccessToken("wwceBMmruNYrGlvQTT9EH3hrKKPs9_lSs5v83owHxBOuTWaTHX2l8DyiYVs-duy13DjwoTUIBl1qAh4YGeGIFeizsiPsGKHW-Ah62a2X-Sg7MIpOrZ_lHqioi82h04qf");
            matchArticleDTO.setBmAppid("1.00003");
            matchArticleDTO.setCond(cond);
            matchArticleDTO.setSeq("abc");
            matchArticleDTO.setToken("");
            matchArticleDTO.setUid("");
        }

        log.info("请求的参数="+JSON.toJSONString(matchArticleDTO));
        String result = HttpUtil.postGeneralUrl(matcharticleUrl,"application/x-www-form-urlencoded", JSON.toJSONString(matchArticleDTO),"UTF-8");
        log.info("返回结果    get key=articles="+JSON.parseObject(result).getString("articles"));
        log.info("matcharticle 返回结果="+result);
        log.info("返回结果="+JSON.parseObject(result));
        return  result;
    }

    @Test
    public void test3() throws Exception {
        HashMap<String,String> hsArticles=new HashMap<String,String>();
        AtomicInteger total=new AtomicInteger(0);//单次文章总数量
        AtomicInteger x=new AtomicInteger(0);//单次文章精数量
        AtomicInteger sTotal=new AtomicInteger(0);//文章总数量
        AtomicInteger sX=new AtomicInteger(0);//文章精数量
        List<ArticlesBO.Articles> articles=null;
        ArrayList<String> probabilityList=new ArrayList<String>();
        ArrayList<String> sProbabilityList=new ArrayList<String>();
        boolean select=true;
        for (int i = 0; i <10 ; i++) {
            articles=JSON.parseObject(test2(select),ArticlesBO.class).getArticles();
            //select=false;
            for (ArticlesBO.Articles article:articles
                 ) {
                total.getAndIncrement();
                sTotal.getAndIncrement();
                HashMap<String,String> hs=new HashMap<String,String>();
                if(article.getQuality().equals("精")){
                    x.getAndIncrement();
                    sX.getAndIncrement();
                }
                hsArticles.put(String.valueOf(article.getId()),article.getQuality());
                hs.put(String.valueOf(article.getId()),article.getQuality());
                FileUtil.writeFileLines("F:/StatisticsTime/1.txt",JSON.toJSONString(hs)+"\n",true);
            }
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            probabilityList.add(nt.format((double) x.get()/total.get()));
            sProbabilityList.add(nt.format((double) sX.get()/sTotal.get()));
            x.set(0);
            total.set(0);
        }
        log.info("一次请求返回文章数量="+ articles.size());
        log.info("文章结果="+JSON.toJSONString(hsArticles));
        log.info("文章总数量="+sTotal);
        log.info("文章精数量="+sX);
        log.info("单次概率统计结果="+JSON.toJSONString(probabilityList));
        log.info("累加概率统计结果="+JSON.toJSONString(sProbabilityList));
    }



    /**
     * 1、修改所有大类全部处于维护或者非维护状态
     * 2、修改某一条大类处于维护或者非维护状态
     * isMaintained=1,文章大类处于维护状态
     * isMaintained=0,文章大类不处于维护状态
     * @param isMaintained
     */
    private void modifyIsMaintainedByMainTypeName(String mainTypeName,Integer isMaintained){
        try{
            if(mainTypeName==null || mainTypeName.isEmpty()){
                List<MainType> list=mainTypeRepository.selectAll();
                List<Long> ids=list.stream().map(mainType-> mainType.getId()).collect(Collectors.toList());
                MainType record=new MainType();
                record.setIsMaintained(isMaintained);
                MainTypeExample example=new MainTypeExample();
                MainTypeExample.Criteria cr=example.createCriteria();
                cr.andIdIn(ids);
                mainTypeRepository.updateByExampleSelective(record,example);
            }else if(!mainTypeName.isEmpty() || mainTypeName!=null){
                MainType record=new MainType();
                record.setIsMaintained(isMaintained);
                MainTypeExample example=new MainTypeExample();
                MainTypeExample.Criteria cr=example.createCriteria();
                cr.andMainTypeNameEqualTo(mainTypeName);
                mainTypeRepository.updateByExampleSelective(record,example);
            }
            applicationServiceManaged.restartArticlePid();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private void init(){
        modifyIsMaintainedByMainTypeName(null,0);
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }

    @Override
    public void initData() {
        init();
        try{
            HashMap<String, String> hs=userLoginTest();
            //获取图片上传接口
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            String url1=uploadImageTest.uploadImageGernal(uploadImageBO);
            saveArticleBO=new SaveArticleBO();
            //文章标签，对应表article_cat_ass_lab
            List<String> assLabs = new ArrayList<>();
            //03000569与03000570对应的主类是01000005与02000749与02000750对应的主类一致
//            ass_lab表存在主类与assLab对应关系
            assLabs.add("03000569");
            assLabs.add("03000570");
            saveArticleBO.setAssLabs(assLabs);
            //文章小类,对应表article_cat_ass_sub_type
            List<String> assSubType = new ArrayList<>();
//            ass_sub_type表存在主类与assLab对应关系
            assSubType.add("02000749");
            assSubType.add("02000750");
            saveArticleBO.setAssSubType(assSubType);
            //main_type_code=01000005，对应的名称是母婴，在表main_type
            saveArticleBO.setMainType("母婴");
            //0为新增文章，填写文章id则是更新
            saveArticleBO.setId("0");
            saveArticleBO.setTitle("title");
            saveArticleBO.setIntro("饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。");
            //tes小类，对应表article.sub_type
            saveArticleBO.setSubType("02003033|02003034");
            //tes标签，对应表article.labs
            saveArticleBO.setLabs("03004435|03004434");
            saveArticleBO.setQuality("优");
            saveArticleBO.setRefUrl("https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg");
            saveArticleBO.setSrcSite("飞华健康网");
            saveArticleBO.setEditor("小编1");
            saveArticleBO.setInfo("");
            saveArticleBO.setComment("备注1");
            saveArticleBO.setEnable(true);//文章的启用与不启用
            //设置图片地址
            saveArticleBO.setThumbnail(url1);
            StringBuilder body=new StringBuilder("<p><img style='width:100%;' src=\"").append(url1).append("\" alt=\"\"></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;'>摔一下就瘫了，你的颈椎“高危”了吗？</span></p><p><span style='color: #424242;font-size: 16px;line-height: 50rpx;font-weight:bold'>为自己，为父母，为朋友为自己，为父母，为朋友为自己，为父母，为朋友</span></p>");
            saveArticleBO.setBody(body.toString());
            saveArticleBO.setToken(hs.get("token"));
            saveArticleBO.setUid(hs.get("uid"));
            saveArticleBO.setAppid("1.00002");
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    @Override
    public void destroyData() {
        List<String> delete = new ArrayList() {
            {
                add("%title%");
                add("%yy%");
            }
        };
        for (String str : delete
        ) {
            ArticleExample example = new ArticleExample();
            ArticleExample.Criteria cr = example.createCriteria();
            cr.andTitleLike(str);
            List<Article> list = articleRepository.selectByExample(example);
            for (Article article : list
            ) {
                deleteArticleTest.deleteArticleTestByGernal(String.valueOf(article.getId()));
            }
        }
    }

}
