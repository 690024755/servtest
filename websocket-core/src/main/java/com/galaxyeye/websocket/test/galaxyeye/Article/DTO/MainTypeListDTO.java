package com.galaxyeye.websocket.test.galaxyeye.Article.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 11:47
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/9日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class MainTypeListDTO implements Serializable {
    private static final long serialVersionUID = 1371782221047235052L;
    /**
     * count : 1
     * list : [{"id":"183","is_maintained":"1","main_type_code":"01000183","main_type_name":"yy"}]
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
    private List<MainTypeDTO> list;
    /**
     * id : 183
     * is_maintained : 1
     * main_type_code : 01000183
     * main_type_name : yy
     */
    @Data
    @ToString
    public static class MainTypeDTO implements Serializable {
        private static final long serialVersionUID = 7044128864919471655L;
        private String id;
        private String is_maintained;
        private String main_type_code;
        private String main_type_name;
    }
}
