package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 10:30
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/1/6日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UpdateUserBO implements Serializable {
    private static final long serialVersionUID = 8222358187800763183L;
    /**
     * euid : 1103551886121766912
     * euname : galaxyeye2
     * passwd : 123456
     * email : galaxyeye@galaxyeye-tech.com
     * mobile : 13912340003
     * contactNumber : 0571-25340125
     * enterpriseName : 北冥星眸科技有限公司
     * addr : 杭州市滨江区滨安路650 号，IX-WORK A 幢2302 室
     * acctype : admin
     * feuid : 1103551886121766913
     * appid : 1.00001
     */
    private Long euid;
    private String euname;
    private String passwd;
    private String email;
    private String mobile;
    private String contactNumber;
    private String enterpriseName;
    private String addr;
    private String acctype;
    private Long feuid;
    private String appid;
}
