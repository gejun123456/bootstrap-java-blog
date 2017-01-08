package com.rest.aop;

import com.google.common.base.Joiner;
import com.rest.annotation.ExecutionTime;
import com.rest.event.ExecutionTimeLogEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Aspect
@Component
public class ExecutionTimeAspect {

    private static Logger logger = LoggerFactory.getLogger("executionTime");

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Pointcut("@annotation(com.rest.annotation.ExecutionTime)")
    public void annotatedWithExecutionTime() {
    }

    @Around("annotatedWithExecutionTime()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        StringBuilder builder = new StringBuilder();
        builder.append(signature.getDeclaringTypeName());
        builder.append("." + signature.getName() + "(");
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                builder.append(args[i].getClass().getName());
                if (i != args.length - 1) {
                    builder.append(", ");
                }
            }
        }
        builder.append(")");
        builder.append(" cost time " + (end - start) + "ms");
        ExecutionTime annotation = signature.getMethod().getAnnotation(ExecutionTime.class);
        if (end - start > annotation.logArgsTime()) {
            builder.append("\n the execution time over " + annotation.logArgsTime());
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    builder.append(args[i].toString());
                    if (i != args.length - 1) {
                        builder.append("\n");
                    }
                }
            }
        }
        logger.info(builder.toString());
        if (annotation.logToDatabase()) {
            ExecutionTimeLogEvent executionTimeLogEvent = ExecutionTimeLogEvent.builder()
                    .className(signature.getDeclaringTypeName())
                    .methodName(signature.getName())
                    .argsValue(Joiner.on(",").join(args))
                    .executionTime(end - start)
                    .createTime(new Date())
                    .build();
            applicationEventPublisher.publishEvent(executionTimeLogEvent);
        }
        return proceed;
    }
}
