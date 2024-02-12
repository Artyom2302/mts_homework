package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.mtsbank.config.CreateServiceAutoConfiguration;
import ru.mtsbank.service.AnimalsRepositoryImpl;
import ru.mtsbank.service.CreateAnimalServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringContextTest {
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
        Assertions.assertArrayEquals(new String[]{"persik","barsik","pushok"},configuration.getProperties().getCatNames());
        Assertions.assertArrayEquals(new String[]{"sharik","persik","sobaka"},configuration.getProperties().getDogNames());

    }

}
