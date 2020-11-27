package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestionUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrafficpoliceQuestionUserMapper {
    long countByExample(TrafficpoliceQuestionUserExample example);

    int deleteByExample(TrafficpoliceQuestionUserExample example);

    int deleteAll();

    int insert(TrafficpoliceQuestionUser record);

    int insertSelective(TrafficpoliceQuestionUser record);

    List<TrafficpoliceQuestionUser> selectByExample(TrafficpoliceQuestionUserExample example);

    int updateByExampleSelective(@Param("record") TrafficpoliceQuestionUser record, @Param("example") TrafficpoliceQuestionUserExample example);

    int updateByExample(@Param("record") TrafficpoliceQuestionUser record, @Param("example") TrafficpoliceQuestionUserExample example);
}