package ru.mtsbank.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "habitat")
public class Habitat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    int areaId;

    @Column(name = "area_name")
    String areaName;
}
