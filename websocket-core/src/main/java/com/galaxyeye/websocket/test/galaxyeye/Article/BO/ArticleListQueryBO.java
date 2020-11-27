package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 10:37
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class ArticleListQueryBO implements Serializable {

    private static final long serialVersionUID = -3816176004728559991L;
    /**获取文章列表
     * mainType : ["疾病","症状"]
     * subType : ["头痛","咳嗽","感冒"]
     * title : 这个专坑女人的美容项目，骗钱还致癌，别再上当
     * labs : ["美容"]
     * quality : 精
     * srcSite : 丁香医生
     * editor : 小编
     * comment : 备注
     * enable : true
     * assLabs : ["03000001","03000002"]
     * assSubType : ["02000001","02000002"]
     * pageIndex : 5
     * perPageCount : 10
     * uid : 100060
     * token : xxxxxx
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9
     * appid : 1.00001
     * seq : abc
     */
    //选填参数
    private String id;
    private String title;
    private String quality;
    private String srcSite;
    private String editor;
    private String comment;
    private Boolean enable;
    private Integer pageIndex;
    private Integer perPageCount;
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;
    private List<String> mainType;
    private String subType;
    private String labs;
    private List<String> assLabs;
    private List<String> assSubType;
}
