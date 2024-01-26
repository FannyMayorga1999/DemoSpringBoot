package com.example.demov01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CategoriaModel {

    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    private boolean deleted;
    private CategoriaModel categoriaModel;

    // Constructor sin argumentos
    public CategoriaModel() {
    }

    // Constructor con argumentos
    public CategoriaModel(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
