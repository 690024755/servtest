package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 15:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/21日galaxyeye All Rights Reserved.
 */
import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.ELoginTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.IcService.BO.IcServiceUserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.IcService.TestCase.IcServiceUserLoginTest;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.ChatLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.OnLineBO;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;


@Slf4j
@Component
public class WebSocketClientStaffTest extends BaseTest {
    @Autowired
    private WebSocketClient webSocketClientStaff;


    @Autowired
    private IcServiceUserLoginTest icServiceUserLoginTest;

    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(15));
    }

    @Override
    public void initData() {
        String wsUrlSy = url;
        webSocketClientStaff = new WebSocketClient(wsUrlSy);
        setStaffOnline();
    }


    private void setStaffOnline() {
        IcServiceUserLoginBO icServiceUserLoginBO=new IcServiceUserLoginBO();
        icServiceUserLoginBO.setUserName("ZLJ_yangyi");
        icServiceUserLoginBO.setUserPwd("123456");
        icServiceUserLoginBO.setPublicKey("");
        String icserviceUserLoginResult=icServiceUserLoginTest.icServiceUserLoginTestByGernal(icServiceUserLoginBO);
        String token= JsonPath.read(icserviceUserLoginResult,"$.data.token");
        ChatLoginBO chatLoginBO=new ChatLoginBO();
        chatLoginBO.setType("chatLogin");
        chatLoginBO.setAppId("1.00002");
        chatLoginBO.setToken(token);
        log.info("请求参数 chatLoginBO="+JSON.toJSONString(chatLoginBO));
        webSocketClientStaff.sendMessageSy(JSON.toJSONString(chatLoginBO));
        OnLineBO onLineBO=new OnLineBO();
        BeanUtils.copyProperties(chatLoginBO,onLineBO);
        onLineBO.setType("online");
        Boolean flag=false;
        while (!flag){
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    String onlineResult=webSocketClientStaff.sendMessageSy(JSON.toJSONString(onLineBO));
                    Integer staffStatus=JsonPath.read(onlineResult,"$.data.staffCurrentInfo.staffStatus");
                    Assert.assertTrue(staffStatus.equals(2));
                    log.info("客服在线 staffStatus="+staffStatus);
                    Thread.sleep(10000);
                }
            }).start();
        }
    }

    @Override
    public void destroyData() {

    }
}
