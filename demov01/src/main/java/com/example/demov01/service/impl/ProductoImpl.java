package com.example.demov01.service.impl;

import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.dto.ProductoDto;
import com.example.demov01.exceptions.ValidationException;
import com.example.demov01.model.CategoriaModel;
import com.example.demov01.model.ProductosModel;
import com.example.demov01.service.ICategoriaService;
import com.example.demov01.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoImpl {
    @Autowired
    private IProductosService iProductosService;

    public List<ProductosModel> getProductos() {
        List<ProductoDto> productoDtos = iProductosService.listProductos();

        return productoDtos.stream()
                .map(productoDto -> {
                    String categoriaName = (productoDto.getCategoria() != null) ? productoDto.getCategoria().getName() : null;
                    return new ProductosModel(
                            productoDto.getId(),
                            productoDto.getName(),
                            productoDto.getDescription(),
                            categoriaName
                    );
                })
                .collect(Collectors.toList());
    }

    public Optional<ProductosModel> getProductosById(Long id) {
        Optional<ProductoDto> productoDto = iProductosService.findById(id);
        return productoDto.map(dto -> {
            ProductosModel productosModel = new ProductosModel();
            productosModel.setId(dto.getId());
            productosModel.setName(dto.getName());
            productosModel.setDescription(dto.getDescription());
            productosModel.setCategoria(dto.getCategoria().getName());
            // Puedes mapear otros campos según sea necesario
            return productosModel;
        });
    }

    /*public void createProduct(ProductosModel productosModel, Long categoriaId) {
        boolean identificationExistente = iProductosService.existsByName(productosModel.getName());

        if (identificationExistente) {
            throw new ValidationException("Producto ya existe");
        }

        ProductoDto productoDto = new ProductoDto();
        productoDto.setName(productosModel.getName());
        productoDto.setDescription(productosModel.getDescription());

        // Setear la categoría por ID
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(categoriaId);
        productoDto.setCategoria(categoriaDto);

        iProductosService.save(productoDto, categoriaId);
    }*/

    public void deleteProducto(Long id) {

        Optional<ProductoDto> productoDtoOptional = iProductosService.findById(id);
        if (productoDtoOptional.isPresent()) {
            ProductoDto productoDto = productoDtoOptional.get();
            productoDto.setDeleted(true);
            iProductosService.save(productoDto);
        } else {
            try {
                throw new ValidationException("Categoría no existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
