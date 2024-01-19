package com.example.demov01.service.impl;

import com.example.demov01.exceptions.ValidationException;
import com.example.demov01.model.CategoriaModel;
import com.example.demov01.service.ICategoriaService;
import com.example.demov01.dto.CategoriaDto;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaImpl {

    @Autowired
    private ICategoriaService iCategoriaService;

    public List<CategoriaModel> getCategorias() {
        List<CategoriaDto> categoriaDtos = iCategoriaService.listCategoria();

        return categoriaDtos.stream()
                .map(categoriaDto -> new CategoriaModel(categoriaDto.getId(), categoriaDto.getName(), categoriaDto.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<CategoriaModel> getCategoriaById(Long id) {
        Optional<CategoriaDto> categoriaDto = iCategoriaService.findById(id);

        return categoriaDto.map(dto -> {
            CategoriaModel categoriaModel = new CategoriaModel();
            categoriaModel.setId(dto.getId());
            categoriaModel.setName(dto.getName());
            categoriaModel.setDescription(dto.getDescription());
            // Puedes mapear otros campos según sea necesario
            return categoriaModel;
        });
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

    public void deleteCategoria(Long id) {
        boolean categoriaIdExists = iCategoriaService.existsById(id);

        if (!categoriaIdExists) {
            try {
                throw new ValidationException("Categoría no existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }

        Optional<CategoriaDto> categoriaDtoOptional = iCategoriaService.findById(id);
        if (categoriaDtoOptional.isPresent()) {
            CategoriaDto categoriaDto = categoriaDtoOptional.get();
            categoriaDto.setDeleted(true);
            iCategoriaService.save(categoriaDto);
        } else {
            try {
                throw new ValidationException("Categoría no existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
