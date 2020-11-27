package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 13:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.DeleteImageBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Slf4j
@Component
public class DeleteImageTest extends BaseTest {

    @Autowired
    UploadImageFormTest uploadImageFormTest;

    @Autowired
    BusinessServiceTrafficPoliceSendImageUrlTest businessServiceTrafficPoliceSendImageUrlTest;

    @Test
    public void deleteimageTest() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl = url+"/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setToken(hs.get("token"));
            String url="www.baidu.com";
            deleteImageBO.setUrl(url);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
            System.out.println("deleteimageTest="+Thread.currentThread().getId());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常删除图片");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("\"result\":1"),true);
        }



    }

    /**
     * 删除不存在的图片地址
     * @throws Exception
     */
    @Test
    public void deleteimageTestByNotExistPicture() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl = url+"/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setToken(hs.get("token"));
            String url="www.baidu.com";
            deleteImageBO.setUrl(url);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("删除不存在的图片地址");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(deleteimageResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 参数url地址为空
     * @throws Exception
     */
    @Test
    public void deleteimageTestByEmptyUrl() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl = url+"/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setToken(hs.get("token"));
            String url="";
            deleteImageBO.setUrl(url);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数url地址为空");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("url is empty"),true);
        }
    }

    /**
     * 参数url不传
     * @throws Exception
     */
    @Test
    public void deleteimageTestByNotParameterUrl() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl = url+"/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setToken(hs.get("token"));
            String url="";
//        deleteImageBO.setUrl(url);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数url不传");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("url is empty"),true);
        }
    }

    /**
     * 使用uid与token删除图片
     * @throws Exception
     */
    @Test
    public void deleteimageTestByUidAndToken() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl =url+ "/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setToken(hs.get("token"));
            //上传图片
            String returnUrl=uploadImageFormTest.getImageUrl();
            //提交问题图片
            businessServiceTrafficPoliceSendImageUrlTest.sendimageurlTest(returnUrl);
            deleteImageBO.setUrl(returnUrl);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token删除图片");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(deleteimageResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     * 使用BmAppid与token删除图片
     * @throws Exception
     */
    @Test
    public void deleteimageTestByBmappidAndAccessToken() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl = url+"/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
//        deleteImageBO.setToken(hs.get("token"));
            //上传图片
            String returnUrl=uploadImageFormTest.getImageUrl();
            //提交问题图片
            businessServiceTrafficPoliceSendImageUrlTest.sendimageurlTest(returnUrl);
            deleteImageBO.setUrl(returnUrl);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与token删除图片");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(deleteimageResult.contains("\"msg\":\"ok\""),true);
        }

    }

    /**
     * 执行主流程业务逻辑，上传图片--》提交问题图片---》删除已经上传的图片
     * @throws Exception
     */
    @Test
    public void deleteimageTestByExistPicture() throws Exception {
        String deleteimageUrl =null;
        DeleteImageBO deleteImageBO =null;
        String deleteimageResult ="";
        try{
            deleteimageUrl =url+ "/BusinessService/trafficpolice/deleteimage";
            deleteImageBO=new DeleteImageBO();
            HashMap<String,String> hs=userLoginTest();
            deleteImageBO.setAppid("1.00002");
            deleteImageBO.setBmAppid("1.00002");
            deleteImageBO.setUid(Long.valueOf(hs.get("uid")));
            deleteImageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
//        deleteImageBO.setToken(hs.get("token"));
            //上传图片
            String returnUrl=uploadImageFormTest.getImageUrl();
            //提交问题图片
            businessServiceTrafficPoliceSendImageUrlTest.sendimageurlTest(returnUrl);
            deleteImageBO.setUrl(returnUrl);
            log.info("deleteimageUrl 请求的参数=" + deleteimageUrl);
            log.info("deleteImageBO 请求的参数=" + JSON.toJSONString(deleteImageBO));
            deleteimageResult = HttpUtil.postGeneralUrl(deleteimageUrl, "application/json", JSON.toJSONString(deleteImageBO), "UTF-8");
            log.info("deleteimageResult 返回结果=" + deleteimageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("执行主流程业务逻辑，上传图片--》提交问题图片---》删除已经上传的图片");
            recordhttp.setUrl(deleteimageUrl);
            recordhttp.setRequest(JSON.toJSONString(deleteImageBO));
            recordhttp.setResponse(deleteimageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(deleteimageResult.contains("\"result\":1"),true);
            Assert.assertEquals(deleteimageResult.contains("\"msg\":\"ok\""),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
