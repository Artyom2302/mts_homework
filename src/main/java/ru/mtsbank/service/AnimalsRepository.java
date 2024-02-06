package ru.mtsbank.service;

import ru.mtsbank.Animal;

import java.util.Set;

public interface AnimalsRepository {
    String[] findLeapYearNames();
    Animal[] findOlderAnimal(int age);
    Set<Animal> findDuplicate();
    void printDuplicate();
}
