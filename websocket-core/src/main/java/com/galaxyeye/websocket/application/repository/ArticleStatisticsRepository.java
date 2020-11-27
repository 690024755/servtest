package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 15:31
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ArticleStatisticsExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SxcGoodsGroupRelationExample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface ArticleStatisticsRepository {

    public ArticleStatistics getByArticleId(Long articleId);

    public List<ArticleStatistics> getByListArticleId(List list);

    public int deleteAllArticleStatistics();

    public int deleteByArticleId(Long articleId);

    public List<ArticleStatistics> selectByExample(ArticleStatisticsExample example);

}
