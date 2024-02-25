package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import ru.mtsbank.animals.Animal;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class AnimalRepositoryScheduler {
    @Autowired
    AnimalsRepository animalsRepository;

    @Scheduled(fixedRate = 60000)
    void doTask(){
        //Вызов методов
        //1)findLeapYearNames
        System.out.println("Поиск  животных високосного года");
        var names = animalsRepository.findLeapYearNames();
        for(Map.Entry<String, LocalDate> keyValue:names.entrySet()){
            System.out.println(keyValue.getKey()+" : "+keyValue.getValue());
        }
        System.out.println();

        //2)findOlderAnimal
        System.out.println("Поиск старших животных");
        Map<Animal, Integer> animals = animalsRepository.findOlderAnimal(3);
        for(Map.Entry<Animal, Integer> animal: animals.entrySet()){
            System.out.println("Животное:"+animal.getKey().getName()+"/Возраст: "+animal.getValue()+"|");
        }
        System.out.println();


        //4)printDuplicate()
        System.out.println("Вывод дубликатов");
        animalsRepository.printDuplicate();
        System.out.println();
    }
}
