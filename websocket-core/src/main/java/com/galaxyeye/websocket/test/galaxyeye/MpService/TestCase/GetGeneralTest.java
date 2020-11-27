package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:23
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
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetgeneralBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.InsertGeneralBatchConfigBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class GetGeneralTest extends BaseTest {
    private Map<String, Long> timeMap = new HashMap<>();
    private Map<String, String> keysMap = new HashMap<>();
    private Map<String, Object> dataMap = new HashMap<>();

    @Autowired
    private ManualConfigRepository manualConfigRepository;

    @Autowired
    private InsertGeneralBatchTest insertGeneralBatchTest;


    /**
     * 获取配置信息
     *
     * @throws Exception
     */
    public String getgeneralTest(String key) throws Exception {
        String getgeneralUrl = url + "/BusinessService/config/getgeneral";
        GetgeneralBO getgeneralBO = new GetgeneralBO();
        HashMap<String, String> userLogin = userLoginTest();
        getgeneralBO.setToken(userLogin.get("token"));
        getgeneralBO.setUid(userLogin.get("uid"));
        getgeneralBO.setBmAppid("4.00090");
        getgeneralBO.setAppid("4.00090");
        getgeneralBO.setEnv("dev");
        getgeneralBO.setKey(key);
        getgeneralBO.setType("object");
        getgeneralBO.setVersion("5.0.0");
        log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
        log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
        String getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
        log.info("getgeneralResult 返回结果=" + getgeneralResult);
        return getgeneralResult;
    }


    /**
     * 参数Type的值为object，返回对象
     *
     * @throws Exception
     */
    @Test
    public void getgeneralTestByParameterTypeValueIsObject() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_object");
            getgeneralBO.setType("object");
            getgeneralBO.setVersion("5.0.0");
            modifyKeys("getgeneral_object","getgeneral_object");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Type的值为object，返回对象");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("config"), true);
            Assert.assertEquals(getgeneralResult.contains("defaultHealthPlanId"), true);
            Assert.assertEquals(getgeneralResult.contains("healthPlan"), true);
        }
    }

    /**
     * 参数type为test,检查结果为字符串
     * @throws Exception
     */
    @Test
    public void getgeneralTestByParameterTypeValueIsTest() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_test");
            getgeneralBO.setType("test");
            getgeneralBO.setVersion("5.0.0");
            modifyKeys("getgeneral_test","getgeneral_test");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数type为test,检查结果为字符串");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("config"), true);
            Assert.assertEquals(getgeneralResult.contains("defaultHealthPlanId"), true);
            Assert.assertEquals(getgeneralResult.contains("healthPlan"), true);
        }
    }

    /**
     * 请求参数key、env、appid确定存在多条记录，则返回update_time的降序第一条
     *
     * @throws Exception
     */
    @Test
    public void getgeneralTestByExistMultiRecord1() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_MultiRecord1");
            getgeneralBO.setType("test");
            getgeneralBO.setVersion("5.0.0");
            modifyKeys("getgeneral_MultiRecord1","getgeneral_MultiRecord1");
            modifyUpdate(120000L, 60000L);
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数key、env、appid确定存在多条记录，则返回update_time的降序第一条");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("config"), true);
            Assert.assertEquals(getgeneralResult.contains("{},[]"), true);
        }
    }

    /**
     * 请求参数key、env、appid、version确定存在多条记录，则返回update_time的降序第一条
     *
     * @throws Exception
     */
    @Test
    public void getgeneralTestByExistMultiRecord2() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_MultiRecord2");
            getgeneralBO.setType("test");
            getgeneralBO.setVersion("5.0.0");
            modifyKeys("getgeneral_MultiRecord2","getgeneral_MultiRecord2");
            modifyUpdate(60000L, 120000L);
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数key、env、appid确定存在多条记录，则返回update_time的降序第一条");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("config"), true);
            Assert.assertEquals(getgeneralResult.contains("defaultHealthPlanId"), true);
            Assert.assertEquals(getgeneralResult.contains("healthPlan"), true);
        }
    }


    /**
     * 请求参数key为精确查询
     *
     * @throws Exception
     */
    @Test
    public void getgeneralTestByParameterKeyValueIsExact() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_exact");
            getgeneralBO.setType("test");
            getgeneralBO.setVersion("5.0.0");
            modifyKeys("getgeneral_exact1","getgeneral_exact2");
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数key为精确查询");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("\"config\":\"\""), true);
        }
    }

    /**
     * manual_config.data为2,则type=int
     * @throws Exception
     */
    @Test
    public void getgeneralTestByParameterTypeValueIsInt() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_int");
            getgeneralBO.setType("int");
            getgeneralBO.setVersion("5.0.0");
            modifyData(1,2);
            modifyUpdate(120000L,60000L);
            modifyKeys("getgeneral_int","getgeneral_int");
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("manual_config.data为2,则type=int");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("\"config\":2"), true);
        }
    }

    /**
     * manual_config.data为2.111112,则type=float
     * @throws Exception
     */
    @Test
    public void getgeneralTestByParameterTypeValueIsFloat() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_float");
            getgeneralBO.setType("float");
            getgeneralBO.setVersion("5.0.0");
            modifyData(1.111112,2.111112);
            modifyUpdate(120000L,60000L);
            modifyKeys("getgeneral_float","getgeneral_float");
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("manual_config.data为2.111112,则type=float");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("\"config\":2.111112"), true);
        }
    }

    /**
     * manual_config.data为true/false,则type=bool
     *
     * @throws Exception
     */
    @Test
    public void getgeneralTestByParameterTypeValueIsBool() throws Exception {
        String getgeneralUrl = null;
        GetgeneralBO getgeneralBO = null;
        String getgeneralResult = "";
        try {
            getgeneralUrl = url + "/BusinessService/config/getgeneral";
            getgeneralBO = new GetgeneralBO();
            HashMap<String, String> userLogin = userLoginTest();
            getgeneralBO.setToken(userLogin.get("token"));
            getgeneralBO.setUid(userLogin.get("uid"));
            getgeneralBO.setBmAppid("4.00090");
            //参数appid=manual_config.appid
            getgeneralBO.setAppid("4.00090");
            getgeneralBO.setEnv("dev");
            getgeneralBO.setKey("getgeneral_bool");
            getgeneralBO.setType("bool");
            getgeneralBO.setVersion("5.0.0");
            modifyData(true,false);
            modifyUpdate(120000L,60000L);
            modifyKeys("getgeneral_bool","getgeneral_bool");
            initData();
            log.info("getgeneralUrl 请求的参数=" + getgeneralUrl);
            log.info("getgeneralBO 请求的参数=" + JSON.toJSONString(getgeneralBO));
            getgeneralResult = HttpUtil.postGeneralUrl(getgeneralUrl, "application/json", JSON.toJSONString(getgeneralBO), "UTF-8");
            log.info("getgeneralResult 返回结果=" + JSON.parseObject(getgeneralResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("manual_config.data为true/false,则type=bool");
            recordhttp.setUrl(getgeneralUrl);
            recordhttp.setRequest(JSON.toJSONString(getgeneralBO));
            recordhttp.setResponse(getgeneralResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getgeneralResult.contains("\"result\":1"), true);
            Assert.assertEquals(getgeneralResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getgeneralResult.contains("\"config\":false"), true);
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
        deleteValues.add("getgeneral_bool");
        deleteValues.add("getgeneral_float");
        deleteValues.add("getgeneral_int");
        deleteValues.add("getgeneral_exact1");
        deleteValues.add("getgeneral_exact2");
        deleteValues.add("getgeneral_test");
        deleteValues.add("getgeneral_object");
        deleteValues.add("getgeneral_MultiRecord1");
        deleteValues.add("getgeneral_MultiRecord2");
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


    @Override
    public void destroyData() {
        ManualConfigExample deleteExample = new ManualConfigExample();
        ManualConfigExample.Criteria deleteCr = deleteExample.createCriteria();
        List<String> deleteValues = new ArrayList<>();
        deleteValues.add("getgeneral_bool");
        deleteValues.add("getgeneral_float");
        deleteValues.add("getgeneral_int");
        deleteValues.add("getgeneral_exact1");
        deleteValues.add("getgeneral_exact2");
        deleteValues.add("getgeneral_test");
        deleteValues.add("getgeneral_object");
        deleteValues.add("getgeneral_MultiRecord1");
        deleteValues.add("getgeneral_MultiRecord2");
        deleteValues.add("healthPlanAbs");
        deleteCr.andConfKeyIn(deleteValues);
        manualConfigRepository.deleteByExample(deleteExample);
    }

}
