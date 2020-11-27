package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:09
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssWithDrawAccountBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceWzhrssWithDrawAccountDTO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class BusinessServiceWzhrssWithDrawAccountTest extends BaseTest {

    /**
     * 通用查询可提现金额接口
     * @throws Exception
     */
    public BusinessServiceWzhrssWithDrawAccountDTO businessServiceWzhrssWithDrawAccountTestByGernal(BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO) throws Exception {
        String businessServiceWzhrssWithDrawAccountUrl =null;
        String businessServiceWzhrssWithDrawAccountResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithDrawAccountUrl = url+"/BusinessService/wzhrss/withdrawaccount";
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("06");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawAccountUrl 请求的参数=" + businessServiceWzhrssWithDrawAccountUrl);
            log.info("businessServiceWzhrssWithDrawAccountBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            businessServiceWzhrssWithDrawAccountResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawAccountUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawAccountResult 返回结果=" +businessServiceWzhrssWithDrawAccountResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用查询可提现金额接口");
            recordhttp.setUrl(businessServiceWzhrssWithDrawAccountUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawAccountResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankcardno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"result\":1"),true);
            return JSON.parseObject(businessServiceWzhrssWithDrawAccountResult, BusinessServiceWzhrssWithDrawAccountDTO.class);
        }
    }


    /**
     *账户可提现金额查询,使用电子钱包查询可提现金额
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawAccountTestByParameterAcckindValueIsOneAndExistParameterCardno() throws Exception {
        String businessServiceWzhrssWithDrawAccountUrl =null;
        BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO =null;
        String businessServiceWzhrssWithDrawAccountResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithDrawAccountUrl = url+"/BusinessService/wzhrss/withdrawaccount";
            businessServiceWzhrssWithDrawAccountBO = new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("01");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawAccountUrl 请求的参数=" + businessServiceWzhrssWithDrawAccountUrl);
            log.info("businessServiceWzhrssWithDrawAccountBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            businessServiceWzhrssWithDrawAccountResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawAccountUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawAccountResult 返回结果=" +businessServiceWzhrssWithDrawAccountResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("账户可提现金额查询,使用电子钱包查询可提现金额");
            recordhttp.setUrl(businessServiceWzhrssWithDrawAccountUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawAccountResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"msg\":\"\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"result\":520"),true);
        }
    }

    /**
     *账户可提现金额查询,使用市民卡查询可提现金额
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawAccountTestByParameterAcckindValueIsTwoAndExistParameterCardno() throws Exception {
        String businessServiceWzhrssWithDrawAccountUrl =null;
        BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO =null;
        String businessServiceWzhrssWithDrawAccountResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithDrawAccountUrl = url+"/BusinessService/wzhrss/withdrawaccount";
            businessServiceWzhrssWithDrawAccountBO = new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("02");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawAccountUrl 请求的参数=" + businessServiceWzhrssWithDrawAccountUrl);
            log.info("businessServiceWzhrssWithDrawAccountBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            businessServiceWzhrssWithDrawAccountResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawAccountUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawAccountResult 返回结果=" +businessServiceWzhrssWithDrawAccountResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("账户可提现金额查询,使用市民卡查询可提现金额");
            recordhttp.setUrl(businessServiceWzhrssWithDrawAccountUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawAccountResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankcardno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"result\":1"),true);
        }
    }

    /**
     *账户可提现金额查询,使用车改户查询可提现金额
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawAccountTestByParameterAcckindValueIsSixAndExistParameterCardno() throws Exception {
        String businessServiceWzhrssWithDrawAccountUrl =null;
        BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO =null;
        String businessServiceWzhrssWithDrawAccountResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithDrawAccountUrl = url+"/BusinessService/wzhrss/withdrawaccount";
            businessServiceWzhrssWithDrawAccountBO = new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("06");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawAccountUrl 请求的参数=" + businessServiceWzhrssWithDrawAccountUrl);
            log.info("businessServiceWzhrssWithDrawAccountBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            businessServiceWzhrssWithDrawAccountResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawAccountUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawAccountResult 返回结果=" +businessServiceWzhrssWithDrawAccountResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("账户可提现金额查询,使用车改户查询可提现金额");
            recordhttp.setUrl(businessServiceWzhrssWithDrawAccountUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawAccountResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankcardno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"result\":1"),true);
        }
    }


    /**
     *账户可提现金额查询,必填参数Acckind校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawAccountTestByNotExistParameterAcckindAndExistParameterCardno() throws Exception {
        String businessServiceWzhrssWithDrawAccountUrl =null;
        BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO =null;
        String businessServiceWzhrssWithDrawAccountResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithDrawAccountUrl = url+"/BusinessService/wzhrss/withdrawaccount";
            businessServiceWzhrssWithDrawAccountBO = new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
//            businessServiceWzhrssWithDrawAccountBO.setAcckind("01");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawAccountUrl 请求的参数=" + businessServiceWzhrssWithDrawAccountUrl);
            log.info("businessServiceWzhrssWithDrawAccountBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            businessServiceWzhrssWithDrawAccountResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawAccountUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawAccountResult 返回结果=" +businessServiceWzhrssWithDrawAccountResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("账户可提现金额查询,必填参数Acckind校验");
            recordhttp.setUrl(businessServiceWzhrssWithDrawAccountUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawAccountResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankcardno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("bankno"),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"result\":1"),true);
        }
    }


    /**
     *账户可提现金额查询,必填参数Cardno校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssWithDrawAccountTestByExistParameterAcckindAndNotExistParameterCardno() throws Exception {
        String businessServiceWzhrssWithDrawAccountUrl =null;
        BusinessServiceWzhrssWithDrawAccountBO businessServiceWzhrssWithDrawAccountBO =null;
        String businessServiceWzhrssWithDrawAccountResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssWithDrawAccountUrl = url+"/BusinessService/wzhrss/withdrawaccount";
            businessServiceWzhrssWithDrawAccountBO = new BusinessServiceWzhrssWithDrawAccountBO();
            businessServiceWzhrssWithDrawAccountBO.setAppid("1.00002");
            businessServiceWzhrssWithDrawAccountBO.setBmAppid("1.00002");
//            businessServiceWzhrssWithDrawAccountBO.setCardno("32500099110004695300");
            businessServiceWzhrssWithDrawAccountBO.setAcckind("01");
            businessServiceWzhrssWithDrawAccountBO.setToken(hs.get("token"));
            businessServiceWzhrssWithDrawAccountBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssWithDrawAccountUrl 请求的参数=" + businessServiceWzhrssWithDrawAccountUrl);
            log.info("businessServiceWzhrssWithDrawAccountBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            businessServiceWzhrssWithDrawAccountResult = HttpUtil.postGeneralUrl(businessServiceWzhrssWithDrawAccountUrl, "application/json", JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO), "UTF-8");
            log.info("businessServiceWzhrssWithDrawAccountResult 返回结果=" +businessServiceWzhrssWithDrawAccountResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("账户可提现金额查询,必填参数Acckind校验");
            recordhttp.setUrl(businessServiceWzhrssWithDrawAccountUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssWithDrawAccountBO));
            recordhttp.setResponse(businessServiceWzhrssWithDrawAccountResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"msg\":\"cardno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssWithDrawAccountResult.contains("\"result\":101"),true);
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
