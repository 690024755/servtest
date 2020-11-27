package com.galaxyeye.websocket.test.galaxyeye.IcService.BO; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.IcService.BO
 * @Date Create on 16:59
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/8/21日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class IcServiceUserLoginBO implements Serializable {
    /**
     * userName : ZLJ_yangyi
     * userPwd : bLoKA9mbPXeWLEstDIe77D9ftVrR6+IDA4axLhfug1O1xXClha4DYpJW7mYd32mRlJPZddpaUnQLRh+2CAzsHfs0buLqK1skYYbiuqczjyYa50290UDmMuu0XFwl6jfH4oJKwBkIOC7DJtmxOcaA+mEf3GgO+TxgvCHiZuC4K/Y=
     * publicKey : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCvXLrcsaRxB4F++AVbXhAVaC6+39sLjA8or51g2GbMBSqpnsQf8l1BAtzLFvy4t5HEtId7StruZsfJmzHDiWP6U0SXFkFPNcsRl4KdEcUEbH+c9c0UyduyHcFYd9raGT3h6vN4iaU+Fmc7NhhCKALiDyEnzszwSr+f/nGwr20cQIDAQAB
     */
    private String userName;
    private String userPwd;
    private String publicKey;
}
