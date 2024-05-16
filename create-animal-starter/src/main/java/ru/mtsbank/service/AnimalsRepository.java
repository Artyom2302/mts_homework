package ru.mtsbank.service;

import ru.mtsbank.animals.Animal;
import ru.mtsbank.exceptions.ArraySizeException;
import ru.mtsbank.exceptions.MinAgeException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames() throws ArraySizeException;
    Map<Animal,Integer> findOlderAnimal(int age) throws MinAgeException,ArraySizeException;
    Map<String,Integer> findDuplicate() throws ArraySizeException;
    void printDuplicate() throws ArraySizeException;
    double findAverageAge() throws ArraySizeException;
    List<Animal> findOldAndExpensive() throws ArraySizeException;
    List<String> findMinConstAnimals() throws ArraySizeException;
}
