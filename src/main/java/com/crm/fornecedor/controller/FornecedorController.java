package com.crm.fornecedor.controller;

import com.crm.fornecedor.dto.FornecedorRequest;
import com.crm.fornecedor.model.Fornecedor;
import com.crm.fornecedor.repository.FornecedorRepository;
import com.crm.fornecedor.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @PostMapping("/addFornecedor/{usuarioId}")
    public ResponseEntity<?> addFornecedor(@PathVariable Long usuarioId, @RequestBody FornecedorRequest fornecedor) {

        Fornecedor fornecedorSalvo = fornecedorService.saveFornecedor(fornecedor, usuarioId);
        return ResponseEntity.ok().body(fornecedorSalvo);
    }

    @PatchMapping("/atualizarFornecedor/{fornecedorId}")
    public ResponseEntity<?> atualizarFornecedor(@PathVariable Long fornecedorId, @RequestBody FornecedorRequest fornecedor) {
        try{
            Fornecedor fornecedorAtualizado = fornecedorService.atualizarFornecedor(fornecedor, fornecedorId);

            return ResponseEntity.ok().body(fornecedorAtualizado);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado.");
        }
    }

    @GetMapping("/getAllFornecedores")
    public ResponseEntity<?> getFornecedores() {

        List<Fornecedor> fornecedores = fornecedorRepository.findAll();

        return ResponseEntity.ok().body(fornecedores);
    }

    @GetMapping("/getFornecedorById/{fornecedorId}")
    public ResponseEntity<?> getFornecedorById(@PathVariable Long fornecedorId) {
        try{
            Fornecedor fornecedor = fornecedorService.getByIdOrThrow(fornecedorId);

            return ResponseEntity.ok().body(fornecedor);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @GetMapping("/getFornecedoresByUsuarioId/{usuarioId}")
    public ResponseEntity<?> getFornecedoresByUsuarioId(@PathVariable Long usuarioId) {
        List<Fornecedor> fornecedoresDoUsuario = fornecedorRepository.getFornecedoresByUsuarioId(usuarioId);
        return ResponseEntity.ok().body(fornecedoresDoUsuario);
    }

    @DeleteMapping("/deleteFornecedor/{fornecedorId}")
    public ResponseEntity<?> deleteFornecedor(@PathVariable Long fornecedorId){
        try{
            Fornecedor fornecedor = fornecedorService.getByIdOrThrow(fornecedorId);

            fornecedorRepository.delete(fornecedor);

            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
