package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 11:13
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/19日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class InsertGeneralBatchBO implements Serializable {
    private static final long serialVersionUID = 4648762989918793570L;
    /**
     * uid : 100005
     * token : rTQCgXHjSHI4XRYxjRxAbmTIr5nHepr7ZNWXWJyqpvRtJWnJJL1NCqLpZVhDsC-x0-faJURTKcIVCjLgU_sKqalhPAzwVWUWc8RfzaTR6RuM4V_oWoD20RrYll5rIty3
     * bmAppid : 9.00003
     * appid : 9.00003
     * confAppid : 8.00008
     * env : dev
     * version : 1.0.0
     * newApp : 1
     * config : [{"key":"test1","data":"123456789","comment":"测试1","enable":1,"verify":0,"editor":"vogel"},{"key":"test2","data":"12345678987654321","comment":"测试2","enable":0,"verify":1,"editor":"vogel2"}]
     */
    private String uid;
    private String token;
    private String accessToken;
    private String bmAppid;
    private String appid;
    private String confAppid;
    private String env;
    private String version;
    private Integer newApp;
    private List<InsertGeneralBatchConfigBO> config;

}
