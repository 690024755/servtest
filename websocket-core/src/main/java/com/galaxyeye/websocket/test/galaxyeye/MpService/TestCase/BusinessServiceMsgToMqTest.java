package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.TestCase
 * @Date Create on 14:09
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.Article.Service.ApplicationServiceManaged;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.MpService.BO.*;
import com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter.IQaExpertResource;
import com.galaxyeye.websocket.test.galaxyeye.MpService.VO.CreateQaExpertMessageVO;
import com.galaxyeye.websocket.tool.encr.Base64Util;
import com.galaxyeye.websocket.tool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class BusinessServiceMsgToMqTest extends BaseTest implements IQaExpertResource {

    @Autowired
    private ApplicationServiceManaged applicationServiceManaged;


    /**
     * 生产消息到kafka
     * @throws Exception
     */
    @Deprecated
    @Test
    public void MsgToMqTest() throws Exception {
        String msgtomqUrl = null;
        MsgToMqBO msgToMqBO = null;
        String msgtomqResult = "";
        try {
            msgtomqUrl = url + "/BusinessService/msgtomq";
            HashMap<String, String> hs = userLoginTest();
            String filePath = "F:/photo/3.jpg";
            String imgParam = "data:image/jpg;base64,".concat(Base64Util.imageChangeBase64(filePath));
            List<String> list = new ArrayList<>();
            list.add("test");
            hs.put("list", JSON.toJSONString(list));
            hs.put("test", str2);
            msgToMqBO = new MsgToMqBO();
            msgToMqBO.setAppid("1.00002");
            msgToMqBO.setBmAppid("1.00002");
            msgToMqBO.setMsg(JSON.toJSONString(hs));
            msgToMqBO.setUid(217903);
            msgToMqBO.setAccessToken("4e3f42a92d0c9c5e746f4c123b0cabb897390ba346603da99969e34e13ecc4f9");
            msgToMqBO.setTopic("mplog_env172.16.0.25_1");
            msgToMqBO.setSeq("abc");
            log.info("msgtomqUrl 请求的参数=" + msgtomqUrl);
            log.info("msgToMqBO 请求的参数=" + JSON.toJSONString(msgToMqBO));
            msgtomqResult = HttpUtil.postGeneralUrl(msgtomqUrl, "application/json", JSON.toJSONString(msgToMqBO), "UTF-8");
            log.info("msgtomqResult 返回结果=" + JSON.parseObject(msgtomqResult));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //cd /home/galaxyeye/app/kafka/kafka_2.12-2.3.1/bin
            //./kafka-console-consumer.sh --bootstrap-server 172.16.0.25:9092 --topic mplog_env172.16.0.25_1 --from-beginning
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("生产消息到kafka");
            recordhttp.setUrl(msgtomqUrl);
            recordhttp.setRequest(JSON.toJSONString(msgToMqBO));
            recordhttp.setResponse(msgtomqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(msgtomqResult.contains("\"result\":1"), true);
            Assert.assertEquals(msgtomqResult.contains("\"msg\":\"ok\""), true);
        }
    }

    /**
     * 消费kafaka上的消息
     *
     * @throws Exception
     */
    @Test
    public void consumerMsgFromMqTest() throws Exception {

        log.info("consumerMsgFromMq 请求的参数=" + JSON.toJSONString(""));
        log.info("consumerMsgFromMq 返回结果=" + JSON.parseObject(""));

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
