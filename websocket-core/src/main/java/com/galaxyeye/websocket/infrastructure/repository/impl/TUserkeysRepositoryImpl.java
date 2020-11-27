package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:52
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.TUserkeysRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.TUserkeys;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TUserkeysExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TKeyindexMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TUserkeysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TUserkeysRepositoryImpl implements TUserkeysRepository {
    @Autowired
    private TUserkeysMapper tUserkeysMapper;

    @Override
    public Integer deleteByExample(TUserkeysExample example) {
        return tUserkeysMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(TUserkeys record) {
        return tUserkeysMapper.insert(record);
    }

    @Override
    public Integer insertBatch(List<TUserkeys> recordList) {
        return tUserkeysMapper.insertBatch(recordList);
    }


    @Override
    public Integer insertSelective(TUserkeys record) {
        return tUserkeysMapper.insertSelective(record);
    }

    @Override
    public List<TUserkeys> selectByExample(TUserkeysExample example) {
        List<TUserkeys> list=tUserkeysMapper.selectByExample(example);
        if(list.size()<=0 || list.isEmpty() || list==null){
            return new ArrayList<>();
        }else {
            return list;
        }
    }
}
