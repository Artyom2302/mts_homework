package ru.mtsbank.abstractClasses;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.mtsbank.animals.*;

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
    protected String secretInformation;

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setName(String name) {        this.name = name;
    }

    @Override
    public String getSecretInformation() {
        return secretInformation;
    }
    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
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
