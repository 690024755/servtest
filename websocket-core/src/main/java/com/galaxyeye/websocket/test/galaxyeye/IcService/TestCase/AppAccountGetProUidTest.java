package com.galaxyeye.websocket.test.galaxyeye.IcService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:29
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TbProAccountRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.BO.AppAccountGetProUidBO;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceQaExpertRegisterQaExpertDTO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;


@Slf4j
@Component
public class AppAccountGetProUidTest extends CIcServiceResource implements IQaExpertResource {

    /**
     * 从智能服务平台获取专家号通用性接口
     * @throws Exception
     */
    public String getQaExpertNoTestByGernal(AppAccountGetProUidBO appAccountGetProUidBO) {
        String getProUidUrl = null;
        String getProUidResult = "";
        HashMap<String, String> hs = userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
//            appAccountGetProUidBO.setAppid(expertAppid);
            appAccountGetProUidBO.setAppid(appAccountGetProUidBO.getAppid());
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("从智能服务平台获取专家号通用性接口");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("data"), true);
            Assert.assertEquals(getProUidResult.contains("uid"), true);
            Assert.assertEquals(getProUidResult.contains("expert_no"), true);
            Assert.assertEquals(getProUidResult.contains("pageNum"), true);
            Assert.assertEquals(getProUidResult.contains("pageSize"), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":1"), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"OK\""), true);
            Assert.assertEquals(getProUidResult.contains("pageNum"), true);
            Assert.assertEquals(getProUidResult.contains("pageNum"), true);
            Assert.assertEquals(getProUidResult.contains("total"), true);
            BusinessServiceQaExpertRegisterQaExpertDTO.Expert expert=JSON.parseObject(getProUidResult,BusinessServiceQaExpertRegisterQaExpertDTO.class).getData();
            log.info("expertNo="+JSON.toJSONString(expert));
            return expert.getExpert_no();
        }
    }

    /**
     * 从智能服务平台获取专家号
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTest() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            appAccountGetProUidBO.setAppid(getExpertAppid());
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("registerQaExpertBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("从智能服务平台获取专家号");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("data"), true);
            Assert.assertEquals(getProUidResult.contains("uid"), true);
            Assert.assertEquals(getProUidResult.contains("expert_no"), true);
            Assert.assertEquals(getProUidResult.contains("pageNum"), true);
            Assert.assertEquals(getProUidResult.contains("pageSize"), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":1"), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"OK\""), true);
            Assert.assertEquals(getProUidResult.contains("pageNum"), true);
            Assert.assertEquals(getProUidResult.contains("pageNum"), true);
            Assert.assertEquals(getProUidResult.contains("total"), true);
        }
    }


    /**
     * 某appid下无专家，返回无专家
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByNotExistAppid() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            appAccountGetProUidBO.setAppid(getExpertAppid()+"f");
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("某appid下无专家，返回无专家");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"没有可用专家号\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11029"), true);
        }
    }

    /**
     * 某appid下专家都被禁用，获取专家返回无专家可用
     * 在智能saasai服务平台，产品服务-->产品账号管理--->禁用所有专家
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByExistExpertButNotAvailableExpert() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            //该appid是这个appid下所有专家
            appAccountGetProUidBO.setAppid(getExpertAppid());
            disableOrEnableExpert(0);
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disableOrEnableExpert(1);
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("某appid下专家都被禁用，获取专家返回无专家可用");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"没有可用专家号\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11029"), true);
        }
    }

    /**
     必填参数Appid校验
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByNotExistParameterHeaderAppid() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
//            headerMaps.put("appid","1.00002");
            //该appid是这个appid下所有专家
            appAccountGetProUidBO.setAppid(getExpertAppid());
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("registerqaexpertResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"unknown token\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11006"), true);
        }
    }

    /**
     必填参数uid校验
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByNotExistParameterHeaderUid() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
//            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            //该appid是这个appid下所有专家
            appAccountGetProUidBO.setAppid(getExpertAppid());
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数uid校验");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"uId param is invalid\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11008"), true);
        }
    }

    /**
     * 必填参数token校验
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByNotExistParameterHeaderToken() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
//            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            //该appid是这个appid下所有专家
            appAccountGetProUidBO.setAppid(getExpertAppid());
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数token校验");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"token is miss\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11014"), true);
        }
    }

    /**
     * 必填参数Appid校验
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByNotExistParameterBodyAppid() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            //该appid是这个appid下所有专家
//             registerQaExpertBO.setAppid(expertAppid);
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("getProUidResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid校验");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"json param error\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11021"), true);
        }
    }

    /**
     * 必填参数Appid为空校验
     * @throws Exception
     */
    @Test
    public void getQaExpertNoTestByNotExistParameterBodyAppidIsEmpty() throws Exception {
        String getProUidUrl = null;
        AppAccountGetProUidBO appAccountGetProUidBO = null;
        String getProUidResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            getProUidUrl = url + "/icservice/appAccount/getProUid";
            appAccountGetProUidBO = new AppAccountGetProUidBO();
            Map<String ,String > headerMaps=new HashMap<>();
            headerMaps.put("token",hs.get("token"));
            headerMaps.put("uid",hs.get("uid"));
            headerMaps.put("appid","1.00002");
            //该appid是这个appid下所有专家
//            registerQaExpertBO.setAppid(expertAppid);
            appAccountGetProUidBO.setAppid("");
            log.info("getProUidUrl 请求的参数=" + getProUidUrl);
            log.info("appAccountGetProUidBO 请求的参数=" + JSON.toJSONString(appAccountGetProUidBO));
            getProUidResult = HttpUtil.postGeneralUrlByCustomHeader(getProUidUrl, headerMaps,"application/json", JSON.toJSONString(appAccountGetProUidBO), "UTF-8");
            log.info("registerqaexpertResult 返回结果=" + JSON.parseObject(getProUidResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Appid为空校验");
            recordhttp.setUrl(getProUidUrl);
            recordhttp.setRequest(JSON.toJSONString(appAccountGetProUidBO));
            recordhttp.setResponse(getProUidResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getProUidResult.contains("\"ret_msg\":\"appid必须不为空\""), true);
            Assert.assertEquals(getProUidResult.contains("\"ret_code\":11008"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(11));
    }


}
