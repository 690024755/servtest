package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase;

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.shell.articleShell;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.LoginTest;
import com.galaxyeye.websocket.test.galaxyeye.Article.DTO.ArticleStatisticsDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Enum.OpEnum;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 */
@Slf4j
@Component
public class ArticleStatisticsTest extends BaseTest {


    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private SaveArticleTest saveArticleTest;

    @Autowired
    private LoginTest loginTest;

    @Autowired
    ApplicationServiceManaged applicationServiceManaged;

    @Test
    public void testRedis1() throws Exception {
        for (int i = 0; i < 1000; i++) {
            jedisTemplate.set(String.valueOf(i),String.valueOf(i));
            System.out.println("测试结果="+jedisTemplate.get(String.valueOf(i)));
        }
    }

    @Test
    public void testArticlePid() throws Exception {
        //重启ArticlePid ，执行衰落定时任务
        articleShell.restartArticlePid();
    }

    /**
     * 文章统计接口
     * @throws Exception
     */
    @Test
    public void ArticleStatisticsByParameter(String appid,String articleId,String uid,String token) throws Exception {
        applicationServiceManaged.restartArticlePid();
        String articleStatisticUrl=url+"/ArticleService/articlestatistics";
//        String appid="1.00002";
//        String uid="417941";
//        String articleId="1183932452452700160";
//        String token="4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9";
        ArticleScoreTest.articleAction(articleStatisticUrl,appid,uid,token,articleId,"read");
        ArticleScoreTest.articleAction(articleStatisticUrl,appid,uid,token,articleId,"push");
        ArticleScoreTest.articleAction(articleStatisticUrl,appid,uid,token,articleId,"share");
        ArticleScoreTest.articleAction(articleStatisticUrl,appid,uid,token,articleId,"collect");
        ArticleScoreTest.articleAction(articleStatisticUrl,appid,uid,token,articleId,"favor");
    }

    /**
     * 第1个用户推送文章,uid=417941
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //执行接口ArticleService/articlestatistics之前，先执行接口ArticleService/savearticle
        saveArticleTest.SaveArticleTestByGernal();//需要设置对应的文章id

        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("openid");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");
        userLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("bindNo1forOpenid");
        keys.setNickname("NBind_openid_0812_01");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq("abc");
        String result=loginTest.LoginByGernal(userLoginBO);
        log.info("登录请求的参数="+ JSON.toJSONString(userLoginBO));
        log.info("登录返回结果="+JSON.parseObject(result));
        String token=JSON.parseObject(result).getString("token");
        String uid=JSON.parseObject(result).getString("uid");

        //文章统计服务
        String articleStatisticUrl=url+"/ArticleService/articlestatistics";
        ArticleStatisticsDTO articleStatisticsDTO=new ArticleStatisticsDTO();
        articleStatisticsDTO.setOp(OpEnum.getName(2));
        articleStatisticsDTO.setId("596547176004325376");
        articleStatisticsDTO.setUid(uid);
        articleStatisticsDTO.setToken(token);
        articleStatisticsDTO.setBmAppid(userLoginBO.getAppid());
        articleStatisticsDTO.setAccessToken("");
        articleStatisticsDTO.setAppid(userLoginBO.getAppid());
        articleStatisticsDTO.setSeq(userLoginBO.getSeq());
        log.info("文章统计请求的参数="+ JSON.toJSONString(articleStatisticsDTO));
        String resultArticleStatistic = HttpUtil.postGeneralUrl(articleStatisticUrl,"application/json", JSON.toJSONString(articleStatisticsDTO),"UTF-8");
        log.info("文章统计返回结果="+JSON.parseObject(resultArticleStatistic));


    }


    /**
     * 第2个用户推送文章,uid=417906
     * @throws Exception
     */

    @Test
    public void test2() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("CACCU02");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");
        userLoginBO.setKeytp("cacc");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("bindNo1forOpenid");
        keys.setNickname("NBind_openid_0812_01");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq("abc");
        String result=loginTest.LoginByGernal(userLoginBO);
        log.info("登录请求的参数="+ JSON.toJSONString(userLoginBO));
        log.info("登录返回结果="+JSON.parseObject(result));
        String token=JSON.parseObject(result).getString("token");
        String uid=JSON.parseObject(result).getString("uid");

