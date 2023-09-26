package com.example.demov01.service;

import com.example.demov01.dto.CategoriaDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ICategoriaService extends JpaRepository<CategoriaDto, Long> {
    List<CategoriaDto> findByDeletedFalse();
}
