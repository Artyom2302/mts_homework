package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Repository;
import ru.mtsbank.animals.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {


    List<Animal> animals;
    public List<Animal> getAnimals() {
        return animals;
    }
    @Lookup
    public CreateAnimalService getCreateAnimalService() {
        return null;
    }

    @PostConstruct
    void createAnimals(){
        animals = new ArrayList<>(20);
        for (int i = 0; i < 20 ; i++) {
           CreateAnimalService createAnimalService = getCreateAnimalService();
            animals.add(createAnimalService.getRandomAnimal());
            System.out.println(animals.get(i).getName());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> result = new HashMap<>();
        for (Animal animal:animals){
            if(animal.getBirthDate().isLeapYear()){
                result.put(animal.toString()+" "+animal.getName(),animal.getBirthDate());
            };
        }
        return result;
    }

    @Override
    public Map<Animal,Integer> findOlderAnimal(int age) {
        Map<Animal,Integer> animalIntegerMap = new HashMap<>();
        int maxlifeAge = 0;
        Animal animalwithMaxAge = null;
        for (Animal animal:animals){
            int lifeAge = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
            if(lifeAge>=age){
                animalIntegerMap.put(animal,lifeAge);
                continue;
            };
            if(lifeAge>=maxlifeAge){
                animalwithMaxAge = animal;
                maxlifeAge = lifeAge;
            }
        }
        if (animalIntegerMap.size() == 0){
            animalIntegerMap.put(animalwithMaxAge,maxlifeAge);
        }

        return animalIntegerMap;
    }

    @Override
    public Map<String,Integer> findDuplicate() {
        Set<Animal> set = new HashSet<>();
        Map<String,Integer>  dublicates = new HashMap<>();
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if(!set.add(animal)){
               if(dublicates.containsKey(animal.toString())){
                   Integer count = dublicates.get(animal.toString());
                   dublicates.replace(animal.toString(),++count);
               }
               else {
                   dublicates.put(animal.toString(),1);
               }
            };

        }
        return dublicates;
    }

    @Override
    public void printDuplicate() {
        Map<String,Integer> duplicates = findDuplicate();
        for(Map.Entry<String,Integer> keyValue:duplicates.entrySet()){
            System.out.println(keyValue.getKey()+":"+keyValue.getValue());
        }
    }

}
