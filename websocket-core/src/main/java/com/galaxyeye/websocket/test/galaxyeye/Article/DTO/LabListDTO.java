package com.galaxyeye.websocket.test.galaxyeye.Article.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 16:27
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/11日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import java.util.List;

@Data
public class LabListDTO {
    /**
     * count : 1061
     * list : [{"ass_lab_code":"03000973","ass_lab_name":"草木犀瘦腿丸","id":"973","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000972","ass_lab_name":"藤黄果燃脂片","id":"972","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000971","ass_lab_name":"口气清新喷雾","id":"971","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000970","ass_lab_name":"果蔬清洁剂","id":"970","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000969","ass_lab_name":"杀蟑胶饵","id":"969","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000968","ass_lab_name":"除甲醛喷雾","id":"968","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000967","ass_lab_name":"漱口水","id":"967","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000966","ass_lab_name":"褪黑素","id":"966","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000965","ass_lab_name":"维生素","id":"965","main":"健康商品大类","main_type_code":"01000003"},{"ass_lab_code":"03000964","ass_lab_name":"暖宫宝","id":"964","main":"健康商品大类","main_type_code":"01000003"}]
     * msg : ok
     * page : 10
     * result : 1
     * seq : null
     */
    private int count;
    private String msg;
    private int page;
    private int result;
    private Object seq;
    private List<LabDTO> list;
    @Data
    public static class LabDTO {
        /**
         * ass_lab_code : 03000973
         * ass_lab_name : 草木犀瘦腿丸
         * id : 973
         * main : 健康商品大类
         * main_type_code : 01000003
         */
        private String ass_lab_code;
        private String ass_lab_name;
        private String id;
        private String main;
        private String main_type_code;
    }
}
