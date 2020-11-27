package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article
 * @Date Create on 13:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.GetArticleAddInfoBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.GetArticleBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Slf4j
@Component
public class GetArticleAddInfoTest extends BaseTest {


    /**
     * 根据uid与token获取全部文章的附加信息
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByUidAndToken() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        try {
            HashMap<String, String> hs = userLoginTest();
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
            getArticleAddInfoBO.setToken(hs.get("token"));
            getArticleAddInfoBO.setUid(hs.get("uid"));
            getArticleAddInfoBO.setBmAppid("1.00002");
            getArticleAddInfoBO.setAppid("1.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据uid与token获取全部文章的附加信息");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssLabs"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssSubTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allMainTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":1"), true);
            JSONObject jSONObject = JSON.parseObject(getarticleaddinfoResult);
            Map<String, Object> Map = jSONObject.getInnerMap();
            Map<String, Object> allAssLabsMap=(Map<String, Object>)Map.get("allAssLabs");
            Map<String, Object> allAssSubTypesMap=(Map<String, Object>)Map.get("allAssSubTypes");
            Map<String, Object> allMainTypesMap=(Map<String, Object>)Map.get("allMainTypes");
            Assert.assertTrue(allAssLabsMap.size() > 0);
            Assert.assertTrue(allAssSubTypesMap.size() > 0);
            Assert.assertTrue(allMainTypesMap.size() > 0);
        }
    }

    /**
     * 根据BmAppidAndAccessToken获取全部文章的附加信息
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByBmAppidAndAccessToken() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
            getArticleAddInfoBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleAddInfoBO.setBmAppid("1.00002");
            getArticleAddInfoBO.setAppid("1.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据BmAppidAndAccessToken获取全部文章的附加信息");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssLabs"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssSubTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allMainTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":1"), true);
            JSONObject jSONObject = JSON.parseObject(getarticleaddinfoResult);
            Map<String, Object> Map = jSONObject.getInnerMap();
            Map<String, Object> allAssLabsMap=(Map<String, Object>)Map.get("allAssLabs");
            Map<String, Object> allAssSubTypesMap=(Map<String, Object>)Map.get("allAssSubTypes");
            Map<String, Object> allMainTypesMap=(Map<String, Object>)Map.get("allMainTypes");
            Assert.assertTrue(allAssLabsMap.size() > 0);
            Assert.assertTrue(allAssSubTypesMap.size() > 0);
            Assert.assertTrue(allMainTypesMap.size() > 0);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByNotExistParameterAppid() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
            getArticleAddInfoBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleAddInfoBO.setBmAppid("1.00002");
//            getArticleAddInfoBO.setAppid("1.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":106"), true);
        }
    }


    /**
     * 方法getarticleaddinfo配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByOpenMethodAndUid() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
//            getArticleAddInfoBO.setToken(hs.get("token"));
            getArticleAddInfoBO.setUid(hs.get("uid"));
            getArticleAddInfoBO.setAppid("1.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticleaddinfo配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssLabs"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssSubTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allMainTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":1"), true);
            JSONObject jSONObject = JSON.parseObject(getarticleaddinfoResult);
            Map<String, Object> Map = jSONObject.getInnerMap();
            Map<String, Object> allAssLabsMap=(Map<String, Object>)Map.get("allAssLabs");
            Map<String, Object> allAssSubTypesMap=(Map<String, Object>)Map.get("allAssSubTypes");
            Map<String, Object> allMainTypesMap=(Map<String, Object>)Map.get("allMainTypes");
            Assert.assertTrue(allAssLabsMap.size() > 0);
            Assert.assertTrue(allAssSubTypesMap.size() > 0);
            Assert.assertTrue(allMainTypesMap.size() > 0);
        }
    }


    /**
     * 方法getarticleaddinfo配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByOpenMethodAndBmAppid() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
//            getArticleAddInfoBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleAddInfoBO.setBmAppid("1.00002");
            getArticleAddInfoBO.setAppid("1.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticleaddinfo配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssLabs"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssSubTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allMainTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":1"), true);
            JSONObject jSONObject = JSON.parseObject(getarticleaddinfoResult);
            Map<String, Object> Map = jSONObject.getInnerMap();
            Map<String, Object> allAssLabsMap=(Map<String, Object>)Map.get("allAssLabs");
            Map<String, Object> allAssSubTypesMap=(Map<String, Object>)Map.get("allAssSubTypes");
            Map<String, Object> allMainTypesMap=(Map<String, Object>)Map.get("allMainTypes");
            Assert.assertTrue(allAssLabsMap.size() > 0);
            Assert.assertTrue(allAssSubTypesMap.size() > 0);
            Assert.assertTrue(allMainTypesMap.size() > 0);
        }
    }


    /**
     * 方法getarticleaddinfo配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByAuthMethodAndUid() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
            getArticleAddInfoBO.setToken(hs.get("token"));
            getArticleAddInfoBO.setUid(hs.get("uid"));
            getArticleAddInfoBO.setAppid("100.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticleaddinfo配置在authMethod当中，校验Token");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssLabs"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssSubTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allMainTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":1"), true);
            JSONObject jSONObject = JSON.parseObject(getarticleaddinfoResult);
            Map<String, Object> Map = jSONObject.getInnerMap();
            Map<String, Object> allAssLabsMap=(Map<String, Object>)Map.get("allAssLabs");
            Map<String, Object> allAssSubTypesMap=(Map<String, Object>)Map.get("allAssSubTypes");
            Map<String, Object> allMainTypesMap=(Map<String, Object>)Map.get("allMainTypes");
            Assert.assertTrue(allAssLabsMap.size() > 0);
            Assert.assertTrue(allAssSubTypesMap.size() > 0);
            Assert.assertTrue(allMainTypesMap.size() > 0);
        }
    }


    /**
     * 方法getarticleaddinfo配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
//            getArticleAddInfoBO.setToken(hs.get("token"));
            getArticleAddInfoBO.setUid(hs.get("uid"));
            getArticleAddInfoBO.setAppid("100.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticleaddinfo配置在authMethod当中，不校验Token");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法getarticleaddinfo配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByAuthMethodAndBmAppid() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
            getArticleAddInfoBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleAddInfoBO.setBmAppid("1.00002");
            getArticleAddInfoBO.setAppid("100.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticleaddinfo配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssLabs"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allAssSubTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("allMainTypes"), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":1"), true);
            JSONObject jSONObject = JSON.parseObject(getarticleaddinfoResult);
            Map<String, Object> Map = jSONObject.getInnerMap();
            Map<String, Object> allAssLabsMap=(Map<String, Object>)Map.get("allAssLabs");
            Map<String, Object> allAssSubTypesMap=(Map<String, Object>)Map.get("allAssSubTypes");
            Map<String, Object> allMainTypesMap=(Map<String, Object>)Map.get("allMainTypes");
            Assert.assertTrue(allAssLabsMap.size() > 0);
            Assert.assertTrue(allAssSubTypesMap.size() > 0);
            Assert.assertTrue(allMainTypesMap.size() > 0);
        }
    }


    /**
     * 方法getarticleaddinfo配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getArticLeaddInfoTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String getarticleaddinfoUrl = null;
        GetArticleAddInfoBO getArticleAddInfoBO = null;
        String getarticleaddinfoResult = "";
        try {
            getarticleaddinfoUrl = url + "/ArticleService/getarticleaddinfo";
            getArticleAddInfoBO = new GetArticleAddInfoBO();
//            getArticleAddInfoBO.setAccessToken(bmAppids.get("1.00002"));
            getArticleAddInfoBO.setBmAppid("1.00002");
            getArticleAddInfoBO.setAppid("100.00002");
            getArticleAddInfoBO.setSeq("abc");
            log.info("getarticleaddinfoUrl 请求的参数=" + getarticleaddinfoUrl);
            log.info("getArticleAddInfoBO 请求的参数=" + JSON.toJSONString(getArticleAddInfoBO));
            getarticleaddinfoResult = HttpUtil.postGeneralUrl(getarticleaddinfoUrl, "application/json", JSON.toJSONString(getArticleAddInfoBO), "UTF-8");
            log.info("getarticleaddinfoResult 返回结果=" + getarticleaddinfoResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getarticleaddinfo配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(getarticleaddinfoUrl);
            recordhttp.setRequest(JSON.toJSONString(getArticleAddInfoBO));
            recordhttp.setResponse(getarticleaddinfoResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getarticleaddinfoResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getarticleaddinfoResult.contains("\"result\":101"), true);
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
