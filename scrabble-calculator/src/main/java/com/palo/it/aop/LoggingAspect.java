package com.palo.it.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Slf4j
@Aspect
public class LoggingAspect {

    private final Environment environment;

    public LoggingAspect(Environment environment) {
        this.environment = environment;
    }


    @Pointcut("within(@org.springframework.stereotype.Repository *) || within(@org.springframework.stereotype.Service * ) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointCut(){}

    @Pointcut("within(com.palo.it.repository..*) || within(com.palo.it.service..*) || within(com.palo.it.rest..*)")
    public void applicationPackagePointCut(){}

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterThrowing(pointcut = "applicationPackagePointCut() && springBeanPointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger(joinPoint).error("Exception in {}() with cause = {}", joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    @Around("applicationPackagePointCut() && springBeanPointCut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Logger log = logger(proceedingJoinPoint);
        if(log.isDebugEnabled()) {
            log.debug("Enter : {}() with argument[s] : {}", proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
        }
        try {
            Object result = proceedingJoinPoint.proceed();
            if(log.isDebugEnabled()) {
                log.debug("Exit : {}() with result = {}", proceedingJoinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(proceedingJoinPoint.getArgs()), proceedingJoinPoint.getSignature().getName());
            throw  e;
        }
    }
}
