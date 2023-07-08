package com.crm.cliente.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;

//    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    private String nome;
    private String dataDeNascimento;
    private String cpfOuCnpj;
    private String email;
    private Long usuarioId;
}
