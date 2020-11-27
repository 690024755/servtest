package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.AppidRepository;
import com.galaxyeye.websocket.application.repository.TbProAccountRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.Appid;
import com.galaxyeye.websocket.infrastructure.repository.entity.TKeyindex;
import com.galaxyeye.websocket.infrastructure.repository.entity.TbProAccount;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.AppidExample;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.TbProAccountExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.AppidMapper;
import com.galaxyeye.websocket.infrastructure.repository.mapper.TbProAccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TbProAccountRepositoryImpl implements TbProAccountRepository {

    @Autowired
    private TbProAccountMapper tbProAccountMapper;


    @Override
    public Long countByExample(TbProAccountExample example) {
        return tbProAccountMapper.countByExample(example);
    }


    @Override
    public Integer deleteByExample(TbProAccountExample example) {
        return tbProAccountMapper.deleteByExample(example);
    }


    @Override
    public Integer insert(TbProAccount record) {
        return tbProAccountMapper.insert(record);
    }


    @Override
    public Integer insertSelective(TbProAccount record) {
        return tbProAccountMapper.insertSelective(record);
    }

    @Override
    public List<TbProAccount> selectByExample(TbProAccountExample example) {
        List<TbProAccount> list=tbProAccountMapper.selectByExample(example);
        if(list.size()<=0 || list.isEmpty() || list==null){
            return new ArrayList<>();
        }else {
            return list;
        }
    }


    @Override
    public Integer updateByExampleSelective(TbProAccount record, TbProAccountExample example) {
        return tbProAccountMapper.updateByExampleSelective(record,example);
    }


    @Override
    public Integer updateByExample(TbProAccount record, TbProAccountExample example) {
        return tbProAccountMapper.updateByExample(record,example);
    }
}
