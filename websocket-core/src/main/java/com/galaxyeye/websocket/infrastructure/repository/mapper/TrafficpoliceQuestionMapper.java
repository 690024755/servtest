package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TrafficpoliceQuestion;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TrafficpoliceQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrafficpoliceQuestionMapper {
    long countByExample(TrafficpoliceQuestionExample example);

    int deleteByExample(TrafficpoliceQuestionExample example);

    int insert(TrafficpoliceQuestion record);

    int insertSelective(TrafficpoliceQuestion record);


    List<TrafficpoliceQuestion> selectByExample(TrafficpoliceQuestionExample example);

    List<TrafficpoliceQuestion> selectAll();

    int updateByExampleSelective(@Param("record") TrafficpoliceQuestion record, @Param("example") TrafficpoliceQuestionExample example);


    int updateByExample(@Param("record") TrafficpoliceQuestion record, @Param("example") TrafficpoliceQuestionExample example);

    int updateByAllDeleteTimeIsNull();
}