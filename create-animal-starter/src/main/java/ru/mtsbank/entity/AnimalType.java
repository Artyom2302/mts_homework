package ru.mtsbank.entity;


import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "animal_type")
@Data
public class AnimalType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int idType;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "is_wild")
    private boolean isWild;
}
