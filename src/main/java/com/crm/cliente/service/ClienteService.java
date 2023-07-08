package com.crm.model.cliente.service;


import com.crm.cliente.dto.ClienteRequest;
import com.crm.cliente.model.Cliente;
import com.crm.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(ClienteRequest clienteDto, Long usuarioId) {
        final Cliente cliente = builderCliente(clienteDto, usuarioId);
        return clienteRepository.save(cliente);
    }

    public static Cliente builderCliente(ClienteRequest clienteRequest, Long usuarioId) {
        return Cliente.builder()
                .nome(clienteRequest.getNome())
                .cpfOuCnpj(clienteRequest.getCpfOuCnpj())
                .email(clienteRequest.getEmail())
                .dataDeNascimento(clienteRequest.getDataDeNascimento())
                .usuarioId(clienteRequest.getUsuarioId())
                .build();
    }


}
