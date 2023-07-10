package com.crm.fornecedor.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
@Entity
@Table(name = "fornecedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fornecedor {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    private String nome;
    private String descricao;
    private String dataDeNascimento;
    private String cpfOuCnpj;
    private String email;
    private Long usuarioId;
}
