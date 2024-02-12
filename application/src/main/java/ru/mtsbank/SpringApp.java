package ru.mtsbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mtsbank.service.AnimalsRepository;


@ComponentScan("ru.mtsbank")
@SpringBootApplication
@EnableScheduling
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }
}
