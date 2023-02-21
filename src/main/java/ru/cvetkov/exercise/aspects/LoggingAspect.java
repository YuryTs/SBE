package ru.cvetkov.exercise.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Slf4j
@Component
@Aspect
public class LoggingAspect {


    @Pointcut("@annotation(ru.cvetkov.exercise.aspects.Loggable)")
    public void someMethodAdvice() {}


    @Around("someMethodAdvice()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object retval = pjp.proceed();
        long end = System.currentTimeMillis();
        String methodName = pjp.getSignature().getName();
        log.info("Выполнение метода " + methodName + " занимает " +
                (end-start) + " милисек.");
        return retval;
    }
}
