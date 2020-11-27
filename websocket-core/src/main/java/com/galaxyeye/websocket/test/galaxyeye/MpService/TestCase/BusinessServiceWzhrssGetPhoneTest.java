package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:08
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/28日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssGetPhoneBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class BusinessServiceWzhrssGetPhoneTest extends BaseTest {

    /**
     * 市民卡已绑定手机号码，则返回手机号码
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssGetPhoneTestByBindPhone() throws Exception {
        String businessServiceWzhrssGetPhoneUrl =null;
        BusinessServiceWzhrssGetPhoneBO businessServiceWzhrssGetPhoneBO =null;
        String businessServiceWzhrssGetPhoneResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssGetPhoneUrl = url+"/BusinessService/wzhrss/getphone";
            businessServiceWzhrssGetPhoneBO = new BusinessServiceWzhrssGetPhoneBO();
            businessServiceWzhrssGetPhoneBO.setAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setBmAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setCardno("32500099110004695300");
            businessServiceWzhrssGetPhoneBO.setToken(hs.get("token"));
            businessServiceWzhrssGetPhoneBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssGetPhoneUrl 请求的参数=" + businessServiceWzhrssGetPhoneUrl);
            log.info("businessServiceWzhrssGetPhoneBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            businessServiceWzhrssGetPhoneResult = HttpUtil.postGeneralUrl(businessServiceWzhrssGetPhoneUrl, "application/json", JSON.toJSONString(businessServiceWzhrssGetPhoneBO), "UTF-8");
            log.info("businessServiceWzhrssGetPhoneResult 返回结果=" +businessServiceWzhrssGetPhoneResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡已绑定手机号码，则返回手机号码");
            recordhttp.setUrl(businessServiceWzhrssGetPhoneUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            recordhttp.setResponse(businessServiceWzhrssGetPhoneResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("phone"),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 市民卡未绑定手机号码，则返回手机号码为空,该用例目前暂时无法测试
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssGetPhoneTestByNotBindPhone() throws Exception {
        String businessServiceWzhrssGetPhoneUrl =null;
        BusinessServiceWzhrssGetPhoneBO businessServiceWzhrssGetPhoneBO =null;
        String businessServiceWzhrssGetPhoneResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssGetPhoneUrl = url+"/BusinessService/wzhrss/getphone";
            businessServiceWzhrssGetPhoneBO = new BusinessServiceWzhrssGetPhoneBO();
            businessServiceWzhrssGetPhoneBO.setAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setBmAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setCardno("32500099110004695300");
            businessServiceWzhrssGetPhoneBO.setToken(hs.get("token"));
            businessServiceWzhrssGetPhoneBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssGetPhoneUrl 请求的参数=" + businessServiceWzhrssGetPhoneUrl);
            log.info("businessServiceWzhrssGetPhoneBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            businessServiceWzhrssGetPhoneResult = HttpUtil.postGeneralUrl(businessServiceWzhrssGetPhoneUrl, "application/json", JSON.toJSONString(businessServiceWzhrssGetPhoneBO), "UTF-8");
            log.info("businessServiceWzhrssGetPhoneResult 返回结果=" +businessServiceWzhrssGetPhoneResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡未绑定手机号码，则返回手机号码为空");
            recordhttp.setUrl(businessServiceWzhrssGetPhoneUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            recordhttp.setResponse(businessServiceWzhrssGetPhoneResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"phone\":\"\""),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 必填参数Cardno校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssGetPhoneTestByNotExistParameterCardno() throws Exception {
        String businessServiceWzhrssGetPhoneUrl =null;
        BusinessServiceWzhrssGetPhoneBO businessServiceWzhrssGetPhoneBO =null;
        String businessServiceWzhrssGetPhoneResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssGetPhoneUrl = url+"/BusinessService/wzhrss/getphone";
            businessServiceWzhrssGetPhoneBO = new BusinessServiceWzhrssGetPhoneBO();
            businessServiceWzhrssGetPhoneBO.setAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setBmAppid("1.00002");
//            businessServiceWzhrssGetPhoneBO.setCardno("32500099110004695300");
            businessServiceWzhrssGetPhoneBO.setToken(hs.get("token"));
            businessServiceWzhrssGetPhoneBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssGetPhoneUrl 请求的参数=" + businessServiceWzhrssGetPhoneUrl);
            log.info("businessServiceWzhrssGetPhoneBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            businessServiceWzhrssGetPhoneResult = HttpUtil.postGeneralUrl(businessServiceWzhrssGetPhoneUrl, "application/json", JSON.toJSONString(businessServiceWzhrssGetPhoneBO), "UTF-8");
            log.info("businessServiceWzhrssGetPhoneResult 返回结果=" +businessServiceWzhrssGetPhoneResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Cardno校验");
            recordhttp.setUrl(businessServiceWzhrssGetPhoneUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            recordhttp.setResponse(businessServiceWzhrssGetPhoneResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"msg\":\"cardno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"result\":101"),true);
        }
    }

    /**
     * 错误的市民卡号
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssGetPhoneTestByParameterCardnoValueIsWrong() throws Exception {
        String businessServiceWzhrssGetPhoneUrl =null;
        BusinessServiceWzhrssGetPhoneBO businessServiceWzhrssGetPhoneBO =null;
        String businessServiceWzhrssGetPhoneResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssGetPhoneUrl = url+"/BusinessService/wzhrss/getphone";
            businessServiceWzhrssGetPhoneBO = new BusinessServiceWzhrssGetPhoneBO();
            businessServiceWzhrssGetPhoneBO.setAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setBmAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setCardno("92500099110004695300");
            businessServiceWzhrssGetPhoneBO.setToken(hs.get("token"));
            businessServiceWzhrssGetPhoneBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssGetPhoneUrl 请求的参数=" + businessServiceWzhrssGetPhoneUrl);
            log.info("businessServiceWzhrssGetPhoneBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            businessServiceWzhrssGetPhoneResult = HttpUtil.postGeneralUrl(businessServiceWzhrssGetPhoneUrl, "application/json", JSON.toJSONString(businessServiceWzhrssGetPhoneBO), "UTF-8");
            log.info("businessServiceWzhrssGetPhoneResult 返回结果=" +businessServiceWzhrssGetPhoneResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("错误的市民卡号");
            recordhttp.setUrl(businessServiceWzhrssGetPhoneUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            recordhttp.setResponse(businessServiceWzhrssGetPhoneResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"msg\":\"市民卡卡号验证失败,验证模式2\""),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"result\":520"),true);
        }
    }


    /**
     * 使用温州市民卡虚拟卡：32500099300039955842
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssGetPhoneTestByParameterCardnoValueIsVirtual() throws Exception {
        String businessServiceWzhrssGetPhoneUrl =null;
        BusinessServiceWzhrssGetPhoneBO businessServiceWzhrssGetPhoneBO =null;
        String businessServiceWzhrssGetPhoneResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssGetPhoneUrl = url+"/BusinessService/wzhrss/getphone";
            businessServiceWzhrssGetPhoneBO = new BusinessServiceWzhrssGetPhoneBO();
            businessServiceWzhrssGetPhoneBO.setAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setBmAppid("1.00002");
            businessServiceWzhrssGetPhoneBO.setCardno("32500099300039955842");
            businessServiceWzhrssGetPhoneBO.setToken(hs.get("token"));
            businessServiceWzhrssGetPhoneBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssGetPhoneUrl 请求的参数=" + businessServiceWzhrssGetPhoneUrl);
            log.info("businessServiceWzhrssGetPhoneBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            businessServiceWzhrssGetPhoneResult = HttpUtil.postGeneralUrl(businessServiceWzhrssGetPhoneUrl, "application/json", JSON.toJSONString(businessServiceWzhrssGetPhoneBO), "UTF-8");
            log.info("businessServiceWzhrssGetPhoneResult 返回结果=" +businessServiceWzhrssGetPhoneResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用温州市民卡虚拟卡");
            recordhttp.setUrl(businessServiceWzhrssGetPhoneUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssGetPhoneBO));
            recordhttp.setResponse(businessServiceWzhrssGetPhoneResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"result\":1"),true);
            Assert.assertEquals(businessServiceWzhrssGetPhoneResult.contains("\"phone\":\"\""),true);
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
