package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:19
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.VO.CreateQaExpertMessageVO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class BusinessServiceQaExpertCreateQaExpertMessageTest extends CIcServiceResource implements IQaExpertResource {
public final static String openid="ou0a55W6lWjrNL0dEGEqqHMq9x2E";

    /**
     * 设置咨询者的uid与token,获取创建会话的id
     * @return
     */
    public CreateQaExpertMessageVO getIDFromCreateqaexpertmessageTest(CreateQaExpertMessageBO createQaExpertMessageBO) {
        String createqaexpertmessageUrl = null;
        String createqaexpertResult = "";
        UserLoginBO commonUserLoginBO = new UserLoginBO();
        commonUserLoginBO.setUname(openid);
        commonUserLoginBO.setPasswd("");
        commonUserLoginBO.setAppid("4.00099");
        commonUserLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setOpenid(openid);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        commonUserLoginBO.setKeys(list);
        commonUserLoginBO.setThirdlogin(false);
        //获取uid=239192为咨询者
        HashMap<String, String> hs=userLoginTest(commonUserLoginBO);

        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setNickname(createQaExpertMessageBO.getNickname());
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setContent(createQaExpertMessageBO.getContent());
            createQaExpertMessageBO.setExpertAppid(createQaExpertMessageBO.getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertResult 返回结果=" + createqaexpertResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CreateQaExpertMessageVO createQaExpertMessageVO = JSON.parseObject(createqaexpertResult, CreateQaExpertMessageVO.class);
            return createQaExpertMessageVO;
        }
    }

    /**
     * * type=4 获取“客户咨询通知”模板，模板id=OOV1R_qUtbNHERr8AgMSDpmAFP6RDRSAfzTaKN_rLVw
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByOpenMethodAndUid() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        UserLoginBO commonUserLoginBO = new UserLoginBO();
        commonUserLoginBO.setUname(openid);
        commonUserLoginBO.setPasswd("");
        commonUserLoginBO.setAppid("4.00099");
        commonUserLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setOpenid(openid);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        commonUserLoginBO.setKeys(list);
        commonUserLoginBO.setThirdlogin(false);
        HashMap<String, String> hs=userLoginTest(commonUserLoginBO);
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            //工号用于区分是业务员还是专家
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者给专家的留言信息,方法createqaexpertmessage写在OpenMethod当中，不校验token");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 咨询者给专家的留言信息,方法createqaexpertmessage写在OpenMethod当中，不校验AccessToken
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByOpenMethodAndBmAppid() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        HashMap<String, String> hs=userLoginTest();
        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者给专家的留言信息,方法createqaexpertmessage写在OpenMethod当中，不校验token");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("id"), true);
        }
    }


    /**
     * 咨询者给专家的留言信息,createqaexpertmessage方法配置在authMethod当中，需要校验token
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByAuthMethodAndUid() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs = userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setAppid("100.00002");
            createQaExpertMessageBO.setBmAppid("100.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者给专家的留言信息,createqaexpertmessage方法配置在authMethod当中，需要校验token");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 咨询者给专家的留言信息,createqaexpertmessage方法配置在authMethod当中，参数token不传
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByAuthMethodAndUidAndNotExistParameterToken() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs = userLoginTest();
//            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            createQaExpertMessageBO.setAppid("100.00002");
            createQaExpertMessageBO.setBmAppid("100.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者给专家的留言信息,createqaexpertmessage方法配置在authMethod当中，参数token不传");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }



    /**
     * 咨询者给专家的留言信息,createqaexpertmessage方法配置在authMethod当中，参数AccessToken不传
     */
    @Test
    public void createqaexpertmessageTestByAuthMethodAndBmAppidAndNotExistParameterAccessToken() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs = userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken(bmAppids.get("100.00002"));
            createQaExpertMessageBO.setAppid("100.00002");
            createQaExpertMessageBO.setBmAppid("100.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者给专家的留言信息,createqaexpertmessage方法配置在authMethod当中，参数AccessToken不传");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("id"), true);
        }
    }

    /**
     * 错误的ExpertAppid值，获取不到专家appid，提示网络错误
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByErrorExpertAppid() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid()+"f");
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("错误的ExpertAppid值，获取不到专家appid，提示网络错误");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"net_error\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":113"), true);
        }
    }

    /**
     * 必填参数ExpertAppid校验
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByNotExistParameterExpertAppid() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
//            createQaExpertMessageBO.setExpertAppid(expertAppid);
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数ExpertAppid校验");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"expertAppid is empty\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数ExpertAppid校验
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByParameterExpertAppidValueIsEmpty() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid("");
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数ExpertAppid校验");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"expertAppid is empty\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Nickname校验
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByNotExistParameterNickname() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
//            createQaExpertMessageBO.setNickname(commonNickname);
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Nickname校验");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"nickname is empty\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Type校验
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByNotExistParameterType() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();

            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
//            createQaExpertMessageBO.setType(1);
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Type校验");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"type is missing\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 必填参数Content校验
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByNotExistParameterContent() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setNickname(getCommonNickname());
//            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Content校验");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"content is missing\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数Content传空
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByParameterContentValueIsEmpty() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setContent("");
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Content传空");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"content is empty\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":101"), true);
        }
    }

    /**
     * 参数Content传内容太长
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByParameterContentValueIsLarge() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setContent(imgParam);
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Content传内容太长");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":112"), true);
        }
    }

    /**
     * 参数Content传内容为表情
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByParameterContentValueIsEmotion() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        try {
           createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            HashMap<String, String> hs=userLoginTest();
            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setContent(str2);
            createQaExpertMessageBO.setNickname(getCommonNickname());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResult 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Content传内容为表情");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("id"), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":1"), true);
        }
    }

    /**
     * 参数Content传内容太长
     * @throws Exception
     */
    @Test
    public void createqaexpertmessageTestByParameterContentValueIsLarge1() {
        String createqaexpertmessageUrl = null;
        CreateQaExpertMessageBO createQaExpertMessageBO = null;
        String createqaexpertmessageResult = "";
        //专家，使用杨逸的微信登录关注的
        UserLoginBO commonUserLoginBO = new UserLoginBO();
        commonUserLoginBO.setUname("o2S4O5LvvKzDuNdK2IQa0x07Yo00");
        commonUserLoginBO.setPasswd("");
        //Appid=4.00071是人保AI助手小程序，用户点击首页顶部的获取订阅
        commonUserLoginBO.setAppid("4.00071");
        commonUserLoginBO.setKeytp("openid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setOpenid("o2S4O5LvvKzDuNdK2IQa0x07Yo00");
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        commonUserLoginBO.setKeys(list);
        commonUserLoginBO.setThirdlogin(false);
        HashMap<String, String> hs=userLoginTest(commonUserLoginBO);
        try {
            createqaexpertmessageUrl = url + "/BusinessService/qaexpert/createqaexpertmessage";
            createQaExpertMessageBO = new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            //设置咨询者uid
            createQaExpertMessageBO.setUid(hs.get("uid"));
//            createQaExpertMessageBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
//            createQaExpertMessageBO.setUid("511762");
            createQaExpertMessageBO.setAppid("1.00002");
            createQaExpertMessageBO.setBmAppid("1.00002");
            createQaExpertMessageBO.setType("1");
            String filePath = getFilePath("3.jpg");
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            createQaExpertMessageBO.setContent(imgParam.substring(0,20));
            createQaExpertMessageBO.setNickname(getCommonNickname());
            //专家的appid,普通用户提问题给专家，微信推送订阅消息给专家
            createQaExpertMessageBO.setExpertAppid("4.00075");
            log.info("createqaexpertmessageUrl 请求的参数=" + createqaexpertmessageUrl);
            log.info("createQaExpertMessageBO 请求的参数=" + JSON.toJSONString(createQaExpertMessageBO));
            createqaexpertmessageResult = HttpUtil.postGeneralUrl(createqaexpertmessageUrl, "application/json", JSON.toJSONString(createQaExpertMessageBO), "UTF-8");
            log.info("createqaexpertmessageResulAccApit 返回结果=" + JSON.parseObject(createqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数Content传内容太长");
            recordhttp.setUrl(createqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(createQaExpertMessageBO));
            recordhttp.setResponse(createqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(createqaexpertmessageResult.contains("\"msg\":\"db_error\""), true);
            Assert.assertEquals(createqaexpertmessageResult.contains("\"result\":112"), true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }

}
