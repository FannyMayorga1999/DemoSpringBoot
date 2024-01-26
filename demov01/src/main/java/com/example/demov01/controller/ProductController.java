package com.example.demov01.controller;


import com.example.demov01.model.CategoriaModel;
import com.example.demov01.model.ProductosModel;
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
    public ResponseEntity<List<ProductosModel>> obtenerProductos() {
        try {
            List<ProductosModel> productosModel = productoImpl.getProductos();
            return new ResponseEntity<>(productosModel, HttpStatus.OK);
        } catch (Exception e) {
            // Si se produce una excepción, se maneja y se devuelve una respuesta de error
            logger.error("Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        Optional<ProductosModel> producto = productoImpl.getProductosById(id);

        if (producto.isPresent()) {
            ProductosModel productosModel = producto.get();
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            String mensaje = "Producto no encontrada";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
        try {
            productoImpl.deleteProducto(id);
            String jsonResponse = "{ \"mensaje\": \"Producto eliminado correctamente\" }";
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage()+ "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@PostMapping
    public ResponseEntity<String> createProducto(@RequestBody ProductosModel productosModel,
                                                 @RequestParam Long categoriaId) {
        try {
            productoImpl.createProduct(productosModel, categoriaId);
            String jsonResponse = "{ \"mensaje\": \"Producto creado correctamente\" }";
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            String jsonResponse = "{ \"Error\":" + e.getMessage() + "}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}
