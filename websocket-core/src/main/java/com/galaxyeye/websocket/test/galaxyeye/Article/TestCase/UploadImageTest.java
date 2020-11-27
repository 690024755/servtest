package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.BO.UploadImageBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.DTO.UploadImageDTO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Slf4j
@Component
public class UploadImageTest extends BaseTest {

    /**
     * 文章通用性图片上传
     * @return
     * @throws Exception
     */
    public String uploadImageGernal(UploadImageBO uploadImageBO) throws Exception {
        String uploadimageUrl = null;
        String uploadimageResult = "";
        UploadImageDTO uploadImageDTO = null;
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
            uploadImageDTO = JSON.parseObject(uploadimageResult, UploadImageDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("文章通用性图片上传");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img"), true);
            return uploadImageDTO.getUrl();
        }
    }


    /**
     * 上传二图片路径，gif图片上传,参数Source=Article
     * @throws Exception
     */
    @Test
    public void uploadimageTestByGifAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，gif图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".gif"), true);
        }
    }

    /**
     * 上传二图片路径，Png图片上传,参数Source=Article
     *
     * @throws Exception
     */
    @Test
    public void uploadimageTestByPngAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathPng = getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            uploadImageBO.setImage(imgParamPng);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，Png图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".png"), true);
        }
    }

    /**
     * 上传二图片路径，Png图片上传,参数Source=Article
     *
     * @throws Exception
     */
    @Test
    public void uploadimageTestByJpgAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，Png图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }

    /**
     * 上传二图片路径，gif图片上传,参数Source=Messageboard
     *
     * @throws Exception
     */
    @Test
    public void uploadimageTestByGifAndParameterSourceValueIsMessageboard() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，gif图片上传,参数Source=Messageboard");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".gif"), true);
        }
    }

    /**
     * 上传二图片路径，Png图片上传,参数Source=Messageboard
     *
     * @throws Exception
     */
    @Test
    public void uploadimageTestByPngAndParameterSourceValueIsMessageboard() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathPng = getFilePath("Article2.png");
            String imgParamPng = "data:image/png;base64,".concat(Base64Util.imageChangeBase64(filePathPng));
            uploadImageBO.setImage(imgParamPng);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，Png图片上传,参数Source=Messageboard");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".png"), true);
        }
    }

    /**
     * 上传二图片路径，Png图片上传,参数Source=Messageboard
     *
     * @throws Exception
     */
    @Test
    public void uploadimageTestByJpgAndParameterSourceValueIsMessageboard() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，Png图片上传,参数Source=Messageboard");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }

    /**
     * 上传二图片路径，Png图片上传,参数Source的值除Messageboard与Article之外的其他值如test
     *
     * @throws Exception
     */
    @Test
    public void uploadimageTestByJpgAndParameterSourceValueIsTest() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("test");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，Png图片上传,参数Source的值除Messageboard与Article之外的其他值如test");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数Source=article,图片上传的支持3M
     * @throws Exception
     */
    @Test
    public void uploadimageTestByPictureSizeEqual3MAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3M.JPG");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Source=article,图片上传的支持3M");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }

    /**
     * 请求参数Source=article,图片上传的不支持5M
     * @throws Exception
     */
    @Test
    public void uploadimageTestByPictureSizeEqual5MAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("5M.jpg");
//            String filePathJpg = getFilePath("Article3M.JPG");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Source=article,图片上传的不支持5M");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"read_error\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":114"), true);
        }
    }


    /**
     * 请求参数Source=article,图片上传的不支持5M
     * @throws Exception
     */
    @Test
    public void uploadimageTestByPictureSizeEqual3poit2MAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("3.2M.jpg");
//            String filePathJpg = getFilePath("Article3M.JPG");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Source=article,图片上传的不支持5M");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"article_image_oversize\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":402"), true);
        }
    }


    /**
     * 请求参数Source=messageboard,图片上传的不支持3M
     * @throws Exception
     */
    @Test
    public void uploadimageTestByPictureSizeEqual3MAndParameterSourceValueIsMessageBoard() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3M.JPG");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数Source=messageboard,图片上传的不支持3M");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"article_image_oversize\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":402"), true);
        }
    }


    /**
     * 使用uid与token上传图片
     * @throws Exception
     */
    @Test
    public void uploadimageTestByUidAndToken() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token上传图片");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }

    /**
     * 使用过期的token上传图片
     * @throws Exception
     */
    @Test
    public void uploadimageTestByExpireToken() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = getuserExpireToken();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用过期的token上传图片");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }


    /**
     * 使用BmAppid与AccessToken上传图片
     * @throws Exception
     */
    @Test
    public void uploadimageTestByBmAppidAndAccessToken() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken上传图片");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void uploadimageTestByNotExistParameterAppid() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
