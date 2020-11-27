package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 20:47
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class HealthPlanDetailParentDTO implements Serializable {
    private String appid;
    private String env;
    private int version;
    private String key;
    //TODO
    private String data;

    private String createdAt;
    private String updatedAt;
    private String comment;
    private Integer enable;
    private Integer verify;
    private String editor;

    private HealthPlanDetailSubDTO config;

}
