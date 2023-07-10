package com.crm.cliente.controller;

import com.crm.cliente.dto.ClienteRequest;
import com.crm.cliente.model.Cliente;
import com.crm.cliente.repository.ClienteRepository;
import com.crm.model.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("crm/v1/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/addCliente/{usuarioId}")
    public ResponseEntity<?> addClientes(@PathVariable Long usuarioId, @RequestBody ClienteRequest cliente) {

        Cliente clienteSalvo = clienteService.saveCliente(cliente, usuarioId);
        return ResponseEntity.ok().body(clienteSalvo);
    }

    @PatchMapping("/atualizarCliente/{clienteId}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteRequest cliente) {
        try{
            Cliente clienteAtualizado = clienteService.atualizarCliente(cliente, clienteId);

            return ResponseEntity.ok().body(clienteAtualizado);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @GetMapping("/getAllClientes")
    public ResponseEntity<?> getClientes() {

        List<Cliente> clientes = clienteRepository.findAll();

        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/getClienteById/{clienteId}")
    public ResponseEntity<?> getClienteById(@PathVariable Long clienteId) {
        try{
            Cliente cliente = clienteService.getByIdOrThrow(clienteId);

            return ResponseEntity.ok().body(cliente);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @GetMapping("/getClientesByUsuario/{usuarioId}")
    public ResponseEntity<?> getClientesByUsuarioId(@PathVariable Long usuarioId) {
        List<Cliente> clientesDoUsuario = clienteRepository.getClientesByUsuarioId(usuarioId);
        return ResponseEntity.ok().body(clientesDoUsuario);
    }

    @DeleteMapping("/deleteCliente/{clienteId}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long clienteId){
        try{
        Cliente cliente = clienteService.getByIdOrThrow(clienteId);

        clienteRepository.delete(cliente);

        return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
