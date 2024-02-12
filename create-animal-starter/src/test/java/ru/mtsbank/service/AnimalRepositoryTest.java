package ru.mtsbank.service;

import org.junit.jupiter.api.*;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


public  class AnimalRepositoryTest {
    AnimalsRepositoryImpl animalsRepository= new AnimalsRepositoryImpl();
    @BeforeEach
    void initRepository(){
        animalsRepository.animals = new Animal[6];
        animalsRepository.animals[0] = new Dog("1","1", BigDecimal.valueOf(1000),"1", LocalDate.now());
        animalsRepository.animals[1] = new Dog("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now().minusYears(1));
        animalsRepository.animals[2] = new Dog("3","3",BigDecimal.valueOf(1000),"3",LocalDate.now().minusYears(2));
        animalsRepository.animals[3] = new Dog("4","4",BigDecimal.valueOf(1000),"4",LocalDate.now().minusYears(3));
        animalsRepository.animals[4] = new Cat("5","5",BigDecimal.valueOf(1000),"5",LocalDate.now().minusYears(4));
        animalsRepository.animals[5] = new Cat("6","6",BigDecimal.valueOf(1000),"6",LocalDate.now().minusYears(5));
    }

    @Test
    void findLeapYearNamesTest(){
        String[] names = {"1","5"};
        Assertions.assertArrayEquals(names,animalsRepository.findLeapYearNames());
    }

    @Test
    void findOlderTest(){
        Assertions.assertArrayEquals(animalsRepository.animals,animalsRepository.findOlderAnimal(0));
    }
    @Test
    void findDuplicateTest(){
        Set<Animal> set =Set.of(new Animal[]{animalsRepository.animals[0],animalsRepository.animals[4]});
        animalsRepository.animals[2]=animalsRepository.animals[0];
        animalsRepository.animals[5] = animalsRepository.animals[4];
        Assertions.assertEquals(set,animalsRepository.findDuplicate());
    }



}
