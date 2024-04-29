package ru.mtsbank.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "provider")
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    int providerId;
    @Column(name = "name")
    String name;
    @Column(name = "phone")
    String phone;
}
