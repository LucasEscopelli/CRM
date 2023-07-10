package com.crm.fornecedor.dto;

import lombok.Getter;

@Getter
public class FornecedorRequest {
    private String nome;
    private String descricao;
    private String dataDeNascimento;
    private String cpfOuCnpj;
    private String email;
}
