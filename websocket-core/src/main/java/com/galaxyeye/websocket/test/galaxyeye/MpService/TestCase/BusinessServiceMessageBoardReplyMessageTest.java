package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 17:11
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/22日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.ReplyMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.VO.CreateMessageVO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.VO.ReplyMessageVO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;


@Component
@Slf4j
@Deprecated
public class BusinessServiceMessageBoardReplyMessageTest extends BaseTest {
    @Autowired
    private BusinessServiceMessageBoardCreateMessageTest businessServiceMessageBoardCreateMessageTest;

    /**
     * 通用性回复消息
     * @param createMessageVO
     * @return
     * @throws Exception
     */
    @Deprecated
    public ReplyMessageVO setReplymessageByGernal(CreateMessageVO createMessageVO) throws Exception {
        String replymessageUrl = null;
        ReplyMessageBO replyMessageBO = null;
        String replymessageResult = "";
        try {
            replymessageUrl = url + "/BusinessService/messageboard/replymessage";
            replyMessageBO = new ReplyMessageBO();
            replyMessageBO.setContent("已读测试内容4");
            replyMessageBO.setMessageboardId(String.valueOf(createMessageVO.getId()));
            replyMessageBO.setReplyType("1");
            replyMessageBO.setNickname("haha");
            replyMessageBO.setBmAppid("3.00014");
            replyMessageBO.setAppid("1.00002");
            replyMessageBO.setAccessToken(bmAppids.get("3.00014"));
            log.info("replymessageUrl 请求的参数=" + JSON.toJSONString(replyMessageBO));
            log.info("replyMessageBO 请求的参数=" + JSON.toJSONString(replyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(replyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReplyMessageVO replyMessageVO = JSON.parseObject(replymessageResult, ReplyMessageVO.class);
            return replyMessageVO;
        }
    }


    /**
     * 交警回复驾驶人员的回复消息，设置ReplyType=1
     * @throws Exception
     */
    @Test
    @Deprecated
    public void BusinessServiceMessageBoardReplyMessageTestByPoliceReply() throws Exception {
        String replymessageUrl = null;
        ReplyMessageBO replyMessageBO = null;
        String replymessageResult = "";
        try {
            replymessageUrl = url + "/BusinessService/messageboard/replymessage";
            replyMessageBO = new ReplyMessageBO();
            replyMessageBO.setContent("已读测试内容4");
            //针对哪条消息进行回复
            replyMessageBO.setMessageboardId(String.valueOf(businessServiceMessageBoardCreateMessageTest.getMessageIDTest().getId()));
            replyMessageBO.setReplyType("1");
            replyMessageBO.setNickname("haha");
            replyMessageBO.setBmAppid("3.00014");
            replyMessageBO.setAppid("1.00002");
            replyMessageBO.setAccessToken(bmAppids.get("3.00014"));
            log.info("replymessageUrl 请求的参数=" + JSON.toJSONString(replyMessageBO));
            log.info("replyMessageBO 请求的参数=" + JSON.toJSONString(replyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(replyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + JSON.parseObject(replymessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("交警回复驾驶人员的回复消息，设置ReplyType=1");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replymessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replymessageResult.contains("id"), true);
        }
    }


    /**
     * 通人员回复咨询者消息，设置ReplyType=2
     * @throws Exception
     */
    @Deprecated
    @Test
    public void BusinessServiceMessageBoardReplyMessageTestByPeopleReply() throws Exception {
        String replymessageUrl = null;
        ReplyMessageBO replyMessageBO = null;
        String replymessageResult = "";
        try {
            replymessageUrl = url + "/BusinessService/messageboard/replymessage";
            replyMessageBO = new ReplyMessageBO();
            replyMessageBO.setContent("已读测试内容4");
            //针对哪条消息进行回复
            replyMessageBO.setMessageboardId(String.valueOf(businessServiceMessageBoardCreateMessageTest.getMessageIDTest().getId()));
            replyMessageBO.setReplyType("2");
            replyMessageBO.setNickname("haha");
            replyMessageBO.setBmAppid("3.00014");
            replyMessageBO.setAppid("1.00002");
            replyMessageBO.setAccessToken(bmAppids.get("3.00014"));
            log.info("replymessageUrl 请求的参数=" + JSON.toJSONString(replyMessageBO));
            log.info("replyMessageBO 请求的参数=" + JSON.toJSONString(replyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(replyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + JSON.parseObject(replymessageResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("通人员回复咨询者消息，设置ReplyType=2");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(replyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(replymessageResult.contains("\"result\":1"), true);
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"ok\""), true);
            Assert.assertEquals(replymessageResult.contains("id"), true);
        }
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {

    }

    @Override
    public void destroyData() {

    }

}
