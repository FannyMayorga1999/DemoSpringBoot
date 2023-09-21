package com.example.demov01.dto;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "categoria", schema = "private")
@Data
public class CategoriaDto {

    @Id
    //auto generacion id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String benefit;

    @Column(name = "deleted")
    private boolean deleted;
}
