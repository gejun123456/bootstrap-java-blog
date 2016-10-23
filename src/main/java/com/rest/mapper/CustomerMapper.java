package com.rest.mapper;

import com.rest.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@Mapper
public interface CustomerMapper {
    Customer selectById(int id);
}
