package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import ru.mtsbank.animals.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {


    Animal[] animals;
    public Animal[] getAnimals() {
        return animals;
    }
    @Lookup
    public CreateAnimalService getCreateAnimalService() {
        return null;
    }

    AnimalsRepositoryImpl(){
        animals = new Animal[10];
    }

    @PostConstruct
    void createAnimals(){
        for (int i = 0; i < animals.length ; i++) {
           CreateAnimalService createAnimalService = getCreateAnimalService();
           animals[i] = createAnimalService.getRandomAnimal();
            System.out.println(animals[i].getName());
        }
    }

    @Override
    public String[] findLeapYearNames() {
        List<String> names = new ArrayList<>();
        for (Animal animal:animals){
            if(animal.getBirthDate().isLeapYear()){
                names.add(animal.getName());
            };
        }
        String[] result = new String[names.size()];
        names.toArray(result);
        return result;
    }

    @Override
    public Animal[] findOlderAnimal(int age) {
        List<Animal> animalList = new ArrayList<>();
        for (Animal animal:animals){
            if(Period.between(animal.getBirthDate(), LocalDate.now()).getYears()>=age){
                animalList.add(animal);
            };
        }
        Animal[] result = new Animal[animalList.size()];
        animalList.toArray(result);
        return result;
    }

    @Override
    public Set<Animal> findDuplicate() {
        Set<Animal> set = new HashSet<>();
        Set<Animal> dublicates = new HashSet<>();
        for (int i = 0; i < animals.length; i++) {
            if(!set.add(animals[i])){
                dublicates.add(animals[i]);
            };

        }
        return dublicates;
    }

    @Override
    public void printDuplicate() {
        Set<Animal> duplicates = findDuplicate();
        for(Animal animal:duplicates){
            System.out.println("Животное:"+animal.getName());
        }
    }

}
