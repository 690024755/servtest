package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.UploadImageDTO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.GetUserCollectBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;


@Slf4j
@Component
public class GetUserCollectTest extends BaseTest {


    /**
     *根据BmAppid与AccessToken获取收藏的文章
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByBmAppidAndAccessToken() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setUid(hs.get("uid"));
            getUserCollectBO.setAccessToken(bmAppids.get("1.00002"));
            getUserCollectBO.setBmAppid("1.00002");
            getUserCollectBO.setAppid("1.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据BmAppid与AccessToken获取收藏的文章");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"result\":1"), true);
            Assert.assertEquals(getusercollectResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(getusercollectResult.contains("articles"), true);
            Assert.assertEquals(getusercollectResult.contains("strId"), true);
            Assert.assertEquals(getusercollectResult.contains("title"), true);
            Assert.assertEquals(getusercollectResult.contains("quality"), true);
            Assert.assertEquals(getusercollectResult.contains("srcSite"), true);
            Assert.assertEquals(getusercollectResult.contains("intro"), true);
            Assert.assertEquals(getusercollectResult.contains("thumbnail"), true);
            Assert.assertEquals(getusercollectResult.contains("editor"), true);
            Assert.assertEquals(getusercollectResult.contains("refUrl"), true);
            Assert.assertEquals(getusercollectResult.contains("mainType"), true);
            Assert.assertEquals(getusercollectResult.contains("subType"), true);
            Assert.assertEquals(getusercollectResult.contains("assLabs"), true);
            Assert.assertEquals(getusercollectResult.contains("assLabsName"), true);
            Assert.assertEquals(getusercollectResult.contains("infoStr"), true);
            Assert.assertEquals(getusercollectResult.contains("createdAt"), true);
            Assert.assertEquals(getusercollectResult.contains("updatedAt"), true);
            Assert.assertEquals(getusercollectResult.contains("enable"), true);
            Assert.assertEquals(getusercollectResult.contains("comment"), true);
            Assert.assertEquals(getusercollectResult.contains("bodyContent"), true);
            Assert.assertEquals(getusercollectResult.contains("mainTypeList"), true);
            Assert.assertEquals(getusercollectResult.contains("subTypeList"), true);
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getusercollectResult.contains("remoteHome"), true);
            Assert.assertEquals(getusercollectResult.contains("uid"), true);
        }
    }


    /**
     *根据uid与token获取收藏的文章
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByUidAndToken() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setUid(hs.get("uid"));
            getUserCollectBO.setToken(hs.get("token"));
            getUserCollectBO.setAppid("1.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token获取收藏的文章");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"result\":1"), true);
            Assert.assertEquals(getusercollectResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(getusercollectResult.contains("articles"), true);
            Assert.assertEquals(getusercollectResult.contains("strId"), true);
            Assert.assertEquals(getusercollectResult.contains("title"), true);
            Assert.assertEquals(getusercollectResult.contains("quality"), true);
            Assert.assertEquals(getusercollectResult.contains("srcSite"), true);
            Assert.assertEquals(getusercollectResult.contains("intro"), true);
            Assert.assertEquals(getusercollectResult.contains("thumbnail"), true);
            Assert.assertEquals(getusercollectResult.contains("editor"), true);
            Assert.assertEquals(getusercollectResult.contains("refUrl"), true);
            Assert.assertEquals(getusercollectResult.contains("mainType"), true);
            Assert.assertEquals(getusercollectResult.contains("subType"), true);
            Assert.assertEquals(getusercollectResult.contains("assLabs"), true);
            Assert.assertEquals(getusercollectResult.contains("assLabsName"), true);
            Assert.assertEquals(getusercollectResult.contains("infoStr"), true);
            Assert.assertEquals(getusercollectResult.contains("createdAt"), true);
            Assert.assertEquals(getusercollectResult.contains("updatedAt"), true);
            Assert.assertEquals(getusercollectResult.contains("enable"), true);
            Assert.assertEquals(getusercollectResult.contains("comment"), true);
            Assert.assertEquals(getusercollectResult.contains("bodyContent"), true);
            Assert.assertEquals(getusercollectResult.contains("mainTypeList"), true);
            Assert.assertEquals(getusercollectResult.contains("subTypeList"), true);
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getusercollectResult.contains("remoteHome"), true);
            Assert.assertEquals(getusercollectResult.contains("uid"), true);
        }
    }

    /**
     * 方法getusercollect配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByOpenMethodAndUid() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setUid(hs.get("uid"));
//            getUserCollectBO.setToken(hs.get("token"));
            getUserCollectBO.setAppid("1.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getusercollect配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"result\":1"), true);
            Assert.assertEquals(getusercollectResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(getusercollectResult.contains("articles"), true);
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getusercollectResult.contains("uid"), true);
        }
    }

    /**
     * 方法getusercollect配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByOpenMethodAndBmAppid() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
//            getUserCollectBO.setAccessToken(bmAppids.get("1.00002"));
            getUserCollectBO.setBmAppid("1.00002");
            getUserCollectBO.setAppid("1.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getusercollect配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"result\":1"), true);
            Assert.assertEquals(getusercollectResult.contains("\"appid\":\"1.00002\""), true);
            Assert.assertEquals(getusercollectResult.contains("articles"), true);
            Assert.assertEquals(getusercollectResult.contains("strId"), true);
            Assert.assertEquals(getusercollectResult.contains("title"), true);
            Assert.assertEquals(getusercollectResult.contains("quality"), true);
            Assert.assertEquals(getusercollectResult.contains("srcSite"), true);
            Assert.assertEquals(getusercollectResult.contains("intro"), true);
            Assert.assertEquals(getusercollectResult.contains("thumbnail"), true);
            Assert.assertEquals(getusercollectResult.contains("editor"), true);
            Assert.assertEquals(getusercollectResult.contains("refUrl"), true);
            Assert.assertEquals(getusercollectResult.contains("mainType"), true);
            Assert.assertEquals(getusercollectResult.contains("subType"), true);
            Assert.assertEquals(getusercollectResult.contains("assLabs"), true);
            Assert.assertEquals(getusercollectResult.contains("assLabsName"), true);
            Assert.assertEquals(getusercollectResult.contains("infoStr"), true);
            Assert.assertEquals(getusercollectResult.contains("createdAt"), true);
            Assert.assertEquals(getusercollectResult.contains("updatedAt"), true);
            Assert.assertEquals(getusercollectResult.contains("enable"), true);
            Assert.assertEquals(getusercollectResult.contains("comment"), true);
            Assert.assertEquals(getusercollectResult.contains("bodyContent"), true);
            Assert.assertEquals(getusercollectResult.contains("mainTypeList"), true);
            Assert.assertEquals(getusercollectResult.contains("subTypeList"), true);
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getusercollectResult.contains("remoteHome"), true);
            Assert.assertEquals(getusercollectResult.contains("uid"), true);
        }
    }

    /**
     * 方法getusercollect配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByAuthMethodAndUid() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setUid(hs.get("uid"));
            getUserCollectBO.setToken(hs.get("token"));
            getUserCollectBO.setAppid("100.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getusercollect配置在authMethod当中，校验Token");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"result\":1"), true);
            Assert.assertEquals(getusercollectResult.contains("\"appid\":\"100.00002\""), true);
            Assert.assertEquals(getusercollectResult.contains("articles"), true);
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getusercollectResult.contains("uid"), true);
        }
    }


    /**
     * 方法getusercollect配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setUid(hs.get("uid"));
//            getUserCollectBO.setToken(hs.get("token"));
            getUserCollectBO.setAppid("100.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getusercollect配置在authMethod当中，不校验Token");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getusercollectResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法getusercollect配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByAuthMethodAndBmAppid() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setBmAppid("100.00002");
            getUserCollectBO.setAccessToken(bmAppids.get("100.00002"));
            getUserCollectBO.setAppid("100.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getusercollect配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"result\":1"), true);
            Assert.assertEquals(getusercollectResult.contains("\"appid\":\"100.00002\""), true);
            Assert.assertEquals(getusercollectResult.contains("articles"), true);
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getusercollectResult.contains("uid"), true);
        }
    }

    /**
     * 方法getusercollect配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getUserCollectTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String getusercollectUrl = null;
        GetUserCollectBO getUserCollectBO = null;
        String getusercollectResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getusercollectUrl = url + "/ArticleService/getusercollect";
            getUserCollectBO = new GetUserCollectBO();
            getUserCollectBO.setBmAppid("100.00002");
//            getUserCollectBO.setAccessToken(bmAppids.get("100.00002"));
            getUserCollectBO.setAppid("100.00002");
            log.info("getusercollectUrl 请求的参数=" + getusercollectUrl);
            log.info("getUserCollectBO 请求的参数=" + JSON.toJSONString(getUserCollectBO));
            getusercollectResult = HttpUtil.postGeneralUrl(getusercollectUrl, "application/json", JSON.toJSONString(getUserCollectBO), "UTF-8");
            log.info("getusercollectResult 返回结果=" + getusercollectResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getusercollect配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(getusercollectUrl);
            recordhttp.setRequest(JSON.toJSONString(getUserCollectBO));
            recordhttp.setResponse(getusercollectResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getusercollectResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getusercollectResult.contains("\"result\":101"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(1));
    }


    @Override
    public final void initData() {

    }


    @Override
    public void destroyData() {
    }

}
