package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.TUserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TUserRepositoryImpl implements TUserRepository {

    @Autowired
    private TUserMapper tUserMapper;


    @Override
    public Integer deleteByExample(TUserExample example) {
        return tUserMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(TUser record) {
        return tUserMapper.insert(record);
    }

    @Override
    public Integer insertList(List<TKeyindex> recordList) {

        return null;
    }

    @Override
    public Integer insertSelective(TUser record) {
        return tUserMapper.insertSelective(record);
    }

    @Override
    public List<TUser> selectByExample(TUserExample example) {
        List<TUser> list=tUserMapper.selectByExample(example);
        if(list.size()<=0 || list.isEmpty() || list==null){
            return new ArrayList<>();
        }else {
            return list;
        }
    }

}
