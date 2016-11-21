package com.rest.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by bruce.ge on 2016/11/21.
 */
@Component
public class ScheduledTask {

    @Scheduled(fixedDelay = 5000)
    public void backUpDatabase(){
        //try to dump the mysql database.
    }
}
