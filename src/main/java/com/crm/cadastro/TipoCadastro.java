package com.crm.cadastro;

public enum TipoCadastro {

    FORNECEDOR("Fornecedor"),
    CLIENTE("Fornecedor");

    private final String name;

    TipoCadastro(String name) {
        this.name = name;
    }
}
