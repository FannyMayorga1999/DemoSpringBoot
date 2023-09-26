package com.example.demov01.service.impl;

import com.example.demov01.service.ICategoriaService;
import com.example.demov01.dto.CategoriaDto;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CategoriaImpl {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDto> getCategorias() {
        return categoriaService.findByDeletedFalse();
    }
}
