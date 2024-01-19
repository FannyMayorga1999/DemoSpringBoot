package com.example.demov01.service.impl;

import com.example.demov01.exceptions.ValidationException;
import com.example.demov01.model.CategoriaModel;
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

    public List<CategoriaDto> getCategorias() {
        return iCategoriaService.listCategoria();
    }

    public void deleteById(Long id) {
        iCategoriaService.deleteCategoria(id);
    }

    public Optional<CategoriaDto> getCategoriaById(Long id) {
        return iCategoriaService.findById(id);
    }

    public void deleteCategoria(CategoriaDto categoria) {
        iCategoriaService.save(categoria);
    }

    public void updateCategoria(Long id, CategoriaModel categoriaModel) {

        boolean categoriaId = !iCategoriaService.existsById(id);

        if (categoriaId) {
            try {
                throw new ValidationException("Categoría no existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(id);
        categoriaDto.setName(categoriaModel.getName());
        categoriaDto.setDescription(categoriaModel.getDescription());

        iCategoriaService.save(categoriaDto);
    }

    public void createCategoria(CategoriaModel categoriaModel) {
        boolean identificationExistente = iCategoriaService.existsByName(categoriaModel.getName());

        if (identificationExistente) {
            try {
                throw new ValidationException("Categoría ya existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setName(categoriaModel.getName());
        categoriaDto.setDescription(categoriaModel.getDescription());

        iCategoriaService.save(categoriaDto);
    }

}
