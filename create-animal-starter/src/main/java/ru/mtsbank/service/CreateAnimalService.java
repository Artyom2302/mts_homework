package ru.mtsbank.service;


import ru.mtsbank.animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.lang.Math.round;

public interface CreateAnimalService {
    public enum AnimalType{
        CAT,
        DOG,
        WOLF,
        SHARK
    }
    default AnimalType getRandomAnimalType(){
        int choice = (int)(round(Math.random()*4) % 4);
        switch (choice){
            case 0:{
                return AnimalType.WOLF;
            }
            case 1:{
                return AnimalType.CAT;
            }
            case 2:{
                return AnimalType.DOG;
            }
            case 3:{
                return AnimalType.SHARK;
            }
        }
        return AnimalType.CAT;
    }
    default Animal createRandomAnimal(AnimalType type){
        Animal animal = null;
        switch (type){
            case DOG:{
                animal = new Dog("Хаски","Собака", BigDecimal.valueOf(3000),"Добрый",LocalDate.now().minusDays((long)(365*5*Math.random())));
                break;
            }
            case CAT:{
                animal = new Cat("Британец","Кошка", BigDecimal.valueOf(2000),"Гордый",LocalDate.now().minusDays((long)(365*5*Math.random())));
                break;
            }
            case WOLF:{
                animal = new Wolf("Северный","Волк","Злой",LocalDate.now().minusDays((long)(365*5*Math.random())));
                break;
            }
            case SHARK:{
                animal = new Shark("Молот","Акула","Опасная",LocalDate.now().minusDays((long)(365*5*Math.random())));
                break;
            }
        }
        return animal;
    }
    Animal getRandomAnimal();
    default  Animal[] createAnimals(){
        Animal[] animals = new Animal[10];
        int i = 0;
        while (i<10){
            animals[i] = createRandomAnimal(getRandomAnimalType());
            System.out.println("------");
            i++;
        }
        return animals;
    }
}
