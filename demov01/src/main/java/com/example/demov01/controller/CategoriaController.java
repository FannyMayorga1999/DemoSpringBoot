package com.example.demov01.controller;
import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.service.impl.CategoriaImpl;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaImpl categoriaService;

    @GetMapping("/list")
    public ResponseEntity<List<CategoriaDto>> obtenerCategorias() {
        try {
            List<CategoriaDto> categorias = categoriaService.getCategorias();
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (Exception e) {
            // Si se produce una excepción, se maneja y se devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
