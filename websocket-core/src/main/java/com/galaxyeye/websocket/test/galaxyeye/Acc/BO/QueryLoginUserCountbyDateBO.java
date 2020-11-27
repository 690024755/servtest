package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 16:50
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/12日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class QueryLoginUserCountbyDateBO implements Serializable {

    private static final long serialVersionUID = -2168974344466795390L;
    /**
     * date : 2019-11-12
     * bmAppid : 4.00047
     * appid : 3.00009
     */

    private String date;
    private String bmAppid;
    private String appid;


}
