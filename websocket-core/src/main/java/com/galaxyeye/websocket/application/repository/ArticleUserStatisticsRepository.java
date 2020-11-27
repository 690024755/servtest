package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:25
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleUserStatistics;

import java.util.List;

public interface ArticleUserStatisticsRepository {

    /**
     * 获取该篇文章下的所有用户记录
     * @param articleId
     * @return
     */
    public int getByArticleId(Long articleId);


    public List<ArticleUserStatistics> getByListUId(List list);


    /**
     * 获取该用户下所有文章
     * @param uid
     * @return
     */
    public List<ArticleUserStatistics> getByUId(Long uid);

    /**
     * 获取一条记录
     * @param uid
     * @return
     */
    public ArticleUserStatistics getByUIdAndArticleId(Long uid,Long articleId);

    public int deleteAllArticleUserStatistics();

    public int deleteByUId(Long uid);

    public int deleteByArticleId(Long articleId);

    public int deleteByUIdAndArticleId(Long uid,Long articleId);


}
