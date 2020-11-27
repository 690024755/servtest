package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:17
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWzhrssPensionTokenBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;


@Component
@Slf4j
public class BusinessServiceWzhrssPensionTokenTest extends BaseTest {
    /**
     *养老金账户查询
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssPensionTokenTestByNormal() throws Exception {
        String businessServiceWzhrssPensionTokenUrl =null;
        BusinessServiceWzhrssPensionTokenBO businessServiceWzhrssPensionTokenBO =null;
        String businessServiceWzhrssPensionTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssPensionTokenUrl = url+"/BusinessService/wzhrss/pensiontoken";
            businessServiceWzhrssPensionTokenBO = new BusinessServiceWzhrssPensionTokenBO();
            businessServiceWzhrssPensionTokenBO.setAppid("1.00002");
            log.info("businessServiceWzhrssPensionTokenUrl 请求的参数=" + businessServiceWzhrssPensionTokenUrl);
            log.info("businessServiceWzhrssPensionTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssPensionTokenBO));
            businessServiceWzhrssPensionTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssPensionTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssPensionTokenBO), "UTF-8");
            log.info("businessServiceWzhrssPensionTokenResult 返回结果=" +businessServiceWzhrssPensionTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("养老金账户查询");
            recordhttp.setUrl(businessServiceWzhrssPensionTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssPensionTokenBO));
            recordhttp.setResponse(businessServiceWzhrssPensionTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssPensionTokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(businessServiceWzhrssPensionTokenResult.contains("timeStr"),true);
            Assert.assertEquals(businessServiceWzhrssPensionTokenResult.contains("token"),true);
            Assert.assertEquals(businessServiceWzhrssPensionTokenResult.contains("\"result\":1"),true);
        }
    }

    /**
     *必填参数Appid校验
     * @throws Exception
     */
    @Test
    public void businessServiceWzhrssPensionTokenTestByNotExistParameterAppid() throws Exception {
        String businessServiceWzhrssPensionTokenUrl =null;
        BusinessServiceWzhrssPensionTokenBO businessServiceWzhrssPensionTokenBO =null;
        String businessServiceWzhrssPensionTokenResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            businessServiceWzhrssPensionTokenUrl = url+"/BusinessService/wzhrss/pensiontoken";
            businessServiceWzhrssPensionTokenBO = new BusinessServiceWzhrssPensionTokenBO();
//            businessServiceWzhrssPensionTokenBO.setAppid("1.00002");
            log.info("businessServiceWzhrssPensionTokenUrl 请求的参数=" + businessServiceWzhrssPensionTokenUrl);
            log.info("businessServiceWzhrssPensionTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWzhrssPensionTokenBO));
            businessServiceWzhrssPensionTokenResult = HttpUtil.postGeneralUrl(businessServiceWzhrssPensionTokenUrl, "application/json", JSON.toJSONString(businessServiceWzhrssPensionTokenBO), "UTF-8");
            log.info("businessServiceWzhrssPensionTokenResult 返回结果=" +businessServiceWzhrssPensionTokenResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(businessServiceWzhrssPensionTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWzhrssPensionTokenBO));
            recordhttp.setResponse(businessServiceWzhrssPensionTokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceWzhrssPensionTokenResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(businessServiceWzhrssPensionTokenResult.contains("\"result\":106"),true);
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
