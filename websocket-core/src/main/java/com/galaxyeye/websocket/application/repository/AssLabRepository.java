package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:23
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */


import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssLabExample;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AssLabRepository {

    public Integer insertOne(HashMap<String, Object> hs);

    public Integer insertBatch(List<AssLab> list);

    public Integer insertSelective(AssLab record);

    List<AssLab> selectByExample(AssLabExample example);

    /**
     * 获取主键id
     * @return
     */
    public Long getId();

    Integer deleteByExample(AssLabExample example);

    Integer updateByExampleSelective(@Param("record") AssLab record, @Param("example") AssLabExample example);

    Integer updateByExample(@Param("record") AssLab record, @Param("example") AssLabExample example);
}
