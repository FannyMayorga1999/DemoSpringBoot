package com.example.demov01.service;
import com.example.demov01.dto.CategoriaDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceService extends JpaRepository<CategoriaDto, Long> {
}
