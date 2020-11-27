package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 16:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/7日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GetAppListDTO implements Serializable {

    private static final long serialVersionUID = 6343395740752159512L;
    /**
     * config : [{"appid":"4.00090","env":"dev","updatedAt":"2020-05-07 15:14:27","version":"5.0.2"}]
     * msg : ok
     * result : 1
     * seq : null
     * total : 23
     */
    private String msg;
    private Integer result;
    private String seq;
    private Integer total;
    private List<ConfigBean> config;

    @Data
    @ToString
    public static class ConfigBean {
        /**
         * appid : 4.00090
         * env : dev
         * updatedAt : 2020-05-07 15:14:27
         * version : 5.0.2
         */
        private String appid;
        private String env;
        private String updatedAt;
        private String version;
    }
}
