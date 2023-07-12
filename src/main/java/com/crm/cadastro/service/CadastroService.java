package com.crm.cadastro.service;


import com.crm.cadastro.dto.CadastroRequest;
import com.crm.cadastro.model.Cadastro;
import com.crm.cadastro.repository.CadastroRepository;
import com.crm.util.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository clienteRepository;

    public Cadastro getByIdOrThrow(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Nao existe cliente de ID %s", id)));
    }

    public Cadastro saveCliente(CadastroRequest clienteDto) {
        Check.notNull(clienteDto.getTipoCadastro(), "Tipo de cadastro nao pode ser null");
        Check.notNull(clienteDto.getCpfOuCnpj(), "CPF ou CNPJ nao pode ser null");
        Check.notNull(clienteDto.getNome(), "Nome nao pode ser null");
        Check.notNull(clienteDto.getDataDeNascimento(), "Data de nascimento nao pode ser null");

        final Cadastro cliente = builderCadastro(clienteDto);
        return clienteRepository.save(cliente);
    }

    public Cadastro atualizarCliente(CadastroRequest cliente, Long clienteId){
        Cadastro clienteAAtualizar = getByIdOrThrow(clienteId);

        Optional.ofNullable(cliente.getNome()).ifPresent(clienteAAtualizar::setNome);
        Optional.ofNullable(cliente.getEmail()).ifPresent(clienteAAtualizar::setEmail);
        Optional.ofNullable(cliente.getCpfOuCnpj()).ifPresent(clienteAAtualizar::setCpfOuCnpj);
        Optional.ofNullable(cliente.getDataDeNascimento()).ifPresent(clienteAAtualizar::setDataDeNascimento);

        return clienteRepository.save(clienteAAtualizar);
    }

    public static Cadastro builderCadastro(CadastroRequest clienteRequest) {
        return Cadastro.builder()
                .nome(clienteRequest.getNome())
                .cpfOuCnpj(clienteRequest.getCpfOuCnpj())
                .email(clienteRequest.getEmail())
                .dataDeNascimento(clienteRequest.getDataDeNascimento())
                .tipoCadastro(clienteRequest.getTipoCadastro())
                .build();
    }


}
