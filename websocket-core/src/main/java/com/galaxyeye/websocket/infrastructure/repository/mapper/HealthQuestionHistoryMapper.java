package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.HealthQuestionHistory;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthQuestionHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HealthQuestionHistoryMapper {
    long countByExample(HealthQuestionHistoryExample example);

    int deleteByExample(HealthQuestionHistoryExample example);

    int deleteAll();

    int insert(HealthQuestionHistory record);

    int insertSelective(HealthQuestionHistory record);

    List<HealthQuestionHistory> selectByExample(HealthQuestionHistoryExample example);

    int updateByExampleSelective(@Param("record") HealthQuestionHistory record, @Param("example") HealthQuestionHistoryExample example);

    int updateByExample(@Param("record") HealthQuestionHistory record, @Param("example") HealthQuestionHistoryExample example);
}