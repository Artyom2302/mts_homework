package ru.mtsbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mtsbank.service.AnimalsRepository;



@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("ru.mtsbank")
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }
}
