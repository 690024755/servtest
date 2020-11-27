package com.galaxyeye.websocket.infrastructure.repository.impl; /*
 * Description:com.galaxyeye.websocket.infrastructure.repository.impl
 * @Date Create on 16:26
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/11/13日galaxyeye All Rights Reserved.
 */
import com.galaxyeye.websocket.application.repository.CustomerServiceStaffRepository;
import com.galaxyeye.websocket.infrastructure.repository.entity.CustomerServiceStaff;
import com.galaxyeye.websocket.infrastructure.repository.entity.example.CustomerServiceStaffExample;
import com.galaxyeye.websocket.infrastructure.repository.mapper.CustomerServiceStaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CustomerServiceStaffRepositoryImpl implements CustomerServiceStaffRepository {

    @Autowired
    private CustomerServiceStaffMapper customerServiceStaffMapper;


    @Override
    public Long countByExample(CustomerServiceStaffExample example) {
        return customerServiceStaffMapper.countByExample(example);
    }

    @Override
    public Integer deleteByExample(CustomerServiceStaffExample example) {
        return customerServiceStaffMapper.deleteByExample(example);
    }

    @Override
    public Integer insert(CustomerServiceStaff record) {
        return customerServiceStaffMapper.insert(record);
    }

    @Override
    public Integer insertSelective(CustomerServiceStaff record) {
        return customerServiceStaffMapper.insertSelective(record);
    }

    @Override
    public List<CustomerServiceStaff> selectByExample(CustomerServiceStaffExample example) {
        List<CustomerServiceStaff> list=customerServiceStaffMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Integer updateByExampleSelective(CustomerServiceStaff record, CustomerServiceStaffExample example) {
        return customerServiceStaffMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer updateByExample(CustomerServiceStaff record, CustomerServiceStaffExample example) {
        return customerServiceStaffMapper.updateByExample(record, example);
    }
}
