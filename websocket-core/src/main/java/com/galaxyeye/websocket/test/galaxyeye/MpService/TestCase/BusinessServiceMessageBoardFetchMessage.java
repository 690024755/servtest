package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 16:48
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/23日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.FetchMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.VO.CreateMessageVO;
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
public class BusinessServiceMessageBoardFetchMessage extends BaseTest {

    @Autowired
    private BusinessServiceMessageBoardReplyMessageTest businessServiceMessageBoardReplyMessageTest;

    @Autowired
    private BusinessServiceMessageBoardCreateMessageTest businessServiceMessageBoardCreateMessageTest;

    /**
     * 获取单条消息详细信息
     * @throws Exception
     */
    @Test
    @Deprecated
    public void fetchmessageTest() throws Exception {
        String fetchmessageUrl =null;
        FetchMessageBO fetchMessageBO =null;
        String fetchMessageResult ="";
        try{
            fetchmessageUrl = url+"/BusinessService/messageboard/fetchmessage";
            fetchMessageBO = new FetchMessageBO();
            CreateMessageVO createMessageVO=businessServiceMessageBoardCreateMessageTest.getMessageIDTest();//创建留言板
            businessServiceMessageBoardReplyMessageTest.setReplymessageByGernal(createMessageVO);
            businessServiceMessageBoardReplyMessageTest.setReplymessageByGernal(createMessageVO);
            fetchMessageBO.setId(String.valueOf(createMessageVO.getId()));
            fetchMessageBO.setAccessToken(bmAppids.get("3.00014"));
            fetchMessageBO.setBmAppid("3.00014");
            fetchMessageBO.setAppid("1.00002");
            log.info("fetchmessageUrl 请求的参数=" + fetchmessageUrl);
            log.info("fetchMessageBO 请求的参数=" + JSON.toJSONString(fetchMessageBO));
            fetchMessageResult = HttpUtil.postGeneralUrl(fetchmessageUrl, "application/json", JSON.toJSONString(fetchMessageBO), "UTF-8");
            log.info("fetchMessageResult 返回结果=" + JSON.parseObject(fetchMessageResult));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取单条消息详细信息");
            recordhttp.setUrl(fetchmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(fetchMessageBO));
            recordhttp.setResponse(fetchMessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(fetchMessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(fetchMessageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(fetchMessageResult.contains("messageDetail"),true);
            Assert.assertEquals(fetchMessageResult.contains("title"),true);
            Assert.assertEquals(fetchMessageResult.contains("reply"),true);
            Assert.assertEquals(fetchMessageResult.contains("readAt"),true);
            Assert.assertEquals(fetchMessageResult.contains("images"),true);
            Assert.assertEquals(fetchMessageResult.contains("createdAt"),true);
            Assert.assertEquals(fetchMessageResult.contains("content"),true);
            Assert.assertEquals(fetchMessageResult.contains("contact"),true);
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
