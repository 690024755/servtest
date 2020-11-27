package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleUserStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserCollectStatistics;

import java.util.List;

public interface UserCollectStatisticsRepository {

    public Integer getByArticleId(Long articleId);

    public List<UserCollectStatistics> getByListUId(List list);

    public List<UserCollectStatistics> getByUId(Long uid);

    public Integer getByUIdAndArticleId(Long uid,Long articleId);


    public int deleteAllUserCollectStatistics();

    public int deleteByUId(Long uid);

    public int deleteByArticleId(Long articleId);

    public int deleteByUIdAndArticleId(Long uid,Long articleId);
}
