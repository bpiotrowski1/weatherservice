package pl.bpiotrowski.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(ExecutionTimeLog)")
    public void handleLoggingAnnotation() {}

    @Around("handleLoggingAnnotation()")
    public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object o = joinPoint.proceed();
        System.out.println("Execution time: " + (System.currentTimeMillis() - startTime));
        return o;
    }
}
