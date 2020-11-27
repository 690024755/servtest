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
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssBankListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssWithdrawCardListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssBankListDTO;
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
public class BusinessServiceWzhrssBankListTest extends BaseTest {

    @Autowired
    private BusinessServiceWzhrssCardListTest businessServiceWzhrssCardListTest;

    /**
     * 通用情况下，获取银行卡列表
     * @param businessServiceWzhrssBankListBO
     * @return
     * @throws Exception
     */
    public BusinessServiceWzhrssBankListDTO businessServiceWzhrssBankListTestByGernal(BusinessServiceWzhrssBankListBO businessServiceWzhrssBankListBO) throws Exception {
        String businessServiceWzhrssBankListUrl =null;
        String businessServiceWzhrssBankListResult ="";
        try{
            businessServiceWzhrssBankListUrl = url+"/BusinessService/wzhrss/banklist";
            log.info("businessServiceWzhrssBankListUrl 请求的参数=" + businessServiceWzhrssBankListUrl);
            log.info("businessServiceWzhrssBankListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssBankListBO));
            businessServiceWzhrssBankListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssBankListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssBankListBO), "UTF-8");
            log.info("businessServiceWzhrssBankListResult 返回结果=" +businessServiceWzhrssBankListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用情况下，获取银行卡列表");
            recordhttp.setUrl(businessServiceWzhrssBankListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssBankListBO));
            recordhttp.setResponse(businessServiceWzhrssBankListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"result\":1"),true);
            return JSON.parseObject(businessServiceWzhrssBankListResult, BusinessServiceWzhrssBankListDTO.class);
        }
    }


    /*
      获取银行列表信息
     */
    @Test
    public void businessServiceWzhrssBankListTestByNormalAndUidAndToken() throws Exception {
        String businessServiceWzhrssBankListUrl =null;
        String businessServiceWzhrssBankListResult ="";
        BusinessServiceWzhrssBankListBO businessServiceWzhrssBankListBO =null;
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssBankListUrl = url+"/BusinessService/wzhrss/banklist";
            businessServiceWzhrssBankListBO = new BusinessServiceWzhrssBankListBO();
            businessServiceWzhrssBankListBO.setAppid("1.00002");
            businessServiceWzhrssBankListBO.setToken(hs.get("token"));
            businessServiceWzhrssBankListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssBankListUrl 请求的参数=" + businessServiceWzhrssBankListUrl);
            log.info("businessServiceWzhrssBankListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssBankListBO));
            businessServiceWzhrssBankListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssBankListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssBankListBO), "UTF-8");
            log.info("businessServiceWzhrssBankListResult 返回结果=" +businessServiceWzhrssBankListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取银行列表信息");
            recordhttp.setUrl(businessServiceWzhrssBankListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssBankListBO));
            recordhttp.setResponse(businessServiceWzhrssBankListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"result\":1"),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssBankListResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
        }
    }


    /*
      配置信息为空，获取银行卡列表信息
     */
    @Test
    public void businessServiceWzhrssCardListTestByConfigBankListIsEmpty() throws Exception {
        String businessServiceWzhrssBankListUrl =null;
        String businessServiceWzhrssBankListResult ="";
        BusinessServiceWzhrssBankListBO businessServiceWzhrssBankListBO =null;
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssBankListUrl = url+"/BusinessService/wzhrss/banklist";
            businessServiceWzhrssBankListBO = new BusinessServiceWzhrssBankListBO();
            businessServiceWzhrssBankListBO.setAppid("1.00002");
            businessServiceWzhrssBankListBO.setToken(hs.get("token"));
            businessServiceWzhrssBankListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssBankListUrl 请求的参数=" + businessServiceWzhrssBankListUrl);
            log.info("businessServiceWzhrssBankListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssBankListBO));
            businessServiceWzhrssBankListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssBankListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssBankListBO), "UTF-8");
            log.info("businessServiceWzhrssBankListResult 返回结果=" +businessServiceWzhrssBankListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("配置信息为空，获取银行卡列表信息");
            recordhttp.setUrl(businessServiceWzhrssBankListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssBankListBO));
            recordhttp.setResponse(businessServiceWzhrssBankListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"result\":1"),true);
        }
    }

    /*
     银行列表未配置
    */
    @Test
    public void businessServiceWzhrssCardListTestByConfigBankListIsNotExist() throws Exception {
        String businessServiceWzhrssBankListUrl =null;
        String businessServiceWzhrssBankListResult ="";
        BusinessServiceWzhrssBankListBO businessServiceWzhrssBankListBO =null;
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssBankListUrl = url+"/BusinessService/wzhrss/banklist";
            businessServiceWzhrssBankListBO = new BusinessServiceWzhrssBankListBO();
            businessServiceWzhrssBankListBO.setAppid("1.00002");
            businessServiceWzhrssBankListBO.setToken(hs.get("token"));
            businessServiceWzhrssBankListBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssBankListUrl 请求的参数=" + businessServiceWzhrssBankListUrl);
            log.info("businessServiceWzhrssBankListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssBankListBO));
            businessServiceWzhrssBankListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssBankListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssBankListBO), "UTF-8");
            log.info("businessServiceWzhrssBankListResult 返回结果=" +businessServiceWzhrssBankListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("配置信息为空，获取银行卡列表信息");
            recordhttp.setUrl(businessServiceWzhrssBankListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssBankListBO));
            recordhttp.setResponse(businessServiceWzhrssBankListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"result\":1"),true);
        }
    }

    /*
     根据bmappid与accessToken获取银行列表
    */
    @Test
    public void businessServiceWzhrssCardListTestByBmappidAndAccessToken() throws Exception {
        String businessServiceWzhrssBankListUrl =null;
        String businessServiceWzhrssBankListResult ="";
        BusinessServiceWzhrssBankListBO businessServiceWzhrssBankListBO =null;
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssBankListUrl = url+"/BusinessService/wzhrss/banklist";
            businessServiceWzhrssBankListBO = new BusinessServiceWzhrssBankListBO();
            businessServiceWzhrssBankListBO.setAppid("1.00002");
            businessServiceWzhrssBankListBO.setBmAppid("1.00002");
            businessServiceWzhrssBankListBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("businessServiceWzhrssBankListUrl 请求的参数=" + businessServiceWzhrssBankListUrl);
            log.info("businessServiceWzhrssBankListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssBankListBO));
            businessServiceWzhrssBankListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssBankListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssBankListBO), "UTF-8");
            log.info("businessServiceWzhrssBankListResult 返回结果=" +businessServiceWzhrssBankListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据bmappid与accessToken获取银行列表");
            recordhttp.setUrl(businessServiceWzhrssBankListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssBankListBO));
            recordhttp.setResponse(businessServiceWzhrssBankListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("bank"),true);
            Assert.assertEquals(businessServiceWzhrssBankListResult.contains("no"),true);
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
