package ru.mtsbank.animals;

import com.fasterxml.jackson.annotation.JsonTypeName;
import ru.mtsbank.abstractClasses.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Wolf extends Predator {
    public Wolf(String breed, String name, String character, LocalDate birthDate){
        this.name = name;
        this.breed = breed;
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
        return this.cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }
    @Override
    public String toString() {
        return "Волк";
    }
}
