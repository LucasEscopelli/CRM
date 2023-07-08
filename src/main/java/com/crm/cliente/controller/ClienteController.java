package com.crm.cliente.controller;

import com.crm.cliente.dto.ClienteRequest;
import com.crm.cliente.model.Cliente;
import com.crm.cliente.repository.ClienteRepository;
import com.crm.model.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("crm/v1/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/{lancamentoId}")
    public ResponseEntity<?> addClientes(@PathVariable Long usuarioId, @RequestBody ClienteRequest cliente) {

        clienteService.saveCliente(cliente, usuarioId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getClientes() {

        List<Cliente> clientes = clienteRepository.findAll();

        return ResponseEntity.ok().body(clientes);
    }
}
