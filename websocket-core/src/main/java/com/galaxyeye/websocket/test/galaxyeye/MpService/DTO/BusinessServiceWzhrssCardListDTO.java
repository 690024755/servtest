package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 15:45
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
public class BusinessServiceWzhrssCardListDTO implements Serializable {
    private static final long serialVersionUID = -918727633931160267L;
    /**
     * list : [{"no":"32500099110004695300","insurance":"B75559280","type":"市民卡","state":"正常","balance":18482,"binding":"未绑定"},{"no":"32500099140000782589","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140001256469","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140001243685","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500040000488056109","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140001274022","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140000823762","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140000805050","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140000814452","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"},{"no":"32500099140000791467","insurance":"","type":"副卡","state":"正常","balance":0,"binding":"未绑定"}]
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
         * no : 32500099110004695300
         * insurance : B75559280
         * type : 市民卡
         * state : 正常
         * balance : 18482
         * binding : 未绑定
         */
        private String no;
        private String insurance;
        private String type;
        private String state;
        private int balance;
        private String binding;
    }
}
