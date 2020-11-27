package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 9:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssCardStateBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class BusinessServiceWzhrssCardStateTest extends BaseTest {

    /**
     *正常情况下，查询卡的状态,返回的state表示卡的状态
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardStateTestByNormal() throws Exception {
        String businessServiceWzhrssCardStateUrl =null;
        BusinessServiceWzhrssCardStateBO businessServiceWzhrssCardStateBO =null;
        String businessServiceWzhrssCardStateResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardStateUrl = url+"/BusinessService/wzhrss/cardstate";
            businessServiceWzhrssCardStateBO = new BusinessServiceWzhrssCardStateBO();
            businessServiceWzhrssCardStateBO.setAppid("1.00002");
            businessServiceWzhrssCardStateBO.setCardno("32500099110004695300");
            businessServiceWzhrssCardStateBO.setToken(hs.get("token"));
            businessServiceWzhrssCardStateBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssCardStateUrl 请求的参数=" + businessServiceWzhrssCardStateUrl);
            log.info("businessServiceWzhrssCardStateBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardStateBO));
            businessServiceWzhrssCardStateResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardStateUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardStateBO), "UTF-8");
            log.info("businessServiceWzhrssCardStateResult 返回结果=" +businessServiceWzhrssCardStateResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正常情况下，可提现账户转出，发送短信");
            recordhttp.setUrl(businessServiceWzhrssCardStateUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardStateBO));
            recordhttp.setResponse(businessServiceWzhrssCardStateResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("name"),true);
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("state"),true);
        }
    }

    /**
     *市民卡卡号的值不存在
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardStateTestByParameterCardnoValueIsNotExist() throws Exception {
        String businessServiceWzhrssCardStateUrl =null;
        BusinessServiceWzhrssCardStateBO businessServiceWzhrssCardStateBO =null;
        String businessServiceWzhrssCardStateResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardStateUrl = url+"/BusinessService/wzhrss/cardstate";
            businessServiceWzhrssCardStateBO = new BusinessServiceWzhrssCardStateBO();
            businessServiceWzhrssCardStateBO.setAppid("1.00002");
            businessServiceWzhrssCardStateBO.setCardno("XXXXXXXX");
            businessServiceWzhrssCardStateBO.setToken(hs.get("token"));
            businessServiceWzhrssCardStateBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssCardStateUrl 请求的参数=" + businessServiceWzhrssCardStateUrl);
            log.info("businessServiceWzhrssCardStateBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardStateBO));
            businessServiceWzhrssCardStateResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardStateUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardStateBO), "UTF-8");
            log.info("businessServiceWzhrssCardStateResult 返回结果=" +businessServiceWzhrssCardStateResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("不存在市民卡卡号查询");
            recordhttp.setUrl(businessServiceWzhrssCardStateUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardStateBO));
            recordhttp.setResponse(businessServiceWzhrssCardStateResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("\"msg\":\"市民卡卡号验证失败,验证模式2\""),true);
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("\"result\":520"),true);
        }
    }


    /**
     *必填参数Cardno市民卡卡号校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardStateTestByNotExistParameterCardno() throws Exception {
        String businessServiceWzhrssCardStateUrl =null;
        BusinessServiceWzhrssCardStateBO businessServiceWzhrssCardStateBO =null;
        String businessServiceWzhrssCardStateResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardStateUrl = url+"/BusinessService/wzhrss/cardstate";
            businessServiceWzhrssCardStateBO = new BusinessServiceWzhrssCardStateBO();
            businessServiceWzhrssCardStateBO.setAppid("1.00002");
//            businessServiceWzhrssCardStateBO.setCardno("32500099110004695300");
            businessServiceWzhrssCardStateBO.setToken(hs.get("token"));
            businessServiceWzhrssCardStateBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssCardStateUrl 请求的参数=" + businessServiceWzhrssCardStateUrl);
            log.info("businessServiceWzhrssCardStateBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardStateBO));
            businessServiceWzhrssCardStateResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardStateUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardStateBO), "UTF-8");
            log.info("businessServiceWzhrssCardStateResult 返回结果=" +businessServiceWzhrssCardStateResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数市民卡卡号校验");
            recordhttp.setUrl(businessServiceWzhrssCardStateUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardStateBO));
            recordhttp.setResponse(businessServiceWzhrssCardStateResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("\"msg\":\"cardno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssCardStateResult.contains("\"result\":101"),true);
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
