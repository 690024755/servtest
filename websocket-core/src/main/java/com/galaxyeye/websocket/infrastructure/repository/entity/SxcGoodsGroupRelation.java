package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SxcGoodsGroupRelation implements Serializable {
	private static final long serialVersionUID = 2909375183585549414L;
	private Long id;

    private Integer groupId;

    private Integer groupType;

    private Integer targetId;

    private Integer state;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer gmtCreator;


}