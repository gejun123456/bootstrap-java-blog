package com.rest.mapper;

import basic.AbstractTest;
import com.rest.domain.Archives;
import com.rest.domain.ContentTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bruce.ge on 2016/11/7.
 */
public class ContentTimeMapperTest extends AbstractTest{
    @Autowired
    private ContentTimeMapper contentTimeMapper;
    @Test
    public void insert() throws Exception {

    }

    @Test
    public void getDistinctYears() throws Exception {
        printToGson(contentTimeMapper.getDistinctYears());
    }

    @Test
    public void findByYear() throws Exception {
        List<Archives> byYear =
                contentTimeMapper.findByYear(2016);
        printToGson(byYear);
    }

}