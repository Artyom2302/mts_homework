package ru.mtsbank.service;

import ru.mtsbank.animals.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames();
    Map<Animal,Integer> findOlderAnimal(int age);
    Map<String,Integer> findDuplicate();
    void printDuplicate();
    double findAverageAge();
    List<Animal> findOldAndExpensive();
    List<String> findMinConstAnimals();
}
