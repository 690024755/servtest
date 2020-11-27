package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.HealthUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HealthUserRepository {

    Integer deleteByExample(HealthUserExample example);

    Integer deleteAll();

    Integer insert(HealthUser record);

    Integer insertSelective(HealthUser record);

    List<HealthUser> selectByExample(HealthUserExample example);

    Integer updateByExampleSelective(@Param("record") HealthUser record, @Param("example") HealthUserExample example);


}
