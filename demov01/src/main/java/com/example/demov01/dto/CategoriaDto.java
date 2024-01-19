package com.example.demov01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "categoria", schema = "private")
public class CategoriaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted;

    // Para ignorar el campo
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoDto> productos;

    // Constructor para la relación
    public CategoriaDto() {
    }

    // Constructor para la service de categorias
    public CategoriaDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

