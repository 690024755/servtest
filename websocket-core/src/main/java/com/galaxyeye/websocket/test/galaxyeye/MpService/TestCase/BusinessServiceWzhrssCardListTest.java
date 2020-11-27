package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 14:29
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssCardListBO;
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
public class BusinessServiceWzhrssCardListTest  extends BaseTest {
    /**
     * 通用接口，获取卡的列表信息
     * @throws Exception
     */
    public String businessServiceWzhrssCardListTestByGernal(BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO) throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        String businessServiceWzhrssCardListResult ="";
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通用接口，获取卡的列表信息");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("list"),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssCardListResult,"$.list[*]");
            if(list.size()>0){
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("no"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("insurance"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("type"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("state"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("balance"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("binding"),true);
            }
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":1"),true);
            return businessServiceWzhrssCardListResult;
        }
    }


    /**
     * 正确的身份证号码与身份证类型
     * 返回的金额字段转为元
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardListTest() throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO =null;
        String businessServiceWzhrssCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
            businessServiceWzhrssCardListBO.setAppid("1.00002");
            businessServiceWzhrssCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssCardListBO.setCerttype("01");
            businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardListBO.setToken(hs.get("token"));
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("正确的身份证号码与身份证类型");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("list"),true);
            List<Object> list=JsonPath.read(businessServiceWzhrssCardListResult,"$.list[*]");
            if(list.size()>0){
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("no"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("insurance"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("type"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("state"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("balance"),true);
                Assert.assertEquals(businessServiceWzhrssCardListResult.contains("binding"),true);
            }
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 身份证类型传递错误
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardListTestByParameterCerttypeValueIsInvalid () throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO =null;
        String businessServiceWzhrssCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
            businessServiceWzhrssCardListBO.setAppid("1.00002");
            businessServiceWzhrssCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssCardListBO.setCerttype("999");
            businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardListBO.setToken(hs.get("token"));
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("身份证类型传递错误");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"certtype wrong\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 身份证号码不符合格式
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardListTestByParameterCertnoValueIsInvalid () throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO =null;
        String businessServiceWzhrssCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
            businessServiceWzhrssCardListBO.setAppid("1.00002");
            businessServiceWzhrssCardListBO.setCertno("99330324198907024490");
            businessServiceWzhrssCardListBO.setCerttype("01");
            businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardListBO.setToken(hs.get("token"));
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("身份证号码不符合格式");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"未找到任何卡信息\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":520"),true);
        }
    }

    /**
     * 非测试身份证号码,无法调用，返回本服务错误信息：WZHRSS_remote_error
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardListTestByParameterCertnoValueIsNotTest() throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO =null;
        String businessServiceWzhrssCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
            businessServiceWzhrssCardListBO.setAppid("1.00002");
            businessServiceWzhrssCardListBO.setCertno("31011019830726325X");
            businessServiceWzhrssCardListBO.setCerttype("01");
            businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardListBO.setToken(hs.get("token"));
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("非测试身份证号码,无法调用，返回本服务错误信息：WZHRSS_remote_error");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"未找到任何卡信息\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":520"),true);
        }
    }

    /**
     * 必填参数certno校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardListTestByNotExistParameterCertno() throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO =null;
        String businessServiceWzhrssCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
            businessServiceWzhrssCardListBO.setAppid("1.00002");
//            businessServiceWzhrssCardListBO.setCertno("330324198907024490");
            businessServiceWzhrssCardListBO.setCerttype("01");
            businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardListBO.setToken(hs.get("token"));
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数certno校验");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"certno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 必填参数Certtype校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssCardListTestByNotExistParameterCerttype() throws Exception {
        String businessServiceWzhrssCardListUrl =null;
        BusinessServiceWzhrssCardListBO businessServiceWzhrssCardListBO =null;
        String businessServiceWzhrssCardListResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssCardListUrl = url+"/BusinessService/wzhrss/cardlist";
            businessServiceWzhrssCardListBO = new BusinessServiceWzhrssCardListBO();
            businessServiceWzhrssCardListBO.setAppid("1.00002");
            businessServiceWzhrssCardListBO.setCertno("330324198907024490");
//            businessServiceWzhrssCardListBO.setCerttype("01");
            businessServiceWzhrssCardListBO.setUid(hs.get("uid"));
            businessServiceWzhrssCardListBO.setToken(hs.get("token"));
            log.info("businessServiceWzhrssCardListUrl 请求的参数=" + businessServiceWzhrssCardListUrl);
            log.info("businessServiceWzhrssCardListBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssCardListBO));
            businessServiceWzhrssCardListResult = HttpUtil.postGeneralUrl(businessServiceWzhrssCardListUrl, "application/json", JSON.toJSONString(businessServiceWzhrssCardListBO), "UTF-8");
            log.info("businessServiceWzhrssCardListResult 返回结果=" +businessServiceWzhrssCardListResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Certtype校验");
            recordhttp.setUrl(businessServiceWzhrssCardListUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssCardListBO));
            recordhttp.setResponse(businessServiceWzhrssCardListResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"msg\":\"certtype is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssCardListResult.contains("\"result\":101"),true);
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
