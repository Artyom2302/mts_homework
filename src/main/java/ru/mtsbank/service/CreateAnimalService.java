package ru.mtsbank.service;

import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.animals.Wolf;

import java.math.BigDecimal;

import static java.lang.Math.round;

public interface CreateAnimalService {
    default Animal createRandomAnimal(int i){
        switch ((int)(round(Math.random()*4) % 4)){
            case 0:{
                return new Dog("Порода №"+i,"Собака №"+i, BigDecimal.valueOf(3000*i),"Добрый");
            }
            case 1:{
                return new Cat("Порода №"+i,"Кошка №"+i, BigDecimal.valueOf(2000*i),"Гордый");
            }
            case 2:{
                return new Wolf("Порода №"+i,"Волк №"+i,"Злой");
            }
            case 3:{
                return new Shark("Порода №"+i,"Акула №"+i,"Опасная");
            }
            default:{
                break;
            }
        }
        return null;
    }
    default  void createAnimals(){
        int i = 1;

        while (i<11){
            Animal animal = createRandomAnimal(i);
            System.out.println("------");
            i++;
        }
    }
}
