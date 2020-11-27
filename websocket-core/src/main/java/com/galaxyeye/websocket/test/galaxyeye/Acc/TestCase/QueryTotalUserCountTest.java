package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc
 * @Date Create on 16:30
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/12日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.QueryLoginUserCountbyDateBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.QueryTotalUserCountBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ArticleScoreService;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

@Slf4j
@Component
public class QueryTotalUserCountTest extends BaseTest {


    @Autowired
    private ArticleScoreService articleScoreService;

    /**
     * 查询用户总数
     * @throws Exception
     */
    @Test
    public void querytotalusercountTest() throws Exception {
        String articleUrl = url+"/AccService/querytotalusercount";
        QueryTotalUserCountBO queryTotalUserCountBO=new QueryTotalUserCountBO();
        //根据某个Appid下查询所有归属下BmAppid下的所有用户
        queryTotalUserCountBO.setAppid("3.00009");
        queryTotalUserCountBO.setBmAppid("4.00047");
        log.info("querytotalusercount 请求的参数=" + JSON.toJSONString(queryTotalUserCountBO));
        String resultArticle = HttpUtil.postGeneralUrl(articleUrl, "application/json", JSON.toJSONString(queryTotalUserCountBO), "UTF-8");
        log.info("querytotalusercount 返回结果=" + JSON.parseObject(resultArticle));
        //SELECT COUNT(1) from userdb.t_user where appid='1.00003' 统计结果
    }

    /**
     * 查询某天的登录用户数
     * @throws Exception
     */
    @Test
    public void queryloginusercountbydateTest() throws Exception {
        Long time=System.currentTimeMillis();
        System.out.println("time="+time);
        String articleUrl = url+"/AccService/queryloginusercountbydate";
        QueryLoginUserCountbyDateBO queryLoginUserCountbyDateBO=new QueryLoginUserCountbyDateBO();
        //根据某个Appid下查询所有归属下BmAppid下的所有用户
        queryLoginUserCountbyDateBO.setAppid("3.00009");
        queryLoginUserCountbyDateBO.setBmAppid("4.00047");
        queryLoginUserCountbyDateBO.setDate("2019-11-18");
        log.info("queryloginusercountbydate 请求的参数=" + JSON.toJSONString(queryLoginUserCountbyDateBO));
        String resultArticle = HttpUtil.postGeneralUrl(articleUrl, "application/json", JSON.toJSONString(queryLoginUserCountbyDateBO), "UTF-8");
        log.info("queryloginusercountbydate 返回结果=" + JSON.parseObject(resultArticle));
        //select * from userdb.t_user where appid='1.00002' and lastLoginTime>=1573488000 and lastLoginTime<1573574400
    }

    @Test
    public void clearRedis() throws Exception {
        articleScoreService.clearRedis();
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
