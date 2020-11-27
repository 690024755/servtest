package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:23
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/9/25日galaxyeye All Rights Reserved.
 */


import com.galaxyeye.websocket.infrastructure.repository.entity.AssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.MainType;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.MainTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MainTypeRepository {

    Integer insertSelective(MainType record);

    Long getId();

    List<MainType> selectAll();

    Integer deleteByExample(MainTypeExample example);


    List<MainType> selectByExample(MainTypeExample example);

    Integer updateByExampleSelective(@Param("record") MainType record, @Param("example") MainTypeExample example);

    Integer updateByExample(@Param("record") MainType record, @Param("example") MainTypeExample example);
}
