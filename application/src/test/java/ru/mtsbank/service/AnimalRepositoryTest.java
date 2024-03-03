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
import java.time.Period;
import java.util.*;

@SpringBootTest
public class AnimalRepositoryTest {
    @Autowired
    AnimalsRepositoryImpl animalsRepository;

    @BeforeEach
    void initRepository(){
        animalsRepository.animals = new ArrayList<>();
        animalsRepository.animals.add(new Dog("1","1", BigDecimal.valueOf(700),"1", LocalDate.now()));
        animalsRepository.animals.add(new Dog("2","2",BigDecimal.valueOf(800),"2",LocalDate.now().minusYears(1)));
        animalsRepository.animals.add(new Dog("3","3",BigDecimal.valueOf(900),"3",LocalDate.now().minusYears(2)));
        animalsRepository.animals.add(new Dog("4","4",BigDecimal.valueOf(1100),"4",LocalDate.now().minusYears(3)));
        animalsRepository.animals.add(new Cat("5","5",BigDecimal.valueOf(1200),"5",LocalDate.now().minusYears(4)));
        animalsRepository.animals.add(new Cat("6","6",BigDecimal.valueOf(1300),"6",LocalDate.now().minusYears(5)));
    }
    @Test
    void findLeapYearNamesTest(){
        Map<String,LocalDate> expected = new HashMap<>();
        expected.put("Собака 1",animalsRepository.getAnimals().get(0).getBirthDate());
        expected.put("Кошка 5",animalsRepository.getAnimals().get(4).getBirthDate());
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
    @Test
    void findAvgAgeTest(){
        double age =0;
        for (int i = 0; i < animalsRepository.animals.size(); i++) {
            age+= Period.between(animalsRepository.animals.get(i).getBirthDate(),LocalDate.now()).getYears();
        }
        age = age/animalsRepository.animals.size();
        Assertions.assertEquals(age,animalsRepository.findAverageAge());
    }
    @Test
    void findOldAndExpensiveTest(){
        animalsRepository.animals.get(5).setBirthDate(LocalDate.now().minusYears(7));
        animalsRepository.animals.get(4).setBirthDate(LocalDate.now().minusYears(8));
        animalsRepository.animals.get(3).setBirthDate(LocalDate.now().minusYears(9));
        List<Animal> expected = new ArrayList<>();
        expected.add(animalsRepository.animals.get(5));
        expected.add(animalsRepository.animals.get(4));
        expected.add(animalsRepository.animals.get(3));
        Collections.sort(expected,Comparator.comparing(Animal::getBirthDate));
        Assertions.assertEquals(expected,animalsRepository.findOldAndExpensive());
    }
    @Test
    void findMinCostTest(){
        List<String> names = new ArrayList<>(List.of(new String[]{"1", "2", "3"}));
        Assertions.assertEquals(3,animalsRepository.findMinConstAnimals().size());
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        Assertions.assertEquals(names,animalsRepository.findMinConstAnimals());

    }

}
