package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.EuserRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Euser;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.EuserExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.EuserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class EuserRepositoryImpl implements EuserRepository {

    @Autowired
    private EuserMapper euserMapper;


    @Override
    public Long countByExample(EuserExample example) {
        return euserMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(EuserExample example) {
        return euserMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(Euser record) {
        return euserMapper.insert(record);
    }

    @Override
    public Integer insertSelective(Euser record) {
        return euserMapper.insertSelective(record);
    }

    @Override
    public List<Euser> selectByExample(EuserExample example) {
        List<Euser> list=euserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(Euser record, EuserExample example) {
        return euserMapper.updateByExampleSelective(record,example);
    }

    @Override
    public Integer updateByExample(Euser record, EuserExample example) {
        return euserMapper.updateByExample(record,example);
    }
}
