package com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase
 * @Date Create on 14:20
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.RefreshAccessTokenBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class RefreshAccessTokenTest extends BaseTest {

    /**
     * 1、刷新AccessToken值，根据BmAppid与AccessToken配对进行刷新，Appid验证接口=refreshaccesstoken是否有权限
     * 2、主要作用是产生一个新AccessToken,防止AccessToken过期，参数里的AccessToken必须在过期前才能调用该接口
     *
     * @throws Exception
     */
    @Test
    public void refreshaccesstokenTestByNotExpireAccessToken1() throws Exception {
        String refreshaccesstokenUrl =null;
        RefreshAccessTokenBO refreshAccessTokenBO =null;
        String refreshaccesstokenResult ="";
        try{
            refreshaccesstokenUrl = url+"/AccService/refreshaccesstoken";
            refreshAccessTokenBO = new RefreshAccessTokenBO();
            refreshAccessTokenBO.setAppid("100.00001");
            refreshAccessTokenBO.setBmAppid("100.00002");
            refreshAccessTokenBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            refreshAccessTokenBO.setSeq("abc");
            log.info("refreshaccesstokenUrl 请求的参数=" + refreshaccesstokenUrl);
            log.info("refreshAccessTokenBO 请求的参数=" + JSON.toJSONString(refreshAccessTokenBO));
            refreshaccesstokenResult = HttpUtil.postGeneralUrl(refreshaccesstokenUrl, "application/json", JSON.toJSONString(refreshAccessTokenBO), "UTF-8");
            log.info("refreshaccesstokenResult 返回结果=" + JSON.parseObject(refreshaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshaccesstoken配置在OpenMethod当中，使用未过期的AccessToken与BmAppid，执行刷新AccessToken");
            recordhttp.setUrl(refreshaccesstokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshAccessTokenBO));
            recordhttp.setResponse(refreshaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshaccesstokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("accessToken"),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("tokenExpire"),true);
        }
    }


    /**
     * 1、接口=refreshaccesstoken配置在userdb.appid.info字段里的AuthMethods，使用普通用户的Token与uid刷新
     *
     * @throws Exception
     */
    @Test
    public void refreshaccesstokenTestByNotExpireToken() throws Exception {
        String refreshaccesstokenUrl =null;
        RefreshAccessTokenBO refreshAccessTokenBO =null;
        String refreshaccesstokenResult ="";
        try{
            refreshaccesstokenUrl = url+"/AccService/refreshaccesstoken";
            refreshAccessTokenBO = new RefreshAccessTokenBO();
            HashMap<String,String> hs=userLoginTest();
            refreshAccessTokenBO.setUid(Long.valueOf(hs.get("uid")));
            refreshAccessTokenBO.setToken(hs.get("token"));
            refreshAccessTokenBO.setAppid("100.00002");
            refreshAccessTokenBO.setBmAppid("100.00002");
            refreshAccessTokenBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            refreshAccessTokenBO.setSeq("abc");
            log.info("refreshaccesstokenUrl 请求的参数=" + refreshaccesstokenUrl);
            log.info("refreshAccessTokenBO 请求的参数=" + JSON.toJSONString(refreshAccessTokenBO));
            refreshaccesstokenResult = HttpUtil.postGeneralUrl(refreshaccesstokenUrl, "application/json", JSON.toJSONString(refreshAccessTokenBO), "UTF-8");
            log.info("refreshaccesstokenResult 返回结果=" + JSON.parseObject(refreshaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshaccesstoken配置在AuthMethods当中，使用未过期的Token与uid，执行刷新AccessToken");
            recordhttp.setUrl(refreshaccesstokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshAccessTokenBO));
            recordhttp.setResponse(refreshaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshaccesstokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("accessToken"),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("tokenExpire"),true);
        }
    }



    /**
     * 1、接口=refreshaccesstoken配置在userdb.appid.info字段里的AuthMethods，使用bmAppid与AccessToken刷新
     * @throws Exception
     */
    @Test
    public void refreshaccesstokenTestByNotExpireAccessToken2() throws Exception {
        String refreshaccesstokenUrl =null;
        RefreshAccessTokenBO refreshAccessTokenBO =null;
        String refreshaccesstokenResult ="";
        try{
            refreshaccesstokenUrl = url+"/AccService/refreshaccesstoken";
            refreshAccessTokenBO = new RefreshAccessTokenBO();
            refreshAccessTokenBO.setAppid("100.00002");
            refreshAccessTokenBO.setBmAppid("100.00002");
            refreshAccessTokenBO.setAccessToken("11cbca83bae3d71c2ed2639a95b9b6a61af5aad2e5a143ab0773feba92770181672c8e26cf6dfc616db2eedb40b049b4");
            refreshAccessTokenBO.setSeq("abc");
            log.info("refreshaccesstokenUrl 请求的参数=" + refreshaccesstokenUrl);
            log.info("refreshAccessTokenBO 请求的参数=" + JSON.toJSONString(refreshAccessTokenBO));
            refreshaccesstokenResult = HttpUtil.postGeneralUrl(refreshaccesstokenUrl, "application/json", JSON.toJSONString(refreshAccessTokenBO), "UTF-8");
            log.info("refreshaccesstokenResult 返回结果=" + JSON.parseObject(refreshaccesstokenResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("接口方法=refreshaccesstoken配置在AuthMethods当中，使用未过期的AccessToken与BmAppid，执行刷新AccessToken");
            recordhttp.setUrl(refreshaccesstokenUrl);
            recordhttp.setRequest(JSON.toJSONString(refreshAccessTokenBO));
            recordhttp.setResponse(refreshaccesstokenResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(refreshaccesstokenResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("accessToken"),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("\"result\":1"),true);
            Assert.assertEquals(refreshaccesstokenResult.contains("tokenExpire"),true);
        }

    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(0));
    }

    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }
}
