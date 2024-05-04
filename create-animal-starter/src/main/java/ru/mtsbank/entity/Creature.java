package ru.mtsbank.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "creature")
@Data
public class Creature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_creature")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_id")
    private int typeId;

    @Column(name = "age")
    private int age;

    @ManyToOne()
    @JoinColumn(name="id_breed")
    private Breed breed;

    @Column(name = "birth_date")
    private LocalDate birthDate;

}
