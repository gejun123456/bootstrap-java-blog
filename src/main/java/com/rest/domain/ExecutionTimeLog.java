package com.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionTimeLog {
    private Integer id;

    private String className;

    private String methodName;

    private Long executionTime;

    private String argsValue;

    private Date createTime;
}
