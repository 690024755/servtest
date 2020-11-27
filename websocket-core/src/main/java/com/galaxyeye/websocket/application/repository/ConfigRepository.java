package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.Config;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigRepository {

    Long countByExample(ConfigExample example);

    Integer deleteByExample(ConfigExample example);

    Integer insert(Config record);

    Integer insertSelective(Config record);

    List<Config> selectByExample(ConfigExample example);

    Integer updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    Integer updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

}
