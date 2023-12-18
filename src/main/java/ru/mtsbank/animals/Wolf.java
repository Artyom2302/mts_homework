package ru.mtsbank.animals;

import ru.mtsbank.abstractClasses.Predator;

import java.math.BigDecimal;

public class Wolf extends Predator {
    public Wolf(String breed, String name, String character){
        this.name = name;
        this.breed = breed;
        this.character = character;
        System.out.println(name);
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
        return null;
    }

    @Override
    public String getCharacter() {
        return character;
    }
}
