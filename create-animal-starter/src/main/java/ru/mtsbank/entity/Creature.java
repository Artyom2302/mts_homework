package ru.mtsbank.entity;

import java.sql.Date;
import java.time.LocalDate;


public class Creature {
    private int id;
    private String name;
    private int typeId;
    private int age;



    private LocalDate birthDate;

    public Creature() {
    }
    public Creature(int id, String name, int typeId, int age, Date birthDate) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.age = age;
        this.birthDate = birthDate.toLocalDate();
    }
    @Override
    public String toString() {
        return "Creature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", age=" + age +
                ", birthDate=" + birthDate +
                '}';
    }

}
