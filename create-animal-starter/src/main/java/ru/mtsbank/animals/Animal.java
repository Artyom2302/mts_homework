package ru.mtsbank.animals;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс животные, реализует методы характерные для животных
 * @author Artem
 * @version 1.0
 * */


public interface Animal {

    /**
     * Метод реализует получение породы животного
     * @return Возвращает строку, содержащую породу животного
     * */
    String getBread();

    /**
     * Метод реализует получение имени животного
     * @return Возвращает строку, содержащую имя животного
     * */
    String getName();

    /**
     * Метод реализует получение цены животного в магазине
     * @return Возвращает значение стоимости животного в магазине или ноль, если животное в магазине не продается
     * */
    BigDecimal getCost();

    /**
     * Метод реализует получение характер животного
     * @return Возвращает строку, содержащую характер животного
     * */
    String getCharacter();
    LocalDate getBirthDate();
    /**
     * Метод реализует получение секретной информации о животном
     * @return Возвращает строку, содержащую сектетную информацию о животном
     * */
    String getSecretInformation();
    void setSecretInformation(String secretInformation);
    public void setBirthDate(LocalDate birthDate);
}
