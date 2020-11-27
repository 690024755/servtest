package com.galaxyeye.websocket.test.galaxyeye.IcService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.IcService.TestCase
 * @Date Create on 14:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/2日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.BO.AppAccountAppLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

@Slf4j
@Component
public class AppAccountAppLoginTest extends CIcServiceResource implements IQaExpertResource {

    /**
     * @throws Exception
     */
    public void AppAccountAppLoginTestByGernal(AppAccountAppLoginBO appAccountAppLoginBO) {
        String apploginUrl = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(appAccountAppLoginBO.getAppid());
            appAccountAppLoginBO.setCid(appAccountAppLoginBO.getCid());
            appAccountAppLoginBO.setCpwd(appAccountAppLoginBO.getCpwd());
            appAccountAppLoginBO.setType(appAccountAppLoginBO.getType());
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人保专家或者普通用户通用登录接口");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_code\":1"), true);
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"OK\""), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByCommonUserAndTypeValueIsZero() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getCommonCid());
            appAccountAppLoginBO.setCpwd(getCommonPwd());
            appAccountAppLoginBO.setType(0);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人保普通用户登录接口");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_code\":1"), true);
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"OK\""), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByCommonUserAndTypeValueIsOne() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getCommonCid());
            appAccountAppLoginBO.setCpwd(getCommonPwd());
            appAccountAppLoginBO.setType(1);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户的使用专家类型登录");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"putong3账号校验失败\""), true);
            Assert.assertEquals(apploginResult.contains("\"ret_code\":11015"), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByCommonUserAndTypeValueIsFive() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getCommonCid());
            appAccountAppLoginBO.setCpwd(getCommonPwd());
            appAccountAppLoginBO.setType(5);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("普通用户的使用既非专家类型登录也非普通用户类型登录");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"putong3账号校验失败\""), true);
            Assert.assertEquals(apploginResult.contains("\"ret_code\":11015"), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByExpertUserAndTypeValueIsZero() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getExpertCid());
            appAccountAppLoginBO.setCpwd(getExpertPwd());
            appAccountAppLoginBO.setType(0);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("专家用户的使用普通类型登录");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"123458账号校验失败\""), true);
            Assert.assertEquals(apploginResult.contains("\"ret_code\":11015"), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByExpertUserAndTypeValueIsOne() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getExpertCid());
            appAccountAppLoginBO.setCpwd(getExpertPwd());
            appAccountAppLoginBO.setType(1);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人保专家用户登录接口，专家用户的使用专家类型登录");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"OK\""), true);
            Assert.assertEquals(apploginResult.contains("\"ret_code\":1"), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByExpertUserAndTypeValueIsFive() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getExpertCid());
            appAccountAppLoginBO.setCpwd(getExpertPwd());
            appAccountAppLoginBO.setType(5);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("人保专家用户登录接口，专家用户使用既非专家类型登录也非普通用户类型登录");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"123458账号校验失败\""), true);
            Assert.assertEquals(apploginResult.contains("\"ret_code\":11015"), true);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void AppAccountAppLoginTestByNotExistParameterType() {
        String apploginUrl = null;
        AppAccountAppLoginBO appAccountAppLoginBO = null;
        String apploginResult = "";
        try {
            apploginUrl = url + "/icservice/appAccount/applogin";
            appAccountAppLoginBO = new AppAccountAppLoginBO();
            Map<String ,String > headerMaps=new HashMap<>();
            HashMap<String, String> hs=userLoginTest();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            //设置接口有无验证权限
            headerMaps.put("appid",getExpertAppid());
            appAccountAppLoginBO.setAppid(getExpertAppid());
            appAccountAppLoginBO.setCid(getExpertCid());
            appAccountAppLoginBO.setCpwd(getExpertPwd());
//            appAccountAppLoginBO.setType(1);
            log.info("apploginUrl 请求的参数=" + apploginUrl);
            log.info("appAccountAppLoginBO 请求的参数=" + JSON.toJSONString(appAccountAppLoginBO));
            apploginResult = HttpUtil.postGeneralUrlByCustomHeader(apploginUrl, headerMaps,"application/json", JSON.toJSONString(appAccountAppLoginBO), "UTF-8");
            log.info("apploginResult 返回结果=" + JSON.parseObject(apploginResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Type校验");
            recordhttp.setUrl(apploginUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountAppLoginBO));
            recordhttp.setResponse(apploginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(apploginResult.contains("\"ret_msg\":\"appid/cid/cpwd/type 必须都不为空\""), true);
            Assert.assertEquals(apploginResult.contains("\"ret_code\":11008"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(11));
    }


}
