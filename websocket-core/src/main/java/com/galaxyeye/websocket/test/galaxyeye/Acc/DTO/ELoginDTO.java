package com.galaxyeye.websocket.test.galaxyeye.Acc.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.DTO
 * @Date Create on 16:43
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/12/4日galaxyeye All Rights Reserved.
 */


import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class ELoginDTO {

    /**
     * msg : ok
     * euid : 1186531925544669184
     * erobots : []
     * euname : yangyi
     * mobile : 13093863511
     * acctype : e-admin
     * token : 7cSbIg5f92KDmOdFfDVgVElgxBfV11ogIfSshytxbF1sRBdUFMbCJTJbXoD_rSkQ132OnJdBovO68R95mku0UAdCL6JJL3MiV7Bvv9-Vt8Rjsce_rXsoA0_EMugbFX_BhwNlFnMBpofMZEgveonV5w==
     * result : 1
     * fromAppid : 3.00009
     * contactNumber : 0571-25340125
     * addr : 杭州市滨江区滨安路650 号，IX-WORK A 幢2302 室
     * enterpriseName : 北冥星眸科技有限公司
     * tokenExpire : 1576132134
     * email : yangyi@galaxyeye-tech.com
     * status : 0
     */

    private String msg;
    private long euid;
    private String euname;
    private String mobile;
    private String acctype;
    private String token;
    private int result;
    private String fromAppid;
    private String contactNumber;
    private String addr;
    private String enterpriseName;
    private int tokenExpire;
    private String email;
    private int status;
    private List<Object> erobots;
}
