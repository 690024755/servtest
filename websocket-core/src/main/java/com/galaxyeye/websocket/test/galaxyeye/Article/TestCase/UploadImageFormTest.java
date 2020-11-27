package com.galaxyeye.websocket.test.galaxyeye.Article.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
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
@Component("articleUploadImageFormTest")
public class UploadImageFormTest extends BaseTest {


    /**
     * 使用二进制流上传图片进行发送，png图片上传,参数Source=Article
     * 返回上传的图片地址在文章服务器的配置文件里设置：参数ArticleRemoteHome=http://imag.galaxyeye.xyz/或者参数ArticleRemoteHome=https://imag.galaxyeye.xyz/
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByPngAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "article");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用二进制流上传图片进行发送，png图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageformResult.contains(".png"), true);
        }
    }


    /**
     * 使用二进制流上传图片进行发送，gif图片上传,参数Source=Article
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByGifAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "article");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article4.gif");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用二进制流上传图片进行发送，gif图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageformResult.contains(".gif"), true);
        }
    }


    /**
     * 上传二图片路径，Jpg图片上传,参数Source=Article
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByJpgAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "article");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3.jpg");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用二进制流上传图片进行发送，Jpg图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageformResult.contains(".jpg"), true);
        }
    }

    /**
     * 上传二图片路径，gif图片上传,参数Source=Messageboard
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByGifAndParameterSourceValueIsMessageboard() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article4.gif");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用二进制流上传图片进行发送，gif图片上传,参数Source=Article");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains(".gif"), true);
        }
    }

    /**
     * 上传二图片路径，Png图片上传,参数Source=Messageboard
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByPngAndParameterSourceValueIsMessageboard() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，Png图片上传,参数Source=Messageboard");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains(".png"), true);
        }
    }

    /**
     * 上传二图片路径，jpg图片上传,参数Source=Messageboard
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByJpgAndParameterSourceValueIsMessageboard() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3.jpg");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，jpg图片上传,参数Source=Messageboard");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains(".jpg"), true);
        }
    }

    /**
     * 上传二图片路径，jpg图片上传,参数Source的值除Messageboard与Article之外的其他值如test
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByJpgAndParameterSourceValueIsTest() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "test");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3.jpg");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二图片路径，jpg图片上传,参数Source的值除Messageboard与Article之外的其他值如test");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 请求参数source=messageboard，上传的图片超过2M
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByPictureSizeMoreThan2MAndParameterSourceValueIsMessageBoard() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3M.JPG");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数source=messageboard，上传的图片超过2M");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"article_image_oversize\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":402"), true);
        }
    }

    /**
     * 请求参数source=article，支持上传的图片小于等于3M
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByPictureSizeMoreThan2MAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "article");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3M.JPG");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数source=article，支持上传的图片小于等于3M");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageformResult.contains(".jpg"), true);
        }
    }


    /**
     * 请求参数source=article，不支持上传的图片大于3M
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByPictureSizeMoreThan3MAndParameterSourceValueIsArticle() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "article");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("5M.jpg");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数source=article，不支持上传的图片大于3M");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"article_image_oversize\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":402"), true);
        }
    }


    /**
     * 使用uid与token上传图片
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByUidAndToken() throws Exception {
        HashMap<String, String> hs = userLoginTest();
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("token",hs.get("token") );
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", hs.get("uid"));
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3M.JPG");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token上传图片");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"article_image_oversize\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":402"), true);
        }
    }

    /**
     * 使用过期的token上传图片
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByExpireToken() throws Exception {
        HashMap<String, String> hs = getuserExpireToken();
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("token",hs.get("token") );
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "article");
            multiParaMap.put("uid", hs.get("uid"));
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article3M.JPG");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用过期的token上传图片");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/article"), true);
            Assert.assertEquals(uploadimageformResult.contains(".jpg"), true);
        }
    }


    /**
     * 使用BmAppid与AccessToken上传图片
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByBmAppidAndAccessToken() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken上传图片");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains("png"), true);
        }
    }


    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByNotExistParameterAppid() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"access_deny\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":106"), true);
        }
    }

    /**
     * 必填参数Source校验
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByNotExistParameterSource() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("seq", "abc");
//            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", "417941");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法uploadimageform配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByOpenMethodAndUid() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", hs.get("uid"));
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimageform配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains("png"), true);
        }
    }

    /**
     * 方法uploadimageform配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByOpenMethodAndBmAppid() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", bmAppids.get("1.00002"));
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("source", "messageboard");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimageform配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains("png"), true);
        }
    }


    /**
     * 方法uploadimageform配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByAuthMethodAndUid() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("appid", "100.00002");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", hs.get("uid"));
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimageform配置在authMethod当中，校验Token");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains("png"), true);
        }
    }

    /**
     * 方法uploadimageform配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
//            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("appid", "100.00002");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("uid", hs.get("uid"));
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimageform配置在authMethod当中，不校验Token");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 方法uploadimageform配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByAuthMethodAndBmAppid() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("accessToken", bmAppids.get("1.00002"));
            multiParaMap.put("appid", "100.00002");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("bmAppid", "1.00002");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimageform配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("url"), true);
            Assert.assertEquals(uploadimageformResult.contains("img/msgboard"), true);
            Assert.assertEquals(uploadimageformResult.contains("png"), true);
        }
    }

    /**
     * 方法uploadimageform配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadImageFormTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/ArticleService/uploadimageform";
            multiParaMap = new HashMap<String, String>();
//            multiParaMap.put("accessToken", bmAppids.get("1.00002"));
            multiParaMap.put("appid", "100.00002");
            multiParaMap.put("source", "messageboard");
            multiParaMap.put("bmAppid", "1.00002");
            multiFileMap = new HashMap<String, String>();
            String filePathPng = getFilePath("Article2.png");
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("multiParaMap 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("multiFileMap 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法uploadimageform配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiFileMap) + JSON.toJSONString(multiParaMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":101"), true);
        }
    }

    /**
     * get请求获取https链接的图片
     * @throws Exception
     */
    @Test
    public void getJpg() throws Exception {
        String JpgUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1906469856,4113625838&fm=26&gp=0.jpg";
        String JpgResult = "";
        String request = null;
        try {
            log.info("JpgUrl 请求的参数=" + JpgUrl);
            log.info("request 请求的参数=" + request);
            JpgResult = HttpUtil.getGeneralUrl(JpgUrl,null,"utf-8");
            log.info("JpgResult 返回结果=" + JpgResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertEquals(JpgResult.length()>0, true);
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
