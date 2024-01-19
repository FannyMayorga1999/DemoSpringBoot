package com.example.demov01.controller;

import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.model.CategoriaModel;
import com.example.demov01.service.impl.CategoriaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Ayuda a dar permiso de cors para el consumo
@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

    @Autowired
    private CategoriaImpl categoriaImpl;

    @GetMapping("/list")
    public ResponseEntity<List<CategoriaModel>> obtenerCategorias() {
        try {
            List<CategoriaModel> categoriaModel = categoriaImpl.getCategorias();
            return new ResponseEntity<>(categoriaModel, HttpStatus.OK);
        } catch (Exception e) {
            // Si se produce una excepción, se maneja y se devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Long id) {
        Optional<CategoriaModel> categoria = categoriaImpl.getCategoriaById(id);

        if (categoria.isPresent()) {
            CategoriaModel categoriaModel = categoria.get();
            return new ResponseEntity<>(categoriaModel, HttpStatus.OK);
        } else {
            String mensaje = "Categoría no encontrada";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCategoria(@RequestBody CategoriaModel categoriaModel) {
        try {
            logger.info("Data recibida: {}", categoriaModel);
            categoriaImpl.createCategoria(categoriaModel);
            String jsonResponse = "{ \"mensaje\": \"Categoria creado correctamente\" }";
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage() + "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategoria(@PathVariable Long id, @RequestBody CategoriaModel categoriaModel) {
        try {
            logger.info("Data recibida: {}", categoriaModel);
            categoriaImpl.updateCategoria(id , categoriaModel);
            String jsonResponse = "{ \"mensaje\": \"Categoria actualizado correctamente\" }";
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaImpl.deleteCategoria(id);
            String jsonResponse = "{ \"mensaje\": \"Categoria eliminado correctamente\" }";
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
