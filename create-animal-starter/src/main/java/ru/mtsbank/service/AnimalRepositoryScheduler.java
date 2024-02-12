package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import ru.mtsbank.animals.Animal;

import java.util.Set;

public class AnimalRepositoryScheduler {
    @Autowired
    AnimalsRepository animalsRepository;

    @Scheduled(fixedRate = 60000)
    void doTask(){
        //Вызов методов
        //1)findLeapYearNames
        System.out.println("Поиск  животных високосного года");
        String[] names = animalsRepository.findLeapYearNames();
        for(String str:names){
            System.out.println(names);
        }
        System.out.println();

        //2)findOlderAnimal
        System.out.println("Поиск старших животных");
        Animal[] animals = animalsRepository.findOlderAnimal(3);
        for(Animal animal: animals){
            System.out.println("Животное:"+animal.getName()+"/год рождения: "+animal.getBirthDate()+"|");
        }
        System.out.println();

        //3)findDuplicate()
        System.out.println("Поиск дубликатов");
        Set<Animal> animalsDuplicate = animalsRepository.findDuplicate();
        for(Animal animal: animals){
            System.out.println("Животное:"+animal.getName());
        }

        //4)printDuplicate()
        System.out.println("Вывод дубликатов");
        animalsRepository.printDuplicate();
        System.out.println();
    }
}
