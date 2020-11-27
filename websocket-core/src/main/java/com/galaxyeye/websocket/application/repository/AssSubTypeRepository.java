package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:23
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */


import com.galaxyeye.websocket.infrastructure.repository.entity.Article;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.AssSubType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AssSubTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AssSubTypeRepository {

    public Integer insertOne(HashMap<String, Object> hs);

    public Integer insertBatch(List<AssSubType> list);

    public Integer insertSelective(AssSubType record);

    List<AssSubType> selectByExample(AssSubTypeExample example);

    /**
     * 获取主键id
     * @return
     */
    public Long getId();

    Integer deleteByExample(AssSubTypeExample example);

    Integer updateByExampleSelective(@Param("record") AssSubType record, @Param("example") AssSubTypeExample example);

    Integer updateByExample(@Param("record") AssSubType record, @Param("example") AssSubTypeExample example);

}
