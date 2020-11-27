package com.galaxyeye.websocket.service.params; /*
 * Description:com.galaxyeye.websocket.service.params
 * @Date Create on 9:37
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/26日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TotalScoreDTO {

    private UserLabStatistics userLabStatistics;
    private UserCollectStatistics userCollectStatistics;
    private ArticleUserStatistics articleUserStatistics;
    private UserFavorStatistics userFavorStatistics;
    private Article article;

}
