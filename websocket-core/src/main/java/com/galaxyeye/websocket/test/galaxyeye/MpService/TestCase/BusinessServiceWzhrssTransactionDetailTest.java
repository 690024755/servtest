package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 13:42
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/25日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssTransactionDetailBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class BusinessServiceWzhrssTransactionDetailTest extends BaseTest {

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=5
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByNormal() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }

    /**
     * 必填参数count校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOfflineAndNotExistParameterCount() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
//            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数count校验");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"index/count错误\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":520"),true);
        }
    }


    /**
     * 必填参数index校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOfflineAndNotExistParameterIndex() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
//            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数index校验");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"index/count错误\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":520"),true);
        }
    }


    /**
     * 必填参数Enddate校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOfflineAndNotExistParameterEnddate() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
//            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Enddate校验");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"enddate format wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 必填参数Startdate校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOfflineAndNotExistParameterStartdate() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
//            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Startdate校验");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"startdate format wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 参数Acckind不传则查询电子钱包与市民卡的这2种类型的交易明细，参数rectype=0,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndNotExistParameterAcckindAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
//            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Acckind不传则查询电子钱包与市民卡的这2种类型的交易明细，参数rectype=0,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，参数不传rectype，默认查询全部类型,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByNotExistParameterRectypeAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
//            businessServiceWzhrssTransactionDetailBO.setRectype(6);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，参数不传rectype，默认查询全部类型,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"公交集团测试\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=6，查询不存在类型,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsSixAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(6);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，rectype=6，查询不存在类型,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"rectype is wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=5，查询退现,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsFiveAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(5);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，rectype=5，查询退现,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=4，查询提现,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsFourAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(4);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，rectype=4，查询提现,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=3，查询充值,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsThreeAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(3);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，rectype=3，查询充值,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=2，查询退货,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsTwoAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(2);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，rectype=1，查询退货,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=1，查询消费,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsOneAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(1);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=01，rectype=1，查询消费,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"公交集团测试\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }


    /**
     * 电子钱包查询交易明细Acckind=01，rectype=0，查询全部,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOfflineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("01");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("电子钱包查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"公交集团测试\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }



    /**
     * 市民卡账号查询交易明细Acckind=02，请求参数rectype不传则默认查询全部,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByNotExistParameterRectypeAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
//            businessServiceWzhrssTransactionDetailBO.setRectype(1);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，请求参数rectype不传则默认查询全部,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=6，查询不存在业务类型,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByNotExistParameterRectypeValueIsSixAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(6);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=6，查询不存在业务类型,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"rectype is wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=5，查询退现,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsFiveAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(5);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=5，查询退现,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=4，查询提现,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsFourAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(4);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=4，查询提现,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=3，查询充值,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsThreeAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(3);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=3，查询充值,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=2，查询退货,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsTwoAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(2);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200621");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200921");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=2，查询退货,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=1，查询消费,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsOneAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(1);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=1，查询消费,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间差小于等于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间差小于等于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间差大于3个月
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateMoreThanThreeMonths() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("201221");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间差大于3个月");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"日期间隔只能在三个月内，请检查\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":520"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间一样，税务交易信息返回空
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndNotExistTransactionDetail() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200521");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,开始时间与结束时间的时间一样，税务交易信息返回空");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=0
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterIndexVulueIsZero() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(0);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=0");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"index/count错误\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":520"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=1
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterIndexVulueIsOne() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=1");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterIndexVulueIsOneHundred() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(100);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"list\":[]"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=-10
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterCountVulueIsNegativeTen() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(-10);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"count value is wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=0
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterCountVulueIsZero() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(0);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"index/count错误\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":520"),true);
        }
    }


    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=5
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterCountVulueIsFive() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
        }
    }

    /**
     * 市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=100
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsOnlineAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterCountVulueIsOneHundred() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("02");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(100);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡账号查询交易明细Acckind=02，rectype=0，查询全部,结束时间与开始时间差小于3个月且Index=100");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"count value is wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 未知账号查询交易明细Acckind=9999的值在规定值之外，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=5
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsInvlidateAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterCountVulueIsOneHundred() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("9999");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("未知账号查询交易明细Acckind=9999的值在规定值之外，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=5");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"acckind is wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":101"),true);
        }
    }


    /**
     * 车改户账号查询交易明细Acckind=06，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=5
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssTransactionDetailTestByParameterRectypeValueIsZeroAndParameterAcckindValueIsSixAndDiffEnddateAndStartdateLessThanThreeMonthsAndParameterCountVulueIsOneHundred() throws Exception {
        String businessServiceWzhrssTransactionDetailUrl =null;
        BusinessServiceWzhrssTransactionDetailBO businessServiceWzhrssTransactionDetailBO =null;
        String businessServiceWzhrssTransactionDetailResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssTransactionDetailUrl = url+"/BusinessService/wzhrss/transactiondetail";
            businessServiceWzhrssTransactionDetailBO = new BusinessServiceWzhrssTransactionDetailBO();
            businessServiceWzhrssTransactionDetailBO.setAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setBmAppid("1.00002");
            businessServiceWzhrssTransactionDetailBO.setCardno("32500099110004695300");
            businessServiceWzhrssTransactionDetailBO.setRectype(0);
            businessServiceWzhrssTransactionDetailBO.setToken(hs.get("token"));
            businessServiceWzhrssTransactionDetailBO.setUid(hs.get("uid"));
            businessServiceWzhrssTransactionDetailBO.setAcckind("06");
            businessServiceWzhrssTransactionDetailBO.setStartdate("200521");
            businessServiceWzhrssTransactionDetailBO.setEnddate("200821");
            businessServiceWzhrssTransactionDetailBO.setIndex(1);
            businessServiceWzhrssTransactionDetailBO.setCount(5);
            log.info("businessServiceWzhrssTransactionDetailUrl 请求的参数=" + businessServiceWzhrssTransactionDetailUrl);
            log.info("businessServiceWzhrssTransactionDetailBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            businessServiceWzhrssTransactionDetailResult = HttpUtil.postGeneralUrl(businessServiceWzhrssTransactionDetailUrl, "application/json", JSON.toJSONString(businessServiceWzhrssTransactionDetailBO), "UTF-8");
            log.info("businessServiceWzhrssTransactionDetailResult 返回结果=" +businessServiceWzhrssTransactionDetailResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("车改户账号查询交易明细Acckind=06，rectype=0，查询全部,结束时间与开始时间差小于3个月,Index=1且Count=5");
            recordhttp.setUrl(businessServiceWzhrssTransactionDetailUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssTransactionDetailBO));
            recordhttp.setResponse(businessServiceWzhrssTransactionDetailResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("list"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"result\":1"),true);
            //市民卡返回值是十足测试商户2，电子钱包返回的是公交集团测试
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("\"location\":\"十足测试商户2\""),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssTransactionDetailResult,"$.list[*]");
            Assert.assertTrue(list.size()>0);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("amount"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("balance"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("time"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("date"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("type"),true);
            Assert.assertEquals(businessServiceWzhrssTransactionDetailResult.contains("location"),true);
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
