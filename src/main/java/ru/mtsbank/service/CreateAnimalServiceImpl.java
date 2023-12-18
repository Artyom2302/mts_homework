package ru.mtsbank.service;

import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Wolf;

import java.math.BigDecimal;

public class CreateAnimalServiceImpl implements CreateAnimalService{
    @Override
    public void createAnimals() {
        int i = 1;
        Animal animal;
        do {
            animal= new Cat("Порода №"+i,"Кошка №"+i, BigDecimal.valueOf(2000*i),"Характер №"+i);
            System.out.println("------");
            i++;
        }while (i<11);
    }

    public void createAnimals(int number){
        Animal animal;
        for (int i = 1; i <number+1; i++) {
            animal= new Wolf("Порода №"+i,"Волк №"+i,"Характер №"+i);
            System.out.println("------");
        }
    }
    public void createAnimalsImpl(){
        CreateAnimalService.super.createAnimals();
    }
}
