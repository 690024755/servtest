package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 10:56
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/1日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.QueueInfoReqBO;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import com.galaxyeye.websocket.tool.websocket.response.WebsocketResLoginPara;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.List;
@Component
@Slf4j
public class QueueInfoReqTest extends BaseTest {
    @Autowired
    private WebSocketClient webSocketClientCustom;

    private WebsocketResLoginPara websocketResLoginPara = null;

    @Autowired
    private KfConnTest kfConnTest;

    @Autowired
    private JedisTemplate jedisTemplate;



    @Test
    public void queueInfoReqTestByGernal(QueueInfoReqBO queueInfoReqBO) {
        String queueInfoReqResult = null;
        try {
            log.info("queueInfoReqBO 请求参数=" + JSON.toJSONString(queueInfoReqBO));
            queueInfoReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(queueInfoReqBO));
            log.info("queueInfoReqResult 返回结果=" + queueInfoReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("客户进入排队通用接口");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(queueInfoReqBO));
            recordhttp.setResponse(queueInfoReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(queueInfoReqResult.contains("bmAppid"), true);
        }
    }


    @Test
    public void queueInfoReqTestByNotExistQueueUser() {
        QueueInfoReqBO queueInfoReqBO = null;
        String queueInfoReqResult = null;
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            queueInfoReqBO = new QueueInfoReqBO();
            queueInfoReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            queueInfoReqBO.setUid(websocketResLoginPara.getUid());
            queueInfoReqBO.setMethod("queueInfoReq");
            queueInfoReqBO.setWxAppid("1234567812345678");
            log.info("queueInfoReqBO 请求参数=" + JSON.toJSONString(queueInfoReqBO));
            queueInfoReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(queueInfoReqBO));
            log.info("queueInfoReqResult 返回结果=" + queueInfoReqResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("该客户接入之前未存在排队客户");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(queueInfoReqBO));
            recordhttp.setResponse(queueInfoReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(queueInfoReqResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("bmAppid"), true);
            Assert.assertEquals(queueInfoReqResult.contains("uid"), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"status\":\"queue\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("queueNum"), true);
        }
    }


    @Test
    public void queueInfoReqTestByExistQueueUser() {
        QueueInfoReqBO queueInfoReqBO = null;
        String queueInfoReqResult = null;
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            queueInfoReqBO = new QueueInfoReqBO();
            queueInfoReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
            queueInfoReqBO.setUid(websocketResLoginPara.getUid());
            queueInfoReqBO.setMethod("queueInfoReq");
            queueInfoReqBO.setWxAppid("1234567812345678");
            log.info("queueInfoReqBO 请求参数=" + JSON.toJSONString(queueInfoReqBO));
            queueInfoReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(queueInfoReqBO));
            log.info("queueInfoReqResult 返回结果=" + queueInfoReqResult);
            getQueueInfo();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("该客户接入之前已存在排队客户");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(queueInfoReqBO));
            recordhttp.setResponse(queueInfoReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(queueInfoReqResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("bmAppid"), true);
            Assert.assertEquals(queueInfoReqResult.contains("uid"), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"status\":\"queue\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("queueNum"), true);
            Integer queueNum=JsonPath.read(queueInfoReqResult,"$.queueNum");
            Assert.assertTrue(queueNum>0);
        }
    }


    /**
     * 必填参数uid校验
     */
    @Test
    public void queueInfoReqTestByNotExistParameterUid() {
        QueueInfoReqBO queueInfoReqBO = null;
        String queueInfoReqResult = null;
        String chatPackSeq=kfConnTest.KfConnTestByGernal();
        try {
            queueInfoReqBO = new QueueInfoReqBO();
            queueInfoReqBO.setBmAppid(websocketResLoginPara.getBmAppid());
//            queueInfoReqBO.setUid(websocketResLoginPara.getUid());
            queueInfoReqBO.setMethod("queueInfoReq");
            queueInfoReqBO.setWxAppid("1234567812345678");
            log.info("queueInfoReqBO 请求参数=" + JSON.toJSONString(queueInfoReqBO));
            queueInfoReqResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(queueInfoReqBO));
            log.info("queueInfoReqResult 返回结果=" + queueInfoReqResult);
            getQueueInfo();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("必填参数uid校验");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(queueInfoReqBO));
            recordhttp.setResponse(queueInfoReqResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(queueInfoReqResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("bmAppid"), true);
            Assert.assertEquals(queueInfoReqResult.contains("uid"), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"result\":1"), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"msg\":\"\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("\"status\":\"queue\""), true);
            Assert.assertEquals(queueInfoReqResult.contains("queueNum"), true);
            Integer queueNum=JsonPath.read(queueInfoReqResult,"$.queueNum");
            Assert.assertTrue(queueNum>0);
        }
    }


    @Test
    public void clearQueueInfo() {
        Long len=jedisTemplate.lGetListSize("QUEUE_MARK:1.00002");
        List<String> list=jedisTemplate.lGet("QUEUE_MARK:1.00002",0L,len);
        for (String str:list
        ) {
            jedisTemplate.lRemove("QUEUE_MARK:1.00002",len,str);
        }
    }


    @Test
    public void getQueueInfo() {
        Long len=jedisTemplate.lGetListSize("QUEUE_MARK:1.00002");
        List<String> list=jedisTemplate.lGet("QUEUE_MARK:1.00002",0L,len);
        log.info("当前排队人数=" + list.size()+",  排队信息:"+JSON.toJSONString(list));
    }



    @Test
    public void test1() {
        Integer uid=239557;
        for (int i = 0; i < 50; i++) {
            uid+=i;
            String customerServInfoKey=uid+":1.00002:customerServInfo";
            Object customerServInfo=jedisTemplate.get(customerServInfoKey);
            if(customerServInfo!=null){
                jedisTemplate.del(customerServInfoKey);
            }
            String chatOfflineKey=uid+":1.00002:customerServInfo";
            Object chatOfflineInfo=jedisTemplate.get(chatOfflineKey);
            if(chatOfflineInfo!=null){
                jedisTemplate.del(chatOfflineKey);
            }
        }
    }





    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }

    @Override
    public void initData() {
        kfConnTest.initData();
        webSocketClientCustom=kfConnTest.getWebSocketClientCustom();
        websocketResLoginPara=kfConnTest.getWebsocketResLoginPara();
    }

    @Override
    public void destroyData() {

    }
}
