package ru.mtsbank.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.animals.Wolf;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Scope("prototype")
public class CreateAnimalServiceImpl implements CreateAnimalService{
    AnimalType type;
    public void setType(AnimalType type) {
        this.type = type;
    }
    public AnimalType getType() {
        return type;
    }

    public Animal getRandomAnimal(){
        return CreateAnimalService.super.createRandomAnimal(type);
    }

    @Override
    public Animal[] createAnimals() {
        int i = 0;
        Animal[] animals = new Animal[10];
        do {
            animals[i] = createRandomAnimal(getRandomAnimalType());
            System.out.println("------");
            i++;
        }while (i<10);
        return animals;
    }


    public Animal[] createAnimals(int number){
        Animal[] animals = new Animal[10];
        for (int i = 0; i <number; i++) {
            animals[i] = createRandomAnimal(getRandomAnimalType());
            System.out.println("------");
        }
        return animals;
    }
    public Animal[] createAnimalsImpl(){
        return CreateAnimalService.super.createAnimals();
    }
}
