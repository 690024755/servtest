package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceReadRoomBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceReplyMessageBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;


@Slf4j
@Component
public class BusinessServiceTrafficPoliceReadRoomTest extends BusinessServiceTrafficPoliceReplyMessageTest {


    /**
     * 咨询者阅读会话
     * @throws Exception
     */
    @Test
    public void readroom() throws Exception {
        String readroomUrl =null;
        TrafficPoliceReadRoomBO trafficPoliceReadRoomBO =null;
        String readroomResult ="";
        try{
            readroomUrl = url+"/BusinessService/trafficpolice/readroom";
            trafficPoliceReadRoomBO = new TrafficPoliceReadRoomBO();
            trafficPoliceReadRoomBO.setUid(Integer.valueOf(hs.get("uid")));
            //trafficpolice_room.id
            trafficPoliceReadRoomBO.setRoomId(trafficpoliceRoom.getId());
            trafficPoliceReadRoomBO.setBmAppid("1.00002");
            trafficPoliceReadRoomBO.setAppid("1.00002");
            trafficPoliceReadRoomBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("readroomUrl 请求的参数=" + readroomUrl);
            log.info("trafficPoliceReadRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceReadRoomBO));
            readroomResult = HttpUtil.postGeneralUrl(readroomUrl, "application/json", JSON.toJSONString(trafficPoliceReadRoomBO), "UTF-8");
            log.info("readroomResult 返回结果=" + readroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("用户阅读会话");
            recordhttp.setUrl(readroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReadRoomBO));
            recordhttp.setResponse(readroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(readroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(readroomResult.contains("\"msg\":\"ok\""),true);
        }
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void destroyData() {

    }
}
