package com.example.demov01.controller;

import com.example.demov01.dto.ClientDto;
import com.example.demov01.model.ClientsModel;
import com.example.demov01.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Ayuda a dar permiso de cors para el consumo
@CrossOrigin(origins ={ "http://localhost:4200"})
@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClientServiceImpl clientService;
    private Long id;
    private ClientsModel clientModel;

    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> getAllClients() {

        try {
            List<ClientDto> clientDto = clientService.getAllClients();
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        } catch (Exception e) {
            // Si se produce una excepción, se maneja y se devuelve una respuesta de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Optional<ClientDto> productoOptional = clientService.getClientById(id);

        if (productoOptional.isPresent()) {
            ClientDto clientDto = productoOptional.get();
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody ClientsModel clientsModel) {
        try {
            Optional<ClientDto> productoOptional = clientService.getClientById(id);
            if (productoOptional.isPresent()) {
                ClientDto clientDto = productoOptional.get();
                clientDto.setName(clientsModel.getName());
                clientDto.setEmail(clientsModel.getEmail());
                clientDto.setPhone(clientsModel.getPhone());
                clientService.updateClient(clientDto);
                return new ResponseEntity<>("Cliente actualizado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            Optional<ClientDto> productoOptional = clientService.getClientById(id);
            if (productoOptional.isPresent()) {
                ClientDto clientDto = productoOptional.get();
                clientDto.setDeleted(true);
                clientService.deleteClient(clientDto);
                return new ResponseEntity<>("Cliente eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminado el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto) {
        try {
            clientService.createClient(clientDto);
            return new ResponseEntity<>("Cliente creado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cliente el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
