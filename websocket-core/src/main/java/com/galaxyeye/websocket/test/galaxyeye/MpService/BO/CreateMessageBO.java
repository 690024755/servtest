package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 11:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class CreateMessageBO implements Serializable {

    private static final long serialVersionUID = -2879305488056511032L;
    /**
     * uid : 100060
     * token : PlWoRt8I5vMeQ5lony2wORWwsFsYeEz8FxK0iiDvY9jW-hnBq_NI6VyxCt4FI5FxdC56gC9fTYs_bp3z4DL0K57pJk3VUTKH_hMKPrfOUG2YR-zHhiDCF3vKSy-NvCgz
     * appid : 1.00002
     * nickname : tester1
     * title : 测试测试
     * contact : 18888888888
     * content : testtesttestsetsessetstese
     * images : ["https://7niu-article.galaxyeye-tech.com/img/msgboard/191018/1185022477248499712.jpg","https://7niu-article.galaxyeye-tech.com/img/msgboard/191018/1185022241557975040.jpg"]
     * seq : 123
     */

    private String uid;
    private String token;
    private String appid;
    private String nickname;
    private String title;
    private String contact;
    private String content;
    private Integer seq;
    private String bmAppid;
    private String accessToken;
    //允许传的张数为9张
    private List<String> images;

}
