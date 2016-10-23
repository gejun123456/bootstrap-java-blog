package com.rest.controller;

import com.rest.converter.BaseConverter;
import com.rest.domain.Customer;
import com.rest.dto.CustomerDto;
import com.rest.dto.Greeting;
import com.rest.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@RestController
public class SimpleRestController {
    private static final String template="Hello,%s";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "world")String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

    @RequestMapping("/getCustomer")
    public CustomerDto getCustomer(@RequestParam(value = "id") int id){
        Customer customer = customerMapper.selectById(id);
        return BaseConverter.convert(customer);
    }

}
