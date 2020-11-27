package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 13:07
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/1日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TbProAccountRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceQaExpertReadQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class BusinessServiceQaExpertReadQaExpertMessageTest extends CIcServiceResource implements IQaExpertResource {

    @Autowired
    private BusinessServiceQaExpertCreateQaExpertMessageTest businessServiceQaExpertCreateQaExpertMessageTest;

    @Autowired
    private BusinessServiceQaExpertReplyQaExpertMessageTest businessServiceQaExpertReplyQaExpertMessageTest;

    /**
     * 提问者阅读留言板信息的通用方法
     * @throws Exception
     */
    public void BusinessServiceQaExpertReadQaExpertMessageTestByGernal(BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO) throws Exception {
        String readqaexpertmessageUrl = null;
        String readqaexpertmessageResult = "";
        try {
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO.setToken(businessServiceQaExpertReadQaExpertMessageBO.getToken());
            businessServiceQaExpertReadQaExpertMessageBO.setUid(businessServiceQaExpertReadQaExpertMessageBO.getUid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid(businessServiceQaExpertReadQaExpertMessageBO.getAppid());
            businessServiceQaExpertReadQaExpertMessageBO.setId(businessServiceQaExpertReadQaExpertMessageBO.getId());
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(businessServiceQaExpertReadQaExpertMessageBO.getNickname());
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("提问者阅读留言板信息的通用方法");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 提问者首次阅读留言信息
     * @throws Exception
     */
    @Test
    public void BusinessServiceQaExpertReadQaExpertMessageTest() throws Exception {
        String readqaexpertmessageUrl = null;
        BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO = null;
        String readqaexpertmessageResult = "";
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs= userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO = new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(id);
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取一条留言信息及该条信息所有回复消息");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 提问者可以多次阅读留言信息
     * @throws Exception
     */
    @Test
    public void BusinessServiceQaExpertReadQaExpertMessageTestByRepeat() throws Exception {
        String readqaexpertmessageUrl = null;
        BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO = null;
        String readqaexpertmessageResult = "";
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs= userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO = new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(id);
            //咨询者工号
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("提问者可以多次阅读留言信息");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 提问者首次阅读不存在的留言信息,目前返回成功
     * @throws Exception
     */
    @Test
    public void BusinessServiceQaExpertReadQaExpertMessageTestByReadNotExistRecord() throws Exception {
        String readqaexpertmessageUrl = null;
        BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO = null;
        String readqaexpertmessageResult = "";
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs= userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO = new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(id+"1");
            //咨询者工号
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("提问者首次阅读不存在的留言信息,目前返回成功");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 必填参数nickname校验
     * @throws Exception
     */
    @Test
    public void BusinessServiceQaExpertReadQaExpertMessageTestByNotExistParameterNickName() throws Exception {
        String readqaexpertmessageUrl = null;
        BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO = null;
        String readqaexpertmessageResult = "";
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs= userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO = new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(id);
            //咨询者工号
//            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数nickname校验");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"nickname is empty\""), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数nickname的值不存在
     * @throws Exception
     */
    @Test
    public void BusinessServiceQaExpertReadQaExpertMessageTestByParameterNickNameValueIsNotExist() throws Exception {
        String readqaexpertmessageUrl = null;
        BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO = null;
        String readqaexpertmessageResult = "";
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs= userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO = new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(id);
            //咨询者工号
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid()+"NotExistNickname");
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数nickname的值不存在");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 提问者依据BmAppid与AccessToken进行阅读留言信息
     * @throws Exception
     */
    @Test
    public void BusinessServiceQaExpertReadQaExpertMessageTestByBmAppidAndAccessToken() throws Exception {
        String readqaexpertmessageUrl = null;
        BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO = null;
        String readqaexpertmessageResult = "";
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            HashMap<String, String> hs= userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            readqaexpertmessageUrl = url + "/BusinessService/qaexpert/readqaexpertmessage";
            businessServiceQaExpertReadQaExpertMessageBO = new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setBmAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            businessServiceQaExpertReadQaExpertMessageBO.setId(id);
            //咨询者工号
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            log.info("readqaexpertmessageUrl 请求的参数=" + readqaexpertmessageUrl);
            log.info("businessServiceQaExpertReadQaExpertMessageBO 请求的参数=" + JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            readqaexpertmessageResult = HttpUtil.postGeneralUrl(readqaexpertmessageUrl, "application/json", JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO), "UTF-8");
            log.info("readqaexpertmessageResult 返回结果=" + JSON.parseObject(readqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("提问者依据BmAppid与AccessToken进行阅读留言信息");
            recordhttp.setUrl(readqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(businessServiceQaExpertReadQaExpertMessageBO));
            recordhttp.setResponse(readqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(readqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(readqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

}
