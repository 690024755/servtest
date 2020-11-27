package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.SysAccFlow;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysAccFlowExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysAccFlowRepository {

    Long countByExample(SysAccFlowExample example);

    Integer deleteByExample(SysAccFlowExample example);

    Integer insert(SysAccFlow record);

    Integer insertSelective(SysAccFlow record);

    List<SysAccFlow> selectByExample(SysAccFlowExample example);

    Integer updateByExampleSelective(@Param("record") SysAccFlow record, @Param("example") SysAccFlowExample example);

    Integer updateByExample(@Param("record") SysAccFlow record, @Param("example") SysAccFlowExample example);

}
