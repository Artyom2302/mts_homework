package ru.mtsbank.animals;

import ru.mtsbank.abstractClasses.Pet;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {
    public Cat(String breed, String name, BigDecimal cost, String character, LocalDate birthDate){
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }

    @Override
    public String getBread() {
        return this.breed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BigDecimal getCost() {
        return cost.setScale(2);
    }

    @Override
    public String getCharacter() {
        return character;
    }
}
