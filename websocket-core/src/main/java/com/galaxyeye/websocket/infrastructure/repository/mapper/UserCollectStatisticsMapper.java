package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleUserStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserCollectStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.UserCollectStatisticsExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserCollectStatisticsMapper {
    int getByArticleId(Long articleId);

     List<UserCollectStatistics> getByUId(Long uid);

    int getByUIdAndArticleId(HashMap<String,Long> map);

    List<UserCollectStatistics> getByListUId(List list);

     int deleteAllUserCollectStatistics();

     int deleteByUId(Long uid);

     int deleteByArticleId(Long articleId);

     int deleteByUIdAndArticleId(HashMap<String,Long> map);

    long countByExample(UserCollectStatisticsExample example);

    int deleteByExample(UserCollectStatisticsExample example);

    int insert(UserCollectStatistics record);

    int insertSelective(UserCollectStatistics record);

    List<UserCollectStatistics> selectByExample(UserCollectStatisticsExample example);

    int updateByExampleSelective(@Param("record") UserCollectStatistics record, @Param("example") UserCollectStatisticsExample example);

    int updateByExample(@Param("record") UserCollectStatistics record, @Param("example") UserCollectStatisticsExample example);
}