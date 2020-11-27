package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 9:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/15日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceWxGetAccessTokenBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
@Slf4j
@Component
@Deprecated
public class BusinessServiceWxGetAccessTokenTest extends BaseTest {


    @Test
    public void businessServiceWxGetAccessTokenTest() {
        String businessServiceWxGetAccessTokenUrl = null;
        BusinessServiceWxGetAccessTokenBO businessServiceWxGetAccessTokenBO = null;
        String businessServiceWxGetAccessTokenResult = "";
        try {
            businessServiceWxGetAccessTokenUrl = url + "/BusinessService/wx/getaccesstoken";
            businessServiceWxGetAccessTokenBO = new BusinessServiceWxGetAccessTokenBO();
            businessServiceWxGetAccessTokenBO.setAccessToken(bmAppids.get("1.00002"));
            businessServiceWxGetAccessTokenBO.setAppid("1.00002");
            businessServiceWxGetAccessTokenBO.setBmAppid("1.00002");
            log.info("businessServiceWxGetAccessTokenUrl 请求的参数=" + businessServiceWxGetAccessTokenUrl);
            log.info("businessServiceWxGetAccessTokenBO 请求的参数=" + JSON.toJSONString(businessServiceWxGetAccessTokenBO));
            businessServiceWxGetAccessTokenResult = HttpUtil.postGeneralUrl(businessServiceWxGetAccessTokenUrl, "application/json", JSON.toJSONString(businessServiceWxGetAccessTokenBO), "UTF-8");
            log.info("businessServiceWxGetAccessTokenResult 返回结果=" + businessServiceWxGetAccessTokenResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取微信的accesstoken");
            recordhttp.setUrl(businessServiceWxGetAccessTokenUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceWxGetAccessTokenBO));
            recordhttp.setResponse(businessServiceWxGetAccessTokenResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(businessServiceWxGetAccessTokenResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(businessServiceWxGetAccessTokenResult.contains("\"result\":112"), true);
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
