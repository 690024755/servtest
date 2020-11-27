package com.galaxyeye.websocket.test.galaxyeye.Article.DTO;

import com.galaxyeye.websocket.test.galaxyeye.Article.Enum.OpEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 文章push、read、favor、unfavor、disfavor、undisfavor、share、collect、uncollect接口
 */
@Data
@ToString
public class ArticleStatisticsDTO {

    /**
     * push、read、favor、unfavor、disfavor、undisfavor、share、collect、uncollect
     */
    private String op;

    /**
     * 文章id
     */
    private String id;


    private String uid;

    private String token;

    private String bmAppid;

    private String accessToken;

    private String appid;

    private String seq;

}
