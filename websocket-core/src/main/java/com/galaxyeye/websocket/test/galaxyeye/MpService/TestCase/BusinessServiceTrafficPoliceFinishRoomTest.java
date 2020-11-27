package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 11:44
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/27日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.application.repository.TrafficpoliceRoomRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceRoom;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceRoomExample;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceFinishRoomBO;
import com.galaxyeye.websocket.tool.date.DateTool;
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
public class BusinessServiceTrafficPoliceFinishRoomTest extends BusinessServiceTrafficPoliceGetListTest {

    @Autowired
    private TrafficpoliceRoomRepository trafficpoliceRoomRepository;


    /**
        使用BmAppid与AccessToken结束会话
     */
    @Test
    public void finishroomByBmAppidAndAccessToken() {
        String finishroomUrl =null;
        TrafficPoliceFinishRoomBO trafficPoliceFinishRoomBO =null;
        String finishroomResult ="";
        try{
            finishroomUrl = url+"/BusinessService/trafficpolice/finishroom";
            trafficPoliceFinishRoomBO = new TrafficPoliceFinishRoomBO();
            trafficPoliceFinishRoomBO.setBmAppid("1.00002");
            trafficPoliceFinishRoomBO.setAccessToken(bmAppids.get("1.00002"));
            trafficPoliceFinishRoomBO.setAppid("1.00002");
            //trafficpolice_room.remote_room_id
            trafficPoliceFinishRoomBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            log.info("finishroomUrl 请求的参数=" + finishroomUrl);
            log.info("trafficPoliceFinishRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceFinishRoomBO));
            action(0);
            finishroomResult = HttpUtil.postGeneralUrl(finishroomUrl, "application/json", JSON.toJSONString(trafficPoliceFinishRoomBO), "UTF-8");
            log.info("finishroomResult 返回结果=" + finishroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用BmAppid与AccessToken结束会话");
            recordhttp.setUrl(finishroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceFinishRoomBO));
            recordhttp.setResponse(finishroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(finishroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(finishroomResult.contains("\"msg\":\"ok\""),true);
        }
    }

    /**
     使用Uid与Token结束会话
     */
    @Test
    public void finishroomByUidAndToken() {
        String finishroomUrl =null;
        TrafficPoliceFinishRoomBO trafficPoliceFinishRoomBO =null;
        String finishroomResult ="";
        HashMap<String, String> hs= userLoginTest();
        try{
            finishroomUrl = url+"/BusinessService/trafficpolice/finishroom";
            trafficPoliceFinishRoomBO = new TrafficPoliceFinishRoomBO();
            trafficPoliceFinishRoomBO.setUid(hs.get("uid"));
            trafficPoliceFinishRoomBO.setToken(hs.get("token"));
            trafficPoliceFinishRoomBO.setAppid("1.00002");
            //trafficpolice_room.remote_room_id
            trafficPoliceFinishRoomBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            action(0);
            log.info("finishroomUrl 请求的参数=" + finishroomUrl);
            log.info("trafficPoliceFinishRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceFinishRoomBO));
            finishroomResult = HttpUtil.postGeneralUrl(finishroomUrl, "application/json", JSON.toJSONString(trafficPoliceFinishRoomBO), "UTF-8");
            log.info("finishroomResult 返回结果=" + finishroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("使用Uid与Token结束会话");
            recordhttp.setUrl(finishroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceFinishRoomBO));
            recordhttp.setResponse(finishroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(finishroomResult.contains("\"result\":1"),true);
            Assert.assertEquals(finishroomResult.contains("\"msg\":\"ok\""),true);
        }
    }


    /**
     * 已经结束的会话重复结束会话，表trafficpolice_room.remote_room_id=请求参数RoomId的值，查看trafficpolice_room.is_finished=1表示该会话已经结束
     * @throws Exception
     */
    @Test
    public void finishroomByRepeatFinishRoom() {
        String finishroomUrl =null;
        TrafficPoliceFinishRoomBO trafficPoliceFinishRoomBO =null;
        String finishroomResult ="";
        try{
            finishroomUrl = url+"/BusinessService/trafficpolice/finishroom";
            trafficPoliceFinishRoomBO = new TrafficPoliceFinishRoomBO();
            trafficPoliceFinishRoomBO.setBmAppid("1.00002");
            trafficPoliceFinishRoomBO.setAccessToken(bmAppids.get("1.00002"));
            trafficPoliceFinishRoomBO.setAppid("1.00002");
            //trafficpolice_room.remote_room_id
            trafficPoliceFinishRoomBO.setRoomId(trafficpoliceRoom.getRemoteRoomId());
            action(1);
            log.info("finishroomUrl 请求的参数=" + finishroomUrl);
            log.info("trafficPoliceFinishRoomBO 请求的参数=" + JSON.toJSONString(trafficPoliceFinishRoomBO));
            finishroomResult = HttpUtil.postGeneralUrl(finishroomUrl, "application/json", JSON.toJSONString(trafficPoliceFinishRoomBO), "UTF-8");
            log.info("finishroomResult 返回结果=" + finishroomResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("已经结束的会话重复结束会话");
            recordhttp.setUrl(finishroomUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceFinishRoomBO));
            recordhttp.setResponse(finishroomResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(finishroomResult.contains("traffic_dialog_finished"),true);
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
        TrafficpoliceRoomExample example=new TrafficpoliceRoomExample();
        example.createCriteria().andIdEqualTo(trafficpoliceRoom.getId());
        trafficpoliceRoomRepository.deleteByExample(example);
    }


    //是否结束会话，IsFinished=1结束会话，IsFinished=0未结束会话
    private void action(Integer IsFinished){
        TrafficpoliceRoom record=new TrafficpoliceRoom();
        record.setIsFinished(IsFinished);
        TrafficpoliceRoomExample example=new TrafficpoliceRoomExample();
        TrafficpoliceRoomExample.Criteria cr=example.createCriteria();
        cr.andIdEqualTo(trafficpoliceRoom.getId());
        trafficpoliceRoomRepository.updateByExampleSelective(record,example);
    }


}
