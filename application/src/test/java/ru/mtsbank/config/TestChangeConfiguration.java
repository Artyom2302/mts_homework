package ru.mtsbank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.service.CreateAnimalServiceImpl;

@TestConfiguration
public class TestChangeConfiguration {
    @Autowired
    CreateServiceProperties properties;

    @Bean
    @Scope("prototype")
    @Primary
    CreateAnimalServiceImpl testCreateAnimalService(){
        properties.setCatNames(new String[]{"Кот №1","Кот №2", "Кот №3"});
        return new CreateAnimalServiceImpl(properties);
    }

}
