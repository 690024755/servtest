package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TGuestUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TGuestUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TGuestUserRepository {

    Long countByExample(TGuestUserExample example);

    Integer deleteByExample(TGuestUserExample example);

    Integer deleteAll();

    Integer insert(TGuestUser record);

    Integer insertSelective(TGuestUser record);

    List<TGuestUser> selectByExample(TGuestUserExample example);

    Integer updateByExampleSelective(@Param("record") TGuestUser record, @Param("example") TGuestUserExample example);

    Integer updateByExample(@Param("record") TGuestUser record, @Param("example") TGuestUserExample example);
}
