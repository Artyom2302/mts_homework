package ru.mtsbank;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mtsbank.service.AnimalsRepository;
import ru.mtsbank.service.CreateAnimalService;

import java.util.Set;


@ComponentScan("ru.mtsbank")
public class SpringApp {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);
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


        context.close();

    }
}
