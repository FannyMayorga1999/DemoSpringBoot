package com.example.demov01.service;

import com.example.demov01.dto.CategoriaDto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService extends JpaRepository<CategoriaDto, Long> {

    boolean existsByName(String name);

    @Query("SELECT new com.example.demov01.dto.CategoriaDto(cd.id, cd.name, cd.description) FROM CategoriaDto cd WHERE cd.deleted = false ORDER BY cd.name ASC")
    List<CategoriaDto> listCategoria();

    @Query("SELECT new com.example.demov01.dto.CategoriaDto(cd.id, cd.name, cd.description) FROM CategoriaDto cd WHERE cd.id = :id")
    Optional<CategoriaDto> findById(@Param("id") Long id);

    boolean existsById(Long id);

    @Modifying
    @Query("UPDATE FROM CategoriaDto SET deleted = true WHERE id = :id")
    void deleteCategoria(@Param("id") Long id);
}
