package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:14
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/24日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceCallBackSrvPoliceFaceCertBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


@Component
@Slf4j
public class BusinessServiceCallBackSrvPoliceFaceCertTest extends BaseTest {

    public void businessServiceWzhrssFaceVerifyTokenTestByGernal() throws Exception {
        String businessServiceCallBackSrvPoliceFaceCertUrl =null;
        BusinessServiceCallBackSrvPoliceFaceCertBO businessServiceCallBackSrvPoliceFaceCertBO =null;
        String businessServiceCallBackSrvPoliceFaceCertResult ="";
        try{
            businessServiceCallBackSrvPoliceFaceCertUrl = url+"/BusinessService/callbacksrv/policefacecert";
            businessServiceCallBackSrvPoliceFaceCertBO = new BusinessServiceCallBackSrvPoliceFaceCertBO();
            businessServiceCallBackSrvPoliceFaceCertBO.setHeadImage("");
            businessServiceCallBackSrvPoliceFaceCertBO.setIdentifier("");
            businessServiceCallBackSrvPoliceFaceCertBO.setOpenId("");
            businessServiceCallBackSrvPoliceFaceCertBO.setVerifiedCode("");
            businessServiceCallBackSrvPoliceFaceCertBO.setVerifiedId("");
            businessServiceCallBackSrvPoliceFaceCertBO.setVerifiedResult("");
            log.info("businessServiceCallBackSrvPoliceFaceCertUrl 请求的参数=" + businessServiceCallBackSrvPoliceFaceCertUrl);
            log.info("businessServiceCallBackSrvPoliceFaceCertBO 请求的参数=" + JSON.toJSONString(businessServiceCallBackSrvPoliceFaceCertBO));
            businessServiceCallBackSrvPoliceFaceCertResult = HttpUtil.postGeneralUrl(businessServiceCallBackSrvPoliceFaceCertUrl, "application/json", JSON.toJSONString(businessServiceCallBackSrvPoliceFaceCertBO), "UTF-8");
            log.info("businessServiceCallBackSrvPoliceFaceCertResult 返回结果=" +businessServiceCallBackSrvPoliceFaceCertResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人脸识别回调通用接口");
            recordhttp.setUrl(businessServiceCallBackSrvPoliceFaceCertUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceCallBackSrvPoliceFaceCertBO));
            recordhttp.setResponse(businessServiceCallBackSrvPoliceFaceCertResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceCallBackSrvPoliceFaceCertResult.contains("\"msg\":\"access_deny\""),true);
        }
    }

    @Test
    public void businessServiceWzhrssFaceVerifyTokenTest() throws Exception {
        String businessServiceCallBackSrvPoliceFaceCertUrl =null;
        BusinessServiceCallBackSrvPoliceFaceCertBO businessServiceCallBackSrvPoliceFaceCertBO =null;
        String businessServiceCallBackSrvPoliceFaceCertResult ="";
        try{
            businessServiceCallBackSrvPoliceFaceCertUrl = url+"/BusinessService/callbacksrv/policefacecert";
            businessServiceCallBackSrvPoliceFaceCertBO = new BusinessServiceCallBackSrvPoliceFaceCertBO();
            businessServiceCallBackSrvPoliceFaceCertBO.setHeadImage("");
            businessServiceCallBackSrvPoliceFaceCertBO.setIdentifier("");
            businessServiceCallBackSrvPoliceFaceCertBO.setOpenId("");
            businessServiceCallBackSrvPoliceFaceCertBO.setVerifiedCode("");
            businessServiceCallBackSrvPoliceFaceCertBO.setVerifiedId("");
            businessServiceCallBackSrvPoliceFaceCertBO.setVerifiedResult("");
            log.info("businessServiceCallBackSrvPoliceFaceCertUrl 请求的参数=" + businessServiceCallBackSrvPoliceFaceCertUrl);
            log.info("businessServiceCallBackSrvPoliceFaceCertBO 请求的参数=" + JSON.toJSONString(businessServiceCallBackSrvPoliceFaceCertBO));
            businessServiceCallBackSrvPoliceFaceCertResult = HttpUtil.postGeneralUrl(businessServiceCallBackSrvPoliceFaceCertUrl, "application/json", JSON.toJSONString(businessServiceCallBackSrvPoliceFaceCertBO), "UTF-8");
            log.info("businessServiceCallBackSrvPoliceFaceCertResult 返回结果=" +businessServiceCallBackSrvPoliceFaceCertResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数appid校验");
            recordhttp.setUrl(businessServiceCallBackSrvPoliceFaceCertUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceCallBackSrvPoliceFaceCertBO));
            recordhttp.setResponse(businessServiceCallBackSrvPoliceFaceCertResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(businessServiceCallBackSrvPoliceFaceCertResult.contains("\"msg\":\"access_deny\""),true);
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
