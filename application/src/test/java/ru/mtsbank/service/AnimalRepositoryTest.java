package ru.mtsbank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class AnimalRepositoryTest {
    @Autowired
    AnimalsRepositoryImpl animalsRepository;

    @BeforeEach
    void initRepository(){
        animalsRepository.animals = new ArrayList<>();
        animalsRepository.animals.add(new Dog("1","1", BigDecimal.valueOf(1000),"1", LocalDate.now()));
        animalsRepository.animals.add(new Dog("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now().minusYears(1)));
        animalsRepository.animals.add(new Dog("3","3",BigDecimal.valueOf(1000),"3",LocalDate.now().minusYears(2)));
        animalsRepository.animals.add(new Dog("4","4",BigDecimal.valueOf(1000),"4",LocalDate.now().minusYears(3)));
        animalsRepository.animals.add(new Cat("5","5",BigDecimal.valueOf(1000),"5",LocalDate.now().minusYears(4)));
        animalsRepository.animals.add(new Cat("6","6",BigDecimal.valueOf(1000),"6",LocalDate.now().minusYears(5)));
    }
    @Test
    void findLeapYearNamesTest(){
        Map<String,LocalDate> expected = new HashMap<>();
        expected.put("Собака 1",animalsRepository.getAnimals().get(1).getBirthDate());
        expected.put("Кошка 5",animalsRepository.getAnimals().get(5).getBirthDate());
        Assertions.assertEquals(expected,animalsRepository.findLeapYearNames());
    }
    @ParameterizedTest
    @ValueSource(ints={5,6})
    void findOlderTest(int age){
        Map<Animal,Integer> expected = new HashMap<>();
        expected.put(animalsRepository.getAnimals().get(5),5);
        Assertions.assertEquals(expected,animalsRepository.findOlderAnimal(age));
    }
    @Test
    void findDublicates(){
       animalsRepository.getAnimals().set(2,animalsRepository.getAnimals().get(0));
       animalsRepository.getAnimals().set(3,animalsRepository.getAnimals().get(0));
       animalsRepository.getAnimals().set(4,animalsRepository.getAnimals().get(0));
        Map<String,Integer> expected = new HashMap<>();
        expected.put("Собака",3);
       Assertions.assertEquals(expected,animalsRepository.findDuplicate());

    }
}
