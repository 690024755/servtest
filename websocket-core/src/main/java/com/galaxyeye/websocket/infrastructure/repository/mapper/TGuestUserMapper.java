package com.galaxyeye.websocket.infrastructure.repository.mapper;

import com.galaxyeye.websocket.infrastructure.repository.entity.TGuestUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TGuestUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGuestUserMapper {
    long countByExample(TGuestUserExample example);

    int deleteByExample(TGuestUserExample example);

    int deleteAll();

    int insert(TGuestUser record);

    int insertSelective(TGuestUser record);

    List<TGuestUser> selectByExample(TGuestUserExample example);

    int updateByExampleSelective(@Param("record") TGuestUser record, @Param("example") TGuestUserExample example);

    int updateByExample(@Param("record") TGuestUser record, @Param("example") TGuestUserExample example);
}