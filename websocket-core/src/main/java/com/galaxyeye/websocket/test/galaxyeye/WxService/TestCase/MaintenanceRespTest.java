package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 16:46
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/14日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.MaintenanceRespBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.WxServiceReqLoginBO;
import com.galaxyeye.websocket.tool.bean.BeanUtil;
import com.galaxyeye.websocket.tool.websocket.client.WebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Deprecated
public class MaintenanceRespTest extends BaseTest {
    private WebSocketClient webSocketClientCustom = null;

    /**
     * 微信服务端维护通用接口
     */
    @Test
    public void maintenanceRespTestByNormal() throws ClassNotFoundException, InterruptedException {
        WebSocketClient webSocketClient = null;
        String wsUrlSy = url;
        webSocketClient = new WebSocketClient(wsUrlSy);
        WxServiceReqLoginBO wxServiceReqLoginBO = new WxServiceReqLoginBO();
        Map<String, String> statistics = new HashMap<>();
        statistics.put("channelNo", "小程序服务端渠道编号");
        statistics.put("维度一", "渠道编号");
        statistics.put("维度二", "28");
        wxServiceReqLoginBO.setBmAppid("1.00002");
        wxServiceReqLoginBO.setMethod("loginReq");
        wxServiceReqLoginBO.setPasswd("123456");
        wxServiceReqLoginBO.setUname("hxw002");
        wxServiceReqLoginBO.setStatistics(statistics);
        log.info("websocket登录请求参数" + JSON.toJSONString(wxServiceReqLoginBO));
        String websocketLoginObj = webSocketClient.sendMessageSy(JSON.toJSONString(wxServiceReqLoginBO));
        log.info("websocket登录返回消息" + websocketLoginObj);
        Boolean flag=false;
        while (!flag){
            Thread.sleep(60000);
        }
        log.info("maintenanceRespBO 请求参数=" + JSON.toJSONString(maintenanceRespBOBeanUtil.getBeanMap()));
        webSocketClientCustom.sendMessageAsy(JSON.toJSONString(maintenanceRespBOBeanUtil.getBeanMap()));
        webSocketClient.onClose();
    }


    private static final BeanUtil maintenanceRespBOBeanUtil = BeanUtil.getBeanUtil();

    private static final BeanUtil maintenanceConfsBOBeanUtil = BeanUtil.getBeanUtil();

    private static MaintenanceRespBO maintenanceRespBO = null;
    private static MaintenanceRespBO.MaintenanceConfsBO maintenanceConfsBO = null;


    private Map<String, Class> getAllPropertyTypeByMaintenanceRespBO(MaintenanceRespBO maintenanceRespTestBO) throws ClassNotFoundException {
        Map<String, Class> allPropertyType_maintenanceRespBO = maintenanceRespBOBeanUtil.getAllPropertyType(maintenanceRespTestBO);
        allPropertyType_maintenanceRespBO.put("maintenanceConfsBO", Class.forName(maintenanceConfsBOBeanUtil.getBeanMap().getClass().getName()));
        return allPropertyType_maintenanceRespBO;
    }


    private Map<String, Object> getAllPropertyValueByMaintenanceRespBO(MaintenanceRespBO maintenanceRespTestBO) throws ClassNotFoundException {
        Map<String, Object> allPropertyValue_maintenanceRespBO = maintenanceRespBOBeanUtil.getAllPropertyValue(maintenanceRespTestBO);
        allPropertyValue_maintenanceRespBO.put("maintenanceConfsBO", maintenanceConfsBOBeanUtil.getBeanMap());
        return allPropertyValue_maintenanceRespBO;
    }


    private void addPropertyByMaintenanceRespBO(Map<String, Class> allPropertyTypeByMaintenanceRespBO, Map<String, Object> allPropertyValueByMaintenanceRespBO) {
        maintenanceRespBOBeanUtil.addProperty(allPropertyTypeByMaintenanceRespBO, allPropertyValueByMaintenanceRespBO);
    }


    private Map<String, Class> getAllPropertyTypeByMaintenanceConfsBO(MaintenanceRespBO.MaintenanceConfsBO maintenanceConfsBO) throws ClassNotFoundException {
        Map<String, Class> allPropertyType_maintenanceConfsBO = maintenanceConfsBOBeanUtil.getAllPropertyType(maintenanceConfsBO);
        allPropertyType_maintenanceConfsBO.put("appid1", Class.forName("java.util.HashMap"));
        return allPropertyType_maintenanceConfsBO;
    }


    private Map<String, Object> getAllPropertyValueByMaintenanceConfsBO(MaintenanceRespBO.MaintenanceConfsBO maintenanceConfsBO) throws ClassNotFoundException {
        Map<String, Object> allPropertyValue_maintenanceConfsBO = maintenanceConfsBOBeanUtil.getAllPropertyValue(maintenanceConfsBO);
        HashMap<String, Object> appidTest = new HashMap<>();
        appidTest.put("test2", "test2");
        appidTest.put("productMaintenance", true);
        appidTest.put("TESMaintenance", true);
        appidTest.put("productMaintenanceVerbal", "产品维护");
        appidTest.put("TESMaintenancVerbal", "xxxxxxxxxxxx");
        HashMap<String, Object> moduleStatusMap = new HashMap<>();

        HashMap<String, Object> 模块编号1Map = new HashMap<>();
        模块编号1Map.put("maintenance",true);
        模块编号1Map.put("maintenanceVerbal","模块维护话术");
        HashMap<String, Object> 模块编号2Map = new HashMap<>();
        模块编号2Map.put("maintenance",true);
        模块编号2Map.put("maintenanceVerbal","模块维护话术");
        moduleStatusMap.put("模块编号1",模块编号1Map);
        moduleStatusMap.put("模块编号2",模块编号2Map);
        appidTest.put("moduleStatus",moduleStatusMap);
        allPropertyValue_maintenanceConfsBO.put("appid1", appidTest);
        return allPropertyValue_maintenanceConfsBO;
    }


    private void addPropertyByMaintenanceConfsBO(Map<String, Class> allPropertyTypeByMaintenanceConfsBO, Map<String, Object> allPropertyValueByMaintenanceConfsBO) {
        maintenanceConfsBOBeanUtil.addProperty(allPropertyTypeByMaintenanceConfsBO, allPropertyValueByMaintenanceConfsBO);
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }

    @Override
    public void initData() {
        maintenanceRespBO = new MaintenanceRespBO();
        maintenanceRespBO.setMethod("maintenanceResp");
        maintenanceRespBO.setMsg("ok");
        maintenanceRespBO.setResult(1);
        maintenanceConfsBO = new MaintenanceRespBO.MaintenanceConfsBO();
        try {
            addPropertyByMaintenanceConfsBO(getAllPropertyTypeByMaintenanceConfsBO(maintenanceConfsBO), getAllPropertyValueByMaintenanceConfsBO(maintenanceConfsBO));
            addPropertyByMaintenanceRespBO(getAllPropertyTypeByMaintenanceRespBO(maintenanceRespBO), getAllPropertyValueByMaintenanceRespBO(maintenanceRespBO));
        } catch (Exception e) {
            e.printStackTrace();
        }


        String wsUrlSy = url;
        webSocketClientCustom = new WebSocketClient(wsUrlSy);
    }

    @Override
    public void destroyData() {

    }
}
