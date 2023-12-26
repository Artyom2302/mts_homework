package ru.mtsbank.abstractClasses;

import ru.mtsbank.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractAnimal implements Animal {
    public AbstractAnimal(){
        birthDate = LocalDate.now().minusDays((long)(365*5*Math.random()));
    }
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() !=obj.getClass())
            return false;
        AbstractAnimal animal = (AbstractAnimal)obj;
        if (!getBread().equals(animal.getBread()))
            return false;
        if (!getCharacter().equals(animal.getCharacter()))
            return false;
        if (!getBirthDate().equals(animal.getBirthDate()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getClass()+"{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
}
