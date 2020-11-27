package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:24
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.ManualConfigRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetGeneralStructBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchConfigBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

@Component
@Slf4j
public class GetGeneralStructTest extends BaseTest {
    private Map<String, Long> timeMap = new HashMap<>();
    private Map<String, String> keysMap = new HashMap<>();
    private Map<String, String> appidMap = new HashMap<>();
    private Map<String, Object> dataMap = new HashMap<>();

    @Autowired
    private ManualConfigRepository manualConfigRepository;

    @Autowired
    private InsertGeneralBatchTest insertGeneralBatchTest;

    /**
     *参数Type的值为object，返回对象
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByParameterTypeValueIsObject() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_object");

            getGeneralStructBO.setType("object");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_object","getgeneralstruct_object");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Type的值为object，返回对象");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("data"), true);
            Assert.assertEquals(getGeneralStructResult.contains("healthPlan"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
        }
    }

    /**
     *参数Type的值为Test，返回字符串
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByParameterTypeValueIsTest() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_test");
            getGeneralStructBO.setType("test");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_test","getgeneralstruct_test");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Type的值为Test，返回字符串");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("data"), true);
            Assert.assertEquals(getGeneralStructResult.contains("healthPlan"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
        }
    }

    /**
     *请求参数key、env、appid确定存在多条记录，则返回update_time的降序第一条
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByExistMultiRecord1() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_MultiRecord1");
            getGeneralStructBO.setType("test");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_MultiRecord1","getgeneralstruct_MultiRecord1");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数key、env、appid确定存在多条记录，则返回update_time的降序第一条");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("data"), true);
            Assert.assertEquals(getGeneralStructResult.contains("healthPlan"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
        }
    }

    /**
     *请求参数key、env、appid、version确定存在多条记录，则返回update_time的降序第一条
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByExistMultiRecord2() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_test");
            getGeneralStructBO.setType("test");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_test","getgeneralstruct_test");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数key、env、appid、version确定存在多条记录，则返回update_time的降序第一条");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("data"), true);
            Assert.assertEquals(getGeneralStructResult.contains("healthPlan"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
        }
    }

    /**
     *请求参数key为精确查询,查询不到返回空
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByParameterKeyValueIsExact() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_exact");
            getGeneralStructBO.setType("test");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_exact1","getgeneralstruct_exact2");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数key为精确查询,查询不到返回空");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"updatedAt\":\"\""), true);
        }
    }

    /**
     *manual_config.data为2,则type=int
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByParameterTypeValueIsInt() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_int");
            getGeneralStructBO.setType("int");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_int","getgeneralstruct_int");
            modifyUpdate(60000L, 120000L);
            modifyData(1,2);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("manual_config.data为2,则type=int");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"1\""), true);
        }
    }

    /**
     *manual_config.data为1.111112,则type=float
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByParameterTypeValueIsFloat() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_float");
            getGeneralStructBO.setType("float");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_float","getgeneralstruct_float");
            modifyUpdate(60000L, 120000L);
            modifyData(1.111112,2.111112);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("manual_config.data为1.111112,则type=float");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"1.111112\""), true);
        }
    }

    /**
     *manual_config.data为2.111112,则type=bool
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByParameterTypeValueIsBool() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("4.00090");
            getGeneralStructBO.setAppid("4.00090");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("manual_config.data为2.111112,则type=bool");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"true\""), true);
        }
    }

    /**
     * 方法getgeneralstruct配置在OpenMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByOpenMethodAndUid() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
//            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("1.00002");
            getGeneralStructBO.setAppid("1.00002");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            modifyAppid("1.00002","1.00002");
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getgeneralstruct配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"true\""), true);
        }
    }

    /**
     * 方法getgeneralstruct配置在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByOpenMethodAndBmAppid() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
//            getGeneralStructBO.setAccessToken(bmAppids.get("1.00002"));
            getGeneralStructBO.setBmAppid("1.00002");
            getGeneralStructBO.setAppid("1.00002");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            modifyAppid("1.00002","1.00002");
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getgeneralstruct配置在OpenMethod当中，不校验AccessToken");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"true\""), true);
        }
    }

    /**
     * 方法getgeneralstruct配置在authMethod当中，校验Token
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByAuthMethodAndUid() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("100.00002");
            getGeneralStructBO.setAppid("100.00002");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            modifyAppid("100.00002","100.00002");
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getgeneralstruct配置在OpenMethod当中，不校验Token");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"true\""), true);
        }
    }

    /**
     * 方法getgeneralstruct配置在authMethod当中，不校验Token
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByAuthMethodAndUidAndNotExistParameterToken() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            HashMap<String, String> userLogin = userLoginTest();
//            getGeneralStructBO.setToken(userLogin.get("token"));
            getGeneralStructBO.setUid(userLogin.get("uid"));
            getGeneralStructBO.setBmAppid("100.00002");
            getGeneralStructBO.setAppid("100.00002");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            modifyAppid("100.00002","100.00002");
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getgeneralstruct配置在authMethod当中，不校验Token");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 方法getgeneralstruct配置在authMethod当中，校验AccessToken
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByAuthMethodAndBmAppid() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
            getGeneralStructBO.setAccessToken(bmAppids.get("100.00002"));
            getGeneralStructBO.setBmAppid("100.00002");
            //参数appid=manual_config.appid，用于查询不同小程序的配置信息
            getGeneralStructBO.setAppid("100.00002");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            modifyAppid("100.00002","100.00002");
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getgeneralstruct配置在authMethod当中，校验AccessToken");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":1"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("config"), true);
            Assert.assertEquals(getGeneralStructResult.contains("enable"), true);
            Assert.assertEquals(getGeneralStructResult.contains("updatedAt"), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"data\":\"true\""), true);
        }
    }


    /**
     * 方法getgeneralstruct配置在authMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void getgeneralstructTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() throws Exception {
        String getGeneralStructUrl = null;
        GetGeneralStructBO getGeneralStructBO = null;
        String getGeneralStructResult = "";
        try {
            getGeneralStructUrl = url + "/BusinessService/config/getgeneralstruct";
            getGeneralStructBO = new GetGeneralStructBO();
//            getGeneralStructBO.setAccessToken(bmAppids.get("100.00002"));
            getGeneralStructBO.setBmAppid("100.00002");
            getGeneralStructBO.setAppid("100.00002");
            getGeneralStructBO.setEnv("dev");
            getGeneralStructBO.setKey("getgeneralstruct_bool");
            getGeneralStructBO.setType("bool");
            getGeneralStructBO.setVersion("5.0.0");
            modifyKeys("getgeneralstruct_bool","getgeneralstruct_bool");
            modifyUpdate(60000L, 120000L);
            modifyData(true,false);
            modifyAppid("100.00002","100.00002");
            initData();
            log.info("getGeneralStructUrl 请求的参数=" + getGeneralStructUrl);
            log.info("getGeneralStructBO 请求的参数=" + JSON.toJSONString(getGeneralStructBO));
            getGeneralStructResult = HttpUtil.postGeneralUrl(getGeneralStructUrl, "application/json", JSON.toJSONString(getGeneralStructBO), "UTF-8");
            log.info("getGeneralStructResult 返回结果=" + JSON.parseObject(getGeneralStructResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("方法getgeneralstruct配置在authMethod当中，不校验AccessToken");
            recordhttp.setUrl(getGeneralStructUrl);
            recordhttp.setRequest(JSON.toJSONString(getGeneralStructBO));
            recordhttp.setResponse(getGeneralStructResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getGeneralStructResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getGeneralStructResult.contains("\"result\":101"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

    @Override
    public void initData() {
        ManualConfigExample selectExample = new ManualConfigExample();
        ManualConfigExample.Criteria selectCr = selectExample.createCriteria();
        List<String> selectValues = new ArrayList<>();
        selectValues.add("healthPlanAbs");
        selectCr.andConfKeyIn(selectValues);
        ManualConfigExample deleteExample = new ManualConfigExample();
        ManualConfigExample.Criteria deleteCr = deleteExample.createCriteria();
        List<String> deleteValues = new ArrayList<>();
        deleteValues.add("getgeneralstruct_bool");
        deleteValues.add("getgeneralstruct_float");
        deleteValues.add("getgeneralstruct_int");
        deleteValues.add("getgeneralstruct_exact1");
        deleteValues.add("getgeneralstruct_exact2");
        deleteValues.add("getgeneralstruct_test");
        deleteValues.add("getgeneralstruct_object");
        deleteValues.add("getgeneralstruct_MultiRecord1");
        deleteValues.add("getgeneralstruct_MultiRecord2");
        deleteValues.add("healthPlanAbs");
        deleteCr.andConfKeyIn(deleteValues);
        manualConfigRepository.deleteByExample(deleteExample);
        InsertGeneralBatchBO insertGeneralBatchBO = new InsertGeneralBatchBO();
        HashMap<String, String> userLogin = userLoginTest();
        insertGeneralBatchBO.setToken(userLogin.get("token"));
        insertGeneralBatchBO.setUid(userLogin.get("uid"));
        insertGeneralBatchBO.setAppid("4.00090");
        insertGeneralBatchBO.setConfAppid("4.00090");
        insertGeneralBatchBO.setEnv("dev");
        insertGeneralBatchBO.setVersion("5.0.0");
        insertGeneralBatchBO.setNewApp(0);
        List<InsertGeneralBatchConfigBO> config = new ArrayList<>();
        InsertGeneralBatchConfigBO insertGeneralBatchConfigBO1 = new InsertGeneralBatchConfigBO();
        insertGeneralBatchConfigBO1.setComment("健康小程序健康计划列表（待修改）");
        insertGeneralBatchConfigBO1.setKey("healthPlanAbs");
        String data1 = "{\"healthPlan\":[{\"id\":\"1\",\"name\":\"7天压力释放计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309798804361216/613607293698707456.png\"},{\"id\":\"2\",\"name\":\"7天元气恢复计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309803191603200/613605459055611904.jpg\"},{\"id\":\"3\",\"name\":\"7天补水保湿计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"},{\"id\":\"4\",\"name\":\"14天晚安好眠计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"},{\"id\":\"5\",\"name\":\"14天发量拯救计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915731869696.jpg\"},{\"id\":\"6\",\"name\":\"21天脾胃养护计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915824144384.jpg\"},{\"id\":\"7\",\"name\":\"21天颈椎保护计划\",\"image\":\"https://7niu-article.galaxyeye-tech.com/article/190820/613309822607036416/613610915627012096.jpg\"}],\"defaultHealthPlanId\":[\"1\",\"2\",\"3\"]}";
        insertGeneralBatchConfigBO1.setData(data1);
        insertGeneralBatchConfigBO1.setEditor(null);
        insertGeneralBatchConfigBO1.setEnable(1);
        insertGeneralBatchConfigBO1.setVerify(0);
        InsertGeneralBatchConfigBO insertGeneralBatchConfigBO2 = new InsertGeneralBatchConfigBO();
        insertGeneralBatchConfigBO2.setComment("健康小程序健康计划列表（测试专用）");
        insertGeneralBatchConfigBO2.setKey("healthPlanAbs");
        String data2 = "[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[[{},[],{},[{}],[{},[],{},[{}]],[{},[[{},[[{},[[{},[[{},[],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]],[{},[[{},[[{},[[{},[[{},[],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]],{},[{}]]]";
        insertGeneralBatchConfigBO2.setData(data2);
        insertGeneralBatchConfigBO2.setEditor(null);
        insertGeneralBatchConfigBO2.setEnable(1);
        insertGeneralBatchConfigBO2.setVerify(1);
        config.add(insertGeneralBatchConfigBO1);
        config.add(insertGeneralBatchConfigBO2);
        insertGeneralBatchBO.setConfig(config);
        insertGeneralBatchTest.insertgeneralbatchTestByGernal(insertGeneralBatchBO, config);
        List<ManualConfig> listTmp = manualConfigRepository.selectByExample(selectExample);
        for (int i = 0; i < listTmp.size(); i++) {
            ManualConfig updateRecord = new ManualConfig();
            updateRecord.setUpdatedAt(new Date());
            ManualConfigExample updateExample = new ManualConfigExample();
            ManualConfigExample.Criteria updateCr = updateExample.createCriteria();
            if (i == 0) {
                Date date = listTmp.get(i).getUpdatedAt();
                if (timeMap.get("time1") != null ) {
                    date.setTime(date.getTime() - timeMap.get("time1"));
                } else {
                    date.setTime(date.getTime());
                }
                if (keysMap.get("keys1") != null) {
                    listTmp.get(i).setConfKey(keysMap.get("keys1"));
                }
                if (dataMap.get("obj1") != null) {
                    listTmp.get(i).setData(String.valueOf(dataMap.get("obj1")));
                }
                if (appidMap.get("appid1") != null) {
                    listTmp.get(i).setAppid(String.valueOf(appidMap.get("appid1")));
                }

                listTmp.get(i).setUpdatedAt(date);

                BeanUtils.copyProperties(listTmp.get(i), updateRecord);
                updateCr.andIdEqualTo(listTmp.get(i).getId());

            } else {
                Date date = listTmp.get(i).getUpdatedAt();
                if (timeMap.get("time2") != null) {
                    date.setTime(date.getTime() - timeMap.get("time2"));
                } else {
                    date.setTime(date.getTime());
                }
                if (keysMap.get("keys2") != null) {
                    listTmp.get(i).setConfKey(keysMap.get("keys2"));
                }
                if (dataMap.get("obj2") != null) {
                    listTmp.get(i).setData(String.valueOf(dataMap.get("obj2")));
                }
                if (appidMap.get("appid2") != null) {
                    listTmp.get(i).setAppid(String.valueOf(appidMap.get("appid2")));
                }
                listTmp.get(i).setUpdatedAt(date);
                BeanUtils.copyProperties(listTmp.get(i), updateRecord);
                updateCr.andIdEqualTo(listTmp.get(i).getId());
            }
            manualConfigRepository.updateByExampleSelective(updateRecord, updateExample);
        }
    }

    private void modifyData(Object obj1, Object obj2) {
        dataMap.put("obj1", obj1);
        dataMap.put("obj2", obj2);
    }


    private void modifyUpdate(Long time1, Long time2) {
        timeMap.put("time1", time1);
        timeMap.put("time2", time2);
    }


    private void modifyKeys(String keys1, String keys2) {
        keysMap.put("keys1", keys1);
        keysMap.put("keys2", keys2);
    }

    private void modifyAppid(String appid1, String appid2) {
        appidMap.put("appid1", appid1);
        appidMap.put("appid2", appid2);
    }

    @Override
    public void destroyData() {
        ManualConfigExample deleteExample = new ManualConfigExample();
        ManualConfigExample.Criteria deleteCr = deleteExample.createCriteria();
        List<String> deleteValues = new ArrayList<>();
        deleteValues.add("getgeneralstruct_bool");
        deleteValues.add("getgeneralstruct_float");
        deleteValues.add("getgeneralstruct_int");
        deleteValues.add("getgeneralstruct_exact1");
        deleteValues.add("getgeneralstruct_exact2");
        deleteValues.add("getgeneralstruct_test");
        deleteValues.add("getgeneralstruct_object");
        deleteValues.add("getgeneralstruct_MultiRecord1");
        deleteValues.add("getgeneralstruct_MultiRecord2");
        deleteValues.add("healthPlanAbs");
        deleteCr.andConfKeyIn(deleteValues);
        manualConfigRepository.deleteByExample(deleteExample);
    }
}
