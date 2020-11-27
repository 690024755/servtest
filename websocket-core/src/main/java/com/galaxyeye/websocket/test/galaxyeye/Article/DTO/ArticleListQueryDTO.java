package com.galaxyeye.websocket.test.galaxyeye.Article.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 13:24
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ArticleListQueryDTO {

    /**
     * articles : []
     * count : 151
     * pageIndex : 5
     * perPageCount : 10
     * sessionId : xxxxxx
     * msg :
     * result : 1
     * seq : null
     */
    private int count;
    private int pageIndex;
    private int perPageCount;
    private String sessionId;
    private String msg;
    private int result;
    private Object seq;
    private List<Articles> articles;

    @Data
    @ToString
    public class Articles {
        private String thumbnail;
        private String srcSite;
        private String strId;
        private List<String> mainTypeList ;
        private String title;
        private String mainType;
        private String quality;
        private String createdAt;
        private String assLabs;
        private String intro;
        private String subType;
        private Long id;
        private List<String> labList ;
        private List<String> subTypeList ;
        private String info;
        private String updatedAt;
    }

}
