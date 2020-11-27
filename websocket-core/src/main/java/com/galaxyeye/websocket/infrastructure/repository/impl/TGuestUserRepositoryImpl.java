package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.TGuestUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TGuestUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TGuestUserExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TGuestUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TGuestUserRepositoryImpl implements TGuestUserRepository {

    @Autowired
    private TGuestUserMapper tGuestUserMapper;


    @Override
    public Long countByExample(TGuestUserExample example) {
        return tGuestUserMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(TGuestUserExample example) {
        return tGuestUserMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteAll() {
        return tGuestUserMapper.deleteAll();
    }

    @Override
    public Integer insert(TGuestUser record) {
        return tGuestUserMapper.insert(record);
    }

    @Override
    public Integer insertSelective(TGuestUser record) {
        return tGuestUserMapper.insertSelective(record);
    }

    @Override
    public List<TGuestUser> selectByExample(TGuestUserExample example) {
        List<TGuestUser> list=tGuestUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(TGuestUser record, TGuestUserExample example) {
        return tGuestUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(TGuestUser record, TGuestUserExample example) {
        return tGuestUserMapper.updateByExample(record, example);
    }

}
