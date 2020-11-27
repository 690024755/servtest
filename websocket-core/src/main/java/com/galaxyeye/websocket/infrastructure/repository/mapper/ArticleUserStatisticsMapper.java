package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleUserStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleUserStatisticsExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ArticleUserStatisticsMapper {

    public int getByArticleId(Long articleId);

    List<ArticleUserStatistics> getByUId(Long uid);

    List<ArticleUserStatistics> getByListUId(List list);

    ArticleUserStatistics getByUIdAndArticleId(HashMap<String,Long> hs);

    public int deleteAllArticleUserStatistics();

    public int deleteByUId(Long uid);

    public int deleteByArticleId(Long articleId);

    public int deleteByUIdAndArticleId(HashMap<String,Long> map);

    long countByExample(ArticleUserStatisticsExample example);

    int deleteByExample(ArticleUserStatisticsExample example);

    int insert(ArticleUserStatistics record);

    int insertSelective(ArticleUserStatistics record);

    List<ArticleUserStatistics> selectByExample(ArticleUserStatisticsExample example);

    int updateByExampleSelective(@Param("record") ArticleUserStatistics record, @Param("example") ArticleUserStatisticsExample example);

    int updateByExample(@Param("record") ArticleUserStatistics record, @Param("example") ArticleUserStatisticsExample example);
}