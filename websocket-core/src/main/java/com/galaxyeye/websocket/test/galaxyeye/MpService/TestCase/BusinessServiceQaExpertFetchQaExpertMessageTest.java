package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:16
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.Inter.CIcServiceResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CreateQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.FetchQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.ReplyQaExpertMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.DTO.BusinessServiceQaExpertFetchQaExpertMessageDTO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.sort.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1、查询表qa_expert_board_reply与qa_expert_board数据
 * 2、
 */
@Slf4j
@Component
public class BusinessServiceQaExpertFetchQaExpertMessageTest extends CIcServiceResource implements IQaExpertResource {

    @Autowired
    private BusinessServiceQaExpertCreateQaExpertMessageTest businessServiceQaExpertCreateQaExpertMessageTest;

    @Autowired
    private BusinessServiceQaExpertReplyQaExpertMessageTest businessServiceQaExpertReplyQaExpertMessageTest;


    /**
     * 获取一条留言信息及该条信息所有回复消息，但是该条信息不存在
     * @throws Exception
     */
    @Test
    public void FetchQaExpertMessageTestByNotExistId() throws Exception {
        String fetchqaexpertmessageUrl = null;
        FetchQaExpertMessageBO fetchQaExpertMessageBO = null;
        String fetchqaexpertmessageResult = "";
        try {
            fetchqaexpertmessageUrl = url + "/BusinessService/qaexpert/fetchqaexpertmessage";
            fetchQaExpertMessageBO = new FetchQaExpertMessageBO();
            fetchQaExpertMessageBO.setBmAppid("1.00002");
            fetchQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            fetchQaExpertMessageBO.setAppid("1.00002");
            fetchQaExpertMessageBO.setId("1");
            log.info("fetchqaexpertmessageUrl 请求的参数=" + fetchqaexpertmessageUrl);
            log.info("fetchQaExpertMessageBO 请求的参数=" + JSON.toJSONString(fetchQaExpertMessageBO));
            fetchqaexpertmessageResult = HttpUtil.postGeneralUrl(fetchqaexpertmessageUrl, "application/json", JSON.toJSONString(fetchQaExpertMessageBO), "UTF-8");
            log.info("fetchqaexpertmessageResult 返回结果=" + JSON.parseObject(fetchqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取一条留言信息及该条信息所有回复消息，但是该条信息不存在");
            recordhttp.setUrl(fetchqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchQaExpertMessageBO));
            recordhttp.setResponse(fetchqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"msg\":\"QAboard_notexist\""), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"result\":500"), true);
        }
    }

    /**
     * 获取一条已存在的留言信息及该条信息所有回复消息
     * @throws Exception
     */
    @Test
    public void FetchQaExpertMessageTestByExistId() throws Exception {
        String fetchqaexpertmessageUrl = null;
        FetchQaExpertMessageBO fetchQaExpertMessageBO = null;
        String fetchqaexpertmessageResult = "";
        CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
        HashMap<String, String> hs= userLoginTest();
        createQaExpertMessageBO.setToken(hs.get("token"));
        createQaExpertMessageBO.setUid(hs.get("uid"));
        createQaExpertMessageBO.setNickname(getCommonCid());
        createQaExpertMessageBO.setContent(getCommonContent());
        createQaExpertMessageBO.setExpertAppid(getExpertAppid());
        String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
        ReplyQaExpertMessageBO replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
        replyQaExpertMessageBO.setNickname(getExpertCid());
        replyQaExpertMessageBO.setContent(getExpertContent());
        replyQaExpertMessageBO.setQaId(id);
        replyQaExpertMessageBO.setReplyType(1);
        businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
        businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
        try {
            fetchqaexpertmessageUrl = url + "/BusinessService/qaexpert/fetchqaexpertmessage";
            fetchQaExpertMessageBO = new FetchQaExpertMessageBO();
            fetchQaExpertMessageBO.setBmAppid("1.00002");
            fetchQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            fetchQaExpertMessageBO.setAppid("1.00002");
            fetchQaExpertMessageBO.setId(id);
            log.info("fetchqaexpertmessageUrl 请求的参数=" + fetchqaexpertmessageUrl);
            log.info("fetchQaExpertMessageBO 请求的参数=" + JSON.toJSONString(fetchQaExpertMessageBO));
            fetchqaexpertmessageResult = HttpUtil.postGeneralUrl(fetchqaexpertmessageUrl, "application/json", JSON.toJSONString(fetchQaExpertMessageBO), "UTF-8");
            log.info("fetchqaexpertmessageResult 返回结果=" + JSON.parseObject(fetchqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取一条已存在的留言信息及该条信息所有回复消息");
            recordhttp.setUrl(fetchqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchQaExpertMessageBO));
            recordhttp.setResponse(fetchqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            //校验咨询者
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("content"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("createdAt"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("nickname"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("qaId"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("repliedAt"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("type"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("uid"), true);
            //校验回复
            Assert.assertEquals(fetchqaexpertmessageResult.contains("reply"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("expert_no"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("replyType"), true);
        }
    }

    /**
     * 获取一条已存在的留言信息及该条信息无回复消息
     * @throws Exception
     */
    @Test
    public void FetchQaExpertMessageTestByExistIdAndNotReply() throws Exception {
        String fetchqaexpertmessageUrl = null;
        FetchQaExpertMessageBO fetchQaExpertMessageBO = null;
        String fetchqaexpertmessageResult = "";
        CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
        HashMap<String, String> hs= userLoginTest();
        createQaExpertMessageBO.setToken(hs.get("token"));
        createQaExpertMessageBO.setUid(hs.get("uid"));
        createQaExpertMessageBO.setNickname(getCommonCid());
        createQaExpertMessageBO.setContent(getCommonContent());
        createQaExpertMessageBO.setExpertAppid(getExpertAppid());
        String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
        ReplyQaExpertMessageBO replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
        replyQaExpertMessageBO.setNickname(getExpertCid());
        replyQaExpertMessageBO.setContent(getExpertContent());
        replyQaExpertMessageBO.setQaId(id);
        replyQaExpertMessageBO.setReplyType(1);
//        businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
        try {
            fetchqaexpertmessageUrl = url + "/BusinessService/qaexpert/fetchqaexpertmessage";
            fetchQaExpertMessageBO = new FetchQaExpertMessageBO();
            fetchQaExpertMessageBO.setBmAppid("1.00002");
            fetchQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            fetchQaExpertMessageBO.setAppid("1.00002");
            fetchQaExpertMessageBO.setId(id);
            log.info("fetchqaexpertmessageUrl 请求的参数=" + fetchqaexpertmessageUrl);
            log.info("fetchQaExpertMessageBO 请求的参数=" + JSON.toJSONString(fetchQaExpertMessageBO));
            fetchqaexpertmessageResult = HttpUtil.postGeneralUrl(fetchqaexpertmessageUrl, "application/json", JSON.toJSONString(fetchQaExpertMessageBO), "UTF-8");
            log.info("fetchqaexpertmessageResult 返回结果=" + JSON.parseObject(fetchqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取一条已存在的留言信息及该条信息无回复消息");
            recordhttp.setUrl(fetchqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchQaExpertMessageBO));
            recordhttp.setResponse(fetchqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("content"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("createdAt"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("nickname"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("qaId"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("repliedAt"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("reply"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("type"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("uid"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("reply"), true);
            Assert.assertEquals(!fetchqaexpertmessageResult.contains("expert_no"), true);
            Assert.assertEquals(!fetchqaexpertmessageResult.contains("replyType"), true);
        }
    }

    /**
     * 获取留言的所有回复消息是按照最近的留言信息创建时间进行降序排序
     * @throws Exception
     */
    @Test
    public void FetchQaExpertMessageTestByExistIdOrderByCreatedAtDes() throws Exception {
        String fetchqaexpertmessageUrl = null;
        FetchQaExpertMessageBO fetchQaExpertMessageBO = null;
        String fetchqaexpertmessageResult = "";
        CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
        HashMap<String, String> hs= userLoginTest();
        createQaExpertMessageBO.setToken(hs.get("token"));
        createQaExpertMessageBO.setUid(hs.get("uid"));
        createQaExpertMessageBO.setNickname(getCommonCid());
        createQaExpertMessageBO.setContent(getCommonContent());
        createQaExpertMessageBO.setExpertAppid(getExpertAppid());
        String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
        ReplyQaExpertMessageBO replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
        replyQaExpertMessageBO.setNickname(getExpertCid());
        replyQaExpertMessageBO.setContent(getExpertContent());
        replyQaExpertMessageBO.setQaId(id);
        replyQaExpertMessageBO.setReplyType(1);
        businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
        Thread.sleep(5000);
        businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
        try {
            fetchqaexpertmessageUrl = url + "/BusinessService/qaexpert/fetchqaexpertmessage";
            fetchQaExpertMessageBO = new FetchQaExpertMessageBO();
            fetchQaExpertMessageBO.setBmAppid("1.00002");
            fetchQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            fetchQaExpertMessageBO.setAppid("1.00002");
            fetchQaExpertMessageBO.setId(id);
            log.info("fetchqaexpertmessageUrl 请求的参数=" + fetchqaexpertmessageUrl);
            log.info("fetchQaExpertMessageBO 请求的参数=" + JSON.toJSONString(fetchQaExpertMessageBO));
            fetchqaexpertmessageResult = HttpUtil.postGeneralUrl(fetchqaexpertmessageUrl, "application/json", JSON.toJSONString(fetchQaExpertMessageBO), "UTF-8");
            log.info("fetchqaexpertmessageResult 返回结果=" + JSON.parseObject(fetchqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取留言的所有回复消息是按照最近的留言信息创建时间进行降序排序");
            recordhttp.setUrl(fetchqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchQaExpertMessageBO));
            recordhttp.setResponse(fetchqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("content"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("createdAt"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("nickname"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("qaId"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("repliedAt"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("type"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("uid"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("reply"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("expert_no"), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("replyType"), true);
            BusinessServiceQaExpertFetchQaExpertMessageDTO businessServiceQaExpertFetchQaExpertMessageDTO=JSON.parseObject(fetchqaexpertmessageResult, BusinessServiceQaExpertFetchQaExpertMessageDTO.class);
            List<BusinessServiceQaExpertFetchQaExpertMessageDTO.ReplyBean> replyBeanList=businessServiceQaExpertFetchQaExpertMessageDTO.getQaDetail().getReply();
            List<String> list=replyBeanList.stream().map(x->x.getCreatedAt()).collect(Collectors.toList());
            log.info("list CreatedAt="+JSON.toJSONString(list));
            Assert.assertTrue(SortUtil.IsSortByDateStr(list, list.size()));
        }
    }

    /**
     * 必填参数id校验
     * @throws Exception
     */
    @Test
    public void FetchQaExpertMessageTestByNotExistParameterId() throws Exception {
        String fetchqaexpertmessageUrl = null;
        FetchQaExpertMessageBO fetchQaExpertMessageBO = null;
        String fetchqaexpertmessageResult = "";
        CreateQaExpertMessageBO createQaExpertMessageBO=new CreateQaExpertMessageBO();
        HashMap<String, String> hs= userLoginTest();
        createQaExpertMessageBO.setToken(hs.get("token"));
        createQaExpertMessageBO.setUid(hs.get("uid"));
        createQaExpertMessageBO.setNickname(getCommonCid());
        createQaExpertMessageBO.setContent(getCommonContent());
        createQaExpertMessageBO.setExpertAppid(getExpertAppid());
        String id=businessServiceQaExpertCreateQaExpertMessageTest.getIDFromCreateqaexpertmessageTest(createQaExpertMessageBO).getId();
        ReplyQaExpertMessageBO replyQaExpertMessageBO = new ReplyQaExpertMessageBO();
        replyQaExpertMessageBO.setNickname(getExpertCid());
        replyQaExpertMessageBO.setContent(getExpertContent());
        replyQaExpertMessageBO.setQaId(id);
        replyQaExpertMessageBO.setReplyType(1);
        businessServiceQaExpertReplyQaExpertMessageTest.ReplyQaExpertMessageTestByGernal(replyQaExpertMessageBO);
        try {
            fetchqaexpertmessageUrl = url + "/BusinessService/qaexpert/fetchqaexpertmessage";
            fetchQaExpertMessageBO = new FetchQaExpertMessageBO();
            fetchQaExpertMessageBO.setBmAppid("1.00002");
            fetchQaExpertMessageBO.setAccessToken(bmAppids.get("1.00002"));
            fetchQaExpertMessageBO.setAppid("1.00002");
//            fetchQaExpertMessageBO.setId(id);
            log.info("fetchqaexpertmessageUrl 请求的参数=" + fetchqaexpertmessageUrl);
            log.info("fetchQaExpertMessageBO 请求的参数=" + JSON.toJSONString(fetchQaExpertMessageBO));
            fetchqaexpertmessageResult = HttpUtil.postGeneralUrl(fetchqaexpertmessageUrl, "application/json", JSON.toJSONString(fetchQaExpertMessageBO), "UTF-8");
            log.info("fetchqaexpertmessageResult 返回结果=" + JSON.parseObject(fetchqaexpertmessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数id校验");
            recordhttp.setUrl(fetchqaexpertmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchQaExpertMessageBO));
            recordhttp.setResponse(fetchqaexpertmessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"msg\":\"QAboard_notexist\""), true);
            Assert.assertEquals(fetchqaexpertmessageResult.contains("\"result\":500"), true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


}
