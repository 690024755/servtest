package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:07
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/25日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssUnstartedCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssWithdrawCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssUnstartedCardListDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
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
public class BusinessServiceWzhrssWithdrawCardListTest extends BaseTest {

    @Autowired
    private BusinessServiceWzhrssCardListTest businessServiceWzhrssCardListTest;

    public String businessServiceWzhrssWithdrawCardListTestByGernal(BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO) throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssUnstartedCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用情况下，待启用市民卡查询");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("state"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":1"),true);
            return businessServiceWzhrssWithdrawCardListResult;
        }
    }


    /*
    正常情况下，查询主卡且卡状态为1且密码Wie启用状态的卡列表信息
     */
    @Test
    public void businessServiceWzhrssCardListTestByNormal() throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        HashMap<String, String> hs= userLoginTest();

        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
        businessServiceWzhrssCardListBO.setAppid("1.00002");
        businessServiceWzhrssCardListBO.setCertno("330324198907024490");
        businessServiceWzhrssCardListBO.setCerttype("01");
        businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
        businessServiceWzhrssCardListBO.setToken(hs.get("token"));
        String businessServiceWzhrssCardListResult=businessServiceWzhrssCardListTest.businessServiceWzhrssCardListTestByGernal(businessServiceWzhrssCardListBO);
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            businessServiceWzhrssWithdrawCardListBO = new BusinessServiceWzhrssWithdrawCardListBO();
            businessServiceWzhrssWithdrawCardListBO.setAppid("1.00002");
            businessServiceWzhrssWithdrawCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssWithdrawCardListBO.setCerttype("01");
            businessServiceWzhrssWithdrawCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssWithdrawCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssWithdrawCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常情况下，查询主卡且卡状态为1且密码Wie启用状态的卡列表信息");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("state"),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":1"),true);
            BusinessServiceWzhrssUnstartedCardListDTO businessServiceWzhrssUnstartedCardListDTO=JSON.parseObject(businessServiceWzhrssWithdrawCardListResult, BusinessServiceWzhrssUnstartedCardListDTO.class);
            List<BusinessServiceWzhrssUnstartedCardListDTO.ListBean> listBean=businessServiceWzhrssUnstartedCardListDTO.getList();
            for (BusinessServiceWzhrssUnstartedCardListDTO.ListBean bean:listBean
                 ) {
                Assert.assertTrue(bean.getState().equals("正常"));
            }
        }
    }


    /*
    身份证类型错误，返回身份证类型错误信息
     */
    @Test
    public void businessServiceWzhrssCardListTestByParameterCerttypeValueIsWrong() throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            businessServiceWzhrssWithdrawCardListBO = new BusinessServiceWzhrssWithdrawCardListBO();
            businessServiceWzhrssWithdrawCardListBO.setAppid("1.00002");
            businessServiceWzhrssWithdrawCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssWithdrawCardListBO.setCerttype("99");
            businessServiceWzhrssWithdrawCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssWithdrawCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssWithdrawCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("身份证类型错误，返回身份证类型错误信息");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"certtype wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":101"),true);
        }
    }

    /*
       无效身份证卡号，查询市民卡信息
     */
    @Test
    public void businessServiceWzhrssCardListTestByParameterCertnoValueIsInvidate() throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            businessServiceWzhrssWithdrawCardListBO = new BusinessServiceWzhrssWithdrawCardListBO();
            businessServiceWzhrssWithdrawCardListBO.setAppid("1.00002");
            businessServiceWzhrssWithdrawCardListBO.setCertno("11111112");
            businessServiceWzhrssWithdrawCardListBO.setCerttype("01");
            businessServiceWzhrssWithdrawCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssWithdrawCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssWithdrawCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("无效身份证卡号，查询市民卡信息");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"未找到任何卡信息\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":520"),true);
        }
    }

    /*
     不是测试身份证号码但是真实身份证号码，查询市民卡信息
    */
    @Test
    public void businessServiceWzhrssCardListTestByParameterCertnoValueIsNotTest() throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            businessServiceWzhrssWithdrawCardListBO = new BusinessServiceWzhrssWithdrawCardListBO();
            businessServiceWzhrssWithdrawCardListBO.setAppid("1.00002");
            businessServiceWzhrssWithdrawCardListBO.setCertno("31011019830726325X");
            businessServiceWzhrssWithdrawCardListBO.setCerttype("01");
            businessServiceWzhrssWithdrawCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssWithdrawCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssWithdrawCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不是测试身份证号码但是真实身份证号码，查询市民卡信息");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"未找到任何卡信息\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":520"),true);
        }
    }

    /*
     必填参数Certno校验
    */
    @Test
    public void businessServiceWzhrssCardListTestByNotExistParameterCertno() throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            businessServiceWzhrssWithdrawCardListBO = new BusinessServiceWzhrssWithdrawCardListBO();
            businessServiceWzhrssWithdrawCardListBO.setAppid("1.00002");
//            businessServiceWzhrssWithdrawCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssWithdrawCardListBO.setCerttype("01");
            businessServiceWzhrssWithdrawCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssWithdrawCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssWithdrawCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Certno校验");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"certno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":101"),true);
        }
    }

    /*
     必填参数Certtype校验
    */
    @Test
    public void businessServiceWzhrssCardListTestByNotExistParameterCerttype() throws Exception {
        String businessServiceWzhrssWithdrawCardListUrl =null;
        BusinessServiceWzhrssWithdrawCardListBO businessServiceWzhrssWithdrawCardListBO =null;
        String businessServiceWzhrssWithdrawCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithdrawCardListUrl = url+"/BusinessService/wzhrss/withdrawcardlist";
            businessServiceWzhrssWithdrawCardListBO = new BusinessServiceWzhrssWithdrawCardListBO();
            businessServiceWzhrssWithdrawCardListBO.setAppid("1.00002");
            businessServiceWzhrssWithdrawCardListBO.setCertno("330324198907024490");
//            businessServiceWzhrssWithdrawCardListBO.setCerttype("01");
            businessServiceWzhrssWithdrawCardListBO.setToken(hs.get("token"));
            businessServiceWzhrssWithdrawCardListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithdrawCardListUrl 请求的参数=" + businessServiceWzhrssWithdrawCardListUrl);
            log.info("businessServiceWzhrssWithdrawCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            businessServiceWzhrssWithdrawCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithdrawCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO), "UTF-8");
            log.info("businessServiceWzhrssWithdrawCardListResult 返回结果=" +businessServiceWzhrssWithdrawCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Certtype校验");
            recordhttp.setUrl(businessServiceWzhrssWithdrawCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithdrawCardListBO));
            recordhttp.setResponse(businessServiceWzhrssWithdrawCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"msg\":\"certtype is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssWithdrawCardListResult.contains("\"result\":101"),true);
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
