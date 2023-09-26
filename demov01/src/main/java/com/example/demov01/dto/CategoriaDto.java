package com.example.demov01.dto;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "categoria", schema="private")
public class CategoriaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted;
}
