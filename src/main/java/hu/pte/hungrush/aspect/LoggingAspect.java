package hu.pte.hungrush.aspect;

import java.time.Duration;
import java.time.Instant;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.logging.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = Logger.getLogger("hu.pte.hungrush.aspect");
    
    @Pointcut("execution(* hu.pte.hungrush.*.*.*(..))")
    public void loggingPointcut() {}
    
    @Around("loggingPointcut()")
    public Object loggingAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        className = className.replaceFirst("class hu.pte.hungrush.", "");
        String methodName = pjp.getSignature().getName();
        
        if (className.startsWith("controller")) {
            log.log(Level.INFO, "Recieved request at endpoint: {0}", methodName);
            Instant start = Instant.now();
            Object result = pjp.proceed();
            Instant end = Instant.now();
            log.log(Level.INFO, "Endpoint {0} returned: {1} in {2} ms", new Object[]{methodName, result, Duration.between(start, end).toMillis()});
            return result;
        }
        else {
            log.log(Level.INFO, "Executing {0}::{1}", new Object[]{className, methodName});
            Instant start = Instant.now();
            Object result = pjp.proceed();
            Instant end = Instant.now();
            log.log(Level.INFO, "Executed {0}::{1} with result: {2} in {3} ms", new Object[]{className, methodName, result, Duration.between(start, end).toMillis()});
            return result;
        }
    }
}
