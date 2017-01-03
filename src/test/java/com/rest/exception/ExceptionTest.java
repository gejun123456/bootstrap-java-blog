package com.rest.exception;

import org.junit.Test;

/**
 * Created by bruce.ge on 2017/1/4.
 */
public class ExceptionTest {
    @Test
    public void test() {
        nimei();
    }


    public static void nimei() {
        System.out.println("start");
        try {
            throw new RuntimeException("nima");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("hehe");
            //never use return in finally statement.
            return;
        }
    }
}
