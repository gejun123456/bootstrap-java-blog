package com.rest.converter;

import com.rest.domain.Customer;
import com.rest.dto.CustomerDto;

/**
 * Created by bruce.ge on 2016/10/23.
 */
public class BaseConverter {
    public static CustomerDto convert(Customer co){
        if(co==null){
            return null;
        }
        CustomerDto dto = new CustomerDto();
        dto.setName(co.getName());
        dto.setAddress(co.getAddress());
        dto.setAge(co.getAge());
        dto.setSalary(co.getSalary());
        return dto;
    }
}
