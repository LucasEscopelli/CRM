package com.crm.fornecedor.service;

import com.crm.cliente.dto.ClienteRequest;
import com.crm.cliente.model.Cliente;
import com.crm.cliente.repository.ClienteRepository;
import com.crm.fornecedor.dto.FornecedorRequest;
import com.crm.fornecedor.model.Fornecedor;
import com.crm.fornecedor.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor getByIdOrThrow(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Nao existe fornecedor de ID %s", id)));
    }

    public Fornecedor saveFornecedor(FornecedorRequest fornecedorDto, Long usuarioId) {
        final Fornecedor fornecedor = builderFornecedor(fornecedorDto, usuarioId);
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizarFornecedor(FornecedorRequest fornecedor, Long clienteId){
        Fornecedor fornecedorAAtualizar = getByIdOrThrow(clienteId);

        Optional.ofNullable(cliente.getNome()).ifPresent(data -> fornecedorAAtualizar.setNome(data));
        Optional.ofNullable(cliente.getEmail()).ifPresent(data -> fornecedorAAtualizar.setEmail(data));
        Optional.ofNullable(cliente.getCpfOuCnpj()).ifPresent(data -> fornecedorAAtualizar.setCpfOuCnpj(data));
        Optional.ofNullable(cliente.getDataDeNascimento()).ifPresent(data -> fornecedorAAtualizar.setDataDeNascimento(data));

        Fornecedor fornecedorAtualizado = fornecedorRepository.save(fornecedorAAtualizar);

        return fornecedorAtualizado;
    }

    public static Fornecedor builderFornecedor(FornecedorRequest fornecedorRequest, Long usuarioId) {
        return Fornecedor.builder()
                .nome(fornecedorRequest.getNome())
                .descricao(fornecedorRequest.getDescricao())
                .cpfOuCnpj(fornecedorRequest.getCpfOuCnpj())
                .email(fornecedorRequest.getEmail())
                .dataDeNascimento(fornecedorRequest.getDataDeNascimento())
                .usuarioId(usuarioId)
                .build();
    }
}
