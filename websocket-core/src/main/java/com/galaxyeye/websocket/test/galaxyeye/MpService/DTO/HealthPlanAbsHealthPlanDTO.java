package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 17:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
@Data
@ToString
public class HealthPlanAbsHealthPlanDTO implements Serializable {
    private static final long serialVersionUID = 1180206289318617038L;
    private String id;
    private String name;
    private String image;
}
