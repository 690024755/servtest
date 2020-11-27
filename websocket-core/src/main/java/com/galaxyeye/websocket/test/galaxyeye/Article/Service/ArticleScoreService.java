package com.galaxyeye.websocket.test.galaxyeye.Article.Service; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.ArticleScoreCal
 * @Date Create on 16:28
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/24日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.*;
import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import com.galaxyeye.websocket.service.params.TotalScoreDTO;
import com.galaxyeye.websocket.shell.ConnectLinuxCommand;
import com.galaxyeye.websocket.shell.RemoteConnect;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.FetchArticleBO;
import com.galaxyeye.websocket.tool.encr.MD5Utils;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Article Score cal service
 */
@Slf4j
@Component
public class ArticleScoreService {
//文章基础分
    /*
    单次阅读加分
     */
    private static final Integer PerReadScore=1;
    /*
    单次收藏加分
     */
    private static final Integer PerCollectScore=10;
    /*
   单次分享加分
    */
    private static final Integer PerShareScore=30;
    /*
   单次喜欢加分
    */
    private static final Integer PerFavorScore=5;
    /*
   单次不喜欢加分
    */
    private static final Integer PerDisFavorScore=-5;

    //tes的匹配分计算相关
    private static final Integer PerMatchSubTypeScore=50;

    private static final Integer PerMatchLabScore=20;

//用户分计算
    /**
     * 计算子类分，表计算相关user_lab_statistics
     */
    private static final Integer PerSubTypeScore=50;

    /**
     * 计算标签分，表计算相关user_lab_statistics
     */
    private static final Integer PerAssLabScore=5;

    /**
     * 用户推送一次减10分
     */
    private static final Integer PerUserPushScore=-10;

    /**
     * 用户阅读一次减400分
     */
    private static final Integer PerUserReadScore=-400;

    /**
     * 用户阅读2次及以上最多减800分
     */
    private static final Integer TopUserReadScore=-800;

    private static final Integer ArticleBaseQualitySCORE_Q1=200;
    private static final Integer ArticleBaseQualitySCORE_Q2=400;
    private static final Integer ArticleBaseQualitySCORE_Q3=600;
    private static final Integer ArticleBaseQualitySCORE_Q4=800;
    private static final Integer ArticleBaseQualitySCORE_Q5=1000;



    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleStatisticsRepository articleStatisticsRepository;

    @Autowired
    private ArticleUserStatisticsRepository articleUserStatisticsRepository;

    @Autowired
    private UserFavorStatisticsRepository userFavorStatisticsRepository;

    @Autowired
    private UserCollectStatisticsRepository userCollectStatisticsRepository;

    @Autowired
    private UserLabStatisticsRepository userLabStatisticsRepository;

    @Autowired
    private ArticleCatAssSubTypeRepository articleCatAssSubTypeRepository;

    @Autowired
    private ArticleCatAssLabRepository articleCatAssLabRepository;

    public List<Article> getByAllArticle() throws Exception {
        return articleRepository.getByAllArticle();
    }

    /**
     *
     * 删除表数据articledb.article_statistics、articledb.article_user_statistics、
     * articledb.user_collect_statistics
     * articledb.user_favor_statistics、articledb.user_lab_statistics
     * @throws Exception
     */
    public void deleteAll() throws Exception {

        log.info("articledb.article_statistics delete num="+articleStatisticsRepository.deleteAllArticleStatistics());
        log.info("articledb.articleUserStatisticsRepository delete num="+articleUserStatisticsRepository.deleteAllArticleUserStatistics());
        log.info("articledb.userFavorStatisticsRepository delete num="+userFavorStatisticsRepository.deleteAllUserFavorStatistics());
        log.info("articledb.userCollectStatisticsRepository delete num="+userCollectStatisticsRepository.deleteAllUserCollectStatistics());
        log.info("articledb.userLabStatisticsRepository delete num="+userLabStatisticsRepository.deleteAllUserLabStatistics());

    }

