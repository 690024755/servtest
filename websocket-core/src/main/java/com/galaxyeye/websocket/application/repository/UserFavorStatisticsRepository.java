package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:27
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.UserCollectStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserFavorStatistics;

import java.util.List;

public interface UserFavorStatisticsRepository {
    public int getByArticleId(Long articleId);

    public List<UserFavorStatistics> getByListUId(List list);

    public List<UserFavorStatistics> getByUId(Long uid);

    public UserFavorStatistics getByUIdAndArticleId(Long uid,Long articleId);

    public int deleteAllUserFavorStatistics();

    public int deleteByUId(Long uid);

    public int deleteByArticleId(Long articleId);

    public int deleteByUIdAndArticleId(Long uid,Long articleId);

}
