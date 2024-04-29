package ru.mtsbank.entity;


import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "animal_type")
public class AnimalType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    int idType;

    @Column(name = "type_name")
    String typeName;

    @Column(name = "is_wild")
    boolean isWild;
}
