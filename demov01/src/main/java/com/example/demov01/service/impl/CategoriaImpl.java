package com.example.demov01.service.impl;

import com.example.demov01.exceptions.ValidationException;
import com.example.demov01.service.ICategoriaService;
import com.example.demov01.dto.CategoriaDto;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaImpl {

    @Autowired
    private ICategoriaService iCategoriaService;

    @GetMapping
    public List<CategoriaDto> getCategorias() {
        return iCategoriaService.listCategoria();
    }

    @PutMapping
    public void deleteById(Long id) {
        iCategoriaService.deleteCategoria(id);
    }

    @GetMapping
    public Optional<CategoriaDto> getCategoriaById(Long id) {
        return iCategoriaService.findById(id);
    }

    @PutMapping
    public void deleteCategoria(CategoriaDto categoria) {
        iCategoriaService.save(categoria);
    }

    @PutMapping
    public void updateCategoria(CategoriaDto categoria) {
        iCategoriaService.save(categoria);
    }

    @PostMapping
    public void createCategoria(CategoriaDto categoria) {
        boolean identificationExistente = iCategoriaService.existsByName(categoria.getName());

        if (identificationExistente) {
            try {
                throw new ValidationException("Categoría ya existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        iCategoriaService.save(categoria);
    }

}
