package ru.mtsbank;

import ru.mtsbank.service.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl animalService = new CreateAnimalServiceImpl();
        animalService.createAnimals(10);
        System.out.println("///////////////");
        animalService.createAnimals();
        System.out.println("///////////////");
        animalService.createAnimalsImpl();
    }
}
