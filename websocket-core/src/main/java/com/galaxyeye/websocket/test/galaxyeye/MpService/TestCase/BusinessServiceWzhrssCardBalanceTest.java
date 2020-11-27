package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:12
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssCardBalanceBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class BusinessServiceWzhrssCardBalanceTest extends BaseTest {

    /*
    查询电子钱包账户余额，返回单位是元
   */
    @Test
    public void businessServiceWzhrssCardBalanceTestByParameterAcckindValueIsOne() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardBalanceBO.setAcckind("01");
            businessServiceWzhrssCardBalanceBO.setCardno("32500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询电子钱包账户余额，返回单位是元");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /*
       查询市民卡的账户余额，返回单位是元
      */
    @Test
    public void businessServiceWzhrssCardBalanceTestByParameterAcckindValueIsTwo() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardBalanceBO.setAcckind("02");
            businessServiceWzhrssCardBalanceBO.setCardno("32500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询市民卡的账户余额，返回单位是元");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /*
       车改账户Acckind=06的账户余额查询，返回单位是元
      */
    @Test
    public void businessServiceWzhrssCardBalanceTestByParameterAcckindValueIsSix() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardBalanceBO.setAcckind("06");
            businessServiceWzhrssCardBalanceBO.setCardno("32500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("车改账户Acckind=06的账户余额查询，返回单位是元");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /*
     市民卡号Cardno必填参数校验
     */
    @Test
    public void businessServiceWzhrssCardBalanceTestByNotExistParameterCardno() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardBalanceBO.setAcckind("02");
//            businessServiceWzhrssCardBalanceBO.setCardno("32500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡号Cardno必填参数校验");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":101"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"cardno is empty\""),true);
        }
    }

    /**
     市民卡号不存在，查询余额
    **/
    @Test
    public void businessServiceWzhrssCardBalanceTestByParameterCardnoValueIsNotExist() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardBalanceBO.setAcckind("02");
            businessServiceWzhrssCardBalanceBO.setCardno("42500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡号不存在，查询余额");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":520"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"市民卡卡号验证失败,验证模式2\""),true);
        }
    }


    /**
     非必填参数Acckind
     **/
    @Test
    public void businessServiceWzhrssCardBalanceTestByNotExistParameterAcckind() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
//            businessServiceWzhrssCardBalanceBO.setAcckind("02");
            businessServiceWzhrssCardBalanceBO.setCardno("32500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非必填参数Acckind");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     参数Acckind值在规定范围之外
     **/
    @Test
    public void businessServiceWzhrssCardBalanceTestByParameterAcckindValueIsInvalidate() throws Exception {
        String businessServiceWzhrssCardBalanceUrl =null;
        BusinessServiceWzhrssCardBalanceBO businessServiceWzhrssCardBalanceBO =null;
        String businessServiceWzhrssCardBalanceResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardBalanceUrl = url+"/BusinessService/wzhrss/cardbalance";
            businessServiceWzhrssCardBalanceBO = new BusinessServiceWzhrssCardBalanceBO();
            businessServiceWzhrssCardBalanceBO.setAppid("1.00002");
            businessServiceWzhrssCardBalanceBO.setToken(hs.get("token"));
            businessServiceWzhrssCardBalanceBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardBalanceBO.setAcckind("9999");
            businessServiceWzhrssCardBalanceBO.setCardno("32500099110004695300");
            log.info("businessServiceWzhrssCardBalanceUrl 请求的参数=" + businessServiceWzhrssCardBalanceUrl);
            log.info("businessServiceWzhrssCardBalanceBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            businessServiceWzhrssCardBalanceResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardBalanceUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardBalanceBO), "UTF-8");
            log.info("businessServiceWzhrssCardBalanceResult 返回结果=" +businessServiceWzhrssCardBalanceResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Acckind值在规定范围之外");
            recordhttp.setUrl(businessServiceWzhrssCardBalanceUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardBalanceBO));
            recordhttp.setResponse(businessServiceWzhrssCardBalanceResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"msg\":\"acckind is wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssCardBalanceResult.contains("\"result\":101"),true);
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
