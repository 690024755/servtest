package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:12
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceGetRoomListBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;

@Component
@Slf4j
public class BusinessServiceTrafficPoliceGetRoomListTest extends BaseTest {

    /**
      获取会话列表的通用性测试
     * @throws Exception
     */
    @Test
    public void getroomlistByGernal(TrafficPoliceGetRoomListBO trafficPoliceGetRoomListBO) throws Exception {
        String getroomlistUrl =null;
        String getroomlistResult ="";
        try{
            getroomlistUrl = url+"/BusinessService/trafficpolice/getroomlist";
            log.info("getroomlistUrl 请求的参数=" + getroomlistUrl);
            log.info("trafficPoliceGetRoomListBO 请求的参数=" + JSON.toJSONString(trafficPoliceGetRoomListBO));
            getroomlistResult = HttpUtil.postGeneralUrl(getroomlistUrl, "application/json", JSON.toJSONString(trafficPoliceGetRoomListBO), "UTF-8");
            log.info("getroomlistResult 返回结果=" + getroomlistResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取会话列表的通用性测试");
            recordhttp.setUrl(getroomlistUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceGetRoomListBO));
            recordhttp.setResponse(getroomlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getroomlistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getroomlistResult.contains("content"),true);
            Assert.assertEquals(getroomlistResult.contains("createdAt"),true);
            Assert.assertEquals(getroomlistResult.contains("imgList"),true);
            Assert.assertEquals(getroomlistResult.contains("isFinished"),true);
            Assert.assertEquals(getroomlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getroomlistResult.contains("replyList"),true);
        }
    }


    /**
        根据请求参数uid与token获取会话列表,uid与token不对应，可以获取该roomid会话消息
     * @throws Exception
     */
    @Test
    public void getroomlistByUidAndToken() throws Exception {
        String getroomlistUrl =null;
        TrafficPoliceGetRoomListBO trafficPoliceGetRoomListBO =null;
        String getroomlistResult ="";
        HashMap<String,String> hs= userLoginTest();
        try{
            getroomlistUrl = url+"/BusinessService/trafficpolice/getroomlist";
            trafficPoliceGetRoomListBO = new TrafficPoliceGetRoomListBO();
            //trafficpolice_room.id或者trafficpolice_message.room_id
            trafficPoliceGetRoomListBO.setId(1218074641600352256L);
            trafficPoliceGetRoomListBO.setToken(hs.get("token"));
            trafficPoliceGetRoomListBO.setUid(225883);
            trafficPoliceGetRoomListBO.setBmAppid("1.00002");
            trafficPoliceGetRoomListBO.setAppid("1.00002");
//        trafficPoliceGetRoomListBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            log.info("getroomlistUrl 请求的参数=" + getroomlistUrl);
            log.info("trafficPoliceGetRoomListBO 请求的参数=" + JSON.toJSONString(trafficPoliceGetRoomListBO));
            getroomlistResult = HttpUtil.postGeneralUrl(getroomlistUrl, "application/json", JSON.toJSONString(trafficPoliceGetRoomListBO), "UTF-8");
            log.info("getroomlistResult 返回结果=" + getroomlistResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据请求参数uid与token获取会话列表,uid与token不对应，可以获取该roomid会话消息");
            recordhttp.setUrl(getroomlistUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceGetRoomListBO));
            recordhttp.setResponse(getroomlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getroomlistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getroomlistResult.contains("content"),true);
            Assert.assertEquals(getroomlistResult.contains("createdAt"),true);
            Assert.assertEquals(getroomlistResult.contains("imgList"),true);
            Assert.assertEquals(getroomlistResult.contains("isFinished"),true);
            Assert.assertEquals(getroomlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getroomlistResult.contains("replyList"),true);
        }
    }

