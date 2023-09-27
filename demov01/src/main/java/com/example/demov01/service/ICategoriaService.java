package com.example.demov01.service;

import com.example.demov01.dto.CategoriaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ICategoriaService extends JpaRepository<CategoriaDto, Long> {
    boolean existsByName(String name);
    @Query("SELECT cd FROM CategoriaDto cd WHERE cd.deleted = false ORDER BY cd.name ASC")
    List<CategoriaDto> listCategoria();

    @Modifying
    @Query("UPDATE FROM CategoriaDto SET deleted = 1 WHERE  id =  :id")
    void deleteCategoria(@Param("id") Long id);
}