        //文章统计服务
        String articleStatisticUrl=url+"/ArticleService/articlestatistics";
        ArticleStatisticsDTO articleStatisticsDTO=new ArticleStatisticsDTO();
        articleStatisticsDTO.setOp(OpEnum.getName(1));
        articleStatisticsDTO.setId("596547176004325376");
        articleStatisticsDTO.setUid(uid);
        articleStatisticsDTO.setToken(token);
        articleStatisticsDTO.setBmAppid(userLoginBO.getAppid());
        articleStatisticsDTO.setAccessToken("");
        articleStatisticsDTO.setAppid(userLoginBO.getAppid());
        articleStatisticsDTO.setSeq(userLoginBO.getSeq());
        log.info("文章统计请求的参数="+ JSON.toJSONString(articleStatisticsDTO));
        String resultArticleStatistic = HttpUtil.postGeneralUrl(articleStatisticUrl,"application/json", JSON.toJSONString(articleStatisticsDTO),"UTF-8");
        log.info("文章统计返回结果="+JSON.parseObject(resultArticleStatistic));
    }

    /**
     * 第3个用户推送文章,uid=410993
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        //用户登录
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("scacctest1166");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00002");
        userLoginBO.setKeytp("cacc");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("bindNo1forOpenid");
        keys.setNickname("NBind_openid_0812_01");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        //userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq("abc");
        log.info("登录请求的参数="+ JSON.toJSONString(userLoginBO));
        String result =loginTest.LoginByGernal(userLoginBO);
        log.info("登录返回结果="+JSON.parseObject(result));
        String token=JSON.parseObject(result).getString("token");
        String uid=JSON.parseObject(result).getString("uid");
        //文章统计服务
        String articleStatisticUrl=url+"url/ArticleService/articlestatistics";
        ArticleStatisticsDTO articleStatisticsDTO=new ArticleStatisticsDTO();
        articleStatisticsDTO.setOp(OpEnum.getName(3));
        articleStatisticsDTO.setId("596547176004325376");
        articleStatisticsDTO.setUid(uid);
        articleStatisticsDTO.setToken(token);
        articleStatisticsDTO.setBmAppid(userLoginBO.getAppid());
        articleStatisticsDTO.setAccessToken("");
        articleStatisticsDTO.setAppid(userLoginBO.getAppid());
        articleStatisticsDTO.setSeq(userLoginBO.getSeq());
        log.info("文章统计请求的参数="+ JSON.toJSONString(articleStatisticsDTO));
        String resultArticleStatistic = HttpUtil.postGeneralUrl(articleStatisticUrl,"application/json", JSON.toJSONString(articleStatisticsDTO),"UTF-8");
        log.info("文章统计返回结果="+JSON.parseObject(resultArticleStatistic));
    }

    @Test
    public void ConcurrentTest() throws Exception{
        UserLoginBO userLoginBO=new UserLoginBO();
        userLoginBO.setUname("openid");
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("1.00003");
        userLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys=new UserLoginBO.Keys();
        keys.setInfo("bindNo1forOpenid");
        keys.setNickname("NBind_openid_0812_01");
        List<UserLoginBO.Keys> list=new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        userLoginBO.setSeq("abc");
        log.info("登录请求的参数="+ JSON.toJSONString(userLoginBO));
        String result = loginTest.LoginByGernal(userLoginBO);
        log.info("登录返回结果="+JSON.parseObject(result));
        String token=JSON.parseObject(result).getString("token");
        String uid=JSON.parseObject(result).getString("uid");

        UserLoginBO userLoginBO2=new UserLoginBO();
        userLoginBO2.setUname("CACCU02");
        userLoginBO2.setPasswd("");
        userLoginBO2.setAppid("1.00003");
        userLoginBO2.setKeytp("cacc");
        UserLoginBO.Keys keys2=new UserLoginBO.Keys();
        keys2.setInfo("bindNo1forOpenid");
        keys2.setNickname("NBind_openid_0812_01");
        List<UserLoginBO.Keys> list2=new ArrayList<>();
        list2.add(keys);
        userLoginBO2.setKeys(list2);
        userLoginBO2.setThirdlogin(false);
        userLoginBO2.setSeq("abc");
        log.info("登录请求的参数="+ JSON.toJSONString(userLoginBO2));
        String result2 = loginTest.Login(userLoginBO2);
        log.info("登录返回结果="+JSON.parseObject(result2));
        String token2=JSON.parseObject(result2).getString("token");
        String uid2=JSON.parseObject(result2).getString("uid");


        final CountDownLatch begin = new CountDownLatch(1);  //为0时开始执行
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
            final int NO =  i;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        begin.await();
                        //执行业务部分
                        System.out.println("=================="+NO);
                        ConcurrentTest(token,uid,userLoginBO.getAppid(),userLoginBO.getSeq());
                        ConcurrentTest(token2,uid2,userLoginBO2.getAppid(),userLoginBO2.getSeq());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.submit(runnable);
        }
        System.out.println("开始执行");
        begin.countDown();
        exec.awaitTermination(30000, TimeUnit.MILLISECONDS);
    }


    public void ConcurrentTest(String token,String uid,String appid,String seq) throws Exception {
        //文章统计服务
        String articleStatisticUrl=url+"/ArticleService/articlestatistics";
        ArticleStatisticsDTO articleStatisticsDTO=new ArticleStatisticsDTO();
        articleStatisticsDTO.setOp(OpEnum.getName(2));
        articleStatisticsDTO.setId("596546386200104960");
        articleStatisticsDTO.setUid(uid);
        articleStatisticsDTO.setToken(token);
        articleStatisticsDTO.setBmAppid(appid);
        articleStatisticsDTO.setAccessToken("");
        articleStatisticsDTO.setAppid(appid);
        articleStatisticsDTO.setSeq(seq);
        log.info("文章统计请求的参数="+ JSON.toJSONString(articleStatisticsDTO));
        String resultArticleStatistic = HttpUtil.postGeneralUrl(articleStatisticUrl,"application/json", JSON.toJSONString(articleStatisticsDTO),"UTF-8");
        log.info("文章统计返回结果="+JSON.parseObject(resultArticleStatistic));
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
