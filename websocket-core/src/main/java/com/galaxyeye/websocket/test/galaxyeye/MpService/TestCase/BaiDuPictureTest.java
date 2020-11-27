package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 9:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/23日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.UploadImageBO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class BaiDuPictureTest extends BaseTest {
    private String picturePath=null;

    @Autowired
    private JedisTemplate jedisTemplate;


    @Test
    public void setKeyTest() throws Exception {
        String baiduKey="ms:baidu:imgtoken";
        Object obj=jedisTemplate.get(baiduKey);
        if(obj!=null){
            log.info("获取结果="+obj);
        }else {
            jedisTemplate.set(baiduKey,"ms:baidu:imgtoken");
            log.info("获取结果="+jedisTemplate.get(baiduKey));
        }
    }

    /**
     * 上传图片识别图片里的物品类型
     * @throws Exception
     */
    @Test
    public void uploadimageTest() throws Exception {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
            HashMap<String,String> hs=userLoginTest();
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setBmAppid("1.00002");
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(uploadimageResult.contains("log_id"),true);
            Assert.assertEquals(uploadimageResult.contains("result_num"),true);
            Assert.assertEquals(uploadimageResult.contains("keyword"),true);
            Assert.assertEquals(uploadimageResult.contains("root"),true);
            Assert.assertEquals(uploadimageResult.contains("score"),true);
        }
    }

    /**
     * 上传图片识别图片里的物品类型，方法uploadimage写在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void uploadimageTestByOpenMethodAndUid() {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
            HashMap<String,String> hs=userLoginTest();
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setBmAppid("1.00002");
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型，方法uploadimage写在OpenMethod当中，不校验Token");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(uploadimageResult.contains("log_id"),true);
            Assert.assertEquals(uploadimageResult.contains("result_num"),true);
            Assert.assertEquals(uploadimageResult.contains("keyword"),true);
            Assert.assertEquals(uploadimageResult.contains("root"),true);
            Assert.assertEquals(uploadimageResult.contains("score"),true);
        }
    }

    /**
     * 上传图片识别图片里的物品类型，方法uploadimage写在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadimageTestByOpenMethodAndBmAppid() {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
            HashMap<String,String> hs=userLoginTest();
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("1.00002");
            uploadImageBO.setBmAppid("1.00002");
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型，方法uploadimage写在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(uploadimageResult.contains("log_id"),true);
            Assert.assertEquals(uploadimageResult.contains("result_num"),true);
            Assert.assertEquals(uploadimageResult.contains("keyword"),true);
            Assert.assertEquals(uploadimageResult.contains("root"),true);
            Assert.assertEquals(uploadimageResult.contains("score"),true);
        }
    }

    /**
     * 上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndUid() {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
            HashMap<String,String> hs=userLoginTest();
            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setBmAppid("100.00002");
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，校验Token");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(uploadimageResult.contains("log_id"),true);
            Assert.assertEquals(uploadimageResult.contains("result_num"),true);
            Assert.assertEquals(uploadimageResult.contains("keyword"),true);
            Assert.assertEquals(uploadimageResult.contains("root"),true);
            Assert.assertEquals(uploadimageResult.contains("score"),true);
        }
    }

    /**
     * 上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，参数Token不校验
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndUidAndNotExistParameterToken() {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
            HashMap<String,String> hs=userLoginTest();
//            uploadImageBO.setToken(hs.get("token"));
            uploadImageBO.setUid(hs.get("uid"));
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setBmAppid("100.00002");
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，参数Token不校验");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndBmAppid() {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setBmAppid("100.00002");
            uploadImageBO.setAccessToken(bmAppids.get("100.00002"));
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，校验AccessToken");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(uploadimageResult.contains("log_id"),true);
            Assert.assertEquals(uploadimageResult.contains("result_num"),true);
            Assert.assertEquals(uploadimageResult.contains("keyword"),true);
            Assert.assertEquals(uploadimageResult.contains("root"),true);
            Assert.assertEquals(uploadimageResult.contains("score"),true);
        }
    }


    /**
     * 上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，参数AccessToken不传
     * @throws Exception
     */
    @Test
    public void uploadimageTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() {
        String uploadImageUrl =null;
        UploadImageBO uploadImageBO =null;
        String uploadimageResult ="";
        try{
            uploadImageUrl = url+"/BusinessService/baidu/uploadimage";
            uploadImageBO = new UploadImageBO();
            //redis设置百度图片key
            setKeyTest();
//            uploadImageBO.setAccessToken(bmAppids.get("100.00002"));
            uploadImageBO.setAppid("100.00002");
            uploadImageBO.setBmAppid("100.00002");
            String filePath=getFilePath("3.jpg");
            String imgParam = "".concat(Base64Util.imageChangeBase64(filePath));
//      uploadImageBO.setImg("/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsAhwDAREAAhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECBAYFAwcI/8QAPRAAAQMCAwUGBQIFBAEFAAAAAQACAwQRBRIhEzFBUZEGM1JhcYEiMnKhsRQjB0JiwdEV4fDxgiRDU4SU/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAIhEBAQEBAAIDAAIDAQAAAAAAAAERAiExAxJBBFEiMmGB/9oADAMBAAIRAxEAPwD8Ele7bP8AiPzHj5oMM7vEeqBnd4j1QM7vEeq6uLQXG0TSbkSaEOHFBqzPgdIY2XlB+INawAOJ8939lFjncRk2Mm0io46Wd2maWoa2TL5AHT2RXHYvWRSQSUVTKHgklwbUZRc/y7zvPPorErj8Qnngock0r6ZrflZdsrQ3gNLarUZteFU1cUgma2SqbJYWsMrXerb3GnVbxztatNUysn0qADxL3WB97JYbXyqat0r7yNY5wPzg3ukha13zue4OytDhxaLFaxNYB1vTkgOcC67RbyQYkEcEREGKCIogiAgICAgiAgICAgICAgiDapu7PqgwljG1fqfmP5QY7McygbMcygbMcygbMcygbMcyguzHMoJsxzKCiMcygbMcygbMcygmzHMoMtk3mUE2Y5lA2Y5lBdmOZQNmOZQNmOZQNmOZQURjmUF2TeZQXZjmUQ2Y5lUNmL7ygojHMqC7McyqLsxbiorIMDiGkmyg+kDM20u51o2FwF9LoM6eQ7Vws0F/xZg0Ai3Lkor2KaQTRvc6KMOY0HMG6kixFzxUa12HZnGKw0zQXsMbXj9ssBafi9Pws2RqXw7PCnmenkkm/c2zGl7CSGm5AtYWWK1+Pdaz9FBliJMe0y7N+rbXtu3DckPx64aGTRsJc8SNLjmcTl9NdAqjcjizkxl77Zd97lErLYNNM1wLgfi3HTQclRotEZaP2Y9zbADcTyWVbooo8zHZ5Pi3i4I6KyErg67EZxUVIaImsicSGtjaLnMRqbX+6jTxO1MIrYa7buJ2MbZBawzGx+bmrylc/Sskl2Uv6mZsgpg5puDlsNAAQQB7LTNczX04xClnq5HuY9rSbMsATnAuSQSd/NajNjm3xNGgJ32WmGGQcyqIYweJQTZi+8ojJ0LRa19Qgx2Y5lBNkOZQQxjmUEMY5lBjsxzKKmzHMoGzHMoGzHMoGzHMoBjHMoJsxzKBsxzKBsxzKBsxzKC7IcygmzHMoGzHMoGzbzKBs28yg2aeMbM6neg//9k=");
            uploadImageBO.setImg(imgParam);
            log.info("uploadImageUrl 请求的参数=" + uploadImageUrl);
            log.info("uploadImageBO 请求的参数=" + JSON.toJSONString(uploadImageBO));
            uploadimageResult = HttpUtil.postGeneralUrl(uploadImageUrl, "application/json", JSON.toJSONString(uploadImageBO), "UTF-8");
            log.info("uploadimageResult 返回结果=" + uploadimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("上传图片识别图片里的物品类型，方法uploadimage写在AuthMethod当中，参数AccessToken不传");
            recordhttp.setUrl(uploadImageUrl);
            recordhttp.setRequest(JSON.toJSONString(uploadImageBO));
            recordhttp.setResponse(uploadimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(uploadimageResult.contains("\"msg\":\"parameter_error\""),true);
            Assert.assertEquals(uploadimageResult.contains("\"result\":101"),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        picturePath=this.getClass().getResource("").getPath()+"DataCase/";
    }

    @Override
    public void destroyData() {

    }
}
