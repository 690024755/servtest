package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.TAllUsersRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TAllUsers;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TAllUsersExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TAllUsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TAllUsersRepositoryImpl implements TAllUsersRepository {

    @Autowired
    private TAllUsersMapper tAllUsersMapper;

    @Override
    public Long countByExample(TAllUsersExample example) {
        return tAllUsersMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(TAllUsersExample example) {
        return tAllUsersMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(TAllUsers record) {
        return tAllUsersMapper.insert(record);
    }

    @Override
    public Integer insertSelective(TAllUsers record) {
        return tAllUsersMapper.insertSelective(record);
    }

    @Override
    public List<TAllUsers> selectByExample(TAllUsersExample example) {
        List<TAllUsers> list=tAllUsersMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(TAllUsers record, TAllUsersExample example) {
        return tAllUsersMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(TAllUsers record, TAllUsersExample example) {
        return tAllUsersMapper.updateByExample(record, example);
    }
}
