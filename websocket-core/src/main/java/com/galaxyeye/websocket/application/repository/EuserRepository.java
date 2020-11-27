package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Euser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.EuserExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface EuserRepository {
    Long countByExample(EuserExample example);

    Integer deleteByExample(EuserExample example);

    Integer insert(Euser record);

    Integer insertSelective(Euser record);

    List<Euser> selectByExample(EuserExample example);

    Integer updateByExampleSelective(@Param("record") Euser record, @Param("example") EuserExample example);

    Integer updateByExample(@Param("record") Euser record, @Param("example") EuserExample example);

}
