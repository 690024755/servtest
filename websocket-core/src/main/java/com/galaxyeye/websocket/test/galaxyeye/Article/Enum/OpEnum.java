package com.galaxyeye.websocket.test.galaxyeye.Article.Enum;

import lombok.Data;
import lombok.ToString;



public enum OpEnum {
    PUSH("push", 1),//文章推送
    READ("read", 2),//文章阅读
    FAVOR("favor", 3),//文章喜欢，FAVOR与UNFAVOR一对
    UNFAVOR("unfavor", 4),//文章取消喜欢，用户取消对文章的喜欢
    DISFAVOR("disfavor", 5),//文章不喜欢，DISFAVOR与UNDISFAVOR一对
    UNDISFAVOR("undisfavor", 6),//取消不喜欢
    SHARE("share", 7),//文章分享
    COLLECT("collect", 8),//文章收藏
    UNCOLLECT("uncollect", 9);//取消文章收藏

    private String opName;
    private int index;

    private OpEnum(String opName, int index) {
                 this.opName = opName;
                 this.index = index;
             }

    public static String getName(int index) {
                 for (OpEnum opEnum : OpEnum.values()) {
                         if (opEnum.getIndex() == index) {
                                 return opEnum.opName;
                             }
                     }
                 return null;
             }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }




}
