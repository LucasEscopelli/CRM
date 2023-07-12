package com.crm.cadastro.dto;

import com.crm.cadastro.TipoCadastro;
import lombok.Getter;

@Getter
public class CadastroRequest {
    private String nome;
    private Long dataDeNascimento;
    private String cpfOuCnpj;
    private String email;
    private TipoCadastro tipoCadastro;
}
