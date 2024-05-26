package ru.mtsbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.mtsbank.AOP.LogAspect;

@EnableAspectJAutoProxy
@Configuration
public class AOPConfig {
    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
