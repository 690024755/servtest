package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.SysLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogRepository {
    Long countByExample(SysLogExample example);

    Integer deleteByExample(SysLogExample example);

    Integer insert(SysLog record);

    Integer insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);

    Integer updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    Integer updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

}
