package ru.mtsbank.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "provider")
@Data
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private int providerId;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
}
