package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 11:22
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/3日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.infrastructure.repository.entity.HttpLog;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.WxServiceReqLoginBO;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import com.galaxyeye.websocket.tool.websocket.response.WebsocketResLoginPara;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class KickOffRespTest extends BaseTest {
    @Autowired
    private WebSocketClient webSocketClientCustom;

    private WebsocketResLoginPara websocketResLoginPara = null;

    /**
     * 同一个用户登录之后，踢掉之前的已登录的用户
     */
    @Test
    public void kickOffRespTestBy() {
        WxServiceReqLoginBO wxServiceReqLoginBO = null;
        String websocketLoginResult = null;
        try {
            String wsUrlSy = url;
            webSocketClientCustom = new WebSocketClient(wsUrlSy);
            wxServiceReqLoginBO = new WxServiceReqLoginBO();
            Map<String, String> statistics = new HashMap<>();
            statistics.put("channelNo", "小程序服务端渠道编号");
            statistics.put("维度一", "渠道编号");
            statistics.put("维度二", "28");
            wxServiceReqLoginBO.setBmAppid("1.00002");
            wxServiceReqLoginBO.setMethod("loginReq");
            wxServiceReqLoginBO.setPasswd("123456");
            wxServiceReqLoginBO.setUname("hxw002");
            wxServiceReqLoginBO.setStatistics(statistics);
            log.info("wxServiceReqLoginBO 请求参数" + JSON.toJSONString(wxServiceReqLoginBO));
            websocketLoginResult = webSocketClientCustom.sendMessageSy(JSON.toJSONString(wxServiceReqLoginBO));
            log.info("websocketLoginResult 返回消息" + websocketLoginResult);
            websocketResLoginPara = JSON.parseObject(websocketLoginResult, WebsocketResLoginPara.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpLog recordhttp = new HttpLog();
            recordhttp.setCreateTime(new Date());
            recordhttp.setCaseDescribe("同一个用户登录之后，踢掉之前的已登录的用户");
            recordhttp.setUrl(url);
            recordhttp.setRequest(JSON.toJSONString(websocketLoginResult));
            recordhttp.setResponse(websocketLoginResult);
            initLog(recordhttp, new Object() {
            });
            Assert.assertEquals(websocketLoginResult.contains("\"method\":\"kfConnResp\""), true);
            Assert.assertEquals(websocketLoginResult.contains("bmAppid"), true);
        }
    }



    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }


    @Override
    public void initData() {
    }

    @Override
    public void destroyData() {

    }
}
