package ru.mtsbank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.service.AnimalRepositoryScheduler;
import ru.mtsbank.service.CreateAnimalServiceImpl;


@Configuration
@EnableConfigurationProperties(CreateServiceProperties.class)
public class  CreateServiceAutoConfiguration {
    @Autowired
    CreateServiceProperties properties;

    public CreateServiceProperties getProperties() {
        return properties;
    }
    @Bean
    @Scope("prototype")
    CreateAnimalServiceImpl createAnimalService(){
        return new CreateAnimalServiceImpl(properties);
    }
    @Bean
    AnimalRepositoryScheduler createScheduler(){
        return new AnimalRepositoryScheduler();
    }

}
