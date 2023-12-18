package ru.mtsbank.animals;

import ru.mtsbank.abstractClasses.Pet;

import java.math.BigDecimal;

public class Dog extends Pet {
    Dog(String breed,String name,BigDecimal cost,String character){
        this.name = name;
        this.breed = breed;
        this.cost = cost;
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
        return cost.setScale(2);
    }

    @Override
    public String getCharacter() {
        return character;
    }
}
