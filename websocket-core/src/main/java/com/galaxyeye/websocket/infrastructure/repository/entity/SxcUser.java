package com.galaxyeye.websocket.infrastructure.repository.entity;/* * @Description:com.galaxyeye.websocket.infrastructure.repository.entity.SxcUser 实体类 * @Date Create on 16:27 * @author <a href="mailto:yangyi@galaxyeye-tech.com">yangyi</a> * @Version JDK 1.8 * @since version 1.0 Copyright 2019-02-14日SXC All Rights Reserved. */import lombok.Data;import lombok.ToString;import java.io.Serializable;import java.util.Date;@ToString@Datapublic class SxcUser implements Serializable {	private static final long serialVersionUID = -5753625158123545362L;	private Integer id;	/**	 * id	 */	private Integer userId;	/**	 * 用户名称	 */	private String userName;	/**	 * 手机	 */	private String mobilePhone;	/**	 * 密码	 */	private String password;	/**	 * 性别	 */	private Integer sex;	/**	 * 地址	 */	private String address;	/**	 * 类型：1:小B;2:内部人员;3:供应商;4:司机;	 */	private Integer userType;	/**	 * 0:删除 1:可用	 */	private Integer state;	/**	 * 地区码	 */	private String areaCode;	/**	 * 地推人员id	 */	private Integer pushId;	/**	 * sxc_buyer_pickhouse的id	 */	private Integer pickhoseId;	private Date createTime;	private Integer cityCode;	/**	 * 用户登录名，唯一	 */	private String loginName;	private Date updateTime;	/**	 * 身份证号码	 */	private String idCard;	/**	 * 图片路径－json格式	 */	private String images;	private Integer isValid;	/**	 * 特征字段－json格式(是否开通小菜宝账户)	 */	private String feature;	private String pickhoseIds;	/**	 * 调研用户id	 */	private Integer surveyUserId;	/**	 * 用户等级：A级：1，B级：2，C级：3，D级：4	 */	private Integer userLevel;	/**	 * 用户成熟度	 */	private Integer userMature;}