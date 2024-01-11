package com.example.demov01.service;

import com.example.demov01.dto.CategoriaDto;
import com.example.demov01.dto.ProductoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductosService extends JpaRepository<ProductoDto, Long>{
    @Query("SELECT p FROM ProductoDto p INNER JOIN FETCH p.categoria WHERE p.deleted = false ORDER BY p.name ASC")
    List<ProductoDto> listProductos();

    @Query("SELECT p FROM ProductoDto p INNER JOIN FETCH p.categoria WHERE p.id = :id")
    Optional<ProductoDto> findById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE FROM ProductoDto SET deleted = 1 WHERE  id =  :id")
    void deleteProductos(@Param("id") Long id);
}
