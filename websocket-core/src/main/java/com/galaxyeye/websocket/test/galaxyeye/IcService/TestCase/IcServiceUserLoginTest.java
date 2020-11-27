package com.galaxyeye.websocket.test.galaxyeye.IcService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.IcService.TestCase
 * @Date Create on 16:57
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/21日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.BO.IcServiceUserLoginBO;
import com.galaxyeye.websocket.tool.encr.RSAUtil;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class IcServiceUserLoginTest extends BaseTest {

    /**
     * 智能服务平台web页面登录,设置用户的token信息到redis当中，方便客服聊天系统websocket客服登录获取token使用
     * @throws Exception
     */
    public String icServiceUserLoginTestByGernal(IcServiceUserLoginBO icServiceUserLoginBO) {
        String icserviceUserLoginUrl = null;
        String icserviceUserLoginResult = "";
        icServiceUserLoginBO.setPublicKey(getPublicKey());
        try {
            String pwd=RSAUtil.encrypt(icServiceUserLoginBO.getUserPwd(),icServiceUserLoginBO.getPublicKey());
            icServiceUserLoginBO.setUserPwd(pwd);
            icserviceUserLoginUrl = url + "/icservice/user/login";
            log.info("icserviceUserLoginUrl 请求的参数=" + icserviceUserLoginUrl);
            log.info("icServiceUserLoginBO 请求的参数=" + JSON.toJSONString(icServiceUserLoginBO));
            icserviceUserLoginResult = HttpUtil.postGeneralUrl(icserviceUserLoginUrl,"application/json", JSON.toJSONString(icServiceUserLoginBO), "UTF-8");
            log.info("icserviceUserLoginResult 返回结果=" + icserviceUserLoginResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("智能服务平台web页面登录,设置用户的token信息到redis当中，方便客服聊天系统websocket客服登录获取token使用");
            recordhttp.setUrl(icserviceUserLoginUrl);
            recordhttp.setRequest(JSON.toJSONString(icServiceUserLoginBO));
            recordhttp.setResponse(icserviceUserLoginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(icserviceUserLoginResult.contains("data"), true);
            return icserviceUserLoginResult;
        }
    }

    /**
     * @return
     */
    String getPublicKey(){
        String icserviceUserGetPublicKeyUrl = null;
        String icserviceUserGetPublicKeyResult = "";
        try {
            icserviceUserGetPublicKeyUrl = url + "/icservice/user/getPublicKey?";
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token","");
            log.info("icserviceUserGetPublicKeyUrl 请求的参数=" + icserviceUserGetPublicKeyUrl);
            icserviceUserGetPublicKeyResult = HttpUtil.getGeneralUrl(icserviceUserGetPublicKeyUrl, "","UTF-8");
            log.info("icserviceUserGetPublicKeyResult 返回结果=" + icserviceUserGetPublicKeyResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("智能服务平台web页面登录,录获PublicKey");
            recordhttp.setUrl(icserviceUserGetPublicKeyUrl);
            recordhttp.setRequest(JSON.toJSONString(""));
            recordhttp.setResponse(icserviceUserGetPublicKeyResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("data"), true);
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("publicKey"), true);
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("pageNum"), true);
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("pageSize"), true);
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("ret_code"), true);
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("ret_msg"), true);
            Assert.assertEquals(icserviceUserGetPublicKeyResult.contains("total"), true);
            return JsonPath.read(icserviceUserGetPublicKeyResult,"$.data.publicKey");
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(11));
    }


    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
