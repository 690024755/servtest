package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrafficpoliceQuestionUserRepository {

    Long countByExample(TrafficpoliceQuestionUserExample example);

    Integer deleteByExample(TrafficpoliceQuestionUserExample example);

    Integer deleteAll();

    Integer insert(TrafficpoliceQuestionUser record);

    Integer insertSelective(TrafficpoliceQuestionUser record);

    List<TrafficpoliceQuestionUser> selectByExample(TrafficpoliceQuestionUserExample example);

    Integer updateByExampleSelective(@Param("record") TrafficpoliceQuestionUser record, @Param("example") TrafficpoliceQuestionUserExample example);

    Integer updateByExample(@Param("record") TrafficpoliceQuestionUser record, @Param("example") TrafficpoliceQuestionUserExample example);

}
