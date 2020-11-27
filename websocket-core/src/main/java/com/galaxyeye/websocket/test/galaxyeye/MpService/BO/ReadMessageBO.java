package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Article.BO
 * @Date Create on 15:05
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/29日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class ReadMessageBO implements Serializable {

    private static final long serialVersionUID = -6046883752052825592L;

    /**
     * uid : 100060
     * token : PlWoRt8I5vMeQ5lony2wORWwsFsYeEz8FxK0iiDvY9jW-hnBq_NI6VyxCt4FI5FxdC56gC9fTYs_bp3z4DL0K57pJk3VUTKH_hMKPrfOUG2YR-zHhiDCF3vKSy-NvCgz
     * appid : 3.00014
     * id : 1185108641909313536
     * seq : abc
     */

    private String uid;
    private String token;
    private String appid;
    private String id;
    private String seq;
    private String bmAppid;
    private String accessToken;



}
