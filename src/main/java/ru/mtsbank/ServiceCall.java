package ru.mtsbank;

import ru.mtsbank.service.CreateAnimalServiceImpl;
import ru.mtsbank.service.SearchServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;

public class ServiceCall {
    public static void main(String[] args) {
          CreateAnimalServiceImpl animalService = new CreateAnimalServiceImpl();
//        HW-3
//        System.out.println("///////////////");
//        animalService.createAnimals();
//        System.out.println("///////////////");

        Animal[] animals =animalService.createAnimals(10);

        System.out.println("----------Поиск животных високосного года-------------");
        var searchServiceImpl = new SearchServiceImpl();
        System.out.println(Arrays.toString(searchServiceImpl.findLeapYearNames(animals)));

        System.out.println("----------Поиск старших животных-------------");
       Animal[] olderAnimal = searchServiceImpl.findOlderAnimal(animals, LocalDate.now().minusYears(2));
       for (var animal:olderAnimal){
           System.out.println(animal);
       }
        System.out.println("-----------Поиск дубликатов----------");
        //Для поиска дубликатов специально сделаем дубликаты кошек,
        // так как поля имя и порода уже одиновые,
        // остается сделать одинаковой дату
        LocalDate date = LocalDate.now();
        for (Animal animal:animals){
            if (animal.getName() == "Кошка"){
                animal.setBirthDate(date);
            }
        }
        System.out.println("Исходный массив");
        for (var animal:animals)
            System.out.println(animal.toString());
        System.out.println("---------------------------------");
        System.out.println("Дубликаты");
        searchServiceImpl.findDublicate(animals);
    }
}
