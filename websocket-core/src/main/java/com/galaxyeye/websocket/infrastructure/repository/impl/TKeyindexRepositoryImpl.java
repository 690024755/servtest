package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 15:09
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/5/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.TKeyindexRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TKeyindexExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TKeyindexMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TKeyindexRepositoryImpl implements TKeyindexRepository {
    @Autowired
    private TKeyindexMapper tKeyindexMapper;

    @Override
    public Integer insert(TKeyindex record) {

        return tKeyindexMapper.insert(record);
    }

    @Override
    public Integer insertBatch(List<TKeyindex> recordList) {
        return tKeyindexMapper.insertBatch(recordList);
    }

    @Override
    public Integer insertSelective(TKeyindex record) {
        return tKeyindexMapper.insertSelective(record);
    }

    @Override
    public Integer deleteByExample(TKeyindexExample example) {
        return tKeyindexMapper.deleteByExample(example);
    }

    @Override
    public List<TKeyindex> selectByExample(TKeyindexExample example) {
        List<TKeyindex> list=tKeyindexMapper.selectByExample(example);
        if(list.size()<=0 || list.isEmpty() || list==null){
            return new ArrayList<>();
        }else {
            return list;
        }
    }
}
