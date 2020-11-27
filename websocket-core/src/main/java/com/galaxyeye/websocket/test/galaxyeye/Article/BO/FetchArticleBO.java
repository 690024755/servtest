package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 10:06
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/19日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 文章统计计算分数DTO
 */
@Data
@ToString
public class FetchArticleBO {
    private String uid;
    private String bmAppid;
    private String accessToken;
    private String token;
    private String appid;
    private Cond cond;
    private String cmd;

    /**
     * 匹配条件
     * tes小类与标签分数计算请求参数
     */
    @Data
    @JSONType(orders={"titles","mainType","subType","lab","begin","count","reSort"})
    @ToString
    public static class Cond {
        private List<String> titles ;
        private List<String> mainType ;
        private List<String> subType ;
        private List<String> lab ;
        private Integer begin;
        private Integer count;
        private Boolean reSort;
    }

}
