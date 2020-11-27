package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.UserLabStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.UserLabStatisticsExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserLabStatisticsMapper {
     List<UserLabStatistics> getByUId(Long uid);

     int getUserSubTypeScoreByUIdAndSubType(HashMap<String,Object> hs);

     int getTesSubTypeScoreByUIdAndSubType(HashMap<String,Object> hs);

     int getUserLabScoreByUIdAndLab(HashMap<String,Object> hs);

     int getTesLabScoreByUIdAndLab(HashMap<String,Object> hs);

     int deleteAllUserLabStatistics();

     int deleteByUId(Long uid);

    long countByExample(UserLabStatisticsExample example);

    int deleteByExample(UserLabStatisticsExample example);

    int insert(UserLabStatistics record);

    int insertSelective(UserLabStatistics record);

    List<UserLabStatistics> selectByExample(UserLabStatisticsExample example);

    int updateByExampleSelective(@Param("record") UserLabStatistics record, @Param("example") UserLabStatisticsExample example);

    int updateByExample(@Param("record") UserLabStatistics record, @Param("example") UserLabStatisticsExample example);
}