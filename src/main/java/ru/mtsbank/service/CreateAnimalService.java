package ru.mtsbank.service;

import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.animals.Wolf;

import java.math.BigDecimal;

public interface CreateAnimalService {
    default  void createAnimals(){
        int i = 1;

        while (i<11){
            Animal animal = new Cat("Порода №"+i,"Собака №"+i, BigDecimal.valueOf(3000*i),"Характер №"+i);
            System.out.println("------");
            i++;
        }
    }
}
