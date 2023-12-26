package ru.mtsbank.service;

import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.animals.Wolf;

import java.math.BigDecimal;

import static java.lang.Math.round;

public interface CreateAnimalService {
    public enum AnimalType{
        CAT,
        DOG,
        WOLF,
        SHARK
    }
    default AnimalType getRandomAnimalType(int i){
        switch ((int)(round(Math.random()*4) % 4)){
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
                animal = new Dog("Хаски","Собака", BigDecimal.valueOf(3000),"Добрый");
                break;
            }
            case CAT:{
                animal = new Cat("Британец","Кошка", BigDecimal.valueOf(2000),"Гордый");
                break;
            }
            case WOLF:{
                animal = new Wolf("Северный","Волк","Злой");
                break;
            }
            case SHARK:{
                animal = new Shark("Молот","Акула","Опасная");
                break;
            }
        }
        return animal;
    }
    default  Animal[] createAnimals(){
        Animal[] animals = new Animal[10];
        int i = 0;
        while (i<10){
            animals[i] = createRandomAnimal(getRandomAnimalType(i));
            System.out.println("------");
            i++;
        }
        return animals;
    }
}
