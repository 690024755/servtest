package com.galaxyeye.websocket.test.galaxyeye.Article.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.DTO
 * @Date Create on 15:47
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/4日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class MatchArticleDTO {

    /**
     * articles : [{"id":1268432765209022464,"strId":"1268432765209022464","title":"yyb628f74b-8603-4e27-8d2d-503c9f8767c2","quality":"优","srcSite":"飞华健康网","intro":"饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。","thumbnail":"http://47.96.10.106/img/article/200604/1268432761371234304.gif","editor":"小编1","refUrl":"https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg","mainType":"母婴","subType":"yy","lab":"03004435|03004434","assLabs":["03000648","03000937"],"assLabsName":["一胎","hellp综合征"],"assSubType":["02000755","02000762"],"infoStr":"","createdAt":"2020-06-04 14:41:52","updatedAt":"2020-06-04 08:41:52","enable":1,"comment":"备注1","bodyContent":"","mainTypeList":["母婴"],"subTypeList":["yy"],"labList":["03004434","03004435"],"info":null}]
     * msg : ok
     * remoteHome : http://47.96.10.106/
     * result : 1
     * seq : null
     * uid : 237671
     */

    private String msg;
    private String remoteHome;
    private Integer result;
    private Object seq;
    private Integer uid;
    private List<ArticlesBean> articles;

    @Data
    @ToString
    public static class ArticlesBean {
        /**
         * id : 1268432765209022464
         * strId : 1268432765209022464
         * title : yyb628f74b-8603-4e27-8d2d-503c9f8767c2
         * quality : 优
         * srcSite : 飞华健康网
         * intro : 饮食习惯不好会导致疾病的产生，食道癌是其中最为严重的一种，这篇文章写的就是食道癌的治疗方法。
         * thumbnail : http://47.96.10.106/img/article/200604/1268432761371234304.gif
         * editor : 小编1
         * refUrl : https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg
         * mainType : 母婴
         * subType : yy
         * lab : 03004435|03004434
         * assLabs : ["03000648","03000937"]
         * assLabsName : ["一胎","hellp综合征"]
         * assSubType : ["02000755","02000762"]
         * infoStr :
         * createdAt : 2020-06-04 14:41:52
         * updatedAt : 2020-06-04 08:41:52
         * enable : 1
         * comment : 备注1
         * bodyContent :
         * mainTypeList : ["母婴"]
         * subTypeList : ["yy"]
         * labList : ["03004434","03004435"]
         * info : null
         */

        private Long id;
        private String strId;
        private String title;
        private String quality;
        private String srcSite;
        private String intro;
        private String thumbnail;
        private String editor;
        private String refUrl;
        private String mainType;
        private String subType;
        private String lab;
        private String infoStr;
        private String createdAt;
        private String updatedAt;
        private Integer enable;
        private String comment;
        private String bodyContent;
        private Object info;
        private List<String> assLabs;
        private List<String> assLabsName;
        private List<String> assSubType;
        private List<String> mainTypeList;
        private List<String> subTypeList;
        private List<String> labList;

    }
}
