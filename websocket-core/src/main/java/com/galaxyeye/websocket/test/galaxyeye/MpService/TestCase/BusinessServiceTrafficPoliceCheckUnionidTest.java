package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase
 * @Date Create on 15:35
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/24日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.CheckUnionidBO;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.TrafficPoliceCreateRoomBO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@Component
public class BusinessServiceTrafficPoliceCheckUnionidTest extends BaseTest {
    @Autowired
    private JedisTemplate jedisTemplate;

    private final  String uid="239191";
    private final  String  unionId="o8Xn91cwv1EU_yAh_2GK9xyLTxRs";


    @Test
    public void checkunionid() throws Exception {
        String checkunionidUrl =null;
        CheckUnionidBO checkUnionidBO =null;
        String checkunionidResult ="";
        try{
            deleteToken();
            checkunionidUrl = url+"/BusinessService/trafficpolice/checkunionid";
            checkUnionidBO = new CheckUnionidBO();
            checkUnionidBO.setUid(Integer.valueOf(uid));
            checkUnionidBO.setUnionId(unionId);
            checkUnionidBO.setBmAppid("1.00002");
            checkUnionidBO.setAppid("1.00002");
            checkUnionidBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb8cff7f85ea7ce62c90a267da8af4876a9");
            checkUnionidBO.setSeq("abc");
            log.info("checkunionidUrl 请求的参数=" + checkunionidUrl);
            log.info("checkUnionidBO 请求的参数=" + JSON.toJSONString(checkUnionidBO));
            checkunionidResult = HttpUtil.postGeneralUrl(checkunionidUrl, "application/json", JSON.toJSONString(checkUnionidBO), "UTF-8");
            log.info("checkunionidResult 返回结果=" + checkunionidResult);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            getToken();
            HttpLog recordhttp=new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("查询用户是否关注过温州交警公众号");
            recordhttp.setUrl(checkunionidUrl);
            recordhttp.setRequest(JSON.toJSONString(checkUnionidBO));
            recordhttp.setResponse(checkunionidResult);
            initLog(recordhttp,new Object(){});
            Assert.assertEquals(checkunionidResult.contains("\"result\":1"),true);
            Assert.assertEquals(checkunionidResult.contains("\"msg\":\"ok\""),true);
            //follow表示关注过
            Assert.assertEquals(checkunionidResult.contains("follow"),true);
        }
    }


    @Test
    public void getToken() throws Exception {
        Object obj=jedisTemplate.get("ms:wzjj:teltoken");
        log.info("getToken ms:wzjj:teltoken =" + JSON.toJSONString(obj));
    }

    @Test
    public void deleteToken() throws Exception {
        jedisTemplate.del("ms:wzjj:teltoken");
        log.info("deleteToken ms:wzjj:teltoken =" + jedisTemplate.get("ms:wzjj:teltoken"));
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
