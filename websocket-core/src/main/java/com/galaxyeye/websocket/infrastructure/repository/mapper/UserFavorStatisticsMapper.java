package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.UserCollectStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserFavorStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.UserFavorStatisticsExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserFavorStatisticsMapper {
    int getByArticleId(Long articleId);

    List<UserFavorStatistics> getByUId(Long uid);

    UserFavorStatistics getByUIdAndArticleId(HashMap<String,Long> map);

    List<UserFavorStatistics> getByListUId(List list);

    int deleteAllUserFavorStatistics();

    int deleteByUId(Long uid);

    int deleteByArticleId(Long articleId);

    int deleteByUIdAndArticleId(HashMap<String,Long> map);

    long countByExample(UserFavorStatisticsExample example);

    int deleteByExample(UserFavorStatisticsExample example);

    int insert(UserFavorStatistics record);

    int insertSelective(UserFavorStatistics record);

    List<UserFavorStatistics> selectByExample(UserFavorStatisticsExample example);

    int updateByExampleSelective(@Param("record") UserFavorStatistics record, @Param("example") UserFavorStatisticsExample example);

    int updateByExample(@Param("record") UserFavorStatistics record, @Param("example") UserFavorStatisticsExample example);
}