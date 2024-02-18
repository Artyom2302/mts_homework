package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mtsbank.config.CreateServiceAutoConfiguration;
import ru.mtsbank.service.AnimalsRepositoryImpl;
import ru.mtsbank.service.CreateAnimalServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
public class SpringStarterTest {
    @Autowired
    CreateAnimalServiceImpl createService;
    @Autowired
    AnimalsRepositoryImpl repository;
    @Autowired
    CreateServiceAutoConfiguration configuration;

    @Test
    void postProcessorTest(){
        Assertions.assertNotEquals(null,createService.getType());
    }
    @Test
    void postConstructTest(){
        Assertions.assertNotEquals(null,repository.getAnimals());
    }
    @Test
    void testConfigInit(){
        Assertions.assertArrayEquals(new String[]{"Кот №1","Кот №2", "Кот №3"},configuration.getProperties().getCatNames());
        Assertions.assertArrayEquals(new String[]{"sharik-Test","persik-Test","sobaka-Test"},configuration.getProperties().getDogNames());
    }
}
