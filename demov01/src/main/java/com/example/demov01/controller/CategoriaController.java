package com.example.demov01.controller;
import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.model.CategoriaModel;
import com.example.demov01.service.impl.CategoriaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

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
ng 
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
                //return new ResponseEntity<>("Categoria actualizado correctamente", HttpStatus.OK);

                String jsonResponse = "{ \"mensaje\": \"Categoria actualizado correctamente\" }";
                return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Categoria no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
                //return new ResponseEntity<>("Categoria eliminado correctamente", HttpStatus.OK);
                String jsonResponse = "{ \"mensaje\": \"Categoria eliminado correctamente\" }";
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            } else {
                //return new ResponseEntity<>("Categoria no encontrado", HttpStatus.NOT_FOUND);
                String jsonResponse = "{ \"mensaje\": \"Categoria no encontrado\" }";
                return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCategoria(@RequestBody CategoriaDto categoria) {
        try {
            logger.info("Data recibida: {}", categoria);
            categoriaImpl.createCategoria(categoria);
            //return new ResponseEntity<>("Categoria creado correctamente", HttpStatus.CREATED);

            String jsonResponse = "{ \"mensaje\": \"Categoria creado correctamente\" }";

            // Devolver una respuesta con el código de estado 201 (Created) y el JSON
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        } catch (Exception e) {

            //return new ResponseEntity<>("Error al crear la categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
