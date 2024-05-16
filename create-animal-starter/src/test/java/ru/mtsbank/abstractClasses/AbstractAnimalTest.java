package ru.mtsbank.abstractClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AbstractAnimalTest {
    @Test
    @DisplayName("Тест для проверки разных животных")
    void equalsTestFalse(){
        Cat cat = new Cat("Британец","Кот", BigDecimal.valueOf(3000),"Злой",LocalDate.now());
        Dog dog = new Dog("Хаски","Собака", BigDecimal.valueOf(3000),"Добрый",LocalDate.now());
        Assertions.assertFalse(cat.equals(dog));
    }

    @Test
    @DisplayName("Тест для проверки одинаковых животных")
    void equalsTestTrue(){
        Cat firstCat = new Cat("Британец","Кот", BigDecimal.valueOf(3000),"Злой",LocalDate.now());
        Cat secondCat = new Cat("Британец","Кот", BigDecimal.valueOf(3000),"Злой",LocalDate.now());
        Assertions.assertTrue(firstCat.equals(secondCat));
    }
}
