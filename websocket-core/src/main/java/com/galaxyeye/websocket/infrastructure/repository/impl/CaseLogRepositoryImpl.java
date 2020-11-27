package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.application.repository.CaseLogRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.CaseLog;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CaseLogExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.CaseLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CaseLogRepositoryImpl implements CaseLogRepository {

    @Autowired
    private CaseLogMapper caseLogMapper;


    @Override
    public long countByExample(CaseLogExample example) {
        return caseLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(CaseLogExample example) {
        return caseLogMapper.deleteByExample(example);
    }

    @Override
    public int insert(CaseLog record) {
        return caseLogMapper.insert(record);
    }

    @Override
    public int insertSelective(CaseLog record) {
        return caseLogMapper.insertSelective(record);
    }

    @Override
    public List<CaseLog> selectByExample(CaseLogExample example) {
        List<CaseLog> list=caseLogMapper.selectByExample(example);
        if(list.isEmpty() || list.size()<=0 || list==null){
            return new ArrayList<>();
        }else{
            return list;
        }
    }

    @Override
    public int updateByExampleSelective(CaseLog record, CaseLogExample example) {
        return caseLogMapper.updateByExampleSelective(record,example);
    }


    @Override
    public int updateByExample(CaseLog record, CaseLogExample example) {
        return caseLogMapper.updateByExample(record,example);
    }

}
