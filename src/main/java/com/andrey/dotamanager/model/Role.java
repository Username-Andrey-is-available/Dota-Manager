package com.andrey.dotamanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private Long id;
    private String name;
    // Другие поля и методы
}
