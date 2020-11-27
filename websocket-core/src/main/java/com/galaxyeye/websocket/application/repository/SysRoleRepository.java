package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */
import com.galaxyeye.websocket.infrastructure.repository.entity.SysRole;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysRoleExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysRoleRepository {
    Long countByExample(SysRoleExample example);

    Integer deleteByExample(SysRoleExample example);

    Integer insert(SysRole record);

    Integer insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    Integer updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    Integer updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);
}
