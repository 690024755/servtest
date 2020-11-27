package com.galaxyeye.websocket.test.galaxyeye.MpService.DTO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.DTO
 * @Date Create on 17:36
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/30日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
@Data
@ToString
public class GetQaExpertMessaGeListDTO implements Serializable {
    private static final long serialVersionUID = 8656461807965887974L;
    /**
     * isReplied : false
     * strId : 1199265542125391872
     * unreadCount : 0
     * type : 1
     * expertNo :
     * content : test1
     * uid : 511762
     * createdAt : 2019-11-26 17:56:01
     * nickname : 昵称
     * expertUid : 417941
     * id : 1199265542125391872
     * updatedAt : 2019-11-26 17:56:01
     * status : 0
     */
    private boolean isReplied;
    private String strId;
    private Integer unreadCount;
    private String type;
    private String expertNo;
    private String content;
    private Integer uid;
    private String createdAt;
    private String nickname;
    private Integer expertUid;
    private Long id;
    private String updatedAt;
    private Integer status;
}
