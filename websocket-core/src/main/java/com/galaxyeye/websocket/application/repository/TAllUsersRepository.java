package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.TAllUsers;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TAllUsersExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TAllUsersRepository {
    Long countByExample(TAllUsersExample example);

    Integer deleteByExample(TAllUsersExample example);

    Integer insert(TAllUsers record);

    Integer insertSelective(TAllUsers record);

    List<TAllUsers> selectByExample(TAllUsersExample example);

    Integer updateByExampleSelective(@Param("record") TAllUsers record, @Param("example") TAllUsersExample example);

    Integer updateByExample(@Param("record") TAllUsers record, @Param("example") TAllUsersExample example);

}
