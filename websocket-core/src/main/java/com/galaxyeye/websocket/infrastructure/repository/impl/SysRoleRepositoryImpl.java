package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.SysRoleRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.SysRole;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.SysRoleExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.SysRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SysRoleRepositoryImpl implements SysRoleRepository {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public Long countByExample(SysRoleExample example) {
        return sysRoleMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(SysRoleExample example) {
        return sysRoleMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public Integer insertSelective(SysRole record) {
        return sysRoleMapper.insertSelective(record);
    }

    @Override
    public List<SysRole> selectByExample(SysRoleExample example) {
        List<SysRole> list=sysRoleMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(SysRole record, SysRoleExample example) {
        return sysRoleMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(SysRole record, SysRoleExample example) {
        return sysRoleMapper.updateByExample(record, example);
    }
}
