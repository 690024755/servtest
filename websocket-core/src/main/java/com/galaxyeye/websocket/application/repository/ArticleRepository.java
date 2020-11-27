package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:23
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */


import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleRepository {

    public List<Article> getByListArticleId(List list);

    public List<Article> getByAllArticle();

    public Article getByArticleId(Long articleId);

    Integer insert(Article record);

    Integer insertSelective(Article record);

    Integer deleteByExample(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    Integer updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

}
