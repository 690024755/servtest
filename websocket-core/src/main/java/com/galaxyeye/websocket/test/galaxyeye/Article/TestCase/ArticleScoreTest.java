package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.ArticleScoreCal
 * @Date Create on 11:47
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/18日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.ArticleScoreBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.ArticleStatisticsDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.FetchArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Enum.OpEnum;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ArticleScoreService;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class ArticleScoreTest extends BaseTest {


    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private ArticleScoreService articleScoreService;

    @Autowired
    private SaveArticleTest saveArticleTest;

    /**
     * 文章id=596547176004325376、596546386200104960基础分初始化,检查服务初始化后，文章基础分变更是否正确
     * @throws Exception
     */
    @Test
    public void testRedisZset() throws Exception {
        jedisTemplate.set("1","1");
        jedisTemplate.zSet("as:fs:bs:","596547176004325376",10);
        jedisTemplate.zSet("as:fs:us:417941","596547176004325376",15);
        jedisTemplate.zSet("as:fs:ms:417941:cad6c734d764021d160da4b8d91c0fb9","596547176004325376",20);
        log.info("该用户下所有文章 md5ScoreMap="+JSON.toJSONString(jedisTemplate.zReverseRangeWithScores("as:fs:ms:417941:cad6c734d764021d160da4b8d91c0fb9",0,-1)));

    }


    public static void articleAction(String articlestatisticsUrl,String appid,String uid,String token,String articleId,String opName) throws Exception {
        //文章统计服务
        ArticleStatisticsDTO articleStatisticsDTO=new ArticleStatisticsDTO();
        articleStatisticsDTO.setOp(opName);
        articleStatisticsDTO.setId(articleId);
        articleStatisticsDTO.setUid(uid);
//        articleStatisticsDTO.setBmAppid(appid);
//        articleStatisticsDTO.setAccessToken(token);
        articleStatisticsDTO.setAppid(appid);
        articleStatisticsDTO.setToken(token);
        log.info("articlestatisticsUrl 请求的参数=" + articlestatisticsUrl);
        log.info("articleStatisticsDTO 请求的参数=" + JSON.toJSONString(articleStatisticsDTO));
        String articlestatisticsResult = HttpUtil.postGeneralUrl(articlestatisticsUrl,"application/json", JSON.toJSONString(articleStatisticsDTO),"UTF-8");
        log.info("articlestatisticsResult 返回结果=" + articlestatisticsResult);

    }


    @Test
    public void deleteAll() throws Exception {
        articleScoreService.clearRedis();
        articleScoreService.restartArticlePid();
    }

    /**
     * 清空redis的值与重启文章服务
     * @throws Exception
     */
    @Test
    public void clearRedis() throws Exception {
        articleScoreService.clearRedis();
        articleScoreService.restartArticlePid();
    }

    @Test
    public void init() throws Exception {
        articleScoreService.deleteAll();
        clearRedis();
    }

    @Test
    public synchronized void testScore(String uid,String articleId) throws Exception {
        String appid="1.00003";
//        String articleId="596547176004325376";
        String opName=OpEnum.getName(3);
//        String uid="417941";
        String token="9e08dcd7ce2c590cb8962832b6dbd5e929fd169d361d91bcd6612bb39d3b178b";

//        articleScoreService.deleteAll();
//        clearRedis();

        //执行接口ArticleService/articlestatistics之前，先执行接口ArticleService/savearticle
        saveArticleTest.SaveArticleTestByGernal();//需要设置对应的文章id

        //执行文章的行为如阅读、喜欢、收藏等操作
        String articleStatisticUrl=url+"/ArticleService/articlestatistics";
        articleAction(articleStatisticUrl,appid,uid,token,articleId,opName);
        articleAction(articleStatisticUrl,appid,uid,token,articleId,"read");
        articleAction(articleStatisticUrl,appid,uid,token,articleId,"push");
        articleAction(articleStatisticUrl,appid,uid,token,articleId,"share");
        articleAction(articleStatisticUrl,appid,uid,token,articleId,"collect");

        //文章分数计算
        String articleScore=url+"/ArticleService/fetcharticle";
        FetchArticleBO fetchArticleDTO=new FetchArticleBO();
        FetchArticleBO.Cond cond=new FetchArticleBO.Cond();
        List<String> lab=new ArrayList<>();
//        lab.add("test|广告|附加分:5|附加分:-1");特殊标签分数计算
        lab.add("test|广告");
        List<String> mainType=new ArrayList<>();
        //mainType.add("");
        List<String> subType=new ArrayList<>();
        subType.add("test3");
        List<String> titles=new ArrayList<>();
        //titles.add("test文章测试2");
        cond.setLab(lab);
        cond.setMainType(mainType);
        cond.setSubType(subType);
        cond.setTitles(titles);
        cond.setBegin(0);
        cond.setCount(1);
        cond.setReSort(true);

        //永久token
        fetchArticleDTO.setAccessToken(token);
        fetchArticleDTO.setUid(uid);
        fetchArticleDTO.setBmAppid(appid);
        fetchArticleDTO.setAppid(appid);
        fetchArticleDTO.setCond(cond);
        fetchArticleDTO.setCmd("clear");
        log.info("文章统计分数请求的参数="+ JSON.toJSONString(fetchArticleDTO));
        String resultArticleScore = HttpUtil.postGeneralUrl(articleScore,"application/json", JSON.toJSONString(fetchArticleDTO),"UTF-8");
        log.info("文章统计分数返回结果="+resultArticleScore);
        ArticleScoreBO articleScoreVO=JSON.parseObject(resultArticleScore, ArticleScoreBO.class);
        List<ArticleScoreBO.Articles> ArticlesList=articleScoreVO.getArticles();
        //设置下请求的文章id与返回的文章id一样，处理的分数计算
        AtomicInteger extraNum=new AtomicInteger(0);
        for (ArticleScoreBO.Articles article:ArticlesList
             ) {
            if(articleId.equals(article.getStrId())){
                extraNum.incrementAndGet();
            }
            continue;
        }
        //查询redis里存储的分数
        ArrayList<String> lists=new ArrayList<String>();
        lists.add(articleId);
        HashMap<String,Double> realScoreHashMap=articleScoreService.queryRedisScore(uid,lists,cond).get("md5ScoreMap");
        Double realScore=realScoreHashMap.get(articleId);

        //排序影响分数计算
        int exceptScore=articleScoreService.calTotalScore(Long.valueOf(uid),Long.valueOf(articleId), lab,subType,extraNum.get());
        log.info("预期的分数="+exceptScore);
        log.info("extraNum  extraNum  extraNum="+extraNum.get());
        //期望分加TES标签分及TES小类分
        Assert.assertEquals(realScore,exceptScore,0,"不相等");
    }

    /**
     * 测试文章分数入口方法
     * @throws Exception
     */
    @Test
    public void testMain() throws Exception {
//        articleScoreService.deleteAll();
//        clearRedis();
        List<String> uidList=new ArrayList<>();
        uidList.add("417941");
//        uidList.add("417906");
//        uidList.add("410993");
        List<Article> articleIdList=articleScoreService.getByAllArticle();
//        List<String> articleIdList=new ArrayList<>() ;
//        articleIdList.add("577741042049093632");
//        articleIdList.add("596547176004325376");

//            每个用户循环阅读所有文章
            for (int j=0;j<uidList.size();j++
                 ) {
//                for (int k = 0; k <articleIdList.size()-7000 ; k++) {
                for (int k = 0; k <5 ; k++) {//每个用户阅读文章次数
                    testScore(uidList.get(j),String.valueOf(articleIdList.get(k).getId()));
//                    testScore(uidList.get(j),articleIdList.get(k));
                }
            }

        //每篇文章被所有用户循环阅读
//        for (int k = 0; k <articleIdList.size()-7000; k++) {
//            for (int j=0;j<uidList.size();j++
//            ) {
//                testScore(uidList.get(j),String.valueOf(articleIdList.get(k).getId()));
//            }
//        }



    }

    @Test
    public void testMain1() throws Exception {
//        articleScoreService.deleteAll();
//        clearRedis();
        testScore("417941","1230335752282312704");
    }




    @Test
    public void Redis() throws Exception {
        for (int i=0;i<300;i++
        ) {
            jedisTemplate.set(String.valueOf(i), String.valueOf(i));
        }
        for (int i=0;i<300;i++
        ) {
            System.out.println(jedisTemplate.get(String.valueOf(i)));
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

    }
}


