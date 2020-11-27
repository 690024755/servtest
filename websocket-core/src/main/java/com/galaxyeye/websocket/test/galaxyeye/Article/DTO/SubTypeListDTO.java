package com.galaxyeye.websocket.test.galaxyeye.Article.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 9:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/10日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SubTypeListDTO {
    /**
     * count : 1
     * list : [{"ass_sub_type_code":"02000852","ass_sub_type_name":"yy","id":"852","main":"yy","main_type_code":"01000341"}]
     * msg : ok
     * page : 1
     * result : 1
     * seq : null
     */
    private Integer count;
    private String msg;
    private Integer page;
    private Integer result;
    private Object seq;
    private List<ListBean> list;
    @Data
    @ToString
    public static class ListBean {
        /**
         * ass_sub_type_code : 02000852
         * ass_sub_type_name : yy
         * id : 852
         * main : yy
         * main_type_code : 01000341
         */
        private String ass_sub_type_code;
        private String ass_sub_type_name;
        private String id;
        private String main;
        private String main_type_code;
    }
}
