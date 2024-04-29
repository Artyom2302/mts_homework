package ru.mtsbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.dao.CreatureDAO;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.exceptions.ArraySizeException;
import ru.mtsbank.exceptions.MinAgeException;
import ru.mtsbank.util.AnimalObjectMapper;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Getter
public class AnimalsRepositoryImpl implements AnimalsRepository {

    List<Animal> animals;

    List<Creature> creatures;

    @Autowired
    CreatureDAO creatureDAO;



    @Autowired
    AnimalObjectMapper animalObjectMapper;

    @Lookup
    public CreateAnimalService getCreateAnimalService() {
        return null;
    }

    @PostConstruct
    void createAnimals() {
        animals = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            CreateAnimalService createAnimalService = getCreateAnimalService();
            animals.add(createAnimalService.getRandomAnimal());
            System.out.println(animals.get(i).getName());
            try {
                System.out.println(animalObjectMapper.objectMapper.writeValueAsString(animals.get(i)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        creatures = creatureDAO.findAll();


    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() throws ArraySizeException {
        if (animals.size() == 0) {
            throw new ArraySizeException(animals.size());
        }
        return animals.stream()
                .filter(animal1 -> animal1.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.toString() + " " + animal.getName(), Animal::getBirthDate));
    }


    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) throws MinAgeException, ArraySizeException {
        if (age <= 0) {
            throw new MinAgeException(age);
        }
        if (animals.size() == 0) {
            throw new ArraySizeException(0);
        }
        Map<Animal, Integer> findedanimals = animals.stream()
                .filter(animal -> (Period.between(animal.getBirthDate(), LocalDate.now()).getYears()) > age)
                .collect(Collectors.toMap(animal -> animal, animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));
        if (findedanimals.size() == 0) {
            //Находим минимальное, так как нужно найти самой старое животное
            Animal animal = animals.stream()
                    .min(Comparator.comparing(Animal::getBirthDate))
                    .get();
            findedanimals.put(animal, Period.between(animal.getBirthDate(), LocalDate.now()).getYears());
        }
        ;
        return findedanimals;
    }


    @Override
    public Map<String, Integer> findDuplicate() throws ArraySizeException {
        if (animals.size() <= 1) {
            throw new ArraySizeException(animals.size());
        }
        //Уменьшение на -1 нужно, чтобы не считать сам объект, к которому ищутся дубликаты
        Map<String, Integer> result = animals.stream().collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .filter(animalListEntry -> animalListEntry.getValue().size() > 1)
                .collect(Collectors.toMap(animalListEntry -> animalListEntry.getKey().toString(), animalListEntry -> animalListEntry.getValue().size() - 1));
        return result;
    }


    @Override
    public void printDuplicate() throws ArraySizeException {
        Map<String, Integer> duplicates = findDuplicate();
        for (Map.Entry<String, Integer> keyValue : duplicates.entrySet()) {
            System.out.println(keyValue.getKey() + ":" + keyValue.getValue());
        }
    }

    @Override
    public double findAverageAge() throws ArraySizeException {
        if (animals.size() == 0) {
            throw new ArraySizeException(animals.size());
        }
        return animals.stream()
                .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElse(0.0);
    }


    @Override
    public List<Animal> findOldAndExpensive() throws ArraySizeException {
        if (animals.size() == 0) {
            throw new ArraySizeException(animals.size());
        }
        var result = animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 2)
                .filter(animal -> animal.getCost().compareTo(
                        BigDecimal.valueOf(animals.stream()
                                .map(Animal::getCost)
                                .mapToDouble(bigDecimal -> bigDecimal.doubleValue())
                                .average().orElse(0.0))) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
        return result;
    }


    @Override
    public List<String> findMinConstAnimals() throws ArraySizeException {
        if (animals.size() == 0) {
            throw new ArraySizeException(animals.size());
        }

        return animals.stream()
                .filter(animal -> animal.getCost() != null && animal.getCost().compareTo(BigDecimal.ZERO) > 0)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .map(Animal::getName)
                .collect(Collectors.toList());
    }
}
