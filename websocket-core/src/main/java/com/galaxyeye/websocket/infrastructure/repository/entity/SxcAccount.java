package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString
@Data
public class SxcAccount implements Serializable {

	private static final long serialVersionUID = -498708153478192383L;

	private Integer id;

    private Integer userId;

    private Integer accountId;

    /**
     * 余额
     */
    private Long balance;

    private Date createTime;

    /**
     * 0:删除 1:可用
     */
    private Integer state;

    /**
     * 红包账户余额
     */
    private Long subsidyBalance;

    /**
     * 现金账户余额
     */
    private Long cashBalance;

    /**
     * 充值送本金余额
     */
    private Long baseCashBalance;


}