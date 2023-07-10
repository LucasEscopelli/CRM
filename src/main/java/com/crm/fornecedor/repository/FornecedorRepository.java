package com.crm.fornecedor.repository;

import com.crm.cliente.model.Cliente;
import com.crm.fornecedor.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    @Query("SELECT t FROM Fornecedor t WHERE t.usuarioId = :usuarioId")
    public List<Fornecedor> getFornecedoresByUsuarioId(Long usuarioId);

}