    /**
     * 重启文章服务应用
     * @throws IOException
     */
    public void restartArticlePid() throws IOException, InterruptedException {
        String str1=" ps -ef|grep 'article' | grep -v grep |awk '{print $2}'";
        String articlePid1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge",str1);
        log.info("articlePid1="+articlePid1);
        String result1_1= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","kill -9 "+articlePid1 );
        log.info("杀死进程 articlePid="+articlePid1+" ;执行结果="+result1_1);
        String articleName=ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","ls /usr/local/code/article/bin" ).replace("\n","");
        String result1_2= ConnectLinuxCommand.connectLinuxResult("172.16.0.25","root","sjxge","daemonize /usr/local/code/article/bin/".concat(articleName) );
        log.info("restart article service, 执行结果="+result1_2);

        Thread.sleep(5000);//等待程序启动完成后，在执行后续业务
    }

    /**
     * 清理redis缓存内容
     */
    public void clearRedis(){
        for (int i = 0; i < 3; i++) {
            jedisTemplate.set(String.valueOf(i),String.valueOf(i));
            log.info("检查设置成功,获取key="+i+",value="+jedisTemplate.get(String.valueOf(i)));
        }
        RemoteConnect remoteConnect=new RemoteConnect();
        remoteConnect.setUserName("root");
        remoteConnect.setPassword("sjxge");
        remoteConnect.setIp("172.16.0.25");
        Boolean login=ConnectLinuxCommand.login(remoteConnect);
        log.info("login="+login);
        if(login){
            ConnectLinuxCommand.execute("redis-cli -h 172.16.0.25 -c -p 7000 flushall && redis-cli -h 172.16.0.25 -c -p 7001 flushall && redis-cli -h 172.16.0.25 -c -p 7003 flushall");
        }
        for (int i = 0; i < 3; i++) {
            log.info("检查清空后,获取i="+jedisTemplate.get(String.valueOf(i)));
        }
    }

    private FetchArticleBO.Cond cond(FetchArticleBO.Cond condtmp) {
        FetchArticleBO.Cond cond=new FetchArticleBO.Cond();
        cond.setLab(condtmp.getLab());
        cond.setMainType(condtmp.getMainType());
        cond.setSubType(condtmp.getSubType());
        cond.setTitles(condtmp.getTitles());
        cond.setBegin(0);
        cond.setCount(0);
        cond.setReSort(false);
        return cond;
    }

    /**
     * 对参数进行mad5，然后返回结果
     * @param cond
     * @return cad6c734d764021d160da4b8d91c0fb9
     */
    public String md5Value(FetchArticleBO.Cond cond) {
        log.info("Md5之前的字符串="+ JSON.toJSONString(cond(cond)));
        String result= MD5Utils.getMD5(JSON.toJSONString(cond(cond)).toLowerCase(),"utf-8");
        log.info("Md5的值="+result);
        return result;
    }

    /**
     *
     * @param uid 用户的uid
     * @param lists articleId集合
     * @param cond tes小类与标签分数计算请求参数
     * @throws Exception
     */
    public HashMap<String,HashMap<String,Double>> queryRedisScore(String uid, List<String> lists, FetchArticleBO.Cond cond) throws Exception {
//        String Uid="417941";
//        List<String> articleIds=new ArrayList<>();
//        articleIds.add("596546386200104960");
//        articleIds.add("596547176004325376");
        String Uid=uid;
        StringBuffer sbMd5=new StringBuffer("as:fs:ms:");
        StringBuffer sbUid=new StringBuffer("as:fs:us:");
        StringBuffer sbBase=new StringBuffer("as:fs:bs:");
        sbMd5.append(Uid).append(":");
        sbMd5.append(md5Value(cond));
        sbUid.append(Uid);
        String keyMd5=sbMd5.toString();
        String keyUid=sbUid.toString();
        String keyBase=sbBase.toString();
        log.info("keyBase="+keyBase);
        log.info("keyUid="+keyUid);
        log.info("keyMd5="+keyMd5);
        //获取某篇文章基础分
        HashMap<String,Double> baseScoreMap=new HashMap<String,Double>(){};
        //获取某用户某篇文章分
        HashMap<String,Double> uidScoreMap=new HashMap<String,Double>(){};
        //获取某用户某篇文章md5分
        HashMap<String,Double> md5ScoreMap=new HashMap<String,Double>(){};
        for (String articleId:lists
        ) {
            Double baseScore=jedisTemplate.zScore(keyBase,articleId);
            Double uidScore=jedisTemplate.zScore(keyUid,articleId);
            Double md5Score=jedisTemplate.zScore(keyMd5,articleId);
            baseScoreMap.put(articleId,baseScore);
            uidScoreMap.put(articleId,uidScore);
            md5ScoreMap.put(articleId,md5Score);
        }
        log.info("zCard="+jedisTemplate.zCard(keyUid));
        log.info("baseScoreMap="+JSON.toJSONString(baseScoreMap));
        log.info("uidScoreMap="+JSON.toJSONString(uidScoreMap));
        log.info("md5ScoreMap="+JSON.toJSONString(md5ScoreMap));
        log.info("该用户下所有文章 md5ScoreMap="+JSON.toJSONString(jedisTemplate.zReverseRangeWithScores(keyMd5,0,-1)));

        HashMap<String,HashMap<String,HashMap<String,Double>>> result=new HashMap<String,HashMap<String,HashMap<String,Double>>>(){};
        HashMap<String,HashMap<String,Double>> resultMap=new HashMap<String,HashMap<String,Double>>(){};
        resultMap.put("baseScoreMap",baseScoreMap);
        resultMap.put("uidScoreMap",uidScoreMap);
        resultMap.put("md5ScoreMap",md5ScoreMap);
        result.put(Uid,resultMap);
        log.info("用户在redis里最终分数="+JSON.toJSONString(result));
        return  resultMap;
    }


