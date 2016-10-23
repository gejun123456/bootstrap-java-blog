package com;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.SampleController.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest(){
        String body = this.restTemplate.getForObject("/", String.class);
        Assertions.assertThat(body).isEqualTo("hello world");
    }
}
