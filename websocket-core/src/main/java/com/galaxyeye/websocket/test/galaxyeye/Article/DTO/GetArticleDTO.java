package com.galaxyeye.websocket.test.galaxyeye.Article.DTO;

import lombok.Data;

@Data
public class GetArticleDTO {
    private String id;
    private String title;
    private String uid;
    private String token;
    private String bmAppid;
    private String accessToken;
    private String appid;
    private String seq;
}