    public Article queryArticle(Long articleId) {
        Article article=articleRepository.getByArticleId(articleId);
            return article;
    }

    public Map<String,List<String>> queryArticleCatAssSubTypeAndarticleCatAssLab(Long articleId) {
        List<ArticleCatAssSubType> articleCatAssSubTypeList=articleCatAssSubTypeRepository.getByArticleId(articleId);
        List<ArticleCatAssLab> articleCatAssLabList=articleCatAssLabRepository.getByArticleId(articleId);
        Map<String,List<String>> hs=new HashMap<>();
        List<String> articleCatAssSubTypeListTmp=new ArrayList<>();
        List<String> articleCatAssLabListTmp=new ArrayList<>();
        for (ArticleCatAssSubType articleCatAssSubType:articleCatAssSubTypeList
             ) {
            articleCatAssSubTypeListTmp.add(articleCatAssSubType.getAssSubTypeCode());
        }

        for (ArticleCatAssLab articleCatAssLab:articleCatAssLabList
        ) {
            articleCatAssLabListTmp.add(articleCatAssLab.getAssLabCode());
        }
        hs.put("articleCatAssSubTypeList",articleCatAssSubTypeListTmp);
        hs.put("articleCatAssLabList",articleCatAssLabListTmp);

        return hs;
    }

    //计算阅读分与推送分、分享分
    public Integer calReadScoreAndPushScoreAndShareScore(ArticleUserStatistics articleUserStatistics,Integer extraNum) {
        ArticleStatistics articleStatistics=articleStatisticsRepository.getByArticleId(articleUserStatistics.getArticleId());

        ArticleUserStatistics t=articleUserStatisticsRepository.getByUIdAndArticleId(articleUserStatistics.getUid(),articleUserStatistics.getArticleId());
        int readScore=0;
        int pushScore=0;
        int shareScore=0;
        if(t==null){
            return readScore+pushScore+shareScore;
        }
        //文章阅读的基础分，需要另外计算
        readScore+=PerReadScore*articleStatistics.getReadNum();
        if(t.getReadNum()<=2){
            readScore+= t.getReadNum()*PerUserReadScore;
        }else {
            readScore+= TopUserReadScore;
        }

        pushScore=(t.getPushNum()-extraNum)*PerUserPushScore;

        //文章分享的基础分，需要另外计算
        int ShareNum=articleUserStatisticsRepository.getByArticleId(articleUserStatistics.getArticleId());
        if(t.getShareNum()>0){
            //多次分享，只算一次
            shareScore=PerShareScore*ShareNum;
        }
        log.info(String.format(" ============================== readScore=%s,pushScore=%s,shareScore=%s",readScore,pushScore,shareScore));
        return readScore+pushScore+shareScore;
    }