//            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":106"), true);
        }
    }

    /**
     * 必填参数Source校验
     * @throws Exception
     */
    @Test
    public void uploadimageTestByNotExistParameterSource() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            uploadImageBO.setUid("417941");
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
//            uploadImageBO.setSource("messageboard");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Source校验");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法uploadimage配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void uploadimageTestByOpenMethodAndUid() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setUid(hs.get("uid"));
//            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimage配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".gif"), true);
        }
    }

    /**
     * 方法uploadimage配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadimageTestByOpenMethodAndBmAppid() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
//            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimage配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".gif"), true);
        }
    }

    /**
     * 方法uploadimage配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndUid() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimage配置在authMethod当中，校验Token");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".gif"), true);
        }
    }

    /**
     * 方法uploadimage配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setUid(hs.get("uid"));
//            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimage配置在authMethod当中，不校验Token");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法uploadimage配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndBmAppid() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimage配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageResult.contains(".gif"), true);
        }
    }


    /**
     * 方法uploadimage配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathGif = getFilePath("Article4.gif");
            String imgParamGif = "data:image/gif;base64,".concat(Base64Util.imageChangeBase64(filePathGif));
            uploadImageBO.setImage(imgParamGif);
//            uploadImageBO.setAccessToken(bmAppids.get("1.00002"));
            uploadImageBO.setBmAppid("1.00002");
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setSource("article");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadimageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimage配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * http请求头中x-forwarded-for字段中如携带有多个ip，
     * 则取第一个ip为原始ip（测试时重点测试header中x-forwarded-for
     * 域携带两个以上ip的情况，例：172.14.1.15,172.14.1.16,172.14.1.17）
     * 测试用例描述：
     * header头文件里包含ip地址且值为无效的
     * @throws Exception
     */
    @Test
    public void uploadimageTestByHeaderContainIpValueIsInvalid() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("x-forwarded-for","999.999.999.999");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            log.info("headerMaps 请求的参数=" + JSON.toJSONString(headerMaps));
            uploadimageResult = HttpUtil.postGeneralUrlByCustomHeader(uploadimageUrl, headerMaps,"application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("header头文件里包含ip地址且值为无效的");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":106"), true);
        }
    }

    /**
     * http请求头中x-forwarded-for字段中如携带有多个ip，
     * 则取第一个ip为原始ip（测试时重点测试header中x-forwarded-for
     * 域携带两个以上ip的情况，例：172.14.1.15,172.14.1.16,172.14.1.17）
     * 测试用例描述：
     * header头文件里包含ip地址且值为空
     * @throws Exception
     */
    @Test
    public void uploadimageTestByHeaderContainIpValueIsEmpty() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("x-forwarded-for"," ");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrlByCustomHeader(uploadimageUrl, headerMaps,"application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("header头文件里不包含ip地址");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":106"), true);
        }
    }


    /**
     * http请求头中x-forwarded-for字段中如携带有多个ip，
     * 则取第一个ip为原始ip（测试时重点测试header中x-forwarded-for
     * 域携带两个以上ip的情况，例：172.14.1.15,172.14.1.16,172.14.1.17）
     * 测试用例描述：
     * header头文件里不包含ip地址
     * @throws Exception
     */
    @Test
    public void uploadimageTestByHeaderContainNoIp() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            Map<String ,String > headerMaps=new HashMap<>();
//            headerMaps.put("x-forwarded-for","172.14.1.15");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrlByCustomHeader(uploadimageUrl, headerMaps,"application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("header头文件里不包含ip地址");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }


    /**
     * http请求头中x-forwarded-for字段中如携带有多个ip，
     * 则取第一个ip为原始ip（测试时重点测试header中x-forwarded-for
     * 域携带两个以上ip的情况，例：172.14.1.15,172.14.1.16,172.14.1.17）
     * 测试用例描述：
     * header头文件里包含单个ip地址
     * @throws Exception
     */
    @Test
    public void uploadimageTestByHeaderContainSingleIp() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("x-forwarded-for","172.14.1.15");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrlByCustomHeader(uploadimageUrl, headerMaps,"application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("header头文件里包含单个ip地址");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
        }
    }

    /**
     * http请求头中x-forwarded-for字段中如携带有多个ip，
     * 则取第一个ip为原始ip（测试时重点测试header中x-forwarded-for
     * 域携带两个以上ip的情况，例：172.14.1.15,172.14.1.16,172.14.1.17）
     * 测试用例描述：
     * header头文件里包含多个ip地址
     * @throws Exception
     */
    @Test
    public void uploadimageTestByHeaderContainMutiIp() throws Exception {
        String uploadimageUrl = null;
        UploadImageBO uploadImageBO = null;
        String uploadimageResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            uploadimageUrl = url + "/ArticleService/uploadimage";
            uploadImageBO = new UploadImageBO();
            String filePathJpg = getFilePath("Article3.jpg");
            String imgParamJpg = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePathJpg));
            uploadImageBO.setImage(imgParamJpg);
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setSeq("abc");
            uploadImageBO.setSource("messageboard");
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("x-forwarded-for","172.14.1.15,172.14.1.16,172.14.1.17");
            log.info("uploadimageUrl 请求的参数=" + uploadimageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrlByCustomHeader(uploadimageUrl, headerMaps,"application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("header头文件里包含多个ip地址");
            recordhttp.setUrl(uploadimageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageResult.contains("url"), true);
            Assert.assertEquals(uploadimageResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageResult.contains(".jpg"), true);
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
