package com.galaxyeye.websocket.application.repository; /*
 * Description:com.galaxyeye.websocket.application.repository
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.ArticleCatAssLab;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppidRepository {
    Integer updateSetDeletedAtValueIsNull(@Param("record") Appid record,@Param("example") AppidExample example);

    Integer deleteByExample(AppidExample example);

    Integer insert(Appid record);

    Integer insertSelective(Appid record);

    List<Appid> selectByExample(AppidExample example);

    Integer updateByExampleSelective(@Param("record") Appid record, @Param("example") AppidExample example);

    Integer updateByExample(@Param("record") Appid record, @Param("example") AppidExample example);

}
