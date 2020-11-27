package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbProAccountRepository {

    Long countByExample(TbProAccountExample example);

    Integer deleteByExample(TbProAccountExample example);

    Integer insert(TbProAccount record);

    Integer insertSelective(TbProAccount record);

    List<TbProAccount> selectByExample(TbProAccountExample example);

    Integer updateByExampleSelective(@Param("record") TbProAccount record, @Param("example") TbProAccountExample example);

    Integer updateByExample(@Param("record") TbProAccount record, @Param("example") TbProAccountExample example);


}
