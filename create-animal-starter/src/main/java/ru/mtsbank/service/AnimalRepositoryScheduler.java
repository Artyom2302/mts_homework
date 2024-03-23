package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.exceptions.ArraySizeException;
import ru.mtsbank.exceptions.MinAgeException;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;


public class AnimalRepositoryScheduler {
    @Autowired
    AnimalsRepository animalsRepository;

    @PostConstruct
    void threadStart(){
        Thread threadPrintDuplicate = new Thread(()->{
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName()+" выполняет метод PrintDuplicate ");
                    animalsRepository.printDuplicate();
                    Thread.sleep(10000);
                } catch (ArraySizeException e) {
                    System.out.println("Ошибка в размере");
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    System.out.println("Ошибка прерывания");
                    throw new RuntimeException(e);
                }
            }
        },"threadPrintDuplicate");
        threadPrintDuplicate.start();

        Thread threadFindAverageAge = new Thread(()->{
            while (true){
                try {

                    double avgAge = animalsRepository.findAverageAge();
                    System.out.println(Thread.currentThread().getName()+" выполняет метод findAverageAge.Результат: "+avgAge);
                    Thread.sleep(20000);
                } catch (ArraySizeException e) {
                    System.out.println("Ошибка в размере");
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    System.out.println("Ошибка прерывания");
                    throw new RuntimeException(e);
                }
            }
        },"threadFindAverageAge");
        threadFindAverageAge.start();
    }

    @Scheduled(fixedRate = 60000)
    void doTask(){
        try {
        //Вызов методов
        //1)findLeapYearNames
        System.out.println("Поиск  животных високосного года");
        Map<String, LocalDate> names = null;

            names = animalsRepository.findLeapYearNames();

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
        }
        catch (ArraySizeException e) {
            System.out.println("Ошибка с размером массива");
            System.out.println(e.getMessage());

        }
        catch (MinAgeException e){
            System.out.println("Ошибка возрастом");
            System.out.println(e.getMessage());
        }
}
}
