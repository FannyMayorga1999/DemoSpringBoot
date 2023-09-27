package com.example.demov01.service.impl;

import com.example.demov01.dto.ClientDto;
import com.example.demov01.exceptions.ValidationException;
import com.example.demov01.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl
{
    @Autowired
    private IClientService iClientService;

    @GetMapping
    public List<ClientDto> getAllClients() {
        return iClientService.listClient();
    }

    @PutMapping
    public void deleteById(Long id) {
        iClientService.deleteClient(id);
    }

    @GetMapping
    public Optional<ClientDto> getClientById(Long id) {
        return iClientService.findById(id);
    }

    @PutMapping
    public void deleteClient(ClientDto client) {
        iClientService.save(client);
    }

    @PutMapping
    public void updateClient(ClientDto client) {
        iClientService.save(client);
    }

    @PostMapping
    public void createClient(ClientDto client) {
        boolean identificationExistente = iClientService.existsByIdentification(client.getIdentification());

        if (identificationExistente) {
            try {
                throw new ValidationException("Identificación ya existe");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        iClientService.save(client);
    }
}
