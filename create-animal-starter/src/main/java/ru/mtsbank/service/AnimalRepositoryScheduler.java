package ru.mtsbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.dao.Creature;
import ru.mtsbank.dao.CreatureDAO;
import ru.mtsbank.exceptions.ArraySizeException;
import ru.mtsbank.exceptions.MinAgeException;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class AnimalRepositoryScheduler {
    @Autowired
    FileWorkService fileWorkService;
    @Autowired
    CreatureDAO creatureDAO;

    @PostConstruct
    void threadStart(){
        Thread threadPrintDuplicate = new Thread(()->{
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName()+" выполняет метод PrintDuplicate ");
                    fileWorkService.animalsRepository.printDuplicate();
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
                    double avgAge = fileWorkService.animalsRepository.findAverageAge();
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
//        try {
//        //Вызов методов
//        //1)findLeapYearNames
//        System.out.println("Поиск  животных високосного года");
//        fileWorkService.writeFindLeapYearFile();
//        Map<String, LocalDate> names = null;
//        names = fileWorkService.readFindLeapYearFile();
//        for(Map.Entry<String, LocalDate> keyValue:names.entrySet()){
//            System.out.println(keyValue.getKey()+" : "+keyValue.getValue());
//        }
//        System.out.println("-----------------------");
//
//
//
//
//        //2)findOlderAnimal
//        System.out.println("Поиск старших животных");
//        fileWorkService.writeOlderAnimal(5);
//        Map<Animal, Integer> animals = fileWorkService.readOlderAnimal();
//        for(Map.Entry<Animal, Integer> animal: animals.entrySet()){
//            System.out.println("Животное:"+animal.getKey().getName()+"/Возраст: "+animal.getValue()+"|");
//        }
//        System.out.println("-----------------------");
//
//        //3)findDuplicate
//        System.out.println("Поиск дубликатов");
//        fileWorkService.writeFindDuplicate();
//        Map<String,Integer> duplicates = fileWorkService.readFindDuplicate();
//        for(Map.Entry<String,Integer> keyValue:duplicates.entrySet()){
//                System.out.println(keyValue.getKey()+":"+keyValue.getValue());
//        }
//        System.out.println("-----------------------");
//        //4)findMinCost
//            System.out.println("Поиск с минимальной ценой");
//        fileWorkService.writeFindMinCost();
//        List<String> minCost = fileWorkService.readFindMinCost();
//        for (String str: minCost){
//            System.out.print(str+", ");
//        }
//            System.out.println();
//            System.out.println("-----------------------");
//        //5)findAvgAge
//            System.out.println("Поиск среднего возраста");
//            fileWorkService.writeAvgAge();
//            System.out.println("Средний возраст равен "+ fileWorkService.readAveAge());
//            System.out.println("-----------------------");
//
//        //6)findOldAndExpensive
//            System.out.println("Поиск старого и дорогово животного");
//            fileWorkService.writeFindOldAndExpensive();
//            List<Animal> animalList = fileWorkService.readFindOldAndExpensive();
//            for(Animal animal:animalList){
//                System.out.println("Животное " +animal.getName()+ " говорит "+animal.getSecretInformation());
//            }
//            System.out.println("-----------------------");
//        }
//        catch (ArraySizeException e) {
//            System.out.println("Ошибка с размером массива");
//            System.out.println(e.getMessage());
//
//        }
//        catch (MinAgeException e){
//            System.out.println("Ошибка возрастом");
//            System.out.println(e.getMessage());
//        } catch (JsonProcessingException e) {
//            System.out.println("Ошибка в обработке Json");
//        }

        try {
            List<Creature> result = creatureDAO.getCreature();
            for(Creature creature: result){
                System.out.println(creature);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
