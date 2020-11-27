package com.galaxyeye.websocket.tool.bean; /*
 * Description:com.galaxyeye.websocket.tool.bean
 * @Date Create on 9:45
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/15日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSONObject;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.ChatLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfConnBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.OnLineBO;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@Slf4j
public class BeanUtil {

    public Object object;

    public BeanMap getBeanMap() {
        return beanMap;
    }

    public BeanMap beanMap;

    public BeanGenerator beanGenerator;

    public Map<String, Class> allProperty;

    public static BeanUtil getBeanUtil(){
        return new BeanUtil();
    }

    public void setValue(String property, Object value){
        beanMap.put(property, value);
    }

    public void setValue(Object object, Map<String, Class> property){
        for(String propertyName : property.keySet()){
            if(allProperty.containsKey(propertyName)){
                Object propertyValue = getPropertyValueByName(object, propertyName);
                this.setValue(propertyName, propertyValue);
            }
        }
    }

    public void setValue(Map<String, Object> propertyValue){
        for(Map.Entry<String, Object> entry : propertyValue.entrySet()){
            this.setValue(entry.getKey(), entry.getValue());
        }
    }


    public Object getValue(String property){
        return beanMap.get(property);
    }



    public Object getObject(){
        return this.object;
    }

    public Map<String, Class> getAllProperty() {
        return allProperty;
    }

    public Object generateObject(Map propertyMap){
        if(null == beanGenerator){
            beanGenerator = new BeanGenerator();
        }

        Set keySet = propertyMap.keySet();
        for(Iterator i = keySet.iterator(); i.hasNext();){
            String key = (String) i.next();
            beanGenerator.addProperty(key, (Class)propertyMap.get(key));
        }
        return beanGenerator.create();
    }


    public void addProperty(Map propertyType, Map propertyValue){
        if(null == propertyType){
            throw new RuntimeException("动态添加属性失败！");
        }
        Object oldObject = object;
        object = generateObject(propertyType);
        beanMap = BeanMap.create(object);

        if(null != oldObject){
            setValue(oldObject, allProperty);
        }

        setValue(propertyValue);
        if(null == allProperty){
            allProperty = propertyType;
        }else{
            allProperty.putAll(propertyType);
        }
    }



    public Map<String, Class> getAllPropertyType(Object object) throws ClassNotFoundException{
        Map<String, Class> map = new HashMap<String, Class>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(int index = 0; index < fields.length; index ++){
            Field field = fields[index];
            String propertyName = field.getName();
            Class<?> propertyType = Class.forName(field.getGenericType().getTypeName());
            map.put(propertyName, propertyType);
        }
        return map;
    }


    public Map<String, Object> getAllPropertyValue(Object object) throws ClassNotFoundException{
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(int index = 0; index < fields.length; index ++){
            Field field = fields[index];
            String propertyName = field.getName();
            Object propertyValue = getPropertyValueByName(object, propertyName);
            map.put(propertyName, propertyValue);
        }
        return map;
    }

    public Object getPropertyValueByName(Object object, String propertyName){
        String methodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Object value = null;
        try {
            Method method = object.getClass().getMethod(methodName, new Class[]{});
            value = method.invoke(object, new Object[]{});
        } catch (Exception e) {
            log.error(String.format("从对象%s获取%s的=属性值失败", object, propertyName));
        }
        return value;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        OnLineBO onLineBO = new OnLineBO();
        onLineBO.setAppId("1.00002");
        onLineBO.setToken("token");
        onLineBO.setType("1");
        System.out.println("onLineBO1="+ JSONObject.toJSONString(onLineBO));


        BeanUtil beanUtil = new BeanUtil();
        Map<String, Class> allPropertyType = beanUtil.getAllPropertyType(onLineBO);
        Map<String, Object> allPropertyValue = beanUtil.getAllPropertyValue(onLineBO);
        allPropertyType.put("houseName", Class.forName("java.lang.String"));
        allPropertyValue.put("houseName", "my hotel");

        allPropertyType.put("appid1", Class.forName("java.util.HashMap"));
        HashMap<String,Object> appidTest=new HashMap<>();
        appidTest.put("test2","test2");
        allPropertyValue.put("appid1",appidTest);

        KfConnBO kfConnBO=new KfConnBO();
        kfConnBO.setUid(123456);
        allPropertyType.put("kfConnBO", Class.forName(kfConnBO.getClass().getName()));
        allPropertyValue.put("kfConnBO",kfConnBO);

        beanUtil.addProperty(allPropertyType, allPropertyValue);
        System.out.println("onLineBO2="+JSONObject.toJSONString(beanUtil.beanMap));

        Map<String, Class> newPropertyType = new HashMap<>();
        Map<String, Object> newPropertyValue = new HashMap<>();
        newPropertyType.put("houseId", Class.forName("java.lang.String"));
        newPropertyValue.put("houseId", "这是什么东西");

        newPropertyType.put("listedInfo", Class.forName("cn.szsc.com.module.company.entity.ListedInfo"));
        newPropertyValue.put("listedInfo", onLineBO);
        beanUtil.addProperty(newPropertyType, newPropertyValue);

        Object entity = beanUtil.getObject();
        System.out.println(JSONObject.toJSONString(entity));
    }
}
