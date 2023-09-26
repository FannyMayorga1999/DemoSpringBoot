package com.example.demov01.service;

import com.example.demov01.dto.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientService extends JpaRepository<ClientDto, Long> {
    List<ClientDto> findByDeletedFalse();

    @Modifying
    @Query("UPDATE FROM ClientDto SET deleted = 1 WHERE  id =  :id")
    void deleteClient(@Param("id") Long id);
}
