package ru.mtsbank.service;


import ru.mtsbank.animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.round;

public interface CreateAnimalService {
    public enum AnimalType{

        CAT("Кошка"),
        DOG("Собака"),
        WOLF("Волк"),
        SHARK("Акула");

        AnimalType(String animalTypeName) {
            typeName = animalTypeName;
        }
        private String typeName;

        public String getTypeName() {
            return typeName;
        }
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
    default Map<String, List<Animal>> createAnimals(){
        var result = new HashMap<String,List<Animal>>();
        for (int j = 0; j < 3; j++) {
            AnimalType animalType = getRandomAnimalType();
            List<Animal> animals = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                animals.add(createRandomAnimal(animalType));
            }
            result.put(animalType.getTypeName(),animals);
        }

        return result;
    }
}
