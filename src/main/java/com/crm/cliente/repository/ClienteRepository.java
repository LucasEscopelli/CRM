package com.crm.cliente.repository;

import com.crm.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT t FROM Cliente t WHERE t.usuarioId = :usuarioId")
    public List<Cliente> getClientesByUsuarioId(Long usuarioId);
}
