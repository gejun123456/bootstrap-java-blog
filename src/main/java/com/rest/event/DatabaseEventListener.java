package com.rest.event;

import com.rest.domain.ExecutionTimeLog;
import com.rest.mapper.ExecutionTimeLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Component
public class DatabaseEventListener {

    public static ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private ExecutionTimeLogDao executionTimeLogDao;

    @EventListener
    public void handleDataBaseLogEvent(ExecutionTimeLogEvent event) {
        executorService.submit(() -> {
            ExecutionTimeLog build = ExecutionTimeLog.builder()
                    .className(event.getClassName())
                    .methodName(event.getMethodName())
                    .argsValue(event.getArgsValue())
                    .createTime(event.getCreateTime())
                    .executionTime(event.getExecutionTime())
                    .build();
            executionTimeLogDao.insert(build);
        });
    }
}
