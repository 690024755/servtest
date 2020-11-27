package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.HealthQuestionHistory;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.HealthQuestionHistoryExample;

import java.util.List;

public interface HealthQuestionHistoryRepository {

    Integer deleteByExample(HealthQuestionHistoryExample example);

    Integer deleteAll();

    Integer insert(HealthQuestionHistory record);

    Integer insertSelective(HealthQuestionHistory record);

    List<HealthQuestionHistory> selectByExample(HealthQuestionHistoryExample example);

}
