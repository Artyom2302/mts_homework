package ru.mtsbank.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breed_id")
    private int id;

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "breed")
    private List<Creature> creatureList;
}
