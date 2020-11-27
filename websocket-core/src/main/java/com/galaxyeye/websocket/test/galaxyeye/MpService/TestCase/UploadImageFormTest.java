package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 9:59
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.UploadImageFormDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class UploadImageFormTest extends BaseTest {


    public String getImageUrl() {
        initData();
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathPng);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
            UploadImageFormDTO uploadImageFormDTO = JSON.parseObject(uploadimageformResult, UploadImageFormDTO.class);
            return uploadImageFormDTO.getUrl();
        }
    }

    /**
     * 上传二进制流的文件，png图片上传
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByPNG() {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = "2.png";
            String filePathGif = "4.gif";
            String filePathJpg = "3.jpg";
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("seq", "abc");
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", getFilePath(filePathGif));
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二进制流的文件，png图片上传");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
        }
    }

    /**
     * 上传二进制流的文件，gif图片上传
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByGIF() {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("seq", "abc");
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathGif);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二进制流的文件，gif图片上传");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
        }

    }

    /**
     * 上传二进制流的文件，jpg图片上传
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByJPG() {
        String uploadimageformUrl = null;
        Map<String, String> multiParaMap = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
//        multiParaMap.put("token",hs.get("token"));
            multiParaMap.put("seq", "abc");
//        multiParaMap.put("uid",String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二进制流的文件，jpg图片上传");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
        }

    }

    /**
     * 上传二进制流的文件，使用bmAppid与accessToken进行图片上传
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByBmAppidAndAccessToken() {
        String uploadimageformUrl = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        Map<String, String> multiParaMap = null;
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
            multiParaMap.put("bmAppid", "1.00002");
            multiParaMap.put("accessToken", "4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
//        multiParaMap.put("token",hs.get("token"));
            multiParaMap.put("seq", "abc");
//        multiParaMap.put("uid",String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二进制流的文件，使用bmAppid与accessToken进行图片上传");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
        }
    }

    /**
     * 上传二进制流的文件，使用uid与token进行图片上传
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByUidAndToken() {
        String uploadimageformUrl = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        Map<String, String> multiParaMap = null;
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("seq", "abc");
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二进制流的文件，使用uid与token进行图片上传");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
        }
    }

    /**
     * 上传二进制流的文件，上传超过2M图片
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByOverSizePicture() {
        String uploadimageformUrl = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        Map<String, String> multiParaMap = null;
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            String filePathJpg1 = getFilePath("3M.JPG") ;
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("seq", "abc");
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg1);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传二进制流的文件，上传超过2M图片");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("traffic_image_size_oversize"), true);
        }
    }

    /**
     * 上传除png、jpg、gif之外的webp格式图片
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByWebp() throws Exception {
        String uploadimageformUrl = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        Map<String, String> multiParaMap = null;
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("眼镜盒.webp");
            String filePathJpg1 = getFilePath("眼镜盒.webp");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "1.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("seq", "abc");
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg1);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传除png、jpg、gif之外的webp格式图片");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("traffic_image_format_error"), true);
        }
    }

    /**
     * 接口方法配置在auth当中，使用未过期的token,验证Auth方法是否生效
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByAuth1() {
        String uploadimageformUrl = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        Map<String, String> multiParaMap = null;
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "100.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", hs.get("token"));
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在auth当中，使用未过期的token,验证Auth方法是否生效");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"result\":1"), true);
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(uploadimageformResult.contains("wzjj/advice/img"), true);
        }
    }

    /**
     * 接口方法配置在auth当中，使用过期的token,验证Auth方法是否生效
     * @throws Exception
     */
    @Test
    public void uploadimageformTestByAuth2() {
        String uploadimageformUrl = null;
        Map<String, String> multiFileMap = null;
        String uploadimageformResult = "";
        Map<String, String> multiParaMap = null;
        try {
            uploadimageformUrl = url + "/BusinessService/trafficpolice/uploadimageform";
            String filePathPng = getFilePath("2.png");
            String filePathGif = getFilePath("4.gif");
            String filePathJpg = getFilePath("3.jpg");
            HashMap<String, String> hs = userLoginTest();
            multiParaMap = new HashMap<String, String>();
            multiParaMap.put("roomId", String.valueOf(1218074641600352256L));
            multiParaMap.put("appid", "100.00002");
            //bmappid=1.00002，对应的AccessToken
//        multiParaMap.put("bmAppid","1.00002");
//        multiParaMap.put("accessToken","4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            multiParaMap.put("token", getuserExpireToken().get("token"));
            multiParaMap.put("uid", String.valueOf(hs.get("uid")));
            multiFileMap = new HashMap<String, String>();
            multiFileMap.put("image", filePathJpg);
            log.info("uploadimageformUrl 请求的参数=" + uploadimageformUrl);
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiParaMap));
            log.info("uploadImageFormBO 请求的参数=" + JSON.toJSONString(multiFileMap));
            uploadimageformResult = HttpUtil.formUpload(uploadimageformUrl, multiParaMap, multiFileMap);
            log.info("uploadimageformResult 返回结果=" + uploadimageformResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法配置在auth当中，使用过期的token,验证Auth方法是否生效");
            recordhttp.setUrl(uploadimageformUrl);
            recordhttp.setRequest(JSON.toJSONString(multiParaMap) + JSON.toJSONString(multiFileMap));
            recordhttp.setResponse(uploadimageformResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(uploadimageformResult.contains("\"msg\":\"token_error\""), true);
            Assert.assertEquals(uploadimageformResult.contains("\"result\":115"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public final void initData() {

    }

    @Override
    public void destroyData() {

    }

}