    //计算喜欢分与取消喜欢分、计算不喜欢分与取消不喜欢分
    public Integer calFavorScoreAndDisfavorScore(UserFavorStatistics userFavorStatistics) {
        UserFavorStatistics t=userFavorStatisticsRepository.getByUIdAndArticleId(userFavorStatistics.getUid(),userFavorStatistics.getArticleId());
        int num=userFavorStatisticsRepository.getByArticleId(userFavorStatistics.getArticleId());
        int score=0;
        if(t==null){
            return score;
        }
        //喜欢的基础分，需要另外计算
        if(t.getFavor().equals(1)){
            score=num*PerFavorScore;
        }else if(t.getFavor().equals(-1)){
            score=num*PerDisFavorScore;
        }
        return score;
    }


    //计算收藏分与取消收藏分
    public Integer calCollectScoreAndUncollectScore(UserCollectStatistics userCollectStatistics) {
        int num=userCollectStatisticsRepository.getByArticleId(userCollectStatistics.getArticleId());
        int score=0;
        score=PerCollectScore*num;
        return score;
    }


    private List<String> deleteRepeatElement(List<String> list){
        Set<String> set = new HashSet<String>(list);
        List<String> newlist = new ArrayList<String>(set);
        return newlist;
    }

    /**
     *
     * @param totalScoreDTO 为内部文章标签与子类
     * @param lab 为外部传入的标签
     * @param subType 为外部传入的子类
     * @return
     */
    //计算用户的标签分与子类分、计算tes的小类分与标签分
    //tes的小类分数根据接口ArticleService/fetcharticle传的参数subType与lab在文章表的sub_type与labs进行计算
    public Integer calSubtypeScoreAndLabScore(TotalScoreDTO totalScoreDTO ,List<String> lab,List<String> subType) {
        UserLabStatistics userLabStatistics=totalScoreDTO.getUserLabStatistics();
        int userLabScore=0;
        int userSubTypeScore=0;
        String[] userLabS={};
        if(userLabStatistics.getLab()!=null && userLabStatistics.getLab().trim().length()>0){
             userLabS=userLabStatistics.getLab().split("\\|");
            //lab为外部传的标签，userLabS为文章的标签
            Integer userLabCount=userLabStatisticsRepository.getUserLabScoreByUIdAndLab(userLabStatistics.getUid(),deleteRepeatElement(Arrays.asList(userLabS)));
            userLabScore=userLabCount*PerAssLabScore;
        }
        String[] userSubTypes={};
        if(userLabStatistics.getSubType()!=null && userLabStatistics.getSubType().trim().length()>0){
            userSubTypes=userLabStatistics.getSubType().split("\\|");
            Integer userSubType=userLabStatisticsRepository.getUserSubTypeScoreByUIdAndSubType(userLabStatistics.getUid(),deleteRepeatElement(Arrays.asList(userSubTypes)));
            userSubTypeScore+=userSubType*PerSubTypeScore;
        }

        //计算tes的小类分与标签分
        int tesLabScore=0;
        int tesSubTypeScore=0;
        Long articleId=totalScoreDTO.getArticle().getId();
        Article article=articleRepository.getByArticleId(articleId);
        if(lab.size()>0){
            AtomicInteger tesLabCount=new AtomicInteger(0);
            List<String> tesLabList=deleteRepeatElement(Arrays.asList(article.getLabs().split("\\|")));
            for (String la:deleteRepeatElement(lab)
                 ) {
                if(tesLabList.contains(la)){
                    tesLabCount.incrementAndGet();
                }
            }
            tesLabScore=tesLabCount.get()*PerMatchLabScore;
        }
        if(subType.size()>0){
            AtomicInteger tesSubType=new AtomicInteger(0);
            List<String> tesSubTypeList=deleteRepeatElement(Arrays.asList(article.getSubType().split("\\|")));
            for (String su:deleteRepeatElement(subType)
            ) {
                if(tesSubTypeList.contains(su)){
                    tesSubType.incrementAndGet();
                }
            }
            tesSubTypeScore+=tesSubType.get()*PerMatchSubTypeScore;
        }
        log.info(String.format("用户小类与标签分及tes的小类与标签分 userLabScore=%s,userSubTypeScore=%s,tesLabScore=%s,tesSubTypeScore=%s",userLabScore,userSubTypeScore,tesLabScore,tesSubTypeScore));
        return userLabScore+userSubTypeScore+tesLabScore+tesSubTypeScore;
    }

