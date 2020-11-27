package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:05
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.galaxyeye.websocket.application.repository.TbProAccountRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.BusinessServiceQaExpertReadQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.GetQaExpertMessaGeListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.ReplyQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.GetQaExpertMessaGeListDTO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.tool.date.DateTool;
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
import java.util.stream.Collectors;


@Slf4j
@Component
public class BusinessServiceQaExpertGetQaExpertMessageListTest extends CIcServiceResource implements IQaExpertResource {

    @Autowired
    private BusinessServiceQaExpertCreateQaExpertMessageTest businessServiceQaExpertCreateQaExpertMessageTest;

    @Autowired
    private BusinessServiceQaExpertReplyQaExpertMessageTest businessServiceQaExpertReplyQaExpertMessageTest;

    @Autowired
    private BusinessServiceQaExpertReadQaExpertMessageTest businessServiceQaExpertReadQaExpertMessageTest;

    /**
     * 使用BmAppid与AccessToken获取所有留言信息列表
     * @throws Exception
     */
    @Test
    public void GetQaExpertMessaGeListTestByBmAppidAndAccessToken() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        try {
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setAccessToken(bmAppids.get("1.00002"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken获取所有留言信息列表");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("list"), true);
        }
    }

    /**
     * 使用uid与token获取所有留言信息列表
     * @throws Exception
     */
    @Test
    public void GetQaExpertMessaGeListTestByUidAndToken() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用uid与token获取所有留言信息列表");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertTrue(JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list").size()>0);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("list"), true);
        }
    }

    /**
     * Begin时间大于End时间，获取会话消息
     * @throws Exception
     */
    @Test
    public void GetQaExpertMessaGeListTestByBeginMoreThanEnd() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin(getTomorrowDay());
            getQaExpertMessaGeListBO.setEnd("2019-11-10 11:19:00");
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("Begin时间大于End时间，获取会话消息");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":101"), true);
        }
    }


    /**
     * 参数值Replied=0，返回已回复与未回复的消息
     * @throws Exception
     */
    @Test
    public void GetQaExpertMessaGeListTestByParameterRepliedValueIsZero() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Replied=0，返回已回复与未回复的消息");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertTrue(JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list").size()>0);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("list"), true);
        }
    }

    /**
     * 参数值Replied=1，返回未回复的消息
     * @throws Exception
     */
    @Test
    public void GetQaExpertMessaGeListTestByParameterRepliedValueIsOne() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(1);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Replied=1，返回未回复的消息");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            JSONArray jSONArray=JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> list=jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            Assert.assertTrue(jSONArray.size()>0);
            Boolean flag=false;
            Assert.assertEquals(getqaexpertmessagelistResult.contains("list"), true);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:list
                 ) {
                        if(!getQaExpertMessaGeListDTO.isReplied()==false){
                            Assert.assertTrue(flag);
                        }
            }
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterRepliedValueIsTwo() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setQaId(id);
            replyQaExpertMessageBO.setReplyType(1);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(2);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Replied=2，返回已回复的消息");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            JSONArray jSONArray=JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> list=jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            Assert.assertTrue(jSONArray.size()>0);
            Boolean flag=false;
            Assert.assertEquals(getqaexpertmessagelistResult.contains("list"), true);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:list
            ) {
                if(!getQaExpertMessaGeListDTO.isReplied()==true){
                    Assert.assertTrue(flag);
                }
            }
        }
    }

    /**
     * 参数值Character=0(不是专家也不是普通用户)，获取不限身份的留言信息
     * @throws Exception
     */
    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsZero() throws Exception {
        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setQaId(id);
            replyQaExpertMessageBO.setReplyType(1);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(0);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Character=0(不是专家也不是普通用户)，获取不限身份的留言信息");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"parameter_error\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":101"), true);
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsOneAndParameterRepliedValueIsZero() throws Exception {
        String expertReplyAndUnReadId="";
        String expertReplyAndReadId="";
        String expertNotReplyAndUnReadId="";
        String expertNotReplyAndReadId="";

        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        String keyValue="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";
        userLoginBO.setUname(keyValue);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("4.00047");
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setUnionid(keyValue);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        HashMap<String, String> experths= userLoginTest(userLoginBO);
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            //普通用户提问给专家
            expertReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setReplyType(1);

            BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO=new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertNotReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndUnReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(experths.get("uid"));
            getQaExpertMessaGeListBO.setToken(experths.get("token"));
            getQaExpertMessaGeListBO.setNickname(getExpertCid());
            getQaExpertMessaGeListBO.setCharacter(1);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe(" 1、参数值Character=1(专家身份)，专家身份的人获取未回复与已回复留言信息，提问者未阅读的留言板id但专家给提问者留言2条消息\n" +
                    " 2、参数值Character=1(专家身份)，专家身份的人获取未回复与已回复留言信息，提问者已阅读的留言板id但专家给提问者留言1条消息\n" +
                    " 3、参数值Character=1(专家身份)，专家身份的人获取未回复与已回复留言信息，专家未回复的留言板id且提问者未阅读该条留言板\n" +
                    " 4、参数值Character=1(专家身份)，专家身份的人获取未回复与已回复留言信息，专家未回复的留言板id且提问者已阅读该条留言板");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            JSONArray jSONArray = JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> listResult = jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:listResult
                 ) {
                if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==2);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
            }
            Assert.assertTrue(listResult.size() > 0);
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsOneAndParameterRepliedValueIsOne() throws Exception {
        String expertReplyAndUnReadId="";
        String expertReplyAndReadId="";
        String expertNotReplyAndUnReadId="";
        String expertNotReplyAndReadId="";

        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        String keyValue="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";
        userLoginBO.setUname(keyValue);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("4.00047");
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setUnionid(keyValue);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        HashMap<String, String> experths= userLoginTest(userLoginBO);
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            //普通用户提问给专家
            expertReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setReplyType(1);

            BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO=new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertNotReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndUnReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(experths.get("uid"));
            getQaExpertMessaGeListBO.setToken(experths.get("token"));
            getQaExpertMessaGeListBO.setNickname(getExpertCid());
            getQaExpertMessaGeListBO.setCharacter(1);
            getQaExpertMessaGeListBO.setReplied(1);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("1、参数值Character=1(专家身份)，专家身份的人获取未回复留言信息，提问者未阅读的留言板id但专家给提问者留言2条消息\n" +
                    " 2、参数值Character=1(专家身份)，专家身份的人获取未回复留言信息，提问者已阅读的留言板id但专家给提问者留言1条消息\n" +
                    " 3、参数值Character=1(专家身份)，专家身份的人获取未回复留言信息，专家未回复的留言板id且提问者未阅读该条留言板\n" +
                    " 4、参数值Character=1(专家身份)，专家身份的人获取未回复留言信息，专家未回复的留言板id且提问者已阅读该条留言板");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            JSONArray jSONArray = JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> listResult = jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:listResult
            ) {
                if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
            }
            Assert.assertTrue(listResult.size() > 0);
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsOneAndParameterRepliedValueIsTwo() throws Exception {
        String expertReplyAndUnReadId="";
        String expertReplyAndReadId="";
        String expertNotReplyAndUnReadId="";
        String expertNotReplyAndReadId="";

        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        UserLoginBO userLoginBO = new UserLoginBO();
        String keyValue="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";
        userLoginBO.setUname(keyValue);
        userLoginBO.setPasswd("");
        userLoginBO.setAppid("4.00047");
        userLoginBO.setKeytp("unionid");
        UserLoginBO.Keys keys = new UserLoginBO.Keys();
        keys.setInfo("");
        keys.setUnionid(keyValue);
        List<UserLoginBO.Keys> list = new ArrayList<>();
        list.add(keys);
        userLoginBO.setKeys(list);
        userLoginBO.setThirdlogin(false);
        HashMap<String, String> experths= userLoginTest(userLoginBO);
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            expertReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setReplyType(1);

            BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO=new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");

            businessServiceQaExpertReadQaExpertMessageBO.setId(expertNotReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndUnReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);

            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(experths.get("uid"));
            getQaExpertMessaGeListBO.setToken(experths.get("token"));
            getQaExpertMessaGeListBO.setNickname(getExpertCid());
            getQaExpertMessaGeListBO.setCharacter(1);
            getQaExpertMessaGeListBO.setReplied(2);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe(" 1、参数值Character=1(专家身份)，专家身份的人获取已回复留言信息，提问者未阅读的留言板id但专家给提问者留言2条消息\n" +
                    " 2、参数值Character=1(专家身份)，专家身份的人获取已回复留言信息，提问者已阅读的留言板id但专家给提问者留言1条消息\n" +
                    " 3、参数值Character=1(专家身份)，专家身份的人获取已回复留言信息，专家未回复的留言板id且提问者未阅读该条留言板\n" +
                    " 4、参数值Character=1(专家身份)，专家身份的人获取已回复留言信息，专家未回复的留言板id且提问者已阅读该条留言板");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            JSONArray jSONArray = JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> listResult = jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:listResult
            ) {
                if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==2);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
            }
            Assert.assertTrue(listResult.size() > 0);
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsTwoAndParameterRepliedValueIsZero() throws Exception {
        String expertReplyAndUnReadId="";
        String expertReplyAndReadId="";
        String expertNotReplyAndUnReadId="";
        String expertNotReplyAndReadId="";

        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            expertReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setReplyType(1);

            BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO=new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");

            businessServiceQaExpertReadQaExpertMessageBO.setId(expertNotReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndUnReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(0);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Character=2（普通用户身份），普通用户身份的人获取已回复与未回复留言信息，提问者未阅读的留言板id但专家给提问者留言2条消息\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取已回复与未回复留言信息，提问者已阅读的留言板id但专家给提问者留言1条消息\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取已回复与未回复留言信息，专家未回复的留言板id且提问者未阅读该条留言板\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取已回复与未回复留言信息，专家未回复的留言板id且提问者已阅读该条留言板");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            JSONArray jSONArray=JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> listResult=jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            Assert.assertTrue(listResult.size() > 0);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:listResult
            ) {
                if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==2);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
            }
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsTwoAndParameterRepliedValueIsOne() throws Exception {
        String expertReplyAndUnReadId="";
        String expertReplyAndReadId="";
        String expertNotReplyAndUnReadId="";
        String expertNotReplyAndReadId="";

        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            expertReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setReplyType(1);

            BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO=new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertNotReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndUnReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(1);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Character=2（普通用户身份），普通用户身份的人获取专家未回复留言信息，提问者未阅读的留言板id但专家给提问者留言2条消息\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取专家未回复留言信息，提问者已阅读的留言板id但专家给提问者留言1条消息\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取专家未回复留言信息，专家未回复的留言板id且提问者未阅读该条留言板\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取专家未回复留言信息，专家未回复的留言板id且提问者已阅读该条留言板");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            JSONArray jSONArray=JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> listResult=jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            Assert.assertTrue(listResult.size() > 0);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:listResult
            ) {
                if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertNotReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertFalse(getQaExpertMessaGeListDTO.isReplied());
                }
            }
        }
    }


    @Test
    public void GetQaExpertMessaGeListTestByParameterCharacterValueIsTwoAndParameterRepliedValueIsTwo() throws Exception {
        String expertReplyAndUnReadId="";
        String expertReplyAndReadId="";
        String expertNotReplyAndUnReadId="";
        String expertNotReplyAndReadId="";

        String getqaexpertmessagelistUrl = null;
        GetQaExpertMessaGeListBO getQaExpertMessaGeListBO = null;
        String getqaexpertmessagelistResult = "";
        HashMap<String, String> hs= userLoginTest();
        try {
            CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
            createQaExpertMessageBO.setToken(hs.get("token"));
            createQaExpertMessageBO.setUid(hs.get("uid"));
            createQaExpertMessageBO.setNickname(getCommonCid());
            createQaExpertMessageBO.setContent(getCommonContent());
            createQaExpertMessageBO.setExpertAppid(getExpertAppid());
            expertReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndUnReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            expertNotReplyAndReadId=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
            ReplyQaExpertMessageBO replyQaExpertMessageBO=new ReplyQaExpertMessageBO();
            replyQaExpertMessageBO.setNickname(getExpertCid());
            replyQaExpertMessageBO.setContent(getExpertContent());
            replyQaExpertMessageBO.setReplyType(1);

            BusinessServiceQaExpertReadQaExpertMessageBO businessServiceQaExpertReadQaExpertMessageBO=new BusinessServiceQaExpertReadQaExpertMessageBO();
            businessServiceQaExpertReadQaExpertMessageBO.setToken(hs.get("token"));
            businessServiceQaExpertReadQaExpertMessageBO.setUid(hs.get("uid"));
            businessServiceQaExpertReadQaExpertMessageBO.setNickname(getCommonCid());
            businessServiceQaExpertReadQaExpertMessageBO.setAppid("1.00002");
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertNotReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndUnReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            replyQaExpertMessageBO.setQaId(expertReplyAndReadId);
            businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
            businessServiceQaExpertReadQaExpertMessageBO.setId(expertReplyAndReadId);
            businessServiceQaExpertReadQaExpertMessageTest.BusinessServiceQaExpertReadQaExpertMessageTestByGernal(businessServiceQaExpertReadQaExpertMessageBO);
            getqaexpertmessagelistUrl = url + "/BusinessService/qaexpert/getqaexpertmessagelist";
            getQaExpertMessaGeListBO = new GetQaExpertMessaGeListBO();
            getQaExpertMessaGeListBO.setAppid("1.00002");
            getQaExpertMessaGeListBO.setBmAppid("1.00002");
            getQaExpertMessaGeListBO.setUid(hs.get("uid"));
            getQaExpertMessaGeListBO.setToken(hs.get("token"));
            getQaExpertMessaGeListBO.setNickname(getCommonCid());
            getQaExpertMessaGeListBO.setCharacter(2);
            getQaExpertMessaGeListBO.setReplied(2);
            getQaExpertMessaGeListBO.setBegin("2019-11-10 11:19:00");
            getQaExpertMessaGeListBO.setEnd(getTomorrowDay());
            log.info("getqaexpertmessagelistUrl 请求的参数=" + getqaexpertmessagelistUrl);
            log.info("getQaExpertMessaGeListBO 请求的参数=" + JSON.toJSONString(getQaExpertMessaGeListBO));
            getqaexpertmessagelistResult = HttpUtil.postGeneralUrl(getqaexpertmessagelistUrl, "application/json", JSON.toJSONString(getQaExpertMessaGeListBO), "UTF-8");
            log.info("getqaexpertmessagelistResult 返回结果=" + JSON.parseObject(getqaexpertmessagelistResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("参数值Character=2（普通用户身份），普通用户身份的人获取专家已回复留言信息，提问者未阅读的留言板id但专家给提问者留言2条消息\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取专家已回复留言信息，提问者已阅读的留言板id但专家给提问者留言1条消息\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取专家已回复留言信息，专家未回复的留言板id且提问者未阅读该条留言板\n" +
                    " 参数值Character=2（普通用户身份），普通用户身份的人获取专家已回复留言信息，专家未回复的留言板id且提问者已阅读该条留言板");
            recordhttp.setUrl(getqaexpertmessagelistUrl);
            recordhttp.setRequest(JSON.toJSONString(getQaExpertMessaGeListBO));
            recordhttp.setResponse(getqaexpertmessagelistResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(getqaexpertmessagelistResult.contains("\"result\":1"), true);
            JSONArray jSONArray=JSON.parseObject(getqaexpertmessagelistResult).getJSONArray("list");
            List<GetQaExpertMessaGeListDTO> listResult=jSONArray.toJavaList(GetQaExpertMessaGeListDTO.class);
            Assert.assertTrue(listResult.size() > 0);
            for (GetQaExpertMessaGeListDTO getQaExpertMessaGeListDTO:listResult
            ) {
                if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndUnReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==2);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
                else if(getQaExpertMessaGeListDTO.getStrId().equals(expertReplyAndReadId)){
                    Assert.assertTrue(getQaExpertMessaGeListDTO.getUnreadCount()==0);
                    Assert.assertTrue(getQaExpertMessaGeListDTO.isReplied());
                }
            }
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    /**
     * 获取明天时间
     * @return
     */
    private String getTomorrowDay(){
        return DateTool.addDays(new Date(),1);
    }

}
