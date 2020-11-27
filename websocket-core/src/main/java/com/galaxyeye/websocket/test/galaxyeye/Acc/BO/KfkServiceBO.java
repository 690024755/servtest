package com.galaxyeye.websocket.test.galaxyeye.Acc.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.Acc.BO
 * @Date Create on 16:29
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/7/22日galaxyeye All Rights Reserved.
 */
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class KfkServiceBO implements Serializable {

    private static final long serialVersionUID = 1561132455139767556L;

    /**msg的内容格式
     * {\"sessionCode\":\"11716f16-a54c-4961-b03e-212b16e96a71\",\"UID\":235225,\"appid\":\"1.00005\",\"loginType\":\"login\",\"heartbeatsTime\":1593672920,\"seq\":\"10cd85b4-8333-422c-9bsd1-45955zc\",\"partitionKey\":\"11716f16-a54c-4961-b03e-212b16e96a71\"}

     请求字段	类型	最大长度(字符)	是否必填	字段说明
     sessionCode	string		是	批次code，同一组登录登出相同，同时用于kafka推入同一个分区，尽量保证数据分区有序
     uid	Int64		是	用户id
     appid	string		是	调用方应用id
     loginType	String		是	loginType = login
     heartbeatsTime	int64		是	在线当前时间戳
     seq	string		是	kafka每条记录唯一标识,建议用uuid
     partitionKey	String		是	kafka分区key，用户把消息同一分区
     */
    private String msg;

    private String appid;
//    签名，签名方式sign=MD5(msg+appkey)
    private String sign;
    private String topic;
    //去重使用，建议使用uuid代替
    private String seq;



}
