package com.galaxyeye.websocket.test.galaxyeye.IcService.Inter; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter
 * @Date Create on 14:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.test.galaxyeye.IResource;

public interface IIcServiceResource<T> extends IResource {

      T getT();

      String getExpertAppid();

      String getExpertCid();

      String getExpertPwd();

      Long getExpertUid();

      String getExpertContent();

      String getExpertNickname();

      String getCommonCid();

      Long getCommonUid();

      String getCommonPwd();

      String getCommonNickname();

      String getCommonContent();


}
