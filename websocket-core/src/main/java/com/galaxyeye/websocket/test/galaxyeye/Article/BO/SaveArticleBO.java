package com.galaxyeye.websocket.test.galaxyeye.Article.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.VO
 * @Date Create on 10:46
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
public class SaveArticleBO implements Serializable {
    private static final long serialVersionUID = -6452448626345228337L;
    /**保存文章
     * id : 575243049722974208
     * title : xxx
     * intro : xxxx
     * mainType : 生活
     * subType : 女性
     * labs : lab1|lab2
     * assLabs : ["03000001","03000002"]
     * assSubType : ["02000001","02000002"]
     * quality : 精
     * refUrl : https://mp.weixin.qq.com/s/xeCerp30SP1uMVx7av9fGg
     * srcSite : 丁香医生
     * editor : 小编
     * info : xxxx
     * comment : 备注
     * enable : true
     * thumbnail : article/190507/575243049722974208/thumbnail_575942875448086528.jpg
     * body : 文章body体
     * uid : 100060
     * token : xxxxxx
     * bmAppid : 3.00007
     * accessToken : 72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9
     * appid : 1.00001
     * seq : abc
     */
    /**
     * 0表示新增文章
     * 填写文章id表示更新文章
     * 填写的文章id不存在则提示错误信息
     */
    private String id;
    private String title;
    private String intro;
    private String mainType;
    private String subType;
    private String labs;
    private String quality;
    private String refUrl;
    private String srcSite;
    private String editor;
    private String info;
    private String comment;
    private Boolean enable;
    private String thumbnail;
    private String body;
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;
    private List<String> assLabs;
    private List<String> assSubType;
}
