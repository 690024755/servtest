package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleCatAssLabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleCatAssLabMapper {

    List<ArticleCatAssLab> getByArticleId(Long articleId);

    long countByExample(ArticleCatAssLabExample example);

    int deleteByExample(ArticleCatAssLabExample example);

    int insert(ArticleCatAssLab record);

    int insertSelective(ArticleCatAssLab record);

    List<ArticleCatAssLab> selectByExample(ArticleCatAssLabExample example);

    int updateByExampleSelective(@Param("record") ArticleCatAssLab record, @Param("example") ArticleCatAssLabExample example);

    int updateByExample(@Param("record") ArticleCatAssLab record, @Param("example") ArticleCatAssLabExample example);
}