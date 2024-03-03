package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Repository;
import ru.mtsbank.animals.Animal;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return animals.stream()
                .filter(animal1 -> animal1.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.toString()+" "+animal.getName(),Animal::getBirthDate));
    }

    @Override
    public Map<Animal,Integer> findOlderAnimal(int age) {
        Map<Animal, Integer> findedanimals = animals.stream()
                .filter(animal -> (Period.between(animal.getBirthDate(), LocalDate.now()).getYears()) > age)
                .collect(Collectors.toMap(animal->animal,animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));
        if (findedanimals.size() == 0){
            //Находим минимальное, так как нужно найти самой старое животное
            Animal animal= animals.stream()
                    .min(Comparator.comparing(Animal::getBirthDate))
                    .get();
            findedanimals.put(animal,Period.between(animal.getBirthDate(), LocalDate.now()).getYears());
        };
        return findedanimals;
    }

    @Override
    public Map<String,Integer> findDuplicate() {

        //Уменьшение на -1 нужно, чтобы не считать сам объект, к которому ищутся дубликаты
        Map<String, Integer> result = animals.stream().collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .filter(animalListEntry ->animalListEntry.getValue().size()>1)
                .collect(Collectors.toMap(animalListEntry -> animalListEntry.getKey().toString(),animalListEntry->animalListEntry.getValue().size()-1));
        return result;
    }

    @Override
    public void printDuplicate() {
        Map<String,Integer> duplicates = findDuplicate();
        for(Map.Entry<String,Integer> keyValue:duplicates.entrySet()){
            System.out.println(keyValue.getKey()+":"+keyValue.getValue());
        }
    }

    @Override
    public double findAverageAge() {
        return animals.stream()
                .mapToInt(animal->Period.between(animal.getBirthDate(),LocalDate.now()).getYears())
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Animal> findOldAndExpensive() {
        var result = animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(),LocalDate.now()).getYears()>5)
                .filter(animal -> animal.getCost().compareTo(
                        BigDecimal.valueOf(animals.stream()
                        .map(Animal::getCost)
                                .mapToDouble(bigDecimal->bigDecimal.doubleValue())
                        .average().orElse(0.0))) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<String> findMinConstAnimals() {
        return animals.stream()
                .filter(animal -> animal.getCost().compareTo(BigDecimal.valueOf(0))>0)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed()).map(Animal::getName).collect(Collectors.toList());
    }

}
