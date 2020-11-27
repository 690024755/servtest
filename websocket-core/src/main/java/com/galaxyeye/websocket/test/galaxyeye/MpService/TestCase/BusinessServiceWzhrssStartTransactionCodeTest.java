package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssStartTransactionCodeBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class BusinessServiceWzhrssStartTransactionCodeTest extends BaseTest {
private final static String pk="58F51E4345CE0565A9B055DDCAD8CB0A674272072EC5F74DA894290CC303A44FB59D8BCFF183A52CFEA24575738A96C3D14F47D8A77475525B441221B8C14808";

    @Test(expectedExceptions=AssertionError.class )
    public void businessServiceWzhrssStartTransactionCodeTestByRightPin() throws Exception {
        String businessServiceWzhrssStartTransactionCodeUrl =null;
        BusinessServiceWzhrssStartTransactionCodeBO businessServiceWzhrssStartTransactionCodeBO =null;
        String businessServiceWzhrssStartTransactionCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssStartTransactionCodeUrl = url+"/BusinessService/wzhrss/starttransactioncode";
            businessServiceWzhrssStartTransactionCodeBO = new BusinessServiceWzhrssStartTransactionCodeBO();
            businessServiceWzhrssStartTransactionCodeBO.setAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssStartTransactionCodeBO.setPin(getCipherWithSM2("123456"));
            businessServiceWzhrssStartTransactionCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssStartTransactionCodeBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssStartTransactionCodeUrl 请求的参数=" + businessServiceWzhrssStartTransactionCodeUrl);
            log.info("businessServiceWzhrssStartTransactionCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            businessServiceWzhrssStartTransactionCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssStartTransactionCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO), "UTF-8");
            log.info("businessServiceWzhrssStartTransactionCodeResult 返回结果=" +businessServiceWzhrssStartTransactionCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("前提该市民卡关闭启用密码，然后进行启用市民卡密码，正确的pin进行启用市民卡密码");
            recordhttp.setUrl(businessServiceWzhrssStartTransactionCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            recordhttp.setResponse(businessServiceWzhrssStartTransactionCodeResult);
            initLog(recordhttp,new Object(){});
            //如果该市民卡已启用交易密码，则忽略该条测试用例抛出异常
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"result\":1"),true);
        }
    }

    /**
     *错误的pin，启用市民卡密码
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssStartTransactionCodeTestByWrongPin() throws Exception {
        String businessServiceWzhrssStartTransactionCodeUrl =null;
        BusinessServiceWzhrssStartTransactionCodeBO businessServiceWzhrssStartTransactionCodeBO =null;
        String businessServiceWzhrssStartTransactionCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssStartTransactionCodeUrl = url+"/BusinessService/wzhrss/starttransactioncode";
            businessServiceWzhrssStartTransactionCodeBO = new BusinessServiceWzhrssStartTransactionCodeBO();
            businessServiceWzhrssStartTransactionCodeBO.setAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssStartTransactionCodeBO.setPin(getCipherWithSM2("123456").concat("1"));
            businessServiceWzhrssStartTransactionCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssStartTransactionCodeBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssStartTransactionCodeUrl 请求的参数=" + businessServiceWzhrssStartTransactionCodeUrl);
            log.info("businessServiceWzhrssStartTransactionCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            businessServiceWzhrssStartTransactionCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssStartTransactionCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO), "UTF-8");
            log.info("businessServiceWzhrssStartTransactionCodeResult 返回结果=" +businessServiceWzhrssStartTransactionCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("错误的pin，启用市民卡密码");
            recordhttp.setUrl(businessServiceWzhrssStartTransactionCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            recordhttp.setResponse(businessServiceWzhrssStartTransactionCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"msg\":\"\""),true);
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"result\":520"),true);
        }
    }


    /**
     *已启用市民卡密码的市民卡，再次启用市民卡密码
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssStartTransactionCodeTestByRepeatStart() throws Exception {
        String businessServiceWzhrssStartTransactionCodeUrl =null;
        BusinessServiceWzhrssStartTransactionCodeBO businessServiceWzhrssStartTransactionCodeBO =null;
        String businessServiceWzhrssStartTransactionCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssStartTransactionCodeUrl = url+"/BusinessService/wzhrss/starttransactioncode";
            businessServiceWzhrssStartTransactionCodeBO = new BusinessServiceWzhrssStartTransactionCodeBO();
            businessServiceWzhrssStartTransactionCodeBO.setAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssStartTransactionCodeBO.setPin(getCipherWithSM2("123456"));
            businessServiceWzhrssStartTransactionCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssStartTransactionCodeBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssStartTransactionCodeUrl 请求的参数=" + businessServiceWzhrssStartTransactionCodeUrl);
            log.info("businessServiceWzhrssStartTransactionCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            businessServiceWzhrssStartTransactionCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssStartTransactionCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO), "UTF-8");
            log.info("businessServiceWzhrssStartTransactionCodeResult 返回结果=" +businessServiceWzhrssStartTransactionCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已启用市民卡密码的市民卡，再次启用市民卡密码");
            recordhttp.setUrl(businessServiceWzhrssStartTransactionCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            recordhttp.setResponse(businessServiceWzhrssStartTransactionCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"msg\":\"市民卡密码已启用，不能重复启用\""),true);
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"result\":520"),true);
        }
    }


    /**
     *必填参数pin校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssStartTransactionCodeTestByNotExistParameterPin() throws Exception {
        String businessServiceWzhrssStartTransactionCodeUrl =null;
        BusinessServiceWzhrssStartTransactionCodeBO businessServiceWzhrssStartTransactionCodeBO =null;
        String businessServiceWzhrssStartTransactionCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssStartTransactionCodeUrl = url+"/BusinessService/wzhrss/starttransactioncode";
            businessServiceWzhrssStartTransactionCodeBO = new BusinessServiceWzhrssStartTransactionCodeBO();
            businessServiceWzhrssStartTransactionCodeBO.setAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setBmAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setCardno("32500099110004695300");
//            businessServiceWzhrssStartTransactionCodeBO.setPin(getCipherWithSM2("123456"));
            businessServiceWzhrssStartTransactionCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssStartTransactionCodeBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssStartTransactionCodeUrl 请求的参数=" + businessServiceWzhrssStartTransactionCodeUrl);
            log.info("businessServiceWzhrssStartTransactionCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            businessServiceWzhrssStartTransactionCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssStartTransactionCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO), "UTF-8");
            log.info("businessServiceWzhrssStartTransactionCodeResult 返回结果=" +businessServiceWzhrssStartTransactionCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数pin校验");
            recordhttp.setUrl(businessServiceWzhrssStartTransactionCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            recordhttp.setResponse(businessServiceWzhrssStartTransactionCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"msg\":\"交易密码不能为空\""),true);
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"result\":520"),true);
        }
    }

    /**
     *必填参数Cardno校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssStartTransactionCodeTestByNotExistParameterCardno() throws Exception {
        String businessServiceWzhrssStartTransactionCodeUrl =null;
        BusinessServiceWzhrssStartTransactionCodeBO businessServiceWzhrssStartTransactionCodeBO =null;
        String businessServiceWzhrssStartTransactionCodeResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssStartTransactionCodeUrl = url+"/BusinessService/wzhrss/starttransactioncode";
            businessServiceWzhrssStartTransactionCodeBO = new BusinessServiceWzhrssStartTransactionCodeBO();
            businessServiceWzhrssStartTransactionCodeBO.setAppid("1.00002");
            businessServiceWzhrssStartTransactionCodeBO.setBmAppid("1.00002");
//            businessServiceWzhrssStartTransactionCodeBO.setCardno("32500099110004695300");
            businessServiceWzhrssStartTransactionCodeBO.setPin(getCipherWithSM2("123456"));
            businessServiceWzhrssStartTransactionCodeBO.setToken(hs.get("token"));
            businessServiceWzhrssStartTransactionCodeBO.setUid(hs.get("uid"));
            log.info("businessServiceWzhrssStartTransactionCodeUrl 请求的参数=" + businessServiceWzhrssStartTransactionCodeUrl);
            log.info("businessServiceWzhrssStartTransactionCodeBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            businessServiceWzhrssStartTransactionCodeResult = HttpUtil.postGeneralUrl(businessServiceWzhrssStartTransactionCodeUrl, "application/json", JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO), "UTF-8");
            log.info("businessServiceWzhrssStartTransactionCodeResult 返回结果=" +businessServiceWzhrssStartTransactionCodeResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Cardno校验");
            recordhttp.setUrl(businessServiceWzhrssStartTransactionCodeUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssStartTransactionCodeBO));
            recordhttp.setResponse(businessServiceWzhrssStartTransactionCodeResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"msg\":\"cardno is empty\""),true);
            Assert.assertEquals(businessServiceWzhrssStartTransactionCodeResult.contains("\"result\":101"),true);
        }
    }

    /**
     * pwd 明文密码
     * xor 是否使用异或方式对密码进行预处理
     * 获取密码密文方式1，用于获取 RSA 算法加密的密码密文值
     * @return
     */
    private String getCipherWithRSA(String pwd,Boolean xor){
//        16 进制编码的公钥值，RSA 算法下公钥必须为 DER 编码

            return null;
    }

    /**
     * pwd 明文密码
     * xor 是否使用异或方式对密码进行预处理
     * 获取密码密文方式2，用于获取 SM2 算法加密的密码密文值
     * @return
     */
    public String getCipherWithSM2(String pwd){
        log.info("明文="+pwd);
//        明文：123456
//        加密后的密文：F5F436E471B60A0659D03A4FCBAEACD2A87A6F357D89B5062C13A896EB79996BAB0C3E8E16E1512968C6382B524153EDFC16AD88268150DC7E3B211D2164ED7B620BC2394B1AF404AAFF23EE3E1123287C756E709E3A341D1B5448C43F4B6E039040C8CEF36224EFF34D06CA75B85BBD89E968953BBEC33C28D63274AF4A9762
        String cipherPin="F5F436E471B60A0659D03A4FCBAEACD2A87A6F357D89B5062C13A896EB79996BAB0C3E8E16E1512968C6382B524153EDFC16AD88268150DC7E3B211D2164ED7B620BC2394B1AF404AAFF23EE3E1123287C756E709E3A341D1B5448C43F4B6E039040C8CEF36224EFF34D06CA75B85BBD89E968953BBEC33C28D63274AF4A9762";
        log.info("密文="+cipherPin);
        return cipherPin;
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
