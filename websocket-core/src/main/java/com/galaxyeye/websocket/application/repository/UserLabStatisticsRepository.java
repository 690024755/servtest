package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:28
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.UserFavorStatistics;
import com.galaxyeye.websocket.infrastructure.repository.entity.UserLabStatistics;

import java.util.List;

public interface UserLabStatisticsRepository {

    public List<UserLabStatistics> getByUId(Long uid);

    public Integer getUserSubTypeScoreByUIdAndSubType(Long uid,List list);

    public Integer getTesSubTypeScoreByUIdAndSubType(Long uid,List list);

    public Integer getUserLabScoreByUIdAndLab(Long uid,List list);

    public Integer getTesLabScoreByUIdAndLab(Long uid,List list);

    public int deleteAllUserLabStatistics();

    public int deleteByUId(Long uid);

}
