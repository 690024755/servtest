package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.HealthUserRepository;
import com.galaxyeye.websocket.application.repository.TrafficpoliceRoomRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceRoom;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthUserExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceRoomExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.AnswerQuestionBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceReplyMessageBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class BusinessServiceTrafficPoliceReplyMessageTest extends BusinessServiceTrafficPoliceSendMessageTest {
    public static TrafficpoliceRoom trafficpoliceRoom;

    @Autowired
    private TrafficpoliceRoomRepository trafficpoliceRoomRepository;

    /**
     * 使用AccessToken与BmAppid回复消息,主要给温州交警提供接口调用服务
     * @throws Exception
     */
    @Test
    public void replymessageByAccessTokenAndBmAppid() {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            //交警体验版人工咨询测试
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setBmAppid("1.00002");
            trafficPoliceReplyMessageBO.setAccessToken(bmAppids.get("1.00002"));
            trafficPoliceReplyMessageBO.setAppid("1.00002");
            //该uid为专家uid,模拟一个专家的uid
            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
            trafficPoliceReplyMessageBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用AccessToken与BmAppid回复消息,主要给温州交警提供接口调用服务");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 使用token与uid回复消息,主要给温州交警提供接口调用服务
     * @throws Exception
     */
    @Test
    public void replymessageByTokenAndUid() {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        HashMap<String, String> hs=userLoginTest();
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceReplyMessageBO.setToken((hs.get("token")));
            trafficPoliceReplyMessageBO.setAppid("1.00002");
            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
            trafficPoliceReplyMessageBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());

            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用token与uid回复消息");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 必填参数roomId校验
     * @throws Exception
     */
    @Test
    public void replymessageByNotExistRoomId() {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            //交警体验版人工咨询测试
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setBmAppid("1.00002");
            trafficPoliceReplyMessageBO.setAppid("1.00002");
            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
//            trafficPoliceReplyMessageBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            trafficPoliceReplyMessageBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数roomId校验");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"roomId is zero\""),true);
            Assert.assertEquals(replymessageResult.contains("\"result\":101"),true);
        }
    }



    /**
     * roomId的值不存在，进行回复
     * @throws Exception
     */
    @Test
    public void replymessageByParameterRoomIdBalueIsOne() {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            //交警体验版人工咨询测试
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setBmAppid("1.00002");
            trafficPoliceReplyMessageBO.setAppid("1.00002");
            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
            trafficPoliceReplyMessageBO.setRoomId(1L);
            trafficPoliceReplyMessageBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("roomId的值不存在，进行回复");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"traffic_roomid_notexist\""),true);
            Assert.assertEquals(replymessageResult.contains("\"result\":651"),true);
        }
    }

    /**
     * 必填参数Uid校验
     * @throws Exception
     */
    @Test
    public void replymessageByNotExistParameterUid() {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            //交警体验版人工咨询测试
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setBmAppid("1.00002");
            trafficPoliceReplyMessageBO.setAppid("1.00002");
//            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
            trafficPoliceReplyMessageBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            trafficPoliceReplyMessageBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Uid校验");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(replymessageResult.contains("\"result\":1"),true);
        }
    }

    /**
     * 必填参数appid校验
     * @throws Exception
     */
    @Test
    public void replymessageByNotExistParameterAppid() {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            //交警体验版人工咨询测试
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setBmAppid("1.00002");
//            trafficPoliceReplyMessageBO.setAppid("1.00002");
            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
            trafficPoliceReplyMessageBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            trafficPoliceReplyMessageBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数Uid校验");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"access_deny\""),true);
            Assert.assertEquals(replymessageResult.contains("\"result\":106"),true);
        }
    }

    /**
     * 回复消息,主要给温州交警提供接口调用服务
     * @throws Exception
     */
    @Test
    public void replymessage() throws Exception {
        String replymessageUrl =null;
        TrafficPoliceReplyMessageBO trafficPoliceReplyMessageBO =null;
        String replymessageResult ="";
        try{
            replymessageUrl = url+"/BusinessService/trafficpolice/replymessage";
            //交警体验版人工咨询测试
//        String Url = "https://acc.galaxyeye-api.com/BusinessService/trafficpolice/replymessage";
            trafficPoliceReplyMessageBO = new TrafficPoliceReplyMessageBO();
            trafficPoliceReplyMessageBO.setBmAppid("1.00002");
            trafficPoliceReplyMessageBO.setAppid("1.00002");
            trafficPoliceReplyMessageBO.setUid(225883);
            String str2="我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";
            trafficPoliceReplyMessageBO.setContent(str2);
            trafficPoliceReplyMessageBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            trafficPoliceReplyMessageBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("replymessageUrl 请求的参数=" + replymessageUrl);
            log.info("trafficPoliceReplyMessageBO 请求的参数=" + JSON.toJSONString(trafficPoliceReplyMessageBO));
            replymessageResult = HttpUtil.postGeneralUrl(replymessageUrl, "application/json", JSON.toJSONString(trafficPoliceReplyMessageBO), "UTF-8");
            log.info("replymessageResult 返回结果=" + replymessageResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("咨询者发送消息");
            recordhttp.setUrl(replymessageUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceReplyMessageBO));
            recordhttp.setResponse(replymessageResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(replymessageResult.contains("\"result\":1"),true);
            Assert.assertEquals(replymessageResult.contains("\"msg\":\"ok\""),true);
        }
    }

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(4));
    }


    @Override
    public void initData() {
        super.initData();
        TrafficpoliceRoomExample example=new TrafficpoliceRoomExample();
        TrafficpoliceRoomExample.Criteria cr=example.createCriteria();
        cr.andIdEqualTo(RoomId);
        List<TrafficpoliceRoom> list= trafficpoliceRoomRepository.selectByExample(example);
        trafficpoliceRoom=list.get(0);
    }

    @Override
    public void destroyData() {

    }
}