    /**
     根据不存在的roomId，获取会话列表
     * @throws Exception
     */
    @Test
    public void getroomlistByBmAppidAndAccessToken() throws Exception {
        String getroomlistUrl =null;
        TrafficPoliceGetRoomListBO trafficPoliceGetRoomListBO =null;
        String getroomlistResult ="";
        try{
            getroomlistUrl = url+"/BusinessService/trafficpolice/getroomlist";
            trafficPoliceGetRoomListBO = new TrafficPoliceGetRoomListBO();
            //trafficpolice_room.id或者trafficpolice_message.room_id
            trafficPoliceGetRoomListBO.setId(1L);
            trafficPoliceGetRoomListBO.setUid(225883);
            trafficPoliceGetRoomListBO.setBmAppid("1.00002");
            trafficPoliceGetRoomListBO.setAppid("1.00002");
            trafficPoliceGetRoomListBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            trafficPoliceGetRoomListBO.setSeq("abc");
            log.info("getroomlistUrl 请求的参数=" + getroomlistUrl);
            log.info("trafficPoliceGetRoomListBO 请求的参数=" + JSON.toJSONString(trafficPoliceGetRoomListBO));
            getroomlistResult = HttpUtil.postGeneralUrl(getroomlistUrl, "application/json", JSON.toJSONString(trafficPoliceGetRoomListBO), "UTF-8");
            log.info("getroomlistResult 返回结果=" + getroomlistResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("根据不存在的roomId，获取会话列表");
            recordhttp.setUrl(getroomlistUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceGetRoomListBO));
            recordhttp.setResponse(getroomlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getroomlistResult.contains("traffic_roomid_notexist"),true);
        }

    }



    /**
     * 请求参数uid不传，获取会话roomid
     * @throws Exception
     */
    @Test
    public void getroomlistByNotExistParameterUid() throws Exception {
        String getroomlistUrl =null;
        TrafficPoliceGetRoomListBO trafficPoliceGetRoomListBO =null;
        String getroomlistResult ="";
        try{
            getroomlistUrl = url+"/BusinessService/trafficpolice/getroomlist";
            trafficPoliceGetRoomListBO = new TrafficPoliceGetRoomListBO();
            trafficPoliceGetRoomListBO.setId(1218074641600352256L);
//        trafficPoliceGetRoomListBO.setUid(1);
            trafficPoliceGetRoomListBO.setBmAppid("1.00002");
            trafficPoliceGetRoomListBO.setAppid("1.00002");
//        trafficPoliceGetRoomListBO.setToken("6DQNyP5-QoKyhwSrcflU29WPI86kSi5gmso7yb_O3qPzy-CORkbUNxD_1mDP_yu56gLJ3BDZ1l2M9FQs6elSdAha3Z0vOm8p3jc-rDzDe7-HjqtF-NlPMEUVllvXlnMJ9NKeZ_q8Nxd8UrM7wSkySg==");
            trafficPoliceGetRoomListBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            trafficPoliceGetRoomListBO.setSeq("abc");
            log.info("getroomlistUrl 请求的参数=" + getroomlistUrl);
            log.info("trafficPoliceGetRoomListBO 请求的参数=" + JSON.toJSONString(trafficPoliceGetRoomListBO));
            getroomlistResult = HttpUtil.postGeneralUrl(getroomlistUrl, "application/json", JSON.toJSONString(trafficPoliceGetRoomListBO), "UTF-8");
            log.info("getroomlistResult 返回结果=" + getroomlistResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("请求参数uid不传，获取会话roomid");
            recordhttp.setUrl(getroomlistUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceGetRoomListBO));
            recordhttp.setResponse(getroomlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getroomlistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getroomlistResult.contains("content"),true);
            Assert.assertEquals(getroomlistResult.contains("createdAt"),true);
            Assert.assertEquals(getroomlistResult.contains("imgList"),true);
            Assert.assertEquals(getroomlistResult.contains("isFinished"),true);
            Assert.assertEquals(getroomlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getroomlistResult.contains("replyList"),true);
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
