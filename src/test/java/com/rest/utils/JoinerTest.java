package com.rest.utils;

import com.google.common.base.Joiner;
import org.junit.Test;

/**
 * Created by bruce.ge on 2017/1/8.
 */
public class JoinerTest {
    @Test
    public void testJoiner(){
        Object[] bb= new Object[0];
        String join = Joiner.on(",").join(bb);
        System.out.println(join);
    }
}
