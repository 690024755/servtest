package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class SxcFixPriceRule implements Serializable {
	private static final long serialVersionUID = -494338232807500867L;
	private Integer id;

    private Integer type;

    private String rules;

    private String ruleTarget;

    private Integer state;

    private Integer operatorId;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer status;


}