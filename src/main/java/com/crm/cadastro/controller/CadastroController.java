package com.crm.cadastro.controller;

import com.crm.cadastro.dto.CadastroRequest;
import com.crm.cadastro.model.Cadastro;
import com.crm.cadastro.repository.CadastroRepository;
import com.crm.cadastro.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("crm/v1/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;
    @Autowired
    private CadastroRepository cadastroRepository;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Cadastro> addCadastro(@RequestBody CadastroRequest cliente) {

        Cadastro clienteSalvo = cadastroService.saveCliente(cliente);
        return ResponseEntity.ok().body(clienteSalvo);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<?> atualizarCadastro(@PathVariable Long clienteId, @RequestBody CadastroRequest cliente) {
        try {
            Cadastro clienteAtualizado = cadastroService.atualizarCliente(cliente, clienteId);

            return ResponseEntity.ok().body(clienteAtualizado);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getCadastros() {
        return ResponseEntity.ok().body(cadastroRepository.findAll());
    }

    @GetMapping("{tipoCadastro}")
    public ResponseEntity<?> getCadastros(@PathVariable String tipoCadastro) {

        List<Cadastro> clientes = cadastroRepository.findAllByTipoCadastro(tipoCadastro);

        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> getCadastroById(@PathVariable Long clienteId) {
        try {
            Cadastro cliente = cadastroService.getByIdOrThrow(clienteId);

            return ResponseEntity.ok().body(cliente);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }


    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long clienteId) {
        try {

            Cadastro cliente = cadastroService.getByIdOrThrow(clienteId);

            cadastroRepository.delete(cliente);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
