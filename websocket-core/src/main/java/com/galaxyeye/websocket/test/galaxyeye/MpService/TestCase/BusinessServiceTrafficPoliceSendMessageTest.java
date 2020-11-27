package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TrafficpoliceRoomRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceSendMessageBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.WxDataMessageSendBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


@Slf4j
@Component
public class BusinessServiceTrafficPoliceSendMessageTest extends BusinessServiceTrafficPoliceCreateRoomTest {
    @Autowired
    private BusinessServiceTrafficPoliceCreateRoomTest businessServiceTrafficPoliceCreateRoomTest;

    //获取表trafficpolice_room.id值
    public static Long RoomId;

    @Test
    public void sendmessage() throws Exception {
        String sendmessageUrl =null;
        TrafficPoliceSendMessageBO trafficPoliceSendMessageBO =null;
        String sendmessageResult ="";
        try{
            sendmessageUrl = url+"/BusinessService/trafficpolice/sendmessage";
            trafficPoliceSendMessageBO = new TrafficPoliceSendMessageBO();
            trafficPoliceSendMessageBO.setUid(Integer.valueOf(hs.get("uid")));
            //表trafficpolice_room.id
            trafficPoliceSendMessageBO.setRoomId(RoomId);
            trafficPoliceSendMessageBO.setContent("测试1");
            trafficPoliceSendMessageBO.setBmAppid("1.00002");
            trafficPoliceSendMessageBO.setAppid("1.00002");
            trafficPoliceSendMessageBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("sendmessageUrl 请求的参数=" + sendmessageUrl);
            log.info("trafficPoliceSendMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceSendMessageBO));
            sendmessageResult = HttpUtil.postGeneralUrl(sendmessageUrl, "application/json", JSON.toJSONString(trafficPoliceSendMessageBO), "UTF-8");
            log.info("sendmessageResult 返回结果=" + sendmessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者发送消息");
            recordhttp.setUrl(sendmessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceSendMessageBO));
            recordhttp.setResponse(sendmessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(sendmessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(sendmessageResult.contains("\"msg\":\"ok\""),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        businessServiceTrafficPoliceCreateRoomTest.initData();
        try{
            RoomId=Long.valueOf(businessServiceTrafficPoliceCreateRoomTest.BusinessServiceTrafficPoliceCreateRoomTestByGernal());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void destroyData() {

    }
}
