package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleStatisticsMapper {

    public ArticleStatistics getByArticleId(Long articleId);

    public List<ArticleStatistics> getByListArticleId(List list);

    public int deleteAllArticleStatistics();

    public int deleteByArticleId(Long articleId);

    long countByExample(ArticleStatisticsExample example);

    int deleteByExample(ArticleStatisticsExample example);

    int insert(ArticleStatistics record);

    int insertSelective(ArticleStatistics record);

    List<ArticleStatistics> selectByExample(ArticleStatisticsExample example);

    int updateByExampleSelective(@Param("record") ArticleStatistics record, @Param("example") ArticleStatisticsExample example);

    int updateByExample(@Param("record") ArticleStatistics record, @Param("example") ArticleStatisticsExample example);
}