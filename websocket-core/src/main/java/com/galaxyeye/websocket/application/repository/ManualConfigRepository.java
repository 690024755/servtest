package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.ManualConfig;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.ManualConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManualConfigRepository {

    Integer insertBatch(List<ManualConfig> list);

    Integer insert(ManualConfig record);

    Integer insertSelective(ManualConfig record);

    Integer deleteByExample(ManualConfigExample example);

    List<ManualConfig> selectByExample(ManualConfigExample example);

    Integer updateByExampleSelective(@Param("record") ManualConfig record, @Param("example") ManualConfigExample example);


}
