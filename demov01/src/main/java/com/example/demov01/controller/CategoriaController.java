package com.example.demov01.controller;
import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.model.CategoriaModel;
import com.example.demov01.service.impl.CategoriaImpl;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Ayuda a dar permiso de cors para el consumo
@CrossOrigin(origins ={ "http://localhost:4200/"})
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaImpl categoriaImpl;

    @GetMapping("/list")
    public ResponseEntity<List<CategoriaDto>> obtenerCategorias() {
        try {
            List<CategoriaDto> categoriasDto = categoriaImpl.getCategorias();
            return new ResponseEntity<>(categoriasDto, HttpStatus.OK);
        } catch (Exception e) {
            // Si se produce una excepción, se maneja y se devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Long id) {
        Optional<CategoriaDto> categoria = categoriaImpl.getCategoriaById(id);

        if (categoria.isPresent()) {
            CategoriaDto categoriaDto = categoria.get();
            return new ResponseEntity<>(categoriaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategoria(@PathVariable Long id, @RequestBody CategoriaModel categoriaModel) {
        try {
            Optional<CategoriaDto> categoria = categoriaImpl.getCategoriaById(id);
            if (categoria.isPresent()) {
                CategoriaDto categoriaDto = categoria.get();
                categoriaDto.setName(categoriaModel.getName());
                categoriaDto.setDescription(categoriaModel.getDescription());
                categoriaImpl.updateCategoria(categoriaDto);
                return new ResponseEntity<>("Categoria actualizado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoria no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
        try {
            Optional<CategoriaDto> categoria = categoriaImpl.getCategoriaById(id);
            if (categoria.isPresent()) {
                CategoriaDto categoriaDto = categoria.get();
                categoriaDto.setDeleted(true);
                categoriaImpl.deleteCategoria(categoriaDto);
                return new ResponseEntity<>("Categoria eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoria no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminado la categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCategoria(@RequestBody CategoriaDto categoria) {
        try {
            categoriaImpl.createCategoria(categoria);
            return new ResponseEntity<>("Categoria creado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
