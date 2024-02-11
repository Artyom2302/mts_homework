package ru.mtsbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mtsbank.service.AnimalsRepository;
import ru.mtsbank.service.CreateAnimalService;

import java.util.Set;


@ComponentScan("ru.mtsbank")
@SpringBootApplication
public class SpringApp {

    @Autowired
     private AnimalsRepository animalsRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }
}