    public Integer calBaseScore(Article articleTmp) {
        Article article=articleRepository.getByArticleId(articleTmp.getId());
        int score=0;
        switch (article.getQuality()) {
            case "精":
                score=ArticleBaseQualitySCORE_Q5;
                break;
            case "优":
                score=ArticleBaseQualitySCORE_Q4;
                break;
            case "良":
                score=ArticleBaseQualitySCORE_Q3;
                break;
            case "合":
                score=ArticleBaseQualitySCORE_Q2;
                break;
            case "差":
                score=ArticleBaseQualitySCORE_Q1;
                break;
        }
        return score;
    }

    public Integer calTotalScore(Long uid,Long articleId,List<String> lab,List<String> subType,Integer extraNum) {
        TotalScoreDTO totalScoreDTO=new TotalScoreDTO();
        Article article=new Article();
        article.setId(articleId);
        ArticleUserStatistics articleUserStatistics=new ArticleUserStatistics();
        articleUserStatistics.setArticleId(articleId);
        UserCollectStatistics userCollectStatistics =new UserCollectStatistics();
        userCollectStatistics.setArticleId(articleId);
        UserFavorStatistics userFavorStatistics =new UserFavorStatistics();
        userFavorStatistics.setArticleId(articleId);
        UserLabStatistics userLabStatistics=new UserLabStatistics();
        userLabStatistics.setUid(uid);

        //用户标签分查询CatAssSubTypeAndCatAssLab
        Map<String,List<String>> queryArticleCatAssSubTypeAndarticleCatAssLab= queryArticleCatAssSubTypeAndarticleCatAssLab(articleId);
        Set<Map.Entry<String, List<String>>> set=queryArticleCatAssSubTypeAndarticleCatAssLab.entrySet();
        Iterator<Map.Entry<String, List<String>>> it=set.iterator();
        StringBuilder SubTypeSb=new StringBuilder();
        StringBuilder LabSb=new StringBuilder();
        while (it.hasNext()){
            Map.Entry<String, List<String>> entry=it.next();
            if(entry.getKey().contains("articleCatAssSubTypeList")){
                List<String> list=entry.getValue();
                for (String l: list
                ) {
                    SubTypeSb.append(l).append("|");
                }
            }else if(entry.getKey().contains("articleCatAssLabList")){
                List<String> list=entry.getValue();
                for (String l: list
                ) {
                    LabSb.append(l).append("|");
                }
            }

        }

        userLabStatistics.setLab(LabSb.toString());
        userLabStatistics.setSubType(SubTypeSb.toString());
        userFavorStatistics.setUid(uid);
        userCollectStatistics.setUid(uid);
        articleUserStatistics.setUid(uid);
        totalScoreDTO.setArticle(article);
        totalScoreDTO.setArticleUserStatistics(articleUserStatistics);
        totalScoreDTO.setUserCollectStatistics(userCollectStatistics);
        totalScoreDTO.setUserFavorStatistics(userFavorStatistics);
        totalScoreDTO.setUserLabStatistics(userLabStatistics);

        int totalScore=0;
        int CollectScoreAndUncollectScore=calCollectScoreAndUncollectScore(totalScoreDTO.getUserCollectStatistics());
        int ReadScoreAndPushScoreAndShareScore=calReadScoreAndPushScoreAndShareScore(totalScoreDTO.getArticleUserStatistics(),extraNum);
        int FavorScoreAndDisfavorScore=calFavorScoreAndDisfavorScore(totalScoreDTO.getUserFavorStatistics());
        //用户子类与标签分计算
        int SubtypeScoreAndLabScore=calSubtypeScoreAndLabScore(totalScoreDTO,lab,subType);
        int BaseScore=calBaseScore((totalScoreDTO.getArticle()));
        log.info(String.format("CollectScoreAndUncollectScore=%s,ReadScoreAndPushScoreAndShareScore=%s," +
                "FavorScoreAndDisfavorScore=%s,SubtypeScoreAndLabScore=%s,BaseScore=%s",CollectScoreAndUncollectScore,ReadScoreAndPushScoreAndShareScore,
                FavorScoreAndDisfavorScore,SubtypeScoreAndLabScore,BaseScore));
        return totalScore+=BaseScore+CollectScoreAndUncollectScore+ReadScoreAndPushScoreAndShareScore+FavorScoreAndDisfavorScore+
                SubtypeScoreAndLabScore;
    }



}
