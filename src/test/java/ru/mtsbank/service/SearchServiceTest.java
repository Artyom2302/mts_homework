package ru.mtsbank.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mtsbank.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;


public class SearchServiceTest {
    SearchServiceImpl searchService = new SearchServiceImpl();
    Animal[] animals;
    @BeforeEach
    void configuration(){
        animals = new Animal[4];
    }
    @Test
    @DisplayName("Не параметризованный тест для проверки животных високосного года")
    void findLeapYearNamesEqualsTest(){
        animals[0] = new Dog("1","1",BigDecimal.valueOf(1000),"1",LocalDate.now());
        animals[1] = new Dog("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now());
        animals[2] = new Dog("3","3",BigDecimal.valueOf(1000),"3",LocalDate.now());
        animals[3] = new Dog("4","4",BigDecimal.valueOf(1000),"4",LocalDate.now());
        String[] names = {"1","2","3","4"};
        Assertions.assertArrayEquals(names,searchService.findLeapYearNames(animals));
    }
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3,4,5,6,7,8 })
    @DisplayName("Параметризованный тест для проверки животных високосного года")
    void findLeapYearNamesNotEqualsTest(int minusYear){
        animals[0] = new Dog("1","1",BigDecimal.valueOf(1000),"1",LocalDate.now().minusYears(minusYear));
        animals[1] = new Dog("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now().minusYears(minusYear));
        animals[2] = new Dog("3","3",BigDecimal.valueOf(1000),"3",LocalDate.now().minusYears(minusYear));
        animals[3] = new Dog("4","4",BigDecimal.valueOf(1000),"4",LocalDate.now().minusYears(minusYear));
        if (minusYear % 4  == 0){
            String[] names = {"1","2","3","4"};
            Assertions.assertArrayEquals(names,searchService.findLeapYearNames(animals));
        }
        else{
            Assertions.assertArrayEquals(new String[0],searchService.findLeapYearNames(animals));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void findOlderAnimalTest(int N){
        animals[0] = new Dog("1","1",BigDecimal.valueOf(1000),"1",LocalDate.now().minusYears(N+1));
        animals[1] = new Dog("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now());
        animals[2] = new Dog("3","3",BigDecimal.valueOf(1000),"3",LocalDate.now());
        animals[3] = new Dog("4","4",BigDecimal.valueOf(1000),"3",LocalDate.now());
        Assertions.assertArrayEquals(new Animal[]{animals[0]},searchService.findOlderAnimal(animals,LocalDate.now().minusYears(N)));
    }
    @Test
    void findDublicateTest(){
        animals[0] = new Dog("1","1",BigDecimal.valueOf(1000),"1",LocalDate.now());
        animals[1] = new Dog("1","1",BigDecimal.valueOf(1000),"1",LocalDate.now());
        animals[2] =new Cat("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now());
        animals[3] =new Cat("2","2",BigDecimal.valueOf(1000),"2",LocalDate.now());
        Assertions.assertArrayEquals(new Animal[]{animals[0],animals[2]},searchService.findDublicate(animals).toArray());

    }
}
