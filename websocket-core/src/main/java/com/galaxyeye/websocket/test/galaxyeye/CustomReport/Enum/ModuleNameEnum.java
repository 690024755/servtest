package com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum;

/*
 * Description:com.galaxyeye.websocket.test
 * @Date Create on 14:10
 * @author <a href="mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020-04-28日galaxyeye All Rights Reserved.
 */

public enum ModuleNameEnum {


    Acc("Acc", 0),
    Article("Article", 1),
    AudioToWord("AudioToWord", 2),
    MPServerJava("MPServerJava", 3),
    MPService("MPService", 4),
    OperationManagement("OperationManagement", 5),
    Saas("Saas", 6),
    TesTesting("TesTesting", 7),
    WxPay("WxPay", 8),
    WxServiceHttp("WxServiceHttp", 9),
    YunGuanWeb("YunGuanWeb", 10),
    IcService("IcService", 11),
    WxServiceWebSocket("WxServiceWebSocket", 12),
    TesDa("TesDa", 13),
    JjServer("JjServer", 14);


    private String name;
    private Integer index;

    private ModuleNameEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(Integer index) {
        for (ModuleNameEnum moduleNameEnum : ModuleNameEnum.values()) {
            if (moduleNameEnum.index == index) {
                return moduleNameEnum.name;
            }
        }
        return null;
    }

    public static Integer num() {
        Integer i=0;
        for (ModuleNameEnum moduleNameEnum : ModuleNameEnum.values()) {
            i++;
        }
        return i;
    }

    public static Integer getIndex(String name) {
        for (ModuleNameEnum moduleNameEnum : ModuleNameEnum.values()) {
            if (moduleNameEnum.name.equals(name) ) {
                return moduleNameEnum.index;
            }
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
