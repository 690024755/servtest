package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class MainType implements Serializable {

    private static final long serialVersionUID = 3394104175063836576L;

    private Long id;

    //小类所属的大类code
    private String mainTypeCode;

    private String mainTypeName;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    //大类维护字段
    //1表示该大类处于维护当中，0表示该大类不处于维护当中，属于正常使用的文章
    private Integer isMaintained;
}