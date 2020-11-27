package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:07
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/25日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssUnstartedCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssUnstartedCardListDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
@Slf4j
public class BusinessServiceWzhrssUnstartedCardListTest extends BaseTest {

    @Autowired
    private BusinessServiceWzhrssCardListTest businessServiceWzhrssCardListTest;

    @Test
    public void businessServiceWzhrssUnstartedCardListTestByGernal(BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO) throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用情况下，待启用市民卡查询");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("state"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":1"),true);
        }
    }


    /*
    正常情况下，待启用市民卡查询,返回的卡都是正常状态的卡
     */
    @Test
    public void businessServiceWzhrssUnstartedCardListTestByNormal() throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        HashMap<String, String> hs= userLoginTest();

        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
        businessServiceWzhrssCardListBO.setAppid("1.00002");
        businessServiceWzhrssCardListBO.setCertno("330324198907024490");
        businessServiceWzhrssCardListBO.setCerttype("01");
        businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
        businessServiceWzhrssCardListBO.setToken(hs.get("token"));
        String businessServiceWzhrssCardListResult=businessServiceWzhrssCardListTest.businessServiceWzhrssCardListTestByGernal(businessServiceWzhrssCardListBO);
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            businessServiceWzhrssUnstartedCardListBO = new BusinessServiceWzhrssUnstartedCardListBO();
            businessServiceWzhrssUnstartedCardListBO.setAppid("1.00002");
            businessServiceWzhrssUnstartedCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssUnstartedCardListBO.setCerttype("01");
            businessServiceWzhrssUnstartedCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssUnstartedCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常情况下，待启用市民卡查询");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("state"),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":1"),true);
            BusinessServiceWzhrssUnstartedCardListDTO businessServiceWzhrssUnstartedCardListDTO=JSON.parseObject(businessServiceWzhrssUnstartedCardListResult, BusinessServiceWzhrssUnstartedCardListDTO.class);
            List<BusinessServiceWzhrssUnstartedCardListDTO.ListBean> listBean=businessServiceWzhrssUnstartedCardListDTO.getList();
            for (BusinessServiceWzhrssUnstartedCardListDTO.ListBean bean:listBean
                 ) {
                Assert.assertTrue(bean.getState().equals("正常"));
            }
        }
    }




    /**
     * 身份证类型错误
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssUnstartedCardListTestByParameterCerttypeValueIsInvalidate() throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            businessServiceWzhrssUnstartedCardListBO = new BusinessServiceWzhrssUnstartedCardListBO();
            businessServiceWzhrssUnstartedCardListBO.setAppid("1.00002");
            businessServiceWzhrssUnstartedCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssUnstartedCardListBO.setCerttype("99");
            businessServiceWzhrssUnstartedCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssUnstartedCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("身份证类型错误");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"certtype wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 身份证号码错误
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssUnstartedCardListTestByParameterCertnoValueIsInvalidate() throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            businessServiceWzhrssUnstartedCardListBO = new BusinessServiceWzhrssUnstartedCardListBO();
            businessServiceWzhrssUnstartedCardListBO.setAppid("1.00002");
            businessServiceWzhrssUnstartedCardListBO.setCertno("198907024490");
            businessServiceWzhrssUnstartedCardListBO.setCerttype("01");
            businessServiceWzhrssUnstartedCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssUnstartedCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("身份证类型错误");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"未找到任何卡信息\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":520"),true);
        }
    }

    /**
     * 非测试身份证号码获取待启用的市民卡查询
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssUnstartedCardListTestByParameterCertnoValueIsNotTest() throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            businessServiceWzhrssUnstartedCardListBO = new BusinessServiceWzhrssUnstartedCardListBO();
            businessServiceWzhrssUnstartedCardListBO.setAppid("1.00002");
            businessServiceWzhrssUnstartedCardListBO.setCertno("31011019830726325X");
            businessServiceWzhrssUnstartedCardListBO.setCerttype("01");
            businessServiceWzhrssUnstartedCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssUnstartedCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非测试身份证号码获取待启用的市民卡查询");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"未找到任何卡信息\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":520"),true);
        }
    }

    /**
     * 必填参数Certno校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssUnstartedCardListTestByNotExistParameterCertno() throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            businessServiceWzhrssUnstartedCardListBO = new BusinessServiceWzhrssUnstartedCardListBO();
            businessServiceWzhrssUnstartedCardListBO.setAppid("1.00002");
//            businessServiceWzhrssUnstartedCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssUnstartedCardListBO.setCerttype("01");
            businessServiceWzhrssUnstartedCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssUnstartedCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Certno校验");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"certno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 必填参数Certtype校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssUnstartedCardListTestByNotExistParameterCerttype() throws Exception {
        String businessServiceWzhrssUnstartedCardListUrl =null;
        BusinessServiceWzhrssUnstartedCardListBO businessServiceWzhrssUnstartedCardListBO =null;
        String businessServiceWzhrssUnstartedCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssUnstartedCardListUrl = url+"/BusinessService/wzhrss/unstartedcardlist";
            businessServiceWzhrssUnstartedCardListBO = new BusinessServiceWzhrssUnstartedCardListBO();
            businessServiceWzhrssUnstartedCardListBO.setAppid("1.00002");
            businessServiceWzhrssUnstartedCardListBO.setCertno("330324198907024490");
///            businessServiceWzhrssUnstartedCardListBO.setCerttype("01");
            businessServiceWzhrssUnstartedCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssUnstartedCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssUnstartedCardListUrl 请求的参数=" + businessServiceWzhrssUnstartedCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            businessServiceWzhrssUnstartedCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssUnstartedCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO), "UTF-8");
            log.info("businessServiceWzhrssUnstartedCardListResult 返回结果=" +businessServiceWzhrssUnstartedCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Certtype校验");
            recordhttp.setUrl(businessServiceWzhrssUnstartedCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssUnstartedCardListBO));
            recordhttp.setResponse(businessServiceWzhrssUnstartedCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"msg\":\"certtype is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssUnstartedCardListResult.contains("\"result\":101"),true);
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
