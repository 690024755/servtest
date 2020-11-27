package com.galaxyeye.websocket.test.galaxyeye.MpService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.BO
 * @Date Create on 11:19
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/4/19日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class InsertGeneralBatchConfigBO implements Serializable {
    private static final long serialVersionUID = -2343147902670249425L;
    /**
     * key : test1
     * data : 123456789
     * comment : 测试1
     * enable : 1
     * verify : 0
     * editor : vogel
     */
    private String key;
    private String data;
    private String comment;
    private Integer enable;
    private Integer verify;
    private String editor;

}
