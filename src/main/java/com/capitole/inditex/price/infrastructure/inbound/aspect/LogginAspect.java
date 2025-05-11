package com.capitole.inditex.price.infrastructure.inbound.aspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogginAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)" +
            " || within(@org.springframework.stereotype.Service *)")
    void springComponentsPointcut() {
    }

    @Around("springComponentsPointcut()")
    Object logForComponents(ProceedingJoinPoint joinPoint)
            throws
            Throwable {
        log.info("Entered: {} with argument[{}] ",
                joinPoint
                        .getSignature()
                        .toShortString(),
                Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.info("Exit: {} with result = {}",
                joinPoint
                        .getSignature()
                        .toShortString(),
                result);
        return result;
    }
}
