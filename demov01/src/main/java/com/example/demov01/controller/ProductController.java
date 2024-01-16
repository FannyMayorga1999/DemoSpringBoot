package com.example.demov01.controller;

import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.dto.ProductoDto;
import com.example.demov01.model.CategoriaModel;
import com.example.demov01.service.impl.ProductoImpl;

import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

//Ayuda a dar permiso de cors para el consumo
@CrossOrigin(origins ={ "http://localhost:4200/"})
@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private ProductoImpl productoImpl;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/list")
    public ResponseEntity<List<ProductoDto>> obtenerProductos() {
        try {
            List<ProductoDto> productoDto = productoImpl.listProductos();
            return new ResponseEntity<>(productoDto, HttpStatus.OK);
        } catch (Exception e) {
            // Si se produce una excepción, se maneja y se devuelve una respuesta de error
            logger.error("Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        Optional<ProductoDto> productoDto = productoImpl.getProductoById(id);

        if (productoDto.isPresent()) {
            ProductoDto producto = productoDto.get();
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createProductos(@RequestBody ProductoDto productoDto, Long CategoriaId) {
        try {
            logger.info("Data recibida: {}", productoDto);
            productoImpl.createProducto(productoDto);
            //return new ResponseEntity<>("Categoria creado correctamente", HttpStatus.CREATED);

            String jsonResponse = "{ \"mensaje\": \"Producto creado correctamente\" }";

            // Devolver una respuesta con el código de estado 201 (Created) y el JSON
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        } catch (Exception e) {

            //return new ResponseEntity<>("Error al crear la categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody ProductosModel productosModel) {
        try {
            Optional<ProductoDto> productoDto = productoImpl.getProductoById(id);
            if (productoDto.isPresent()) {
                ProductoDto productoDto = categoria.get();
                productosModel.setName(categoriaModel.getName());
                productosModel.setDescription(categoriaModel.getDescription());
                productosModel.updateCategoria(categoriaDto);
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
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
        try {
            Optional<ProductoDto> productoDto = productoImpl.getProductoById(id);
            if (productoDto.isPresent()) {
                ProductoDto producto = productoDto.get();
                producto.setDeleted(true);
                productoImpl.deleteProductos(producto);
                //return new ResponseEntity<>("Categoria eliminado correctamente", HttpStatus.OK);
                String jsonResponse = "{ \"mensaje\": \"Producto eliminado correctamente\" }";
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            } else {
                //return new ResponseEntity<>("Categoria no encontrado", HttpStatus.NOT_FOUND);
                String jsonResponse = "{ \"mensaje\": \"Producto no encontrado\" }";
                return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
