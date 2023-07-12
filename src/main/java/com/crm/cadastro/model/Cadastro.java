package com.crm.cadastro.model;

import com.crm.cadastro.TipoCadastro;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cadastro {

    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    private String nome;
    private String descricao;
    private Long dataDeNascimento;
    private int atualAge;
    private String cpfOuCnpj;

    private String email;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoCadastro tipoCadastro;


    public boolean isCnpj() {
        // Remove todos os caracteres não numéricos
        String digits = cpfOuCnpj.replaceAll("[^\\d]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (digits.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (digits.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Validação do CNPJ utilizando o dígito verificador
        int[] factors = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] cnpjDigits = digits.chars().map(Character::getNumericValue).toArray();

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += cnpjDigits[i] * factors[i];
        }

        int remainder = (sum % 11) < 2 ? 0 : 11 - (sum % 11);

        return cnpjDigits[12] == remainder;
    }

    public boolean isCpf() {
        // Remove todos os caracteres não numéricos
        String digits = cpfOuCnpj.replaceAll("[^\\d]", "");

        // Verifica se o CPF tem 11 dígitos
        if (digits.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (digits.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validação do CPF utilizando os dígitos verificadores
        int[] cpfDigits = digits.chars().map(Character::getNumericValue).toArray();

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += cpfDigits[i] * (10 - i);
        }

        int firstDigit = (sum % 11) < 2 ? 0 : 11 - (sum % 11);
        if (cpfDigits[9] != firstDigit) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += cpfDigits[i] * (11 - i);
        }

        int secondDigit = (sum % 11) < 2 ? 0 : 11 - (sum % 11);

        return cpfDigits[10] == secondDigit;
    }

}
