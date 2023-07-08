package com.crm.cliente.dto;

import lombok.Getter;

@Getter
public class ClienteRequest {
    private String nome;
    private String dataDeNascimento;
    private String cpfOuCnpj;
    private String email;
    private Long usuarioId;
}
