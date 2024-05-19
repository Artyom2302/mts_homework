package ru.mtsbank.AOP;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mtsbank.annotation.Logging;


import java.util.Optional;


@Aspect
@Log4j2
public class LogAspect {
    private static final String ENTER = ">> {}";
    private static final String EXIT = "<< {}";
    @PostConstruct
    void setLog(){
    }
    @Around("@annotation(logging) && execution(* ru.mtsbank..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        String annotationValue = fetchAnnotationValue(joinPoint, logging);
        String logLevel = logging.level();
        boolean entering = logging.entering();
        boolean exiting = logging.exiting();
        Level level = Level.toLevel(logLevel,Level.INFO);
        if (entering) {
            log.log(level,ENTER, annotationValue);
        }
        Object result = joinPoint.proceed();
        if (exiting) {
            log.log(level,EXIT, annotationValue);
        }

        return result;
    }


    private String fetchAnnotationValue(ProceedingJoinPoint joinPoint, Logging logging) {
        return Optional.ofNullable(logging)
                .map(Logging::value)
                .filter(StringUtils::isNotBlank)
                .orElse(joinPoint.getSignature().getName());
    }
}
