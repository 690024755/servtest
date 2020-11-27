package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 16:34
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/12日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class QueryTotalUserCountBO implements Serializable {

    private static final long serialVersionUID = 3622954024572243968L;
    /**
     * bmAppid : 4.00047
     * appid : 3.00009
     */

    private String bmAppid;
    private String appid;


}
