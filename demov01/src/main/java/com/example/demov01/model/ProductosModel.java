package com.example.demov01.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class ProductosModel {
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    private boolean deleted;
    private String categoria;

    public ProductosModel() {
    }

    public ProductosModel(Long id, String name, String description, String categoria) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoria = categoria;
    }
}
