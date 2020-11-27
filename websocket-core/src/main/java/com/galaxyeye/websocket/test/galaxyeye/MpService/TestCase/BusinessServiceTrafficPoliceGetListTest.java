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
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceGetListBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceReadRoomBO;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


@Slf4j
@Component
public class BusinessServiceTrafficPoliceGetListTest extends BusinessServiceTrafficPoliceReadRoomTest {


    /**
     * 咨询者获取已回复与待回复列表
     * @throws Exception
     */
    @Test
    public void getlist() throws Exception {
        String getlistUrl =null;
        TrafficPoliceGetListBO trafficPoliceGetListBO =null;
        String getlistResult ="";
        try{
            getlistUrl = url+"/BusinessService/trafficpolice/getlist";
            trafficPoliceGetListBO = new TrafficPoliceGetListBO();
            trafficPoliceGetListBO.setType(4);
            trafficPoliceGetListBO.setUid(Integer.valueOf(hs.get("uid")));
            trafficPoliceGetListBO.setBmAppid("1.00002");
            trafficPoliceGetListBO.setAppid("1.00002");
            trafficPoliceGetListBO.setAccessToken(bmAppids.get("1.00002"));
            log.info("getlistUrl 请求的参数=" + getlistUrl);
            log.info("trafficPoliceGetListMessage 请求的参数=" + JSON.toJSONString(trafficPoliceGetListBO));
            getlistResult = HttpUtil.postGeneralUrl(getlistUrl, "application/json", JSON.toJSONString(trafficPoliceGetListBO), "UTF-8");
            log.info("getlistResult 返回结果=" + getlistResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("获取已回复与待回复列表");
            recordhttp.setUrl(getlistUrl);
            recordhttp.setRequest(JSON.toJSONString(trafficPoliceGetListBO));
            recordhttp.setResponse(getlistResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(getlistResult.contains("\"result\":1"),true);
            Assert.assertEquals(getlistResult.contains("\"msg\":\"ok\""),true);
            Assert.assertEquals(getlistResult.contains("list"),true);
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
