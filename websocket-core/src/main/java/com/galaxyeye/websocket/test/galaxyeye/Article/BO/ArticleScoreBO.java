package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 10:42
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class ArticleScoreBO implements Serializable {
    private static final long serialVersionUID = -5474260127650216020L;
    private String msg;
    private Integer result;
    private Long uid;
    private List<Double> scores ;
    private Integer nextPos;
    private List<Articles> articles ;
    private String remoteHome;

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
