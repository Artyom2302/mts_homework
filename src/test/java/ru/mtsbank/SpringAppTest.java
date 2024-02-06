package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mtsbank.service.AnimalsRepository;
import ru.mtsbank.service.AnimalsRepositoryImpl;
import ru.mtsbank.service.CreateAnimalServiceImpl;

import javax.annotation.PostConstruct;

public class SpringAppTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);
    @Test
    void initCreateServiceTypeTest(){
        CreateAnimalServiceImpl createAnimalService = context.getBean(CreateAnimalServiceImpl.class);
        Assertions.assertNotEquals(null,createAnimalService.getType());
    }
    @Test
    void PostConstructTest(){
        AnimalsRepositoryImpl animalsRepository = context.getBean(AnimalsRepositoryImpl.class);
        Assertions.assertNotEquals(0,animalsRepository.getAnimals().length);
    }
}
