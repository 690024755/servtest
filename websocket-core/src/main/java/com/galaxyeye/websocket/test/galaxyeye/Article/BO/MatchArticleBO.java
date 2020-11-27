package com.galaxyeye.websocket.test.galaxyeye.Article.BO;
import lombok.Data;
import lombok.ToString;
import java.util.List;

//{ "uid":"100060", "token":"xxxxxx","bmAppid": "3.00007", "accessToken":"72b25378ce643cf691fb69c03c2ffbce03b2fec1e4d728c189fff01e857a7cc9",
// "cond": { "mainType":["症状"], "subType":["头痛", "咳嗽", "感冒"], "andLab":["疾病预防"], "orLab":[], "maxCount":2, "minCount":1, "addmainType":["生活"], "addsubType":["中医", "厨房", "乏力"] },
// "appid":"3.00011", "seq":"abc" }

@Data
@ToString
public class MatchArticleBO {
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private Cond cond;
    private String appid;
    private String seq;

    /**
     * 匹配文章的条件
     */
    @ToString
    @Data
    public static class Cond {
        private List<String> mainType ;
        private List<String> subType ;
        private List<String> andLab ;
        private List<String> orLab ;
        private List<String> includeAndSubType ;
        private List<String> includeOrSubType ;
        private List<String> includeAndLab ;
        private List<String> includeOrLab ;
        private List<String> excludeAndSubType ;
        private List<String> excludeOrSubType ;
        private List<String> excludeAndLab ;
        private List<String> excludeOrLab ;
        private Integer maxCount;
        private Integer minCount;
        private List<String> addmainType ;
        private List<String> addsubType ;
        private List<String> titles ;
        private List<String> ids ;
        //测试文章统计分数，新增字段
        private List<String> lab ;
        private Integer begin;
        private Integer count;
        private Boolean reSort;
        //根据更新时间文章逆序排序,默认传true/false,传其他如test或者不传相当于false
        private Object sortByUpd;
    }

}
