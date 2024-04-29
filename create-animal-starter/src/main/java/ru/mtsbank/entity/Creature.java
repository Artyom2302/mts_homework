package ru.mtsbank.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "creature")
@Data
public class Creature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "type_id")
    int typeId;

    @Column(name = "age")
    int age;

    @ManyToOne()
    @JoinColumn(name="id_breed")
    Breed breed;

    @Column(name = "birth_date")
    private LocalDate birthDate;

}
