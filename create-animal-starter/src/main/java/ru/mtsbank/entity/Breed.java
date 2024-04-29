package ru.mtsbank.entity;

import lombok.Data;

import javax.persistence.*;
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
