package ru.mtsbank.service;

import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Wolf;

import java.math.BigDecimal;

public class CreateAnimalServiceImpl implements CreateAnimalService{
    @Override
    public Animal[] createAnimals() {
        int i = 0;
        Animal[] animals = new Animal[10];
        do {
            animals[i] = createRandomAnimal(getRandomAnimalType(i));
            System.out.println("------");
            i++;
        }while (i<10);
        return animals;
    }

    public Animal[] createAnimals(int number){
        Animal[] animals = new Animal[10];
        for (int i = 0; i <number; i++) {
            animals[i] = createRandomAnimal(getRandomAnimalType(i));
            System.out.println("------");
        }
        return animals;
    }
    public Animal[] createAnimalsImpl(){
        return CreateAnimalService.super.createAnimals();
    }
}
