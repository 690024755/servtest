package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 15:16
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/25日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class BusinessServiceWzhrssUnstartedCardListDTO implements Serializable {
    private static final long serialVersionUID = 1586477922691910186L;
    /**
     * list : [{"no":"32500099140000782589","type":"副卡","state":"正常"},{"no":"32500099140001256469","type":"副卡","state":"正常"},{"no":"32500099140001243685","type":"副卡","state":"正常"},{"no":"32500099140001274022","type":"副卡","state":"正常"},{"no":"32500099140000823762","type":"副卡","state":"正常"},{"no":"32500099140000805050","type":"副卡","state":"正常"},{"no":"32500099140000814452","type":"副卡","state":"正常"},{"no":"32500099140000791467","type":"副卡","state":"正常"}]
     * msg : ok
     * result : 1
     * seq : null
     */
    private String msg;
    private int result;
    private Object seq;
    private List<ListBean> list;
    @Data
    @ToString
    public static class ListBean {
        /**
         * no : 32500099140000782589
         * type : 副卡
         * state : 正常
         */
        private String no;
        private String type;
        private String state;
    }
}
