package com.crm.model.cliente.service;


import com.crm.cliente.dto.ClienteRequest;
import com.crm.cliente.model.Cliente;
import com.crm.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente getByIdOrThrow(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Nao existe cliente de ID %s", id)));
    }

    public Cliente saveCliente(ClienteRequest clienteDto, Long usuarioId) {
        final Cliente cliente = builderCliente(clienteDto, usuarioId);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(ClienteRequest cliente, Long clienteId){
        Cliente clienteAAtualizar = getByIdOrThrow(clienteId);

        Optional.ofNullable(cliente.getNome()).ifPresent(data -> clienteAAtualizar.setNome(data));
        Optional.ofNullable(cliente.getEmail()).ifPresent(data -> clienteAAtualizar.setEmail(data));
        Optional.ofNullable(cliente.getCpfOuCnpj()).ifPresent(data -> clienteAAtualizar.setCpfOuCnpj(data));
        Optional.ofNullable(cliente.getDataDeNascimento()).ifPresent(data -> clienteAAtualizar.setDataDeNascimento(data));

        Cliente clienteAtualizado = clienteRepository.save(clienteAAtualizar);

        return clienteAtualizado;
    }

    public static Cliente builderCliente(ClienteRequest clienteRequest, Long usuarioId) {
        return Cliente.builder()
                .nome(clienteRequest.getNome())
                .cpfOuCnpj(clienteRequest.getCpfOuCnpj())
                .email(clienteRequest.getEmail())
                .dataDeNascimento(clienteRequest.getDataDeNascimento())
                .usuarioId(usuarioId)
                .build();
    }


}
