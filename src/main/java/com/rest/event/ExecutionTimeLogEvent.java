package com.rest.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExecutionTimeLogEvent {
    private String className;

    private String methodName;

    private String argsValue;

    private long executionTime;

    private Date createTime;
}
