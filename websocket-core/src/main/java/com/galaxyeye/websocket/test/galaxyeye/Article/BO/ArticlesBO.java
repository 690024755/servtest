package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 11:42
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/10日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class ArticlesBO implements Serializable {
    private static final long serialVersionUID = 8902947281581556651L;
    private List<Articles> articles;
    private Long id;

    private String title;

    private String quality;

    private String srcSite;

    private String intro;

    private String thumbnail;

    private String mainType;

    private String subType;

    private String assLabs;

    private String info;

    private String createdAt;

    private String updatedAt;

    private List<String> mainTypeList ;

    private List<String> subTypeList ;

    private List<String> labList ;

    private String strId;

    private List<String> assLabList ;

    private String infoObj;

    @Data
    @ToString
    public class Articles {
        private String thumbnail;

        private String srcSite;

        private String strId;

        private List<String> mainTypeList;

        private List<String> assLabList;

        private String title;

        private String mainType;

        private String quality;

        private String createdAt;

        private String assLabs;

        private String intro;

        private String subType;

        private Long id;

        private List<String> labList;

        private List<String> subTypeList;

        private String info;

        private String updatedAt;
    }

}
