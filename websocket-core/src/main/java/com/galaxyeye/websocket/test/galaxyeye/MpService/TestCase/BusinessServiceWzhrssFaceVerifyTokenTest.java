package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 13:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/24日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssFaceVerifyTokenBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;

@Component
@Slf4j
public class BusinessServiceWzhrssFaceVerifyTokenTest extends BaseTest {

    /**
     * 人脸识别通用接口,返回id
     * @param businessServiceWzhrssFaceVerifyTokenBO
     * @throws Exception
     */
    public String businessServiceWzhrssFaceVerifyTokenTestByGernal(BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO) throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人脸识别通用接口");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("id"),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":1"),true);
            return JsonPath.read(businessServiceWzhrssFaceVerifyTokenResult,"$.id");
        }
    }

    /**
     * 人脸识别接口,身份证与姓名匹配获取人脸识别id
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssFaceVerifyTokenTestByParameterIdCardAndNameIsMatch() throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            businessServiceWzhrssFaceVerifyTokenBO.setAppid("1.00002");
            businessServiceWzhrssFaceVerifyTokenBO.setIdCard("330324198907024490");
            businessServiceWzhrssFaceVerifyTokenBO.setName("徐列方");
            businessServiceWzhrssFaceVerifyTokenBO.setToken(hs.get("token"));
            businessServiceWzhrssFaceVerifyTokenBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人脸识别接口,身份证与姓名匹配获取人脸识别id");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("id"),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 人脸识别接口,身份证与姓名不匹配获取人脸识别id
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssFaceVerifyTokenTestByParameterIdCardAndNameIsNotMatch() throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            businessServiceWzhrssFaceVerifyTokenBO.setAppid("1.00002");
            businessServiceWzhrssFaceVerifyTokenBO.setIdCard("330324198907024490");
            businessServiceWzhrssFaceVerifyTokenBO.setName("徐列方1");
            businessServiceWzhrssFaceVerifyTokenBO.setToken(hs.get("token"));
            businessServiceWzhrssFaceVerifyTokenBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("id"),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 人脸识别接口,身份证与姓名不匹配获取人脸识别id
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssFaceVerifyTokenTestByParameterIdCardValueWithX() throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            businessServiceWzhrssFaceVerifyTokenBO.setAppid("1.00002");
            businessServiceWzhrssFaceVerifyTokenBO.setIdCard("31011019830726325X");
            businessServiceWzhrssFaceVerifyTokenBO.setName("徐列方1");
            businessServiceWzhrssFaceVerifyTokenBO.setToken(hs.get("token"));
            businessServiceWzhrssFaceVerifyTokenBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("id"),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 必填参数Name校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssFaceVerifyTokenTestByNotExistParameterName() throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            businessServiceWzhrssFaceVerifyTokenBO.setAppid("1.00002");
            businessServiceWzhrssFaceVerifyTokenBO.setIdCard("330324198907024490");
//            businessServiceWzhrssFaceVerifyTokenBO.setName("徐列方1");
            businessServiceWzhrssFaceVerifyTokenBO.setToken(hs.get("token"));
            businessServiceWzhrssFaceVerifyTokenBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Name校验");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"name is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 必填参数IdCard校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssFaceVerifyTokenTestByNotExistParameterIdCard() throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            businessServiceWzhrssFaceVerifyTokenBO.setAppid("1.00002");
//            businessServiceWzhrssFaceVerifyTokenBO.setIdCard("330324198907024490");
            businessServiceWzhrssFaceVerifyTokenBO.setName("徐列方1");
            businessServiceWzhrssFaceVerifyTokenBO.setToken(hs.get("token"));
            businessServiceWzhrssFaceVerifyTokenBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数IdCard校验");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"idCard is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 方法=faceverifytoken配置在openMethod当中，参数uid与token不填写，进行人脸识别认证
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssFaceVerifyTokenTestByNotExistParameterUidAndNotExistParameterToken() throws Exception {
        String businessServiceWzhrssFaceVerifyTokenUrl =null;
        BusinessServiceWzhrssFaceVerifyTokenBO businessServiceWzhrssFaceVerifyTokenBO =null;
        String businessServiceWzhrssFaceVerifyTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssFaceVerifyTokenUrl = url+"/BusinessService/wzhrss/faceverifytoken";
            businessServiceWzhrssFaceVerifyTokenBO = new BusinessServiceWzhrssFaceVerifyTokenBO();
            businessServiceWzhrssFaceVerifyTokenBO.setAppid("1.00002");
            businessServiceWzhrssFaceVerifyTokenBO.setIdCard("330324198907024490");
            businessServiceWzhrssFaceVerifyTokenBO.setName("徐列方1");
//            businessServiceWzhrssFaceVerifyTokenBO.setToken(hs.get("token"));
//            businessServiceWzhrssFaceVerifyTokenBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssFaceVerifyTokenUrl 请求的参数=" + businessServiceWzhrssFaceVerifyTokenUrl);
            log.info("businessServiceWzhrssFaceVerifyTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            businessServiceWzhrssFaceVerifyTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssFaceVerifyTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO), "UTF-8");
            log.info("businessServiceWzhrssFaceVerifyTokenResult 返回结果=" +businessServiceWzhrssFaceVerifyTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法=faceverifytoken配置在openMethod当中，参数uid与token不填写，进行人脸识别认证");
            recordhttp.setUrl(businessServiceWzhrssFaceVerifyTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssFaceVerifyTokenBO));
            recordhttp.setResponse(businessServiceWzhrssFaceVerifyTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("id"),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssFaceVerifyTokenResult.contains("\"result\":1"),true);
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
