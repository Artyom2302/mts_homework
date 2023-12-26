package ru.mtsbank.abstractClasses;

import ru.mtsbank.Animal;

import java.math.BigDecimal;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
}
