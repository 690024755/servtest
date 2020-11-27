package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SxcFreightRuleMap implements Serializable {

	private static final long serialVersionUID = 2047413044094348954L;
	private Integer id;

    private Integer ruleId;

    private Integer linkId;

    private Integer status;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Integer type;

    private Integer cityCode;


}