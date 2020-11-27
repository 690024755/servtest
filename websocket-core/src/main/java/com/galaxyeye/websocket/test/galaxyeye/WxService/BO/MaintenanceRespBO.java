package com.galaxyeye.websocket.test.galaxyeye.WxService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.BO
 * @Date Create on 17:02
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/14日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.tool.bean.BeanUtil;
import lombok.Data;
import lombok.ToString;
import net.sf.cglib.beans.BeanMap;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class MaintenanceRespBO implements Serializable {
    /**
     * method : maintenanceResp
     * maintenanceConfs : {}
     * result : 1
     * msg :
     */
    private String method;
    private MaintenanceConfsBO maintenanceConfs;
    private Integer result;
    private String msg;

    @Data
    @ToString
    public static class MaintenanceConfsBO {
//        动态增加类型字段，类型是HashMap<String,Object>，属性字段名称不固定

//    private HashMap<String,Object> appid;
    }


    public static void main(String[] args) throws ClassNotFoundException {

        BeanUtil beanUtil_maintenanceRespTestBO = new BeanUtil();
        MaintenanceRespBO maintenanceRespTestBO=new MaintenanceRespBO();
        maintenanceRespTestBO.setMethod("maintenanceResp");
        maintenanceRespTestBO.setMsg("ok");
        maintenanceRespTestBO.setResult(1);
        Map<String, Class> allPropertyType_maintenanceRespTestBO = beanUtil_maintenanceRespTestBO.getAllPropertyType(maintenanceRespTestBO);
        Map<String, Object> allPropertyValue_maintenanceRespTestBO = beanUtil_maintenanceRespTestBO.getAllPropertyValue(maintenanceRespTestBO);
        MaintenanceRespBO.MaintenanceConfsBO maintenanceConfsBO=new MaintenanceRespBO.MaintenanceConfsBO();

        BeanUtil beanUtil_maintenanceConfsBO = new BeanUtil();
        Map<String, Class> allPropertyType_maintenanceConfsBO = beanUtil_maintenanceConfsBO.getAllPropertyType(maintenanceConfsBO);
        Map<String, Object> allPropertyValue_maintenanceConfsBO = beanUtil_maintenanceConfsBO.getAllPropertyValue(maintenanceConfsBO);
        allPropertyType_maintenanceConfsBO.put("appid1", Class.forName("java.util.HashMap"));
        HashMap<String,Object> appidTest=new HashMap<>();
        appidTest.put("test2","test2");
        allPropertyValue_maintenanceConfsBO.put("appid1",appidTest);
        beanUtil_maintenanceConfsBO.addProperty(allPropertyType_maintenanceConfsBO, allPropertyValue_maintenanceConfsBO);
        System.out.println("beanUtil_maintenanceConfsBO="+ JSONObject.toJSONString(beanUtil_maintenanceConfsBO.getBeanMap()));
        BeanUtils.copyProperties(beanUtil_maintenanceConfsBO.getBeanMap(),maintenanceConfsBO);


        allPropertyType_maintenanceRespTestBO.put("maintenanceConfsBO", Class.forName(beanUtil_maintenanceConfsBO.getBeanMap().getClass().getName()));
        allPropertyValue_maintenanceRespTestBO.put("maintenanceConfsBO",beanUtil_maintenanceConfsBO.getBeanMap());

        beanUtil_maintenanceRespTestBO.addProperty(allPropertyType_maintenanceRespTestBO, allPropertyValue_maintenanceRespTestBO);
        System.out.println("beanUtil_maintenanceRespTestBO="+ JSONObject.toJSONString(beanUtil_maintenanceRespTestBO.getBeanMap()));
    }

}
