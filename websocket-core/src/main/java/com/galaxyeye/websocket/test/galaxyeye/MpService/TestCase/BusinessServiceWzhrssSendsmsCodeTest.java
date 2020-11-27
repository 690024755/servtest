package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:09
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/26日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssSendsmsCodeBO;
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
public class BusinessServiceWzhrssSendsmsCodeTest extends BaseTest {
    /**
     *发送短信验证码通用接口
     * @throws Exception
     */
    public String businessServiceWzhrssSendsmsCodeTestByGernal(BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO) throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("发送短信验证码通用接口");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"ok\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":1"),true);
            return JsonPath.read(businessServiceWzhrssSendsmsCodeResult,"$.no");
        }
    }


    /**
     *登录发送短信验证码,参数Smstype=01且手机号码参数不传
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsOneAndNotExistParameterPhone() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("01");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
//            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("登录发送短信验证码,参数Smstype=01且手机号码参数不传");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"phone is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":101"),true);
        }
    }

    /**
     *登录发送短信验证码,参数Smstype=01,先校验手机号和市民卡绑定的手机号是否一样，不一样的话mpservice这边直接报错 一样我再调用对方发短信接口
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsOneAndParameterPhoneIsRight() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("01");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("登录发送短信验证码,参数Smstype=01");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"ok\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":1"),true);
        }
    }

    /**
     *登录发送短信验证码,参数Smstype=01,先校验手机号和市民卡绑定的手机号是否一样，不一样的话我这边直接报错 一样我再调用对方发短信接口
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsOneAndParameterPhoneIsWrong() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("01");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13093863511");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("登录发送短信验证码,参数Smstype=01");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"交易码错误\""),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":520"),true);
        }
    }


    /**
     *登录发送短信验证码,参数Smstype=01
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsOne() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("01");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("登录发送短信验证码,参数Smstype=01");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"ok\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":1"),true);
        }
    }

    /**
     *支付送短信验证码,参数Smstype=02
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsTwo() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("02");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("支付送短信验证码,参数Smstype=02");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"ok\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":1"),true);
        }
    }


    /**
     *支付送短信验证码,参数Smstype=03
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsThree() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("03");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("支付送短信验证码,参数Smstype=02");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"ok\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("no"),true);
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":1"),true);
        }
    }

    /**
     *未知业务类型送短信验证码,参数Smstype=99
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByParameterSmstypeValueIsOther() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("99");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("未知业务类型送短信验证码,参数Smstype=99");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"smstype is wrong\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":101"),true);
        }
    }


    /**
     *市民卡号不传，发送短信验证码
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssSendsmsCodeTestByNotExistParameterCardno() throws Exception {
        String businessServiceWzhrssSendsmsCodeUrl =null;
        BusinessServiceWzhrssSendsmsCodeBO businessServiceWzhrssSendsmsCodeBO =null;
        String businessServiceWzhrssSendsmsCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssSendsmsCodeUrl = url+"/BusinessService/wzhrss/sendsmscode";
            businessServiceWzhrssSendsmsCodeBO = new BusinessServiceWzhrssSendsmsCodeBO();
            businessServiceWzhrssSendsmsCodeBO.setAppid("1.00002");
            businessServiceWzhrssSendsmsCodeBO.setBmAppid("1.00002");
//            businessServiceWzhrssSendsmsCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssSendsmsCodeBO.setSmstype("01");
            businessServiceWzhrssSendsmsCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssSendsmsCodeBO.setUid(hs.get("uid"));
            businessServiceWzhrssSendsmsCodeBO.setPhone("13819723663");
            log.info("businessServiceWzhrssSendsmsCodeUrl 请求的参数=" + businessServiceWzhrssSendsmsCodeUrl);
            log.info("businessServiceWzhrssSendsmsCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            businessServiceWzhrssSendsmsCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssSendsmsCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO), "UTF-8");
            log.info("businessServiceWzhrssSendsmsCodeResult 返回结果=" +businessServiceWzhrssSendsmsCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("市民卡号不传，发送短信验证码");
            recordhttp.setUrl(businessServiceWzhrssSendsmsCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssSendsmsCodeBO));
            recordhttp.setResponse(businessServiceWzhrssSendsmsCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"msg\":\"cardno is empty\""),true);
            //返回短信流水号
            Assert.assertEquals(businessServiceWzhrssSendsmsCodeResult.contains("\"result\":101"),true);
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
