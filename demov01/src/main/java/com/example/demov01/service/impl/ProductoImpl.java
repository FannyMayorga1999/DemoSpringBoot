package com.example.demov01.service.impl;

import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.dto.ProductoDto;
import com.example.demov01.service.ICategoriaService;
import com.example.demov01.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoImpl {
    @Autowired
    private IProductosService iProductosService;

    @GetMapping
    public List<ProductoDto> listProductos() {
        return iProductosService.listProductos();
    }

    @GetMapping
    public Optional<ProductoDto> getProductoById(Long id) {
        return iProductosService.findById(id);
    }

    @PutMapping
    public void deleteProductos(ProductoDto productoDto) {
        iProductosService.save(productoDto);
    }
}
