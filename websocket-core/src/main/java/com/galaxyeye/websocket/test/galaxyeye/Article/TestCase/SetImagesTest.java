package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.SetImagesBO;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 */
@Slf4j
@Component
public class SetImagesTest extends BaseTest {


    @Autowired
    private UploadImageTest uploadImageTest;


    /**
     * 设置图片是否启用接口，图片类型SourceValue=Messageboard
     * @throws Exception
     */
    @Test
    public void setImagesTestByJpgAndParameterSourceValueIsMessageboard() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            setImagesBO.setUid("417941");
            setImagesBO.setBmAppid("1.00002");
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            //设置图片启用状态
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            HashMap<String, String> hs = userLoginTest();
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("messageboard");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("设置图片是否启用接口，图片类型SourceValue=Messageboard");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 设置图片启用图片接口，图片类型SourceValue=Article
     * @throws Exception
     */
    @Test
    public void setImagesTestByJpgAndParameterSourceValueIsArticle() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            setImagesBO.setUid("417941");
            setImagesBO.setBmAppid("1.00002");
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            HashMap<String, String> hs = userLoginTest();
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("设置图片启用图片接口，图片类型SourceValue=Article");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 使用BmAppid与AccessToken启用图片
     * @throws Exception
     */
    @Test
    public void setImagesTestByBmAppidAndAccessToken() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            setImagesBO.setUid("417941");
            setImagesBO.setBmAppid("1.00002");
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            HashMap<String, String> hs = userLoginTest();
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken启用图片");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 使用uid与Token启用图片
     * @throws Exception
     */
    @Test
    public void setImagesTestByUidAndToken() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与Token启用图片");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
        }
    }


    /**
     * 使用过期的token启用图片
     * @throws Exception
     */
    @Test
    public void setImagesTestByExpireToken() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hsExpire = getuserExpireToken();
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hsExpire.get("uid"));
            setImagesBO.setToken(hsExpire.get("token"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用过期的token启用图片");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void setImagesTestByNotExistParameterAppid() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
//            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":106"), true);
        }
    }


    /**
     * 必填参数Enable校验,不填写默认是false
     * @throws Exception
     */
    @Test
    public void setImagesTestByNotExistParameterEnable() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
//            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Enable校验,不填写默认是false");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 必填参数urls校验
     * @throws Exception
     */
    @Test
    public void setImagesTestByNotExistParameterUrls() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
//            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数urls校验");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数urls是个空数组
     * @throws Exception
     */
    @Test
    public void setImagesTestByParameterUrlsValueIsEmpty() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
//            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数urls是个空数组");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 方法setimages配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void setImagesTestByOpenMethodAndUid() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法setimages配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 方法setimages配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void setImagesTestByOpenMethodAndBmAppid() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setBmAppid("1.00002");
            setImagesBO.setAccessToken(bmAppids.get("1.00002"));
            setImagesBO.setAppid("1.00002");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法setimages配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 方法setimages配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void setImagesTestByAuthMethodAndUid() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("100.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法setimages配置在authMethod当中，校验Token");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }


    /**
     * 方法setimages配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void setImagesTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setUid(hs.get("uid"));
//            setImagesBO.setToken(hs.get("token"));
            setImagesBO.setAppid("100.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法setimages配置在authMethod当中，不校验Token");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法setimages配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void setImagesTestByAuthMethodAndBmAppid() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setBmAppid("1.00002");
            setImagesBO.setAccessToken(bmAppids.get("1.00002"));
            setImagesBO.setAppid("100.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法setimages配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 方法setimages配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void setImagesTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String setimagesUrl = null;
        SetImagesBO setImagesBO = null;
        String setimagesResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            setimagesUrl = url + "/ArticleService/setimages";
            setImagesBO = new SetImagesBO();
            setImagesBO.setBmAppid("1.00002");
//            setImagesBO.setAccessToken(bmAppids.get("1.00002"));
            setImagesBO.setAppid("100.00002");
            setImagesBO.setSeq("abc");
            setImagesBO.setEnable(true);
            List<String> urls = new ArrayList<>();
            UploadImageBO uploadImageBO = new UploadImageBO();
            String filePath = getFilePath("Article3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            uploadImageBO.setImage(imgParam);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            urls.add(uploadImageTest.uploadImageGernal(uploadImageBO));
            setImagesBO.setUrls(urls);
            log.info("setimagesUrl 请求的参数=" + setimagesUrl);
            log.info("setImagesBO 请求的参数=" + JSON.toJSONString(setImagesBO));
            setimagesResult = HttpUtil.postGeneralUrl(setimagesUrl, "application/json", JSON.toJSONString(setImagesBO), "UTF-8");
            log.info("setimagesResult 返回结果=" + setimagesResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法setimages配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(setimagesUrl);
            recordhttp.setRequest(JSON.toJSONString(setImagesBO));
            recordhttp.setResponse(setimagesResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(setimagesResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(setimagesResult.contains("\"result\":101"), true);
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
