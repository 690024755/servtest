package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestion;
import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrafficpoliceQuestionRepository {
    Long countByExample(TrafficpoliceQuestionExample example);

    Integer deleteByExample(TrafficpoliceQuestionExample example);

    Integer insert(TrafficpoliceQuestion record);

    Integer insertSelective(TrafficpoliceQuestion record);

    List<TrafficpoliceQuestion> selectAll();

    List<TrafficpoliceQuestion> selectByExample(TrafficpoliceQuestionExample example);

    Integer updateByExampleSelective(@Param("record") TrafficpoliceQuestion record, @Param("example") TrafficpoliceQuestionExample example);


    Integer updateByExample(@Param("record") TrafficpoliceQuestion record, @Param("example") TrafficpoliceQuestionExample example);

    Integer updateByAllDeleteTimeIsNull();

}
